package visualizer.graph.scalar;

import static org.junit.Assert.*;

import java.util.StringTokenizer;

import org.junit.Before;
import org.junit.Test;

public class ScalarDAOTest {

	@Before
	public void setUp() throws Exception {
	}

	//cdata;year
	//filename1.txt;1.3;0.70
	//filename2.txt;4.0;0.06
	//filename3.txt;6.7;0.40
	//filename4.txt;3.0;0.12
	//filename5.txt;8.9;0.11
	@Test
	public void testImportScalarsGraphString() {
		fail("Not yet implemented");
	}

	@Test
	public void testImportScalarsGraphFile() {
		fail("Not yet implemented");
	}

	@Test
	public void testImportScalarsGraphInputStreamReader() {
		fail("Not yet implemented");
	}

	@Test
	public void testExportScalarsGraphString() {
		fail("Not yet implemented");
	}

	@Test
	public void testExportScalarsGraphFile() {
		fail("Not yet implemented");
	}

	@Test
	public void testExportScalarsGraphOutputStreamWriter() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testTokenizer_WithEndComma() {
		StringTokenizer st = new StringTokenizer("abc;def;", ";");
		assertEquals(2, st.countTokens());
	}

}
