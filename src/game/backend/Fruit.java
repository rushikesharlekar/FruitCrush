package game.backend;

public class Fruit extends Element {
	
	private FruitColor color;
	
	public Fruit() {
	}
	
	public Fruit(FruitColor color) {
		this.color = color;
	}
	
	public FruitColor getColor() {
		return color;
	}
	
	public void setColor(FruitColor color) {
		this.color = color;
	}
	
	@Override
	public boolean isMovable() {
		return true;
	}
	
	@Override
	public int hashCode() {
		return ((color == null) ? 0 : color.hashCode());
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Fruit))
			return false;
		Fruit other = (Fruit) obj;
		if (color != other.color)
			return false;
		return true;
	}
	
	@Override
	public String getFullKey() {
		return color.toString() + "-FRUIT";
	}
	
	@Override
	public String getKey() {
		return "FRUIT";
	}
	
	@Override
	public long getScore() {
		return 50;
	}
}
