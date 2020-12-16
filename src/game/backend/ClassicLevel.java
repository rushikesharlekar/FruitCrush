package game.backend;

public class ClassicLevel extends Grid {
	
	private static int REQUIRED_SCORE = 5000; 
	private static int MAX_MOVES = 20; 
	
	private Cell wallCell;
	private Cell fruitGenCell;
	
	@Override
	protected GameState newState() {
		return new Level1State(REQUIRED_SCORE, MAX_MOVES);
	}

	@Override
	protected void fillCells() //populates cells with wall cells at border and random fruits in center
        {
		
		wallCell = new Cell(this);
		wallCell.setContent(new Wall());
		fruitGenCell = new FruitGenerator(this);
		
		//corners
		g()[0][0].setAround(fruitGenCell, g()[1][0], wallCell, g()[0][1]);
		g()[0][SIZE-1].setAround(fruitGenCell, g()[1][SIZE-1], g()[0][SIZE-2], wallCell);
		g()[SIZE-1][0].setAround(g()[SIZE-2][0], wallCell, wallCell, g()[SIZE-1][1]);
		g()[SIZE-1][SIZE-1].setAround(g()[SIZE-2][SIZE-1], wallCell, g()[SIZE-1][SIZE-2], wallCell);

		//upper line cells
		for (int j = 1; j < SIZE-1; j++) {
			g()[0][j].setAround(fruitGenCell,g()[1][j],g()[0][j-1],g()[0][j+1]);
		}
		//bottom line cells
		for (int j = 1; j < SIZE-1; j++) {
			g()[SIZE-1][j].setAround(g()[SIZE-2][j], wallCell, g()[SIZE-1][j-1],g()[SIZE-1][j+1]);
		}
		//left line cells
		for (int i = 1; i < SIZE-1; i++) {
			g()[i][0].setAround(g()[i-1][0],g()[i+1][0], wallCell ,g()[i][1]);
		}
		//right line cells
		for (int i = 1; i < SIZE-1; i++) {
			g()[i][SIZE-1].setAround(g()[i-1][SIZE-1],g()[i+1][SIZE-1], g()[i][SIZE-2], wallCell);
		}		
		//central cells
		for (int i = 1; i < SIZE-1; i++) {
			for (int j = 1; j < SIZE-1; j++) {
				g()[i][j].setAround(g()[i-1][j],g()[i+1][j],g()[i][j-1],g()[i][j+1]);
			}
		}
	}

	
	private class Level1State extends GameState {
		private long requiredScore;
		private long maxMoves;
		
		public Level1State(long requiredScore, int maxMoves) {
			this.requiredScore = requiredScore;
			this.maxMoves = maxMoves;
		}
		
		public boolean gameOver() {
			return playerWon() || getMoves() >= maxMoves;
		}
		
		public boolean playerWon() {
			return getScore() > requiredScore;
		}
	}
}
