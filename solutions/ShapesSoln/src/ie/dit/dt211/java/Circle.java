package ie.dit.dt211.java;

import static java.lang.Math.PI;

public class Circle extends Shape {
	
	private double radius;

	Circle(double radius) {
		this.radius = radius;
	}

	public double getRadius() {
		return radius;
	}
	
	@Override
	public double area() {
		return PI * radius * radius;
	}
	
	@Override
	public String toString() {
		return "Circle<" + radius + ">";
	}
}
