package bruteAutoComplete;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit Test Case for BruteAutoComplete.<br>
 * Tests for: 
 * <ul>
 * <li></li>  
 * </ul>
 * @see BruteAutoComplete
 * @author Brian
 *
 */
public class BruteForceTest {

	private BruteAutoComplete bruteAutoComplete;
	
	/**
	 * Setup the main instance of brute auto complete.<br>
	 * This instance will be used for successful test.<br>
	 * Shoudn't throw exception.
	 */
	@Before
	public void setup() {
		try {
			bruteAutoComplete = new BruteAutoComplete(".\\TextFile\\wiktionary.txt");
		} catch (IllegalArgumentException | FileNotFoundException | NullPointerException e) {
			fail();
		}
	}
	
	/**
	 * Test that the size of the arraylist is the same as the number of lines in the wikitionary.
	 */
	@Test
	public void testSize() {
		assertEquals(10000, bruteAutoComplete.getSize());
	}
	
	/**
	 * This test checks that the weight of a word cannot be less than or equal to zero.
	 * @throws IllegalArgumentException (Expected: test passes).
	 * @throws FileNotFoundException (Not expected: test fails).
	 * @throws NullPointerException (Not expected: test fails).
	 */
	@Test  (expected = IllegalArgumentException.class)
	public void testIllegalArgumentException() throws IllegalArgumentException, FileNotFoundException, NullPointerException {
		new BruteAutoComplete(".\\TextFile\\wiktionary2.txt");
	}
	
	/**
	 * This test checks if the constructor throws a file not found.
	 * @throws IllegalArgumentException (Not expected: test fails).
	 * @throws FileNotFoundException  (Expected: test passes).
	 * @throws NullPointerException (Not expected: test fails).
	 */
	@Test (expected = FileNotFoundException.class)
	public void testFileNotFoundException() throws IllegalArgumentException, FileNotFoundException, NullPointerException {
		new BruteAutoComplete("");
	}
	
	/**
	 * This test checks if a Null pointer exception is thrown if a blank/ empty text file is passed to the constructor.
	 * @throws IllegalArgumentException (Not expected: test fails).
	 * @throws FileNotFoundException (Not expected: test fails).
	 * @throws NullPointerException (Expected: test passes).
	 */
	@Test  (expected = NullPointerException.class)
	public void testNullPointerException() throws IllegalArgumentException, FileNotFoundException, NullPointerException {
		new BruteAutoComplete(".\\TextFile\\wiktionary3.txt");
	}
	
	/**
	 * This test checks that if the same word is contained twice an Illegal argument is thrown from the constructor.
	 * @throws IllegalArgumentException (Expected: test passes).
	 * @throws FileNotFoundException (Not expected: test fails).
	 * @throws NullPointerException (Not expected: test fails).
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testDuplicates() throws IllegalArgumentException, FileNotFoundException, NullPointerException {
		new BruteAutoComplete(".\\TextFile\\wiktionary4.txt");
	}
	
	/**
	 * Destory the instance of brute auto complete.
	 */
	@After
	public void tearDown() {
		bruteAutoComplete = null;
	}

}
