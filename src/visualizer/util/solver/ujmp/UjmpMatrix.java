package visualizer.util.solver.ujmp;

import org.ujmp.core.enums.ValueType;

import visualizer.util.solver.Matrix;
import visualizer.util.solver.MatrixType;
import visualizer.util.solver.MatrixValueType;

public abstract class UjmpMatrix<T> implements Matrix<T>
{
	private MatrixType matrixType;
	
	private ValueType matrixValueType;

	protected org.ujmp.core.Matrix ujmpMatrix;
	
	protected UjmpMatrix(MatrixValueType matrixValueType, MatrixType matrixType, long numRows, long numColumns)
	{
		// This is a hack, as it must always be the same value as the generics type.
		switch (matrixValueType) {
			case INT:
				this.matrixValueType = ValueType.INT;
				break;
			case FLOAT:
				this.matrixValueType = ValueType.FLOAT;
				break;
			default:
				this.matrixValueType = ValueType.FLOAT;
		}
		
		this.matrixType = matrixType;
		switch (this.matrixType) {
			case SPARSE:
				ujmpMatrix = org.ujmp.core.MatrixFactory.sparse(this.matrixValueType, numRows, numColumns);
				break;
			case DENSE:
				ujmpMatrix = org.ujmp.core.MatrixFactory.dense(this.matrixValueType, numRows, numColumns);
				break;
			default:
				ujmpMatrix = org.ujmp.core.MatrixFactory.dense(this.matrixValueType, numRows, numColumns);

		}
	}
	
	protected UjmpMatrix(org.ujmp.core.Matrix matrix)
	{
		// This is a hack, as it must always be the same value as the generics type.
		switch (matrix.getValueType()) {
			case INT:
				this.matrixValueType = ValueType.INT;
				break;
			case FLOAT:
				this.matrixValueType = ValueType.FLOAT;
				break;
			default:
				this.matrixValueType = ValueType.FLOAT;
		}

		switch (matrix.getStorageType()) {
			case DENSE:
				this.matrixType = MatrixType.DENSE;
				break;
			case SPARSE:
				this.matrixType = MatrixType.SPARSE;
				break;
			default:
				throw new IllegalArgumentException("Unsupported matrix type: " + matrix.getStorageType().toString());
		}

		ujmpMatrix = matrix;
	}
	
	@Override
	public long getNumCol()
	{
		return ujmpMatrix.getColumnCount();
	}

	@Override
	public long getNumRow()
	{
		return ujmpMatrix.getRowCount();
	}

	@Override
	public Object getRealImplementation()
	{
		return ujmpMatrix;
	}
}
