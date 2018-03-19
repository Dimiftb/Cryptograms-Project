import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PlayersTest
{
	private Players game;
	@Before
	public void setUp()
	{
		game =  new Players();
		game.addPlayer(new Player("Mark"));
		game.addPlayer(new Player("John"));
		game.addPlayer(new Player("Josh"));
		game.addPlayer(new Player("Paul"));
		game.addPlayer(new Player("Idk"));
		game.addPlayer(new Player("Nobody cares"));
		game.addPlayer(new Player("Again"));
		game.addPlayer(new Player("1"));
		game.addPlayer(new Player("2"));
		game.addPlayer(new Player("3"));
		game.addPlayer(new Player("4"));
		game.addPlayer(new Player("5"));
		game.addPlayer(new Player("6"));
	}
	@Test
	public void playersExistTest()
	{
		assertNotNull(game);
	}
	@Test
	public void addPlayerTest()
	{
		game.addPlayer(new Player("Mark"));
		assertEquals(game.getPlayerCount(), 14);
	}
	@Test
	public void findPlayerTest()
	{
		assertEquals(game.getPlayerCount(), 13);
		assertEquals(game.findPlayer("4").getName(), "4");
	}
	@Test
	public void removePlayerTest()
	{
		game.removePlayer("Mark");
		assertEquals(game.getPlayerCount(), 13);
	}
	

}
