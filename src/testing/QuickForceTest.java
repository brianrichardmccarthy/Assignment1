package testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.FileNotFoundException;
import com.google.common.collect.Iterables;

import quickAutoComplete.QuickAutoComplete;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit Test Case for BruteAutoComplete.<br>
 * Tests for:
 * <ul>
 * <li>Test the size of array list to lines in specific file.</li>
 * <li>Tests the methods and constructor for exceptions when invalid data is passed.</li>
 * <li>Test for the weight of several words pick randomly from the start, middle, and end of the file.</li>
 * <li>Test for the best matches of several prefixs or words pick randomly from the start, middle, and end of the file.</li>
 * <li>Test for a list of words with a specific prefix and that an array list size is the same as the one specifed</li>
 * </ul>
 * 
 * @see QuickAutoComplete
 * @author Brian
 *
 */
public class QuickForceTest {

	private QuickAutoComplete quickAutoComplete;

	/**
	 * Setup the main instance of brute auto complete.<br>
	 * This instance will be used for successful test.<br>
	 * Shoudn't throw exception.
	 */
	@Before
	public void setup() {
		try {
			quickAutoComplete = new QuickAutoComplete(".\\TextFile\\wiktionary.txt");
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
		assertEquals(10000, quickAutoComplete.getSize());
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
		new QuickAutoComplete(".\\TextFile\\wiktionary2.txt");
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
		new QuickAutoComplete("");
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
		new QuickAutoComplete(".\\TextFile\\wiktionary3.txt");
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
		new QuickAutoComplete(".\\TextFile\\wiktionary4.txt");
	}

	/**
	 * Test the weightOf method by passing in values taken from the start,
	 * middle, and end of the file with the weight from the file. Also test if a
	 * given prefix is not in the arraylist.
	 */
	@Test
	public void testWeightOfWord() {
		// hight weight, start of the file
		assertTrue(quickAutoComplete.weightOf("the") == 5627187200.0);
		assertTrue(quickAutoComplete.weightOf("of") == 3395006400.0);
		assertTrue(quickAutoComplete.weightOf("and") == 2994418400.0);
		assertTrue(quickAutoComplete.weightOf("to") == 2595609600.0);
		assertTrue(quickAutoComplete.weightOf("was") == 1007824500.0);
		
		// middle weight, middle of the file
		assertTrue(quickAutoComplete.weightOf("confirmation") == 1285590.0);
		assertTrue(quickAutoComplete.weightOf("glances") == 1285510.0);
		assertTrue(quickAutoComplete.weightOf("diamonds") == 1285110.0);
		assertTrue(quickAutoComplete.weightOf("goal") == 1284800.0);
		assertTrue(quickAutoComplete.weightOf("representations") == 1284560.0);

		// low weights, end of file
		assertTrue(quickAutoComplete.weightOf("calves") == 392323.0);
		assertTrue(quickAutoComplete.weightOf("wench") == 392402.0);
		assertTrue(quickAutoComplete.weightOf("sequel") == 392402.0);
		assertTrue(quickAutoComplete.weightOf("cooperation") == 392481.0);
		assertTrue(quickAutoComplete.weightOf("repressed") == 392640.0);

		// test for no word found
		assertTrue(quickAutoComplete.weightOf("xyz") == 0.0);
	}

	/**
	 * Tests if the weightOf method successfully throws a null pointer exception
	 * if the given string is null.
	 * 
	 * @throws NullPointerException thrown if the string passed to it is null.
	 */
	@Test(expected = NullPointerException.class)
	public void testWeightof() throws NullPointerException {
		quickAutoComplete.weightOf(null);
	}

	/**
	 * Tests if the given prefix is the start of the word in the array list or not.
	 */
	@Test
	public void testBestMatchWord() {
		// hight weight, start of the file
		assertTrue(quickAutoComplete.bestMatch("th").equals("the"));
		assertTrue(quickAutoComplete.bestMatch("of").equals("of"));
		assertTrue(quickAutoComplete.bestMatch("and").equals("and"));
		assertTrue(quickAutoComplete.bestMatch("to").equals("to"));
		// assertTrue(quickAutoComplete.bestMatch("in").equals("in"));

		// middle weight, middle of the file
		assertTrue(quickAutoComplete.bestMatch("confirmatio").equals("confirmation"));
		assertTrue(quickAutoComplete.bestMatch("glances").equals("glances"));
 		assertTrue(quickAutoComplete.bestMatch("diamonds").equals("diamonds"));
		assertTrue(quickAutoComplete.bestMatch("goal").equals("goal"));
		assertTrue(quickAutoComplete.bestMatch("representations").equals("representations"));

		// low weights, end of file
		assertTrue(quickAutoComplete.bestMatch("calve").equals("calves"));
		assertTrue(quickAutoComplete.bestMatch("wenc").equals("wench"));
		assertTrue(quickAutoComplete.bestMatch("sequel").equals("sequel"));
		assertTrue(quickAutoComplete.bestMatch("cooperatio").equals("cooperation"));
		assertTrue(quickAutoComplete.bestMatch("represse").equals("repressed"));

		// test if given string is not in the arraylist
		assertTrue(quickAutoComplete.bestMatch("xyz") == null);
	}

	/**
	 * Tests if the method best match throws a null pointer exception if the given string is null.
	 * @throws NullPointerException - Thrown when given string is null.
	 */
	@Test(expected = NullPointerException.class)
	public void testBestMatch() throws NullPointerException {
		quickAutoComplete.bestMatch(null);
	}

	/**
	 * Tests that matches method returns a list of strings matching the size given.
	 */
	@Test
	public void testMatches() {
		
		Iterable<String> temp = quickAutoComplete.matches("the", 7);
		assertTrue(Iterables.size(temp) == 7);
		
		temp = quickAutoComplete.matches("the", 10);
		assertTrue(Iterables.size(temp) == 10);
		
		temp = quickAutoComplete.matches("the", 15);
		assertTrue(Iterables.size(temp) == 15);

		temp = quickAutoComplete.matches("the", 5);
		assertTrue(Iterables.size(temp) == 5);
	}

	/**
	 * Tests if the method matchs throws a null pointer exception if the given string is null.
	 * @throws NullPointerException - Thrown when given string is null.
	 */
	@Test(expected = NullPointerException.class)
	public void testMatchesNull() {
		quickAutoComplete.matches(null, 5);
	}

	/**
	 * Tests if the method matches throws a illegal argument exception if the given int is less than or equal to zero.
	 * @throws NullPointerException - Thrown when given int is less than or equal to zero.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testMatchesIllegal() {
		quickAutoComplete.matches("the", 0);
	}

	/**
	 * Tests if the method matches throws a null pointer exception if the given int is bigger than the arraylist.
	 * @throws NullPointerException - Thrown when given int is bigger than the total size of arraylist.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testMatchesIllegal2() {
		quickAutoComplete.matches("the", quickAutoComplete.getSize() * 2);
	}

	/**
	 * Destory the instance of brute auto complete.
	 */
	@After
	public void tearDown() {
		quickAutoComplete = null;
	}

}
