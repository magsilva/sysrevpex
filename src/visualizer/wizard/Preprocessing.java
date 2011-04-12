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

package visualizer.wizard;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JCheckBox;

import visualizer.projection.ProjectionData;
import visualizer.textprocessing.transformation.MatrixTransformationType;
import visualizer.textprocessing.stemmer.StemmerType;
import visualizer.view.tools.LuhnCutAnalizer;

/**
 *
 * @author  Fernando Vieira Paulovich
 */
public class Preprocessing extends WizardPanel
{
	private ProjectionData pdata;
    
	private JButton analyzeButton;
    private JComboBox gramsComboBox;
    private JLabel gramsLabel;
    private JLabel luhnLabel;
    private JTextField luhnLowerTextField;
    private JLabel luhnUpperLabel;
    private JTextField luhnUpperTextField;
    private JPanel matrixTypePanel;
    private JComboBox matrixtransfComboBox;
    private JPanel preProcessingPanel;
    private JComboBox stemmerComboBox;
    private JCheckBox stopwordCheckBox;
    private JCheckBox startwordCheckBox;
    private JCheckBox useWeightCheckBox;
    private JPanel wordListTypePanel;
    // End of variables declaration//GEN-END:variables

    /**
     * Creates new form Preprocessing.
     * 
     * @param pdata Projection data.
     */
    public Preprocessing(ProjectionData pdata)
    {
        this.pdata = pdata;
        initComponents();

        for (StemmerType st : StemmerType.getTypes()) {
            stemmerComboBox.addItem(st);
        }

        for (MatrixTransformationType mtt : MatrixTransformationType.getTypes()) {
            matrixtransfComboBox.addItem(mtt);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        GridBagConstraints gridBagConstraints;

        preProcessingPanel = new JPanel();
        luhnLabel = new JLabel();
        gramsComboBox = new JComboBox();
        gramsLabel = new JLabel();
        luhnLowerTextField = new JTextField();
        analyzeButton = new JButton();
        luhnUpperLabel = new JLabel();
        luhnUpperTextField = new JTextField();
        stemmerComboBox = new JComboBox();
        matrixTypePanel = new JPanel();
        matrixtransfComboBox = new JComboBox();
        wordListTypePanel = new JPanel();
        stopwordCheckBox = new JCheckBox();
        startwordCheckBox = new JCheckBox();
        useWeightCheckBox = new JCheckBox();

        setBorder(BorderFactory.createTitledBorder("Corpus Pre-processing"));
        setLayout(new GridBagLayout());

        preProcessingPanel.setBorder(BorderFactory.createTitledBorder("Parameters"));
        preProcessingPanel.setLayout(new GridBagLayout());

        luhnLabel.setText("Luhn's lower cut");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        preProcessingPanel.add(luhnLabel, gridBagConstraints);

        gramsComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3" }));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        preProcessingPanel.add(gramsComboBox, gridBagConstraints);

        gramsLabel.setText("Number of grams");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        preProcessingPanel.add(gramsLabel, gridBagConstraints);

        luhnLowerTextField.setColumns(5);
        luhnLowerTextField.setText("10");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        preProcessingPanel.add(luhnLowerTextField, gridBagConstraints);

        analyzeButton.setText("Analyze");
        analyzeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                analyzeButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        preProcessingPanel.add(analyzeButton, gridBagConstraints);

        luhnUpperLabel.setText("Luhn's upper cut");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        preProcessingPanel.add(luhnUpperLabel, gridBagConstraints);

        luhnUpperTextField.setColumns(5);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        preProcessingPanel.add(luhnUpperTextField, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        preProcessingPanel.add(stemmerComboBox, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        add(preProcessingPanel, gridBagConstraints);

        matrixTypePanel.setBorder(BorderFactory.createTitledBorder("Matrix Transformation"));
        matrixTypePanel.setLayout(new GridBagLayout());
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        matrixTypePanel.add(matrixtransfComboBox, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        add(matrixTypePanel, gridBagConstraints);

        wordListTypePanel.setBorder(BorderFactory.createTitledBorder("Word List Type"));
        wordListTypePanel.setLayout(new GridBagLayout());

        stopwordCheckBox.setText("Stop Words");
        stopwordCheckBox.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        stopwordCheckBox.setMargin(new Insets(0, 0, 0, 0));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        wordListTypePanel.add(stopwordCheckBox, gridBagConstraints);

        startwordCheckBox.setText("Start Words");
        startwordCheckBox.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        startwordCheckBox.setMargin(new Insets(0, 0, 0, 0));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        wordListTypePanel.add(startwordCheckBox, gridBagConstraints);

        useWeightCheckBox.setText("Use weights");
        useWeightCheckBox.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        useWeightCheckBox.setMargin(new Insets(0, 0, 0, 0));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        wordListTypePanel.add(useWeightCheckBox, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        add(wordListTypePanel, gridBagConstraints);
    }
    
    private void analyzeButtonActionPerformed(ActionEvent evt)
    {
        refreshData();
        ProjectionWizardView view = (ProjectionWizardView) getTopLevelAncestor();
        LuhnCutAnalizer.getInstance(view).display(pdata);
        luhnLowerTextField.setText(Integer.toString(pdata.getLunhLowerCut()));
        luhnUpperTextField.setText(Integer.toString(pdata.getLunhUpperCut()));
    }

    @Override
    public void refreshData()
    {
        pdata.setStemmer((StemmerType) stemmerComboBox.getSelectedItem());
        pdata.setUseStopword(stopwordCheckBox.isSelected());
        pdata.setUseStartword(startwordCheckBox.isSelected());
        pdata.setUseWeight(useWeightCheckBox.isSelected());
        pdata.setMatrixTransformationType((MatrixTransformationType) matrixtransfComboBox.getSelectedItem());
        pdata.setNumberGrams(gramsComboBox.getSelectedIndex() + 1);

        if (luhnLowerTextField.getText().trim().length() > 0) {
            pdata.setLunhLowerCut(Integer.parseInt(luhnLowerTextField.getText()));
        } else {
            pdata.setLunhLowerCut(1);
        }

        if (luhnUpperTextField.getText().trim().length() > 0) {
            pdata.setLunhUpperCut(Integer.parseInt(luhnUpperTextField.getText()));
        } else {
            pdata.setLunhUpperCut(-1);
        }
    }
}
