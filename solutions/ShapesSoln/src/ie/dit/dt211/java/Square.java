package ie.dit.dt211.java;

public class Square extends Shape {

	private double dimension;
	
	Square(double dimension) {
		this.dimension = dimension;
	}
	
	public double getDimension() {
		return dimension;
	}
	
	@Override
	public double area() {
		return dimension * dimension;
	}

	@Override
	public String toString() {
		return "Square<" + dimension + ">";
	}
}
