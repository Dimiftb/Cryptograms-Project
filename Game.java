import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Game {

	private HashMap<Player, Cryptogram> playerGameMapping;
	private Cryptogram currentCryptogram;
	private Player currentPlayer;
	private Players allPlayers;
	Scanner reader;
	String currentPlayerName;
	String currentLetter;

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
		if (currentCryptogram == null)
			currentCryptogram = generateCryptogram();
		
		int choice;
		boolean finishGameCheck = false;
		//System.out.println(currentCryptogram.getEncryptedPhrase());
		//System.out.println("cryptogram printed");
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
			choice = reader.nextInt();
			System.out.println("Option " + choice + " chosen");
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
				// start a new game
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
		System.out.println("Enter a letter: ");
		currentLetter = reader.next();
		if (currentCryptogram.contains(currentLetter)) {
			// find index of all of these letters in the cryptogram and replace the instances of it
			List<Integer> numberList = currentCryptogram.getOccurencesOfLetter(currentLetter);
			currentCryptogram.updateProgress(numberList, currentLetter);
		} else {
			System.out.println("This letter is not present!");
		}
	}

	public void undoLetter() {
		System.out.println("Undo letter: ");
		currentLetter = reader.next();
	}

	public void viewFrequencies() {
		// getFrenquencies();
	}

	public void saveGame() {
		String option;
		System.out.println("Would you like to save your progress?");
		option = reader.next();
		System.out.println(option);
		if (option.toLowerCase().equals("yes")) {
			try {
				FileWriter fw = new FileWriter("savedGames.txt", true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw);
				System.out.println(currentPlayer.getName());
				out.println(currentPlayer.getName());
				out.println(currentCryptogram.getPhrase());
				System.out.println(currentCryptogram.getEncryptedPhrase());
				out.println(currentCryptogram.getEncryptedPhrase());
				System.out.println(currentCryptogram.getMapping());
				out.println(currentCryptogram.getMapping());
				out.close();
				bw.close();
				fw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			return;
		}

	}

	public void loadGame() {
		String option;
		System.out.println("Would you like to load a game(if you have any saved)?");
		option = reader.next().toLowerCase();
		if (option.equals("yes")) {
			try {
				File file = new File("savedGames.txt");
				Scanner fileReader = new Scanner(file);
				String tempName;
				String tempPhrase;
				String tempEncPhrase;
				boolean flag = false;
				HashMap<Integer, Character> tempMapping = new HashMap<>();
				HashMap<Character, Integer> tempKeys = new HashMap<>();
				while (fileReader.hasNextLine()) {
					tempName = fileReader.nextLine();
					// I believe the problem is here!!!
					if (currentPlayer.getName().equals(allPlayers.findPlayer(tempName).getName())) {
						tempPhrase = fileReader.nextLine();
						tempEncPhrase = fileReader.nextLine();
						StringTokenizer forHashMap = new StringTokenizer(fileReader.nextLine(), "{} =", false);
						while (forHashMap.hasMoreTokens()) {
							int firstToken = (Integer) Integer.parseInt(forHashMap.nextToken());
							char secondToken = forHashMap.nextToken().charAt(0);
							tempMapping.put(firstToken, secondToken);
							tempKeys.put(secondToken, firstToken);

						}

						flag = true;
						currentCryptogram = new NumberCryptogram(tempPhrase, tempEncPhrase, tempMapping, tempKeys);
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
				System.out.println("Null pointer exception! Did you try loading a file that wasnt there?");
			}
		}
		playGame();
	}

	public void viewScoreboard() {

		allPlayers.showTop10();
	}

	public void getHint() {

	}

	public void displaySolution() {
		System.out.println(currentCryptogram.getPhrase());
	}

	public Players getPlayers() {
		return allPlayers;
	}
}
