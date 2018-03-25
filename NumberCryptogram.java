import java.util.HashMap;
import java.util.Map;

public class NumberCryptogram extends Cryptogram{

	HashMap<Object, Object> NumberMapping;
	String sequence;

	public NumberCryptogram(String sequence, Map<Integer,Character> mapping) {
		super(sequence, mapping);

	}

	public char getLetter(int i) {
		return sequence.charAt(i);
	}

}
