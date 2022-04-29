package collections_oop;

import java.util.Arrays;
import java.util.stream.IntStream;

public class HashSet implements Set {
	
	/**
	 * @invar | buckets != null
	 * @invar | IntStream.range(0, buckets.length).allMatch(i ->
	 *        |     buckets[i] != null &&
	 *        |     buckets[i].stream().allMatch(e ->
	 *        |         Math.floorMod(e.hashCode(), buckets.length) == i))
	 * @invar | size == Arrays.stream(buckets).mapToInt(b -> b.size()).sum()
	 * @representationObjects
	 */
	private Set[] buckets;
	private int size;
	
	@Override
	public Object[] toArray() {
		return Arrays.stream(buckets).flatMap(b -> b.stream()).toArray();
	}
	
	@Override
	public int size() {
		return size;
	}
	
	private Set getBucket(Object object) {
		return buckets[Math.floorMod(object.hashCode(), buckets.length)];
	}
	
	@Override
	public boolean contains(Object object) {
		return getBucket(object).contains(object);
	}

	/**
	 * @post | size() == 0
	 */
	public HashSet(int capacity) {
		buckets = new Set[capacity];
		for (int i = 0; i < buckets.length; i++)
			buckets[i] = new ArraySet();
	}
	
	@Override
	public void add(Object object) {
		getBucket(object).add(object);
	}
	
	@Override
	public void remove(Object object) {
		getBucket(object).remove(object);
	}

}
