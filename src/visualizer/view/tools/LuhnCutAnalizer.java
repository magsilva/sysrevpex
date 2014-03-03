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

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import opennlp.tools.util.StringUtil;

import visualizer.corpus.CorpusFactory;
import visualizer.corpus.Corpus;
import visualizer.textprocessing.BasicPreProcessor;
import visualizer.textprocessing.Ngram;
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
public class LuhnCutAnalizer extends JDialog
{

    private ProjectionData pdata;
    
    private TopicData tdata;
    
    private static LuhnCutAnalizer instance;

    private Corpus corpus;
  
    private DefaultTableModel tableModel;

    private boolean useStartword = false;

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton analyzeButton;
    private JPanel buttonPanel;
    private JButton changeStartwordsButton;
    private JButton changeStopwordsButton;
    protected JButton closeButton;
    private JPanel cutConfigurationPanel;
    private JButton exportStartwordsButton;
    private JButton exportStopwordsButton;
    private JButton loerCutMinusButton;
    private JPanel lowerCutButtonPanel;
    private JLabel lowerCutLabel;
    private JPanel lowerCutPanel;
    private JButton lowerCutPlusButton;
    protected JSlider lowerCutSlider;
    protected JTextField lowerCutTextField;
    private JPanel luhnPanel;
    private JLabel ngramsLabel;
    private JPanel ngramsPanel;
    private JScrollPane ngramsScrollPane;
    protected JTable ngramsTable;
    protected JTextField ngramsTextField;
    private JPanel numberGramsPanel;
    private JPanel upperCutButtonPanel;
    private JLabel upperCutLabel;
    private JButton upperCutMinusButton;
    private JPanel upperCutPanel;
    private JButton upperCutPlusButton;
    protected JSlider upperCutSlider;
    protected JTextField upperCutTextField;
    protected ZipfCurve zipfCurvePanel;
    private JPanel zipfPanel;
    // End of variables declaration//GEN-END:variables

    
	/**
	 * Creates new form LuhnCutAnalizer
	 * 
     * @param parent 
     */
    protected LuhnCutAnalizer(JDialog parent) {
        super(parent);
        initModels();
        initComponents();

        this.setPreferredSize(new Dimension(700, 550));
        this.setSize(700, 550);
    }

    protected LuhnCutAnalizer(JFrame parent) {
        super(parent);
        initModels();
        initComponents();

        this.setPreferredSize(new Dimension(700, 550));
        this.setSize(700, 550);
    }

    /** 
     * This method is called from within the constructor to initialize the form.
     */
    private void initComponents() {
        GridBagConstraints gridBagConstraints;

        luhnPanel = new JPanel();
        zipfPanel = new JPanel();
        zipfCurvePanel = new ZipfCurve();
        cutConfigurationPanel = new JPanel();
        upperCutPanel = new JPanel();
        upperCutSlider = new JSlider();
        upperCutLabel = new JLabel();
        upperCutButtonPanel = new JPanel();
        upperCutTextField = new JTextField();
        upperCutPlusButton = new JButton();
        upperCutMinusButton = new JButton();
        lowerCutPanel = new JPanel();
        lowerCutSlider = new JSlider();
        lowerCutButtonPanel = new JPanel();
        lowerCutTextField = new JTextField();
        lowerCutPlusButton = new JButton();
        loerCutMinusButton = new JButton();
        lowerCutLabel = new JLabel();
        ngramsPanel = new JPanel();
        numberGramsPanel = new JPanel();
        ngramsLabel = new JLabel();
        ngramsTextField = new JTextField();
        ngramsScrollPane = new JScrollPane();
        ngramsTable = new JTable();
        changeStopwordsButton = new JButton();
        changeStartwordsButton = new JButton();
        exportStopwordsButton = new JButton();
        exportStartwordsButton = new JButton();
        buttonPanel = new JPanel();
        analyzeButton = new JButton();
        closeButton = new JButton();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Luhn's cut-off Analysis");
        setModal(true);

        luhnPanel.setBorder(BorderFactory.createTitledBorder("Data"));
        luhnPanel.setLayout(new BorderLayout());

        zipfPanel.setBorder(BorderFactory.createTitledBorder("Zipf's Curve"));
        zipfPanel.setLayout(new BorderLayout());
        zipfPanel.add(zipfCurvePanel, BorderLayout.CENTER);

        luhnPanel.add(zipfPanel, BorderLayout.CENTER);

        cutConfigurationPanel.setBorder(BorderFactory.createTitledBorder("Cut-off Configuration"));
        cutConfigurationPanel.setLayout(new BorderLayout());

        upperCutPanel.setLayout(new BorderLayout());

        upperCutSlider.setMajorTickSpacing(1);
        upperCutSlider.setSnapToTicks(true);
        upperCutSlider.setValue(0);
        upperCutSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent evt) {
                upperCutSliderStateChanged(evt);
            }
        });
        upperCutPanel.add(upperCutSlider, BorderLayout.CENTER);

        upperCutLabel.setText("Upper Cut");
        upperCutPanel.add(upperCutLabel, BorderLayout.WEST);

        upperCutButtonPanel.setLayout(new GridBagLayout());

        upperCutTextField.setColumns(5);
        upperCutTextField.setEditable(false);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        upperCutButtonPanel.add(upperCutTextField, gridBagConstraints);

        upperCutPlusButton.setText("+");
        upperCutPlusButton.setPreferredSize(new Dimension(43, 15));
        upperCutPlusButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
                upperCutPlusButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        upperCutButtonPanel.add(upperCutPlusButton, gridBagConstraints);

        upperCutMinusButton.setText("-");
        upperCutMinusButton.setPreferredSize(new Dimension(39, 15));
        upperCutMinusButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                upperCutMinusButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        upperCutButtonPanel.add(upperCutMinusButton, gridBagConstraints);

        upperCutPanel.add(upperCutButtonPanel, BorderLayout.EAST);

        cutConfigurationPanel.add(upperCutPanel, BorderLayout.SOUTH);

        lowerCutPanel.setLayout(new BorderLayout());

        lowerCutSlider.setMajorTickSpacing(1);
        lowerCutSlider.setSnapToTicks(true);
        lowerCutSlider.setValue(0);
        lowerCutSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent evt) {
                lowerCutSliderStateChanged(evt);
            }
        });
        lowerCutPanel.add(lowerCutSlider, BorderLayout.CENTER);

        lowerCutButtonPanel.setLayout(new GridBagLayout());

        lowerCutTextField.setColumns(5);
        lowerCutTextField.setEditable(false);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        lowerCutButtonPanel.add(lowerCutTextField, gridBagConstraints);

        lowerCutPlusButton.setText("+");
        lowerCutPlusButton.setPreferredSize(new Dimension(43, 15));
        lowerCutPlusButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                lowerCutPlusButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        lowerCutButtonPanel.add(lowerCutPlusButton, gridBagConstraints);

        loerCutMinusButton.setText("-");
        loerCutMinusButton.setPreferredSize(new Dimension(39, 15));
        loerCutMinusButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                lowerCutMinusButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        lowerCutButtonPanel.add(loerCutMinusButton, gridBagConstraints);

        lowerCutPanel.add(lowerCutButtonPanel, BorderLayout.EAST);

        lowerCutLabel.setText("Lower Cut");
        lowerCutPanel.add(lowerCutLabel, BorderLayout.WEST);

        cutConfigurationPanel.add(lowerCutPanel, BorderLayout.NORTH);

        luhnPanel.add(cutConfigurationPanel, BorderLayout.SOUTH);

        ngramsPanel.setLayout(new GridBagLayout());

        ngramsLabel.setText("Number ngrams");
        numberGramsPanel.add(ngramsLabel);

        ngramsTextField.setColumns(5);
        ngramsTextField.setEditable(false);
        numberGramsPanel.add(ngramsTextField);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridwidth = 2;
        ngramsPanel.add(numberGramsPanel, gridBagConstraints);

        ngramsScrollPane.setBorder(BorderFactory.createTitledBorder("Ngrams and Frequency"));
        ngramsScrollPane.setPreferredSize(new Dimension(230, 275));

        ngramsTable.setModel(this.tableModel);
        ngramsTable.setEnabled(false);
        ngramsScrollPane.setViewportView(ngramsTable);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        ngramsPanel.add(ngramsScrollPane, gridBagConstraints);

        changeStopwordsButton.setText("Change Stopwords");
        changeStopwordsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                changeStopwordsButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        ngramsPanel.add(changeStopwordsButton, gridBagConstraints);

        changeStartwordsButton.setText("Change Startwords");
        changeStartwordsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                changeStartwordsButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        ngramsPanel.add(changeStartwordsButton, gridBagConstraints);

        exportStopwordsButton.setText("Export Stopwords");
        exportStopwordsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                exportStopwordsButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        ngramsPanel.add(exportStopwordsButton, gridBagConstraints);

        exportStartwordsButton.setText("Export Startwords");
        exportStartwordsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                exportStartwordsButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        ngramsPanel.add(exportStartwordsButton, gridBagConstraints);

        luhnPanel.add(ngramsPanel, BorderLayout.EAST);

        getContentPane().add(luhnPanel, BorderLayout.CENTER);

        analyzeButton.setText("Analyze");
        analyzeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                analyzeButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(analyzeButton);

        closeButton.setText("Close");
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(closeButton);

        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        pack();
    }
    
    private void exportStartwordsButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_exportStartwordsButtonActionPerformed
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

    private void exportStopwordsButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_exportStopwordsButtonActionPerformed
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

    private void changeStartwordsButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_changeStartwordsButtonActionPerformed
        WordsManager.getInstance(this, false).display();
    }//GEN-LAST:event_changeStartwordsButtonActionPerformed

    private void upperCutMinusButtonActionPerformed(ActionEvent evt) {
        int upperCut = upperCutSlider.getValue();
        upperCutSlider.setValue(upperCut - 1);
    }

    private void upperCutPlusButtonActionPerformed(ActionEvent evt) {
        int upperCut = upperCutSlider.getValue();
        upperCutSlider.setValue(upperCut + 1);
    }

    private void lowerCutMinusButtonActionPerformed(ActionEvent evt) {
        int lowerCut = lowerCutSlider.getValue();
        lowerCutSlider.setValue(lowerCut - 1);
    }

    private void lowerCutPlusButtonActionPerformed(ActionEvent evt) {
        int lowerCut = lowerCutSlider.getValue();
        lowerCutSlider.setValue(lowerCut + 1);
    }

    private void changeStopwordsButtonActionPerformed(ActionEvent evt) {
        WordsManager.getInstance(this, true).display();
    }

    private void lowerCutSliderStateChanged(ChangeEvent evt) {
    	updateLowerUpperSlider();
        if (lowerCutSlider.getValue() > upperCutSlider.getValue()) {
            upperCutSlider.setValue(lowerCutSlider.getValue());
        }
    }

    private void upperCutSliderStateChanged(ChangeEvent evt) {
    	updateLowerUpperSlider();
        if (upperCutSlider.getValue() < lowerCutSlider.getValue()) {
            lowerCutSlider.setValue(upperCutSlider.getValue());
        }
    }
    
    private void updateLowerUpperSlider() {
        int lowerCut = lowerCutSlider.getValue();
        int upperCut = upperCutSlider.getValue();

        int[] freqs = zipfCurvePanel.setCutLines(lowerCut, upperCut);
        lowerCutTextField.setText(Integer.toString(freqs[0]));
        upperCutTextField.setText(Integer.toString(freqs[1]));
        ngramsTable.setRowSelectionInterval(lowerCut, upperCut);
        ngramsTextField.setText(Integer.toString(upperCut - lowerCut + 1));
    }

    private void analyzeButtonActionPerformed(ActionEvent evt) {
        analizeButtonAction(evt);
    }

    private void closeButtonActionPerformed(ActionEvent evt) {
        closeButtonAction(evt);
    }

    public static LuhnCutAnalizer getInstance(Container parent) {
        if (instance == null || instance.getParent() != parent) {
            if (parent instanceof JFrame) {
                instance = new LuhnCutAnalizer((JFrame) parent);
            } else {
                instance = new LuhnCutAnalizer((JDialog) parent);
            }
        }

        return instance;
    }

    public void display(ProjectionData pdata, TopicData ldata) {
        this.tdata = ldata;
        this.pdata = pdata;
        
        int lowercut = pdata.getLunhLowerCut();
        if (tdata != null) {
            lowercut = tdata.getLunhLowerCut();
        }

        int uppercut = pdata.getLunhUpperCut();
        if (tdata != null) {
        	uppercut = tdata.getLunhLowerCut();
        }
        
        upperCutTextField.setText("");
        lowerCutTextField.setText("");
        ngramsTextField.setText("");

        lowerCutSlider.setEnabled(false);
        lowerCutSlider.setValue(lowercut);
        upperCutSlider.setEnabled(false);
        upperCutSlider.setValue(uppercut);

        zipfCurvePanel.setNgrams(null);

        initModels();
        ngramsTable.setModel(this.tableModel);

        corpus = CorpusFactory.getInstance(pdata.getSourceFile(), pdata);

        setLocationRelativeTo(getParent());
        setVisible(true);
    }

    public void display(ProjectionData pdata) {
        this.pdata = pdata;
        
        tdata = null;
        upperCutTextField.setText("");
        lowerCutTextField.setText("");
        ngramsTextField.setText("");

        lowerCutSlider.setEnabled(false);
        lowerCutSlider.setValue(pdata.getLunhLowerCut());
        upperCutSlider.setEnabled(false);
        upperCutSlider.setValue(pdata.getLunhUpperCut());

        zipfCurvePanel.setNgrams(null);

        initModels();
        ngramsTable.setModel(tableModel);

        corpus = CorpusFactory.getInstance(pdata.getSourceFile(), pdata);

        pack();
        setLocationRelativeTo(getParent());
        setVisible(true);
    }

    public void display(ProjectionData pdata, Corpus corpus) {
        this.pdata = pdata;
        this.corpus = corpus;
        
        tdata = null;
        upperCutTextField.setText("");
        lowerCutTextField.setText("");
        ngramsTextField.setText("");

        lowerCutSlider.setEnabled(false);
        lowerCutSlider.setValue(pdata.getLunhLowerCut());
        upperCutSlider.setEnabled(false);
        upperCutSlider.setValue(pdata.getLunhUpperCut());

        zipfCurvePanel.setNgrams(null);

        initModels();
        ngramsTable.setModel(tableModel);

        pack();
        setLocationRelativeTo(getParent());
        setVisible(true);
    }

    protected void analizeButtonAction(ActionEvent evt)
    {
    	HashMap<String, Ngram> corporaNgrams = new HashMap<String, Ngram>();
        countWordsFrequency(corporaNgrams);

        int lowercut = pdata.getLunhLowerCut();
        if (tdata != null) {
            lowercut = tdata.getLunhLowerCut();
        }

        int uppercut = pdata.getLunhUpperCut();
        if (tdata != null) {
        	uppercut = tdata.getLunhLowerCut();
        }
        
        // Apply LU-cut
        List<Ngram> ngrams = new ArrayList<Ngram>();
        for (String key : corporaNgrams.keySet()) {
            Ngram n = corporaNgrams.get(key);
            int frequency = n.getFrequency();
            if (frequency >= lowercut && (uppercut == -1 || frequency <= uppercut)) {
                ngrams.add(n);
            }
        }

        // Sorting the ngrams by its frequency in decreasing order
        Collections.sort(ngrams);

        initModels();
        ngramsTable.setModel(tableModel);
        for (int i = ngrams.size() - 1; i >= 0; i--) {
        	Ngram ngram = ngrams.get(i);
            String[] label = new String[2];
            label[0] = ngram.getNgram();
            label[1] = Integer.toString(ngram.getFrequency());
            tableModel.addRow(label);
        }

        zipfCurvePanel.setNgrams(ngrams);

        lowerCutSlider.setValue(lowercut);
        lowerCutSlider.setMaximum(ngrams.size() - 1);
        lowerCutSlider.setEnabled(true);

        if (uppercut == -1) {
        	upperCutSlider.setValue(ngrams.size() - 1);
        } else {
        	upperCutSlider.setValue(uppercut);
        }
        upperCutSlider.setMaximum(ngrams.size() - 1);
        upperCutSlider.setEnabled(true);
    }

    protected void closeButtonAction(ActionEvent evt) {
        if (! StringUtil.isEmpty(lowerCutTextField.getText())) {
        	int lower = Integer.parseInt(lowerCutTextField.getText());
            if (tdata != null) {
                tdata.setLunhLowerCut(lower);
            } else {
                pdata.setLunhLowerCut(lower);
            }
        }

        if (! StringUtil.isEmpty(upperCutTextField.getText())) {
        	int upper = Integer.parseInt(this.upperCutTextField.getText());
            if (tdata != null) {
                tdata.setLunhUpperCut(upper);
            } else {
                pdata.setLunhUpperCut(upper);
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
