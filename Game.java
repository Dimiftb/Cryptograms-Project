import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
public class Game {

	private HashMap<Player, Cryptogram> playerGameMapping;
	private Cryptogram currentCryptogram;
	private Player currentPlayer;
	private Players allPlayers;
	Scanner reader;
	String currentPlayerName;
	String currentLetter;

	public Game() {
		playerGameMapping =  new HashMap<Player, Cryptogram>();
		currentPlayer = new Player("");
		reader = new Scanner(System.in);
		currentPlayerName = null;
		currentLetter = null;
		allPlayers = new Players();
	}


	public void loadPlayer() {
		String option;
		String name;
		System.out.println("Type 1 to create a new account  ");
		System.out.println("Type 2 to load to an existing one  ");
		option = reader.next().toLowerCase();

		if(option.equals("1")) {
			System.out.println("Please enter your username: ");
			name = reader.next();
			allPlayers.addPlayer(name);
			currentPlayer = allPlayers.findPlayer(name);
		}
		else if (option.equals("2")) {
			System.out.println("Please enter your account name: ");
			name = reader.next();
			currentPlayer = allPlayers.findPlayer(name);
		}
		else {
			System.out.println("Invalid option. Please try again.");
		}
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
			System.out.println("Type 7 to reset");
			System.out.println("Type 8 to start a new game");
			System.out.println("Type 9 to exit");
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
					// reset
					break;
				case 8:
					//start a new game
					break;
				case 9:
					return;
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

		allPlayers.showTop10();
	}
	public void getHint() {

	}
	public void displaySolution() {

	}
}
