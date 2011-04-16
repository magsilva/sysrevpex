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

package visualizer.view;

import visualizer.dimensionreduction.DimensionalityReductionType;
import visualizer.projection.ProjectionData;
import visualizer.projection.ProjectionType;
import visualizer.projection.SourceType;
import visualizer.projection.distance.DissimilarityType;
import visualizer.projection.distance.kolmogorov.CompressorType;

/**
 *
 * @author  Fernando Vieira Paulovich
 */
public class ReportView extends javax.swing.JPanel {

    /** Creates new form ReportView */
    public ReportView() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        sourceLabel = new javax.swing.JLabel();
        sourceTextField = new javax.swing.JTextField();
        preprocessingPanel = new javax.swing.JPanel();
        luhnLowerCutLabel = new javax.swing.JLabel();
        luhnLowerCutTextField = new javax.swing.JTextField();
        numberGramsLabel = new javax.swing.JLabel();
        numberGramsTextField = new javax.swing.JTextField();
        luhnUpperCutLabel = new javax.swing.JLabel();
        luhnUpperCutTextField = new javax.swing.JTextField();
        projectionPanel = new javax.swing.JPanel();
        numberCPTextField = new javax.swing.JTextField();
        projectionTypeTextField = new javax.swing.JTextField();
        numberCPLabel = new javax.swing.JLabel();
        distanceTypeTextField = new javax.swing.JTextField();
        distanceTypeLabel = new javax.swing.JLabel();
        numberNeighborsTextField = new javax.swing.JTextField();
        clusterFactorLabel = new javax.swing.JLabel();
        numberNeighborsLabel = new javax.swing.JLabel();
        projectionTypeLabel = new javax.swing.JLabel();
        projTechniqueTextField = new javax.swing.JTextField();
        projTechniqueLabel = new javax.swing.JLabel();
        clusterFactorTextField = new javax.swing.JTextField();
        forcePanel = new javax.swing.JPanel();
        fractionDeltaTextField = new javax.swing.JTextField();
        numberIterationsTextField = new javax.swing.JTextField();
        numberIterationsLabel = new javax.swing.JLabel();
        fractionDeltaLabel = new javax.swing.JLabel();
        dimenReductionPanel = new javax.swing.JPanel();
        dimenRedTechniqueLabel = new javax.swing.JLabel();
        dimenRedTechniqueTextField = new javax.swing.JTextField();
        resultingDimenLabel = new javax.swing.JLabel();
        resultingDimenLabelTextField = new javax.swing.JTextField();
        dataPanel = new javax.swing.JPanel();
        numberObjectsLabel = new javax.swing.JLabel();
        numberObjectsTextField = new javax.swing.JTextField();
        numberDimensionsLabel = new javax.swing.JLabel();
        numberDimensionsTextField = new javax.swing.JTextField();

        setBorder(javax.swing.BorderFactory.createTitledBorder("Projection Parameters"));
        setLayout(new java.awt.GridBagLayout());

        sourceLabel.setText("Source");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        add(sourceLabel, gridBagConstraints);

        sourceTextField.setColumns(40);
        sourceTextField.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        add(sourceTextField, gridBagConstraints);

        preprocessingPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Pre-processing"));
        preprocessingPanel.setLayout(new java.awt.GridBagLayout());

        luhnLowerCutLabel.setText("Luhn's lower cut");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        preprocessingPanel.add(luhnLowerCutLabel, gridBagConstraints);

        luhnLowerCutTextField.setColumns(5);
        luhnLowerCutTextField.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        preprocessingPanel.add(luhnLowerCutTextField, gridBagConstraints);

        numberGramsLabel.setText("Number of Grams");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        preprocessingPanel.add(numberGramsLabel, gridBagConstraints);

        numberGramsTextField.setColumns(5);
        numberGramsTextField.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        preprocessingPanel.add(numberGramsTextField, gridBagConstraints);

        luhnUpperCutLabel.setText("Luhn's upper cut");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        preprocessingPanel.add(luhnUpperCutLabel, gridBagConstraints);

        luhnUpperCutTextField.setColumns(5);
        luhnUpperCutTextField.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        preprocessingPanel.add(luhnUpperCutTextField, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(preprocessingPanel, gridBagConstraints);

        projectionPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Projection"));
        projectionPanel.setLayout(new java.awt.GridBagLayout());

        numberCPTextField.setColumns(5);
        numberCPTextField.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        projectionPanel.add(numberCPTextField, gridBagConstraints);

        projectionTypeTextField.setColumns(10);
        projectionTypeTextField.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        projectionPanel.add(projectionTypeTextField, gridBagConstraints);

        numberCPLabel.setText("Number Control Points");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        projectionPanel.add(numberCPLabel, gridBagConstraints);

        distanceTypeTextField.setColumns(10);
        distanceTypeTextField.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        projectionPanel.add(distanceTypeTextField, gridBagConstraints);

        distanceTypeLabel.setText("Distance Type");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        projectionPanel.add(distanceTypeLabel, gridBagConstraints);

        numberNeighborsTextField.setColumns(5);
        numberNeighborsTextField.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        projectionPanel.add(numberNeighborsTextField, gridBagConstraints);

        clusterFactorLabel.setText("Cluster Factor");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        projectionPanel.add(clusterFactorLabel, gridBagConstraints);

        numberNeighborsLabel.setText("Number Neighbors");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        projectionPanel.add(numberNeighborsLabel, gridBagConstraints);

        projectionTypeLabel.setText("Projection Type");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        projectionPanel.add(projectionTypeLabel, gridBagConstraints);

        projTechniqueTextField.setColumns(10);
        projTechniqueTextField.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        projectionPanel.add(projTechniqueTextField, gridBagConstraints);

        projTechniqueLabel.setText("Projection Technique");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        projectionPanel.add(projTechniqueLabel, gridBagConstraints);

        clusterFactorTextField.setColumns(5);
        clusterFactorTextField.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        projectionPanel.add(clusterFactorTextField, gridBagConstraints);

        forcePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Force Scheme"));
        forcePanel.setLayout(new java.awt.GridBagLayout());

        fractionDeltaTextField.setColumns(5);
        fractionDeltaTextField.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        forcePanel.add(fractionDeltaTextField, gridBagConstraints);

        numberIterationsTextField.setColumns(5);
        numberIterationsTextField.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        forcePanel.add(numberIterationsTextField, gridBagConstraints);

        numberIterationsLabel.setText("Number iterations");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        forcePanel.add(numberIterationsLabel, gridBagConstraints);

        fractionDeltaLabel.setText("Fraction of delta");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        forcePanel.add(fractionDeltaLabel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        projectionPanel.add(forcePanel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(projectionPanel, gridBagConstraints);

        dimenReductionPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Dimensionality Reduction"));
        dimenReductionPanel.setLayout(new java.awt.GridBagLayout());

        dimenRedTechniqueLabel.setText("Technique");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        dimenReductionPanel.add(dimenRedTechniqueLabel, gridBagConstraints);

        dimenRedTechniqueTextField.setColumns(10);
        dimenRedTechniqueTextField.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        dimenReductionPanel.add(dimenRedTechniqueTextField, gridBagConstraints);

        resultingDimenLabel.setText("Resulting dimensions");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        dimenReductionPanel.add(resultingDimenLabel, gridBagConstraints);

        resultingDimenLabelTextField.setColumns(5);
        resultingDimenLabelTextField.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        dimenReductionPanel.add(resultingDimenLabelTextField, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(dimenReductionPanel, gridBagConstraints);

        dataPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Data"));
        dataPanel.setLayout(new java.awt.GridBagLayout());

        numberObjectsLabel.setText("Number Objects");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        dataPanel.add(numberObjectsLabel, gridBagConstraints);

        numberObjectsTextField.setColumns(5);
        numberObjectsTextField.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        dataPanel.add(numberObjectsTextField, gridBagConstraints);

        numberDimensionsLabel.setText("Number Dimensions");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        dataPanel.add(numberDimensionsLabel, gridBagConstraints);

        numberDimensionsTextField.setColumns(5);
        numberDimensionsTextField.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        dataPanel.add(numberDimensionsTextField, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(dataPanel, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    public void reset(ProjectionData pdata) {
        this.clean();

        if (pdata.getSourceFile().trim().length() > 0) {
            //set source file
            this.sourceTextField.setText(pdata.getSourceFile());

            //set data 
            this.numberDimensionsTextField.setText(Integer.toString(pdata.getNumberDimensions()));
            this.numberObjectsTextField.setText(Integer.toString(pdata.getNumberObjects()));

            //set pre-processing
            if (pdata.getSourceType() == SourceType.CORPUS) {
                this.luhnLowerCutTextField.setText(Integer.toString(pdata.getLunhLowerCut()));
                this.numberGramsTextField.setText(Integer.toString(pdata.getNumberGrams()));

                if (pdata.getLunhUpperCut() > -1) {
                    this.luhnUpperCutTextField.setText(Integer.toString(pdata.getLunhUpperCut()));
                }
            }

            //set dimensionality reduction
            this.dimenRedTechniqueTextField.setText(pdata.getDimensionReductionType().toString());

            if (pdata.getDimensionReductionType() != DimensionalityReductionType.NONE) {
                this.resultingDimenLabelTextField.setText(Integer.toString(pdata.getTargetDimension()));
            }

            //set projection
            if (pdata.getProjectionType() != null) {
                //projection technique
                this.projTechniqueTextField.setText(pdata.getProjectionType().toString());

                if (pdata.getProjectionType() == ProjectionType.CLASSICAL_SCALING) {
                } else if (pdata.getProjectionType() == ProjectionType.IDMAP) {
                    //projection type
                    this.projectionTypeTextField.setText(pdata.getProjectorType().toString());
                    //fraction of delta
                    this.fractionDeltaTextField.setText(Float.toString(pdata.getFractionDelta()));
                    //number of iterations
                    this.numberIterationsTextField.setText(Integer.toString(pdata.getNumberIterations()));
                } else if (pdata.getProjectionType() == ProjectionType.ISOMAP) {
                    //number neighbors
                    this.numberNeighborsTextField.setText(Integer.toString(pdata.getNumberNeighborsConnection()));
                } else if (pdata.getProjectionType() == ProjectionType.LLE) {
                    //number neighbors
                    this.numberNeighborsTextField.setText(Integer.toString(pdata.getNumberNeighborsConnection()));
                } else if (pdata.getProjectionType() == ProjectionType.LSP) {
                    //projection type
                    this.projectionTypeTextField.setText(pdata.getProjectorType().toString());
                    //fraction of delta
                    this.fractionDeltaTextField.setText(Float.toString(pdata.getFractionDelta()));
                    //number of iterations
                    this.numberIterationsTextField.setText(Integer.toString(pdata.getNumberIterations()));
                    //number control points
                    this.numberCPTextField.setText(Integer.toString(pdata.getNumberControlPoints()));
                    //number neighbors
                    this.numberNeighborsTextField.setText(Integer.toString(pdata.getNumberNeighborsConnection()));
                } else if (pdata.getProjectionType() == ProjectionType.MST) {
                } else if (pdata.getProjectionType() == ProjectionType.NJ) {
                } else if (pdata.getProjectionType() == ProjectionType.PCA) {
                } else if (pdata.getProjectionType() == ProjectionType.PROJCLUS) {
                    //projection type
                    this.projectionTypeTextField.setText(pdata.getProjectorType().toString());
                    //cluster factor
                    this.clusterFactorTextField.setText(Float.toString(pdata.getClusterFactor()));
                    //fraction of delta
                    this.fractionDeltaTextField.setText(Float.toString(pdata.getFractionDelta()));
                    //number of iterations
                    this.numberIterationsTextField.setText(Integer.toString(pdata.getNumberIterations()));
                } else if (pdata.getProjectionType() == ProjectionType.SAMMON) {
                    //fraction of delta
                    this.fractionDeltaTextField.setText(Float.toString(pdata.getFractionDelta()));
                    //number of iterations
                    this.numberIterationsTextField.setText(Integer.toString(pdata.getNumberIterations()));
                }

                //distance type
                if (pdata.getSourceType() != SourceType.DISTANCE_MATRIX) {
                    if (pdata.getDissimilarityType() != null) {
                        this.distanceTypeTextField.setText(pdata.getDissimilarityType().toString());
                    }

                    if (pdata.getDissimilarityType() == DissimilarityType.KOLMOGOROV) {
                        if (pdata.getCompressorType() == CompressorType.GZIP) {
                            this.distanceTypeTextField.setText("NCD(gzip)");
                        } else {
                            this.distanceTypeTextField.setText("NCD(zip)");
                        }
                    }
                }
            }
        }
    }

    private void clean() {
        this.clusterFactorTextField.setText("");
        this.dimenRedTechniqueTextField.setText("");
        this.distanceTypeTextField.setText("");
        this.fractionDeltaTextField.setText("");
        this.luhnLowerCutTextField.setText("");
        this.luhnUpperCutTextField.setText("");
        this.numberCPTextField.setText("");
        this.numberDimensionsTextField.setText("");
        this.numberGramsTextField.setText("");
        this.numberIterationsTextField.setText("");
        this.numberNeighborsTextField.setText("");
        this.numberObjectsTextField.setText("");
        this.projTechniqueTextField.setText("");
        this.projectionTypeTextField.setText("");
        this.resultingDimenLabelTextField.setText("");
        this.sourceTextField.setText("");
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel clusterFactorLabel;
    private javax.swing.JTextField clusterFactorTextField;
    private javax.swing.JPanel dataPanel;
    private javax.swing.JLabel dimenRedTechniqueLabel;
    private javax.swing.JTextField dimenRedTechniqueTextField;
    private javax.swing.JPanel dimenReductionPanel;
    private javax.swing.JLabel distanceTypeLabel;
    private javax.swing.JTextField distanceTypeTextField;
    private javax.swing.JPanel forcePanel;
    private javax.swing.JLabel fractionDeltaLabel;
    private javax.swing.JTextField fractionDeltaTextField;
    private javax.swing.JLabel luhnLowerCutLabel;
    private javax.swing.JTextField luhnLowerCutTextField;
    private javax.swing.JLabel luhnUpperCutLabel;
    private javax.swing.JTextField luhnUpperCutTextField;
    private javax.swing.JLabel numberCPLabel;
    private javax.swing.JTextField numberCPTextField;
    private javax.swing.JLabel numberDimensionsLabel;
    private javax.swing.JTextField numberDimensionsTextField;
    private javax.swing.JLabel numberGramsLabel;
    private javax.swing.JTextField numberGramsTextField;
    private javax.swing.JLabel numberIterationsLabel;
    private javax.swing.JTextField numberIterationsTextField;
    private javax.swing.JLabel numberNeighborsLabel;
    private javax.swing.JTextField numberNeighborsTextField;
    private javax.swing.JLabel numberObjectsLabel;
    private javax.swing.JTextField numberObjectsTextField;
    private javax.swing.JPanel preprocessingPanel;
    private javax.swing.JLabel projTechniqueLabel;
    private javax.swing.JTextField projTechniqueTextField;
    private javax.swing.JPanel projectionPanel;
    private javax.swing.JLabel projectionTypeLabel;
    private javax.swing.JTextField projectionTypeTextField;
    private javax.swing.JLabel resultingDimenLabel;
    private javax.swing.JTextField resultingDimenLabelTextField;
    private javax.swing.JLabel sourceLabel;
    private javax.swing.JTextField sourceTextField;
    // End of variables declaration//GEN-END:variables
}
