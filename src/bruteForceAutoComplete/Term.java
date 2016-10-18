package bruteForceAutoComplete;

public class Term  {

	private Double weight;
	private String word;
	
	public Term(Double weight, String word) {
		this.weight = weight;
		this.word = word;
	}
	public Double getWeight() {
		return weight;
	}
	public String getWord() {
		return word;
	}
	
	public String toString() {
		return "Weight: " + weight + ", Word: " + word + ".\n";
	}
	
}