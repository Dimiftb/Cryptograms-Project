import java.util.HashMap;
import java.util.Random;

public class LetterCryptogram extends Cryptogram {

	private HashMap<Integer, Character> letterMapping;
	private HashMap<Character, Integer> keys;
	private String phrase;
	private String encryptedPhrase;
	
	
	public LetterCryptogram() {
		Random randMapping = new Random();
		int randomNumber;
		phrase = super.phrase;
		for (int i = 0; i < phrase.length(); i++) {
			randomNumber = randMapping.nextInt();
			letterMapping.put(randomNumber, phrase.charAt(i));
			keys.put(phrase.charAt(i), randomNumber);
		}
	}
	public LetterCryptogram(String encryptedPhrase, HashMap<Integer, Character> letterMapping)
	{
		this.encryptedPhrase = encryptedPhrase;
		this.letterMapping = letterMapping;
	}
	public HashMap<Integer, Character> getLetterMapping()
	{
		return letterMapping;
	}
	public char getLetter(int i) {
		if (i > 0 && i < phrase.length()) {
			return phrase.charAt(i);
		} else {
			return '#';
		}

	}
	public String getEncryptedPhrase()
	{
		StringBuilder encryptedPhraseBuild = new StringBuilder();
		for(int count = 0; count < phrase.length(); count++)
		{
			encryptedPhraseBuild.append(keys.get(phrase.charAt(count)));
			encryptedPhraseBuild.append(",");
		}
		encryptedPhrase = encryptedPhraseBuild.toString();
		return encryptedPhrase;
	}
}
