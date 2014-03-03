/*
 * Copyright (c) 2011 Marco Aurélio Graciotto Silva <magsilva@gmail.com>
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
 * You should have received a copy of the GNU General Public License along 
 * with PEx. If not, see <http://www.gnu.org/licenses/>.
 */

package visualizer.textprocessing;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import com.ironiacorp.string.StringUtil;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class Ngram implements Comparable<Ngram>, Serializable
{
	/**
	 * Default behaviour. For compability with ordered structures, it must be
	 * set to false.
	 */
	public static final boolean DEFAULT_BEHAVIOUR_PEDANTIC = false;
	
	public static final String SEPARATOR = " "; 
	
    private String ngram;
    
    private String[] words;
    
    private int frequency;
    
    private int n;
    
    private static final long serialVersionUID = 1L;
	
    private boolean pedantic = DEFAULT_BEHAVIOUR_PEDANTIC;
    
    public Ngram(String ngram) {
    	this(ngram, 1);
    }
    
    public Ngram(String ngram, int frequency) {  	
        setNgram(ngram);
        discoverN();
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
		if (frequency < 0) {
			throw new IllegalArgumentException("Cannot set the frequency to a negative value: " + frequency);
		}
		this.frequency = frequency;
	}

	public int getN()
	{
		return n;
	}

	private void discoverN()
	{
		List<String> newWords = new ArrayList<String>();
		Iterator<String> i;

		String[] wordsTemp = ngram.split(SEPARATOR);
   		for (int j = 0; j < wordsTemp.length; j++) {
   			newWords.add(wordsTemp[j]);
   		}
    		
   		i = newWords.iterator();
   		while (i.hasNext()) { 
   			String newWord = i.next();
   			if (StringUtil.isEmpty(newWord)) {
   				i.remove();
   			}
		}

   		words = new String[newWords.size()];
   		i = newWords.iterator();
   		for (int j = 0; j < words.length; j++) {
   			words[j] = i.next();
    	}
		
		this.n = words.length;
	}
	
	
	public int compareTo(Ngram o)
	{
		return compareTo(o, pedantic);
	}

	public int compareTo(Ngram otherNgram, boolean pedantic)
	{
		if (pedantic) {
			if (! ngram.equals(otherNgram.ngram)) {
				throw new UnsupportedOperationException("Cannot compare ngrams which values are different");
			} 
		}
		
		return frequency - otherNgram.frequency;
    }
	
	
	

    @Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + frequency;
		result = prime * result + ((ngram == null) ? 0 : ngram.hashCode());
		return result;
	}

    @Override
	public boolean equals(Object obj)
	{
    	return equals(obj, pedantic);
	}
    
	public boolean equals(Object obj, boolean pedantic)
	{
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (getClass() != obj.getClass())
			return false;
		
		Ngram other = (Ngram) obj;
			
		if (ngram == null) {
			if (other.ngram != null) {
				return false;
			}
		} else if (! ngram.equals(other.ngram)) {
			return false;
		}
		
		if (pedantic) {
			if (frequency != other.frequency) {
				return false;
			}
		}
		
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
	
	public static class PedandicComparator implements Comparator<Ngram> {

		@Override
		public int compare(Ngram o1, Ngram o2) {
			return o1.compareTo(o2, true);
		}
	}
	
	public static class ForgivingComparator implements Comparator<Ngram> {

		@Override
		public int compare(Ngram o1, Ngram o2) {
			return o1.compareTo(o2, false);
		}
	}
}
