import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
public class Game {

	private HashMap<Player, Game> playerGameMapping;
	private LetterCryptogram currentCryptogram;
	private Player currentPlayer;
	private Players allPlayers;
	Scanner reader;
	String currentPlayerName;
	String currentLetter;

	public Game() {
		playerGameMapping =  new HashMap<Player, Game>();
		currentPlayer = new Player("");
		reader = new Scanner(System.in);
		currentPlayerName = null;
		currentLetter = null;
		allPlayers.getPlayers();
	}


	public void loadPlayer() {

			System.out.println("Please enter your username: ");
			currentPlayerName = reader.next();
			System.out.println("Hi " + currentPlayerName);

	}
	public void playGame() {
		int choice;
		boolean finishGameCheck = false;
		while(!finishGameCheck) {
			System.out.println("Type 1 to enter a letter");
			System.out.println("Type 2 to delete a letter");
			System.out.println("Type 3 to get a hint");
			System.out.println("Type 4 to save current progress");
			System.out.println("Type 5 to show solution");
			System.out.println("Type 6 to view the scoreboard");
			System.out.println("Type 7 to start a new game");
			System.out.println("Type 8 to exit");
			choice = reader.nextInt();
			System.out.println("Option " + choice + " chosen" );
			switch (choice) {
				case 1:
					enterLetter();
					break;
				case 2:
					undoLetter();
					break;
				case 3:
					getHint();
					break;
				case 4:
					saveGame();
					break;
				case 5:
					displaySolution();
					break;
				case 6:
					viewScoreboard();
					break;
				case 7:
					//
					break;
				case 8:
					//
					break;
				default:
					System.out.println("Option " + choice + " is not valid. Please try again");
					break;
			}
		}
	}
	public Cryptogram generateCryptogram() {
		//makeCryptogram();
		return null;
	}
	public void enterLetter() {
		System.out.println("Enter a letter: ");
		currentLetter = reader.next();

	}
	public void undoLetter() {
		System.out.println("Undo letter: ");
		currentLetter = reader.next();
	}
	public void viewFrequencies() {
		//getFrenquencies();
	}
	public void saveGame() {
		String option;
		System.out.println("Would you like to save your progress?");
		option = reader.next();
		if(option.toLowerCase().equals("yes")){
                //save current state
		}
		else {
                //do nothing
		}


	}
	public void loadGame() {
		if (true/*file not exists*/) {
			System.out.println("No saved game was found. Please save a game before loading :)");
		}
	}
	public void viewScoreboard() {

	allPlayers.getAllPlayersAccuracies();
	allPlayers.getAllPlayersCryptogramsPlayed();
	allPlayers.getAllPlayersCryptos();
	allPlayers.getAllPlayersTimes();
	}
	public void getHint() {

	}
	public void displaySolution() {

	}
}
