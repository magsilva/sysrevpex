/* ***** BEGIN LICENSE BLOCK *****
 *
 * Copyright (c) 2005-2007 Universidade de Sao Paulo, Sao Carlos/SP, Brazil.
 * All Rights Reserved.
 *
 * This file is part of LSP-Solver.
 *
 * How to cite this work:
 *  
 * LSP-Solver is free software: you can redistribute it and/or modify it under 
 * the terms of the GNU General Public License as published by the Free 
 * Software Foundation, either version 3 of the License, or (at your option) 
 * any later version.
 *
 * LSP-Solver is distributed in the hope that it will be useful, but WITHOUT 
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
 * with LSP-Solver. If not, see <http://www.gnu.org/licenses/>.
 *
 * ***** END LICENSE BLOCK ***** */

package lspsolver;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class Main {

    public static void main(String args[]) {
        try {
            Solver solver = new Solver(7, 4);

            //creating matrix A
            solver.addToA(0, 0, 1);
            solver.addToA(0, 1, -1.0f / 3.0f);
            solver.addToA(0, 2, -1.0f / 3.0f);
            solver.addToA(0, 3, -1.0f / 3.0f);

            solver.addToA(1, 0, -1.0f / 3.0f);
            solver.addToA(1, 1, 1);
            solver.addToA(1, 2, -1.0f / 3.0f);
            solver.addToA(1, 3, -1.0f / 3.0f);

            solver.addToA(2, 0, -1.0f / 3.0f);
            solver.addToA(2, 1, -1.0f / 3.0f);
            solver.addToA(2, 2, 1);
            solver.addToA(2, 3, -1.0f / 3.0f);

            solver.addToA(3, 0, -1.0f / 3.0f);
            solver.addToA(3, 1, -1.0f / 3.0f);
            solver.addToA(3, 2, -1.0f / 3.0f);
            solver.addToA(3, 3, 1);

            solver.addToA(4, 0, 1);
            solver.addToA(5, 1, 1);
            solver.addToA(6, 2, 1);

            //creating matrix B
            solver.addToB(4, 0, 1);
            solver.addToB(4, 1, 1);

            solver.addToB(5, 0, 1.5f);
            solver.addToB(5, 1, 2);

            solver.addToB(6, 0, 2);
            solver.addToB(6, 1, 1);

            float[] result = solver.solve();

            for (int i = 0; i < result.length; i += 2) {
                System.out.println(result[i] + " " + result[i + 1]);
            }
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println();
        
        
        try {
            Solver solver = new Solver(7, 4);

            //creating matrix A
            solver.addToA(0, 0, 1);
            solver.addToA(0, 1, -1.0f / 3.0f);
            solver.addToA(0, 2, -1.0f / 3.0f);
            solver.addToA(0, 3, -1.0f / 3.0f);

            solver.addToA(1, 0, -1.0f / 3.0f);
            solver.addToA(1, 1, 1);
            solver.addToA(1, 2, -1.0f / 3.0f);
            solver.addToA(1, 3, -1.0f / 3.0f);

            solver.addToA(2, 0, -1.0f / 3.0f);
            solver.addToA(2, 1, -1.0f / 3.0f);
            solver.addToA(2, 2, 1);
            solver.addToA(2, 3, -1.0f / 3.0f);

            solver.addToA(3, 0, -1.0f / 3.0f);
            solver.addToA(3, 1, -1.0f / 3.0f);
            solver.addToA(3, 2, -1.0f / 3.0f);
            solver.addToA(3, 3, 1);

            solver.addToA(4, 0, 1);
            solver.addToA(5, 1, 1);
            solver.addToA(6, 2, 1);

            //creating matrix B
            solver.addToB(4, 0, 1);
            solver.addToB(4, 1, 1);
            solver.addToB(4, 2, 1);

            solver.addToB(5, 0, 1.5f);
            solver.addToB(5, 1, 2);
            solver.addToB(5, 2, 2);

            solver.addToB(6, 0, 2);
            solver.addToB(6, 1, 1);
            solver.addToB(6, 2, 1);

            float[] result = solver.solve();
            
            for (int i = 0; i < result.length; i += 3) {
                System.out.println(result[i] + " " + result[i + 1] + " " + result[i + 2]);
            }
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
