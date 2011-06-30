package visualizer.util.solver;

import visualizer.util.solver.ujmp.UjmpMatrixFloat;
import visualizer.util.solver.ujmp.UjmpSolver;

public class SolverFactory
{
	private String implementationAffinity;
	
	private boolean preferSpeed;
	
	private boolean preferMemory;
	
	private MatrixType prefferedMatrixType;
	
	public MatrixType getPrefferedMatrixType()
	{
		return prefferedMatrixType;
	}

	public void setPrefferedMatrixType(MatrixType prefferedMatrixType)
	{
		this.prefferedMatrixType = prefferedMatrixType;
	}

	public String getImplementationAffinity()
	{
		return implementationAffinity;
	}

	public void setImplementationAffinity(String implementationAffinity)
	{
		this.implementationAffinity = implementationAffinity;
	}

	public boolean preferSpeed()
	{
		return preferSpeed;
	}

	public void setPreferSpeed(boolean preferSpeed)
	{
		this.preferSpeed = preferSpeed;
	}

	public boolean preferMemory()
	{
		return preferMemory;
	}

	public void setPreferMemory(boolean preferMemory)
	{
		this.preferMemory = preferMemory;
	}

	public Solver createSolver()
	{
		Solver<Float> solver = new UjmpSolver<Float>();
		return solver;
	}

	public Matrix createMatrix(int numCols, int numRows)
	{
		return createMatrix(numCols, numRows, prefferedMatrixType);
	}
	
	public Matrix createMatrix(int numCols, int numRows, MatrixType type)
	{
		Matrix<Float> matrix = new UjmpMatrixFloat(type, numCols, numRows);
		return matrix;
	}
}
