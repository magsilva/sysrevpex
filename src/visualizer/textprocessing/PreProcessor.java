package visualizer.textprocessing;

import java.util.Collection;

import visualizer.corpus.Corpus;
import visualizer.graph.Vertex;
import visualizer.matrix.Matrix;

public interface PreProcessor
{
	void run();
	
	Matrix getMatrix();
	
	Matrix getMatrixForSelection(Collection<Vertex> selectedVertex);
	
	Collection<Ngram> getNgrams();
	
	void setCorpus(Corpus corpus);
	
	Corpus getCorpus();
}
