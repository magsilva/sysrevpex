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

/*
 * ZipfCurve.java
 *
 * Created on 27 de Agosto de 2006, 13:31
 */
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
 *
 * @author Fernando Vieira Paulovich
 */
public class ZipfCurve extends JPanel
{
    private int upperLine;
    
    private int lowerLine;
    
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
        this.upperLine = upperLine;
        this.lowerLine = lowerLine;
        drawImage();
        repaint();

        int[] freqs = new int[2];
        freqs[0] = ngrams.get(lowerLine).getFrequency();
        freqs[1] = ngrams.get(upperLine).getFrequency();

        return freqs;
    }

    private void drawImage()
    {
        imageBuffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics gBuffer = imageBuffer.createGraphics();

        Dimension size = getSize();
        gBuffer.setColor(Color.WHITE);
        gBuffer.fillRect(0, 0, size.width, size.height);

        int grid = 40;
        for (int i = 0; i < grid; i++) {
            gBuffer.setColor(Color.LIGHT_GRAY);

            int col = (int) ((((float) size.width) / grid) * (i + 1));
            gBuffer.drawLine(col, 0, col, size.height);

            int lin = (int) ((((float) size.height) / grid) * (i + 1));
            gBuffer.drawLine(0, lin, size.width, lin);
        }

        gBuffer.setColor(Color.BLACK);
        gBuffer.drawRect(0, 0, size.width - 1, size.height - 1);

        if (ngrams != null) {
            int nelements = ngrams.size();
            float maxf = ngrams.get(0).getFrequency();
            float minf = ngrams.get(0).getFrequency();

            for (int i = 1; i < nelements; i++) {
                if (ngrams.get(i).getFrequency() > maxf) {
                    maxf = ngrams.get(i).getFrequency();
                } else if (ngrams.get(i).getFrequency() < minf) {
                    minf = ngrams.get(i).getFrequency();
                }
            }

            maxf = (float) Math.log(maxf);
            minf = (float) Math.log(minf);

            for (int i = 0; i < nelements - 1; i++) {
                int posx1 = (int) ((((float) i) / nelements) * (size.width - 40)) + 20;
                int posy1 = (int) ((((Math.log(ngrams.get(i).getFrequency()) - minf)) / (maxf - minf)) * (size.height - 40)) + 20;

                int posx2 = (int) ((((float) (i + 1)) / nelements) * (size.width - 40)) + 20;
                int posy2 = (int) ((((Math.log(ngrams.get(i + 1).getFrequency()) - minf)) / (maxf - minf)) * (size.height - 40)) + 20;

                gBuffer.setColor(Color.RED);
                gBuffer.drawLine(posx1, size.height - posy1, posx2, size.height - posy2);
            }

            int posL1 = (int) ((((float) upperLine) / nelements) * (size.width - 40)) + 20;
            int posL2 = (int) ((((float) lowerLine) / nelements) * (size.width - 40)) + 20;

            if ((posL2 - posL1) >= 0) {
                gBuffer.setColor(Color.BLUE);
                //gBuffer.drawLine(posL1, 20, posL1, size.height-10);
                //gBuffer.drawString("UPPER", posL1+5, 40);
                //gBuffer.drawLine(posL2, 20, posL2, size.height-10);
                //gBuffer.drawString("LOWER", posL2+5, size.height-20);

                Graphics2D g2 = (Graphics2D) gBuffer;
                gBuffer.drawRect(posL1, 20, Math.abs(posL2 - posL1), size.height - 30);
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.2f));
                g2.fill(new Rectangle(posL1, 20, Math.abs(posL2 - posL1), size.height - 30));
            } else {
                gBuffer.drawString("ERROR", size.width / 2, size.height / 2);
            }
        }
    }
}
