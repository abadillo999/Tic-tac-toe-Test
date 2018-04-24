
package es.codeurjc.ais.tictactoe;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import org.hamcrest.core.IsEqual;
import org.junit.Before;
import org.junit.Test;

import es.codeurjc.ais.tictactoe.*;
import es.codeurjc.ais.tictactoe.TicTacToeGame.Cell;


public class BoardUnitTest {

	private Board board;
	
	@Before
	public void setUp() {
		board = new Board();
		board.enableAll();
	}
	
	@Test
	public void NullBefore() {
		
		assertThat(board.getCellsIfWinner("X"), equalTo(null));
		assertThat(board.getCellsIfWinner("0"), equalTo(null));
		assertThat(board.checkDraw(), is(false));

	}
	
	@Test
	public void winPlayerX() {
		
		String match [] = {"X", "O", "X" ,
						   "O" ,"X" ,"O",
						   "X", "O"};
		for (int n= 0; n <= 7; n++) {
			Cell cell = this.board.getCell(n);
			cell.value = match[n]; 
			cell.active = true;
		}
		
		int line [] = {  6, 4, 2  }; 
	
		assertThat(board.getCellsIfWinner("X"), is(line));
		assertThat(board.getCellsIfWinner("0"), equalTo(null));
		assertThat(board.checkDraw(), is(false));

	}
	
	
	@Test
	public void winPlayerO() {
		String match [] = {"X", "O", "X" ,
						   "O" ,"O" ,"X",
						   "X", "O"};
		for (int n= 0; n <= 7; n++) {
			Cell cell = this.board.getCell(n);
			cell.value = match[n]; 
			cell.active = true;
		}		

		int line [] = { 1, 4, 7 }; 
	
		assertThat(board.getCellsIfWinner("O"), is(line));
		assertThat(board.getCellsIfWinner("X"), equalTo(null));
		assertThat(board.checkDraw(), is(false));

	}
	
	@Test
	public void Draw() {
		
		String match [] = {"O", "X", "O" ,
						   "X" ,"O" ,"X",
						   "X", "O", "X"};
		
		for (int n= 0; n <= 8; n++) {
			Cell cell = this.board.getCell(n);
			cell.value = match[n]; 
			cell.active = true;
		}	
		
		
		assertThat(board.getCellsIfWinner("X"), equalTo(null));
		assertThat(board.getCellsIfWinner("0"), equalTo(null));
		assertThat(board.checkDraw(), is(true));

	}
	
	
	


}
