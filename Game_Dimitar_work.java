import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner
public class Game {



    private Player currentPlayer;
    private HashMap<Player, Game> playerGameMapping;
    private LetterCryptogram currentCryptogram;
    private Player currentPlayer;
    private Players allPlayers;
    ArrayList<Game> savedGames;
    Scanner reader;
    String currentPlayerName;
    String currentLetter;

    {
        reader = new Scanner(System.in);
        currentPlayerName = null;
        currentLetter = null;
    }

    public Game() {
        playerGameMapping =  new HashMap<Player, Game>();
        currentPlayer = new Player( "John");
        savedGames = new ArrayList<Game>();

    }
    public Player getCurrentPlayer() {
        System.out.println("Enter your Player name: ");
        currentPlayerName = reader.next();
        currentPlayer.setName(currentPlayerName);
    }

    public void loadPlayer() {
    if(!findPlayer(currentPlayerName)) {
        System.out.println("No player record was found");
    }
    }
    public void playGame() {
        while(1) {

        }
    }
    public Cryptogram generateCryptogram() {
        makeCryptogram();
    }
    public void enterLetter() {
        System.out.println("Enter a letter: ");
        currentLetter = reader.nextChar();

    }
    public void undoLetter() {
        System.out.println("Undo letter: ");
        currentLetter = reader.nextChar();
    }
    public void viewFrequencies() {
        getFrenquencies();
    }
    public void saveGame() {

    }
    public void loadGame() {
        if(savedGames.size() == 0 ) {
            System.out.println("No saved game was found. Please save a game before loading :)");
        }
    }
}
