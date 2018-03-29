import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Cryptogram {

	private String phrase;
	private HashMap<?,?> symbolMappings;
	private HashMap<?,?> keysMappings;
	private HashMap<Character, String> progressMap;
	private HashMap<String, Character> oppositeMap;

	public Cryptogram(String encryptedPhrase,HashMap<?,?> mappings,HashMap<?,?> keys, HashMap<Character, String> progress, HashMap<String, Character> opposite) {
 		phrase = encryptedPhrase;
		symbolMappings = mappings;
		keysMappings = keys;
 		progressMap = progress;
 		oppositeMap = opposite;

	}

	public HashMap<Character, Integer> getFrequencies() {
		HashMap<Character, Integer> frequencies = new HashMap<>();
		char letter;
		for (int i = 0; i < phrase.length(); i++) {
			letter = phrase.toLowerCase().charAt(i);
			if (frequencies.containsKey(letter)) {
				frequencies.replace(letter, frequencies.get(letter) + 1);
			} else {
				frequencies.put(letter, 1);
			}

		}
		return frequencies;
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
	public char getLetter(int i) {
		return phrase.charAt(i);
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
	public HashMap<Character, String> getProgressMap() {
		return progressMap;
	}
	public String getPhrase()
	{
		return phrase;
	}
	public String getEncryptedPhrase() {
		String encryptedPhrase;
		StringBuilder encryptedPhraseBuild = new StringBuilder();
		for (int count = 0; count < phrase.length(); count++) {
			if (keysMappings.get(phrase.charAt(count)) != null)
				encryptedPhraseBuild.append(keysMappings.get(phrase.charAt(count)));
			encryptedPhraseBuild.append(" ");
		}
		encryptedPhrase = encryptedPhraseBuild.toString();
		return encryptedPhrase;
	}
	public abstract HashMap<?, ?> getMapping();
	public abstract void updateProgress(String currentLetter, String currentNumber);
	public abstract void resetProgress();
	public abstract void undo(char c);
	public abstract void getOneHint();


}
