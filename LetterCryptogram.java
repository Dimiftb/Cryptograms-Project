import java.util.HashMap;

public class LetterCryptogram extends Cryptogram {

	HashMap<Integer, Character> letterMapping;
	String phrase;

	public LetterCryptogram() {
		phrase = super.phrase;
		for (int i = 0; i < phrase.length(); i++) {
			letterMapping.put(i, phrase.charAt(i));
		}
	}

	public char getLetter(int i) {
		if (i > 0 && i < phrase.length()) {
			return phrase.charAt(i);
		} else {
			return '#';
		}

	}

}
