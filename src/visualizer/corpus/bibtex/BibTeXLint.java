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
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipOutputStream;

import com.ironiacorp.sysrev.selection.Argument;
import com.ironiacorp.sysrev.selection.PublicationSelectionStatus;
import com.ironiacorp.sysrev.selection.Reason;
import com.ironiacorp.sysrev.selection.Status;

import net.sf.jabref.BibtexEntry;
import net.sf.jabref.Globals;
import net.sf.jabref.JabRefPreferences;
import net.sf.jabref.export.LatexFieldFormatter;
import net.sf.jabref.imports.BibtexParser;
import net.sf.jabref.imports.ParserResult;


public class BibTeXLint
{
	private HashMap<String, BibtexEntry> cache;

	private File inputFile;
	
	private File outputFile;
	
	public BibTeXLint()
	{
		cache = new HashMap<String, BibtexEntry>();
	}
	
	public File getInputFile()
	{
		return inputFile;
	}

	public void setInputFile(File inputFile)
	{
		this.inputFile = inputFile;
	}

	public File getOutputFile()
	{
		return outputFile;
	}

	public void setOutputFile(File outputFile)
	{
		this.outputFile = outputFile;
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
			String status = bibtexEntry.getField("status");
			if (status == null || status.trim().isEmpty()) {
				cache.put(key, bibtexEntry);	
			}
		} else {
			cache.put(key, bibtexEntry);
		}
	
		return bibtexEntry;
	}
	
	public void clean() throws IOException
	{
		ParserResult result = BibtexParser.parse(new FileReader(inputFile));
		Collection<BibtexEntry> entries = result.getDatabase().getEntries();
		Iterator<BibtexEntry> iterator = entries.iterator();
		while (iterator.hasNext()) {
			BibtexEntry bibtexEntry = iterator.next();
			BibtexEntry cachedEntry = findInCache(bibtexEntry);
			if (bibtexEntry != cachedEntry) {
				iterator.remove();
			}
		}
		
		iterator = cache.values().iterator();
		FileWriter writer = new FileWriter(outputFile);
		BufferedWriter buffer = new BufferedWriter(writer);
		Globals.prefs = JabRefPreferences.getInstance();
		LatexFieldFormatter formatter = new LatexFieldFormatter();
		while (iterator.hasNext()) {
			BibtexEntry bibtexEntry = iterator.next();
			if (bibtexEntry.getField("status") != null) {
				bibtexEntry.setField("status", null);
			}
			if (bibtexEntry.getField("keywords") != null) {
				bibtexEntry.setField("keywords", null);
			}
			bibtexEntry.write(buffer, formatter, true);
			buffer.write(",\n");
		}
		
		buffer.flush();
		writer.flush();
		buffer.close();
		writer.close();
	}

	public static void main(String[] args) throws IOException
	{
		BibTeXLint lint = new BibTeXLint();
		lint.setInputFile(new File("/home/magsilva/Publications/TechnicalReports/Learning objects requirements/Selection/Interactive Learning Objects - IEEE - Retrieved.raw.bib"));
		lint.setOutputFile(new File("/home/magsilva/Publications/TechnicalReports/Learning objects requirements/Selection/Interactive Learning Objects - IEEE - Retrieved.raw2.bib"));
		lint.clean();
	}
}
