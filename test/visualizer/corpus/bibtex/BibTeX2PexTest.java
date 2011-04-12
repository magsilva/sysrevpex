package visualizer.corpus.bibtex;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class BibTeX2PexTest
{
	private BibTeX2Pex converter;
	
	@Before
	public void setUp()
	{
		converter = new BibTeX2Pex();
	}

	
	@Test
	public void testConvertACM() throws IOException
	{
		BibTeX2Pex converter = new BibTeX2Pex();
		converter.setBibtexFile(new File("/home/magsilva/Publications/TechnicalReports/Learning objects requirements/Selection/Interactive Learning Objects - ACM - Retrieved.bib"));
		converter.convert();
	}
		
		
	@Test
	public void testConvertIEEE() throws IOException
	{
		converter.setBibtexFile(new File("/home/magsilva/Publications/TechnicalReports/Learning objects requirements/Selection/Interactive Learning Objects - IEEE - Retrieved.bib"));
		converter.convert();
	}

	@Test
	public void testConvertScienceDirect() throws IOException
	{
		converter.setBibtexFile(new File("/home/magsilva/Publications/TechnicalReports/Learning objects requirements/Selection/Interactive Learning Objects - ScienceDirect - Retrieved.bib"));
		converter.convert();
	}
	
	@Test
	public void testConvertConsolidated() throws IOException
	{
		converter.setBibtexFile(new File("/home/magsilva/Publications/TechnicalReports/Learning objects requirements/Selection/Studies-Retrieved.bib"));
		converter.convert();
	}
}
