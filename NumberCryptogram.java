import java.util.HashMap;
import java.util.Random;

public class NumberCryptogram extends Cryptogram {

    private String phrase;
    private HashMap<Integer, Character> numberMapping;
    private HashMap<Character, Integer> convenienceKeys;
    private HashMap<Character, String> progressMap;
    private HashMap<String, Character> oppositeMap;


    public NumberCryptogram(String sequence, HashMap<Integer, Character> mapping, HashMap<Character, Integer> keys,
                            HashMap<Character, String> progress, HashMap<String, Character> opposite) {
        super(sequence, mapping, keys, progress, opposite);
        phrase = sequence;
        numberMapping = mapping;
        convenienceKeys = keys;
        progressMap = progress;
        oppositeMap = opposite;
    }

    public HashMap<Integer, Character> getMapping() {
        return numberMapping;
    }

    public void resetProgress() {
        for (int count = 0; count < phrase.length(); count++) {
            progressMap.put(phrase.charAt(count), String.valueOf(convenienceKeys.get(phrase.charAt(count))));
            numberMapping.get(String.valueOf(convenienceKeys.get(phrase.charAt(count))));
        }
    }

    public void updateProgress(String currentLetter, String currentNumber) {
        char c = numberMapping.get(Integer.parseInt(currentNumber));
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
        Random rand = new Random();
        char hintedChar = ' ';
        int r = 0;
        while (!progressMap.containsValue(String.valueOf(hintedChar)) && !convenienceKeys.containsValue(r)) {
            r = rand.nextInt(101) + 1;
            if (progressMap.containsValue(String.valueOf(r))) {
                hintedChar = numberMapping.get(r);
            }
        }

        updateProgress(String.valueOf(hintedChar), String.valueOf(r));
    }


}
