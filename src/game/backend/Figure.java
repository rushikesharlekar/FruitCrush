package game.backend;


import java.awt.Point;

public enum Figure {
	
	F1(new Point[]{new Point(0,1), new Point(0,2)}, 48),
	F2(new Point[]{new Point(0,-1), new Point(0,1)}, 80),
	F3(new Point[]{new Point(0,-1), new Point(0,-2)}, 192),
	F4(new Point[]{ new Point(1,0), new Point(2,0)}, 12),	
	F5(new Point[]{ new Point(-1,0), new Point(1,0)}, 5),	
	F6(new Point[]{ new Point(-2,0), new Point(-1,0)}, 3);
	
	
	private Point[] points;
	private int value;
	private Class<?> replacementClass;
	private boolean isFruitRepl = true;
	
	private Figure(Point[] points, int value, Class<?> replacementClass) {
		this.points = points;
		this.value = value;
		this.replacementClass = replacementClass;
	}
	
	private Figure(Point[] points, int value, Class<?> replacementClass, boolean isFruitRepl) {
		this.points = points;
		this.value = value;
		this.replacementClass = replacementClass;
		this.isFruitRepl = isFruitRepl;
	}
	
	private Figure(Point[] points, int value) {
		this.points = points;
		this.value = value;
		this.replacementClass = null;
	}
	
	public Point[] getPoints() {
		return points;
	}
	
	public int size() {
		return points.length;
	}
	
	public Class<?> getReplacementClass() {
		return replacementClass;
	}
	
	public boolean hasReplacement() {
		return replacementClass != null;
	}
	
	public boolean matches(int acum) {
		return value == (value & acum);
	}
	
	public Element generateReplacement(FruitColor color) {
		try {
			Element e;
			e = (Element) replacementClass.newInstance();
			if (isFruitRepl) {
				((Fruit)e).setColor(color);
			} 
			return e;
		} catch(Exception e) {
		}
		return null;
	}	
}
