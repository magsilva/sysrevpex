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
 * Contributor(s): Rosane Minghim <rminghim@icmc.usp.br>
 *
 * You should have received a copy of the GNU General Public License along 
 * with PEx. If not, see <http://www.gnu.org/licenses/>.
 *
 * ***** END LICENSE BLOCK ***** */

package visualizer.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Fernando Vieira Paulovich
 */
public class SystemPropertiesManager
{
	private static final String filename = "system.properties";

	private Properties properties = null;

	private static SystemPropertiesManager _instance = null;

	/**
	 * Creates a new instance of SystemPropertiesManager
	 */
	private SystemPropertiesManager()
	{
		properties = new Properties();

		try {
			InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(filename);
			
			if (is != null) {
				properties.load(is);
				is.close();
			}
		} catch (FileNotFoundException ex) {
			Logger.getLogger(SystemPropertiesManager.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(SystemPropertiesManager.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	synchronized public static SystemPropertiesManager getInstance()
	{
		if (_instance == null) {
			_instance = new SystemPropertiesManager();
		}
		return _instance;
	}

	public String getProperty(String id)
	{
		if (properties.containsKey(id)) {
			return properties.getProperty(id);
		} else {
			return "";
		}
	}

	public void setProperty(String id, String value)
	{
		properties.setProperty(id, value);

		try {
			URL propertiesFilename = Thread.currentThread().getContextClassLoader().getResource(filename);
			if (propertiesFilename != null) {
				File file = new File(propertiesFilename.toURI());
				FileOutputStream out = new FileOutputStream(file);
				properties.store(out, "Recording the system's properties");
				out.flush();
				out.close();
			}
		} catch (URISyntaxException ex) {
			Logger.getLogger(SystemPropertiesManager.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
		}
	}
}
