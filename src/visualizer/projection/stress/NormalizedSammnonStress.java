/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package visualizer.projection.stress;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import visualizer.graph.Graph;
import visualizer.matrix.DenseMatrix;
import visualizer.matrix.DenseVector;
import visualizer.matrix.Matrix;
import visualizer.matrix.MatrixFactory;
import visualizer.projection.distance.CosineBased;
import visualizer.projection.distance.Dissimilarity;
import visualizer.projection.distance.DistanceMatrix;
import visualizer.projection.distance.Euclidean;
import visualizer.projection.distance.LightWeightDistanceMatrix;
import visualizer.util.Util;

/**
 *
 * @author Fernando
 */
public class NormalizedSammnonStress extends Stress {

    @Override
    public double calculate(Matrix matrix, Dissimilarity diss, Matrix projection) throws IOException {
        LightWeightDistanceMatrix dmat = new LightWeightDistanceMatrix(matrix, diss);
        return this.calculate(dmat, projection);
    }

    @Override
    public double calculate(DistanceMatrix dmat, Matrix projection) throws IOException {
        LightWeightDistanceMatrix dmatprj = new LightWeightDistanceMatrix(projection, new Euclidean());

        double maxrn = Float.MIN_VALUE;
        double maxr2 = Float.MIN_VALUE;

        for (int i = 0; i < dmat.getElementCount(); i++) {
            for (int j = i + 1; j < dmat.getElementCount(); j++) {
                double valuern = dmat.getDistance(i, j);
                double valuer2 = dmatprj.getDistance(i, j);

                if (valuern > maxrn) {
                    maxrn = valuern;
                }

                if (valuer2 > maxr2) {
                    maxr2 = valuer2;
                }
            }
        }

        double num = 0.0f;
        for (int i = 0; i < dmat.getElementCount(); i++) {
            for (int j = i + 1; j < dmat.getElementCount(); j++) {
                double distrn = dmat.getDistance(i, j) / maxrn;
                double distr2 = dmatprj.getDistance(i, j) / maxr2;
                num += ((distrn - distr2) * (distrn - distr2)) /(distrn * distrn);
            }
        }

        double den = 0.0f;
        for (int i = 0; i < dmat.getElementCount(); i++) {
            for (int j = i + 1; j < dmat.getElementCount(); j++) {
                double distrn = dmat.getDistance(i, j) / maxrn;
                den += distrn * distrn;
            }
        }

        return num / den;
    }

    @Override
    public double calculate(Matrix matrix, Dissimilarity diss, Graph graph) throws IOException {
        LightWeightDistanceMatrix dmat = new LightWeightDistanceMatrix(matrix, diss);
        return this.calculate(dmat, graph);
    }

    @Override
    public double calculate(DistanceMatrix dmat, Graph graph) throws IOException {
        DenseMatrix projection = new DenseMatrix();

        for (int i = 0; i < graph.getVertex().size(); i++) {
            double[] vect = new double[2];
            vect[0] = graph.getVertex().get(i).getX();
            vect[1] = graph.getVertex().get(i).getY();
            projection.addRow(new DenseVector(vect));
        }

        return calculate(dmat, projection);
    }

    public static void main(String[] args) {
        try {
            Util.log(true, false);

            if (args.length != 3) {
                System.out.println("Usage: java NormalizedSammnoStress <points> <projection> <dissimilarity>");
                System.out.println("   ex: java NormalizedSammnoStress points.data projection.data cosine");
            }

            String pointsfilename = args[0];
            Matrix points = MatrixFactory.getInstance(pointsfilename);

            String projfilename = args[1];
            Matrix projection = MatrixFactory.getInstance(projfilename);

            Dissimilarity diss = null;

            if (args[2].trim().toLowerCase().equals("cosine")) {
                diss = new CosineBased();
            } else {
                diss = new Euclidean();
            }

            NormalizedSammnonStress stress = new NormalizedSammnonStress();
            double value = stress.calculate(points, diss, projection);

            System.out.println("---");
            System.out.println("Points: " + pointsfilename);
            System.out.println("Projection: " + projfilename);
            System.out.println("Normalized Sammnon Stress stress: " + value);
            System.out.println("---");

        } catch (IOException ex) {
            Logger.getLogger(NormalizedSammnonStress.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
