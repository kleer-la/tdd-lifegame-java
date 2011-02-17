package la.kleer.training.tdd.lifegame;

import static org.junit.Assert.*;

import org.junit.Test;


public class BoardTest {
	
	@Test
	public void testBoardShouldHaveIqualSizes()
	{
		Board board = new Board(10);
		assertTrue( ( board.getX() == 10 ) && ( board.getY() == 10 ) );
	}
	
	@Test
	public void testBoardShouldDieIfOnlyOneCell()
	{
		Board board = new Board(10);
		board.addCellAt(4,4, new Cell() );
		Board  newGen = board.evol();
		assertFalse( newGen.getCellAt(4,4).isAlive() );
	}

	@Test
	public void testBoardCellShouldDieIfJustTwoCells()
	{
		Board board = new Board(10);
		board.addCellAt(4,4, new Cell() );
		board.addCellAt(5,4, new Cell() );
		Board newGen = board.evol();
		assertFalse( newGen.getCellAt(4,4).isAlive() );
	}
	
	@Test
	public void testCellShoulSurviveIfThreeAliveHorizontallyAlignedCells()
	{
		Board board = new Board(10);
		board.addCellAt(4, 4, new Cell() );
		board.addCellAt(5, 4, new Cell() );
		board.addCellAt(6, 4, new Cell() );
		Board newBoard = board.evol();
		assertTrue( newBoard.getCellAt(5, 4).isAlive() );
	}
	
	@Test
	public void testCellShoulSurviveIfThreeAliveVerticallyAlignedCells()
	{
		Board board = new Board(10);
		board.addCellAt(4, 4, new Cell() );
		board.addCellAt(4, 5, new Cell() );
		board.addCellAt(4, 6, new Cell() );
		Board newBoard = board.evol();
		assertTrue( newBoard.getCellAt(4, 5).isAlive() );
	}
	
	@Test
	public void testCellShoulSurviveIfThreeAliveRightDiagonallyAlignedCells()
	{
		Board board = new Board(10);
		board.addCellAt(4, 6, new Cell() );
		board.addCellAt(5, 5, new Cell() );
		board.addCellAt(6, 4, new Cell() );
		Board newBoard = board.evol();
		assertTrue( newBoard.getCellAt(5, 5).isAlive() );
	}
	
	@Test
	public void testCellShoulSurviveIfThreeAliveLeftDiagonallyAlignedCells()
	{
		Board board = new Board(10);
		board.addCellAt(4, 4, new Cell() );
		board.addCellAt(5, 5, new Cell() );
		board.addCellAt(6, 6, new Cell() );
		Board newBoard = board.evol();
		assertTrue( newBoard.getCellAt(5, 5).isAlive() );
	}


}
