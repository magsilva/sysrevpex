package visualizer.textprocessing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import visualizer.corpus.Corpus;
import visualizer.graph.Vertex;
import visualizer.matrix.Matrix;
import visualizer.matrix.SparseMatrix;
import visualizer.matrix.SparseVector;
import visualizer.textprocessing.stemmer.StemmerType;

public abstract class BasicPreProcessor implements PreProcessor
{
	protected Corpus corpus;
	
	protected List<Ngram> ngrams;
	
	protected StemmerType stemmerType;
	
	protected boolean useStopword;
	
	protected boolean useStartword;
	
	protected int numberGrams;
	
	protected int lowerCut = 0;
	
	protected int upperCut = Integer.MAX_VALUE;

	public BasicPreProcessor()
	{
		super();
	}

	public Corpus getCorpus()
	{
		return corpus;
	}

	public void setCorpus(Corpus corpus)
	{
		this.corpus = corpus;
	}

	public StemmerType getStemmer()
	{
		return stemmerType;
	}

	public void setStemmer(StemmerType stemmerType)
	{
		this.stemmerType = stemmerType;
	}

	public boolean getStopword()
	{
		return useStopword;
	}

	public void setStopword(boolean useStopword)
	{
		this.useStopword = useStopword;
	}

	public boolean getStartword()
	{
		return useStartword;
	}

	public void setStartword(boolean useStartword)
	{
		this.useStartword = useStartword;
	}

	public int getNumberGrams()
	{
		return numberGrams;
	}

	public void setNumberGrams(int numberGrams)
	{
		this.numberGrams = numberGrams;
	}

	public int getLowerCut()
	{
		return lowerCut;
	}

	public void setLowerCut(int lowerCut)
	{
		if (lowerCut < 0) {
			lowerCut = 0;
		}
		this.lowerCut = lowerCut;
	}

	public int getUpperCut()
	{
		return upperCut;
	}

	public void setUpperCut(int upperCut)
	{
		if (upperCut < 0) {
			upperCut = Integer.MAX_VALUE;
		}
		this.upperCut = upperCut;
	}

	public void run()
	{
		// Store the ngrams present on the corpus
        ngrams = getCorpusNgrams();
	}
	

	public Matrix getMatrix()
    {
    	if (ngrams == null) {
    		run();
    	}
        return getMatrix(corpus.getIds());
    }

	public Matrix getMatrixForSelection(Collection<Vertex> selected)
    {
    	List<String> urls = new ArrayList<String>();
        for (Vertex v : selected) {
            if (v.isValid()) {
                urls.add(v.getUrl());
            }
        }

        //store the ngrams present on the selected corpus
        ngrams = getCorpusNgrams(urls);

        return getMatrix(urls);
    }
    
	public List<Ngram> getNgrams() {
    	if (ngrams == null) {
    		run();
    	}
        return ngrams;
    }


    protected Matrix getMatrix(List<String> urls)  {
        long start = System.currentTimeMillis();
        Matrix matrix = new SparseMatrix();

        //For each file
        for (int i = 0; i < urls.size(); i++) {
            double[] vector = new double[this.ngrams.size()];

            //get the ngrams of the file
            Map<String, Integer> docNgrams = getNgrams(urls.get(i));

            //For each ngram in the corpus which occurs more than lowerCut
            int j = 0;
            for (Ngram n : this.ngrams) {
                if (docNgrams.containsKey(n.getNgram())) {
                    vector[j] = docNgrams.get(n.getNgram());
                } else {
                    vector[j] = 0.0f;
                }

                j++;
            }

            SparseVector spv = new SparseVector(vector, urls.get(i), corpus.getClassData()[i]);
            matrix.addRow(spv);
        }

        //setting the attibutes
        List<String> attr = new ArrayList<String>();
        for (Ngram n : this.ngrams) {
            attr.add(n.getNgram());
        }

        matrix.setAttributes(attr);

        long finish = System.currentTimeMillis();

        Logger.getLogger(this.getClass().getName()).log(Level.INFO,  "Corpus pre-processing time: " + (finish - start) / 1000.0f + "s");

        return matrix;
    }
	
    protected List<Ngram> getCorpusNgrams(List<String> urls)
    {
        Map<String, Integer> corpusNgrams_aux = new HashMap<String, Integer>();

        for (String url : urls) {
            Map<String, Integer> docNgrams = this.getNgrams(url);
            for (String key : docNgrams.keySet()) {
                if (corpusNgrams_aux.containsKey(key)) {
                    corpusNgrams_aux.put(key, corpusNgrams_aux.get(key) + docNgrams.get(key));
                } else {
                    corpusNgrams_aux.put(key, docNgrams.get(key));
                }
            }
        }

        List<Ngram> ngrams_aux = new ArrayList<Ngram>();
        for (String key : corpusNgrams_aux.keySet()) {
            int freq = corpusNgrams_aux.get(key);
            if (upperCut >= 0) {
                if (freq >= lowerCut && freq <= upperCut) {
                    ngrams_aux.add(new Ngram(key, freq));
                }
            } else {
                if (freq >= lowerCut) {
                    ngrams_aux.add(new Ngram(key, freq));
                }
            }
        }

        Collections.sort(ngrams_aux);

        return ngrams_aux;
    }
	
    protected abstract List<Ngram> getCorpusNgrams();
    
    protected abstract Map<String, Integer> getNgrams(String url);
}
