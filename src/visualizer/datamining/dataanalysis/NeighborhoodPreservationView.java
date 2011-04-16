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

package visualizer.datamining.dataanalysis;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


import visualizer.matrix.Matrix;
import visualizer.matrix.MatrixFactory;
import visualizer.projection.distance.DistanceMatrix;
import visualizer.projection.distance.Dissimilarity;
import visualizer.projection.distance.DissimilarityFactory;
import visualizer.projection.distance.DissimilarityType;
import visualizer.projection.distance.LightWeightDistanceMatrix;
import visualizer.util.KNN;
import visualizer.util.OpenDialog;
import visualizer.util.Pair;
import visualizer.util.filefilter.DATAFilter;
import visualizer.util.filefilter.DMATFilter;
import visualizer.util.filefilter.PRJandXMLFilter;

/**
 *
 * @author  Fernando Vieira Paulovich
 */
public class NeighborhoodPreservationView extends JDialog
{

    private DefaultTableModel projTableModel;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton addButton;
    private JPanel buttonPanel;
    private JPanel chooseDistanceTypePanel2;
    private JButton closeButton;
    private JPanel dataPanel;
    private JComboBox distanceComboBox;
    private JButton distanceMatrixButton;
    private JRadioButton distanceMatrixRadioButton;
    private JTextField distanceMatrixTextField;
    private JButton generateButton;
    private JPanel multiDataPanel;
    private JLabel nrNeighborsLabel;
    private JPanel nrNeighborsPanel;
    private JTextField nrNeighborsTextField;
    private JButton pointsButton;
    private JRadioButton pointsRadioButton;
    private JTextField pointsTextField;
    private JPanel projButtonPanel;
    private JPanel projPanel;
    private JScrollPane projScrollPane;
    private JTable projTable;
    private JButton removeButton;
    private ButtonGroup sourceButtonGroup;
    private JPanel sourcePanel;
    // End of variables declaration//GEN-END:variables
    
    /**
     * Creates new form NeighborhoodPreservationView
     */
    private NeighborhoodPreservationView(Frame parent) {
        super(parent);
        initComponents();
        initModels();

        for (DissimilarityType disstype : DissimilarityType.getTypes()) {
            if (disstype != DissimilarityType.KOLMOGOROV && disstype != DissimilarityType.NONE) {
                distanceComboBox.addItem(disstype);
            }
        }

        this.projTable.setModel(this.projTableModel);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        GridBagConstraints gridBagConstraints;

        sourceButtonGroup = new ButtonGroup();
        buttonPanel = new JPanel();
        generateButton = new JButton();
        closeButton = new JButton();
        dataPanel = new JPanel();
        multiDataPanel = new JPanel();
        chooseDistanceTypePanel2 = new JPanel();
        distanceComboBox = new JComboBox();
        sourcePanel = new JPanel();
        pointsRadioButton = new JRadioButton();
        distanceMatrixRadioButton = new JRadioButton();
        distanceMatrixTextField = new JTextField();
        pointsTextField = new JTextField();
        pointsButton = new JButton();
        distanceMatrixButton = new JButton();
        nrNeighborsPanel = new JPanel();
        nrNeighborsLabel = new JLabel();
        nrNeighborsTextField = new JTextField();
        projPanel = new JPanel();
        projScrollPane = new JScrollPane();
        projTable = new JTable();
        projButtonPanel = new JPanel();
        addButton = new JButton();
        removeButton = new JButton();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Neighborhood Preservation");

        generateButton.setText("Generate");
        generateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                generateButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(generateButton);

        closeButton.setText("Close");
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(closeButton);

        getContentPane().add(buttonPanel, BorderLayout.PAGE_END);

        dataPanel.setBorder(BorderFactory.createTitledBorder("Data"));
        dataPanel.setLayout(new BorderLayout(5, 5));

        multiDataPanel.setBorder(BorderFactory.createTitledBorder("Multidimensional Data"));
        multiDataPanel.setLayout(new GridBagLayout());

        chooseDistanceTypePanel2.setBorder(BorderFactory.createTitledBorder("Choose the Distance Type"));
        chooseDistanceTypePanel2.setLayout(new GridBagLayout());
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(3, 0, 3, 3);
        chooseDistanceTypePanel2.add(distanceComboBox, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        multiDataPanel.add(chooseDistanceTypePanel2, gridBagConstraints);

        sourcePanel.setBorder(BorderFactory.createTitledBorder("Source File"));
        sourcePanel.setLayout(new GridBagLayout());

        sourceButtonGroup.add(pointsRadioButton);
        pointsRadioButton.setText("Points File");
        pointsRadioButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        pointsRadioButton.setMargin(new Insets(0, 0, 0, 0));
        pointsRadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                pointsRadioButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        sourcePanel.add(pointsRadioButton, gridBagConstraints);

        sourceButtonGroup.add(distanceMatrixRadioButton);
        distanceMatrixRadioButton.setText("Distance File");
        distanceMatrixRadioButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        distanceMatrixRadioButton.setMargin(new Insets(0, 0, 0, 0));
        distanceMatrixRadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                distanceMatrixRadioButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        sourcePanel.add(distanceMatrixRadioButton, gridBagConstraints);

        distanceMatrixTextField.setColumns(35);
        distanceMatrixTextField.setEnabled(false);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        sourcePanel.add(distanceMatrixTextField, gridBagConstraints);

        pointsTextField.setColumns(35);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        sourcePanel.add(pointsTextField, gridBagConstraints);

        pointsButton.setText("Search...");
        pointsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                pointsButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        sourcePanel.add(pointsButton, gridBagConstraints);

        distanceMatrixButton.setText("Search...");
        distanceMatrixButton.setEnabled(false);
        distanceMatrixButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                distanceMatrixButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        sourcePanel.add(distanceMatrixButton, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        multiDataPanel.add(sourcePanel, gridBagConstraints);

        nrNeighborsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        nrNeighborsLabel.setText("Number of neighbors");
        nrNeighborsPanel.add(nrNeighborsLabel);

        nrNeighborsTextField.setColumns(10);
        nrNeighborsTextField.setText("30");
        nrNeighborsPanel.add(nrNeighborsTextField);

        multiDataPanel.add(nrNeighborsPanel, new GridBagConstraints());

        dataPanel.add(multiDataPanel, BorderLayout.SOUTH);

        projPanel.setBorder(BorderFactory.createTitledBorder("Projections"));
        projPanel.setLayout(new BorderLayout());

        projScrollPane.setPreferredSize(new Dimension(452, 250));
        projScrollPane.setViewportView(projTable);

        projPanel.add(projScrollPane, BorderLayout.CENTER);

        projButtonPanel.setLayout(new GridBagLayout());

        addButton.setText("Add");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        projButtonPanel.add(addButton, gridBagConstraints);

        removeButton.setText("Remove");
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                removeButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        projButtonPanel.add(removeButton, gridBagConstraints);

        projPanel.add(projButtonPanel, BorderLayout.LINE_END);

        dataPanel.add(projPanel, BorderLayout.CENTER);

        getContentPane().add(dataPanel, BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void generateButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_generateButtonActionPerformed
        if (this.projTable.getRowCount() > 0) {
            ArrayList<NeighborhoodPreservation.Serie> series = new ArrayList<NeighborhoodPreservation.Serie>();

            for (int i = 0; i < this.projTable.getRowCount(); i++) {
                String filename = (String) this.projTable.getValueAt(i, 1);

                if (filename.trim().length() > 0) {
                    String description = (String) this.projTable.getValueAt(i, 0);

                    NeighborhoodPreservation.Serie serie = new NeighborhoodPreservation.Serie(description, filename);

                    series.add(serie);
                }
            }

            if (this.pointsRadioButton.isSelected()) {
                if (this.pointsTextField.getText().trim().length() > 0) {
                    try {
                        Matrix matrix = MatrixFactory.getInstance(this.pointsTextField.getText());

                        DissimilarityType mtype = (DissimilarityType) this.distanceComboBox.getSelectedItem();
                        Dissimilarity diss = DissimilarityFactory.getInstance(mtype);

                        //DistanceMatrix dmat = new DistanceMatrix(matrix, diss);
                        LightWeightDistanceMatrix dmat = new LightWeightDistanceMatrix(matrix, diss);

                        int maxneigh = Integer.parseInt(this.nrNeighborsTextField.getText());
                        KNN knndata = new KNN(maxneigh);
                        Pair[][] ndata = knndata.execute(dmat);

                        NeighborhoodPreservation.getInstance(this).display(ndata, series, maxneigh);

                    } catch (IOException ex) {
                        Logger.getLogger(NeighborhoodPreservationView.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "A points file must be selected.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else if (this.distanceMatrixRadioButton.isSelected()) {
                if (this.distanceMatrixTextField.getText().trim().length() > 0) {
                    try {
                        DistanceMatrix dmat = new DistanceMatrix(this.distanceMatrixTextField.getText());

                        int maxneigh = Integer.parseInt(this.nrNeighborsTextField.getText());
                        KNN knndata = new KNN(maxneigh);
                        Pair[][] ndata = knndata.execute(dmat);

                        NeighborhoodPreservation.getInstance(this).display(ndata, series, maxneigh);

                    } catch (IOException ex) {
                        Logger.getLogger(NeighborhoodPreservationView.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "A distance matrix file must be selected.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "At least one projection must be added.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_generateButtonActionPerformed

    private void closeButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_closeButtonActionPerformed

    private void addButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        int result = OpenDialog.showOpenDialog(new PRJandXMLFilter(), this);

        if (result == JFileChooser.APPROVE_OPTION) {
            String filename = OpenDialog.getFilename();
            String description = filename.substring(filename.lastIndexOf("\\") + 1, filename.lastIndexOf("."));
            projTableModel.addRow(new String[]{description, filename});
        }
    }//GEN-LAST:event_addButtonActionPerformed

    private void removeButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_removeButtonActionPerformed
        int index = this.projTable.getSelectedRow();

        if (index > -1) {
            this.projTableModel.removeRow(index);
        }
    }//GEN-LAST:event_removeButtonActionPerformed

    private void pointsRadioButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_pointsRadioButtonActionPerformed
    	this.pointsButton.setEnabled(true);
        this.pointsTextField.setEnabled(true);

        this.distanceComboBox.setEnabled(false);
        this.distanceMatrixButton.setEnabled(false);
        this.distanceMatrixTextField.setEnabled(false);
    }//GEN-LAST:event_pointsRadioButtonActionPerformed

    private void distanceMatrixRadioButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_distanceMatrixRadioButtonActionPerformed
        this.distanceComboBox.setEnabled(true);
        this.distanceMatrixButton.setEnabled(true);
        this.distanceMatrixTextField.setEnabled(true);

        this.pointsButton.setEnabled(false);
        this.pointsTextField.setEnabled(false);
    }//GEN-LAST:event_distanceMatrixRadioButtonActionPerformed

    private void pointsButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_pointsButtonActionPerformed
        int result = OpenDialog.showOpenDialog(new DATAFilter(), this);

        if (result == JFileChooser.APPROVE_OPTION) {
            String filename = OpenDialog.getFilename();
            this.pointsTextField.setText(filename);
        }
    }//GEN-LAST:event_pointsButtonActionPerformed

    private void distanceMatrixButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_distanceMatrixButtonActionPerformed
        int result = OpenDialog.showOpenDialog(new DMATFilter(), this);

        if (result == JFileChooser.APPROVE_OPTION) {
            String filename = OpenDialog.getFilename();
            this.distanceMatrixTextField.setText(filename);
        }
    }//GEN-LAST:event_distanceMatrixButtonActionPerformed

    public static NeighborhoodPreservationView getInstance(JFrame parent) {
        return new NeighborhoodPreservationView(parent);
    }

    public void display() {
        this.pack();
        this.setLocationRelativeTo(this.getParent());
        this.setVisible(true);
    }

    private void initModels() {
        String[] titles = new String[]{"Description", "File name"};
        this.projTableModel = new DefaultTableModel(null, titles);
    }
}
