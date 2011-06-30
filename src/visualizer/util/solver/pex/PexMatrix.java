package visualizer.util.solver.pex;

import visualizer.util.solver.Matrix;

public class PexMatrix<T> implements Matrix<T>
{
	private int numcol;
	
	private int numrow;
	
	private void setNumCol(int numcol)
	{
		this.numcol = numcol;
	}

	private void setNumRow(int numrow)
	{
		this.numrow = numrow;
	}

	public PexMatrix(int numcol, int numrow)
	{
		setNumCol(numcol);
		setNumRow(numrow);
	}
	
	/* (non-Javadoc)
	 * @see visualizer.util.solver.PexSolver.Matrix#getNumCol()
	 */
	@Override
	public long getNumCol()
	{
		return numcol;
	}
	
	/* (non-Javadoc)
	 * @see visualizer.util.solver.PexSolver.Matrix#getNumRow()
	 */
	@Override
	public long getNumRow()
	{
		return numrow;
	}

	@Override
	public T get(long col, long row)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void set(long col, long row, T value)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getRealImplementation()
	{
		// TODO Auto-generated method stub
		return null;
	}
}
