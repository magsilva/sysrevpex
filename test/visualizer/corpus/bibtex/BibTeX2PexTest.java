package visualizer.corpus.bibtex;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

public class BibTeX2PexTest
{
	@Test
	public void testConvert() throws IOException
	{
		BibTeX2Pex converter = new BibTeX2Pex();
		converter.setBibtexFile(new File("/home/magsilva/Publications/TechnicalReports/Learning objects requirements/Selection/Interactive Learning Objects - IEEE - Retrieved.bib"));
		converter.convert();
		converter.setBibtexFile(new File("/home/magsilva/Publications/TechnicalReports/Learning objects requirements/Selection/Interactive Learning Objects - ACM - Retrieved.bib"));
		converter.convert();
		converter.setBibtexFile(new File("/home/magsilva/Publications/TechnicalReports/Learning objects requirements/Selection/Interactive Learning Objects - ScienceDirect - Retrieved.bib"));
		converter.convert();
		converter.setBibtexFile(new File("/home/magsilva/Publications/TechnicalReports/Learning objects requirements/Selection/Interactive Learning Objects - IEEE - Selected.bib"));
		converter.convert();
		converter.setBibtexFile(new File("/home/magsilva/Publications/TechnicalReports/Learning objects requirements/Selection/Interactive Learning Objects - ACM - Selected.bib"));
		converter.convert();
		converter.setBibtexFile(new File("/home/magsilva/Publications/TechnicalReports/Learning objects requirements/Selection/Interactive Learning Objects - ScienceDirect - Retrieved plus Selected.bib"));
		converter.convert();
		converter.setBibtexFile(new File("/home/magsilva/Publications/TechnicalReports/Learning objects requirements/Selection/Interactive Learning Objects - ScienceDirect - Retrieved plus Extracted.bib"));
		converter.convert();
		converter.setBibtexFile(new File("/home/magsilva/Publications/TechnicalReports/Learning objects requirements/Selection/Studies-Selected.bib"));
		converter.convert();
		converter.setBibtexFile(new File("/home/magsilva/Publications/TechnicalReports/Learning objects requirements/Selection/Studies-Extracted.bib"));
		converter.convert();
	}
}
