package visualizer.corpus.bibtex;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Collection;
import java.util.Iterator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import net.sf.jabref.BibtexEntry;
import net.sf.jabref.imports.BibtexParser;
import net.sf.jabref.imports.ParserResult;

public class BibTeX2Pex
{
	private static final String BIBTEX_EXTENSION = ".bib";

	private static final String PEX_DOCUMENT_EXTENSION = ".zip";

	private static final String PEX_SCALAR_EXTENSION = ".scalar";

	private String baseDir;

	private String name;

	private File bibtexFile;

	private File pexFile;

	private File scalarFile;

	private String[] fieldsToImport = { "title", "abstract"};

	public BibTeX2Pex(File file)
	{
		if (file == null || !file.exists()) {
			throw new IllegalArgumentException("Invalid BibTeX file");
		}

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

	public void convert() throws IOException
	{
		BufferedWriter scalarwriter = new BufferedWriter(new FileWriter(scalarFile));
		scalarwriter.write("year");
		scalarwriter.newLine();

		ZipOutputStream zipfile = new ZipOutputStream(new FileOutputStream(pexFile));
		ParserResult result = BibtexParser.parse(new FileReader(bibtexFile));
		Collection<BibtexEntry> entries = result.getDatabase().getEntries();
		Iterator<BibtexEntry> iterator = entries.iterator();
		int i = 0;
		while (iterator.hasNext()) {
			BibtexEntry bibtexEntry = iterator.next();
			String fileName = Integer.toString(i++).concat(".txt");
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
			ZipEntry entry = new ZipEntry(fileName);
			zipfile.putNextEntry(entry);
			byte[] data = writer.toString().getBytes();
			zipfile.write(data, 0, data.length);

			String year = bibtexEntry.getField("year");
			scalarwriter.write(fileName + ";" + year);
			scalarwriter.newLine();
			scalarwriter.flush();
		}
		
		zipfile.close();
		scalarwriter.close();
	}
}
