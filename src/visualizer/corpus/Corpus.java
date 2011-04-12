package visualizer.corpus;

import java.io.IOException;
import java.util.ArrayList;

import visualizer.textprocessing.Ngram;

public interface Corpus
{
	/**
	 * Return the raw file content
	 * @param id The file id
	 * @return File content
	 * @throws IOException 
	 */
	public abstract String getFullContent(String id) throws IOException;

	/**
	 * Return the modified file content - Custom for viewing
	 * @param id The file id
	 * @return File content
	 * @throws IOException 
	 */
	public abstract String getViewContent(String id) throws IOException;

	/**
	 * Return the ngrams associated with a file
	 * @param id The file id
	 * @throws IOException Throws an exception with a problem occurs
	 * @return Return a list with the ngrams
	 */
	public abstract ArrayList<Ngram> getNgrams(String id) throws IOException;

	/**
	 * Return the ngrams of this corpus
	 * @return
	 * @throws IOException
	 */
	public abstract ArrayList<Ngram> getCorpusNgrams() throws IOException;

	/**
	 * Get the cdata of the documents belonging to this corpus.
	 * @return The cdata of the corpus documents.
	 */
	public abstract float[] getClassData();

	/**
	 * Get the ids of the documents belonging to this corpus.
	 * @return The ids of the corpus documents.
	 */
	public abstract ArrayList<String> getIds();

	/**
	 * Returns the number of grams used to create this data set.
	 * @return The number of grams.
	 */
	public abstract int getNumberGrams();

	/**
	 * Get the title of a document belonging to this corpus.
	 * @param nrLines The number of lines used to construct the title.
	 * @param id The document id
	 * @return The title of a documents.
	 * @throws java.io.IOException 
	 */
	public abstract String getTitle(int nrLines, String id) throws IOException;
}
