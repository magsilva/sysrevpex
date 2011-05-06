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
	public void testConvertConsolidated() throws IOException
	{
		converter.setBibtexFile(new File("/home/magsilva/Publications/TechnicalReports/Learning objects requirements/Selection/Studies-Screened.bib"));
		converter.convert();
	}
	
	@Test
	public void testConvertConsolidated_ACM() throws IOException
	{
		converter.setBibtexFile(new File("/home/magsilva/Publications/TechnicalReports/Learning objects requirements/Selection/Interactive Learning Objects - ACM - Retrieved.bib"));
		converter.convert();
		converter.setBibtexFile(new File("/home/magsilva/Publications/TechnicalReports/Learning objects requirements/Selection/Interactive Learning Objects - ACM - Screened.bib"));
		converter.convert();
		converter.setBibtexFile(new File("/home/magsilva/Publications/TechnicalReports/Learning objects requirements/Selection/Interactive Learning Objects - ACM - Selected.bib"));
		converter.convert();
	}
	
	@Test
	public void testConvertConsolidated_IEEE() throws IOException
	{
		converter.setBibtexFile(new File("/home/magsilva/Publications/TechnicalReports/Learning objects requirements/Selection/Interactive Learning Objects - IEEE - Retrieved.bib"));
		converter.convert();
		converter.setBibtexFile(new File("/home/magsilva/Publications/TechnicalReports/Learning objects requirements/Selection/Interactive Learning Objects - IEEE - Screened.bib"));
		converter.convert();
		converter.setBibtexFile(new File("/home/magsilva/Publications/TechnicalReports/Learning objects requirements/Selection/Interactive Learning Objects - IEEE - Selected.bib"));
		converter.convert();
	}

	@Test
	public void testConvertConsolidated_ScienceDirect() throws IOException
	{
		converter.setBibtexFile(new File("/home/magsilva/Publications/TechnicalReports/Learning objects requirements/Selection/Interactive Learning Objects - ScienceDirect - Retrieved.bib"));
		converter.convert();
	}
}
