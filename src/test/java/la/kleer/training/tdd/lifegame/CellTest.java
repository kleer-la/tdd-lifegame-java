package la.kleer.training.tdd.lifegame;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

public class CellTest {

	@Test
	public void testCellShouldAllowADeadCellCreation()
	{
		Cell cell = Cell.getDeadCell();
	    assertFalse( cell.isAlive() );
	}
	
	@Test
	public void testCellShouldDieBecauseOfLonelinessIfLessThanTwoAliveNeighbours()
	{
		Random randomGenerator = new Random();
	    Cell cell = new Cell();
	    cell.setAliveNeighbours( randomGenerator.nextInt(2) );
	    
	    cell.evol();
	    
	    assertFalse( cell.isAlive() );
	}
	
	@Test
	public void testCellShouldDieBecauseOfOverpopulationIfMoreThanThreeAliveNeighbours()
	{
		Random randomGenerator = new Random();
	    Cell cell = new Cell();
	    cell.setAliveNeighbours( randomGenerator.nextInt(100)+3 );
	    
	    cell.evol();
	    
	    assertFalse( cell.isAlive() );
	}
	
	@Test
	public void testCellShouldSurviveIfTwoAliveNeighbours()
	{
	    Cell cell = new Cell();
	    cell.setAliveNeighbours(2);
	    
	    cell.evol();
	    
	    assertTrue( cell.isAlive() );
	}


	@Test
	public void testCellShouldSurviveIfThreeAliveNeighbours()
	{
	    Cell cell = new Cell();
	    cell.setAliveNeighbours(3);
	    
	    cell.evol();
	    
	    assertTrue( cell.isAlive() );
	}
	
	@Test
	public void testCellShouldAllowToBeKilled()
	{
		Cell cell = new Cell();
		
		cell.kill();
		
	    assertFalse( cell.isAlive() );
	}
	
	@Test
	public void testCellShouldGetBackToLifeIfDeadAndExatlyThreeAliveNeighbours()
	{
		Cell cell = new Cell();
		cell.kill();
	    cell.setAliveNeighbours(3);
	    
	    cell.evol();
	    
	    assertTrue( cell.isAlive() );
	}
	
	@Test
	public void testCellShouldNotGetBackToLifeIfDeadAndExatlyTwoAliveNeighbours()
	{
		Cell cell = new Cell();
		cell.kill();
	    cell.setAliveNeighbours(2);
	    
	    cell.evol();
	    
	    assertFalse( cell.isAlive() );
	}
	
}
