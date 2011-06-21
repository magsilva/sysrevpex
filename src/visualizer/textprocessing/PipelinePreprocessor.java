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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ironiacorp.miner.BufferComponent;
import com.ironiacorp.miner.ProducerAsListAdapter;
import com.ironiacorp.miner.preprocessing.text.AdverbFilter;
import com.ironiacorp.miner.preprocessing.text.HyphenizationUndoer;
import com.ironiacorp.miner.preprocessing.text.InvalidCharRemover;
import com.ironiacorp.miner.preprocessing.text.LUCuts;
import com.ironiacorp.miner.preprocessing.text.LowerCaseTransformer;
import com.ironiacorp.miner.preprocessing.text.NumberInWordRemover;
import com.ironiacorp.miner.preprocessing.text.PunctuationFilter;
import com.ironiacorp.miner.preprocessing.text.WhiteSpaceFilter;
import com.ironiacorp.miner.preprocessing.text.WordReplacer;
import com.ironiacorp.miner.preprocessing.text.WordSingularizer;
import com.ironiacorp.miner.preprocessing.text.stopword.StopNgramFilter;
import com.ironiacorp.miner.preprocessing.text.stopword.StopWord;
import com.ironiacorp.miner.preprocessing.text.stopword.StopWordFilter;
import com.ironiacorp.miner.preprocessing.text.stopword.exact.SetStopword;
import com.ironiacorp.resource.Resource;

import visualizer.textprocessing.stemmer.Stemmer;
import visualizer.textprocessing.stemmer.StemmerFactory;
import visualizer.util.SystemPropertiesManager;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class PipelinePreprocessor extends BasicPreProcessor
{
    @Override
    protected List<Ngram> getCorpusNgrams()
    {
        Map<String, Integer> corpusNgrams_aux = new HashMap<String, Integer>();
        List<Ngram> ngrams_aux = new ArrayList<Ngram>();
        Stemmer stemmer = StemmerFactory.getInstance(stemmerType);
        ProducerAsListAdapter inputComponent = new ProducerAsListAdapter();
        BufferComponent resultBuffer = setupPipeline(inputComponent);
        
        
        //For each ngram in the corpus
        for (Ngram n : corpus.getCorpusNgrams()) {
        	inputComponent.addResource(new PexNgramResource(n));
        }
        inputComponent.start();
        inputComponent.stop();
        for (Resource resource : resultBuffer.getResources()) {
        	PexNgramResource pexNgramResource = (PexNgramResource) resource;
       		Ngram ngram = pexNgramResource.getNgram();
        	String token = stemmer.stem(ngram.getNgram());
            if (corpusNgrams_aux.containsKey(token)) {
            	corpusNgrams_aux.put(token, corpusNgrams_aux.get(token) + ngram.getFrequency());
            } else {
            	corpusNgrams_aux.put(token, ngram.getFrequency());
            }
        }

        for (String key : corpusNgrams_aux.keySet()) {
            int freq = corpusNgrams_aux.get(key);
            if (freq >= lowerCut && freq <= upperCut) {
                ngrams_aux.add(new Ngram(key, numberGrams, freq));
            }
        }
        
        Collections.sort(ngrams_aux);

        return ngrams_aux;
    }
    
    @Override
    protected Map<String, Integer> getNgrams(String url)
    {
    	Map<String, Integer> ngrams_aux = new HashMap<String, Integer>();
        Stemmer stemmer = StemmerFactory.getInstance(stemmerType);
        ProducerAsListAdapter inputComponent = new ProducerAsListAdapter();
        BufferComponent resultBuffer = setupPipeline(inputComponent);
        //For each ngram in the corpus
        for (Ngram ngram : corpus.getNgrams(url)) {
        	inputComponent.addResource(new PexNgramResource(ngram));
         }
        inputComponent.start();
        inputComponent.stop();
        
        for (Resource resource : resultBuffer.getResources()) {
        	Ngram ngram;
       		PexNgramResource pexNgramResource = (PexNgramResource) resource;
       		ngram = pexNgramResource.getNgram();
        	String token = stemmer.stem(ngram.getNgram());
            if (ngrams_aux.containsKey(token)) {
            	ngrams_aux.put(token, ngrams_aux.get(token) + ngram.getFrequency());
            } else {
            	ngrams_aux.put(token, ngram.getFrequency());
            }

        }
        
        return ngrams_aux;      
    }
    
    private BufferComponent setupPipeline(ProducerAsListAdapter inputComponent)
    {
		// What is left is plain text
		LowerCaseTransformer lowercaseTransformer = new LowerCaseTransformer();
		NumberInWordRemover numberRemover = new NumberInWordRemover();
		AdverbFilter adverbFilter = new AdverbFilter();
		StopWordFilter stopwordFilter1;
		StopWordFilter stopwordFilter2;
		StopWordFilter stopwordFilter3;
		StopWord stopwords = new SetStopword();
		WhiteSpaceFilter whitespaceWordFilter1 = new WhiteSpaceFilter();
		WhiteSpaceFilter whitespaceWordFilter2 = new WhiteSpaceFilter();
		WhiteSpaceFilter whitespaceWordFilter3 = new WhiteSpaceFilter();
		InvalidCharRemover invalidCharRemover = new InvalidCharRemover();
		LUCuts luCutWordFilter = new LUCuts();
		PunctuationFilter punctuationFilter = new PunctuationFilter();
		WordSingularizer wordSingularizer = new WordSingularizer();
		NgramReducerFilter ngramReducerFilter = new NgramReducerFilter();
		WordReplacer wordReplacer = new WordReplacer();
		BufferComponent bufferComponent = new BufferComponent();

		if (numberGrams == 1) {
			stopwordFilter1 = new StopWordFilter();
			stopwordFilter2 = new StopWordFilter();
			stopwordFilter3 = new StopWordFilter();
		} else {
			stopwordFilter1 = new StopNgramFilter();
			stopwordFilter2 = new StopNgramFilter();
			stopwordFilter3 = new StopNgramFilter();
		}
		
		
		inputComponent.setConsumer(lowercaseTransformer);
		
		lowercaseTransformer.setConsumer(numberRemover);

		numberRemover.setConsumer(invalidCharRemover);

		invalidCharRemover.setConsumer(whitespaceWordFilter1);
		
		whitespaceWordFilter1.setConsumer(punctuationFilter);
		
		punctuationFilter.setConsumer(whitespaceWordFilter2);
		
		if (useStopword) {
			whitespaceWordFilter2.setConsumer(stopwordFilter1);
			stopwordFilter1.setStopWord(stopwords);
			SystemPropertiesManager props = SystemPropertiesManager.getInstance();
			String propsDir = props.getProperty("SPW.DIR");
			stopwordFilter1.loadLanguage(propsDir, "en");
			stopwordFilter1.setConsumer(wordSingularizer);
		} else {
			whitespaceWordFilter2.setConsumer(wordSingularizer);
		}
			
		wordSingularizer.setConsumer(whitespaceWordFilter3);
		
		whitespaceWordFilter3.setConsumer(luCutWordFilter);

		luCutWordFilter.setLowCut(2);
		luCutWordFilter.setUpCut(100);
		luCutWordFilter.setConsumer(adverbFilter);
		
		if (useStopword) {
			adverbFilter.setConsumer(stopwordFilter2);
			stopwordFilter2.setStopWord(stopwords);
			SystemPropertiesManager props = SystemPropertiesManager.getInstance();
			String propsDir = props.getProperty("SPW.DIR");
			stopwordFilter2.loadLanguage(propsDir, "en");
			stopwordFilter2.setConsumer(wordReplacer);
		} else {
			adverbFilter.setConsumer(wordReplacer);
		}
		
		// ngramReducerFilter.setConsumer(wordReplacer);
		
		wordReplacer.addSynonym("learning object", "LO");
		wordReplacer.addSynonym("learning object", "RLO");
		wordReplacer.addSynonym("learning object", "GLO");
		wordReplacer.addSynonym("learning object", "educational module");
		wordReplacer.addSynonym("learning object", "educational resource");
		wordReplacer.addSynonym("learning object", "unit of learning");
		wordReplacer.addSynonym("learning object", "learning resource");
		
		wordReplacer.addSynonym("instructional design", "learning design");
		
		wordReplacer.addSynonym("interaction", "interactive");
		wordReplacer.addSynonym("interaction", "interactivity");
		
		wordReplacer.addSynonym("collaboration", "colaborative");
		wordReplacer.addSynonym("collaboration", "colaborativity");
		
		wordReplacer.addSynonym("interaction design", "interaction modeling");
		wordReplacer.addSynonym("interaction design", "interaction modelling");

		wordReplacer.addSynonym("HCI", "user interface");
		wordReplacer.addSynonym("HCI", "human-computer interaction");
		wordReplacer.addSynonym("HCI", "human computer interaction");
		wordReplacer.addSynonym("HCI", "human-computer interface");
		wordReplacer.addSynonym("HCI", "human computer interface");
		wordReplacer.addSynonym("HCI", "UX");
		wordReplacer.addSynonym("HCI", "user experience");
		wordReplacer.addSynonym("HCI", "usability");
		wordReplacer.addSynonym("HCI", "UI");
		
		wordReplacer.addSynonym("learning system", "learning environment");
		wordReplacer.addSynonym("learning system", "LMS");
		wordReplacer.addSynonym("learning system", "LCMS");
		wordReplacer.addSynonym("learning system", "learning management system");
		wordReplacer.addSynonym("learning system", "Moodle");
		wordReplacer.addSynonym("learning system", "WebCT");
		wordReplacer.addSynonym("learning system", "BlackBoard");
		
		wordReplacer.addSynonym("software", "tool");
		wordReplacer.addSynonym("software", "application");

		wordReplacer.setConsumer(stopwordFilter3);
		
		stopwordFilter3.setStopWord(stopwords);
		SystemPropertiesManager props = SystemPropertiesManager.getInstance();
		String propsDir = props.getProperty("SPW.DIR");
		stopwordFilter3.loadLanguage(propsDir, "en");
		stopwordFilter3.setConsumer(bufferComponent);

		return bufferComponent;
    }
}
