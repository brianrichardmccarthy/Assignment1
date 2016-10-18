package bruteForceAutoComplete;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TermTest {

	private Term data;
	
	@Before
	public void setup() {
		data = new Term(123.0, "one");
	}
	
	@SuppressWarnings("unused")
	@Test
	public void testConstructor() {
		Term data1 = new Term(231.0, "two");
		data1 = null;
	}
	
	@Test
	public void testGetters() {
		assertTrue(123.0 == data.getWeight());
		assertEquals("one", data.getWord());
	}
	
	@After
	public void tearDown() {
		data = null;
	}

}
