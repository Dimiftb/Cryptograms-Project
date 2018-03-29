import java.io.*;
import java.util.Random;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Timer;

public class Game {

    private HashMap<Player, Cryptogram> playerGameMapping;
    private Cryptogram currentCryptogram;
    private Player currentPlayer;
    private Players allPlayers;
    Scanner reader;
    String currentPlayerName;
    String currentLetter;
    MyTimer t;

    public Game() {
        playerGameMapping = new HashMap<Player, Cryptogram>();
        currentPlayer = new Player("");
        reader = new Scanner(System.in);
        currentPlayerName = null;
        currentLetter = null;
        allPlayers = new Players();
        // currentCryptogram = new Cryptogram();
    }

    public void loadPlayer() {
        String option;
        String name = "";
        System.out.println("Type 1 to create a new account  ");
        System.out.println("Type 2 to load to an existing one  ");
        option = reader.next().toLowerCase();
        if (!option.equals("1") && !option.equals("2")) {
            System.out.println("Invalid option. Please choose between 1 or 2. Thanks");
            reader.nextLine();
            loadPlayer();
        }

        if (option.equals("1")) {
            System.out.println("Please enter your username: ");
            name = reader.next();
            allPlayers.addPlayer(name);
            currentPlayer = allPlayers.findPlayer(name);
        } else if (option.equals("2")) {
            System.out.println("Ok, here are the possibilities:");
            for (Player pl : allPlayers.getPlayers()) {
                System.out.println(pl.getName());
            }
            boolean validFlag = false;
            int i = 0;
            while (validFlag == false) {
                System.out.println("Please enter your account name: ");
                name = reader.next();
                currentPlayer = allPlayers.findPlayer(name);
                if (i == 2) {
                    System.out.println("If you haven't got an account already please create one");
                    loadPlayer();
                }
                if (currentPlayer != null) {
                    validFlag = true;
                } else {
                    System.out.print("User account not found! ");
                }
                i++;
            }
        } else {
            System.out.println("Invalid option. Please try again.");
        }
        currentPlayerName = name;
        System.out.println("Hi " + currentPlayerName + "!");

    }

    public void playGame() {
        int hints = 0;
        currentPlayer.incrementPlayedCryptos();
        t = new MyTimer();
        if (currentCryptogram == null)
            currentCryptogram = generateCryptogram();

        int choice;
        // System.out.println(currentCryptogram.getEncryptedPhrase());
        // System.out.println("cryptogram printed");
        while (true) {
            System.out.println(currentCryptogram.getProgress());
            System.out.println("Type 1 to enter a letter");
            System.out.println("Type 2 to delete a letter");
            System.out.println("Type 3 to get a hint");
            System.out.println("Type 4 to view frequencies");
            System.out.println("Type 5 for more options");
            System.out.print("Your choice: ");
            choice = reader.nextInt();
            System.out.println("Option " + choice + " chosen");
            switch (choice) {
                case 1:
                    enterLetter();
                    if (currentCryptogram.completeCheck()) {
                        System.out.println("Well done you won!");
                        t.stopTimer();
                        currentPlayer.incrementCompletedCryptos();
                        currentPlayer.updateAvgTime(t.getInterval());

                        currentCryptogram.resetProgress();
                        return;
                    }
                    break;
                case 2:
                    undoLetter();
                    break;
                case 3:
                    if (hints < 5) {
                        getHint();
                        t.penaltyTime();
                        if (currentCryptogram.completeCheck()) {
                            System.out.println("Well done you won!");
                            currentCryptogram.resetProgress();
                            t.stopTimer();
                            System.out.print(t.getInterval());
                            currentPlayer.incrementCompletedCryptos();
                            currentPlayer.updateAvgTime(t.getInterval());
                            hints++;
                        } else {
                            System.out.println("Out of hints!");
                        }
                        return;
                    }
                    break;
                case 4:
                    viewFrequencies();
                    break;
                case 5:
                    System.out.println("Type 1 to save current progress");
                    System.out.println("Type 2 to show solution");
                    System.out.println("Type 3 to view the scoreboard");
                    System.out.println("Type 4 to reset");
                    System.out.println("Type 5 to start a new game");
                    System.out.println("Type 6 for help");
                    System.out.println("Type 7 to get back to the cryptogram");
                    System.out.println("Type 8 to exit and return to main menu");
                    int input = reader.nextInt();
                    switch (input) {
                        case 1:
                            saveGame();
                            break;
                        case 2:
                            displaySolution();
                            t.stopTimer();
                            return;
                        case 3:
                            viewScoreboard();
                        case 4:
                            resetProgress();
                            break;
                        case 5:
                            currentCryptogram = generateCryptogram();
                            break;
                        case 6:
                            help("game");
                            break;
                        case 7:
                            break;
                        case 8:
                            return;
                        default:
                            System.out.println("Option " + input + " is not valid. Please try again");
                            break;
                    }
                    break;
                default:
                    System.out.println("Option " + choice + " is not valid. Please try again");
                    break;
            }
        }
    }

    private Cryptogram generateCryptogram() {
        CryptogramFactory factory = new CryptogramFactory();
        return factory.makeCryptogram("number");

    }

    public void enterLetter() {

        try {
            System.out.println("Enter a number in the cryptogram");
            String currentNumber = reader.next();
            System.out.println("Enter a letter: ");
            currentLetter = reader.next();
            if (currentCryptogram.getPhrase().contains(currentLetter)
                    && currentCryptogram.getEncryptedPhrase().contains(currentNumber)) {
                // find index of all of these letters in the cryptogram and
                // replace the instances of it

                currentCryptogram.updateProgress(currentLetter, currentNumber);
            } else {
                System.out.println("This letter is not present!");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please try again.");
        }

    }

    public void undoLetter() {
        System.out.println("Enter a letter in the cryptogram that you want to undo: ");
        currentLetter = reader.next();
        currentCryptogram.undo(currentLetter.charAt(0));
    }

    public void viewFrequencies() {
        System.out.println("Frequencies: " + currentCryptogram.getFrequencies());
    }

    public void saveGame() {
        String option;
        System.out.println("Would you like to save your progress?");
        System.out.println("Type yes or no");
        option = reader.next();
        System.out.println(option);
        if (option.toLowerCase().equals("yes")) {
            try {
                FileWriter fw = new FileWriter("savedGames" + currentPlayerName + ".txt", false);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw);
                System.out.println(currentPlayer.getName());
                out.println(currentPlayer.getName());
                out.println(currentCryptogram.getPhrase());
                System.out.println(currentCryptogram.getEncryptedPhrase());
                out.println(currentCryptogram.getEncryptedPhrase());
                System.out.println(currentCryptogram.getMapping());
                out.println(currentCryptogram.getMapping());
                out.println(currentCryptogram.getProgressMap());
                out.println(t.getInterval());
                out.close();
                bw.close();
                fw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (option.toLowerCase().equals("no")) {
            return;
        } else {
            System.out.println("Invalid option please choose again");
            return;
        }

    }

    public void loadGame() {
        String option;
        System.out.println("Would you like to load a game(if you have any saved)?");
        option = reader.next().toLowerCase();
        if (option.equals("yes")) {
            try {
                File file = new File("savedGames" + currentPlayerName + ".txt");
                Scanner fileReader = new Scanner(file);
                String tempName;
                String tempPhrase;
                String tempEncPhrase;
                boolean flag = false;
                HashMap<Integer, Character> tempMapping = new HashMap<>();
                HashMap<Character, Integer> tempKeys = new HashMap<>();
                HashMap<Character, String> tempProgressMap = new HashMap<>();
                HashMap<String, Character> oppositeTempProgressMap = new HashMap<>();
                while (fileReader.hasNextLine()) {
                    tempName = fileReader.nextLine();
                    // I believe the problem is here!!!
                    if (currentPlayer.getName().equals(allPlayers.findPlayer(tempName).getName())) {
                        tempPhrase = fileReader.nextLine();
                        tempEncPhrase = fileReader.nextLine();
                        StringTokenizer forHashMap = new StringTokenizer(fileReader.nextLine(), "{}, =", false);
                        while (forHashMap.hasMoreTokens()) {
                            int firstToken = (Integer) Integer.parseInt(forHashMap.nextToken());
                            char secondToken = forHashMap.nextToken().charAt(0);
                            tempMapping.put(firstToken, secondToken);
                            tempKeys.put(secondToken, firstToken);
                        }
                        StringTokenizer forProgressMap = new StringTokenizer(fileReader.nextLine(), "{}, =", false);
                        while (forProgressMap.hasMoreTokens()) {
                            char firstToken = forProgressMap.nextToken().charAt(0);
                            String secondToken = forProgressMap.nextToken();
                            tempProgressMap.put(firstToken, secondToken);
                            oppositeTempProgressMap.put(secondToken, firstToken);
                        }

                        t.setInterval(fileReader.nextInt());
                        flag = true;
                        System.out.println("Welcome back to your saved game.");
                        currentCryptogram = new NumberCryptogram(tempPhrase, tempMapping, tempKeys, tempProgressMap,
                                oppositeTempProgressMap);
                        // The progress needs to be saved now too then passed
                        // into the constructor - didn't want to mess up the
                        // code though
                    } else {
                        fileReader.nextLine();
                    }
                    if (!flag) {
                        System.out.println("No saved game was found. Please save a game before loading :)");
                    }
                }
                fileReader.close();

            } catch (FileNotFoundException e) {
                System.out.println("No saved game was found. Please save a game before loading :)");
            } catch (NullPointerException e) {

            }
        }
        playGame();
    }

    public void viewScoreboard() {

        allPlayers.showTop10();
    }

    public void getHint() {
        currentCryptogram.getOneHint();
    }

    public void displaySolution() {
        System.out.println(currentCryptogram.getPhrase());
    }

    public Players getPlayers() {
        return allPlayers;
    }

    public void resetProgress() {
        currentCryptogram.resetProgress();
    }

    public void help(String option) {
        if (option.equals("game")) {

            System.out.println("Welcome to the help section!");
            System.out.println("Description: ");
            System.out.println("1 - enter a letter - choosing option 1 you will be prompted to choose the position you want to place a letter from the alphabet in order to crack the cypher.");
            System.out.println(" 2 - removing a letter -if you change your mind about a previous decision you can always undo a letter you've placed and replace it with a new one. ");
            System.out.println("3 - get a hint - you're stuck? Don't worry! You can use one of your hints. If you use one of your hints one letter from the cypher will be revealed to you. Just don't forget you can use a maximum of 4 hints.");
            System.out.println("4 - save current progress - If you'd like to continue your game later on you can save it, so that later on you can continue from where you left.");
            System.out.println("5 - show solution  - Perhaps you're stuck and out of hints, or maybe you just want to see the solution, option 5 will display it to you.");
            System.out.println("6 - view the scoreboard - see how you've done compared to other players. Scoreboard will display you information about players' overall ratings. ");
            System.out.println("7 - reset - if you decide you'd like to start over you can always use the reset button to start again.");
            System.out.println("8 - start a new game - instead of going back to main menu to start a new game with different cryptogram you can simply choose option 8 and get cracking.");

        } else if (option.equals("menu")) {
            System.out.println("Welcome to the help section!");
            System.out.println("Description: ");
            System.out.println("1 - start a new game - if you'd like to start a solving a new cryptogram choose option 1. Good luck!");
            System.out.println(" 2 - load a previous game - Loading a previous game would allow you to continue from the point you've last saved your previous game.");
            System.out.println("3 - view scoreboard -see how you've done compared to other players. Scoreboard will display you information about players' overall ratings.");

        } else {
            System.out.println("only 2 options for help");
        }
        System.out.println("Good luck!");
        return;
    }
}
