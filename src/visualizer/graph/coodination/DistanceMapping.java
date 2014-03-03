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

package visualizer.graph.coodination;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import visualizer.corpus.Corpus;
import visualizer.matrix.Matrix;
import visualizer.matrix.SparseMatrix;
import visualizer.matrix.SparseVector;
import visualizer.ranking.text.MatrixTransformation;
import visualizer.ranking.text.MatrixTransformationFactory;
import visualizer.textprocessing.BasicPreProcessor;
import visualizer.textprocessing.Ngram;
import visualizer.textprocessing.MonoliticPreprocessor;
import visualizer.textprocessing.PipelinePreprocessor;
import visualizer.textprocessing.PreProcessor;
import visualizer.textprocessing.PreProcessorFactory;
import visualizer.projection.ProjectionData;
import visualizer.projection.distance.DissimilarityFactory;
import visualizer.projection.distance.Dissimilarity;
import visualizer.util.Pair;
import visualizer.view.Viewer;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class DistanceMapping extends Mapping
{
    private ProjectionData localPData;
    
    private ProjectionData outerPData;
    
    private int nrNeighbors = 5;

    /** Creates a new instance of DistanceMapping
     * @param localPData
     * @param outerPData
     * @param nrNeighbors
     * @param local
     * @param outer
     * @throws IOException 
     */
    public DistanceMapping(ProjectionData localPData, ProjectionData outerPData,
            int nrNeighbors, Viewer local, Viewer outer) throws IOException {
        this.localPData = localPData;
        this.outerPData = outerPData;
        this.nrNeighbors = nrNeighbors;
        this.outer = outer;

        this.createMapping(local, outer);
    }

    @Override
    public String getName() {
        return "distance";
    }

    public void setLocalPData(ProjectionData localPData) {
        this.localPData = localPData;
    }

    public void setOuterPData(ProjectionData outerPData) {
        this.outerPData = outerPData;
    }

    public void setNrNeighbors(int nrNeighbors) {
        this.nrNeighbors = nrNeighbors;
    }

    @Override
    protected void createMapping(Viewer local, Viewer outer) throws IOException {
        if (local != null && outer != null) {
            //processing local corpus
            Corpus local_cp = local.getGraph().getCorpus();

            // MonoliticPreprocessor local_pp = new MonoliticPreprocessor();
            BasicPreProcessor local_pp = PreProcessorFactory.getBasicInstance();
            local_pp.setCorpus(local_cp);
            local_pp.setLowerCut(localPData.getLunhLowerCut());
            local_pp.setUpperCut(localPData.getLunhUpperCut());
            local_pp.setStopword(localPData.isUseStopword());
            local_pp.setStartword(false);
            local_pp.setStemmer(localPData.getStemmer());
            local_pp.setNumberGrams(localPData.getNumberGrams());
            Matrix local_points = local_pp.getMatrix();
            Collection<Ngram> local_ngrams = local_pp.getNgrams();

            MatrixTransformation transf1 = MatrixTransformationFactory.getInstance(localPData.getMatrixTransformationType());
            local_points = transf1.tranform(local_points, null);
            
            //processing outer corpus
            Corpus outer_cp = outer.getGraph().getCorpus();

            // MonoliticPreprocessor outer_pp = new MonoliticPreprocessor();
            BasicPreProcessor outer_pp = PreProcessorFactory.getBasicInstance();
            outer_pp.setCorpus(outer_cp);
            outer_pp.setLowerCut(outerPData.getLunhLowerCut());
            outer_pp.setUpperCut(outerPData.getLunhUpperCut());
            outer_pp.setStopword(outerPData.isUseStopword());
            outer_pp.setStartword(false);
            outer_pp.setStemmer(outerPData.getStemmer());
            outer_pp.setNumberGrams(outerPData.getNumberGrams());
            Matrix outer_points = outer_pp.getMatrix();
            List<Ngram> outer_ngrams = outer_pp.getNgrams();

            MatrixTransformation transf2 = MatrixTransformationFactory.getInstance(outerPData.getMatrixTransformationType());
            outer_points = transf2.tranform(outer_points, null);

            //tranforming the matrixes to have the same format (same common columns)
            Collection<Ngram> common_ngrams = findCommonNGrams(local_ngrams, outer_ngrams);

            if (common_ngrams.size() < 1) {
                throw new IOException("There are not common attributes between the projections!");
            }

            Matrix new_local_points = transformMatrix(local_ngrams, common_ngrams, local_points);
            Matrix new_outer_points = transformMatrix(outer_ngrams, common_ngrams, outer_points);

            //Deciding the distance type
            Dissimilarity diss = DissimilarityFactory.getInstance(this.localPData.getDissimilarityType());

            //for each outer point
            for (int i = 0; i < new_outer_points.getRowCount(); i++) {
                Pair[] pairList = new Pair[this.nrNeighbors];

                for (int j = 0; j < this.nrNeighbors; j++) {
                    double distance = diss.calculate(new_outer_points.getRow(i), new_local_points.getRow(j));
                    pairList[j] = new Pair(j, distance);
                }

                //find the nearest points between the local points
                for (int j = this.nrNeighbors; j < new_local_points.getRowCount(); j++) {
                    double distance = diss.calculate(new_outer_points.getRow(i), new_local_points.getRow(j));
                    this.addDistance(pairList, new Pair(j, distance));
                }

                //creating the mappings
                Map m = new Map();
                m.outer = outer.getGraph().getVertex().get(i);

                for (int j = 0; j < pairList.length; j++) {
                    m.local.add(local.getGraph().getVertex().get(pairList[j].index));
                }

                this.mapping.add(m);
            }
        }
    }

    public void addDistance(Pair[] pairList, Pair newPair) {
        if (pairList[0].value > newPair.value) {
            int i = 0;
            while (i < pairList.length && pairList[i].value > newPair.value) {
                if (i < pairList.length - 1) {
                    pairList[i] = pairList[i + 1];
                }
                i++;
            }
            pairList[i - 1] = newPair;
        }
    }

    public Matrix transformMatrix(Collection<Ngram> old_ngrams, Collection<Ngram> new_ngrams, Matrix old_matrix)
    {
        //creating the new matrix
        Matrix new_matrix = new SparseMatrix();

        //creating the index
        int[] index = new int[new_ngrams.size()];
        int i = 0;

        //for each new ngram
        for (Ngram new_ngram : new_ngrams) {
            int old_index = 0;
            int j = 0;
            for (Ngram old_ngram : old_ngrams) {
                if (old_ngram.getNgram().equals(new_ngram.getNgram())) {
                    old_index = j;
                    break;
                } else {
                	j++;
                }
            }
            index[i] = old_index;
            i++;
        }

        for (i = 0; i < old_matrix.getRowCount(); i++) {
            double[] vector = new double[index.length];
            Arrays.fill(vector, 0.0f);

            for (int j = 0; j < vector.length; j++) {
                vector[j] = old_matrix.getRow(i).getValue(index[j]);
            }

            SparseVector sv = new SparseVector(vector, old_matrix.getRow(i).getId(), old_matrix.getRow(i).getKlass());
            new_matrix.addRow(sv);
        }

        return new_matrix;
    }

    public Collection<Ngram> findCommonNGrams(Collection<Ngram> ngrams_1, Collection<Ngram> ngrams_2)
    {
        ArrayList<Ngram> final_ngrams = new ArrayList<Ngram>();

        for (Ngram ngram1 : ngrams_1) {
        	for (Ngram ngram2 : ngrams_2) {
        		if (ngram1.getNgram().equals(ngram2.getNgram())) {
        			final_ngrams.add(ngram1);
        		}
        	}
        }

        return final_ngrams;
    }

}
