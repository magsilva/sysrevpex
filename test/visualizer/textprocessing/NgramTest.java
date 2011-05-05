package visualizer.textprocessing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class NgramTest
{
	private Ngram ngram;

	@Test(expected=IllegalArgumentException.class)
	public void testSetText_Null_Constructor()
	{
		ngram = new Ngram(null, 0);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testSetText_Null_Setter()
	{
		ngram = new Ngram("software", 0);
		ngram.setNgram(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetN_Invalid_Zero()
	{
		ngram = new Ngram("software", 0);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testSetN_Invalid_Negative()
	{
		ngram = new Ngram("software", -1);
	}
	
	@Test
	public void testGetNgram()
	{
		ngram = new Ngram("software", 1);
		assertEquals("software", ngram.getNgram());
		assertEquals(1, ngram.getFrequency());
		assertEquals(1, ngram.getN());
	}


	@Test
	public void testSetFrequency_Constructor()
	{
		ngram = new Ngram("software", 1, 5);
		assertEquals("software", ngram.getNgram());
		assertEquals(5, ngram.getFrequency());
		assertEquals(1, ngram.getN());
	}

	@Test
	public void testSetFrequency_Setter()
	{
		ngram = new Ngram("software", 1);
		ngram.setFrequency(5);
		assertEquals("software", ngram.getNgram());
		assertEquals(5, ngram.getFrequency());
		assertEquals(1, ngram.getN());
	}

	@Test
	public void testCompareTo_Equals()
	{
		assertEquals(0, new Ngram("software", 1).compareTo(new Ngram("software", 1)));
	}
	
	@Test
	public void testCompareTo_Greater()
	{
		assertEquals(1, new Ngram("software", 1).compareTo(new Ngram("software", 1, 2)));
	}
	
	@Test
	public void testCompareTo_Less()
	{
		assertEquals(-2, new Ngram("software", 1, 3).compareTo(new Ngram("software", 1, 1)));
	}
	
	public void testCompareTo_Null()
	{
		assertEquals(-3, new Ngram("software", 1, 3).compareTo(null));
	}
	
	@Test
	public void testCompareTo_Same()
	{
		ngram = new Ngram("software", 1);
		assertEquals(0, ngram.compareTo(ngram));
	}

	@Test
	public void testEqualsObject()
	{
		assertEquals(new Ngram("software", 1), new Ngram("software", 1));
	}

	@Test
	public void testDiffersObject()
	{
		assertFalse(new Ngram("software", 1).equals(new Ngram("software", 1, 2)));
	}
	
	@Test
	public void testToString_1()
	{
		assertEquals("software", new Ngram("software", 1).toString());
	}

	@Test
	public void testToString_2()
	{
		assertEquals("software testing", new Ngram("software<>testing", 2).toString());
	}

	
}
