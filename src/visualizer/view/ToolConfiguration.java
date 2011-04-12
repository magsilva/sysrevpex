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
 * Contributor(s): Roberto Pinho <robertopinho@yahoo.com.br>, 
 *                 Rosane Minghim <rminghim@icmc.usp.br>
 *
 * You should have received a copy of the GNU General Public License along 
 * with PEx. If not, see <http://www.gnu.org/licenses/>.
 *
 * ***** END LICENSE BLOCK ***** */

package visualizer.view;

import visualizer.corpus.BaseCorpus;
import visualizer.corpus.Encoding;
import visualizer.graph.Edge;
import visualizer.graph.Graph;
import visualizer.graph.Vertex;
import visualizer.topic.CovarianceTopicSettings;
import visualizer.topic.TopicData.TopicType;
import visualizer.topic.TopicRuleTFSettings;

/**
 *
 * @author  Fernando Vieira Paulovich
 */
public class ToolConfiguration extends javax.swing.JDialog {

    private static final long serialVersionUID = 1L;
    /** Creates new form ToolConfiguration */
    private ToolConfiguration(javax.swing.JFrame parent) {
        super(parent);
        initComponents();
        this.setModal(true);

        this.encodingsComboBox.removeAllItems();
        for (Encoding e : Encoding.getEncodings()) {
            this.encodingsComboBox.addItem(e);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        graphicQualityGroup = new javax.swing.ButtonGroup();
        labelGroup = new javax.swing.ButtonGroup();
        drawbuttonGroup = new javax.swing.ButtonGroup();
        adjustsPanel = new javax.swing.JPanel();
        vertexRaySlider = new javax.swing.JSlider();
        labelPanel = new javax.swing.JPanel();
        covarianceRadioButton = new javax.swing.JRadioButton();
        settingsRulesButton = new javax.swing.JButton();
        rulesRadioButton = new javax.swing.JRadioButton();
        settingsCovarianceButton = new javax.swing.JButton();
        encodingPanel = new javax.swing.JPanel();
        encodingsComboBox = new javax.swing.JComboBox();
        paremetersPanel = new javax.swing.JPanel();
        labelfontSizeLabel = new javax.swing.JLabel();
        labelfontSizeComboBox = new javax.swing.JComboBox(new String[]{"8","9","10","11","12", "13","14","16","18","20"});
        backGroundButton = new javax.swing.JButton();
        qualityPanel = new javax.swing.JPanel();
        highRadioButton = new javax.swing.JRadioButton();
        poorRadioButton = new javax.swing.JRadioButton();
        drawPanel = new javax.swing.JPanel();
        circlesRadioButton = new javax.swing.JRadioButton();
        dotsRadioButton = new javax.swing.JRadioButton();
        titlefontSizeLabel = new javax.swing.JLabel();
        titlefontSizeComboBox = new javax.swing.JComboBox(new String[]{"8","9","10","11","12", "13","14","16","18","20"});
        alphaSlider = new javax.swing.JSlider();
        generalPanel = new javax.swing.JPanel();
        shownonvalidCheckBox = new javax.swing.JCheckBox();
        showVertexLabel = new javax.swing.JCheckBox();
        showEdgesCheckBox = new javax.swing.JCheckBox();
        showEdgesLabelsCheckBox = new javax.swing.JCheckBox();
        neighborhoodDepthLabel = new javax.swing.JLabel();
        neighborhoodDepthComboBox = new javax.swing.JComboBox();
        buttonPanel = new javax.swing.JPanel();
        closeButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Tools Properties");

        adjustsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Adjustments "));
        adjustsPanel.setLayout(new java.awt.GridBagLayout());

        vertexRaySlider.setMajorTickSpacing(10);
        vertexRaySlider.setMaximum(30);
        vertexRaySlider.setMinorTickSpacing(1);
        vertexRaySlider.setPaintLabels(true);
        vertexRaySlider.setPaintTicks(true);
        vertexRaySlider.setToolTipText("Vertex ray");
        vertexRaySlider.setValue(10);
        vertexRaySlider.setBorder(javax.swing.BorderFactory.createTitledBorder("Vertex Ray"));
        vertexRaySlider.setPreferredSize(new java.awt.Dimension(200, 65));
        vertexRaySlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                vertexRaySliderStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        adjustsPanel.add(vertexRaySlider, gridBagConstraints);

        labelPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Labels"));
        labelPanel.setLayout(new java.awt.GridBagLayout());

        labelGroup.add(covarianceRadioButton);
        covarianceRadioButton.setMnemonic('H');
        covarianceRadioButton.setText("Covariance");
        covarianceRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                covarianceRadioButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        labelPanel.add(covarianceRadioButton, gridBagConstraints);

        settingsRulesButton.setText("Settings");
        settingsRulesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingsRulesButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        labelPanel.add(settingsRulesButton, gridBagConstraints);

        labelGroup.add(rulesRadioButton);
        rulesRadioButton.setMnemonic('P');
        rulesRadioButton.setText("Rules");
        rulesRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rulesRadioButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        labelPanel.add(rulesRadioButton, gridBagConstraints);

        settingsCovarianceButton.setText("Settings");
        settingsCovarianceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingsCovarianceButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        labelPanel.add(settingsCovarianceButton, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        adjustsPanel.add(labelPanel, gridBagConstraints);

        encodingPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Character Encoding"));
        encodingPanel.setLayout(new java.awt.BorderLayout());

        encodingsComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                encodingsComboBoxActionPerformed(evt);
            }
        });
        encodingPanel.add(encodingsComboBox, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        adjustsPanel.add(encodingPanel, gridBagConstraints);

        paremetersPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Graphics Parameters"));
        paremetersPanel.setLayout(new java.awt.GridBagLayout());

        labelfontSizeLabel.setText("Labels's font size");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        paremetersPanel.add(labelfontSizeLabel, gridBagConstraints);

        labelfontSizeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                labelfontSizeComboBoxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        paremetersPanel.add(labelfontSizeComboBox, gridBagConstraints);

        backGroundButton.setText("BackGround Color");
        backGroundButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backGroundButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        paremetersPanel.add(backGroundButton, gridBagConstraints);

        qualityPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Render Quality"));
        qualityPanel.setLayout(new java.awt.GridBagLayout());

        graphicQualityGroup.add(highRadioButton);
        highRadioButton.setSelected(true);
        highRadioButton.setText("High quality (slow)");
        highRadioButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        highRadioButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        highRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                highRadioButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        qualityPanel.add(highRadioButton, gridBagConstraints);

        graphicQualityGroup.add(poorRadioButton);
        poorRadioButton.setText("Poor quality (fast)");
        poorRadioButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        poorRadioButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        poorRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                poorRadioButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        qualityPanel.add(poorRadioButton, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        paremetersPanel.add(qualityPanel, gridBagConstraints);

        drawPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Draw"));
        drawPanel.setLayout(new java.awt.GridBagLayout());

        drawbuttonGroup.add(circlesRadioButton);
        circlesRadioButton.setSelected(true);
        circlesRadioButton.setText("circles");
        circlesRadioButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        circlesRadioButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        circlesRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                circlesRadioButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        drawPanel.add(circlesRadioButton, gridBagConstraints);

        drawbuttonGroup.add(dotsRadioButton);
        dotsRadioButton.setText("points");
        dotsRadioButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        dotsRadioButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        dotsRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dotsRadioButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        drawPanel.add(dotsRadioButton, gridBagConstraints);

        paremetersPanel.add(drawPanel, new java.awt.GridBagConstraints());

        titlefontSizeLabel.setText("Title's font size");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        paremetersPanel.add(titlefontSizeLabel, gridBagConstraints);

        titlefontSizeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                titlefontSizeComboBoxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        paremetersPanel.add(titlefontSizeComboBox, gridBagConstraints);

        alphaSlider.setPaintLabels(true);
        alphaSlider.setPaintTicks(true);
        alphaSlider.setSnapToTicks(true);
        alphaSlider.setBorder(javax.swing.BorderFactory.createTitledBorder("Alpha"));
        alphaSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                alphaSliderStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        paremetersPanel.add(alphaSlider, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        adjustsPanel.add(paremetersPanel, gridBagConstraints);

        generalPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("General"));
        generalPanel.setLayout(new java.awt.GridBagLayout());

        shownonvalidCheckBox.setText("Show intermediate vertex");
        shownonvalidCheckBox.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        shownonvalidCheckBox.setMargin(new java.awt.Insets(0, 0, 0, 0));
        shownonvalidCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shownonvalidCheckBoxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        generalPanel.add(shownonvalidCheckBox, gridBagConstraints);

        showVertexLabel.setText("Show vertex label");
        showVertexLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        showVertexLabel.setMargin(new java.awt.Insets(0, 0, 0, 0));
        showVertexLabel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showVertexLabelActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        generalPanel.add(showVertexLabel, gridBagConstraints);

        showEdgesCheckBox.setText("Show edges");
        showEdgesCheckBox.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        showEdgesCheckBox.setMargin(new java.awt.Insets(0, 0, 0, 0));
        showEdgesCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showEdgesCheckBoxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        generalPanel.add(showEdgesCheckBox, gridBagConstraints);

        showEdgesLabelsCheckBox.setText("Show edge's label");
        showEdgesLabelsCheckBox.setBorder(null);
        showEdgesLabelsCheckBox.setMargin(new java.awt.Insets(0, 0, 0, 0));
        showEdgesLabelsCheckBox.setMaximumSize(new java.awt.Dimension(141, 15));
        showEdgesLabelsCheckBox.setMinimumSize(new java.awt.Dimension(141, 15));
        showEdgesLabelsCheckBox.setPreferredSize(new java.awt.Dimension(141, 15));
        showEdgesLabelsCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showEdgesLabelsCheckBoxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        generalPanel.add(showEdgesLabelsCheckBox, gridBagConstraints);

        neighborhoodDepthLabel.setText("Neighborhood depth");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        generalPanel.add(neighborhoodDepthLabel, gridBagConstraints);

        neighborhoodDepthComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
        neighborhoodDepthComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                neighborhoodDepthComboBoxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        generalPanel.add(neighborhoodDepthComboBox, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        adjustsPanel.add(generalPanel, gridBagConstraints);

        getContentPane().add(adjustsPanel, java.awt.BorderLayout.CENTER);

        closeButton.setText("Close");
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(closeButton);

        getContentPane().add(buttonPanel, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void showEdgesCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showEdgesCheckBoxActionPerformed
        if (this.graph != null) {
            this.graph.setEdgeVisible(this.showEdgesCheckBox.isSelected());

            if (this.gv != null) {
                this.gv.updateImage();
            }
        }
    }//GEN-LAST:event_showEdgesCheckBoxActionPerformed

    private void titlefontSizeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_titlefontSizeComboBoxActionPerformed
        Vertex.setFont(new java.awt.Font("Verdana", java.awt.Font.BOLD,
                Integer.parseInt((String) titlefontSizeComboBox.getSelectedItem())));
        if (this.gv != null) {
            this.gv.updateImage();
        }
    }//GEN-LAST:event_titlefontSizeComboBoxActionPerformed

    private void dotsRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dotsRadioButtonActionPerformed
        Vertex.setDrawAsCircles(false);

        this.poorRadioButton.setSelected(true);
        this.highRadioButton.setSelected(false);

        if (this.gv != null) {
            this.gv.setHighQualityRender(false);
            this.gv.updateImage();
        }
    }//GEN-LAST:event_dotsRadioButtonActionPerformed

    private void circlesRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_circlesRadioButtonActionPerformed
        Vertex.setDrawAsCircles(true);

        if (this.gv != null) {
            this.gv.updateImage();
        }
    }//GEN-LAST:event_circlesRadioButtonActionPerformed

    private void poorRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_poorRadioButtonActionPerformed
        if (this.gv != null) {
            this.gv.setHighQualityRender(false);
            this.gv.updateImage();
        }
    }//GEN-LAST:event_poorRadioButtonActionPerformed

    private void highRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_highRadioButtonActionPerformed
        if (this.gv != null) {
            this.gv.setHighQualityRender(true);
            this.gv.updateImage();
        }
    }//GEN-LAST:event_highRadioButtonActionPerformed

    private void shownonvalidCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shownonvalidCheckBoxActionPerformed
        Vertex.setShowNonValid(this.shownonvalidCheckBox.isSelected());
        if (this.gv != null) {
            this.gv.updateImage();
        }
    }//GEN-LAST:event_shownonvalidCheckBoxActionPerformed

    private void settingsRulesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settingsRulesButtonActionPerformed
        if (this.graph != null) {
            TopicRuleTFSettings.getInstance(this).display(this.graph);
        }
}//GEN-LAST:event_settingsRulesButtonActionPerformed

    private void rulesRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rulesRadioButtonActionPerformed
        if (this.graph != null) {
            this.graph.getTopicData().setTopicType(TopicType.RULE);
        }
    }//GEN-LAST:event_rulesRadioButtonActionPerformed

    private void covarianceRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_covarianceRadioButtonActionPerformed
        if (this.graph != null) {
            this.graph.getTopicData().setTopicType(TopicType.COVARIANCE);
        }
    }//GEN-LAST:event_covarianceRadioButtonActionPerformed

    private void showVertexLabelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showVertexLabelActionPerformed
        if (this.gv != null) {
            this.gv.setVertexLabelVisible(this.showVertexLabel.isSelected());
        }
    }//GEN-LAST:event_showVertexLabelActionPerformed

    private void labelfontSizeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_labelfontSizeComboBoxActionPerformed
        if (this.gv != null) {
            this.gv.setViewerFont(new java.awt.Font("Verdana", java.awt.Font.BOLD,
                    Integer.parseInt((String) labelfontSizeComboBox.getSelectedItem())));
        }
    }//GEN-LAST:event_labelfontSizeComboBoxActionPerformed

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        this.graph = null;
        this.gv = null;
        this.setVisible(false);
    }//GEN-LAST:event_closeButtonActionPerformed

    private void backGroundButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backGroundButtonActionPerformed
        if (this.gv != null) {
            java.awt.Color color = javax.swing.JColorChooser.showDialog(this,
                    "Choose the Backgroud Color", java.awt.Color.BLACK);
            this.gv.setViewerBackground(color);
            this.gv.updateImage();
        }
    }//GEN-LAST:event_backGroundButtonActionPerformed

    private void vertexRaySliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_vertexRaySliderStateChanged
        javax.swing.JSlider slider = (javax.swing.JSlider) evt.getSource();
        Vertex.setRayBase(slider.getValue());
        if (this.gv != null) {
            this.gv.updateImage();
        }
    }//GEN-LAST:event_vertexRaySliderStateChanged

    private void showEdgesLabelsCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showEdgesLabelsCheckBoxActionPerformed
        Edge.setShowLength(this.showEdgesLabelsCheckBox.isSelected());
        if (this.gv != null) {
            this.gv.updateImage();
        }
}//GEN-LAST:event_showEdgesLabelsCheckBoxActionPerformed

    private void encodingsComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_encodingsComboBoxActionPerformed
        Encoding encoding = (Encoding) encodingsComboBox.getSelectedItem();
        if (encoding != null) {
            BaseCorpus.setEncoding(encoding);
        }
    }//GEN-LAST:event_encodingsComboBoxActionPerformed

    private void neighborhoodDepthComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_neighborhoodDepthComboBoxActionPerformed
        if (this.gv != null) {
            this.gv.setNeighborhoodDepth(this.neighborhoodDepthComboBox.getSelectedIndex() + 1);
        }
}//GEN-LAST:event_neighborhoodDepthComboBoxActionPerformed

private void settingsCovarianceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settingsCovarianceButtonActionPerformed
    if (this.graph != null) {
        CovarianceTopicSettings.getInstance(this).display(this.graph);
    }
}//GEN-LAST:event_settingsCovarianceButtonActionPerformed

private void alphaSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_alphaSliderStateChanged
    javax.swing.JSlider slider = (javax.swing.JSlider) evt.getSource();
    Vertex.setAlpha((float) Math.pow(slider.getValue() / 100.0f, 2));

    if (this.gv != null) {
        this.gv.updateImage();
    }
}//GEN-LAST:event_alphaSliderStateChanged

    public static ToolConfiguration getInstance(javax.swing.JFrame parent) {
        if (instance == null || instance.getParent() != parent) {
            instance = new ToolConfiguration(parent);
        }
        return instance;
    }

    public void display(Viewer gv) {
        if (gv != null) {
            this.gv = gv;
            this.graph = gv.getGraph();

            this.labelfontSizeComboBox.setSelectedItem(Integer.toString(gv.getFont().getSize()));
            this.showVertexLabel.setSelected(gv.isVertexLabelVisible());

            if (gv.isHighQualityRender()) {
                this.highRadioButton.setSelected(true);
                this.poorRadioButton.setSelected(false);
            } else {
                this.highRadioButton.setSelected(false);
                this.poorRadioButton.setSelected(true);
            }

            this.covarianceRadioButton.setSelected(false);
            this.rulesRadioButton.setSelected(false);

            if (this.graph != null && this.graph.getTopicData().getTopicType() == TopicType.RULE) {
                rulesRadioButton.setSelected(true);
            } else {
                covarianceRadioButton.setSelected(true);
            }

            if (graph != null) {
                this.showEdgesCheckBox.setSelected(this.graph.isEdgeVisible());
            }

            this.vertexRaySlider.setValue(Vertex.getRayBase());

            this.neighborhoodDepthComboBox.setSelectedIndex(gv.getNeighborhoodDepth() - 1);
        } else {
            this.gv = null;
            this.graph = null;
        }

        this.titlefontSizeComboBox.setSelectedItem(Integer.toString(Vertex.getFont().getSize()));
        this.shownonvalidCheckBox.setSelected(Vertex.isShowNonValid());
        this.showEdgesLabelsCheckBox.setSelected(Edge.isShowLength());

        if (Vertex.isDrawAsCircles()) {
            this.circlesRadioButton.setSelected(true);
            this.dotsRadioButton.setSelected(false);
        } else {
            this.circlesRadioButton.setSelected(false);
            this.dotsRadioButton.setSelected(true);
        }

        this.alphaSlider.setValue((int) (Math.sqrt(Vertex.getAlpha()) * 100));

        this.pack();
        this.setLocationRelativeTo(this.getParent());
        this.setVisible(true);
    }

    private Graph graph;
    private Viewer gv;
    private static ToolConfiguration instance;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel adjustsPanel;
    private javax.swing.JSlider alphaSlider;
    private javax.swing.JButton backGroundButton;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JRadioButton circlesRadioButton;
    private javax.swing.JButton closeButton;
    private javax.swing.JRadioButton covarianceRadioButton;
    private javax.swing.JRadioButton dotsRadioButton;
    private javax.swing.JPanel drawPanel;
    private javax.swing.ButtonGroup drawbuttonGroup;
    private javax.swing.JPanel encodingPanel;
    private javax.swing.JComboBox encodingsComboBox;
    private javax.swing.JPanel generalPanel;
    private javax.swing.ButtonGroup graphicQualityGroup;
    private javax.swing.JRadioButton highRadioButton;
    private javax.swing.ButtonGroup labelGroup;
    private javax.swing.JPanel labelPanel;
    private javax.swing.JComboBox labelfontSizeComboBox;
    private javax.swing.JLabel labelfontSizeLabel;
    private javax.swing.JComboBox neighborhoodDepthComboBox;
    private javax.swing.JLabel neighborhoodDepthLabel;
    private javax.swing.JPanel paremetersPanel;
    private javax.swing.JRadioButton poorRadioButton;
    private javax.swing.JPanel qualityPanel;
    private javax.swing.JRadioButton rulesRadioButton;
    private javax.swing.JButton settingsCovarianceButton;
    private javax.swing.JButton settingsRulesButton;
    private javax.swing.JCheckBox showEdgesCheckBox;
    private javax.swing.JCheckBox showEdgesLabelsCheckBox;
    private javax.swing.JCheckBox showVertexLabel;
    private javax.swing.JCheckBox shownonvalidCheckBox;
    private javax.swing.JComboBox titlefontSizeComboBox;
    private javax.swing.JLabel titlefontSizeLabel;
    private javax.swing.JSlider vertexRaySlider;
    // End of variables declaration//GEN-END:variables
}
