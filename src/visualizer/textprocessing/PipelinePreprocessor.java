/* ***** BEGIN LICENSE BLOCK *****
 *
 * Copyright (c) 2005-2007 Universidade de Sao Paulo, Sao Carlos/SP, Brazil.
 * All Rights Reserved.
 *
 * This file is part of Projection Explorer (PEx).
 *
 * How to cite this work:
 *  
@inproceedings{paulovich2007pex,
author = {Fernando V. Paulovich and Maria Cristina F. Oliveira and Rosane 
Minghim},
title = {The Projection Explorer: A Flexible Tool for Projection-based 
Multidimensional Visualization},
booktitle = {SIBGRAPI '07: Proceedings of the XX Brazilian Symposium on 
Computer Graphics and Image Processing (SIBGRAPI 2007)},
year = {2007},
isbn = {0-7695-2996-8},
pages = {27--34},
doi = {http://dx.doi.org/10.1109/SIBGRAPI.2007.39},
publisher = {IEEE Computer Society},
address = {Washington, DC, USA},
}
 *  
 * PEx is free software: you can redistribute it and/or modify it under 
 * the terms of the GNU General Public License as published by the Free 
 * Software Foundation, either version 3 of the License, or (at your option) 
 * any later version.
 *
 * PEx is distributed in the hope that it will be useful, but WITHOUT 
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License 
 * for more details.
 *
 * This code was developed by members of Computer Graphics and Image
 * Processing Group (http://www.lcad.icmc.usp.br) at Instituto de Ciencias
 * Matematicas e de Computacao - ICMC - (http://www.icmc.usp.br) of 
 * Universidade de Sao Paulo, Sao Carlos/SP, Brazil. The initial developer 
 * of the original code is Fernando Vieira Paulovich <fpaulovich@gmail.com>, 
 * Roberto Pinho <robertopinho@yahoo.com.br>.
 *
 * Contributor(s): Rosane Minghim <rminghim@icmc.usp.br>
 *
 * You should have received a copy of the GNU General Public License along 
 * with PEx. If not, see <http://www.gnu.org/licenses/>.
 *
 * ***** END LICENSE BLOCK ***** */

package visualizer.textprocessing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ironiacorp.miner.BufferComponent;
import com.ironiacorp.miner.ProducerAsListAdapter;
import com.ironiacorp.miner.preprocessing.text.AdverbFilter;
import com.ironiacorp.miner.preprocessing.text.DuplicatedPunctuationFilter;
import com.ironiacorp.miner.preprocessing.text.DuplicatedWordFilter;
import com.ironiacorp.miner.preprocessing.text.HyphenizationUndoer;
import com.ironiacorp.miner.preprocessing.text.InvalidCharRemover;
import com.ironiacorp.miner.preprocessing.text.LUCuts;
import com.ironiacorp.miner.preprocessing.text.LowerCaseTransformer;
import com.ironiacorp.miner.preprocessing.text.NumberInWordRemover;
import com.ironiacorp.miner.preprocessing.text.PunctuationFilter;
import com.ironiacorp.miner.preprocessing.text.WhiteSpaceFilter;
import com.ironiacorp.miner.preprocessing.text.WordSingularizer;
import com.ironiacorp.miner.preprocessing.text.stopword.StopWord;
import com.ironiacorp.miner.preprocessing.text.stopword.StopWordFilter;
import com.ironiacorp.miner.preprocessing.text.stopword.exact.SetStopword;
import com.ironiacorp.resource.Resource;
import com.ironiacorp.resource.mining.UnformattedTextResource;

import visualizer.corpus.Corpus;
import visualizer.graph.Vertex;
import visualizer.matrix.Matrix;
import visualizer.matrix.SparseMatrix;
import visualizer.matrix.SparseVector;
import visualizer.textprocessing.stemmer.StemmerType;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class PipelinePreprocessor implements PreProcessor
{
    private Corpus corpus;

    private List<Ngram> ngrams;
    
    private StemmerType stemmer;
    
    private boolean useStopword;
    
    private boolean useStartword;
    
    private int numberGrams;
    
    private int lowerCut;
    
    private int upperCut;
    
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
		return stemmer;
	}

	public void setStemmer(StemmerType stemmer)
	{
		this.stemmer = stemmer;
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
		this.lowerCut = lowerCut;
	}

	public int getUpperCut()
	{
		return upperCut;
	}

	public void setUpperCut(int upperCut)
	{
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
    	ArrayList<String> urls = new ArrayList<String>();
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

    //If upperCut == -1 ths Luhn's upper cut-off will be ignored
    private Matrix getMatrix(ArrayList<String> urls)  {
        long start = System.currentTimeMillis();

        Matrix matrix = new SparseMatrix();

        //For each file
        for (int i = 0; i < urls.size(); i++) {
            float[] vector = new float[this.ngrams.size()];

            //get the ngrams of the file
            Map<String, Integer> docNgrams = getNgrams(urls.get(i));

            //For each ngram in the corpus which occurs more than lowerCut
            int j = 0;
            for (Ngram n : this.ngrams) {
                if (docNgrams.containsKey(n.ngram)) {
                    vector[j] = docNgrams.get(n.ngram);
                } else {
                    vector[j] = 0.0f;
                }

                j++;
            }

            SparseVector spv = new SparseVector(vector, urls.get(i), corpus.getClassData()[i]);
            matrix.addRow(spv);
        }

        //setting the attibutes
        ArrayList<String> attr = new ArrayList<String>();
        for (Ngram n : this.ngrams) {
            attr.add(n.ngram);
        }

        matrix.setAttributes(attr);

        long finish = System.currentTimeMillis();

        Logger.getLogger(this.getClass().getName()).log(Level.INFO,
                "Document collection processing time: " + (finish - start) / 1000.0f + "s");

        return matrix;
    }

    private List<Ngram> getCorpusNgrams(List<String> urls)
    {
        HashMap<String, Integer> corpusNgrams_aux = new HashMap<String, Integer>();

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

        ArrayList<Ngram> ngrams_aux = new ArrayList<Ngram>();

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

    private List<Ngram> getCorpusNgrams()
    {
        Map<String, Integer> corpusNgrams_aux = new HashMap<String, Integer>();
        List<Ngram> ngrams_aux = new ArrayList<Ngram>();
    
        ProducerAsListAdapter inputComponent = new ProducerAsListAdapter();
        BufferComponent resultBuffer = setupPipeline(inputComponent);
        //For each ngram in the corpus
        for (Ngram n : corpus.getCorpusNgrams()) {
        	inputComponent.addResource(new UnformattedTextResource(n.ngram));
        }
        inputComponent.start();
        inputComponent.stop();
        
        for (Resource resource : resultBuffer.getResources()) {
        	UnformattedTextResource textResource = (UnformattedTextResource) resource;
        	String text = textResource.getText();
            if (corpusNgrams_aux.containsKey(text)) {
            	int value = corpusNgrams_aux.get(text);
            	corpusNgrams_aux.put(text, value + 1);
            } else {
            	corpusNgrams_aux.put(text, 1);
            }
        }

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

    private Map<String, Integer> getNgrams(String url)
    {
    	Map<String, Integer> ngrams_aux = new HashMap<String, Integer>();
        
        ProducerAsListAdapter inputComponent = new ProducerAsListAdapter();
        BufferComponent resultBuffer = setupPipeline(inputComponent);
        //For each ngram in the corpus
        for (Ngram n : corpus.getNgrams(url)) {
        	inputComponent.addResource(new UnformattedTextResource(n.ngram));
        }
        inputComponent.start();
        inputComponent.stop();
        
        for (Resource resource : resultBuffer.getResources()) {
        	UnformattedTextResource textResource = (UnformattedTextResource) resource;
        	String text = textResource.getText();
            if (ngrams_aux.containsKey(text)) {
            	int value = ngrams_aux.get(text);
            	ngrams_aux.put(text, value++);
            } else {
            	ngrams_aux.put(text, 1);
            }
        }
        
        return ngrams_aux;      
    }
    
    private BufferComponent setupPipeline(ProducerAsListAdapter inputComponent)
    {
		// What is left is plain text
		HyphenizationUndoer hyphenizationUndoer = new HyphenizationUndoer();
		LowerCaseTransformer lowercaseTransformer = new LowerCaseTransformer();
		NumberInWordRemover numberRemover = new NumberInWordRemover();
		AdverbFilter adverbFilter = new AdverbFilter();
		StopWordFilter stopwordFilter = new StopWordFilter();
		StopWord stopwords = new SetStopword();
		WhiteSpaceFilter whitespaceWordFilter0 = new WhiteSpaceFilter();
		WhiteSpaceFilter whitespaceWordFilter1 = new WhiteSpaceFilter();
		WhiteSpaceFilter whitespaceWordFilter2 = new WhiteSpaceFilter();
		InvalidCharRemover invalidCharRemover = new InvalidCharRemover();
		LUCuts luCutWordFilter = new LUCuts();
		DuplicatedWordFilter duplicatedWordFilter = new DuplicatedWordFilter();
		DuplicatedPunctuationFilter duplicatedPunctuationFilter = new DuplicatedPunctuationFilter();
		PunctuationFilter punctuationFilter = new PunctuationFilter();
		WordSingularizer wordSingularizer = new WordSingularizer();
		BufferComponent bufferComponent = new BufferComponent();

		inputComponent.setConsumer(hyphenizationUndoer);
		
		hyphenizationUndoer.setConsumer(lowercaseTransformer);
		
		lowercaseTransformer.setConsumer(numberRemover);

		numberRemover.setConsumer(adverbFilter);

		adverbFilter.setConsumer(whitespaceWordFilter0);
		
		if (useStopword) {
			whitespaceWordFilter0.setConsumer(stopwordFilter);
			stopwordFilter.setStopWord(stopwords);
			stopwordFilter.loadLanguage("/home/magsilva/Projects/LabES/Lode/resources", "en");
			stopwordFilter.setConsumer(invalidCharRemover);
		} else {
			whitespaceWordFilter0.setConsumer(invalidCharRemover);
		}
		
		invalidCharRemover.setConsumer(whitespaceWordFilter1);
		
		whitespaceWordFilter1.setConsumer(luCutWordFilter);

		luCutWordFilter.setLowCut(2);
		luCutWordFilter.setUpCut(100);
		luCutWordFilter.setIgnorePunctuation(true);
		luCutWordFilter.setConsumer(duplicatedWordFilter);

		duplicatedWordFilter.setConsumer(duplicatedPunctuationFilter);
		
		duplicatedPunctuationFilter.setConsumer(punctuationFilter);
		
		punctuationFilter.setConsumer(whitespaceWordFilter2);
		
		whitespaceWordFilter2.setConsumer(wordSingularizer);
		
		wordSingularizer.setConsumer(bufferComponent);
		
		return bufferComponent;
    }
}
