import java.util.HashMap;

public class Cryptogram {

	String phrase;


	public Cryptogram() {
	}

	public HashMap<Character, Integer> getFrequencies() {
		HashMap<Character, Integer> frequencies = new HashMap<>();
		char letter;
		for(int i = 0; i < phrase.length(); i++ ) {
			letter = phrase.toLowerCase().charAt(i);
			if(frequencies.containsKey(letter)) {
				frequencies.replace(letter, frequencies.get(letter) + 1);
			}
			else {
				frequencies.put(letter, 1);
			}

		}
		return frequencies;

	}
	
}
