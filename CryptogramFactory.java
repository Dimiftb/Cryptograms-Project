import java.io.*;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class CryptogramFactory {

    Random rand;

    public CryptogramFactory() {
        rand = new Random();
    }

    //change method return type
    public LetterCryptogram makeCryptogram(String type) {

        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        HashMap<Character, Character> letterMappings = new HashMap<Character, Character>();
        HashMap<Integer, Character> numberMappings = new HashMap<Integer, Character>();
        HashMap<Integer, Character> letterKeys = new HashMap<Integer, Character>();
        HashMap<Integer, Character> numberKeys = new HashMap<Integer, Character>();

           String sentance = getPhrase();
           if(type.equals("number")) {
               for( int i = 0 ; i < sentance.length(); i++) {
                   int m = rand.nextInt(sentance.length());
                   numberMappings.put(m, sentance.charAt(i));
                   numberKeys.put(i, sentance.charAt(i));
                   NumberCryptogram cryptogram = new NumberCryptogram(sentance, numberMappings);
                   //return new NumberCryptogram(sentance, numberMappings);
               }
           }
           else if( type.equals("letter")) {
                for(int i = 0; i < sentance.length(); i++) {
                    char randomLetter = alphabet.charAt(rand.nextInt(sentance.length()));
                    letterMappings.put(randomLetter,sentance.charAt(i));
                    letterKeys.put(i,sentance.charAt(i));
                    return new LetterCryptogram(sentance, letterMappings);
               }
           }
           else {
               System.out.println("Currently supporting only two types of cryptograms");
           }
        return null;
    }


    public String getPhrase() {
        try {
            File file = new File("Cryptograms.txt");
            Scanner reader = new Scanner(file);
            if (!file.exists()) {
                boolean check = file.createNewFile();
                System.out.println("file creation check: " + check);
            }
            String[] sentances = new String[50];
            int i = 0;
            while (reader.hasNext()) {
                sentances[i] = reader.nextLine();
                i++;
            }
            int randomNumber = rand.nextInt(i);
            return sentances[randomNumber];
        } catch (IOException e) {
            System.out.println("File error");
        }
        return null;
}
    public static void main(String[] args)  {
        CryptogramFactory factory = new CryptogramFactory();
            for(int i = 0; i <= 4; i ++) {
                String phrase = factory.getPhrase();
                System.out.println((i + 1) + ": " + phrase);
            }
    }
}
