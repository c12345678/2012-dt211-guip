package ie.dit.dt211.java;

//import static java.lang.System.err;

public abstract class ShapeVector {
	public static final int capacityIncrement = 10;
	protected Shape[] elementData;
	protected int next = 0;			// Where to add the next element (also its current size)

	public ShapeVector() {
		elementData = new Shape[capacityIncrement];
	}

	// Add an element in sorted order
	public abstract boolean add(Shape o);
	
	// Return a copy of this vector with the elements sorted in reverse order
	public abstract ShapeVector reverse();

	public Shape get(int index) {
		return elementData[index];
	}
	
	public void ensureCapacity(int index) {
		if (index >= elementData.length) {
			Shape[] newElementData = new Shape[index + capacityIncrement];
			System.arraycopy(elementData, 0, newElementData, 0, elementData.length);
			elementData = newElementData;
		}
	}

	public int size() {
		return next;
	}

	public int capacity() {
		return elementData.length;
	}

	public boolean contains(Shape target) {
		for (Shape o: elementData) {
			if (o != null && o.equals(target)) {
				return true;
			}
		}
		return false;
	}

	public Shape remove(Shape target) {
		for (int index = 0; index < next; index++) {
			if (elementData[index] != null && elementData[index].equals(target)) {
				if (next > 0 && index != next - 1) {
					System.arraycopy(elementData, index + 1, elementData, index, next - index - 1);
				} else {
					elementData[index] = null;
				}
				next--;
				return target;
			}
		}
		return null;
	}

	public void clear() {
		while (--next > 0) {
			elementData[next] = null;
		}
	}

	public int indexOf(Shape target) {
		for (int index = 0; index < next; index++) {
			if (elementData[index] != null && elementData[index].equals(target)) {
				return index;
			}
		}
		return -1;
	}
	
	public int lastIndexOf(Shape target) {
		for (int index = next - 1; index >= 0; index--) {
			if (elementData[index] != null && elementData[index].equals(target)) {
				return index;
			}
		}
		return -1;
	}
	
	@Override public String toString() {
		StringBuilder sb = new StringBuilder("[");
		for (int index = 0; index < next; index++) {
			sb.append(elementData[index]);
			if (index < next - 1) {
				sb.append(", ");
			}
		}
		return sb.append("]").toString();
	}
	
	@Override public boolean equals(Object o) {
		if (o instanceof ShapeVector) {
			if (capacity() == ((ShapeVector)o).capacity() && size() == ((ShapeVector)o).size()) {
		    	for (int index = 0; index < size(); index++) {
		    		if (!get(index).equals(((ShapeVector)o).get(index))) {
		    			return false;
		    		}
		    	}
		    	return true;
			}
		}
		return false;
	}

}
