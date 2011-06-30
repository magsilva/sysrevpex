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

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;


/**
 * 
 * @author Fernando Vieira Paulovich
 */
public class SolverTest
{
	private static SolverFactory factory;
	
	private Solver<Float> solver;
	
	private Matrix<Float> matrixA;
	
	private Matrix<Float> matrixB;
	
	@BeforeClass
	public static void setUpTestSet()
	{
		factory = new SolverFactory();
	}
	
	@Test
	@Ignore
	public void testSolve_B2() {
		solver = factory.createSolver();
		matrixA = factory.createMatrix(7, 4);
		matrixB = factory.createMatrix(7, 2);

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

		// Configure solver
		solver.setMatrixA(matrixA);
		solver.setMatrixB(matrixB);

		// Solve
		Matrix<Float> result = solver.solve();
		for (int i = 0; i < result.getNumCol(); i++) {
			for (int j = 0; j < result.getNumRow(); j++) {
				System.out.print(result.get(i, j) + "\t");
			}
			System.out.println();
		}
	}

	@Test
	@Ignore
	public void testSolve_B3() {
		solver = factory.createSolver();
		matrixA = factory.createMatrix(7, 4);
		matrixB = factory.createMatrix(7, 3);

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

		// Configure solver
		solver.setMatrixA(matrixA);
		solver.setMatrixB(matrixB);

		// Solve
		Matrix<Float> result = solver.solve();
		for (int i = 0; i < result.getNumCol(); i++) {
			for (int j = 0; j < result.getNumRow(); j++) {
				System.out.print(result.get(i, j) + "\t");
			}
			System.out.println();
		}
	}

	@Test
	@Ignore
	public void testConnectivity() {
		solver = factory.createSolver();
		matrixA = factory.createMatrix(24, 15);
		matrixB = factory.createMatrix(24, 2);

		// creating matrix A
		for (int i = 0; i < 5; i++) {
			matrixA.set(i, i, 1.0f);
			if (i < 4) {
				matrixA.set(i, i + 1, -0.5f);
				matrixA.set(i + 1, i, -0.5f);
			}
		}
		matrixA.set(0, 4, -0.5f);
		matrixA.set(4, 0, -0.5f);

		for (int i = 5; i < 10; i++) {
			matrixA.set(i, i, 1.0f);
			if (i < 9) {
				matrixA.set(i, i + 1, -0.5f);
				matrixA.set(i + 1, i, -0.5f);
			}
		}
		matrixA.set(5, 9, -0.5f);
		matrixA.set(9, 5, -0.5f);

		for (int i = 10; i < 15; i++) {
			matrixA.set(i, i, 1.0f);
			if (i < 14) {
				matrixA.set(i, i + 1, -0.5f);
				matrixA.set(i + 1, i, -0.5f);
			}
		}
		matrixA.set(10, 14, -0.5f);
		matrixA.set(14, 10, -0.5f);

		matrixA.set(15, 0, 1.0f);
		matrixA.set(16, 2, 1.0f);
		matrixA.set(17, 4, 1.0f);
		matrixA.set(18, 5, 1.0f);
		matrixA.set(19, 7, 1.0f);
		matrixA.set(20, 9, 1.0f);
		matrixA.set(21, 10, 1.0f);
		matrixA.set(22, 12, 1.0f);
		matrixA.set(23, 14, 1.0f);

		// creating matrix B
		matrixB.set(15, 0, 0.0f);
		matrixB.set(15, 1, 0.0f);

		matrixB.set(16, 0, 1.0f);
		matrixB.set(16, 1, 0.0f);

		matrixB.set(17, 0, 0.5f);
		matrixB.set(17, 1, 0.5f);

		matrixB.set(18, 0, 5.0f);
		matrixB.set(18, 1, 0.0f);

		matrixB.set(19, 0, 6.0f);
		matrixB.set(19, 1, 0.0f);

		matrixB.set(20, 0, 5.5f);
		matrixB.set(20, 1, 0.5f);

		matrixB.set(21, 0, 5.0f);
		matrixB.set(21, 1, 5.0f);

		matrixB.set(22, 0, 6.0f);
		matrixB.set(22, 1, 5.0f);

		matrixB.set(23, 0, 5.5f);
		matrixB.set(23, 1, 5.5f);

		// Configure solver
		solver.setMatrixA(matrixA);
		solver.setMatrixB(matrixB);

		// Solve
		Matrix<Float> result = solver.solve();
		for (int i = 0; i < result.getNumCol(); i++) {
			for (int j = 0; j < result.getNumRow(); j++) {
				System.out.print(result.get(i, j) + "\t");
			}
			System.out.println();
		}
	}

	@Test
	@Ignore
	public void testWeight() {
		solver = factory.createSolver();
		matrixA = factory.createMatrix(7, 4);
		matrixB = factory.createMatrix(7, 2);

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

		// Configure solver
		solver.setMatrixA(matrixA);
		solver.setMatrixB(matrixB);

		// Solve
		Matrix<Float> result = solver.solve();
		for (int i = 0; i < result.getNumCol(); i++) {
			for (int j = 0; j < result.getNumRow(); j++) {
				System.out.print(result.get(i, j) + "\t");
			}
			System.out.println();
		}
		System.out.println();

		matrixA = factory.createMatrix(7, 4);
		matrixB = factory.createMatrix(7, 2);

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

		float w = 20.0f;

		matrixA.set(4, 0, w);
		matrixA.set(5, 1, w);
		matrixA.set(6, 2, w);

		// creating matrix B
		matrixB.set(4, 0, 1 * w);
		matrixB.set(4, 1, 1 * w);

		matrixB.set(5, 0, 1.5f * w);
		matrixB.set(5, 1, 2 * w);

		matrixB.set(6, 0, 2 * w);
		matrixB.set(6, 1, 1 * w);

		// Configure solver
		solver.setMatrixA(matrixA);
		solver.setMatrixB(matrixB);

		// Solve
		result = solver.solve();
		for (int i = 0; i < result.getNumCol(); i++) {
			for (int j = 0; j < result.getNumRow(); j++) {
				System.out.print(result.get(i, j) + "\t");
			}
			System.out.println();
		}
	}
}
