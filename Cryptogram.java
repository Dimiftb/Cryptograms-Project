import java.util.HashMap;
import java.util.Map;

public class Cryptogram {

	String phrase;
	HashMap<Object, Object> symbolMapping;

	public Cryptogram(String encryptedPhrase, Map<?, ?> mapping) {
 		phrase = encryptedPhrase;
 		symbolMapping = new HashMap<>();
 		symbolMapping.putAll(mapping);
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
