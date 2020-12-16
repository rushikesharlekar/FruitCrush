package game.backend;

import java.util.HashMap;
import java.util.Map;

public class MoveMaker {

	private Map<String, Move> map;
	private Grid grid;
	
	public MoveMaker(Grid grid) {
		this.grid = grid;
		initMap();
	}

	private void initMap(){
		map = new HashMap<String, Move>();
		map.put(new Fruit().getKey() + new Fruit().getKey(), new FruityMove(grid));
		map.put(new Fruit().getKey() + new Nothing().getKey(), new NothingMove(grid));
		
		map.put(new Nothing().getKey() + new Fruit().getKey(), new NothingMove(grid));
		map.put(new Nothing().getKey() + new Nothing().getKey(), new NothingMove(grid));
	}
	
	public Move getMove(int i1, int j1, int i2, int j2) {
		Move move = map.get(grid.get(i1, j1).getKey() + grid.get(i2, j2).getKey());
		move.setCoords(i1, j1, i2, j2);
		return move;
	}
}
