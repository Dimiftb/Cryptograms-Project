import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class NumberCryptogram extends Cryptogram {

	private String encryptedPhrase;
	private String phrase;
	private HashMap<Integer, Character> numberMapping;
	private HashMap<Character, Integer> convenienceKeys;
	private HashMap<Character, String> progressMap;
	private HashMap<String, Character> oppositeMap;
	private HashMap<String, Character> origIntToOrigChar;

	public NumberCryptogram(String sequence, HashMap<Integer, Character> mapping, HashMap<Character, Integer> keys,
			HashMap<Character, String> progress, HashMap<String, Character> opposite) {
		super(sequence);
		phrase = sequence;
		numberMapping = mapping;
		convenienceKeys = keys;
		progressMap = progress;
		oppositeMap = opposite;
	}

	public void resetProgress() {
		for (int count = 0; count < phrase.length(); count++) {
			progressMap.put(phrase.charAt(count), String.valueOf(convenienceKeys.get(phrase.charAt(count))));
			numberMapping.get(String.valueOf(convenienceKeys.get(phrase.charAt(count))));
		}
	}

	public HashMap<Integer, Character> getMapping() {
		return numberMapping;
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
					progress += " ";
				}
				progress += " ";
			}
			return progress;
		}
		return "";
	}

	public void updateProgress(String currentLetter, String currentNumber) {
		char c = numberMapping.get(Integer.parseInt(currentNumber));
		progressMap.put(c, currentLetter);
		oppositeMap.put(String.valueOf(currentLetter), c);
	}

	public boolean completeCheck() {

		String check = getProgress();
		check = check.replaceAll("   ", "#");
		check = check.replaceAll(" ", "");
		check = check.replaceAll("#", " ");

		if (check.equals(phrase)) {
			return true;
		}
		return false;

	}

	public void undo(char whatToUndo) {
		if (progressMap.containsValue(String.valueOf(whatToUndo))) {
			progressMap.put(oppositeMap.get(String.valueOf(whatToUndo)),
					String.valueOf(convenienceKeys.get(oppositeMap.get(String.valueOf(whatToUndo)))));
		} else {
			System.out.println("Invalid character. Please try again.");
		}
	}

	public void getOneHint() {
		Random rand = new Random();
		char hintedChar = ' ';
		int r = 0;
		while (!progressMap.containsValue(String.valueOf(hintedChar)) && !convenienceKeys.containsValue(r)) {
			r = rand.nextInt(101)+1;
			if (progressMap.containsValue(String.valueOf(r))) {
				hintedChar = numberMapping.get(r);
			}
		}

		updateProgress(String.valueOf(hintedChar), String.valueOf(r));
	}

	public HashMap<Character, String> getProgressMap() {
		return progressMap;
	}

}
