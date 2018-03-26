import java.util.HashMap;
import java.util.Map;

public abstract class Cryptogram {

	private String phrase;
	

	public Cryptogram(String encryptedPhrase) {
 		phrase = encryptedPhrase;
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
	public String getPhrase()
	{
		return phrase;
	}
	public abstract HashMap<?, ?> getMapping();
	public abstract String getEncryptedPhrase();
}
