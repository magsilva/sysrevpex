package visualizer.representation;


import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import visualizer.corpus.Corpus;
import visualizer.graph.Vertex;
import visualizer.matrix.SparseMatrix;
import visualizer.projection.ProjectionData;
import visualizer.ranking.text.MatrixTransformationType;
import visualizer.textprocessing.Ngram;
import visualizer.textprocessing.stemmer.Stemmer;
import visualizer.textprocessing.stemmer.StemmerFactory;
import visualizer.textprocessing.stemmer.StemmerType;
import weka.core.Stopwords;

public abstract class AbstractRepresentation implements Representation {

    protected Corpus corpus;
    
    protected StemmerType stemmerType;
    
    protected MatrixTransformationType mattype;
    protected int numberGrams;
    protected int lowerCut, upperCut;
    protected boolean useStopword, resolve_plural = false, include_references = false;
    protected ArrayList<Ngram> ngrams;

    public AbstractRepresentation(Corpus corpus) {
        this.corpus = corpus;
    }

    public SparseMatrix getMatrix(int lowerCut, int upperCut, int numberGrams, StemmerType stemmerType, MatrixTransformationType mattype, boolean useStopword, boolean include_references, boolean resolve_plural) throws IOException {
        this.lowerCut = lowerCut;
        this.upperCut = upperCut;
        this.numberGrams = numberGrams;
        this.stemmerType = stemmerType;
        this.mattype = mattype;
        this.include_references = include_references;
        this.useStopword = useStopword;
        this.resolve_plural = resolve_plural;

        //store the ngrams present on the corpus
        this.ngrams = getCorpusNgrams();
        return this.getMatrix(corpus.getDocumentsIds(), null);
    }

    protected ArrayList<Ngram> getCorpusNgrams() throws IOException {
        HashMap<String, Integer> corpusNgrams_aux = new HashMap<>();
        Stopwords stp = null;

        if (useStopword) {
            stp = Stopwords.getInstance();
        }
        String token;

        if (this.stemmerType == StemmerType.NONE && this.resolve_plural) {
            for (Ngram n : this.corpus.getCorpusNgrams()) {
                token = n.getNgram();
                if (useStopword && !stp.isStopWord(token)) {
                    token = StemmerFactory.getInstance(stemmerType).stem(token);
                    if (token.trim().length() > 0) {
                        token = PlingStemmer.stem(token);
                        if (corpusNgrams_aux.containsKey(token)) {
                            corpusNgrams_aux.put(token, corpusNgrams_aux.get(token) + n.getFrequency());
                        } else {
                            corpusNgrams_aux.put(token, n.getFrequency());
                        }
                    }
                }
            }
        } else {
            for (Ngram n : this.corpus.getCorpusNgrams()) {
                token = n.getNgram();
                if (useStopword && !stp.isStopWord(token)) {
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
        }

        int freq;
        ArrayList<Ngram> ngrams_aux = new ArrayList<>();
        for (Entry<String, Integer> entry : corpusNgrams_aux.entrySet()) {
            freq = entry.getValue();
            if (upperCut >= 0) {
                if (freq >= lowerCut && freq <= upperCut) {
                    ngrams_aux.add(new Ngram(entry.getKey(), freq));
                }
            } else {
                if (freq >= lowerCut) {
                    ngrams_aux.add(new Ngram(entry.getKey(), freq));
                }
            }
        }

        Collections.sort(ngrams_aux);
        return ngrams_aux;
    }

    public SparseMatrix getMatrix(ProjectionData pdata) throws IOException {
        this.lowerCut = pdata.getLunhLowerCut();
        this.upperCut = pdata.getLunhUpperCut();
        this.numberGrams = corpus.getNumberGrams();
        this.stemmerType = pdata.getStemmer();
        this.useStopword = pdata.isUseStopword();
        this.include_references = pdata.getIncludeReferencesInBOW();

        //store the ngrams present on the corpus
        this.ngrams = this.getCorpusNgrams();
        return this.getMatrix(corpus.getDocumentsIds(), pdata);
    }

    public SparseMatrix getMatrixSelected(int lowerCut, int upperCut, int numberGrams, StemmerType stemmer, boolean useStopword, boolean resolve_plural, ArrayList<Vertex> selected) throws IOException {

        this.lowerCut = lowerCut;
        this.upperCut = upperCut;
        this.numberGrams = numberGrams;
        this.stemmerType = stemmer;
        this.useStopword = useStopword;
        this.resolve_plural = resolve_plural;

        long[] ids = new long[selected.size()];
        for (int i = 0; i < ids.length; i++) {
            ids[i] = selected.get(i).getId();
        }

        //store the ngrams present on the selected corpus
        this.ngrams = getCorpusNgrams(ids);

        return getMatrix(ids, null);
    }

    protected ArrayList<Ngram> getCorpusNgrams(long[] ids) throws IOException {
        HashMap<String, Integer> corpusNgrams_aux = new HashMap<>();
        Iterator<Map.Entry<String, Integer>> iterator;
        Map.Entry<String, Integer> entry;
        HashMap<String, Integer> docNgrams;
        String token, new_token;

        Stopwords stp = null;

        if (useStopword) {
            stp = Stopwords.getInstance();
        }

        if (this.stemmerType == StemmerType.NONE && this.resolve_plural) {
            for (long url : ids) {
                docNgrams = getNgrams(url);
                iterator = docNgrams.entrySet().iterator();
                while (iterator.hasNext()) {
                    entry = iterator.next();
                    token = entry.getKey();
                    if (useStopword && !stp.isStopWord(token)) {
                        if (token.trim().length() > 0) {
                            new_token = PlingStemmer.stem(token);
                            if (corpusNgrams_aux.containsKey(new_token)) {
                                corpusNgrams_aux.put(new_token, corpusNgrams_aux.get(new_token) + docNgrams.get(token));
                            } else {
                                corpusNgrams_aux.put(new_token, docNgrams.get(token));
                            }
                        }
                    }
                }
            }
        } else {
            for (long url : ids) {
                docNgrams = getNgrams(url);
                iterator = docNgrams.entrySet().iterator();
                while (iterator.hasNext()) {
                    entry = iterator.next();
                    token = entry.getKey();
                    if (useStopword && !stp.isStopWord(token)) {
                        if (corpusNgrams_aux.containsKey(token)) {
                            corpusNgrams_aux.put(token, corpusNgrams_aux.get(token) + docNgrams.get(token));
                        } else {
                            corpusNgrams_aux.put(token, docNgrams.get(token));
                        }
                    }

                }
            }
        }
        ArrayList<Ngram> ngrams_aux = new ArrayList<>();
        iterator = corpusNgrams_aux.entrySet().iterator();
        while (iterator.hasNext()) {
            entry = iterator.next();
            if (upperCut >= 0) {
                if (entry.getValue() >= lowerCut && entry.getValue() <= upperCut) {
                    ngrams_aux.add(new Ngram(entry.getKey(), entry.getValue()));
                }
            } else {
                if (entry.getValue() >= lowerCut) {
                    ngrams_aux.add(new Ngram(entry.getKey(), entry.getValue()));
                }
            }
        }

        Collections.sort(ngrams_aux);
        return ngrams_aux;
    }

    protected HashMap<String, Integer> getNgrams(long url) {
        try {
            HashMap<String, Integer> ngrams_aux = new HashMap<>();
            Stopwords stp = null;
            if (useStopword) {
                stp = Stopwords.getInstance();
            }
            List<Ngram> fngrams = corpus.getNgrams(Long.toString(url));
            String token;
            Stemmer stemmer = StemmerFactory.getInstance(stemmerType);
            if (fngrams != null) {
                for (Ngram n : fngrams) {
                    token = n.getNgram();
                    if (useStopword && !stp.isStopWord(token)) {
                        token = stemmer.stem(token);
                        if (token.trim().length() > 0) {
                            if (ngrams_aux.containsKey(token)) {
                                ngrams_aux.put(token, ngrams_aux.get(token) + n.getFrequency());
                            } else {
                                ngrams_aux.put(token, n.getFrequency());
                            }
                        }
                    }
                }
            }
            return ngrams_aux;
        } catch (Exception ex) {
            Logger.getLogger(AbstractRepresentation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Ngram> getNgramsAccordingTo(int lowerCut, int upperCut, int numberGrams,
            StemmerType stemmer, boolean useStopword) throws IOException {

        this.lowerCut = lowerCut;
        this.upperCut = upperCut;
        this.numberGrams = numberGrams;
        this.stemmerType = stemmer;
        this.useStopword = useStopword;

        return this.getCorpusNgrams();
    }

    public ArrayList<Ngram> getNgrams() {
        return ngrams;
    }

    public abstract SparseMatrix getMatrix(long[] ids, ProjectionData pdata) throws IOException;
}
