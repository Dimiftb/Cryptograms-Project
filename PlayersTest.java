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
		game.addPlayer("Mark");
		game.addPlayer("John");
		game.addPlayer("Josh");
		game.addPlayer("Paul");
		game.addPlayer("Idk");
		game.addPlayer("Nobody cares");
		game.addPlayer("Again");
		game.addPlayer("1");
		game.addPlayer("2");
		game.addPlayer("3");
		game.addPlayer("4");
		game.addPlayer("5");
		game.addPlayer("6");
	}
	@Test
	public void playersExistTest()
	{
		assertNotNull(game);
	}
	@Test
	public void addPlayerTest()
	{
		game.addPlayer("Nerd");
		assertEquals(game.getPlayerCount(), 16);
	}
	@Test
	public void findPlayerTest()
	{
		
		assertEquals(game.findPlayer("4").getName(), "4");
	}
	@Test
	public void removePlayerTest()
	{
		game.removePlayer("Mark");
		assertEquals(game.getPlayerCount(), 14);
	}
	

}
