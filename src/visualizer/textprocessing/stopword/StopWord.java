package visualizer.textprocessing.stopword;

import java.util.List;

public interface StopWord extends Iterable<String>
{
	void changeStopwordList(String stpFilename) throws java.io.IOException;

	void addStopword(String stopword);
	
	void addStopwords(List<String> stopwords);

	void removeStopword(String stopword);

	void saveStopwordsList(String filename) throws java.io.IOException;

	String getFilename();

	boolean isStopWord(String word);
}
