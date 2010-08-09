/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package visualizer.projection.distance;

import java.io.IOException;
import visualizer.matrix.DenseVector;
import visualizer.matrix.Matrix;
import visualizer.matrix.MatrixFactory;
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

        //calculate the first row
        for (int j = 1; j <= r + 1; j++) {
            g[0][j] = g[0][j - 1] + Math.sqrt((a[j] - b[0]) * (a[j] - b[0]));
        }

        //calculate the fist column
        for (int i = 1; i <= r + 1; i++) {
            g[i][0] = g[i - 1][0] + Math.sqrt((a[0] - b[i]) * (a[0] - b[i]));
        }

        //calculate the lower diagonal
        for (int i = r + 2, j = 1; i < b.length; i++, j++) {
            g[i][j] = g[i - 1][j - 1] + Math.sqrt((a[j] - b[i]) * (a[j] - b[i]));
        }

        //calculate the upper diagonal
        for (int i = 1, j = r + 2; j < a.length; i++, j++) {
            g[i][j] = g[i - 1][j - 1] + Math.sqrt((a[j] - b[i]) * (a[j] - b[i]));
        }        

        //calculate the remaining values
        for (int i = 1; i < b.length; i++) {
            int min = Math.max(1, i - r);
            int max = Math.min(i + r, a.length - 1);

            for (int j = min; j <= max; j++) {
                g[i][j] = Math.min(Math.min(g[i - 1][j], g[i - 1][j - 1]), g[i][j - 1]) + Math.sqrt((a[j] - b[i]) * (a[j] - b[i]));
            }
        }

        return (float) g[a.length - 1][b.length - 1];
    }
    
    private static final float WWINDOW = 0.25f;

    public static void main(String[] args) throws IOException {
        Matrix matrix = MatrixFactory.getInstance("D:/My Dropbox/artigos/Case-Prudente/single-molecule/novoArquivo02.data");
        DistanceMatrix dmat = new DistanceMatrix(matrix, new DynamicTimeWarping());

//        DenseVector v1 = new DenseVector(new float[]{4,6,2,4,2,6,7,8});
//        DenseVector v2 = new DenseVector(new float[]{4,5,7,8,4,1,3,3});
//
//        DynamicTimeWarping dtw = new DynamicTimeWarping();
//        float res = dtw.calculate(v1, v2);
//        System.out.println(res);
    }
}
