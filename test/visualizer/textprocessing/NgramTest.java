package visualizer.textprocessing;

import static org.junit.Assert.*;

import org.junit.Test;

public class NgramTest
{
	private Ngram ngram;

	@Test(expected=IllegalArgumentException.class)
	public void testSetText_Null_Constructor()
	{
		ngram = new Ngram(null);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testSetText_Null_Setter()
	{
		ngram = new Ngram("software");
		ngram.setNgram(null);
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
		ngram = new Ngram("software", 5);
		assertEquals("software", ngram.getNgram());
		assertEquals(5, ngram.getFrequency());
		assertEquals(1, ngram.getN());
	}

	@Test
	public void testSetFrequency_Setter()
	{
		ngram = new Ngram("software");
		ngram.setFrequency(5);
		assertEquals("software", ngram.getNgram());
		assertEquals(5, ngram.getFrequency());
		assertEquals(1, ngram.getN());
	}

	@Test
	public void testCompareTo_Equals()
	{
		assertEquals(0, new Ngram("software").compareTo(new Ngram("software")));
	}
	
	@Test
	public void testCompareTo_Greater()
	{
		assertEquals(-1, new Ngram("software").compareTo(new Ngram("software", 2)));
	}
	
	@Test
	public void testCompareTo_Less()
	{
		assertEquals(2, new Ngram("software", 3).compareTo(new Ngram("software", 1)));
	}
	
	public void testCompareTo_Null()
	{
		assertEquals(3, new Ngram("software", 3).compareTo(null));
	}
	
	@Test
	public void testCompareTo_Same()
	{
		ngram = new Ngram("software");
		assertEquals(0, ngram.compareTo(ngram));
	}

	@Test
	public void testCompareTo_Greater_Pedantic()
	{
		Ngram n1 = new Ngram("software testing", 1);
		Ngram n2 = new Ngram("software testing", 2);
		n1.setPedantic(true);
		n2.setPedantic(true);
		assertEquals(1, n2.compareTo(n1));
	}
	
	@Test
	public void testCompareTo_Lesser_Pedantic()
	{
		Ngram n1 = new Ngram("software testing", 1);
		Ngram n2 = new Ngram("software testing", 2);
		n1.setPedantic(true);
		n2.setPedantic(true);
		assertEquals(-1, n1.compareTo(n2));
	}

	@Test
	public void testCompareTo_Same_Pedantic()
	{
		Ngram n1 = new Ngram("software testing", 2);
		Ngram n2 = new Ngram("software testing", 2);
		n1.setPedantic(true);
		n2.setPedantic(true);
		assertEquals(0, n1.compareTo(n2));
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void testCompareTo_Different_Pedantic()
	{
		Ngram n1 = new Ngram("software testing", 1);
		Ngram n2 = new Ngram("software", 2);
		n1.setPedantic(true);
		n2.setPedantic(true);
		assertEquals(1, n1.compareTo(n2));
	}
	


	
	@Test
	public void testEqualsObject()
	{
		assertEquals(new Ngram("software"), new Ngram("software"));
	}

	@Test
	public void testDiffersObject()
	{
		// That's a behaviour specifc to ngram
		assertTrue(new Ngram("software").equals(new Ngram("software", 2)));
	}

	@Test
	public void testDiffersObject_Pedantic()
	{
		// That's a behaviour specifc to ngram
		Ngram ngram = new Ngram("software");
		ngram.setPedantic(true);
		assertFalse(ngram.equals(new Ngram("software", 2)));
	}
	
	@Test
	public void testToString_1()
	{
		assertEquals("software", new Ngram("software").toString());
	}

	@Test
	public void testToString_2()
	{
		assertEquals("software testing", new Ngram("software testing").toString());
	}
	
	@Test
	public void testToString2_ExtraSpaces()
	{
		assertEquals("software testing", new Ngram("software   testing").toString());
	}
}
