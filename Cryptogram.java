import java.util.HashMap;

public class Cryptogram {

	String phrase;
	

	public Cryptogram() {	
	}

	public HashMap<Character, Integer> getFrequencies() {
		HashMap<Character, Integer> frequencies = new HashMap<>();
		char letter;
		for(int i = 0; i < phrase.length(); i++ ) {
			letter = phrase.toLowerCase().charAt(i);
			if(frequencies.containsKey(letter)) {
				frequencies.replace(letter, frequencies.get(letter) + 1);
			}
			else {
				frequencies.put(letter, 1);
			}

		}
	/*	frequencies.put('a', 0);
		frequencies.put('b', 0);
		frequencies.put('c', 0);
		frequencies.put('d', 0);
		frequencies.put('e', 0);
		frequencies.put('f', 0);
		frequencies.put('g', 0);
		frequencies.put('h', 0);
		frequencies.put('i', 0);
		frequencies.put('j', 0);
		frequencies.put('k', 0);
		frequencies.put('l', 0);
		frequencies.put('m', 0);
		frequencies.put('n', 0);
		frequencies.put('o', 0);
		frequencies.put('p', 0);
		frequencies.put('q', 0);
		frequencies.put('r', 0);
		frequencies.put('s', 0);
		frequencies.put('t', 0);
		frequencies.put('u', 0);
		frequencies.put('v', 0);
		frequencies.put('w', 0);
		frequencies.put('x', 0);
		frequencies.put('y', 0);
		frequencies.put('z', 0);

		String phraseLower = phrase.toLowerCase();
		for (int i = 0; i < phraseLower.length(); i++) {
			switch (phraseLower.charAt(i)) {
			case 'a':
				frequencies.put('a', frequencies.get('a') + 1);
				break;
			case 'b':
				frequencies.put('a', frequencies.get('a') + 1);
				break;
			case 'c':
				frequencies.put('a', frequencies.get('a') + 1);
				break;
			case 'd':
				frequencies.put('a', frequencies.get('a') + 1);
				break;
			case 'e':
				frequencies.put('a', frequencies.get('a') + 1);
				break;
			case 'f':
				frequencies.put('a', frequencies.get('a') + 1);
				break;
			case 'g':
				frequencies.put('a', frequencies.get('a') + 1);
				break;
			case 'h':
				frequencies.put('a', frequencies.get('a') + 1);
				break;
			case 'i':
				frequencies.put('a', frequencies.get('a') + 1);
				break;
			case 'j':
				frequencies.put('a', frequencies.get('a') + 1);
				break;
			case 'k':
				frequencies.put('a', frequencies.get('a') + 1);
				break;
			case 'l':
				frequencies.put('a', frequencies.get('a') + 1);
				break;
			case 'm':
				frequencies.put('a', frequencies.get('a') + 1);
				break;
			case 'n':
				frequencies.put('a', frequencies.get('a') + 1);
				break;
			case 'o':
				frequencies.put('a', frequencies.get('a') + 1);
				break;
			case 'p':
				frequencies.put('a', frequencies.get('a') + 1);
				break;
			case 'q':
				frequencies.put('a', frequencies.get('a') + 1);
				break;
			case 'r':
				frequencies.put('a', frequencies.get('a') + 1);
				break;
			case 's':
				frequencies.put('a', frequencies.get('a') + 1);
				break;
			case 't':
				frequencies.put('a', frequencies.get('a') + 1);
				break;
			case 'u':
				frequencies.put('a', frequencies.get('a') + 1);
				break;
			case 'v':
				frequencies.put('a', frequencies.get('a') + 1);
				break;
			case 'w':
				frequencies.put('a', frequencies.get('a') + 1);
				break;
			case 'x':
				frequencies.put('a', frequencies.get('a') + 1);
				break;
			case 'y':
				frequencies.put('a', frequencies.get('a') + 1);
				break;
			case 'z':
				frequencies.put('a', frequencies.get('a') + 1);
				break;
			}
		} */
		
		return frequencies;

	}

}
