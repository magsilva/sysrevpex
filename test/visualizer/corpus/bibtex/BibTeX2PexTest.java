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
		BibTeX2Pex converter = new BibTeX2Pex(new File("/home/magsilva/teste.bib"));
		converter.convert();
	}

}
