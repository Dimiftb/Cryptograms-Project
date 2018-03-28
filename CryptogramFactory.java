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
    public Cryptogram makeCryptogram(String type) {

        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        HashMap<Character, Character> letterMappings = new HashMap<Character, Character>();
        HashMap<Integer, Character> numberMappings = new HashMap<Integer, Character>();
        HashMap<Character, Character> letterKeys = new HashMap<Character, Character>();
        HashMap<Character, Integer> numberKeys = new HashMap<Character, Integer>();
        HashMap<Character, String> progressMapping = new  HashMap<Character, String>();
        HashMap<String, Character> opMapping = new  HashMap<String, Character>();
        HashMap<String, Character> origIntToChar = new  HashMap<String, Character>();
        int m;
           String sentence = getPhrase();
           
           if(type.equals("number")) {
               for( int i = 0 ; i < sentence.length(); i++) {
            	   if(sentence.charAt(i) != ' ')
            	   {
            		   if(!numberMappings.containsValue(sentence.charAt(i)))
            		   {
	            		   m = rand.nextInt(101)+1;
	            		   while(numberMappings.containsKey(m))
	            		   {	
	            			   System.out.println("m value: " + m);
	            			   m = rand.nextInt(101)+1;
	            		   }
            		   }
            		   else
            		   {
            			   m = numberKeys.get(sentence.charAt(i));
            		   }
                       numberMappings.put(m, sentence.charAt(i));
                       numberKeys.put(sentence.charAt(i), m);
                       progressMapping.put(sentence.charAt(i), Integer.toString(m));
                       opMapping.put(Integer.toString(m), sentence.charAt(i));
                      
            	   }
                   
                   
               }
               NumberCryptogram cryptogram = new NumberCryptogram(sentence, numberMappings, numberKeys, progressMapping, opMapping);
               //cryptogram.resetProgress();
               return cryptogram;
           }
           else if( type.equals("letter")) {
                for(int i = 0; i < sentence.length(); i++) {
                	if(sentence.charAt(i) != ' ')
             	    {
                		char randomLetter = alphabet.charAt(rand.nextInt(30));
                		letterMappings.put(randomLetter,sentence.charAt(i));
                		letterKeys.put(sentence.charAt(i), randomLetter);
             	    }
                   
               }
               return new LetterCryptogram(sentence, letterMappings, letterKeys);
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
            String[] sentences = new String[50];
            int i = 0;
            while (reader.hasNext()) {
                sentences[i] = reader.nextLine();
                i++;
            }
            int randomNumber = rand.nextInt(i);
            reader.close();
            return sentences[randomNumber];
            
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
