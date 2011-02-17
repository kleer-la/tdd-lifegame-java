package la.kleer.training.tdd.lifegame;

public class Cell {

	private int aliveNeigbours;
	private boolean alive = true;
	
	public void setAliveNeighbours(int aliveNeighbours) 
	{
		this.aliveNeigbours = aliveNeighbours;
	}

	public void evol() {
		if ( this.isAliveAndHasTwoOrThreeAliveNeighbours())
			return;
		else if (this.isDeadAndHasExactlyThreeAliveNeighbours())
			this.reborn();
		else
			this.kill();
	}

	private void reborn() {
		this.alive = true;
	}

	private boolean isAliveAndHasTwoOrThreeAliveNeighbours() {
		return (this.isAlive() && ((this.aliveNeigbours == 2) || (this.aliveNeigbours == 3)));
	}

	private boolean isDeadAndHasExactlyThreeAliveNeighbours() {
		return (!this.isAlive() && (this.aliveNeigbours == 3));
	}

	public boolean isAlive() {
		return alive;
	}

	public void kill() 
	{
		this.alive = false;
		
	}

	public static Cell getDeadCell() {
		Cell cell = new Cell();
		cell.kill();
		return cell;
	}

}
