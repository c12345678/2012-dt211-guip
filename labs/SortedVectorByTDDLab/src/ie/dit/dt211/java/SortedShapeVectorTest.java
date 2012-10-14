package ie.dit.dt211.java;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
//import static java.lang.System.err;


/*
 * Good idea to list the interfaces to be tested here and tick them off as you
 * go along
 * 
 * [ ] new SortedShapeVector()
 * [ ] boolean add(Object o)
 * [ ] SortedVector reverse()
 *
 */
public class SortedShapeVectorTest {
	
	private SortedShapeVector vector;

	@Before public void makeAVector() {
		vector = new SortedShapeVector();
	}

	private void fillVector() {
		for (double r = 0.0; r < vector.capacity(); r += 1.0) {
			vector.add(new Circle(r + 1.0));
		}
	}
	
	@Test public void createsAVector() {
		assertTrue(vector instanceof ShapeVector);
	}

	@Test public void addsAnElement() {
		assertTrue(vector.add(new Circle(2.0)));
	}
	
	
	@Test public void getsAnElement() {
		Circle s = new Circle(2.0);
		vector.add(s);
		assertEquals(s, vector.get(0));
	}
	
	@Test public void addTwoElementsInSortedOrder() {
		Circle s1 = new Circle(2.0);
		Circle s2 = new Circle(1.0);
		vector.add(s2);
		vector.add(s1);
		assertEquals(s1, vector.get(0));
		assertEquals(s2, vector.get(1));
	}
	
	@Test public void addMoreThanTheInitialSize() {
		fillVector();
		Circle s = new Circle(2.0);
		vector.add(s);
		assertEquals(s, vector.get(SortedShapeVector.capacityIncrement));
	}

	
	@Test public void checksVectorSize() {
		assertEquals(0, vector.size());
		vector.add(new Square(2.0));
		assertEquals(1, vector.size());
	}
	
	@Test public void checksNewVectorCapacity() {
		assertEquals(SortedShapeVector.capacityIncrement, vector.capacity());
	}
	
	@Test public void checksExpandedVectorCapacity() {
		fillVector();
		vector.add(new Circle(2.0));
		assertTrue(vector.capacity() > SortedShapeVector.capacityIncrement);
	}
	
	@Test public void removesAnElementFromTheFront() {
		Circle s = new Circle(2.0);
		vector.add(s);
		assertTrue(vector.contains(s));
		vector.remove(s);
		assertFalse(vector.contains(s));
	}
	
	@Test public void removesAnElementFromTheMiddle() {
		fillVector();
		Circle s = new Circle(2.0);
		vector.remove(s);
		assertFalse(vector.contains(s));
	}
	
	@Test public void clearsAVector() {
		fillVector();
		assertEquals(vector.capacity(), vector.size());
		vector.clear();
		assertEquals(0, vector.size());
	}
	
	@Test public void getsFirstIndexOfExistantElement() {
		Circle s = new Circle(2.0);
		vector.add(s);
		assertEquals(0, vector.indexOf(s));
	}
	
	@Test public void getsFirstIndexOfNonExistantElement() {
		vector.add(new Circle(2.0));
		assertEquals(-1, vector.indexOf(new Square(2.0)));
	}
	
	@Test public void getsLastIndexOfExistantElement() {
		Circle s = new Circle(2.0);
		vector.add(s);
		vector.add(s);
		assertEquals(1, vector.lastIndexOf(s));
	}

	@Test public void getsLastIndexOfNonExistantElement() {
		Circle s = new Circle(2.0);
		vector.add(s);
		vector.add(s);
		assertEquals(-1, vector.lastIndexOf(new Square(2.0)));
	}
	
	@Test public void getAReveredShapeVector() {
		fillVector();
		ShapeVector reversed = new SortedShapeVector();
		for (double r = reversed.capacity(); r >= 1.0; r -= 1.0) {
			reversed.add(new Circle(r));
		}
		assertEquals(reversed, vector.reverse());
	}
}
