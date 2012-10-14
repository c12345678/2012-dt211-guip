package ie.dit.dt211.java;

import static java.lang.Math.PI;

public class Sphere extends Shape implements ThreeDShape {

	private double radius;

	Sphere(double radius) {
		this.radius = radius;
	}
	
	public double getDimension() {
		return radius;
	}
	
	@Override
	public double area() {
		// Surface area
		return 4.0 * PI * radius * radius;
	}
	
	public double volume() {
		return 4.0/3.0 * PI * radius * radius * radius;
	}

}
