package ie.dit.dt211.java;

public class Circle extends Shape {
	
	private double radius;

	Circle(double radius) {
		this.radius = radius;
	}

	@Override
	public double area() {
		return Math.PI * radius * radius;
	}
	
	@Override
	public String toString() {
		return "Circle";
	}
}
