package visualizer.corpus.bibtex;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
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
import net.sf.jabref.Globals;
import net.sf.jabref.JabRefPreferences;
import net.sf.jabref.export.LatexFieldFormatter;
import net.sf.jabref.imports.BibtexParser;
import net.sf.jabref.imports.ParserResult;


public class BibTeXFieldReplacer
{
	private HashMap<String, BibtexEntry> cache;

	private File inputFileWithWrongField;

	private File inputFileWithCorrectField;

	private File outputFile;
	
	private Set<String> fields;
	
	public BibTeXFieldReplacer()
	{
		cache = new HashMap<String, BibtexEntry>();
		fields = new HashSet<String>();
	}

	public File getInputFileWithWrongField()
	{
		return inputFileWithWrongField;
	}

	public void setInputFileWithWrongField(File inputFileWithWrongField)
	{
		this.inputFileWithWrongField = inputFileWithWrongField;
	}

	public File getInputFileWithCorrectField()
	{
		return inputFileWithCorrectField;
	}

	public void setInputFileWithCorrectField(File inputFileWithCorrectField)
	{
		this.inputFileWithCorrectField = inputFileWithCorrectField;
	}

	public File getOutputFile()
	{
		return outputFile;
	}

	public void setOutputFile(File outputFile)
	{
		this.outputFile = outputFile;
	}

	private String getKey(BibtexEntry bibtexEntry)
	{
		String title = bibtexEntry.getField("title").toLowerCase();
		String doi = bibtexEntry.getField("doi");
		String key = title;
		if (doi != null) {
			key += doi.toLowerCase();
		}
		
		return key;
	}
	
	private void addToCache(BibtexEntry bibtexEntry)
	{
		cache.put(getKey(bibtexEntry), bibtexEntry);
	}
	
	private void merge(BibtexEntry bibtexEntry)
	{
		String key = getKey(bibtexEntry);
		if (cache.containsKey(key)) {
			BibtexEntry cachedEntry = cache.get(key);
			Iterator<String> i = fields.iterator();
			while (i.hasNext()) {
				String fieldName = i.next();
				String fieldValue = bibtexEntry.getField(fieldName);
				if (fieldValue != null) {
					cachedEntry.setField(fieldName, fieldValue);
				}
			}
		}
	}
	
	public void clean() throws IOException
	{
		ParserResult resultWrong = BibtexParser.parse(new FileReader(inputFileWithWrongField));
		ParserResult resultCorrect = BibtexParser.parse(new FileReader(inputFileWithCorrectField));
		Collection<BibtexEntry> wrongEntries = resultWrong.getDatabase().getEntries();
		Collection<BibtexEntry> correctEntries = resultCorrect.getDatabase().getEntries();
		Iterator<BibtexEntry> iterator;
		
		iterator = wrongEntries.iterator();
		while (iterator.hasNext()) {
			BibtexEntry bibtexEntry = iterator.next();
			addToCache(bibtexEntry);
		}

		iterator = correctEntries.iterator();
		while (iterator.hasNext()) {
			BibtexEntry bibtexEntry = iterator.next();
			merge(bibtexEntry);
		}

		
		iterator = cache.values().iterator();
		FileWriter writer = new FileWriter(outputFile);
		BufferedWriter buffer = new BufferedWriter(writer);
		Globals.prefs = JabRefPreferences.getInstance();
		LatexFieldFormatter formatter = new LatexFieldFormatter();
		int i = 0;
		while (iterator.hasNext()) {
			BibtexEntry bibtexEntry = iterator.next();
			bibtexEntry.write(buffer, formatter, true);
			buffer.write(",\n");
			if (i == 100) {
				System.out.print(".");
				i = 0;
			} else {
				i++;
			}
			
		}
		
		buffer.flush();
		writer.flush();
		buffer.close();
		writer.close();
	}
	
	public void selectField(String field)
	{
		fields.add(field);
	}

	public static void main(String[] args) throws IOException
	{
		BibTeXFieldReplacer lint = new BibTeXFieldReplacer();
		lint.setInputFileWithCorrectField(new File("/home/magsilva/Publications/TechnicalReports/Learning objects requirements/Selection/Interactive Learning Objects - IEEE - Retrieved.bib"));
		lint.setInputFileWithWrongField(new File("/home/magsilva/Publications/TechnicalReports/Learning objects requirements/Selection/Interactive Learning Objects - IEEE - Retrieved.raw.bib"));
		lint.selectField("booktitle");
		lint.setOutputFile(new File("/home/magsilva/Publications/TechnicalReports/Learning objects requirements/Selection/Interactive Learning Objects - IEEE - Retrieved.raw2.bib"));
		lint.clean();
	}
}
