package game.backend;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

public abstract class Grid {

	public static final int SIZE = 9;
	public static final int DELAY_MS = 10;

	protected Cell[][] g = new Cell[SIZE][SIZE];
	private Map<Cell, Point> gMap = new HashMap<Cell, Point>();



	protected abstract void fillCells();

	protected Cell[][] g() {
		return g;
	}

	protected void setCell(int i, int j, Cell cell) {
		Cell old = g[i][j];
		gMap.remove(old);
		g[i][j] = cell;
		gMap.put(g[i][j], new Point(i, j));
	}


	public void initialize() {

		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				g[i][j] = new Cell(this);
				gMap.put(g[i][j], new Point(i, j));
			}
		}
		fillCells();
		fallElements();
	}


	public Element get(int i, int j) {
		return g[i][j].getContent();
	}

	public Cell getCell(int i, int j) {
		return g[i][j];
	}

	public void fallElements() {

		boolean emptyflag = false;

		int i = SIZE - 1;

		while (i >= 0) {
			int j = 0;
			while (j < SIZE) {
				if (g[i][j].isEmpty()) {
					if (g[i][j].fallContent(g[i][j].getAround()[Direction.UP.ordinal()])) {
						emptyflag = false;
						i = SIZE;
						j = -1;
						break;
					}
					emptyflag = true;
				}
				j++;
			}
			i--;
		}
		if (emptyflag) {
			for (i = SIZE - 1; i >= 0; i--) {
				for (int j = 0; j < SIZE; j++) {
					if (g[i][j].isEmpty()) {
						if (g[i][j].fallContent(g[i][j].getAround()[Direction.UP.ordinal()].getAround()[Direction.LEFT.ordinal()]))
							fallElements();
						else if (g[i][j].fallContent(g[i][j].getAround()[Direction.UP.ordinal()].getAround()[Direction.RIGHT.ordinal()]))
							fallElements();

					}
				}
			}

		}
	}


	public void delay(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException ie) {
		}
	}

}
