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
public class TestWeight {

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

            float w = 20.0f;

            solver.addToA(4, 0, w);
            solver.addToA(5, 1, w);
            solver.addToA(6, 2, w);

            //creating matrix B
            solver.addToB(4, 0, 1*w);
            solver.addToB(4, 1, 1*w);

            solver.addToB(5, 0, 1.5f*w);
            solver.addToB(5, 1, 2*w);

            solver.addToB(6, 0, 2*w);
            solver.addToB(6, 1, 1*w);

            float[] result = solver.solve();

            for (int i = 0; i < result.length; i += 2) {
                System.out.println(result[i] + " " + result[i + 1]);
            }
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
