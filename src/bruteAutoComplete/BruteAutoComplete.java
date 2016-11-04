package bruteAutoComplete;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import edu.princeton.cs.introcs.Stopwatch;
import term.Term;
import interfaces.AutoComplete;

/**
 * This Class reads in a file from a given String in the Constructor.<br>
 * Sort the content from the file by highest to lowest descending order of occurences.<br>
 * Porvides three options.
 * <ul>
 * 	<li> Search a specified string for its occurence.</li>
 * 	<li> Search for the best match (linear).</li>
 * 	<li> Search for a list of prefixs (linear).</li>
 * </ul>
 * As outlined in the AutoComplete interface.
 * @see AutoComplete
 * @author Brian
 *
 */
public class BruteAutoComplete implements AutoComplete {

	private List<Term> words;

	/**
	 * 1. Reads in a file from a given String.<br>
	 * 2. For each line in the file split the weight of the word and the
	 * word.<br>
	 * 3. Convert the weight into a double.<br>
	 * 4. Add the weight and word to ArrayList of Terms class.<br>
	 * 5. Sort the arraylist based on weight.<br>
	 * 6. Or Throw exceptions.
	 * 
	 * @param file
	 *            (String) path to local file.
	 * @throws IllegalArgumentException
	 *             if the weight of the word is less than or equal to zero. Or
	 *             if the word is repeated.
	 * @throws FileNotFoundException
	 *             if the file is not found at the given path.
	 * @throws NullPointerException
	 *             if the file given is empty.
	 */
	public BruteAutoComplete(String file) throws IllegalArgumentException, FileNotFoundException, NullPointerException {

		// file object
		File dictionaryFile = new File(file);

		// scanner object used to read the lines in file
		Scanner inTerms = new Scanner(dictionaryFile);

		// String for spliting the lines in the file
		String delims = "\\s+";

		// Terms ArrayList
		words = new ArrayList<Term>();

		/*
		 * Check if the file has a next line. Test if file is empty. If true
		 * close scanner, throw null pointer exception.
		 */
		if (!inTerms.hasNextLine()) {
			inTerms.close();
			throw new NullPointerException(file + " is empty.");
		}
		
		// setup a stop watch for timing
		Stopwatch stopwatch = new Stopwatch();
		
		/*
		 * 1. Read in the next line of the file. 2. Split the line by weight and
		 * word. 3. Add both to new Term class and add new Term class to
		 * arraylist.
		 */
		while (inTerms.hasNextLine()) {

			// .trim() removes whitespace at start and end of line.
			String termString = inTerms.nextLine().trim();

			// split the termString with the delims.
			String[] termSplit = termString.split(delims);

			// check if the array does not contain weight and word
			if (termSplit.length < 2)
				continue;

			Double tempWeight = Double.parseDouble(termSplit[0]);

			// check the index is less than or equal to zero, 
			// if true throws Illegal Argument Exception.
			if (tempWeight <= 0) {
				inTerms.close();
				throw new IllegalArgumentException("Weight of word cannot be less than or equal to zero.");
			}
				

			// check that the word is not already in the array list,
			// if it is then throws Illegal Argument Exception.
			for (Term word : words)
				if (word.getWord().equals(termSplit[1])) {
					inTerms.close();
					throw new IllegalArgumentException("Duplicate word.");
				}

			// create new instance of Term with the weight and word then add new Term to array list
			words.add(new Term(tempWeight, termSplit[1]));

		}

		// sort the arrayList
		sort();

		// print the time taken to read the file
		System.out.println("Time taken to read in file: " + stopwatch.elapsedTime());
		
		// close the scanner
		inTerms.close();

	}

	/**
	 * Sort the arrayList by weight, highest to lowest.
	 */
	private void sort() {
		
		// sort the arraylist by weight with the comparator interface
		Collections.sort(words, new Comparator<Term>() {

			public int compare(Term word1, Term word2) {
				// sorts the words as highest to lowest.
				return word2.getWeight().compareTo(word1.getWeight());
			}

		});
		
	}

	/**
	 * Get the Weight of a given string or prefix. (Linear search: best case = 1, worst case = N)
	 * @param term (String) whole word or substring of a word to be searched to find the weight of.
	 * @return (double) weight of term given or 0.0 if no weight found.
	 * @throws null pointer exception if the given term equals null.
	 */
	public double weightOf(String term) throws NullPointerException {

		// check if the given string is null,
		// if true throws Null Pointer Exception.
		if (term == null)
			throw new NullPointerException();

		// sort the arrayList
		sort();

		// setup a stop watch for timing
		Stopwatch stopwatch = new Stopwatch();
		
		/*
		 * linear search each value in the array list to see if starts with given prefix.
		 * Best case: 1 (because the word in the first index starts with given prefix). Then return its weight.
		 * Worst case: N (because the last word is the only match or arraylist contains no word with given prefix). Return the last Terms' weight or 0.0.
		 * */
		for (Term word : words)	{// Worst: N Best: 1.
			if (word.getWord().startsWith(term)) {				// Worst: N Best: 1.
				System.out.println("Time taken to find weight: " + stopwatch.elapsedTime());
				return word.getWeight();						// Worst: 0 Best: 1.
			}
		}
		
		System.out.println("Time taken to find weight: " + stopwatch.elapsedTime());
		// return 0.0 if no String starts with the given prefix.
		return 0.0;

	}

	/**
	 * Get the word with the highes weigth and starts with a given prefix. (Linear search: best case = 1, worst case = N)
	 * @param term (String) whole word or substring of a word to be searched.
	 * @return (String) the word with the highest weigth and contains the prefix given or returns null.
	 * @throws null pointer exception if the given term equals null.
	 */
	public String bestMatch(String prefix) throws NullPointerException {

		// check if the given string is null,
		// if true throws Null Pointer Exception.
		if (prefix == null)
			throw new NullPointerException();

		// sort the arrayList
		sort();

		// setup a stop watch for timing
		Stopwatch stopwatch = new Stopwatch();
		
		/*
		 * linear search each value in the array list to see if starts with given prefix and return the word with highest weight.
		 * Best case: 1 (because the word in the first index starts with given prefix) and return first word of arraylist.
		 * Worst case: N (because the last word is the only match or arraylist contains no word with given prefix) return last word in arraylist or return null.
		 * */
		for (Term word : words)									// Worst: N Best: 1.
			if (word.getWord().startsWith(prefix)) {			// Worst: N Best: 1.
				System.out.println("Time taken to find string: " + stopwatch.elapsedTime());
				return word.getWord();							// Worst: 0 Best: 1.
			}

		System.out.println("Time taken to find string: " + stopwatch.elapsedTime());
		// return null if no String starts with given prefix.
		return null;

	}

	/**
	 * Given a string prefix and a number k.<br> 
	 * Searchs the arraylist for words with the given prefix.<br>
	 * if the number of words in the arraylist with the prefix given is less than k,<br>
	 * return all words found.<br>
	 * else if the number of words in the arraylist with the prefix given is greater than k,<br>
	 * return a list of size k with the words.<br>
	 * The highest weight at the first index and going in descending order of weigth.<br>
	 * Uses linear search.
	 * <br>		Best  case: k
	 * <br>		Worst case: N
	 * @param (String) prefix to be search.
	 * @param (int) k size of list to be returned.
	 * @return (Iterable<String>) arraylist of words with the given prefix.
	 * @throws illegal argument exception if k is less than or equal to zero.
	 * @throws null pointer exception if prefix equals null.
	 */
	public Iterable<String> matches(String prefix, int k) throws IllegalArgumentException, NullPointerException {

		// check if the given string is null,
		// if true throws Null Pointer Exception.
		if (prefix == null)
			throw new NullPointerException();
		
		// check if the given int is less than or equal to zero,
		// if true throws Illegal Argument Exception.
		if (k <= 0 || k > words.size())
			throw new IllegalArgumentException();

		List<String> matches = new ArrayList<String>();

		// sort the arrayList
		sort();

		// setup a stop watch for timing
		Stopwatch stopwatch = new Stopwatch();
		
		for (Term word : words)									// Worst: N Best: K
			if (matches.size() == k) {							// Worst: N Best: K
				System.out.println("Time taken to find top words: " + stopwatch.elapsedTime());
				return matches;									// Worst: 0 Best: 1
			}
			else if (word.getWord().startsWith(prefix))			// Worst: N Best: K
				matches.add(word.getWord());					// Worst: X < N && K  Best: K
																// where x is the number of strings found.
		System.out.println("Time taken to find top words: " + stopwatch.elapsedTime());
		return matches;

	}

	/**
	 * Accessor for the array list size.
	 * @return (int) number of Term classes in the arraylist.
	 */
	public int getSize() {

		return words.size();

	}

}
