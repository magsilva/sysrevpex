package visualizer.corpus.database;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import visualizer.corpus.Encoding;

public class ZipFileImporterTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	@Ignore
	public void testExecute() throws IOException {
		String filename = "G:\\User\\users\\Documents\\FERNANDO\\Codigo\\java\\ExtractArticles\\ExtractArticles.zip";
		ZipFileImporter zfi = new ZipFileImporter(filename);
		zfi.execute("Infovis 2004 contest IV", 1, 1, Encoding.ASCII);
	}
}
