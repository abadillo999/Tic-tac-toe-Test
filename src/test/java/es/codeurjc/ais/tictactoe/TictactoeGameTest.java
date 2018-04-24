package es.codeurjc.ais.tictactoe;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.mockito.Matchers.*;

import org.hamcrest.core.IsEqual;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatcher;

import es.codeurjc.ais.tictactoe.TicTacToeGame.Cell;
import es.codeurjc.ais.tictactoe.TicTacToeGame.CellMarkedValue;
import es.codeurjc.ais.tictactoe.TicTacToeGame.EventType;
import es.codeurjc.ais.tictactoe.TicTacToeGame.WinnerValue;



public class TictactoeGameTest {
	abstract class Players implements ArgumentMatcher<List<Player>> {

	    public boolean matches(List<Player> list) {
	    	//list.contains(player1, player2)
	        return list.size() == 2;
	    }
	    public String toString() {
	        //printed in verification errors
	        return "[list of 2 elements]";
	    }
	}
	
	static TicTacToeGame game;
	static Player player1;
	static Player player2;
	static Connection connection1;
	static Connection connection2;
	static WinnerValue winner;

	
	@Before
	public  void set() {
		game = new TicTacToeGame();
		player1 = new Player(0, "X", "player1");
		player2 = new Player(1, "O", "player2");
		
		game.addPlayer(player1);
		game.addPlayer(player2);
		
		connection1 = mock(Connection.class);
		connection2 = mock(Connection.class);
		
		game.addConnection(connection1);
		game.addConnection(connection2);
		
		winner  = new WinnerValue();
		
		
	
	}

	@Test
	public void winPlayer1() {
			
			for (int n= 0; n <= 6; n++) {
				assertThat(game.checkTurn(0) , equalTo(n%2==0));
				assertThat(game.checkTurn(1) , equalTo(n%2==1));

				game.mark(n);
			}	
			int n [] = {6,4,2};
						
			verify(connection1, times(7)).sendEvent(eq(EventType.MARK),any());
			verify(connection2, times(7)).sendEvent(eq(EventType.MARK),any());

			verify(connection1, times(6)).sendEvent(eq(EventType.SET_TURN),any());
			verify(connection2, times(6)).sendEvent(eq(EventType.SET_TURN),any());
			
			ArgumentCaptor<WinnerValue> argument = ArgumentCaptor.forClass(WinnerValue.class);
			
			verify(connection1).sendEvent(eq(EventType.GAME_OVER), argument.capture());
			winner = argument.getValue();
			assertThat(winner.player, equalTo(player1));
			assertThat(winner.pos, equalTo(n));
			
			verify(connection2).sendEvent(eq(EventType.GAME_OVER), argument.capture());
			winner = argument.getValue();
			assertThat(winner.player, equalTo(player1));
			assertThat(winner.pos, equalTo(n));
			
			reset(connection1);
			game.restart();
	}
	

	@Test
	public void winPlayer2() {
			
			int match [] = {0,1,2,4,5,7};
			
				
			for (int n= 0; n <= 5; n++) {
				assertThat(game.checkTurn(0) , equalTo(n%2==0));
				assertThat(game.checkTurn(1) , equalTo(n%2==1));

				game.mark(match[n]);
			}	
			int n [] = {1,4,7};

			verify(connection1, times(6)).sendEvent(eq(EventType.MARK),any());
			verify(connection2, times(6)).sendEvent(eq(EventType.MARK),any());

			verify(connection1, times(5)).sendEvent(eq(EventType.SET_TURN),any());
			verify(connection2, times(5)).sendEvent(eq(EventType.SET_TURN),any());
			
			ArgumentCaptor<WinnerValue> argument = ArgumentCaptor.forClass(WinnerValue.class);
			
			verify(connection1).sendEvent(eq(EventType.GAME_OVER), argument.capture());
			winner = argument.getValue();
			assertThat(winner.player, equalTo(player2));
			assertThat(winner.pos, equalTo(n));
			
			verify(connection2).sendEvent(eq(EventType.GAME_OVER), argument.capture());
			winner = argument.getValue();
			assertThat(winner.player, equalTo(player2));
			assertThat(winner.pos, equalTo(n));
			
			reset(connection1);
			game.restart();
	}
	
	

	@Test
	public void draw() {
			
			int match [] = {1,0,3,2,5,4,6,7,8};
			
			for (int n= 0; n <= 8; n++) {
				assertThat(game.checkTurn(0) , equalTo(n%2==0));
				assertThat(game.checkTurn(1) , equalTo(n%2==1));
				game.mark(match[n]);
			}	
			int n [] = {1,4,7};

			verify(connection1, times(9)).sendEvent(eq(EventType.MARK),any());
			verify(connection2, times(9)).sendEvent(eq(EventType.MARK),any());

			verify(connection1, times(8)).sendEvent(eq(EventType.SET_TURN),any());
			verify(connection2, times(8)).sendEvent(eq(EventType.SET_TURN),any());
			
			
			verify(connection1).sendEvent(eq(EventType.GAME_OVER), eq(null));

			
			verify(connection2).sendEvent(eq(EventType.GAME_OVER),eq(null));

	}


}
