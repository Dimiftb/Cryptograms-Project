import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Players
{
	private ArrayList<Player> players;
	private int playersCount;
	
	
	
	public Players()
	{
		players = new ArrayList<Player>();
		playersCount = 0;
		
		
	}
	public void addPlayer(Player newPlayer)
	{
		players.add(newPlayer);
		playersCount++;
		
	}
	public void removePlayer(String name)
	{
		for(int i = 0; i< players.size(); i++)
		{
			if(players.get(i).equals(name))
			{
				players.remove(i);
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
		throw new IndexOutOfBoundsException();
	}
	public int getPlayerCount()
	{
		return  playersCount;
	}
}
