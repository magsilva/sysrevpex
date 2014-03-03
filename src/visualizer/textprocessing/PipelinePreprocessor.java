package br.edu.utfpr.cm.scienceevol;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lode.miner.BufferComponent;
import lode.miner.ProducerAsListAdapter;
import lode.miner.preprocessing.text.AdverbFilter;
import lode.miner.preprocessing.text.HyphenizationUndoer;
import lode.miner.preprocessing.text.InvalidCharRemover;
import lode.miner.preprocessing.text.LUCuts;
import lode.miner.preprocessing.text.LowerCaseTransformer;
import lode.miner.preprocessing.text.NumberInWordRemover;
import lode.miner.preprocessing.text.PunctuationFilter;
import lode.miner.preprocessing.text.WhiteSpaceFilter;
import lode.miner.preprocessing.text.WordReplacer;
import lode.miner.preprocessing.text.normalizer.LodeNormalizer;
import lode.miner.preprocessing.text.normalizer.Normalizer;
import lode.miner.preprocessing.text.normalizer.NormalizerComponent;
import lode.miner.preprocessing.text.stopword.StopNgramFilter;
import lode.miner.preprocessing.text.stopword.StopWord;
import lode.miner.preprocessing.text.stopword.StopWordFilter;
import lode.miner.preprocessing.text.stopword.StopWordLoader;
import lode.miner.preprocessing.text.stopword.exact.SetStopword;

import lode.model.Element;

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
        for (Element resource : resultBuffer.getResources()) {
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
                ngrams_aux.add(new Ngram(key, freq));
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
        
        for (Element resource : resultBuffer.getResources()) {
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
		StopWordLoader stopwordLoader = new StopWordLoader();
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
		NormalizerComponent wordSingularizer = new NormalizerComponent();
		Normalizer normalizer = new LodeNormalizer();
		NgramReducerFilter ngramReducerFilter = new NgramReducerFilter();
		WordReplacer wordReplacer = new WordReplacer();
		BufferComponent bufferComponent = new BufferComponent();
		SystemPropertiesManager props = SystemPropertiesManager.getInstance();
		String propsDir = props.getProperty("SPW.DIR");
		System.setProperty("user.dir", propsDir);
		
		if (numberGrams == 1) {
			stopwordFilter1 = new StopWordFilter();
			stopwordFilter2 = new StopWordFilter();
			stopwordFilter3 = new StopWordFilter();
		} else {
			stopwordFilter1 = new StopNgramFilter();
			stopwordFilter2 = new StopNgramFilter();
			stopwordFilter3 = new StopNgramFilter();
		}
		stopwordLoader.loadLanguage(stopwords, "en");

		
		inputComponent.setConsumer(lowercaseTransformer);
		
		lowercaseTransformer.setConsumer(numberRemover);

		numberRemover.setConsumer(invalidCharRemover);

		invalidCharRemover.setConsumer(whitespaceWordFilter1);
		
		whitespaceWordFilter1.setConsumer(punctuationFilter);
		
		punctuationFilter.setConsumer(whitespaceWordFilter2);
		
		if (useStopword) {
			whitespaceWordFilter2.setConsumer(stopwordFilter1);
			stopwordFilter1.setStopWord(stopwords);
			stopwordFilter1.setConsumer(wordSingularizer);
		} else {
			whitespaceWordFilter2.setConsumer(wordSingularizer);
		}
			
		wordSingularizer.setNormalizer(normalizer);
		wordSingularizer.setConsumer(whitespaceWordFilter3);
		
		whitespaceWordFilter3.setConsumer(luCutWordFilter);

		luCutWordFilter.setLowCut(2);
		luCutWordFilter.setUpCut(100);
		luCutWordFilter.setConsumer(adverbFilter);
		
		if (useStopword) {
			adverbFilter.setConsumer(stopwordFilter2);
			stopwordFilter2.setStopWord(stopwords);
			stopwordFilter2.setConsumer(wordReplacer);
		} else {
			adverbFilter.setConsumer(wordReplacer);
		}
		
		// ngramReducerFilter.setConsumer(wordReplacer);
		
		/*
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
		*/

		wordReplacer.setConsumer(stopwordFilter3);
		
		stopwordFilter3.setStopWord(stopwords);
		stopwordFilter3.setConsumer(bufferComponent);

		return bufferComponent;
    }
}
