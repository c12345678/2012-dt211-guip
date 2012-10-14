package ie.dit.dt211.java;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import static java.lang.System.err;


/*
 * Good idea to list the interfaces to be tested here and tick them off as you
 * go along
 * 
 * [X] new Vector()
 * [X] boolean add(Object o)
 * [X] void add(int index, Object o)
 * [X] Object get(int index)
 * [X] boolean remove(Object o)
 * [X] void clear()
 * [X] int size()
 * [X] int capacity()
 * [X] boolean contains(Object o)
 * [X] int indexOf(Object o)
 * [X] int lastIndexOf(Object o)
 *
 */
public class VectorTest {
	
	private Vector vector;

	@Before public void makeAVector() {
		vector = new Vector();
	}

	private void fillVector() {
		for (int i = 0; i < vector.capacity(); i++) {
			vector.add("" + i);
		}
	}
	
	@Test public void createsAVector() {
		assertTrue(vector instanceof Vector);
	}

	@Test public void addsAnElement() {
		assertTrue(vector.add("Red"));
	}
	
	@Test public void addsAnElementAtIndex0InAnEmptyVector() {
		vector.add(0, "Red");
	}
	
	@Test public void addsAnElementAtIndex0InASingleElementVector() {
		vector.add(0, "Red");
		vector.add(0, "Blue");
		assertEquals("Red", vector.get(1));
	}
	
	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void addsAnOutOfBoundsElement() {
		vector.add(10, "Red");
	}
	
	@Test public void getsAnElement() {
		vector.add("Red");
		assertEquals("Red", vector.get(0));
	}
	
	@Test public void addTwoElements() {
		vector.add("Red");
		vector.add("Blue");
		assertEquals("Red", vector.get(0));
		assertEquals("Blue", vector.get(1));
	}
	
	@Test public void addMoreThanTheInitialSize() {
		fillVector();
		vector.add("10");
		assertEquals("10", vector.get(Vector.capacityIncrement));
	}

	
	@Test public void checkssVectorSize() {
		assertEquals(0, vector.size());
		vector.add("Red");
		assertEquals(1, vector.size());
	}
	
	@Test public void checksNewVectorCapacity() {
		assertEquals(Vector.capacityIncrement, vector.capacity());
	}
	
	@Test public void checksExpandedVectorCapacity() {
		fillVector();
		vector.add("10");
		assertTrue(vector.capacity() > Vector.capacityIncrement);
	}
	
	@Test public void removesAnElementFromTheFront() {
		vector.add("Red");
		assertTrue(vector.contains("Red"));
		vector.remove("Red");
		assertFalse(vector.contains("Red"));
	}
	
	@Test public void removesAnElementFromTheMiddle() {
		fillVector();
		vector.remove("5");
		assertFalse(vector.contains("5"));

	}
	
	@Test public void clearsAVector() {
		fillVector();
		assertEquals(vector.capacity(), vector.size());
		vector.clear();
		assertEquals(0, vector.size());
	}
	
	@Test public void getsFirstIndexOfExistantElement() {
		vector.add("Red");
		assertEquals(0, vector.indexOf("Red"));
	}
	
	@Test public void getsFirstIndexOfNonExistantElement() {
		vector.add("Red");
		assertEquals(-1, vector.indexOf("Blue"));
	}
	
	@Test public void getsLastIndexOfExistantElement() {
		vector.add("Red");
		vector.add("Red");
		assertEquals(1, vector.lastIndexOf("Red"));
	}

	@Test public void getsLastIndexOfNonExistantElement() {
		vector.add("Red");
		vector.add("Red");
		assertEquals(-1, vector.lastIndexOf("Blue"));
	}
}
