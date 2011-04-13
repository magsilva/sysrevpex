package visualizer.textprocessing;

import java.util.Collection;
import java.util.List;

import visualizer.corpus.Corpus;
import visualizer.graph.Vertex;
import visualizer.matrix.Matrix;

public interface PreProcessor
{
	void run();
	
	Matrix getMatrix();
	
	Matrix getMatrixForSelection(Collection<Vertex> selectedVertex);
	
	List<Ngram> getNgrams();
	
	void setCorpus(Corpus corpus);
	
	Corpus getCorpus();
}
