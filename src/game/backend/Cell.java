package game.backend;

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
			if (this.hasFloor()) {
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
			if (this.hasFloor()) {
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
			if (this.hasFloor()) {
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