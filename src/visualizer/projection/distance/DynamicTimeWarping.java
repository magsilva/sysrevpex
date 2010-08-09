/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package visualizer.projection.distance;

import visualizer.matrix.SparseVector;
import visualizer.matrix.Vector;

/**
 *
 * @author paulovich
 */
public class DynamicTimeWarping implements Dissimilarity {

    @Override
    public float calculate(Vector v1, Vector v2) {
        assert (v1.size() == v2.size()) : "ERROR: vectors of different sizes!";

        float[] a = v1.getValues();
        float[] b = v2.getValues();

        if (v1 instanceof SparseVector) {
            a = v1.toArray();
        }

        if (v2 instanceof SparseVector) {
            b = v2.toArray();
        }

        //creating the matrix
        double[][] g = new double[a.length][b.length];

        //initial condition
        g[0][0] = Math.sqrt((a[0] - b[0]) * (a[0] - b[0]));

        int r = (int) ((a.length - 1) * WWINDOW);

        //filling the diagonals
        for (int i = r + 1, j = 0; i < b.length; i++, j++) {
            g[j][i] = g[i][j] = Double.POSITIVE_INFINITY;
        }

        //calculate the first row
        for (int j = 1; j <= r; j++) {
            double diff = a[j] - b[0];
            g[0][j] = g[0][j - 1] + Math.sqrt(diff * diff);
        }

        //calculate the fist column
        for (int i = 1; i <= r; i++) {
            double diff = a[0] - b[i];
            g[i][0] = g[i - 1][0] + Math.sqrt(diff * diff);
        }

        //calculate the remaining values
        for (int i = 1; i < b.length; i++) {
            int min = Math.max(1, i - r);
            int max = Math.min(a.length - 1, i + r);

            for (int j = min; j <= max; j++) {
                double diff = a[j] - b[i];
                g[i][j] = Math.min(Math.min(g[i - 1][j], g[i - 1][j - 1]), g[i][j - 1]) + Math.sqrt(diff * diff);
            }
        }        

        return (float) g[a.length - 1][b.length - 1];
    }

    private static final float WWINDOW = 0.25f;
}
