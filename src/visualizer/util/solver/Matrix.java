package visualizer.util.solver;

public interface Matrix<T>
{
	T get(long col, long row);

	void set(long col, long row, T value);

	long getNumCol();

	long getNumRow();
	
	Object getRealImplementation();
}
