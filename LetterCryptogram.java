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

	@Override
	public String getProgress() {
		// TODO Auto-generated method stub
		return null;
	}



	public void resetProgress()
	{
		
	}


	@Override
	public void updateProgress(String currentLetter, String currentNumber) {
		// TODO Auto-generated method stub
		
	}
	public void undo(char c)
	{
		
	}


	@Override
	public void getOneHint() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public String getEncryptedPhrase() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean completeCheck() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public HashMap<?, ?> getProgressMap() {
		// TODO Auto-generated method stub
		return null;
	}


}
