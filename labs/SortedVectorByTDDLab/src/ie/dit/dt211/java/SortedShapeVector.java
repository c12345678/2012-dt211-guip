package ie.dit.dt211.java;

public class SortedShapeVector extends ShapeVector {
	
	public boolean add(Shape s) {
		ensureCapacity(next);
		
		/*
		 * Implement the sorted order insertion logic here
		 * HINT: Use the compareTo() method defined in the Shape class
		 */
		return false;
	}
	
	public ShapeVector reverse() {
		
		/*
		 *  HINT: Assuming that the elements are sorted in one direction already
		 *  then it is relatively trivial to produce an vector copy with the
		 *  elements sorted in the opposite direction
		 */
		return null;
	}

}
