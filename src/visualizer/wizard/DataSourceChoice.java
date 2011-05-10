/* ***** BEGIN LICENSE BLOCK *****
 *
 * Copyright (c) 2005-2007 Universidade de Sao Paulo, Sao Carlos/SP, Brazil.
 * All Rights Reserved.
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
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import visualizer.corpus.bibtex.BibTeXCorpus;
import visualizer.projection.ProjectionData;
import visualizer.projection.SourceType;
import visualizer.util.OpenDialog;
import visualizer.util.filefilter.BibTeXFilter;
import visualizer.util.filefilter.DATAFilter;
import visualizer.util.filefilter.DMATFilter;
import visualizer.util.filefilter.TITLEFilter;
import visualizer.util.filefilter.ZIPFilter;

/**
 *
 * @author  Fernando Vieira Paulovich
 */
public class DataSourceChoice extends WizardPanel
{
    private ProjectionData pdata;

    
    private JButton bibtexButton;
    private JRadioButton bibtexRadioButton;
    private JTextField bibtexTextField;
    
    private JButton corporaButton;
    private JRadioButton corporaRadioButton;
    private JTextField corporaTextField;
    
    private JButton distanceMatrixButton;
    private JRadioButton distanceMatrixRadioButton;
    private JTextField distanceMatrixTextField;
    
    private JButton pointsButton;
    private JRadioButton pointsRadioButton;
    private JTextField pointsTextField;
    
    private ButtonGroup sourceButtonGroup;
    private JButton titlesButton;
    private JLabel titlesLabel;
    private JTextField titlesTextField;
    

    /**
     * Creates new form DataSourceChoice
     * @param pdata
     */
    public DataSourceChoice(ProjectionData projectionData) {
        pdata = projectionData;
        initComponents();
        reset();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     */
    private void initComponents() {
    	GridBagConstraints gridBagConstraints = null;

        sourceButtonGroup = new ButtonGroup();
              
        setBorder(BorderFactory.createTitledBorder("Choose the Data Source"));
        setLayout(new GridBagLayout());

        // Bibtex 
        bibtexRadioButton = new JRadioButton();
        sourceButtonGroup.add(bibtexRadioButton);
        bibtexRadioButton.setText("BibTeX");
        bibtexRadioButton.setToolTipText("BibTeX file");
        bibtexRadioButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        bibtexRadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                bibtexRadioButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        add(bibtexRadioButton, gridBagConstraints);
       
        bibtexTextField = new JTextField();
        bibtexTextField.setColumns(35);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        add(bibtexTextField, gridBagConstraints);

        bibtexButton = new JButton();
        bibtexButton.setText("Search...");
        bibtexButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                bibtexButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        add(bibtexButton, gridBagConstraints);

        
        // Corpora
        corporaRadioButton = new JRadioButton();
        sourceButtonGroup.add(corporaRadioButton);
        corporaRadioButton.setText("Documents");
        corporaRadioButton.setToolTipText("Document collection (ZIP file)");
        corporaRadioButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        corporaRadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                corporaRadioButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        add(corporaRadioButton, gridBagConstraints);

        corporaTextField = new JTextField();
        corporaTextField.setColumns(35);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        add(corporaTextField, gridBagConstraints);

        corporaButton = new JButton();
        corporaButton.setText("Search...");
        corporaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                corporaButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        add(corporaButton, gridBagConstraints);

        
        // Points
        pointsRadioButton = new JRadioButton();
        sourceButtonGroup.add(pointsRadioButton);
        pointsRadioButton.setText("Points File");
        pointsRadioButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
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
        add(pointsRadioButton, gridBagConstraints);

        pointsTextField = new JTextField();
        pointsTextField.setColumns(35);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        add(pointsTextField, gridBagConstraints);

        pointsButton = new JButton();
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
        add(pointsButton, gridBagConstraints);

        
        // Distance matrix
        distanceMatrixRadioButton = new JRadioButton();
        sourceButtonGroup.add(distanceMatrixRadioButton);
        distanceMatrixRadioButton.setText("Distance File");
        distanceMatrixRadioButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        distanceMatrixRadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                distanceMatrixRadioButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        add(distanceMatrixRadioButton, gridBagConstraints);

        distanceMatrixTextField = new JTextField();
        distanceMatrixTextField.setColumns(35);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        add(distanceMatrixTextField, gridBagConstraints);

        distanceMatrixButton = new JButton();     
        distanceMatrixButton.setText("Search...");
        distanceMatrixButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                distanceMatrixButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        add(distanceMatrixButton, gridBagConstraints);

        
        // Titles
        titlesLabel = new JLabel();
        titlesLabel.setText("Titles File");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        gridBagConstraints.insets = new Insets(6, 3, 3, 3);
        add(titlesLabel, gridBagConstraints);

        titlesTextField = new JTextField();
        titlesTextField.setColumns(35);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(6, 3, 3, 3);
        add(titlesTextField, gridBagConstraints);

        titlesButton = new JButton();
        titlesButton.setText("Search...");
        titlesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                titlesButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(6, 3, 3, 3);
        add(titlesButton, gridBagConstraints);
    }

	private void bibtexButtonActionPerformed(ActionEvent evt){
        int result = OpenDialog.showOpenDialog(new BibTeXFilter(), this);

        if (result == JFileChooser.APPROVE_OPTION) {
            String filename = OpenDialog.getFilename();
            bibtexTextField.setText(filename);
        }
		
	}

    
    private void corporaButtonActionPerformed(ActionEvent evt) {
        int result = OpenDialog.showOpenDialog(new ZIPFilter(), this);

        if (result == JFileChooser.APPROVE_OPTION) {
            String filename = OpenDialog.getFilename();
            corporaTextField.setText(filename);
        }
    }

    private void pointsButtonActionPerformed(ActionEvent evt) {
        int result = OpenDialog.showOpenDialog(new DATAFilter(), this);

        if (result == JFileChooser.APPROVE_OPTION) {
            String filename = OpenDialog.getFilename();
            pointsTextField.setText(filename);
            findTitlesFile(OpenDialog.getJFileChooser().getSelectedFile());
        }
    }

    private void distanceMatrixButtonActionPerformed(ActionEvent evt) {
        int result = OpenDialog.showOpenDialog(new DMATFilter(), this);

        if (result == JFileChooser.APPROVE_OPTION) {
            String filename = OpenDialog.getFilename();
            distanceMatrixTextField.setText(filename);
            findTitlesFile(OpenDialog.getJFileChooser().getSelectedFile());
        }
    }

    private void titlesButtonActionPerformed(ActionEvent evt) {
        int result = OpenDialog.showOpenDialog(new TITLEFilter(), this);

		if (result == JFileChooser.APPROVE_OPTION) {
            String filename = OpenDialog.getFilename();
            titlesTextField.setText(filename);
        }
    }

    private void bibtexRadioButtonActionPerformed(ActionEvent evt) {
    	reset();
    	pdata.setSourceType(SourceType.BIBTEX);
        bibtexButton.setEnabled(true);
        bibtexTextField.setEnabled(true);
    }


    private void corporaRadioButtonActionPerformed(ActionEvent evt) {
    	reset();
        pdata.setSourceType(SourceType.CORPUS);
        corporaButton.setEnabled(true);
        corporaTextField.setEnabled(true);
    }


    private void pointsRadioButtonActionPerformed(ActionEvent evt) {
    	reset();
        pdata.setSourceType(SourceType.POINTS);
        pointsButton.setEnabled(true);
        pointsTextField.setEnabled(true);

        titlesButton.setEnabled(true);
        titlesTextField.setEnabled(true);
        titlesTextField.setText("");
    }
    

    private void distanceMatrixRadioButtonActionPerformed(ActionEvent evt) {
    	reset();
        pdata.setSourceType(SourceType.DISTANCE_MATRIX);
        distanceMatrixButton.setEnabled(true);
        distanceMatrixTextField.setEnabled(true);

        titlesButton.setEnabled(true);
        titlesTextField.setEnabled(true);
        titlesTextField.setText("");
    }
    
    private void findTitlesFile(File file) {
        String filename = file.getName();
        String baseDir = file.getPath();
        
        String titleFileName = filename.substring(0, filename.lastIndexOf('.')) + ".titles";
        String namesFileName = file.getName().substring(0, filename.lastIndexOf('.')) + ".names";
        
        File titlesFile = new File(baseDir, titleFileName);
        if (titlesFile.exists() && titlesFile.isFile()) {
            titlesTextField.setText(titlesFile.getAbsolutePath());
        }
        
        File namesFile = new File(baseDir, namesFileName);
        if (namesFile.exists() && namesFile.isFile()) {
        	
        }
    }
    
    public void reset() {
        pdata.setSourceType(null);

        bibtexButton.setEnabled(false);
        bibtexTextField.setEnabled(false);
        bibtexTextField.setText("");

        corporaButton.setEnabled(false);
        corporaTextField.setEnabled(false);
        corporaTextField.setText("");

        distanceMatrixButton.setEnabled(false);
        distanceMatrixTextField.setEnabled(false);
        distanceMatrixTextField.setText("");

        pointsButton.setEnabled(false);
        pointsTextField.setEnabled(false);
        pointsTextField.setText("");

        titlesButton.setEnabled(false);
        titlesTextField.setEnabled(false);
        titlesTextField.setText("");
    }

    @Override
    public void refreshData() {
        if (bibtexRadioButton.isSelected()) {
            pdata.setSourceFile(BibTeXCorpus.translateFilename(bibtexTextField.getText()));
        } else if (corporaRadioButton.isSelected()) {
            pdata.setSourceFile(corporaTextField.getText());
        } else if (pointsRadioButton.isSelected()) {
            pdata.setSourceFile(pointsTextField.getText());
            pdata.setTitlesFile(titlesTextField.getText());
        } else if (distanceMatrixRadioButton.isSelected()) {
            pdata.setSourceFile(distanceMatrixTextField.getText());
            pdata.setTitlesFile(titlesTextField.getText());
        }
    }
}
