package visualizer.textprocessing;

import com.ironiacorp.resource.mining.UnformattedTextResource;

public class PexNgramResource extends UnformattedTextResource implements Cloneable
{
	private Ngram ngram;

	public Ngram getNgram()
	{
		return ngram;
	}

	public void setNgram(Ngram ngram)
	{
		this.ngram = ngram;
		setText(ngram.ngram);
	}
	
	public PexNgramResource()
	{
	}
	
	public PexNgramResource(Ngram ngram)
	{
		setNgram(ngram);
	}

	@Override
	public Object clone() throws CloneNotSupportedException
	{
		PexNgramResource clone = new PexNgramResource();
		clone.setNgram(ngram);
		
		return clone;
	}
}
