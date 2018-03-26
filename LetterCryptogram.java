import java.util.HashMap;
import java.util.List;

public class LetterCryptogram extends Cryptogram {

	private HashMap<Character, Character> letterMapping;
	private HashMap<Character, Character> keys;
	private String phrase;
	private String encryptedPhrase;
	
	
	/*public LetterCryptogram(String sentance, HashMap<Character, Character> letterMappings) {
		Random randMapping = new Random();
		int randomNumber;
		phrase = super.phrase;
		for (int i = 0; i < phrase.length(); i++) {
			randomNumber = randMapping.nextInt();
			letterMapping.put(randomNumber, phrase.charAt(i));
			keys.put(phrase.charAt(i), randomNumber);
		}
	}*/
	public LetterCryptogram(String encryptedPhrase, HashMap<Character, Character> letterMapping, HashMap<Character, Character> keys)
	{
		super(encryptedPhrase);
		phrase = encryptedPhrase;
		this.letterMapping = letterMapping;
		this.keys = keys;
	}


	public HashMap<Character, Character> getMapping()
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
			if(keys.get(phrase.charAt(count)) != null)
			encryptedPhraseBuild.append(keys.get(phrase.charAt(count)));
			encryptedPhraseBuild.append(" ");
		}
		encryptedPhrase = encryptedPhraseBuild.toString();
		return encryptedPhrase;
	}


	@Override
	public boolean contains(String c) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public List<Integer> getOccurencesOfLetter(String s) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String getProgress() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void updateProgress(List<Integer> numberList, String currentLetter) {
		// TODO Auto-generated method stub
		
	}

}
