package bruteForceAutoComplete;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BruteForceTest {

	private BruteAutoComplete bruteAutoComplete;
	
	@Before
	public void setup() {
		try {
			bruteAutoComplete = new BruteAutoComplete(".\\TextFile\\wiktionary.txt");
		} catch (IllegalArgumentException | FileNotFoundException | NullPointerException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testSize() {
		assertEquals(10000, bruteAutoComplete.getSize());
	}
	
	@Test  (expected = IllegalArgumentException.class)
	public void testIllegalArgumentException() throws IllegalArgumentException, FileNotFoundException, NullPointerException {
		new BruteAutoComplete(".\\TextFile\\wiktionary2.txt");
	}
	
	@Test (expected = FileNotFoundException.class)
	public void testFileNotFoundException() throws IllegalArgumentException, FileNotFoundException, NullPointerException {
		new BruteAutoComplete("");
	}
	
	@Test  (expected = NullPointerException.class)
	public void testNullPointerException() throws IllegalArgumentException, FileNotFoundException, NullPointerException {
		new BruteAutoComplete(".\\TextFile\\wiktionary3.txt");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testDuplicates() throws IllegalArgumentException, FileNotFoundException, NullPointerException {
		new BruteAutoComplete(".\\TextFile\\wiktionary4.txt");
	}
	
	@After
	public void tearDown() {
		bruteAutoComplete = null;
	}

}
