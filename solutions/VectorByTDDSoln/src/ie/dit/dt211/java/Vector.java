package ie.dit.dt211.java;

import static java.lang.System.err;

public class Vector {
	public static final int capacityIncrement = 10;
	Object[] elementData;
	int next = 0;			// Where to add the next element (also its current size)

	public Vector() {
		elementData = new Object[capacityIncrement];
	}

	public boolean add(Object o) {
		ensureCapacity(next);
		elementData[next++] = o;
		return true;
	}

	public Object get(int index) {
		return elementData[index];
	}
	
	public void ensureCapacity(int index) {
		if (index >= elementData.length) {
			Object[] newElementData = new Object[index + capacityIncrement];
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

	public Object add(int index, Object o) {
		if (index < 0 || index >= elementData.length) {
			throw new ArrayIndexOutOfBoundsException("" + index);
		}
		ensureCapacity(index);
		if (elementData[index] != null) {
			System.arraycopy(elementData, index, elementData, index + 1, elementData.length - index - 1);
		}
		elementData[index] = o;
		return o;
	}

	public boolean contains(Object target) {
		for (Object o: elementData) {
			if (o != null && o.equals(target)) {
				return true;
			}
		}
		return false;
	}

	public Object remove(Object target) {
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

	public Object indexOf(Object target) {
		for (int index = 0; index < next; index++) {
			if (elementData[index] != null && elementData[index].equals(target)) {
				return index;
			}
		}
		return -1;
	}
	
	public Object lastIndexOf(Object target) {
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

}
