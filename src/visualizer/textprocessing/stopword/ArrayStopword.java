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
 * Contributor(s): Roberto Pinho <robertopinho@yahoo.com.br>
 *                 Rosane Minghim <rminghim@icmc.usp.br>
 *
 * You should have received a copy of the GNU General Public License along 
 * with PEx. If not, see <http://www.gnu.org/licenses/>.
 *
 * ***** END LICENSE BLOCK ***** */

package visualizer.textprocessing.stopword;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import visualizer.util.SystemPropertiesManager;

/**
 *  Note that the words on the stopwords list must be on different lines without spaces.
 * 
 * @author Fernando Vieira Paulovich
 */
public class ArrayStopword implements StopWord
{
	private static ArrayStopword instance;
	
	private List<String> stopwords;

	private File file;

	private ArrayStopword()
	{
		stopwords = new ArrayList<String>();
	}

	public synchronized static ArrayStopword getInstance() throws java.io.IOException
	{
		if (instance == null) {
			instance = new ArrayStopword();
			
			SystemPropertiesManager m = SystemPropertiesManager.getInstance();
			String stpFilename = m.getProperty("SPW.FILE");
			instance.changeStopwordList(stpFilename);
		}
		
		return instance;
	}

	public void changeStopwordList(String stpFilename) throws java.io.IOException
	{
		readStopwordList(stpFilename);
	}

	public Iterator<String> iterator()
	{
		return stopwords.iterator();
	}

	public void addStopword(String stopword)
	{
		stopwords.add(stopword);
		Collections.sort(stopwords);
	}

	
	public void addStopwords(List<String> stopwords)
	{
		this.stopwords.addAll(stopwords);
		Collections.sort(this.stopwords);
	}

	public void removeStopword(String stopword)
	{
		stopwords.remove(stopword);
	}

	public void saveStopwordsList(String filename) throws java.io.IOException
	{
		File file = new File(filename);
		
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new FileWriter(file));
			for (String stopword : stopwords) {
				out.write(stopword);
				out.write("\n");
			}
		} catch (IOException ex) {
			throw new java.io.IOException("Problems saving \"" + filename + "\" file!");
		} finally {
			this.file = file;
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

	public String getFilename()
	{
		return file.getAbsolutePath();
	}

	public boolean isStopWord(String word)
	{
		return (Collections.binarySearch(stopwords, word) >= 0);
	}

	private void readStopwordList(String filename) throws IOException
	{
		BufferedReader in = null;
		File file = new File(filename);
		if (! file.exists()) {
			throw new java.io.IOException("File \"" + filename + "\" was not found!");
		}
		this.file = file;

		try {
			in = new BufferedReader(new FileReader(file));
			String line = null;
			while ((line = in.readLine()) != null) {
				line = line.trim();
				if (! line.isEmpty()) {
					stopwords.add(line.toLowerCase());
				}
			}
			Collections.sort(this.stopwords);
		}  catch (IOException e) {
			throw new IOException("Problems reading the file \"" + filename + "\"");
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException ex) {
				}
			}
		}
	}
}
