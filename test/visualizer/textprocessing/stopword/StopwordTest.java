package visualizer.textprocessing.stopword;

import static org.junit.Assert.*;

import org.junit.Test;

import visualizer.textprocessing.stopword.StopWord;

public abstract class StopwordTest
{
	protected StopWord stopWord;

	@Test
	public void testIsStopWord()
	{
		assertTrue(stopWord.isStopWord("a"));
		assertTrue(stopWord.isStopWord("abstract"));
		assertTrue(stopWord.isStopWord("has"));
		assertFalse(stopWord.isStopWord("software"));
	}
	
	@Test
	public void testStressTest()
	{
		long start = 0;
		long end = 0;
		
		
		String[] words = "In this paper we present a hierarchy-based visualization approach for software metrics using Treemaps. Contrary to existing rectangle-based Treemap layout algorithms, we introduce layouts based on arbitrary polygons that are advantageous with respect to the aspect ratio between width and height of the objects and the identification of boundaries between and within the hierarchy levels in the Treemap. The layouts are computed by the iterative relaxation of Voronoi tessellations. Additionally, we describe techniques that allow the user to investigate software metric data of complex systems by utilizing transparencies in combination with interactive zooming.".split(" ");
		start = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			for (String word : words) {
				stopWord.isStopWord(word);
			}
		}
		end = System.currentTimeMillis();
		System.out.println("Time spent: " + (end - start));
	}
}
