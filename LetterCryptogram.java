import java.util.HashMap;
import java.util.List;

public class LetterCryptogram extends Cryptogram {

	private String phrase;

	private HashMap<Character, Character> letterMapping;
	private HashMap<Character, Character> convenienceKeys;
	private HashMap<Character, String> progressMap;
	private HashMap<String, Character> oppositeMap;




	public LetterCryptogram(String sequence, HashMap<Character, Character> mapping, HashMap<Character, Character> keys, HashMap<Character, String> progress,  HashMap<String, Character> opposite)
	{
		super(sequence, mapping, keys, progress, opposite);
		phrase = sequence;
		letterMapping = mapping;
		convenienceKeys = keys;
		progressMap = progress;
		oppositeMap = opposite;
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
	public boolean completeCheck() {
		// TODO Auto-generated method stub
		return false;
	}



}
