package game.backend;

import java.awt.Point;

public class FigureDetector {
	
	private Grid grid;
	
	public FigureDetector(Grid grid) {
		this.grid = grid;
	}
	
	
	
	
	
	public void removeFigure(int i, int j, Figure f) {
		FruitColor color = ((Fruit)grid.get(i, j)).getColor();
		grid.clearContent(i, j);
		if (f.hasReplacement()) {
			grid.setContent(i, j, f.generateReplacement(color));
		}
		for (Point p: f.getPoints()) {
			grid.clearContent(i + p.x, j + p.y);
		}
	}
	
}
