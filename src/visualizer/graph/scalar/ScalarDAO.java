package visualizer.graph.scalar;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import visualizer.graph.Graph;
import visualizer.graph.Vertex;
import visualizer.util.PExConstants;

public class ScalarDAO
{
	private Charset charset = Charset.defaultCharset();

	public static final String FILENAME_EXTENSION = ".scalar";
	
	public static final String SEPARATOR = ";";

	public static final String REPLACEMENT = "_";

	public static final String COMMENT = "#";

	public Charset getCharset() {
		return charset;
	}

	public void setCharset(Charset charset) {
		this.charset = charset;
	}

	public void importScalars(Graph graph, String filename) {
		File file = new File(filename);
		importScalars(graph, file);
	}

	public void importScalars(Graph graph, File file) {
		if (!file.exists()) {
			throw new IllegalArgumentException("File does not exist");
		}
		if (!file.isFile()) {
			throw new IllegalArgumentException("File is not a file!");
		}

		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException(e);
		}
		InputStreamReader isr = new InputStreamReader(fis, charset);
		importScalars(graph, isr);
	}

	public void importScalars(Graph graph, InputStreamReader isr) {
		BufferedReader in = null;
		Set<String> scalarsName = new LinkedHashSet<String>();
		Set<String> verticesName = new LinkedHashSet<String>();
		Map<String, float[]> vertexScalars = new HashMap<String, float[]>();

		try {
			in = new BufferedReader(isr);
			int linenumber = 0;
			String line = null;

			// Get scalar's names
			while ((line = in.readLine()) != null) {
				linenumber++;

				// ignore comments
				if (line.startsWith(COMMENT)) {
					continue;
				}
				if (line.trim().length() == 0) {
					continue;
				}

				// Effectively read the scalars
				StringTokenizer t = new StringTokenizer(line, SEPARATOR);
				while (t.hasMoreTokens()) {
					String scalar = t.nextToken().trim();
					if (scalarsName.contains(scalar)) {
						throw new IllegalArgumentException("Line " + linenumber + ": Found duplicated scalar: " + scalar);
					}
					if (scalar.isEmpty()) {
						throw new IllegalArgumentException("Line " + linenumber + ": Not scalar name was set");
					}
					scalarsName.add(scalar);
				}
				break;
			}

			// Read the scalars for each vertex
			while ((line = in.readLine()) != null) {
				linenumber++;
				// ignore comments
				if (line.startsWith(COMMENT)) {
					continue;
				}
				if (line.trim().length() == 0) {
					continue;
				}
	
				StringTokenizer t = new StringTokenizer(line, SEPARATOR, false);
				if (t.countTokens() != (scalarsName.size() + 1)) {
					throw new IllegalArgumentException("Line " + linenumber + ": The number of values does not match with the number of declared scalars");
				}

				// Read the vertex id
				String vertexId = t.nextToken();
				if (verticesName.contains(vertexId)) {
					throw new IllegalArgumentException("Line " + linenumber + ": Found duplicated scalars for the vertex: " + vertexId);
				}
				verticesName.add(vertexId);
				
				// Read the scalar values
				float[] values = new float[scalarsName.size()];
				for (int i = 0; i < values.length; i++) {
					String token = t.nextToken();
					float value = 0;
					try {
						value = Float.parseFloat(token);
					} catch (NumberFormatException nfe) {
						throw new IllegalArgumentException("Line " + linenumber	+ ": Invalid number: " + token);
					}
					values[i] = value;
				}
				vertexScalars.put(vertexId, values);
			}
		} catch (IOException e) {
			throw new IllegalArgumentException("Error reading data", e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException ex) {
				}
			}
		}
			
		// Add scalars to the graph
		Iterator<String> i = scalarsName.iterator();
		while (i.hasNext()) {
			String scalarName = i.next();
			graph.addScalar(scalarName);
		}
		
		// Adding the scalar values to the vertexes
		i = verticesName.iterator();
		while (i.hasNext()) {
			String vertexId = i.next();
			Vertex vertex = graph.getVertexByURL(vertexId);
			if (vertex == null) {
				System.err.println("Scalar assigned to an inexistent vertex: " + vertexId);
				continue;
			}
			float[] values = vertexScalars.get(vertexId);
	
			Iterator<String> iScalar = scalarsName.iterator();
			for (int iValue = 0; iScalar.hasNext(); iValue++) {
				String scalarName = iScalar.next();
				Scalar scalar = graph.getScalarByName(scalarName);
				vertex.setScalar(scalar, values[iValue]);
			}
		}
	}

	public void exportScalars(Graph graph, String filename) {
		File file = new File(filename);
		exportScalars(graph, file);
	}

	public void exportScalars(Graph graph, File file) {
		if (!file.exists() || !file.isFile()) {
			throw new IllegalArgumentException("File does not exist");
		}

		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException(e);
		}
		OutputStreamWriter osw = new OutputStreamWriter(fos, charset);
		exportScalars(graph, osw);
	}

	public void exportScalars(Graph graph, OutputStreamWriter osw) {
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(osw);

			// Write scalar names
			List<Scalar> scalars = graph.getScalars();
			Iterator<Scalar> i = scalars.iterator();
			while (i.hasNext()) {
				Scalar scalar = i.next();
				String name = scalar.getName();
				if (name.equals(PExConstants.DOTS)) {
					continue;
				}
				// Quietly replace all ';' by '_' (as the values are separated
				// by ';').
				out.write(name.replaceAll(SEPARATOR, REPLACEMENT));
				if (i.hasNext()) {
					out.write(";");
				}
			}
			out.newLine();

			// Write the scalar values for each vertex
			for (Vertex v : graph.getVertex()) {
				if (!v.isValid()) {
					continue;
				}

				out.write(v.getUrl());
				out.write(SEPARATOR);
				i = scalars.iterator();
				for (int iValue = 0; i.hasNext(); iValue++) {
					Scalar scalar = i.next();
					String scalarName = scalar.getName();
					if (scalarName.equals(PExConstants.DOTS)) {
						continue;
					}
					double scalarValue = v.getScalar(scalar);
					out.write(Double.toString(scalarValue));
					if (i.hasNext()) {
						out.write(";");
					}
				}
				out.newLine();
			}
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		} finally {
			if (out != null) {
				try {
					out.flush();
					out.close();
				} catch (IOException ex) {
				}
			}
		}
	}
}
