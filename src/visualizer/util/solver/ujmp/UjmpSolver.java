package visualizer.util.solver.ujmp;

import org.ujmp.core.doublematrix.calculation.general.decomposition.Solve;

import visualizer.util.solver.Matrix;
import visualizer.util.solver.Solver;

public class UjmpSolver<T> implements Solver<T>
{
	private Solve<org.ujmp.core.Matrix> solver = Solve.MATRIXSQUARELARGEMULTITHREADED;

	private Matrix<T> matrixA;

	private Matrix<T> matrixB;

	@Override
	public Matrix<T> getMatrixA()
	{
		return matrixA;
	}

	@Override
	public void setMatrixA(Matrix<T> matrix)
	{
		this.matrixA = matrix;
	}

	@Override
	public Matrix<T> getMatrixB()
	{
		return matrixB;
	}

	@Override
	public void setMatrixB(Matrix<T> matrix)
	{
		this.matrixB = matrix;
	}

	@Override
	public Matrix<T> solve()
	{
		org.ujmp.core.Matrix result = solver.calc( (org.ujmp.core.Matrix) matrixA.getRealImplementation(), (org.ujmp.core.Matrix) matrixB.getRealImplementation());
		return (Matrix<T>) new UjmpMatrixFloat(result);
	}

}
