package ie.dit.dt211.java;

public abstract class Shape implements Comparable<Shape> {
	public abstract double area();
	
	@Override public boolean equals(Object o) {
		/*
		 * XXX Gross simplification. Shapes considered equal if areas
		 * are equal in magnitude
		 */
		if (o instanceof Shape) {
			return this.area() == ((Shape)o).area();
		}
		return false;
	}
	
	@Override public int compareTo(Shape s) {
		if (this.area() == s.area()) {
			return 0;
		} else if (this.area() < s.area()) {
			return -1;
		} else {
			return 1;
		}
	}
}
