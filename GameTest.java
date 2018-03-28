import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class GameTest {
	private Cryptogram crypt;


	@Before
	public void setUp() {
		String phrase = "test";
		HashMap<Integer, Character> testMap = new HashMap<Integer, Character>();
		HashMap<Character, Integer> testMap2 = new HashMap<Character, Integer>();
		HashMap<Character, String> ptestMap = new HashMap<Character, String>();
		HashMap<String, Character>  ptestMap2 = new HashMap<String, Character>();
		testMap.put(1, 't');
		testMap.put(2, 'e');
		testMap.put(3, 's');
		testMap2.put('t', 1);
		testMap2.put('e', 2);
		testMap2.put('s', 3);
		ptestMap.put('t', Integer.toString(1));
		ptestMap.put('e', Integer.toString(2));
		ptestMap.put('s', Integer.toString(3));
		ptestMap2.put(Integer.toString(1), 't');
		ptestMap2.put(Integer.toString(2), 'e');
		ptestMap2.put(Integer.toString(3), 's');

		crypt = new NumberCryptogram(phrase, testMap, testMap2, ptestMap, ptestMap2);
		// Cryptogram test = new NumberCryptogram
	}

	@Test
	public void getPhraseTest() {
		assertEquals(crypt.getPhrase(), "test");
	}

	@Test
	public void incompleteCheckTest() {
		assertEquals(crypt.completeCheck(), false);
	}
	
	@Test
	public void progressTest(){
		assertEquals(crypt.getProgress(), "1 2 3 1 ");
		crypt.updateProgress("t", "1");
		assertEquals(crypt.getProgress(), "t 2 3 t ");
	}
	
	public void completeCheckTest() {
		crypt.updateProgress("t", "1");
		crypt.updateProgress("e", "2");
		crypt.updateProgress("s", "3");
		assertEquals(crypt.completeCheck(), true);
	}

}
