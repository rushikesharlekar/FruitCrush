package game.backend;

import java.awt.Point;

public class FruitGame {

	private Class<?> levelClass;
	private Grid grid;

	public FruitGame(Class<?> clazz) {
		this.levelClass = clazz;
	}

	public void initGame() {
		try {
			grid = (Grid) levelClass.newInstance();
		} catch (IllegalAccessException e) {
			System.out.println("ERROR");
		} catch (InstantiationException e) {
			System.out.println("ERROR");
		}
		grid.initialize();
		
	}

	public int getSize() {
		return Grid.SIZE;
	}

	public Cell get(int i, int j) {
		return grid.getCell(i, j);
	}

}
