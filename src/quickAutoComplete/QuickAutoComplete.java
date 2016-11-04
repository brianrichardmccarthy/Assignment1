package quickAutoComplete;

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
 * Sort the content from the file by highest to lowest descending order of
 * occurences.<br>
 * Porvides three options.
 * <ul>
 * <li>Search a specified string for its occurence.</li>
 * <li>Search for the best match (linear).</li>
 * <li>Search for a list of prefixs (linear).</li>
 * </ul>
 * As outlined in the AutoComplete interface.
 * 
 * @see AutoComplete
 * @author Brian
 *
 */
public class QuickAutoComplete implements AutoComplete {

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
	public QuickAutoComplete(String file) throws IllegalArgumentException, FileNotFoundException, NullPointerException {

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

			/*
			 * Check if array list is empty, if true add the current contents of the termSplit array to the first index.
			 * */
			if (words.isEmpty())
				words.add(new Term(Double.parseDouble(termSplit[0]), termSplit[1]));
			/*
			 * Else sort the array list by it's word.
			 * Then do a bnary search. If the binary search is less than 0 add the term to the array list.
			 * Else if the binary search is greater than zero throw illegal argument exception.
			 * */
			else {
				if (Collections.binarySearch(words, new Term(0.0, termSplit[1]), compareWord()) > 0) {
					inTerms.close();
					throw new IllegalArgumentException("Duplicate word at index " + words.size());
				}
				words.add(new Term(tempWeight, termSplit[1]));
			}

		}

		// sort the arrayList by weight
		sort(compareWeight());

		// print the time taken to read the file
		System.out.println("Time taken to read in file: " + stopwatch.elapsedTime() + ", Size: " + words.size());

		// close the scanner
		inTerms.close();

	}

	/**
	 * Sort the arrayList by Comparator given as a parameter.
	 */
	private void sort(Comparator<Term> sort) {

		// sort the arraylist by weight with the comparator interface
		Collections.sort(words, sort);

	}

	/**
	 * Compares the List of Terms via weight in highest to lowest order.
	 * 
	 * @return (Comparator<Term>)
	 */
	private Comparator<Term> compareWeight() {
		return new Comparator<Term>() {

			public int compare(Term word1, Term word2) {
				// sorts the words by weights as highest to lowest.
				return word2.getWeight().compareTo(word1.getWeight());
			}

		};
	}

	/**
	 * Compares the List of Terms via word.<br>
	 * If the first word starts with the second word or if the first word equals
	 * the second word The Comparator returns 0. Else it will use the compare to
	 * ingore case method from Class String<br>
	 * and return that.
	 * 
	 * @return (Comparator<Term>)
	 */
	private Comparator<Term> compareWord() {
		return new Comparator<Term>() {

			public int compare(Term word1, Term word2) {
				if (word1.getWord().startsWith(word2.getWord()) || word1.getWord().equals(word2.getWord()))
					return 0;
				return word1.getWord().compareToIgnoreCase(word2.getWord());
			}

		};
	}

	/**
	 * Firstly preform binary search to get an index of a String that either equals or starts with the given term.<br>
	 * Taking that index to be the middle, preform a linear search on both sides (Decrementing and incrementing)<br>
	 * till it finds the first word that equals or starts with the given term or it reaches the start of the array list.<br>
	 * And it finds the last word that equals or starts with the given term or it reaches the end of the array list.<br>
	 * 
	 * @param term (String) to be searched.
	 * @return (List<Term>) sublist of Strings equal to or start with the given term, if non found return null.
	 */
	private List<Term> binarySearch(String term) {
		
		Collections.sort(words, compareWord());
		
		// preform binary search to get an index of a String that either equals or starts with the given term
		int middle = Collections.binarySearch(words, new Term(0.0, term), compareWord());
		
		// if an index is found
		if (middle > 0) {
			// variables for the start and end
			int start = middle;
			
			/*
			 * The start - 1 is greater than 0 and start - 1 word starts with the given term.
			 * Or the middle + 1 is less than the size of the array list and middle + 1 word starts with the given term.
			 * Increment the start or middle or both.
			 * */
			while ((start - 1 > 0 && words.get(start - 1).getWord().startsWith(term))
					|| (middle + 1 < words.size() && words.get(middle + 1).getWord().startsWith(term))) {
				// The start - 1 is greater than 0 and start - 1 word starts with the given term.
				// decrement start
				if (start - 1 > 0 && words.get(start - 1).getWord().startsWith(term))
					start--;
				// the middle + 1 is less than the size of the array list and middle + 1 word starts with the given term.
				// increment middle
				if (middle + 1 < words.size() && words.get(middle + 1).getWord().startsWith(term))
					middle++;
			}
			
			// create a sublist of the words with the start index and end index +1
			List<Term> subList = words.subList(start, middle + 1);
			
			// sort the sublist by weight
			Collections.sort(subList, compareWeight());
			
			// return sublist
			return subList;
			
		}
		
		// else return null
		return null;
	}
	
	
	/**
	 * Get the Weight of a given string or prefix. (Binary search + linear
	 * search)
	 * 
	 * @param term
	 *            (String) whole word or substring of a word to be searched to
	 *            find the weight of.
	 * @return (double) weight of term given or 0.0 if no weight found.
	 * @throws null
	 *             pointer exception if the given term equals null.
	 */
	public double weightOf(String term) throws NullPointerException {

		// check if the given string is null,
		// if true throws Null Pointer Exception.
		if (term == null)
			throw new NullPointerException();

		// setup a stop watch for timing
		Stopwatch stopwatch = new Stopwatch();
		
		// binary search for the list of words with the prefix
		List<Term> subList = binarySearch(term);
		
		// print the time taken to get the list
		System.out.println("Time taken to find weight: " + stopwatch.elapsedTime());

		// sort the sublist by weight
		Collections.sort(subList, compareWeight());
		
		// if the sublist is not equal to null return the weight of the first index of the sublist else return null
		return (subList != null) ? subList.get(0).getWeight() : 0.0;

	}


	/**
	 * Get the word with the highest weigth and starts with a given prefix.
	 * (Binary Search + Linear search)
	 * 
	 * @param term
	 *            (String) whole word or substring of a word to be searched.
	 * @return (String) the word with the highest weigth and contains the prefix
	 *         given or returns null.
	 * @throws null
	 *             pointer exception if the given term equals null.
	 */
	public String bestMatch(String prefix) throws NullPointerException {

		// check if the given string is null,
		// if true throws Null Pointer Exception.
		if (prefix == null)
			throw new NullPointerException();

		// setup a stop watch for timing
		Stopwatch stopwatch = new Stopwatch();
		
		// binary search for the list of words with the prefix
		List<Term> subList = binarySearch(prefix);
		
		// print the time taken to get the list
		System.out.println("Time taken to find weight: " + stopwatch.elapsedTime());

		// sort the sublist by weight
		Collections.sort(subList, compareWeight());
		
		// if the sublist is not equal to null return the word of the first index of the sublist else return null
		return (subList != null) ? subList.get(0).getWord() : null;

	}

	/**
	 * Given a string prefix and a number k.<br>
	 * Searchs the arraylist for words with the given prefix.<br>
	 * if the number of words in the arraylist with the prefix given is less
	 * than k,<br>
	 * return all words found.<br>
	 * else if the number of words in the arraylist with the prefix given is
	 * greater than k,<br>
	 * return a list of size k with the words.<br>
	 * The highest weight at the first index and going in descending order of
	 * weigth.<br>
	 * Uses binary + linear search. <br>
	 * 
	 * @param (String)
	 *            prefix to be search.
	 * @param (int)
	 *            k size of list to be returned.
	 * @return (Iterable<String>) arraylist of words with the given prefix.
	 * @throws illegal
	 *             argument exception if k is less than or equal to zero.
	 * @throws null
	 *             pointer exception if prefix equals null.
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

		// setup a stop watch for timing
		Stopwatch stopwatch = new Stopwatch();
		
		// binary search for the list of words with the prefix
		List<Term> subList = binarySearch(prefix);
		
		// print the time taken to get the list
		System.out.println("Time taken to find weight: " + stopwatch.elapsedTime());

		
		// the prefix is not found return null
		if (subList == null)
			return null;

		// create a list of string to be returned
		List<String> list = new ArrayList<String>();
		
		// sort the sublist by weight
		Collections.sort(subList, compareWeight());

		// add all the words in terms sublist to string sub list
		for (Term term : subList)
			list.add(term.getWord());

		// if the size of the sublist of strings is greater than k return a sublist of this sublist of size k
		// else return sublist
		return (list.size() > k) ? list.subList(0, k) : list;

	}

	/**
	 * Accessor for the array list size.
	 * 
	 * @return (int) number of Term classes in the arraylist.
	 */
	public int getSize() {

		return words.size();

	}

	/**
	 * 
	 */
	public void printAll() {
		Collections.sort(words, compareWeight());
		for (Term word : words)
			System.out.println(word);
	}

}
