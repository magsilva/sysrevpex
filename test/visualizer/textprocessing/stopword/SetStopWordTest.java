package visualizer.textprocessing.stopword;

import org.junit.Before;

public class SetStopWordTest extends StopwordTest
{
	@Before
	public void setUp() throws Exception
	{
		stopWord = SetStopword.getInstance();
	}
}
