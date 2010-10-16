/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lspsolver;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fernando
 */
public class TestConnectivity {

    public static void main(String args[]) {
        try {
            Solver solver = new Solver(24, 15);

            //creating matrix A
            for (int i = 0; i < 5; i++) {
                solver.addToA(i, i, 1);

                if (i < 4) {
                    solver.addToA(i, i + 1, -0.5f);
                    solver.addToA(i + 1, i, -0.5f);
                }
            }

            solver.addToA(0, 4, -0.5f);
            solver.addToA(4, 0, -0.5f);

            for (int i = 5; i < 10; i++) {
                solver.addToA(i, i, 1);

                if (i < 9) {
                    solver.addToA(i, i + 1, -0.5f);
                    solver.addToA(i + 1, i, -0.5f);
                }
            }

            solver.addToA(5, 9, -0.5f);
            solver.addToA(9, 5, -0.5f);
            
            for (int i = 10; i < 15; i++) {
                solver.addToA(i, i, 1);

                if (i < 14) {
                    solver.addToA(i, i + 1, -0.5f);
                    solver.addToA(i + 1, i, -0.5f);
                }
            }

            solver.addToA(10, 14, -0.5f);
            solver.addToA(14, 10, -0.5f);            

            solver.addToA(15, 0, 1);
            solver.addToA(16, 2, 1);
            solver.addToA(17, 4, 1);
            solver.addToA(18, 5, 1);
            solver.addToA(19, 7, 1);
            solver.addToA(20, 9, 1);
            solver.addToA(21, 10, 1);
            solver.addToA(22, 12, 1);
            solver.addToA(23, 14, 1);            

            //creating matrix B
            solver.addToB(15, 0, 0);
            solver.addToB(15, 1, 0);

            solver.addToB(16, 0, 1);
            solver.addToB(16, 1, 0);

            solver.addToB(17, 0, 0.5f);
            solver.addToB(17, 1, 0.5f);

            solver.addToB(18, 0, 5);
            solver.addToB(18, 1, 0);

            solver.addToB(19, 0, 6);
            solver.addToB(19, 1, 0);

            solver.addToB(20, 0, 5.5f);
            solver.addToB(20, 1, 0.5f);

            solver.addToB(21, 0, 5);
            solver.addToB(21, 1, 5);

            solver.addToB(22, 0, 6);
            solver.addToB(22, 1, 5);

            solver.addToB(23, 0, 5.5f);
            solver.addToB(23, 1, 5.5f);

            float[] result = solver.solve();

            for (int i = 0; i < result.length; i += 2) {
                System.out.println(result[i] + " " + result[i + 1]);
            }
        } catch (IOException ex) {
            Logger.getLogger(TestConnectivity.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
