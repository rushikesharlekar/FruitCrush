package game.backend;

public interface GameListener {
	
	public void gridUpdated();
	
	public void cellExplosion(Element e);
	
}