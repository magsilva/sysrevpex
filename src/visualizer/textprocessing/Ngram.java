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
 * of the original code is Fernando Vieira Paulovich <fpaulovich@gmail.com>.
 *
 * Contributor(s): Rosane Minghim <rminghim@icmc.usp.br>, 
 *                 Roberto Pinho <robertopinho@yahoo.com.br>
 *
 * You should have received a copy of the GNU General Public License along 
 * with PEx. If not, see <http://www.gnu.org/licenses/>.
 *
 * ***** END LICENSE BLOCK ***** */

package visualizer.textprocessing;

import java.io.Serializable;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class Ngram implements Comparable, Serializable
{
	private static final String[] PARSING_SEPARATORS = { " ", "<>" }; 

	private static final String SEPARATOR = " "; 
	
    private String ngram;
    
    private String[] words;
    
    private int frequency;
    
    private int n;
    
    private static final long serialVersionUID = 1L;
	
    private boolean pedantic = false;
    
    public Ngram(String ngram, int n) {
        this(ngram, n, 1);
    }

    public Ngram(String ngram, int n, int frequency) {  	
        setNgram(ngram);
        setN(n);
        setFrequency(frequency);
    }

    public boolean isPedantic()
	{
		return pedantic;
	}

	public void setPedantic(boolean pedantic)
	{
		this.pedantic = pedantic;
	}

	public String getNgram()
	{
		return ngram;
	}

	public int getFrequency()
	{
		return frequency;
	}

	public void setFrequency(int frequency)
	{
		this.frequency = frequency;
	}

	public int getN()
	{
		return n;
	}
	
	private void setN(int n)
	{
		if (n <= 0) {
			throw new IllegalArgumentException("Invalid size for the N-gram");
		}
		
    	String[] words = null;	
    	for (String separator : PARSING_SEPARATORS) {
    		words = ngram.split(separator);
    		if (words.length > 1) {
    			if (n != words.length) {
    				throw new IllegalArgumentException("Invalid ngram: " + ngram + " has an order " + (words.length + 1) + ", but you said it was " + n);
    			}
    			this.words = words;
    			break;
    		}
    	}
    	if (n == 1) {
    		words = new String[1];
    		words[0] = ngram;
    	}
		
		this.n = n;
	}
	

	public int compareTo(Object o)
	{
		Ngram otherNgram = (Ngram) o;
		if (! equals(otherNgram)) {
			throw new UnsupportedOperationException("Cannot compara ngrams which values are different");
		}
		
		return otherNgram.frequency - frequency;
    }
	

    @Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + frequency;
		result = prime * result + n;
		result = prime * result + ((ngram == null) ? 0 : ngram.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (getClass() != obj.getClass())
			return false;
		
		Ngram other = (Ngram) obj;
		
		if (pedantic) {
			if (frequency != other.frequency)
				return false;
			
			if (n != other.n)
				return false;
		}
		
		if (ngram == null) {
			if (other.ngram != null)
				return false;
		} else if (! ngram.equals(other.ngram))
			return false;
		
		return true;
	}

	@Override
    public String toString() {
		if (n == 1) {
			return ngram;
		} else {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < words.length; i++) {
				sb.append(words[i]);
				if (i < (words.length - 1)) {
					sb.append(SEPARATOR);
				}
			}
			return sb.toString();
		}
    }

	public void setNgram(String text)
	{
		if (text == null || text.trim().isEmpty()) {
			throw new IllegalArgumentException("Invalid text");
		}
		this.ngram = text;
	}
}
