package visualizer.corpus;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;

import au.com.bytecode.opencsv.CSVReader;

public class CSV2PexZip
{
	private File input;

	private File output;

	private String prefix = "file-";

	private String wordSeparator = " ";

	/**
	 * - US-ASCII Seven-bit ASCII, a.k.a. ISO646-US, a.k.a. the Basic Latin block of the Unicode character set
	 * - ISO-8859-1 ISO Latin Alphabet No. 1, a.k.a. ISO-LATIN-1
	 * - UTF-8 Eight-bit UCS Transformation Format
	 * - UTF-16BE Sixteen-bit UCS Transformation Format, big-endian byte order
	 * - UTF-16LE Sixteen-bit UCS Transformation Format, little-endian byte order
	 * - UTF-16 Sixteen-bit UCS Transformation Format, byte order identified by an optional byte-order mark
	 */
	private Charset charset =  Charset.defaultCharset();

	public void setCharset(String charsetName)
	{
		charset = Charset.forName(charsetName);
	}
	
	public String getCharset()
	{
		return charset.displayName();
	}
	
	public String getWordSeparator()
	{
		return wordSeparator;
	}

	public void setWordSeparator(String wordSeparator)
	{
		this.wordSeparator = wordSeparator;
	}

	public String getPrefix()
	{
		return prefix;
	}

	public void setPrefix(String prefix)
	{
		this.prefix = prefix;
	}

	public File getInput()
	{
		return input;
	}

	public void setInput(File input)
	{
		this.input = input;
	}

	public File getOutput()
	{
		return output;
	}

	public void setOutput(File output)
	{
		this.output = output;
	}

	public void convert()
	{
		try {
			ZipArchiveOutputStream zos = new ZipArchiveOutputStream(output);
			CSVReader reader = new CSVReader(new FileReader(input));
			List<String[]> lines = reader.readAll();
			int i = 0;

			for (String[] line : lines) {
				ZipArchiveEntry entry = new ZipArchiveEntry(prefix + i);
				zos.putArchiveEntry(entry);
				for (String word : line) {
					zos.write(charset.encode(word).array());
					zos.write(charset.encode(wordSeparator).array());
				}
				zos.closeArchiveEntry();
				i++;
			}
			zos.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
