package visualizer.corpus.bibtex;

import java.io.File;
import java.io.IOException;

import visualizer.corpus.zip.ZipCorpus;

public class BibTeXCorpus extends ZipCorpus
{
	public BibTeXCorpus(String url, int nrGrams)
	{
		super(url, nrGrams);
	}
	
	@Override
	protected void initializeCorpus()
	{
	 	BibTeX2Pex converter = new BibTeX2Pex();
	 	String filename = url.substring(0, url.lastIndexOf(".")) + BibTeX2Pex.BIBTEX_EXTENSION;
		converter.setBibtexFile(new File(filename));
		try {
			converter.convert();
		} catch (IOException e) {
			throw new UnsupportedOperationException("Cannot create the corpus from the BibTeX file");
		}
		url = url.substring(0, url.lastIndexOf(".")) + BibTeX2Pex.PEX_DOCUMENT_EXTENSION;
		super.initializeCorpus();
	}
}
