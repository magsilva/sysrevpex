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

package visualizer.view.tools;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.JPanel;

import visualizer.textprocessing.Ngram;

/**
 * Panel to plot Zipf-like data.
 * 
 * The X axis uses a linear scale while the Y axis uses a logarithm scale.
 * The Y represents the frequency of the term (ngram) and that value is
 * normalized using the minimum and maximum frequencies of the ngrams.
 */
public class ZipfCurve extends JPanel
{
    public static final int DEFAULT_GRID_SIZE = 40;
    
    public static final int DEFAULT_MARGIN_SIZE = 10;

    private int upperLine;
    
    private int lowerLine;
    
    private int gridSize = DEFAULT_GRID_SIZE;
    
    private int marginSize = DEFAULT_MARGIN_SIZE;

    
    private BufferedImage imageBuffer;
    
    private List<Ngram> ngrams;

    public ZipfCurve() {
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                drawImage();
                repaint();
            }
        });
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(this.imageBuffer, 0, 0, this.getWidth(), this.getHeight(), null);
    }

    public void setNgrams(List<Ngram> ngrams)
    {
        this.ngrams = ngrams;
        drawImage();
        repaint();
    }

    public int[] setCutLines(int lowerLine, int upperLine)
    {
        this.lowerLine = lowerLine;
        this.upperLine = upperLine;
        drawImage();
        repaint();

    	int[] freqs = {0, 0};
        if (ngrams != null) {
        	freqs[0] = ngrams.get(lowerLine).getFrequency();
        	freqs[1] = ngrams.get(upperLine).getFrequency();
        }

        return freqs;
    }

    private void drawImage()
    {
    	Graphics2D gBuffer;
    	Dimension size;
        
    	imageBuffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        gBuffer = imageBuffer.createGraphics();
        size = getSize();
        gBuffer.setColor(Color.WHITE);
        gBuffer.fillRect(0, 0, size.width, size.height);

        // Draw the grid
        for (int i = 0; i < gridSize; i++) {
        	int column, line;
        	
            gBuffer.setColor(Color.LIGHT_GRAY);
            column = (size.width * (i + 1)) / gridSize;
            line = (size.height * (i + 1)) / gridSize;
            gBuffer.drawLine(column, 0, column, size.height);
            gBuffer.drawLine(0, line, size.width, line);
        }

        // Draw the points and link them (one by one). We use a logarithm scale for the frequency (y) and linear scale for x
        gBuffer.setColor(Color.BLACK);
        gBuffer.drawRect(0, 0, size.width - 1, size.height - 1);
        if (ngrams != null) {
            int nelements = ngrams.size();
            int nFrequency;
            int pos1x, pos1y, pos2x, pos2y;
            double maxf = ngrams.get(0).getFrequency();
            double minf = ngrams.get(0).getFrequency();

            for (int i = 1; i < nelements; i++) {
            	nFrequency = ngrams.get(i).getFrequency();
                if (nFrequency > maxf) {
                    maxf = nFrequency;
                } else if (nFrequency < minf) {
                    minf = nFrequency;
                }
            }

            // We normalize using maxf and minf for the y axis
            maxf = Math.log(maxf);
            minf = Math.log(minf);
            gBuffer.setColor(Color.RED);
            for (int i = nelements - 1, j = 0; i > 0; i--, j++) {
                pos1x = (j * (size.width - (2 * marginSize))) / nelements + marginSize;
                pos1y = (int) (((Math.log(ngrams.get(i).getFrequency()) - minf) * (size.height - (2 * marginSize))) / (maxf - minf)) + marginSize;
                pos2x = (j + 1) * (size.width - (2 * marginSize)) / nelements + marginSize;
                pos2y = (int) (((Math.log(ngrams.get(i - 1).getFrequency()) - minf) * (size.height - (2 * marginSize))) / (maxf - minf)) + marginSize;
                gBuffer.drawLine(pos1x, size.height - pos1y, pos2x, size.height - pos2y);
            }

            
            int posL1 = (int) (( upperLine * (size.width - marginSize)) / nelements) + marginSize;
            int posL2 = (int) (( lowerLine * (size.width - marginSize)) / nelements) + marginSize;

            if (posL2 > posL1) {
                gBuffer.setColor(Color.BLUE);
                gBuffer.drawRect(posL1, marginSize, Math.abs(posL2 - posL1), size.height - marginSize);
                gBuffer.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.2f));
                gBuffer.fill(new Rectangle(posL1, marginSize, Math.abs(posL2 - posL1), size.height - marginSize));
            }
        }
    }
}
