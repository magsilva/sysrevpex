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

import java.io.BufferedReader;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.StringTokenizer;

import org.junit.Ignore;
import org.junit.Test;

/**
 * 
 * @author Fernando Vieira Paulovich
 */
public class SolverMemoryUsageTest
{
	@Ignore
	@Test
	public void testMemoryUsage() throws IOException
	{
		BufferedReader in = null;
		String filename = "lsp.tmp";

		in = new BufferedReader(new java.io.FileReader(filename));
		String line = null;

		int lin = Integer.parseInt(in.readLine());
		int col = Integer.parseInt(in.readLine());

		SolverFactory factory = new SolverFactory();
		Solver solver = factory.createSolver();
		Matrix<Float> matrixA = factory.createMatrix(col, lin);
		Matrix<Float> matrixB = factory.createMatrix(col, lin);

		// creating matrix A
		while ((line = in.readLine()) != null && !line.trim().equals("-1 -1 -1")) {
			StringTokenizer t = new StringTokenizer(line, " \t");
			int i = Integer.parseInt(t.nextToken());
			int j = Integer.parseInt(t.nextToken());
			float value = Float.parseFloat(t.nextToken());

			matrixA.set(i, j, value);
		}

		in.readLine();
		in.readLine();

		// creating matrix B
		while ((line = in.readLine()) != null) {
			StringTokenizer t = new StringTokenizer(line, " \t");
			int i = Integer.parseInt(t.nextToken());
			int j = Integer.parseInt(t.nextToken());
			float value = Float.parseFloat(t.nextToken());

			matrixB.set(i, j, value);
		}

		solver.setMatrixA(matrixA);
		solver.setMatrixB(matrixB);
		Runtime r = Runtime.getRuntime();
		double usedMemory = (r.totalMemory() / 1024.0 / 1024.0)	- (r.freeMemory() / 1024.0 / 1024.0);
		NumberFormat form = NumberFormat.getInstance();
		form.setMaximumFractionDigits(2);
		form.setMinimumFractionDigits(2);
		System.out.println(form.format(usedMemory) + " MB");

		Matrix<Float> result = solver.solve();
		usedMemory = (r.totalMemory() / 1024.0 / 1024.0) - (r.freeMemory() / 1024.0 / 1024.0);
		System.out.println(form.format(usedMemory) + " MB");
		for (int i = 0; i < result.getNumCol(); i++) {
			for (int j = 0; j < result.getNumRow(); j++) {
				System.out.print(result.get(i, j) + "\t");
			}
			System.out.println();
		}
	}

}
