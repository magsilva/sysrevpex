package visualizer.util.solver;


public interface Solver<T>
{
	Matrix<T> getMatrixA();

	void setMatrixA(Matrix<T> matrix);

	Matrix<T> getMatrixB();

	void setMatrixB(Matrix<T> matrix);

	Matrix<T> solve();
}
