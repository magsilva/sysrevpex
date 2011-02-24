package visualizer.util.solver;

public class Matrix<T>
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

	public Matrix(int numcol, int numrow)
	{
		setNumCol(numcol);
		setNumRow(numrow);
	}
	
	public T get(int col, int row)
	{
		return null;
	}
	
	public void set(int col, int row, T value)
	{
	}
	
	public int getNumCol()
	{
		return numcol;
	}
	
	public int getNumRow()
	{
		return numrow;
	}
}
