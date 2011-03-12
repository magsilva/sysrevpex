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

package visualizer.util.solver;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import visualizer.util.solver.Matrix;
import visualizer.util.solver.Solver;

/**
 * 
 * @author Fernando Vieira Paulovich
 */
public class SolverTest {
	public void testSolve_B2() {
		Solver<Float> solver = new Solver<Float>();
		Matrix<Float> matrixA = new Matrix<Float>(7, 4);
		Matrix<Float> matrixB = new Matrix<Float>(7, 2);

		// creating matrix A
		matrixA.set(0, 0, 1.0f);
		matrixA.set(0, 1, -1.0f / 3.0f);
		matrixA.set(0, 2, -1.0f / 3.0f);
		matrixA.set(0, 3, -1.0f / 3.0f);

		matrixA.set(1, 0, -1.0f / 3.0f);
		matrixA.set(1, 1, 1.0f);
		matrixA.set(1, 2, -1.0f / 3.0f);
		matrixA.set(1, 3, -1.0f / 3.0f);

		matrixA.set(2, 0, -1.0f / 3.0f);
		matrixA.set(2, 1, -1.0f / 3.0f);
		matrixA.set(2, 2, 1.0f);
		matrixA.set(2, 3, -1.0f / 3.0f);

		matrixA.set(3, 0, -1.0f / 3.0f);
		matrixA.set(3, 1, -1.0f / 3.0f);
		matrixA.set(3, 2, -1.0f / 3.0f);
		matrixA.set(3, 3, 1.0f);

		matrixA.set(4, 0, 1.0f);
		matrixA.set(5, 1, 1.0f);
		matrixA.set(6, 2, 1.0f);

		// creating matrix B
		matrixB.set(4, 0, 1.0f);
		matrixB.set(4, 1, 1.0f);

		matrixB.set(5, 0, 1.5f);
		matrixB.set(5, 1, 2.0f);

		matrixB.set(6, 0, 2.0f);
		matrixB.set(6, 1, 1.0f);

		Matrix<Float> result = solver.solve();

		for (int i = 0; i < result.getNumCol(); i++) {
			for (int j = 0; j < result.getNumRow(); j++) {
				System.out.print(result.get(i, j) + "\t");
			}
			System.out.println();
		}
	}

	public void testSolve_B3() {
		Solver<Float> solver = new Solver<Float>();
		Matrix<Float> matrixA = new Matrix<Float>(7, 4);
		Matrix<Float> matrixB = new Matrix<Float>(7, 3);

		// creating matrix A
		matrixA.set(0, 0, 1.0f);
		matrixA.set(0, 1, -1.0f / 3.0f);
		matrixA.set(0, 2, -1.0f / 3.0f);
		matrixA.set(0, 3, -1.0f / 3.0f);

		matrixA.set(1, 0, -1.0f / 3.0f);
		matrixA.set(1, 1, 1.0f);
		matrixA.set(1, 2, -1.0f / 3.0f);
		matrixA.set(1, 3, -1.0f / 3.0f);

		matrixA.set(2, 0, -1.0f / 3.0f);
		matrixA.set(2, 1, -1.0f / 3.0f);
		matrixA.set(2, 2, 1.0f);
		matrixA.set(2, 3, -1.0f / 3.0f);

		matrixA.set(3, 0, -1.0f / 3.0f);
		matrixA.set(3, 1, -1.0f / 3.0f);
		matrixA.set(3, 2, -1.0f / 3.0f);
		matrixA.set(3, 3, 1.0f);

		matrixA.set(4, 0, 1.0f);
		matrixA.set(5, 1, 1.0f);
		matrixA.set(6, 2, 1.0f);

		// creating matrix B
		matrixB.set(4, 0, 1.0f);
		matrixB.set(4, 1, 1.0f);
		matrixB.set(4, 2, 1.0f);

		matrixB.set(5, 0, 1.5f);
		matrixB.set(5, 1, 2.0f);
		matrixB.set(5, 2, 2.0f);

		matrixB.set(6, 0, 2.0f);
		matrixB.set(6, 1, 1.0f);
		matrixB.set(6, 2, 1.0f);

		Matrix<Float> result = solver.solve();

		for (int i = 0; i < result.getNumCol(); i++) {
			for (int j = 0; j < result.getNumRow(); j++) {
				System.out.print(result.get(i, j) + "\t");
			}
			System.out.println();
		}
	}

	public void testConnectivity() {
		Solver solver = new Solver(24, 15);

		// creating matrix A
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

		// creating matrix B
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
	}

	public void testWeight() {
		Solver solver = new Solver(7, 4);

		// creating matrix A
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

		// creating matrix B
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

		System.out.println();

		Solver solver = new Solver(7, 4);

		// creating matrix A
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

		// creating matrix B
		solver.addToB(4, 0, 1 * w);
		solver.addToB(4, 1, 1 * w);

		solver.addToB(5, 0, 1.5f * w);
		solver.addToB(5, 1, 2 * w);

		solver.addToB(6, 0, 2 * w);
		solver.addToB(6, 1, 1 * w);

		float[] result = solver.solve();

		for (int i = 0; i < result.length; i += 2) {
			System.out.println(result[i] + " " + result[i + 1]);
		}
	}
}
