import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Players
{
	private List<Player> players;
	private int playersCount;
	
	
	
	public Players()
	{
		players = new LinkedList<Player>();
		File file = new File("accounts.txt");
		String tempName;
		double tempAccuracy;
		int tempAvg;
		int tempCompleted;
		int tempPlayed;
		try
		{
			file.createNewFile();
		}
		catch(IOException e)
		{}
		try
		{
			Scanner readFile = new Scanner(file);
			while(readFile.hasNextLine())
			{
				
				tempName = readFile.next();
				tempAccuracy = readFile.nextDouble();
				tempAvg = readFile.nextInt();
				tempPlayed = readFile.nextInt();
				tempCompleted = readFile.nextInt();
				players.add(new Player(tempName, tempAccuracy, tempAvg, tempPlayed, tempCompleted));
			}
			readFile.close();
		}
		catch(InputMismatchException e)
		{
			e.printStackTrace();
		}
		catch(FileNotFoundException f)
		{
			f.printStackTrace();
		}
		catch(NoSuchElementException m)
		{
		}
		
		playersCount = players.size();
		
		
	}
	public void addPlayer(String newName)
	{
		if(newName.contains(" "))
		{
			System.out.println("Names cannot contain a space.");
			return;
		}
		for(Player pl : players)
		{
			if(pl.getName().equals(newName))
			{
				System.out.println("Player already exists.");
				return;
			}
		}
		
		players.add(new Player(newName, 0, 0, 0, 0));
		
		playersCount++;
		
		
		
	}
	public void removePlayer(String name)
	{
		for(int i = 0; i< players.size(); i++)
		{
			if(players.get(i).getName().equals(name))
			{
				players.remove(i);
				playersCount--;
				return;
			}
		}
	}
	public Player findPlayer(String name)
	{
		for(Player player : players)
		{
			if(player.getName().equals(name))
			{
				return player;
			}
		}
		return null;
	}
	public int getPlayerCount()
	{
		return  playersCount;
	}
	public List<Player> getPlayers()
	{
		return players;
	}
	public void savePlayers()
	{
		
		try
		{
			FileWriter fw = new FileWriter("accounts.txt", false);
			BufferedWriter bw = new BufferedWriter(fw);
		    PrintWriter out = new PrintWriter(bw);
		    for(Player pl : players)
		    {
			    out.print(pl.getName());
			    out.print(" ");
			    out.print(pl.getPlayerAccuracy());
			    out.print(" ");
			    out.print(pl.getAvgCompletionTime());
			    out.print(" ");
			    out.print(pl.getNrOfPlayedCryptograms());
			    out.print(" ");
			    out.println(pl.getNrOfCompletedCryptograms());
		    }
		    out.close();
		    bw.close();
		    fw.close();
		    
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	public void showTop10()
	{
		Collections.sort(players, new Comparator<Player>() {
		    @Override
		    public int compare(Player p1, Player p2) {
		    	if(p1.getAvgCompletionTime() - p2.getAvgCompletionTime() > 0)
				{
					return 1;
				}
				return -1;
		    }
		});
		for(int count = 0;count < 10 && count< playersCount; count++)
		{
			System.out.print(players.get(count).getName());
			System.out.print(": ");
			players.get(count).showStats();
		}
	}
	public static void main(String args[])
	{
		Players testPl = new Players();
		testPl.addPlayer("Mark");
		testPl.addPlayer("John");
		testPl.addPlayer("Josh");
		testPl.addPlayer("Paul");
		testPl.addPlayer("Idk");
		testPl.addPlayer("Nobody cares");
		testPl.addPlayer("Again");
		testPl.addPlayer("1");
		testPl.addPlayer("2");
		testPl.addPlayer("3");
		testPl.addPlayer("4");
		testPl.addPlayer("5");
		testPl.addPlayer("6");
		System.out.println(testPl.getPlayerCount());
		System.out.println("");
		testPl.showTop10();
		testPl.savePlayers();
	}
}




