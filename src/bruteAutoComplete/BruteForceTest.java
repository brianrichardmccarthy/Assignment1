package bruteAutoComplete;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit Test Case for BruteAutoComplete.<br>
 * Tests for:
 * <ul>
 * <li></li>
 * </ul>
 * 
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
	 * Test that the size of the arraylist is the same as the number of lines in
	 * the wikitionary.
	 */
	@Test
	public void testSize() {
		assertEquals(10000, bruteAutoComplete.getSize());
	}

	/**
	 * This test checks that the weight of a word cannot be less than or equal
	 * to zero.
	 * 
	 * @throws IllegalArgumentException
	 *             (Expected: test passes).
	 * @throws FileNotFoundException
	 *             (Not expected: test fails).
	 * @throws NullPointerException
	 *             (Not expected: test fails).
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testIllegalArgumentException()
			throws IllegalArgumentException, FileNotFoundException, NullPointerException {
		new BruteAutoComplete(".\\TextFile\\wiktionary2.txt");
	}

	/**
	 * This test checks if the constructor throws a file not found.
	 * 
	 * @throws IllegalArgumentException
	 *             (Not expected: test fails).
	 * @throws FileNotFoundException
	 *             (Expected: test passes).
	 * @throws NullPointerException
	 *             (Not expected: test fails).
	 */
	@Test(expected = FileNotFoundException.class)
	public void testFileNotFoundException()
			throws IllegalArgumentException, FileNotFoundException, NullPointerException {
		new BruteAutoComplete("");
	}

	/**
	 * This test checks if a Null pointer exception is thrown if a blank/ empty
	 * text file is passed to the constructor.
	 * 
	 * @throws IllegalArgumentException
	 *             (Not expected: test fails).
	 * @throws FileNotFoundException
	 *             (Not expected: test fails).
	 * @throws NullPointerException
	 *             (Expected: test passes).
	 */
	@Test(expected = NullPointerException.class)
	public void testNullPointerException()
			throws IllegalArgumentException, FileNotFoundException, NullPointerException {
		new BruteAutoComplete(".\\TextFile\\wiktionary3.txt");
	}

	/**
	 * This test checks that if the same word is contained twice an Illegal
	 * argument is thrown from the constructor.
	 * 
	 * @throws IllegalArgumentException
	 *             (Expected: test passes).
	 * @throws FileNotFoundException
	 *             (Not expected: test fails).
	 * @throws NullPointerException
	 *             (Not expected: test fails).
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testDuplicates() throws IllegalArgumentException, FileNotFoundException, NullPointerException {
		new BruteAutoComplete(".\\TextFile\\wiktionary4.txt");
	}

	/**
	 * Test the weightOf method by passing in values taken from the start,
	 * middle, and end of the file with the weight from the file. Also test if a
	 * given prefix is not in the arraylist.
	 */
	@Test
	public void testWeightOfWord() {
		// hight weight, start of the file
		assertTrue(bruteAutoComplete.weightOf("th") == 5627187200.0);
		assertTrue(bruteAutoComplete.weightOf("of") == 3395006400.0);
		assertTrue(bruteAutoComplete.weightOf("and") == 2994418400.0);
		assertTrue(bruteAutoComplete.weightOf("to") == 2595609600.0);
		assertTrue(bruteAutoComplete.weightOf("in") == 1742063600.0);

		// middle weight, middle of the file
		assertTrue(bruteAutoComplete.weightOf("confirmation") == 1285590.0);
		assertTrue(bruteAutoComplete.weightOf("glances") == 1285510.0);
		assertTrue(bruteAutoComplete.weightOf("diamonds") == 1285110.0);
		assertTrue(bruteAutoComplete.weightOf("goal") == 1284800.0);
		assertTrue(bruteAutoComplete.weightOf("representations") == 1284560.0);

		// low weights, end of file
		assertTrue(bruteAutoComplete.weightOf("calves") == 392323.0);
		assertTrue(bruteAutoComplete.weightOf("wench") == 392402.0);
		assertTrue(bruteAutoComplete.weightOf("sequel") == 392402.0);
		assertTrue(bruteAutoComplete.weightOf("cooperation") == 392481.0);
		assertTrue(bruteAutoComplete.weightOf("repressed") == 392640.0);

		// test for no word found
		assertTrue(bruteAutoComplete.weightOf("xyz") == 0.0);
	}

	/**
	 * Tests if the weightOf method successfully throws a null pointer exception
	 * if the given string is null.
	 * 
	 * @throws NullPointerException thrown if the string passed to it is null.
	 */
	@Test(expected = NullPointerException.class)
	public void testWeightof() throws NullPointerException {
		bruteAutoComplete.weightOf(null);
	}

	/**
	 * Tests if the given prefix is the start of the word in the array list or not.
	 */
	@Test
	public void testBestMatchWord() {
		// hight weight, start of the file
		assertTrue(bruteAutoComplete.bestMatch("th").equals("the"));
		assertTrue(bruteAutoComplete.bestMatch("of").equals("of"));
		assertTrue(bruteAutoComplete.bestMatch("and").equals("and"));
		assertTrue(bruteAutoComplete.bestMatch("to").equals("to"));
		assertTrue(bruteAutoComplete.bestMatch("in").equals("in"));

		// middle weight, middle of the file
		assertTrue(bruteAutoComplete.bestMatch("confirmatio").equals("confirmation"));
		assertTrue(bruteAutoComplete.bestMatch("glances").equals("glances"));
		assertTrue(bruteAutoComplete.bestMatch("diamond").equals("diamonds"));
		assertTrue(bruteAutoComplete.bestMatch("goal").equals("goal"));
		assertTrue(bruteAutoComplete.bestMatch("representations").equals("representations"));

		// low weights, end of file
		assertTrue(bruteAutoComplete.bestMatch("calve").equals("calves"));
		assertTrue(bruteAutoComplete.bestMatch("wenc").equals("wench"));
		assertTrue(bruteAutoComplete.bestMatch("sequel").equals("sequel"));
		assertTrue(bruteAutoComplete.bestMatch("cooperatio").equals("cooperation"));
		assertTrue(bruteAutoComplete.bestMatch("represse").equals("repressed"));

		// test if given string is not in the arraylist
		assertTrue(bruteAutoComplete.bestMatch("xyz") == null);
	}

	/**
	 * Tests if the method best match throws a null pointer exception if the given string is null.
	 * @throws NullPointerException - Thrown when given string is null.
	 */
	@Test(expected = NullPointerException.class)
	public void testBestMatch() throws NullPointerException {
		bruteAutoComplete.bestMatch(null);
	}

	/**
	 * Tests that matches method returns a list of strings matching the size given.
	 */
	@Test
	public void testMatches() {
		ArrayList<String> temp = (ArrayList<String>) bruteAutoComplete.matches("the", 5);
		assertTrue(temp.size() == 5);

		temp = (ArrayList<String>) bruteAutoComplete.matches("the", 7);
		assertTrue(temp.size() == 7);

		temp = (ArrayList<String>) bruteAutoComplete.matches("the", 10);
		assertTrue(temp.size() == 10);

		temp = (ArrayList<String>) bruteAutoComplete.matches("the", 15);
		assertTrue(temp.size() == 15);
	}

	/**
	 * Tests if the method matchs throws a null pointer exception if the given string is null.
	 * @throws NullPointerException - Thrown when given string is null.
	 */
	@Test(expected = NullPointerException.class)
	public void testMatchesNull() {
		bruteAutoComplete.matches(null, 5);
	}

	/**
	 * Tests if the method matches throws a illegal argument exception if the given int is less than or equal to zero.
	 * @throws NullPointerException - Thrown when given int is less than or equal to zero.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testMatchesIllegal() {
		bruteAutoComplete.matches("the", 0);
	}

	/**
	 * Tests if the method matches throws a null pointer exception if the given int is bigger than the arraylist.
	 * @throws NullPointerException - Thrown when given int is bigger than the total size of arraylist.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testMatchesIllegal2() {
		bruteAutoComplete.matches("the", bruteAutoComplete.getSize() * 2);
	}

	/**
	 * Destory the instance of brute auto complete.
	 */
	@After
	public void tearDown() {
		bruteAutoComplete = null;
	}

}
