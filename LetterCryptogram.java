import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class LetterCryptogram extends Cryptogram {

    private String phrase;
    private HashMap<Character, Character> letterMapping;
    private HashMap<Character, Character> convenienceKeys;
    private HashMap<Character, String> progressMap;
    private HashMap<String, Character> oppositeMap;


    public LetterCryptogram(String sequence, HashMap<Character, Character> mapping, HashMap<Character, Character> keys,
                            HashMap<Character, String> progress, HashMap<String, Character> opposite) {
        super(sequence, mapping, keys, progress, opposite);
        phrase = sequence;
        letterMapping = mapping;
        convenienceKeys = keys;
        progressMap = progress;
        oppositeMap = opposite;
    }


    public HashMap<Character, Character> getMapping() {
        return letterMapping;
    }

    public void resetProgress() {
        for (int count = 0; count < phrase.length(); count++) {
            progressMap.put(phrase.charAt(count), String.valueOf(convenienceKeys.get(phrase.charAt(count))));
            letterMapping.get(String.valueOf(convenienceKeys.get(phrase.charAt(count))));
        }
    }


    public void updateProgress(String currentLetter, String currentNumber) {
            char c = letterMapping.get(currentNumber.charAt(0));
            progressMap.put(c, currentLetter);
            oppositeMap.put(String.valueOf(currentLetter), c);

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
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        ArrayList<Character> alphabetMap = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            alphabetMap.add(alphabet.charAt(i));
        }
        Random rand = new Random();
        char hintedChar = ' ';
        char r = ' ';
        while (!progressMap.containsValue(String.valueOf(hintedChar)) && !convenienceKeys.containsValue(r)) {
            r = alphabetMap.get(rand.nextInt(25));
            if (progressMap.containsValue(String.valueOf(r))) {
                hintedChar = letterMapping.get(r);
            }
        }

        updateProgress(String.valueOf(hintedChar), String.valueOf(r));
    }

}
