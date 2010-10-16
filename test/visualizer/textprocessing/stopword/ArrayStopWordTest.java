package visualizer.textprocessing.stopword;

import org.junit.Before;

public class ArrayStopWordTest extends StopwordTest
{
	@Before
	public void setUp() throws Exception
	{
		stopWord = ArrayStopword.getInstance();
	}
}
