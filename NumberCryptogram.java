import java.util.HashMap;

public class NumberCryptogram extends Cryptogram{

	HashMap<Integer, Character> NumberMapping;
	String sequence;

	public NumberCryptogram(String sentence) {
		sequence = sentence;
		for (int i = 0; i < sequence.length(); i++) {
			NumberMapping.put(i, sequence.charAt(i));
		}
	}

	public char getLetter(int i) {
		return sequence.charAt(i);
	}

}
