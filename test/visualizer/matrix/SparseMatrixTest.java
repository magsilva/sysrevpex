/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package visualizer.matrix;

import java.io.IOException;

import org.junit.Test;
import static org.junit.Assert.*;

import visualizer.projection.distance.CosineBased;
import visualizer.projection.distance.DistanceMatrix;
import visualizer.projection.distance.Dissimilarity;

/**
 *
 * @author Fernando Paulovich
 */
public class SparseMatrixTest {

    /**
     * Test of addRow method, of class SparseMatrix.
     */
	@Test
    public void testAddRow() {
        System.out.println("addRow");
        Vector vector = null;
        SparseMatrix instance = new SparseMatrix();
        instance.addRow(vector);
    }

    /**
     * Test of setRow method, of class SparseMatrix.
     */
	@Test
    public void testSetRow() {
        System.out.println("setRow");
        int index = 0;
        Vector vector = null;
        SparseMatrix instance = new SparseMatrix();
        instance.setRow(index, vector);
    }

    /**
     * Test of load method, of class SparseMatrix.
     */
	@Test
    public void testLoad() throws Exception {
        System.out.println("load");
        String filename = "";
        SparseMatrix instance = new SparseMatrix();
        instance.load(filename);
    }

    /**
     * Test of save method, of class SparseMatrix.
     */
	@Test
    public void testSave() throws Exception {
        System.out.println("save");
        String filename = "";
        SparseMatrix instance = new SparseMatrix();
        instance.save(filename);
    }

	@Test
    public void testEquality() throws IOException {
        System.out.println("equality between matrix and distance matrix");

        String dmatfilename = "/home/magsilva/Projects/LabES/SysRevPEx/test-resource/cbr-ilp-ir.dmat";
        DistanceMatrix dmat = new DistanceMatrix(dmatfilename);

        String matrixfilename = "/home/magsilva/Projects/LabES/SysRevPEx/test-resource/cbr-ilp-ir.data";
        Matrix matrix = MatrixFactory.getInstance(matrixfilename);

        Dissimilarity diss = new CosineBased();

        for (int i = 0; i < dmat.getElementCount(); i++) {
            for (int j = 0; j < dmat.getElementCount(); j++) {
                double dmatvalue = dmat.getDistance(i, j);
                double matrixvalue = diss.calculate(matrix.getRow(i), matrix.getRow(j));

                assertEquals(dmatvalue, matrixvalue, 0.00001f);
            }
        }
    }
}
