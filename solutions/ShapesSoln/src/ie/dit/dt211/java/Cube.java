package ie.dit.dt211.java;

public class Cube extends Shape implements ThreeDShape {

	private double dimension;

	Cube(double dimension) {
		this.dimension = dimension;
	}
	
	public double getDimension() {
		return dimension;
	}
	
	@Override
	public double area() {
		// Surface area
		return dimension * dimension * 6;
	}

	public double volume() {
		return dimension * dimension * dimension;
	}

}
