package game.backend;

public class NothingMove extends Move {

	public NothingMove(Grid grid) {
		super(grid);
	}

	@Override
	public void removeElements() {
		
	}

	@Override
	public boolean internalValidation(){
		return false;
	}
}