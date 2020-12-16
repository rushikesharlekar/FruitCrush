package game.backend;

import java.awt.Point;

public class Cell {

	private Grid grid;
	private Cell[] around = new Cell[Direction.values().length];
	private Element content;

	public Cell(Grid grid) {
		this.grid = grid;
		this.content = new Nothing();
	}

	public void setAround(Cell up, Cell down, Cell left, Cell right) {
		this.around[Direction.UP.ordinal()] = up;
		this.around[Direction.DOWN.ordinal()] = down;
		this.around[Direction.LEFT.ordinal()] = left;
		this.around[Direction.RIGHT.ordinal()] = right;
	}

	public boolean hasFloor() {
		return !around[Direction.DOWN.ordinal()].isEmpty();
	}

	public boolean isMovable() {
		return content.isMovable();
	}

	public boolean isEmpty() {
		return !content.isSolid();
	}

	public Element getContent() {
		return content;
	}

	public void clearContent() {
		if (content.isMovable()) {
			Direction[] explosionCascade = content.explode();
			grid.cellExplosion(content);
			this.content = new Nothing();
			if (explosionCascade != null) {
				expandExplosion(explosionCascade);
			}
			this.content = new Nothing();
		}
		
		explosionMessage();
	}

	private void explosionMessage(){
		
		Cell up = this.around[Direction.UP.ordinal()];
		if (up != null)
			up.explosionNotice();
		
		Cell down = this.around[Direction.DOWN.ordinal()];
		if (down != null)
			down.explosionNotice();
		
		Cell left = this.around[Direction.LEFT.ordinal()];
		if (left != null)
			left.explosionNotice();
		
		Cell right = this.around[Direction.RIGHT.ordinal()];
		if (right != null)
			right.explosionNotice();
		
	
	}
	
	public void explosionNotice(){
		
	}
	
	private void expandExplosion(Direction[] explosion) {
		for (Direction d : explosion) {
			this.around[d.ordinal()].explode(d);
		}
	}

	private void explode(Direction d) {
		clearContent();
		if (this.around[d.ordinal()] != null)
			this.around[d.ordinal()].explode(d);
	}

	public Element getAndClearContent() {
		if (content.isMovable()) {
			Element ret = content;
			this.content = new Nothing();
			return ret;
		}
		return null;
	}

	public boolean fallContent (Cell cell)
	{
		if (this.isEmpty() && !cell.isEmpty() && cell.isMovable()) {
			this.content = cell.getAndClearContent();
			grid.wasUpdated();
			if (this.hasFloor()) {
				grid.tryRemove(this);
				return true;
			} else {
				Cell down = around[Direction.DOWN.ordinal()];
				return down.fallUpperContent();
			}
		}
		return false;
	}
	
	public boolean fallUpperContent(Cell cell)
	{
		if (this.isEmpty() && !cell.isEmpty() && cell.isMovable()) {
			this.content = cell.getAndClearContent();
			grid.wasUpdated();
			if (this.hasFloor()) {
				grid.tryRemove(this);
				return true;
			} else {
				Cell down = around[Direction.DOWN.ordinal()];
				return down.fallUpperContent();
			}
		}
		return false;
	}
	
	public boolean fallUpperContent() {
		Cell up = around[Direction.UP.ordinal()];
		if (this.isEmpty() && !up.isEmpty() && up.isMovable()) {
			this.content = up.getAndClearContent();
			grid.wasUpdated();
			if (this.hasFloor()) {
				grid.tryRemove(this);
				return true;
			} else {
				Cell down = around[Direction.DOWN.ordinal()];
				return down.fallUpperContent();
			}
		}
		return false;
	}
	

	public void setContent(Element content) {
		this.content = content;
	}

	public FruitColor getColor() {
		try {
			return ((Fruit) getContent()).getColor();
		} catch (Exception e) {
			return null;
		}
	}

	public Cell getRight() {
		return around[Direction.RIGHT.ordinal()];
	}

	public Cell getLeft() {
		return around[Direction.LEFT.ordinal()];
	}

	public Cell getDown() {
		return around[Direction.DOWN.ordinal()];
	}

	public Cell getUp() {
		return around[Direction.UP.ordinal()];
	}

	public Point[] evalAroundColors(int i, int j) {
		try {
			Point[] points = new Point[3];
			points[0] = new Point(i, j);
			if (getColor() == getRight().getColor()
					&& getColor() == getLeft().getLeft().getColor()) {
				if (i >= 2) {
					points[1] = new Point(i + 1, j);
					points[2] = new Point(i - 2, j);
					return points;
				}
			}
			if (getColor() == getRight().getColor()
					&& getColor() == getRight().getRight().getRight()
							.getColor()) {
				points[1] = new Point(i + 1, j);
				points[2] = new Point(i + 3, j);
				return points;
			}
			if (getColor() == getLeft().getColor()
					&& getColor() == getRight().getRight().getColor()) {
				if (i >= 1) {
					points[1] = new Point(i - 1, j);
					points[2] = new Point(i + 2, j);
					return points;
				}
			}
			if (getColor() == getLeft().getColor()
					&& getColor() == getLeft().getLeft()
							.getLeft().getColor()) {
				if (i >= 3) {
					points[1] = new Point(i - 1, j);
					points[2] = new Point(i - 3, j);
					return points;
				}
			}
			if (getColor() == getDown().getColor()
					&& getColor() == getUp().getUp().getColor()) {
				if (j >= 2) {
					points[1] = new Point(i, j + 1);
					points[2] = new Point(i, j - 2);
					return points;
				}
			}
			if (getColor() == getDown().getColor()
					&& getColor() == getDown().getDown().getDown()
							.getColor()) {
				points[1] = new Point(i, j + 1);
				points[2] = new Point(i, j + 3);
				return points;
			}
			if (getColor() == getUp().getColor()
					&& getColor() == getDown().getDown().getColor()) {
				if (j >= 1) {
					points[1] = new Point(i, j - 1);
					points[2] = new Point(i, j + 2);
					return points;
				}
			}
			if (getColor() == getUp().getColor()
					&& getColor() == getUp().getUp().getUp()
							.getColor()) {
				if (j >= 3) {
					points[1] = new Point(i, j - 1);
					points[2] = new Point(i, j - 3);
					return points;
				}
			}
			if (getColor() == getRight().getRight().getColor()
					&& getColor() == getDown().getRight().getColor()) {
				points[1] = new Point(i + 2, j);
				points[2] = new Point(i + 1, j + 1);
				return points;
			}
			if (getColor() == getLeft().getLeft().getColor()
					&& getColor() == getDown().getLeft().getColor()) {
				if (i >= 2) {
					points[1] = new Point(i - 2, j);
					points[2] = new Point(i - 1, j + 1);
					return points;
				}
			}
			if (getColor() == getUp().getLeft().getColor()
					&& getColor() == getDown().getLeft().getColor()) {
				if (i >= 1 && j >= 1) {
					points[1] = new Point(i - 1, j - 1);
					points[2] = new Point(i - 1, j + 1);
					return points;
				}
			}
			if (getColor() == getUp().getRight().getColor()
					&& getColor() == getDown().getRight().getColor()) {
				if (j >= 1) {
					points[1] = new Point(i + 1, j - 1);
					points[2] = new Point(i + 1, j + 1);
					return points;
				}
			}
		} catch (Exception e) {
			System.err.println("Error.");
		}
		return null;
		
	}

	public Grid getGrid(){
		return this.grid;
	}
	
	public void setGrid(Grid grid){
		this.grid = grid;
	}
	
	public Cell[] getAround(){
		return around;
	}
	
}