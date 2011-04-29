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
	public void setText(String text)
	{
		super.setText(text);
		ngram.ngram = text;
	}

	@Override
	public Object clone() throws CloneNotSupportedException
	{
		PexNgramResource clone = new PexNgramResource();
		if (getText() != ngram.ngram) {
			ngram.ngram = getText();
		}
		clone.setNgram(ngram);
		
		return clone;
	}
}
