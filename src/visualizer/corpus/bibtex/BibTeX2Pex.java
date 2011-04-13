package visualizer.corpus.bibtex;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipOutputStream;

import com.ironiacorp.sysrev.selection.Argument;
import com.ironiacorp.sysrev.selection.PublicationSelectionStatus;
import com.ironiacorp.sysrev.selection.Reason;
import com.ironiacorp.sysrev.selection.Status;

import net.sf.jabref.BibtexEntry;
import net.sf.jabref.imports.BibtexParser;
import net.sf.jabref.imports.ParserResult;

public class BibTeX2Pex
{
	private HashMap<String, BibtexEntry> cache;
	
	private Set<Integer> numbers;
	
	private static final String BIBTEX_EXTENSION = ".bib";

	private static final String PEX_DOCUMENT_EXTENSION = ".zip";

	private static final String PEX_SCALAR_EXTENSION = ".scalar";

	private String baseDir;

	private String name;

	private File bibtexFile;

	private File pexFile;

	private File scalarFile;

	private String[] fieldsToImport = { "title", "abstract" };

	private int i = 0;

	
	public BibTeX2Pex()
	{
		cache = new HashMap<String, BibtexEntry>();
	}
	
	public void setBibtexFile(File file)
	{
		if (file == null || !file.exists()) {
			throw new IllegalArgumentException("Invalid BibTeX file");
		}

		numbers = new HashSet<Integer>();
		baseDir = file.getParent();
		name = file.getName();
		name = name.substring(0, name.lastIndexOf("."));

		this.bibtexFile = new File(baseDir, name + BIBTEX_EXTENSION);
		this.pexFile = new File(baseDir, name + PEX_DOCUMENT_EXTENSION);
		this.scalarFile = new File(baseDir, name + PEX_SCALAR_EXTENSION);
	}
	

	public File getBibtexFile()
	{
		return bibtexFile;
	}

	public File getPexFile()
	{
		return pexFile;
	}

	public File getScalarFile()
	{
		return scalarFile;
	}
	
	private BibtexEntry findInCache(BibtexEntry bibtexEntry)
	{
		String title = bibtexEntry.getField("title").toLowerCase();
		String doi = bibtexEntry.getField("doi");
		String key = title;
		if (doi != null) {
			key += doi.toLowerCase();
		}

		if (cache.containsKey(key)) {
			bibtexEntry = cache.get(key);
		} else {
			bibtexEntry.setField("pexid", Integer.toString(i++));
			cache.put(key, bibtexEntry);
			numbers.add(i);
		}
	
		return bibtexEntry;
	}

	public void convert() throws IOException
	{
		// Write scalar names
		BufferedWriter scalarwriter = new BufferedWriter(new FileWriter(scalarFile));
      	scalarwriter.append("year;status;selected;excluded");
        scalarwriter.newLine();
        
		ZipOutputStream zipfile = new ZipOutputStream(new FileOutputStream(pexFile));
		ParserResult result = BibtexParser.parse(new FileReader(bibtexFile));
		Collection<BibtexEntry> entries = result.getDatabase().getEntries();
		Iterator<BibtexEntry> iterator = entries.iterator();
		while (iterator.hasNext()) {
			BibtexEntry bibtexEntry = iterator.next();
			bibtexEntry = findInCache(bibtexEntry);
			String fileName = bibtexEntry.getField("pexid").concat(".txt");
			StringWriter writer = new StringWriter();
			BufferedWriter buffer = new BufferedWriter(writer);
			for (String field : fieldsToImport) {
				String value = bibtexEntry.getField(field);
				if (value != null && ! value.trim().isEmpty()) {
					if ("keywords".equals(field)) {
						writer.append("Keywords: ");
					}
					buffer.append(value);
					buffer.append("\n");
					buffer.append("\n");
					buffer.flush();
				}
			}
			buffer.flush();
			buffer.close();
			
			writer.flush();
			writer.close();
			
			try {
				ZipEntry entry = new ZipEntry(fileName);
				zipfile.putNextEntry(entry);
				byte[] data = writer.toString().getBytes();
				zipfile.write(data, 0, data.length);
			} catch (ZipException e) {
				System.err.println(bibtexEntry.getField("title"));
			}

			scalarwriter.write(fileName);
			scalarwriter.write(";");
			scalarwriter.write(bibtexEntry.getField("year"));
			scalarwriter.write(";");
			if (bibtexEntry.getField("status") != null) {
				PublicationSelectionStatus status = new PublicationSelectionStatus(bibtexEntry.getField("status"));
				List<Argument> arguments = status.getArguments();
				Reason reason = arguments.get(arguments.size() - 1).getReason(); 
				switch (status.getDecision()) {
					case SELECTED:
						switch (reason) {
							case FULL_TEXT:
								scalarwriter.write("1.0");
								break;
							default:
								scalarwriter.write("0.5");
						}
						break;
					case EXCLUDED:
						scalarwriter.write("0.0");
						break;
					default:
						scalarwriter.write("0.2");
				}
			} else {
				scalarwriter.write("0");
			}
			scalarwriter.write(";");
			if (bibtexEntry.getField("status") != null) {
				PublicationSelectionStatus status = new PublicationSelectionStatus(bibtexEntry.getField("status"));
				List<Argument> arguments = status.getArguments();
				Reason reason = arguments.get(arguments.size() - 1).getReason(); 
				if (status.getDecision() == Status.SELECTED && reason == Reason.FULL_TEXT) {
					scalarwriter.write("1");
				} else {
					scalarwriter.write("0");
				}
			} else {
				scalarwriter.write("0");
			}
			scalarwriter.write(";");
			if (bibtexEntry.getField("status") != null) {
				PublicationSelectionStatus status = new PublicationSelectionStatus(bibtexEntry.getField("status"));
				List<Argument> arguments = status.getArguments();
				Reason reason = arguments.get(arguments.size() - 1).getReason(); 
				if (status.getDecision() == Status.EXCLUDED && reason == Reason.ABSTRACT) {
					scalarwriter.write("1");
				} else {
					scalarwriter.write("0");
				}
			} else {
				scalarwriter.write("0");
			}
			scalarwriter.newLine();
		}

		zipfile.flush();
		zipfile.close();
		
		scalarwriter.flush();
		scalarwriter.close();
	}

	
	/*
	public void removeExcludedArticles(String zipFile, String scalarsFile) {
		removeExcludedArticles(new File(zipFile), new File(scalarsFile));
	}

	public void removeExcludedArticles(File zipFile, File scalarsFile) {
		if (!zipFile.exists() || !zipFile.isFile()) {
			throw new IllegalArgumentException("ZIP file does not exist");
		}

		if (!scalarsFile.exists() || ! scalarsFile.isFile()) {
			throw new IllegalArgumentException("Scalar file does not exist");
		}

		FileInputStream zipFis = null;
		FileInputStream scalarsFis = null;
		try {
			zipFis = new FileInputStream(zipFile);
			scalarsFis = new FileInputStream(scalarsFile);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException(e);
		}
		InputStreamReader zipIsr = new InputStreamReader(zipFis, Charset.defaultCharset());
		InputStreamReader scalarsIsr = new InputStreamReader(scalarsFis, Charset.defaultCharset());
		removeExcludedArticles(zipIsr, scalarsIsr);
	}

	public void removeExcludedArticles(InputStreamReader zipIsr, InputStreamReader scalarsIsr) {
		BufferedReader in = null;

		try {
			in = new BufferedReader(scalarsIsr);
			Set<String> scalars = new LinkedHashSet<String>();

			// Get scalar's names
			int linenumber = 0;
			String line = null;
			while ((line = in.readLine()) != null) {
				linenumber++;

				// ignore comments
				if (line.startsWith(ScalarDAO.COMMENT)) {
					continue;
				}
				if (line.trim().length() == 0) {
					continue;
				}

				StringTokenizer t = new StringTokenizer(line, ScalarDAO.SEPARATOR);
				while (t.hasMoreTokens()) {
					String scalar = t.nextToken().trim();
					if (scalars.contains(scalar)) {
						throw new IllegalArgumentException("Line " + linenumber + ": Found duplicated scalar: " + scalar);
					}
					if (scalar.length() != 0) {
						scalars.add(scalar);
					}
				}
			}

			// Reading the scalars
			while ((line = in.readLine()) != null) {
				linenumber++;
				// ignore comments
				if (line.startsWith(ScalarDAO.COMMENT)) {
					continue;
				}
				if (line.trim().length() == 0) {
					continue;
				}

				List<Float> values = new ArrayList<Float>();
				StringTokenizer t = new StringTokenizer(line, ScalarDAO.SEPARATOR, false);
				if (t.countTokens() < scalars.size() + 1) {
					throw new IllegalArgumentException("Line " + linenumber + ": The number of values does not match with the number of declared scalars");
				}

				// Read the vertex id
				String vertexId = t.nextToken();

				// Read the scalar values
				while (t.hasMoreTokens()) {
					String token = t.nextToken();
					float value = 0;
					try {
						value = Float.parseFloat(token);
					} catch (NumberFormatException nfe) {
						throw new IllegalArgumentException("Line " + linenumber + ": Invalid number: " + token);
					}
					values.add(value);

					// Adding the scalar values to the vertex
					Vertex vertex = graph.get(vertexId);
					if (vertex != null) {
						Iterator<String> iScalar = scalars.iterator();
						for (int iValue = 0; iScalar.hasNext(); iValue++) {
							String scalarName = iScalar.next();
							Scalar scalar = graph.addScalar(scalarName);
							vertex.setScalar(scalar, values.get(iValue));
						}
					} else {
						System.err.println("Scalar assigned to an inexistent vertex: " + vertexId);
					}
				}
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
	}
	*/
}
