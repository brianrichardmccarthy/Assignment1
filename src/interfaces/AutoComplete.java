package interfaces;


public interface AutoComplete {

	/**
	 * Returns the weight of the term, or 0.0 if no such term.
	 * @param term
	 * @return (double) weight of given prefix or 0.0 if no String has the prefix given.
	 */
	public double weightOf(String term);

	/**
	 * Returns the highest weighted matching term, or null if no matching term.
	 * @param prefix (String) prefix to be searched.
	 * @return (String) with the given prefix and the highest weight. Or null if no word in list has the given prefix.
	 */
	public String bestMatch(String prefix);

	/**
	 * Returns the highest weighted k matching terms (in descending order of
	 * weight), as an iterable.
	 * If fewer than k matches, return all matching terms (in descending order of weight).
	 * @param prefix (String) to be searched.
	 * @param k (int) max size of the list to be returned.
	 * @return List<String> of size <= k
	 */
	public Iterable<String> matches(String prefix, int k);
}