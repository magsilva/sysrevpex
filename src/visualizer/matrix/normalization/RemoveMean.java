/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package visualizer.matrix.normalization;

import java.io.IOException;
import visualizer.matrix.Matrix;

/**
 *
 * @author Fernando
 */
public class RemoveMean extends Normalization {

    @Override
    public Matrix execute(Matrix matrix) throws IOException {
        assert (matrix.getRowCount() > 0) : "More than zero vectors must be used!";

        for (int i = 0; i < matrix.getRowCount(); i++) {
            double[] vect = matrix.getRow(i).getValues();

            //calculate the mean
            double mean = 0;
            for (int j = 0; j < vect.length; j++) {
                mean = mean + vect[j];
            }
            mean /= vect.length;

            //extracting the mean
            for (int j = 0; j < vect.length; j++) {
                vect[j] = vect[j] - mean;
            }
        }

        return matrix;
    }
}
