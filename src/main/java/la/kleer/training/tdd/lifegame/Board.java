package la.kleer.training.tdd.lifegame;

public class Board {

	private int x;
	private int y;
	private Cell[][] board = null;
	
	public Board(int size) {
		this.x = this.y = size;
		board = new Cell[this.x][this.y];
		initializeBoard();
	}

	private void initializeBoard() {
		for (int x=0;x<board.length;x++)
		{
			for (int y=0;y<board[x].length;y++)
			{
				board[x][y] = Cell.getDeadCell();
			}
		}
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public void addCellAt(int x, int y, Cell cell) 
	{
		board[x][y] = cell;	
	}

	public Board evol() {
		Board nextGenerationBoard = new Board(this.x);
		nextGenerationBoard.fillWithNextGenerationCells(this);
		return nextGenerationBoard;
	}


	private void fillWithNextGenerationCells(Board oldGenerationBoard) {
		for (int x=0;x<oldGenerationBoard.getX();x++)
		{
			for (int y=0;y<oldGenerationBoard.getY();y++)
			{
				int aliveNeighbours = getAliveNeighboards(oldGenerationBoard, x, y);
				Cell nextGenerationCell = nextGenerationCell(oldGenerationBoard.getCellAt(x, y), aliveNeighbours);
				this.addCellAt(x, y, nextGenerationCell);
			}
		}
	}

	private Cell nextGenerationCell(Cell oldGenerationCell, int aliveNeighbours) {
		Cell newGenCell = new Cell();
		if (!oldGenerationCell.isAlive()) newGenCell.kill();
		newGenCell.setAliveNeighbours(aliveNeighbours);
		newGenCell.evol();
		return newGenCell;
	}

	private int getAliveNeighboards(Board oldGenerationBoard, int x, int y) {
		int aliveNeighbours = 0;
		aliveNeighbours += horizontallyAliveNeighbords(oldGenerationBoard, x, y);
		aliveNeighbours += verticallyAliveNeighbords(oldGenerationBoard, x, y);
		aliveNeighbours += diagonallyAliveNeighbords(oldGenerationBoard, x, y);
		return aliveNeighbours;
	}

	private int diagonallyAliveNeighbords(Board oldGenerationBoard, int x, int y) {
		int aliveNeighbours = 0;
		
		if (x>0 && y<oldGenerationBoard.getY()-1 && oldGenerationBoard.getCellAt(x-1,y+1).isAlive()) aliveNeighbours++;
		if (y>0 && x<oldGenerationBoard.getX()-1 && oldGenerationBoard.getCellAt(x+1,y-1).isAlive()) aliveNeighbours++;
		
		if (x>0 && y>0 && oldGenerationBoard.getCellAt(x-1,y-1).isAlive()) aliveNeighbours++;
		if (x<oldGenerationBoard.getX()-1 && y<oldGenerationBoard.getY()-1 && oldGenerationBoard.getCellAt(x+1,y+1).isAlive()) aliveNeighbours++;
	
		return aliveNeighbours;
	}

	private int verticallyAliveNeighbords(Board oldGenerationBoard, int x, int y) {
		int aliveNeighbours = 0;
		if (y>0 && oldGenerationBoard.getCellAt(x,y-1).isAlive()) aliveNeighbours++;
		if (y<oldGenerationBoard.getY()-1 && oldGenerationBoard.getCellAt(x,y+1).isAlive()) aliveNeighbours++;
		return aliveNeighbours;
	}
	
	private int horizontallyAliveNeighbords(Board oldGenerationBoard, int x, int y) {
		int aliveNeighbours = 0;
		if (x>0 && oldGenerationBoard.getCellAt(x-1,y).isAlive()) aliveNeighbours++;
		if (x<oldGenerationBoard.getX()-1 && oldGenerationBoard.getCellAt(x+1,y).isAlive()) aliveNeighbours++;
		return aliveNeighbours;
	}

	public Cell getCellAt(int x, int y) {
		return board[x][y];
	}


}
