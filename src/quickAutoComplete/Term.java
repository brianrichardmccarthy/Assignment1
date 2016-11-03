package quickAutoComplete;

/**
 * This class stores a Double weight of occurence and String word.<br>
 * Neither of these variables changes after they are set.
 * @author Brian
 *
 */
public class Term implements Comparable<Term> {

	private Double weight;
	private String word;
	
	/**
	 * Constructor for Term Class.
	 * @param weight (Double) weight of the word.
	 * @param word (String) word.
	 */
	public Term(Double weight, String word) {
		this.weight = weight;
		this.word = word;
	}
	
	/**
	 * Gets the weight of the word.
	 * @return (Double) weight of occurenece.
	 */
	public Double getWeight() {
		return weight;
	}
	
	/**
	 * Get the word for this Object.
	 * @return (String) returns the word value for this Object.
	 */
	public String getWord() {
		return word;
	}
	
	/**
	 * @return (String) "Weight: " + weight of the word + ", Word" + word;
	 */
	public String toString() {
		return "Weight: " + weight + ", Word: " + word;
	}

	@Override
	public int compareTo(Term arg0) {
		return word.compareToIgnoreCase(arg0.getWord());
	}
	
}