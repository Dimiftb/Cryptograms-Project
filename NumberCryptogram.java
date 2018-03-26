import java.util.HashMap;
import java.util.Map;

public class NumberCryptogram extends Cryptogram{

	private String encryptedPhrase;
	private String phrase;
	private HashMap<Integer, Character> NumberMapping;
	private HashMap<Character, Integer> convenienceKeys;
	

	public NumberCryptogram(String sequence, HashMap<Integer,Character> mapping, HashMap<Character,Integer> keys) {
		super(sequence);
		phrase = sequence;
		NumberMapping = mapping;
		convenienceKeys = keys;

	}
	public NumberCryptogram(String phrase, String encryptedPhrase, HashMap<Integer,Character> mapping, HashMap<Character,Integer> keys)
	{
		super(phrase);
		this.phrase = phrase;
		this.encryptedPhrase = encryptedPhrase;
		NumberMapping = mapping;
		convenienceKeys = keys;
	}
	public HashMap<Integer, Character> getMapping()
	{
		return NumberMapping;
	}
	public char getLetter(int i) {
		return phrase.charAt(i);
	}
	public String getEncryptedPhrase()
	{
		StringBuilder encryptedPhraseBuild = new StringBuilder();
		for(int count = 0; count < phrase.length(); count++)
		{
			if(convenienceKeys.get(phrase.charAt(count)) != null)
			encryptedPhraseBuild.append(convenienceKeys.get(phrase.charAt(count)));
			encryptedPhraseBuild.append(" ");
		}
		encryptedPhrase = encryptedPhraseBuild.toString();
		return encryptedPhrase;
	}
}
