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
			while (validFlag == false) {
				System.out.println("Please enter your account name: ");
				name = reader.next();
				currentPlayer = allPlayers.findPlayer(name);
				if (currentPlayer != null) {
					validFlag = true;
				} else {
					System.out.print("User account not found! ");
				}
			}
		} else {
			System.out.println("Invalid option. Please try again.");
		}
		currentPlayerName = name;
		System.out.println("Hi " + currentPlayerName);

	}

	public void playGame() {
		int hints = 0;
		currentPlayer.incrementPlayedCryptos();
		t = new MyTimer();
		if (currentCryptogram == null)
			currentCryptogram = generateCryptogram();

		int choice;
		boolean finishGameCheck = false;
		// System.out.println(currentCryptogram.getEncryptedPhrase());
		// System.out.println("cryptogram printed");
		while (!finishGameCheck) {
			System.out.println(currentCryptogram.getProgress());
			System.out.println("Type 1 to enter a letter");
			System.out.println("Type 2 to delete a letter");
			System.out.println("Type 3 to get a hint");
			System.out.println("Type 4 to save current progress");
			System.out.println("Type 5 to show solution");
			System.out.println("Type 6 to view the scoreboard");
			System.out.println("Type 7 to reset");
			System.out.println("Type 8 to start a new game");
			System.out.println("Type 9 to exit");
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
				saveGame();
				break;
			case 5:
				displaySolution();

				t.stopTimer();
				return;
			case 6:
				viewScoreboard();
				break;
			case 7:
				resetProgress();
				break;
			case 8:
				currentCryptogram = generateCryptogram();
				break;
			case 9:
				return;
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

	public void help() {
		System.out.println("Welcome to the help section! What do you need help with?");
		System.out.println("Type 1 to get help with how to play the game ");
		System.out.println("Type 2 to get help with how to move around in the menu");
		String random = reader.next();
		switch (random) {
		case "1":
			System.out.println("The numbers");
			break;
		case "2":
			break;
		default:
			break;
		}
	}
}
