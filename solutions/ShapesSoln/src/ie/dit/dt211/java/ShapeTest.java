package ie.dit.dt211.java;

import static org.junit.Assert.*;

import org.junit.Test;

public class ShapeTest {

	@Test public void createsACircle() {
		new Circle(2.0);
	}

	@Test public void getsAreaOfACircle() {
		assertEquals(Math.PI * 2.0 * 2.0, new Circle(2.0).area(), 0.000000001);
	}
	
	@Test public void createsACube() {
		new Cube(3.0);
	}
}
