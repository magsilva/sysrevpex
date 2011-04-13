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

package visualizer.corpus;

import java.util.ArrayList;
import java.util.StringTokenizer;
import visualizer.textprocessing.Ngram;

/**
 * This class represents a Corpus (a set of documents).
 * 
 * @author Fernando Vieira Paulovich, Roberto Pinho
 */
public abstract class BaseCorpus implements Corpus
{
    protected float[] cdata;
    
    protected ArrayList<String> ids;
    
    protected String url;
    
    protected int nrGrams;
    
    protected static Encoding encoding = Encoding.ASCII;
    
    /**
     * Creates a new instance of Corpus
     * @param url
     * @param nrGrams 
     */
    public BaseCorpus(String url, int nrGrams)
    {
        this.url = url;
        this.nrGrams = nrGrams;
    }

    /* (non-Javadoc)
	 * @see visualizer.corpus.ICorpus#getFullContent(java.lang.String)
	 */
    @Override
	public abstract String getFullContent(String id);

    /* (non-Javadoc)
	 * @see visualizer.corpus.ICorpus#getViewContent(java.lang.String)
	 */
    @Override
	public abstract String getViewContent(String id);


    /* (non-Javadoc)
	 * @see visualizer.corpus.ICorpus#getNgrams(java.lang.String)
	 */
    @Override
	public abstract ArrayList<Ngram> getNgrams(String id);

    /* (non-Javadoc)
	 * @see visualizer.corpus.ICorpus#getCorpusNgrams()
	 */
    @Override
	public abstract ArrayList<Ngram> getCorpusNgrams();

    /**
     * This method must be implemented to fill all attributes (urls, and 
     * cdata) of a corpus.
     */
    protected abstract void initializeCorpus();

    /* (non-Javadoc)
	 * @see visualizer.corpus.ICorpus#getClassData()
	 */
    @Override
	public float[] getClassData() {
        return this.cdata;
    }

    /* (non-Javadoc)
	 * @see visualizer.corpus.ICorpus#getIds()
	 */
    @Override
	public ArrayList<String> getIds() {
        return this.ids;
    }

    /**
     * Return the encoding of this corpus. 
     * @return The corpus enconding.
     */
    public static Encoding getEncoding() {
        return encoding;
    }

    /* (non-Javadoc)
	 * @see visualizer.corpus.ICorpus#getNumberGrams()
	 */
    @Override
	public int getNumberGrams() {
        return nrGrams;
    }

    /* (non-Javadoc)
	 * @see visualizer.corpus.ICorpus#getTitle(int, java.lang.String)
	 */
    @Override
	public String getTitle(int nrLines, String id)
    {       
        if (nrLines <= 0) {
        	throw new IllegalArgumentException("Number of lines must be greater than zero");
        }

        String content = getFullContent(id);
        StringTokenizer tokenizer = new StringTokenizer(content, "\r\n");
        StringBuilder title = new StringBuilder();

        for (int i = 0; i < nrLines && tokenizer.hasMoreTokens();) {
        	String line = tokenizer.nextToken();
        	line = line.replaceAll("<.*?>", "");
        	line = line.trim();
        	if (! line.isEmpty()) {
        		title.append(line);
        		title.append(" ");
        		i++;
        	}
        }

        if (title.length() != 0) {
        	title.deleteCharAt(title.length() - 1);
        }

        return title.toString();
    }

    /**
     * Changes the corpus encoding.
     * @param aEncoding The new encoding.
     */
    public static void setEncoding(Encoding aEncoding) {
        encoding = aEncoding;
    }

}
