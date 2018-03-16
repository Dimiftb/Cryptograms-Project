import java.io.IOException;
import java.util.ArrayList;

public class Players
{
	private ArrayList<Player> players;

	
	public Players()
	{
		players = new ArrayList<Player>();

	}
	public void addPlayer(Player newPlayer)
	{
		players.add(newPlayer);
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
	public Player findPlayer(String name) throws IOException
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
	public void savePlayers()
	{
		
	}
}
