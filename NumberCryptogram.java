import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NumberCryptogram extends Cryptogram {

	private String encryptedPhrase;
	private String phrase;
	private HashMap<Integer, Character> NumberMapping;
	private HashMap<Character, Integer> convenienceKeys;
	private HashMap<Character, String> progressMap;

	public NumberCryptogram(String sequence, HashMap<Integer, Character> mapping, HashMap<Character, Integer> keys,
			HashMap<Character, String> progress) {
		super(sequence);
		phrase = sequence;
		NumberMapping = mapping;
		convenienceKeys = keys;
		progressMap = progress;
	}

	public NumberCryptogram(String phrase, String encryptedPhrase, HashMap<Integer, Character> mapping,
			HashMap<Character, Integer> keys, HashMap<Character, String> progress) {
		super(phrase);
		this.phrase = phrase;
		this.encryptedPhrase = encryptedPhrase;
		NumberMapping = mapping;
		convenienceKeys = keys;
		progressMap = progress;
	}

	public void resetProgress() {
		System.out.println(phrase);
		for (int count = 0; count < phrase.length(); count++) {
			System.out.print(phrase.charAt(count));
			progressMap.put(phrase.charAt(count), convenienceKeys.get(phrase.charAt(count)).toString());
		}
	}

	public HashMap<Integer, Character> getMapping() {
		return NumberMapping;
	}

	public char getLetter(int i) {
		return phrase.charAt(i);
	}

	public String getEncryptedPhrase() {
		StringBuilder encryptedPhraseBuild = new StringBuilder();
		for (int count = 0; count < phrase.length(); count++) {
			if (convenienceKeys.get(phrase.charAt(count)) != null)
				encryptedPhraseBuild.append(convenienceKeys.get(phrase.charAt(count)));
			encryptedPhraseBuild.append(" ");
		}
		encryptedPhrase = encryptedPhraseBuild.toString();
		return encryptedPhrase;
	}

	public String getProgress() {
		String progress = "";
		if (!progressMap.isEmpty()) {
			for (int i = 0; i < phrase.length(); i++) {
				if (phrase.charAt(i) != ' ') {
					progress += progressMap.get(phrase.charAt(i));
				} else {
					progress.concat(" ");
				}
				progress += " ";
			}
			return progress;
		}
		return "";
	}

	public boolean contains(String c) {
		return true;
	}

	public List<Integer> getOccurencesOfLetter(String s) {
		List<Integer> numberList = new ArrayList<>();
		for (int i = 0; i < phrase.length(); i++) {
			if (phrase.charAt(i) == s.charAt(0)) {
				numberList.add(i);
			}
		}
		return numberList;

	}

	public void updateProgress(List<Integer> numberList, String currentLetter) {
		progressMap.put(currentLetter.charAt(0), currentLetter);
	}

}
