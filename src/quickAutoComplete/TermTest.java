package quickAutoComplete;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This JUnit test class tests the Term class.
 * @author Brian
 * @see Term
 *
 */
public class TermTest {

	private Term data;
	
	/**
	 * Setup the class instance for the test.
	 */
	@Before
	public void setup() {
		data = new Term(123.0, "one");
	}
	
	/**
	 * Test the constructor.<br>
	 * Also test the getters.
	 */
	@Test
	public void testConstructor() {
		Term data1 = new Term(231.0, "two");
		assertTrue(231.0 == data1.getWeight());
		assertEquals("two", data1.getWord());
	}
	
	/**
	 * Test the getters.
	 */
	@Test
	public void testGetters() {
		assertTrue(123.0 == data.getWeight());
		assertEquals("one", data.getWord());
	}
	
	/**
	 * Tear down the instance of Term class after the tests.
	 */
	@After
	public void tearDown() {
		data = null;
	}

}
