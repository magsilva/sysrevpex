/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package visualizer.projection.distance;

import visualizer.matrix.DenseVector;
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

        if(v1 instanceof SparseVector) {
            a = v1.toArray();
        }

        if(v2 instanceof SparseVector) {
            b = v2.toArray();
        }

        //creating the matrix
        double[][] g = new double[a.length][b.length];

        //initial condition
        g[0][0] = Math.sqrt((a[0] - b[0]) * (a[0] - b[0]));

        int r = a.length - 1;

        //calculate the first row
        for (int j = 1; j <= r; j++) {
            g[0][j] = g[0][j - 1] + Math.sqrt((a[0] - b[j]) * (a[0] - b[j]));
        }

        //calculate the fist column
        for (int i = 1; i <= r; i++) {
            g[i][0] = g[i - 1][0] + Math.sqrt((a[i] - b[0]) * (a[i] - b[0]));
        }

        //calculate the remaining values
        for (int j = 1; j < a.length; j++) {
            int min = Math.max(1, j - r);
            int max = Math.min(j + r, b.length-1);

            for (int i = min; i <= max; i++) {
                g[j][i] = Math.min(Math.min(g[j - 1][i], g[j - 1][i - 1]), g[j][i - 1]) + Math.sqrt((a[j] - b[i]) * (a[j] - b[i]));
            }
        }

        return (float)g[a.length - 1][b.length - 1];
    }
    
    public static void main(String[] args) {
        DenseVector v1 = new DenseVector(new float[]{1,2,3,4,5});
        DenseVector v2 = new DenseVector(new float[]{4,5,7,8,5});

        DynamicTimeWarping dtw = new DynamicTimeWarping();
        float res = dtw.calculate(v1, v2);
        System.out.println(res);
    }
}
