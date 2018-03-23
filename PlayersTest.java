import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
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
		game.addPlayer("Nobody_cares");
		game.addPlayer("Again");
		game.addPlayer("1");
		game.addPlayer("2");
		game.addPlayer("3");
		game.addPlayer("4");
		game.addPlayer("5");
		game.addPlayer("6");
		game.savePlayers();
	}
	@Test
	public void playersExistTest()
	{
		assertNotNull(game);
	}
	@Test
	public void addPlayerTest()
	{
		
		game.addPlayer("Stan");
		assertEquals(game.getPlayerCount(), 14);
	}
	@Test
	public void findPlayerTest()
	{
		assertEquals(game.getPlayerCount(), 14);
		assertEquals(game.findPlayer("4").getName(), "4");
	}
	@Test
	public void removePlayerTest()
	{
		game.removePlayer("1");
		assertEquals(game.getPlayerCount(), 13);
	}

}
