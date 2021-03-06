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

    public double calculate(Vector v1, Vector v2) {
        assert (v1.size() == v2.size()) : "ERROR: vectors of different sizes!";

        double[] a = v1.getValues();
        double[] b = v2.getValues();

        if (v1 instanceof SparseVector) {
            a = v1.toArray();
        }

        if (v2 instanceof SparseVector) {
            b = v2.toArray();
        }

        //creating the matrix
        if (g == null || g.length < a.length) {
            g = new double[a.length][b.length];
        }

        //initial condition
        g[0][0] = dist(a[0], b[0]);

        int r = (int) ((a.length - 1) * WWINDOW);

        //filling the diagonals
        for (int i = r + 1, j = 0; i < b.length; i++, j++) {
            g[j][i] = g[i][j] = Float.POSITIVE_INFINITY;
        }

        //calculate the first row and column
        for (int i = 1; i <= r; i++) {
            g[0][i] = g[0][i - 1] + dist(a[i], b[0]);
            g[i][0] = g[i - 1][0] + dist(a[0], b[i]);
        }

        //calculate the remaining values
        for (int i = 1; i < b.length; i++) {
            int min = Math.max(1, i - r);
            int max = Math.min(a.length - 1, i + r);

            for (int j = min; j <= max; j++) {
                g[i][j] = Math.min(Math.min(g[i - 1][j], g[i - 1][j - 1]), g[i][j - 1]) + dist(a[j], b[i]);
            }
        }

        return (double) g[a.length - 1][b.length - 1];
    }

    private double dist(double a, double b) {
        double diff = a - b;
        return (double)Math.sqrt(diff * diff);
    }
    
    private static double[][] g;
    private static final double WWINDOW = 0.25f;
}
