package collections_oop;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public interface List {
	
	/**
	 * @inspects | this
	 * @post | result != null
	 * @post | Arrays.stream(result).allMatch(e -> e != null)
	 * @creates | result 
	 */
	Object[] toArray();
	
	default Stream<Object> stream() { return Arrays.stream(toArray()); }
	
	/**
	 * @inspects | this
	 * @post | result == toArray().length
	 */
	int size();
	
	/**
	 * @inspects | this
	 * @pre | 0 <= index && index < size()
	 * @post | result == toArray()[index]
	 */
	Object get(int index);
	
	/**
	 * Returns the index of the first occurrence of the given object in this list, or -1 if the object does not occur in this list.
	 * The given object is compared to the elements of the list using the Object.equals method.
	 * 
	 * @pre | object != null
	 * @inspects | this
	 * @post | result == IntStream.range(0, size()).filter(i -> toArray()[i].equals(object)).findFirst().orElse(-1)
	 */
	int indexOf(Object object);
	
	/**
	 * @inspects | this
	 * @pre | object != null
	 * @post | result == (indexOf(object) != -1)
	 */
	public default boolean contains(Object object) { return indexOf(object) != -1; }
	
	/**
	 * @pre | 0 <= index
	 * @pre | index <= size()
	 * @pre | object != null
	 * @mutates | this
	 * @post | size() == 1 + old(size())
	 * @post | get(index) == object
	 * @post | Arrays.equals(toArray(), 0, index, old(toArray()), 0, index)
	 * @post | Arrays.equals(toArray(), index + 1, size(), old(toArray()), index, old(size()))
	 */
	void add(int index, Object object);
	
	/**
	 * @pre | object != null
	 * @mutates | this
	 * @post | size() == 1 + old(size())
	 * @post | get(old(size())) == object
	 * @post | Arrays.equals(toArray(), 0, old(size()), old(toArray()), 0, old(size()))
	 */
	default void add(Object object) { add(size(), object); }
	
	/**
	 * @pre | 0 <= index
	 * @pre | index < size()
	 * @mutates | this
	 * @post | size() == old(size()) - 1
	 * @post | Arrays.equals(toArray(), 0, index, old(toArray()), 0, index)
	 * @post | Arrays.equals(toArray(), index, size(), old(toArray()), index + 1, old(size()))
	 */
	void remove(int index);
	
	/**
	 * @pre | object != null
	 * @mutates | this
	 * @post | Arrays.equals(toArray(), old(IntStream.range(0, size()).filter(i -> i != indexOf(object)).mapToObj(i -> get(i)).toArray()))
	 */
	default void remove(Object object) {
		int index = indexOf(object);
		if (index != -1)
			remove(index);
	}

}
