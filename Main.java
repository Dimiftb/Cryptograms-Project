import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        boolean exit = false;
        int choice = 0;
        Game newGame = new Game();
        Players playersList = new Players();
        Player player = new Player("");
        Scanner reader = new Scanner(System.in);
        System.out.println("Greetings summoner!");
        newGame.loadPlayer();
        while(!exit) {
        System.out.println("MAIN MENU");
        System.out.println("Please choose an option: ");
        System.out.println("Type 1 to start a game");
        System.out.println("Type 2 to load a previous game");
        System.out.println("Type 3 to view the scoreboard");
        System.out.println("Type 4 to exit");
        choice = reader.nextInt();
            switch (choice) {
                case 1:
                    newGame.playGame();
                    break;
                case 2:
                    newGame.loadGame();
                    break;
                case 3:
                    newGame.viewScoreboard();
                    break;
                case 4:
                    exit = true;
                    System.exit(0);
                default:
                    System.out.println("Option " + choice + " is invalid. Please try again!");
                    break;
            }
        }
    }
}
