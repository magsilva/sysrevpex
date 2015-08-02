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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import visualizer.textprocessing.stemmer.Stemmer;
import visualizer.textprocessing.stemmer.StemmerFactory;
import visualizer.textprocessing.stopword.SetStopword;
import visualizer.textprocessing.stopword.StopWord;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class MonoliticPreprocessor extends BasicPreProcessor
{ 
	@Override
    protected List<Ngram> getCorpusNgrams()
    {
        Map<String, Integer> corpusNgrams_aux = new HashMap<String, Integer>();
        List<Ngram> ngrams_aux = new ArrayList<Ngram>();
        Stemmer stemmer = StemmerFactory.getInstance(stemmerType);
        StopWord stp = null;
        Startword sta = null;

        try {
        	if (useStopword) {
        		stp = SetStopword.getInstance();
        	}
        	if (useStartword) {
        		sta = Startword.getInstance();
        	}
        } catch (IOException e) {
        	throw new UnsupportedOperationException(e);
        }

        // For each ngram in the corpus
        for (Ngram n : corpus.getCorpusNgrams()) {
            String token = n.getNgram();
            if ((useStopword && ! useStartword && ! stp.isStopWord(token))) {
                token = stemmer.stem(token);
                if (token.trim().length() > 0) {
                    if (corpusNgrams_aux.containsKey(token)) {
                        corpusNgrams_aux.put(token, corpusNgrams_aux.get(token) + n.getFrequency());
                    } else {
                        corpusNgrams_aux.put(token, n.getFrequency());
                    }
                }
            }
            if ((! useStopword && useStartword && sta.isStartWord(token))) {
                token = stemmer.stem(token);
                if (token.trim().length() > 0) {
                    if (corpusNgrams_aux.containsKey(token)) {
                        corpusNgrams_aux.put(token, corpusNgrams_aux.get(token) + n.getFrequency());
                    } else {
                        corpusNgrams_aux.put(token, n.getFrequency());
                    }
                }
            }
            if ((useStopword && useStartword && ! stp.isStopWord(token) && sta.isStartWord(token))) {
                token = stemmer.stem(token);
                if (token.trim().length() > 0) {
                    if (corpusNgrams_aux.containsKey(token)) {
                        corpusNgrams_aux.put(token, corpusNgrams_aux.get(token) + n.getFrequency());
                    } else {
                        corpusNgrams_aux.put(token, n.getFrequency());
                    }
                }
            }
            if (! useStopword && ! useStartword) {
                token = StemmerFactory.getInstance(stemmerType).stem(token);
                if (token.trim().length() > 0) {
                    if (corpusNgrams_aux.containsKey(token)) {
                        corpusNgrams_aux.put(token, corpusNgrams_aux.get(token) + n.getFrequency());
                    } else {
                        corpusNgrams_aux.put(token, n.getFrequency());
                    }
                }
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

	@Override
    protected Map<String, Integer> getNgrams(String url)
    {
        HashMap<String, Integer> corpusNgrams_aux = new HashMap<String, Integer>();
        StopWord stp = null;
        Startword sta = null;
        Stemmer stemmer = StemmerFactory.getInstance(stemmerType);

        try {
        	if (useStopword) {
        		stp = SetStopword.getInstance();
        	}
        	if (useStartword) {
        		sta = Startword.getInstance();
        	}
        } catch (IOException e) {
        	throw new UnsupportedOperationException(e);
        }

        List<Ngram> fngrams = corpus.getNgrams(url);
        if (fngrams != null) {
            for (Ngram n : fngrams) {
                String token = n.getNgram();

                if ((useStopword && ! useStartword && ! stp.isStopWord(token))) {
                    token = stemmer.stem(token);
                    if (token.trim().length() > 0) {
                        if (corpusNgrams_aux.containsKey(token)) {
                            corpusNgrams_aux.put(token, corpusNgrams_aux.get(token) + n.getFrequency());
                        } else {
                            corpusNgrams_aux.put(token, n.getFrequency());
                        }
                    }
                }
                if ((! useStopword && useStartword && sta.isStartWord(token))) {
                    token = stemmer.stem(token);
                    if (token.trim().length() > 0) {
                        if (corpusNgrams_aux.containsKey(token)) {
                            corpusNgrams_aux.put(token, corpusNgrams_aux.get(token) + n.getFrequency());
                        } else {
                            corpusNgrams_aux.put(token, n.getFrequency());
                        }
                    }
                }
                if ((useStopword && useStartword && ! stp.isStopWord(token) && sta.isStartWord(token))) {
                    token = stemmer.stem(token);
                    if (token.trim().length() > 0) {
                        if (corpusNgrams_aux.containsKey(token)) {
                            corpusNgrams_aux.put(token, corpusNgrams_aux.get(token) + n.getFrequency());
                        } else {
                            corpusNgrams_aux.put(token, n.getFrequency());
                        }
                    }
                }
                if (! useStopword && ! useStartword) {
                    token = stemmer.stem(token);
                    if (token.trim().length() > 0) {
                        if (corpusNgrams_aux.containsKey(token)) {
                            corpusNgrams_aux.put(token, corpusNgrams_aux.get(token) + n.getFrequency());
                        } else {
                            corpusNgrams_aux.put(token, n.getFrequency());
                        }
                    }
                }
            }
        }

        return corpusNgrams_aux;
    }
}
