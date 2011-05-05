package visualizer.corpus;

import java.io.IOException;
import java.util.List;

import visualizer.textprocessing.Ngram;

public interface Corpus
{
	public static final String NGRAM_SEPARATOR = "<>";
	
	/**
	 * Return the raw file content
	 * 
	 * @param id The file id
	 * @return File content
	 * @throws IOException
	 */
	String getFullContent(String id);

	/**
	 * Return the modified file content - Custom for viewing
	 * 
	 * @param id The file id
	 * @return File content
	 * @throws IOException
	 */
	String getViewContent(String id);

	/**
	 * Return the ngrams associated with a file
	 * 
	 * @param id The file id
	 * @throws IOException Throws an exception with a problem occurs
	 * @return Return a list with the ngrams
	 */
	List<Ngram> getNgrams(String id);

	/**
	 * Return the ngrams of this corpus
	 * 
	 * @return
	 * @throws IOException
	 */
	List<Ngram> getCorpusNgrams();

	/**
	 * Get the cdata of the documents belonging to this corpus.
	 * 
	 * @return The cdata of the corpus documents.
	 */
	float[] getClassData();

	/**
	 * Get the ids of the documents belonging to this corpus.
	 * 
	 * @return The ids of the corpus documents.
	 */
	List<String> getIds();

	/**
	 * Returns the number of grams used to create this data set.
	 * 
	 * @return The number of grams.
	 */
	int getNumberGrams();

	/**
	 * Get the title of a document belonging to this corpus.
	 * 
	 * @param nrLines The number of lines used to construct the title.
	 * @param id The document id
	 * @return The title of a documents.
	 * @throws java.io.IOException
	 */
	String getTitle(int nrLines, String id);
}
