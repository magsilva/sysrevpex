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

package visualizer.matrix;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public abstract class Matrix
{
    protected int dimensions;
    
    protected List<String> attributes = new ArrayList<String>();
    
    protected List<Vector> rows = new ArrayList<Vector>();

    public abstract void load(String filename) throws IOException;

    public abstract void save(String filename) throws IOException;

    public boolean contains(Vector vector) {
        return this.rows.contains(vector);
    }

    public void addRow(Vector vector) {
        assert (rows.isEmpty() || this.dimensions == vector.size()) :
                "ERROR: vector of wrong size!";

        this.rows.add(vector);
        this.dimensions = vector.size();
    }

    public void setRow(int index, Vector vector) {
        assert (rows.size() > index && this.dimensions == vector.size()) :
                "ERROR: wrong index or vector of wrong size!";

        this.rows.set(index, vector);
    }

    public Vector removeRow(int index) {
        assert (rows.size() > index) : "ERROR: wrong index!";

        return this.rows.remove(index);
    }

    public int getRowCount() {
        return this.rows.size();
    }

    public int getDimensions() {
        return dimensions;
    }

    public Vector getRow(int row) {
        assert (rows.size() > row) :
                "ERROR: this row does not exists in the matrix!";

        return this.rows.get(row);
    }

    public void normalize() {
        int size = this.rows.size();

        for (int i = 0; i < size; i++) {
            if (!this.rows.get(i).isNull()) {
                this.rows.get(i).normalize();
            } else {
                Logger.getLogger(Matrix.class.getName()).log(Level.INFO,
                        "Ignoring null vector on the normalization.");
            }
        }
    }

    public double[][] toMatrix() {
        double[][] matrix = new double[this.rows.size()][];

        int size = this.rows.size();
        for (int i = 0; i < size; i++) {
            matrix[i] = this.rows.get(i).toArray();
        }

        return matrix;
    }

    public List<String> getAttributes() {
        return this.attributes;
    }

    public void setAttributes(List<String> attributes) {
        assert (rows.isEmpty() || this.dimensions == attributes.size()) :
                "ERROR: attributes and vectors of different sizes!";

        this.attributes = attributes;
    }

    public ArrayList<String> getIds() {
        ArrayList<String> ids = new ArrayList<String>();

        int size = this.rows.size();
        for (int i = 0; i < size; i++) {
            ids.add(this.rows.get(i).getId());
        }

        return ids;
    }

    public double[] getClassData() {
        double[] cdata = new double[this.rows.size()];

        for (int i = 0; i < cdata.length; i++) {
            cdata[i] = this.rows.get(i).getKlass();
        }

        return cdata;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        SparseMatrix clone = new SparseMatrix();
        clone.dimensions = this.dimensions;

        for (String attr : attributes) {
            clone.attributes.add(attr);
        }

        for (Vector v : this.rows) {
            clone.rows.add((Vector) v.clone());
        }

        return clone;
    }
}
