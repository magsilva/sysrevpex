/*
 * Copyright (c) 2013 Marco Aurélio Graciotto Silva <magsilva@gmail.com>
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

/**
 * Extracts elements from a resource.
 *
 * @param <T> Type of element to be extracted.
 */
public interface TermExtractor<T>
{
	T next();
}
