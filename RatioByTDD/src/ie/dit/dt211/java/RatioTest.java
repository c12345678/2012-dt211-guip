/*
 * Java doesn't have a built in ratio data type so let's create a simple
 * implementation here.
 * 
 * As an exercise, fill in code details for the following methods driven
 * through TDD/JUnit
 * 
 * [X] new Ratio(int numerator, int denominator)
 * [X] public int getNumerator()
 * [X] public int getDenominator()
 * [X] public String toString()
 * [ ] public static Ratio add(Ratio r1, Ratio r2)
 * [ } public Ratio add(Ratio r)
 * [ ] public static Ratio subtract(Ratio r1, Ratio r2)
 * [ } public Ratio subtract(Ratio r)
 * [ ] public static Ratio multiply(Ratio r1, Ratio r2)
 * [ } public Ratio multiply(Ratio r)
 * [ ] public static Ratio divide(Ratio r1, Ratio r2)
 * [ } public Ratio divide(Ratio r)
 * [ ] public Ratio simplestForm()
 * 
 * You may find the following tutorial document useful with the mathematics
 * side of this exercise:
 * 
 * http://goo.gl/Fc5kx
 */

package ie.dit.dt211.java;

import static org.junit.Assert.*;

import org.junit.Test;

public class RatioTest {

	@Test public void createsARatio() {
		assertEquals(true, (new Ratio(1, 2)) instanceof Ratio);
	}
	
	@Test public void numeratorIsAccessible() {
		assertEquals(3, new Ratio(3, 4).getNumerator());
	}
	
	@Test public void denominatorIsAccssible() {
		assertEquals(4, new Ratio(3, 4).getDenominator());
	}
	
	@Test public void getsRatioAsString() {
		assertEquals("5/6", new Ratio(5, 6).toString());
		assertEquals("1/2", new Ratio(3, 6).toString());
		assertEquals("4", new Ratio(4, 1).toString());
	}
	
	@Test public void addsTwoRatios() {
		Ratio r1 = new Ratio(1, 2);
		Ratio r2 = new Ratio(1, 4);
		assertEquals(new Ratio(3, 4), Ratio.add(r1, r2));
	}
	
	@Test public void addsTwoRatiosAlt() {
		Ratio r1 = new Ratio(1, 2);
		Ratio r2 = new Ratio(1, 4);
		assertEquals(new Ratio(3, 4), r1.add(r2));
	}
	
	@Test public void subtactOneRatioFromAnother() {
		Ratio r1 = new Ratio(1, 2);
		Ratio r2 = new Ratio(1, 4);
		assertEquals(new Ratio(1, 4), Ratio.subtract(r1, r2));
	}
	
	@Test public void subtactOneRatioFromAnotherAlt() {
		Ratio r1 = new Ratio(1, 2);
		Ratio r2 = new Ratio(1, 4);
		assertEquals(new Ratio(1, 4), r1.subtract(r2));
	}
	
	@Test public void multiplyTwoRatios() {
		Ratio r1 = new Ratio(1, 2);
		Ratio r2 = new Ratio(1, 4);
		assertEquals(new Ratio(1, 8), Ratio.multiply(r1, r2));
	}
	
	@Test public void multiplyTwoRatiosAlt() {
		Ratio r1 = new Ratio(1, 2);
		Ratio r2 = new Ratio(1, 4);
		assertEquals(new Ratio(1, 8), r1.multiply(r2));
	}
	
	@Test public void divideOneRatioIntoAnother() {
		Ratio r1 = new Ratio(1, 2);
		Ratio r2 = new Ratio(1, 4);
		assertEquals(new Ratio(2, 1), Ratio.divide(r1, r2));
	}
	
	@Test public void divideOneRatioIntoAnotherAlt() {
		Ratio r1 = new Ratio(1, 2);
		Ratio r2 = new Ratio(1, 4);
		assertEquals(new Ratio(2, 1), r1.divide(r2));
	}
	
	@Test public void ratiosAreConvertedToTheirSimplestForm() {
		assertEquals(new Ratio(1, 2), new Ratio(4, 8).simplestForm());
	}
	
}
