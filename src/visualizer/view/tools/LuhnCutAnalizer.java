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

package visualizer.view.tools;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;
import visualizer.corpus.CorpusFactory;
import visualizer.corpus.Corpus;
import visualizer.textprocessing.BasicPreProcessor;
import visualizer.textprocessing.Ngram;
import visualizer.textprocessing.PipelinePreprocessor;
import visualizer.textprocessing.PreProcessorFactory;
import visualizer.projection.ProjectionData;
import visualizer.textprocessing.stemmer.Stemmer;
import visualizer.textprocessing.stemmer.StemmerFactory;
import visualizer.textprocessing.stemmer.StemmerType;
import visualizer.textprocessing.stopword.SetStopword;
import visualizer.textprocessing.stopword.StopWord;
import visualizer.util.SaveDialog;
import visualizer.util.filefilter.STARTFilter;
import visualizer.util.filefilter.STOPFilter;
import visualizer.topic.TopicData;

/**
 *
 * @author  Fernando Vieira Paulovich
 */
public class LuhnCutAnalizer extends javax.swing.JDialog
{

    private ProjectionData pdata;
    private TopicData tdata;
    private static LuhnCutAnalizer instance;
    private DefaultTableModel tableModel;
    private Corpus corpus;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton analyzeButton;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JButton changeStartwordsButton;
    private javax.swing.JButton changeStopwordsButton;
    protected javax.swing.JButton closeButton;
    private javax.swing.JPanel cutConfigurationPanel;
    private javax.swing.JButton exportStartwordsButton;
    private javax.swing.JButton exportStopwordsButton;
    private javax.swing.JButton loerCutMinusButton;
    private javax.swing.JPanel lowerCutButtonPanel;
    private javax.swing.JLabel lowerCutLabel;
    private javax.swing.JPanel lowerCutPanel;
    private javax.swing.JButton lowerCutPlusButton;
    protected javax.swing.JSlider lowerCutSlider;
    protected javax.swing.JTextField lowerCutTextField;
    private javax.swing.JPanel luhnPanel;
    private javax.swing.JLabel ngramsLabel;
    private javax.swing.JPanel ngramsPanel;
    private javax.swing.JScrollPane ngramsScrollPane;
    protected javax.swing.JTable ngramsTable;
    protected javax.swing.JTextField ngramsTextField;
    private javax.swing.JPanel numberGramsPanel;
    private javax.swing.JPanel upperCutButtonPanel;
    private javax.swing.JLabel upperCutLabel;
    private javax.swing.JButton upperCutMinusButton;
    private javax.swing.JPanel upperCutPanel;
    private javax.swing.JButton upperCutPlusButton;
    protected javax.swing.JSlider upperCutSlider;
    protected javax.swing.JTextField upperCutTextField;
    protected javax.swing.JPanel zipfCurvePanel;
    private javax.swing.JPanel zipfPanel;
    // End of variables declaration//GEN-END:variables

    private boolean useStartword = false;
    
	/**
	 * Creates new form LuhnCutAnalizer
	 * 
     * @param parent 
     */
    protected LuhnCutAnalizer(javax.swing.JDialog parent) {
        super(parent);
        initModels();
        initComponents();

        this.setPreferredSize(new Dimension(700, 550));
        this.setSize(700, 550);
    }

    protected LuhnCutAnalizer(javax.swing.JFrame parent) {
        super(parent);
        initModels();
        initComponents();

        this.setPreferredSize(new Dimension(700, 550));
        this.setSize(700, 550);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        luhnPanel = new javax.swing.JPanel();
        zipfPanel = new javax.swing.JPanel();
        zipfCurvePanel = new ZipfCurve();
        cutConfigurationPanel = new javax.swing.JPanel();
        upperCutPanel = new javax.swing.JPanel();
        upperCutSlider = new javax.swing.JSlider();
        upperCutLabel = new javax.swing.JLabel();
        upperCutButtonPanel = new javax.swing.JPanel();
        upperCutTextField = new javax.swing.JTextField();
        upperCutPlusButton = new javax.swing.JButton();
        upperCutMinusButton = new javax.swing.JButton();
        lowerCutPanel = new javax.swing.JPanel();
        lowerCutSlider = new javax.swing.JSlider();
        lowerCutButtonPanel = new javax.swing.JPanel();
        lowerCutTextField = new javax.swing.JTextField();
        lowerCutPlusButton = new javax.swing.JButton();
        loerCutMinusButton = new javax.swing.JButton();
        lowerCutLabel = new javax.swing.JLabel();
        ngramsPanel = new javax.swing.JPanel();
        numberGramsPanel = new javax.swing.JPanel();
        ngramsLabel = new javax.swing.JLabel();
        ngramsTextField = new javax.swing.JTextField();
        ngramsScrollPane = new javax.swing.JScrollPane();
        ngramsTable = new javax.swing.JTable();
        changeStopwordsButton = new javax.swing.JButton();
        changeStartwordsButton = new javax.swing.JButton();
        exportStopwordsButton = new javax.swing.JButton();
        exportStartwordsButton = new javax.swing.JButton();
        buttonPanel = new javax.swing.JPanel();
        analyzeButton = new javax.swing.JButton();
        closeButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Luhn's cut-off Analysis");
        setModal(true);

        luhnPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Data"));
        luhnPanel.setLayout(new java.awt.BorderLayout());

        zipfPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Zipf's Curve"));
        zipfPanel.setLayout(new java.awt.BorderLayout());
        zipfPanel.add(zipfCurvePanel, java.awt.BorderLayout.CENTER);

        luhnPanel.add(zipfPanel, java.awt.BorderLayout.CENTER);

        cutConfigurationPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Cut-off Configuration"));
        cutConfigurationPanel.setLayout(new java.awt.BorderLayout());

        upperCutPanel.setLayout(new java.awt.BorderLayout());

        upperCutSlider.setMajorTickSpacing(1);
        upperCutSlider.setSnapToTicks(true);
        upperCutSlider.setValue(0);
        upperCutSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                upperCutSliderStateChanged(evt);
            }
        });
        upperCutPanel.add(upperCutSlider, java.awt.BorderLayout.CENTER);

        upperCutLabel.setText("Upper Cut");
        upperCutPanel.add(upperCutLabel, java.awt.BorderLayout.WEST);

        upperCutButtonPanel.setLayout(new java.awt.GridBagLayout());

        upperCutTextField.setColumns(5);
        upperCutTextField.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        upperCutButtonPanel.add(upperCutTextField, gridBagConstraints);

        upperCutPlusButton.setText("+");
        upperCutPlusButton.setPreferredSize(new java.awt.Dimension(43, 15));
        upperCutPlusButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                upperCutPlusButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        upperCutButtonPanel.add(upperCutPlusButton, gridBagConstraints);

        upperCutMinusButton.setText("-");
        upperCutMinusButton.setPreferredSize(new java.awt.Dimension(39, 15));
        upperCutMinusButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                upperCutMinusButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        upperCutButtonPanel.add(upperCutMinusButton, gridBagConstraints);

        upperCutPanel.add(upperCutButtonPanel, java.awt.BorderLayout.EAST);

        cutConfigurationPanel.add(upperCutPanel, java.awt.BorderLayout.SOUTH);

        lowerCutPanel.setLayout(new java.awt.BorderLayout());

        lowerCutSlider.setMajorTickSpacing(1);
        lowerCutSlider.setSnapToTicks(true);
        lowerCutSlider.setValue(0);
        lowerCutSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                lowerCutSliderStateChanged(evt);
            }
        });
        lowerCutPanel.add(lowerCutSlider, java.awt.BorderLayout.CENTER);

        lowerCutButtonPanel.setLayout(new java.awt.GridBagLayout());

        lowerCutTextField.setColumns(5);
        lowerCutTextField.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        lowerCutButtonPanel.add(lowerCutTextField, gridBagConstraints);

        lowerCutPlusButton.setText("+");
        lowerCutPlusButton.setPreferredSize(new java.awt.Dimension(43, 15));
        lowerCutPlusButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lowerCutPlusButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        lowerCutButtonPanel.add(lowerCutPlusButton, gridBagConstraints);

        loerCutMinusButton.setText("-");
        loerCutMinusButton.setPreferredSize(new java.awt.Dimension(39, 15));
        loerCutMinusButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loerCutMinusButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        lowerCutButtonPanel.add(loerCutMinusButton, gridBagConstraints);

        lowerCutPanel.add(lowerCutButtonPanel, java.awt.BorderLayout.EAST);

        lowerCutLabel.setText("Lower Cut");
        lowerCutPanel.add(lowerCutLabel, java.awt.BorderLayout.WEST);

        cutConfigurationPanel.add(lowerCutPanel, java.awt.BorderLayout.NORTH);

        luhnPanel.add(cutConfigurationPanel, java.awt.BorderLayout.SOUTH);

        ngramsPanel.setLayout(new java.awt.GridBagLayout());

        ngramsLabel.setText("Number ngrams");
        numberGramsPanel.add(ngramsLabel);

        ngramsTextField.setColumns(5);
        ngramsTextField.setEditable(false);
        numberGramsPanel.add(ngramsTextField);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 2;
        ngramsPanel.add(numberGramsPanel, gridBagConstraints);

        ngramsScrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder("Ngrams and Frequency"));
        ngramsScrollPane.setPreferredSize(new java.awt.Dimension(230, 275));

        ngramsTable.setModel(this.tableModel);
        ngramsTable.setEnabled(false);
        ngramsScrollPane.setViewportView(ngramsTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        ngramsPanel.add(ngramsScrollPane, gridBagConstraints);

        changeStopwordsButton.setText("Change Stopwords");
        changeStopwordsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeStopwordsButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        ngramsPanel.add(changeStopwordsButton, gridBagConstraints);

        changeStartwordsButton.setText("Change Startwords");
        changeStartwordsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeStartwordsButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        ngramsPanel.add(changeStartwordsButton, gridBagConstraints);

        exportStopwordsButton.setText("Export Stopwords");
        exportStopwordsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportStopwordsButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        ngramsPanel.add(exportStopwordsButton, gridBagConstraints);

        exportStartwordsButton.setText("Export Startwords");
        exportStartwordsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportStartwordsButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        ngramsPanel.add(exportStartwordsButton, gridBagConstraints);

        luhnPanel.add(ngramsPanel, java.awt.BorderLayout.EAST);

        getContentPane().add(luhnPanel, java.awt.BorderLayout.CENTER);

        analyzeButton.setText("Analyze");
        analyzeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                analyzeButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(analyzeButton);

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
    private void exportStartwordsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportStartwordsButtonActionPerformed
        try {
            int result = SaveDialog.showSaveDialog(new STARTFilter(), this, "startwords.stw");

            if (result == JFileChooser.APPROVE_OPTION) {
                String filename = SaveDialog.getFilename();
                this.exportStartWords(filename);
            }

        } catch (IOException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_exportStartwordsButtonActionPerformed

    private void exportStopwordsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportStopwordsButtonActionPerformed
        try {
            int result = SaveDialog.showSaveDialog(new STOPFilter(), this, "stopwords.spw");

            if (result == JFileChooser.APPROVE_OPTION) {
                String filename = SaveDialog.getFilename();
                this.exportStopWords(filename);
            }

        } catch (IOException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_exportStopwordsButtonActionPerformed

    private void changeStartwordsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeStartwordsButtonActionPerformed
        WordsManager.getInstance(this, false).display();
    }//GEN-LAST:event_changeStartwordsButtonActionPerformed

    private void upperCutMinusButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_upperCutMinusButtonActionPerformed
        int upperCut = this.upperCutSlider.getValue();
        this.upperCutSlider.setValue(upperCut + 1);
    }//GEN-LAST:event_upperCutMinusButtonActionPerformed

    private void upperCutPlusButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_upperCutPlusButtonActionPerformed
        int upperCut = this.upperCutSlider.getValue();
        this.upperCutSlider.setValue(upperCut - 1);
    }//GEN-LAST:event_upperCutPlusButtonActionPerformed

    private void loerCutMinusButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loerCutMinusButtonActionPerformed
        int lowerCut = this.lowerCutSlider.getValue();
        this.lowerCutSlider.setValue(lowerCut + 1);
    }//GEN-LAST:event_loerCutMinusButtonActionPerformed

    private void lowerCutPlusButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lowerCutPlusButtonActionPerformed
        int lowerCut = this.lowerCutSlider.getValue();
        this.lowerCutSlider.setValue(lowerCut - 1);
    }//GEN-LAST:event_lowerCutPlusButtonActionPerformed

    private void changeStopwordsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeStopwordsButtonActionPerformed
        WordsManager.getInstance(this, true).display();
    }//GEN-LAST:event_changeStopwordsButtonActionPerformed

    private void lowerCutSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_lowerCutSliderStateChanged
        int upperCut = this.upperCutSlider.getValue();
        int lowerCut = this.lowerCutSlider.getValue();

        int[] freqs = ((ZipfCurve) this.zipfCurvePanel).setCutLines(lowerCut, upperCut);
        this.lowerCutTextField.setText(Integer.toString(freqs[0]));
        this.upperCutTextField.setText(Integer.toString(freqs[1]));
        this.ngramsTable.setRowSelectionInterval(upperCut, lowerCut);
        this.ngramsTextField.setText(Integer.toString(lowerCut - upperCut + 1));

        if (this.lowerCutSlider.getValue() < this.upperCutSlider.getValue()) {
            this.upperCutSlider.setValue(this.lowerCutSlider.getValue());
        }
    }//GEN-LAST:event_lowerCutSliderStateChanged

    private void upperCutSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_upperCutSliderStateChanged
        int upperCut = this.upperCutSlider.getValue();
        int lowerCut = this.lowerCutSlider.getValue();

        int[] freqs = ((ZipfCurve) this.zipfCurvePanel).setCutLines(lowerCut, upperCut);
        this.lowerCutTextField.setText(Integer.toString(freqs[0]));
        this.upperCutTextField.setText(Integer.toString(freqs[1]));
        this.ngramsTable.setRowSelectionInterval(upperCut, lowerCut);
        this.ngramsTextField.setText(Integer.toString(lowerCut - upperCut + 1));

        if (this.upperCutSlider.getValue() > this.lowerCutSlider.getValue()) {
            this.lowerCutSlider.setValue(this.upperCutSlider.getValue());
        }
    }//GEN-LAST:event_upperCutSliderStateChanged

    private void analyzeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_analyzeButtonActionPerformed
        analizeButtonAction(evt);
    }//GEN-LAST:event_analyzeButtonActionPerformed

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        closeButtonAction(evt);
    }//GEN-LAST:event_closeButtonActionPerformed

    public static LuhnCutAnalizer getInstance(java.awt.Container parent) {
        if (instance == null || instance.getParent() != parent) {
            if (parent instanceof javax.swing.JFrame) {
                instance = new LuhnCutAnalizer((javax.swing.JFrame) parent);
            } else {
                instance = new LuhnCutAnalizer((javax.swing.JDialog) parent);
            }
        }

        return instance;
    }

    public void display(ProjectionData pdata, TopicData ldata) {
        this.tdata = ldata;
        this.pdata = pdata;
        this.upperCutTextField.setText("");
        this.lowerCutTextField.setText("");
        this.ngramsTextField.setText("");

        this.lowerCutSlider.setEnabled(false);
        this.lowerCutSlider.setValue(0);
        this.upperCutSlider.setEnabled(false);
        this.upperCutSlider.setValue(0);

        ((ZipfCurve) this.zipfCurvePanel).setNgrams(null);

        this.initModels();
        this.ngramsTable.setModel(this.tableModel);

        this.corpus = CorpusFactory.getInstance(pdata.getSourceFile(), pdata);

        this.setLocationRelativeTo(this.getParent());
        this.setVisible(true);
    }

    public void display(ProjectionData pdata) {
        this.pdata = pdata;
        this.tdata = null;
        this.upperCutTextField.setText("");
        this.lowerCutTextField.setText("");
        this.ngramsTextField.setText("");

        this.lowerCutSlider.setEnabled(false);
        this.lowerCutSlider.setValue(0);
        this.upperCutSlider.setEnabled(false);
        this.upperCutSlider.setValue(0);

        ((ZipfCurve) this.zipfCurvePanel).setNgrams(null);

        this.initModels();
        this.ngramsTable.setModel(this.tableModel);

        this.corpus = CorpusFactory.getInstance(pdata.getSourceFile(), pdata);

        this.pack();
        this.setLocationRelativeTo(this.getParent());
        this.setVisible(true);
    }

    public void display(ProjectionData pdata, Corpus corpus) {
        this.pdata = pdata;
        this.tdata = null;
        this.upperCutTextField.setText("");
        this.lowerCutTextField.setText("");
        this.ngramsTextField.setText("");

        this.lowerCutSlider.setEnabled(false);
        this.lowerCutSlider.setValue(0);
        this.upperCutSlider.setEnabled(false);
        this.upperCutSlider.setValue(0);

        ((ZipfCurve) this.zipfCurvePanel).setNgrams(null);

        this.initModels();
        this.ngramsTable.setModel(this.tableModel);

        this.corpus = corpus;

        this.pack();
        this.setLocationRelativeTo(this.getParent());
        this.setVisible(true);
    }

    protected void analizeButtonAction(ActionEvent evt)
    {
    	HashMap<String, Ngram> corporaNgrams = new HashMap<String, Ngram>();
        countWordsFrequency(corporaNgrams);

        int lowercut = pdata.getLunhLowerCut();
        if (tdata != null) {
            lowercut = tdata.getLunhLowerCut();
        }

        // Remove the ngrams which occurs less than LUHN-LOWER-CUT times
        List<Ngram> ngrams = new ArrayList<Ngram>();
        for (String key : corporaNgrams.keySet()) {
            Ngram n = corporaNgrams.get(key);
            if (n.getFrequency() >= lowercut) {
                ngrams.add(n);
            }
        }

        // Sorting the ngrams by its frequency in decreasing order
        Collections.sort(ngrams);

        initModels();
        ngramsTable.setModel(tableModel);

        for (Ngram ngram : ngrams) {
            String[] label = new String[2];
            label[0] = ngram.getNgram();
            label[1] = Integer.toString(ngram.getFrequency());
            tableModel.addRow(label);
        }

        ((ZipfCurve) zipfCurvePanel).setNgrams(ngrams);

        lowerCutSlider.setMaximum(ngrams.size() - 1);
        //this.lowerCutSlider.setMajorTickSpacing(ngrams.size()/100);
        lowerCutSlider.setEnabled(true);

        upperCutSlider.setMaximum(ngrams.size() - 1);
        //this.upperCutSlider.setMajorTickSpacing(ngrams.size()/100);
        upperCutSlider.setEnabled(true);
    }

    protected void closeButtonAction(java.awt.event.ActionEvent evt) {
        if (this.lowerCutTextField.getText().trim().length() > 0) {
            if (this.tdata != null) {
                this.tdata.setLunhLowerCut(Integer.parseInt(this.lowerCutTextField.getText()));
            } else {
                this.pdata.setLunhLowerCut(Integer.parseInt(this.lowerCutTextField.getText()));
            }
        }

        if (this.upperCutTextField.getText().trim().length() > 0) {
            if (this.tdata != null) {
                this.tdata.setLunhUpperCut(Integer.parseInt(this.upperCutTextField.getText()));
            } else {
                this.pdata.setLunhUpperCut(Integer.parseInt(this.upperCutTextField.getText()));
            }
        }
        this.setVisible(false);
    }

    protected void countWordsFrequency(Map<String, Ngram> corporaNgrams)
    {
        boolean useStopword = true;
        if (tdata != null) {
            useStopword = tdata.isUseStopword();
        } else {
            useStopword = pdata.isUseStopword();
        }

        boolean useStartword = false;
        if (tdata != null) {
            useStartword = tdata.isUseStartword();
        } else {
            useStartword = pdata.isUseStartword();
        }

        StemmerType stemmer = null;
        if (tdata != null) {
            stemmer = tdata.getStemmer();
        } else {
            stemmer = pdata.getStemmer();
        }

        int lowercut = pdata.getLunhLowerCut();
        int nrGrams = pdata.getNumberGrams();

        if (tdata != null) {
            lowercut = tdata.getLunhLowerCut();
            nrGrams = tdata.getNumberGrams();
        }

        // BasicPreProcessor pre = new MonoliticPreprocessor();
        BasicPreProcessor pre = PreProcessorFactory.getBasicInstance();
        pre.setCorpus(corpus);
        pre.setLowerCut(lowercut);
        pre.setUpperCut(-1);
        pre.setStemmer(stemmer);
        pre.setStopword(useStopword);
        pre.setStartword(useStartword);
        pre.setNumberGrams(nrGrams);
        pre.run();
        Collection<Ngram> ngrams = pre.getNgrams();

        for (Ngram n : ngrams) {
            corporaNgrams.put(n.getNgram(), n);
        }
    }

    protected void exportStopWords(String filename) throws IOException {
        Corpus cp = CorpusFactory.getInstance(pdata.getSourceFile(), pdata);

        boolean useStopword = true;
        if (this.tdata != null) {
            useStopword = this.tdata.isUseStopword();
        } else {
            useStopword = this.pdata.isUseStopword();
        }

        StemmerType stemmerType = null;
        if (this.tdata != null) {
            stemmerType = this.tdata.getStemmer();
        } else {
            stemmerType = this.pdata.getStemmer();
        }

        int nrGrams = pdata.getNumberGrams();
        int lowercut = pdata.getLunhLowerCut();
        if (this.tdata != null) {
            lowercut = tdata.getLunhLowerCut();
            nrGrams = tdata.getNumberGrams();
        }

        if (lowerCutTextField.getText().trim().length() > 0) {
            lowercut = Integer.parseInt(this.lowerCutTextField.getText());
        }

        int uppercut = -1;
        if (upperCutTextField.getText().trim().length() > 0) {
            uppercut = Integer.parseInt(this.upperCutTextField.getText());
        }

        // BasicPreProcessor pre = new MonoliticPreprocessor();
        BasicPreProcessor pre = PreProcessorFactory.getBasicInstance();
        pre.setCorpus(cp);
        pre.setLowerCut(lowercut);
        pre.setUpperCut(uppercut);
        pre.setStemmer(stemmerType);
        pre.setStopword(useStopword);
        pre.setStartword(useStartword);
        pre.setNumberGrams(nrGrams);
        pre.run();
        Collection<Ngram> res_ngrams = pre.getNgrams();
        
        pre.setLowerCut(1);
        pre.setUpperCut(-1);
        pre.setStemmer(StemmerType.NONE);
        pre.run();
        Collection<Ngram> corpus_ngrams = pre.getNgrams();
        StopWord stopWords = SetStopword.getInstance();
        Stemmer stemmer = StemmerFactory.getInstance(stemmerType);
        
        for (Ngram ngram : corpus_ngrams) {
            boolean contain = false;
            String corpus_ngram = ngram.getNgram();
            String corpus_ngram_stem = stemmer.stem(corpus_ngram);
            for (Ngram res_ngram : res_ngrams) {
                if (corpus_ngram_stem.equals(res_ngram.getNgram())) {
                    contain = true;
                    break;
                }
            }

            if (! contain) {
                stopWords.addStopword(corpus_ngram);
            }
        }

        //saving to the file
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new FileWriter(filename));
            Iterator<String> i = stopWords.iterator();
            while (i.hasNext()) {
            	String stp = i.next();
                out.write(stp);
                out.write("\n");
            }
        } catch (IOException ex) {
            throw new IOException(ex.getMessage());
        } finally {
            if (out != null) {
                try {
                    out.flush();
                    out.close();
                } catch (IOException e1) {
                    throw new IOException(e1.getMessage());
                }
            }
        }
    }

    protected void exportStartWords(String filename) throws IOException {
        Corpus cp = CorpusFactory.getInstance(pdata.getSourceFile(), pdata);

        boolean useStopword = true;
        if (this.tdata != null) {
            useStopword = this.tdata.isUseStopword();
        } else {
            useStopword = this.pdata.isUseStopword();
        }

        StemmerType stemmer = null;
        if (this.tdata != null) {
            stemmer = this.tdata.getStemmer();
        } else {
            stemmer = this.pdata.getStemmer();
        }

        int nrGrams = pdata.getNumberGrams();
        int lowercut = pdata.getLunhLowerCut();
        if (this.tdata != null) {
            lowercut = tdata.getLunhLowerCut();
            nrGrams = tdata.getNumberGrams();
        }

        if (this.lowerCutTextField.getText().trim().length() > 0) {
            lowercut = Integer.parseInt(this.lowerCutTextField.getText());
        }

        int uppercut = -1;
        if (this.upperCutTextField.getText().trim().length() > 0) {
            uppercut = Integer.parseInt(this.upperCutTextField.getText());
        }

        // BasicPreProcessor pre = new MonoliticPreprocessor();
        BasicPreProcessor pre = PreProcessorFactory.getBasicInstance();
        pre.setCorpus(cp);
        pre.setLowerCut(lowercut);
        pre.setUpperCut(uppercut);
        pre.setStemmer(stemmer);
        pre.setStopword(useStopword);
        pre.setStartword(useStartword);
        pre.setNumberGrams(nrGrams);
        pre.run();
        Collection<Ngram> res_ngrams = pre.getNgrams();
   
        //saving to the file
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new FileWriter(filename));
            for (Ngram stt : res_ngrams) {
                out.write(stt.getNgram());
                out.write("\r\n");
            }
        } catch (IOException ex) {
            throw new IOException(ex.getMessage());
        } finally {
            if (out != null) {
                try {
                    out.flush();
                    out.close();
                } catch (IOException e1) {
                    throw new IOException(e1.getMessage());
                }
            }
        }
    }

    protected void initModels() {
        String[] titulos = new String[]{"Ngram", "Frequency"};
        this.tableModel = new DefaultTableModel(null, titulos);
    }
}
