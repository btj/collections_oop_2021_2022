package collections_oop;

import java.util.Arrays;

public class ArrayList implements List {
	
	/**
	 * @invar | elements != null
	 * @invar | 0 <= size
	 * @invar | size <= elements.length
	 * @representationObject
	 */
	private Object[] elements;
	private int size;
	
	@Override
	public Object[] toArray() {
		return Arrays.copyOf(elements, size);
	}
	
	@Override
	public int size() {
		return size;
	}
	
	@Override
	public Object get(int index) {
		return elements[index];
	}
	
	@Override
	public int indexOf(Object object) {
		for (int i = 0; i < size; i++)
			if (elements[i].equals(object))
				return i;
		return -1;
	}
	
	/**
	 * @post | size() == 0
	 */
	public ArrayList() {
		elements = new Object[10];
	}
	
	/**
	 * When adding to the end, this operation has
	 * constant amortized time complexity.
	 */
	@Override
	public void add(int index, Object object) {
		if (size == elements.length) {
			Object[] newElements = new Object[elements.length * 2];
			System.arraycopy(elements, 0, newElements, 0, size);
			elements = newElements;
		}
		System.arraycopy(elements, index, elements, index + 1, size - index);
		elements[index] = object;
		size++;
	}
	
	@Override
	public void remove(int index) {
		System.arraycopy(elements, index + 1, elements, index, size - index - 1);
		size--;
	}
	
}
