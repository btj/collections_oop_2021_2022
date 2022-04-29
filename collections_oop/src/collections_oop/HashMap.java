package collections_oop;

import java.util.Arrays;
import java.util.stream.IntStream;

public class HashMap implements Map {
	
	/**
	 * @invar | buckets != null
	 * @invar | IntStream.range(0, buckets.length).allMatch(i ->
	 *        |     buckets[i] != null &&
	 *        |     buckets[i].entrySet().stream().allMatch(e ->
	 *        |         Math.floorMod(((Entry)e).getKey().hashCode(), buckets.length)
	 *        |         == i))
	 * @invar | size == Arrays.stream(buckets).mapToInt(b -> b.size()).sum()
	 * @representationObject
	 * @representationObjects
	 */
	private Map[] buckets;
	private int size;
	
	@Override
	public Set entrySet() {
		HashSet result = new HashSet(size);
		for (Map bucket : buckets)
			for (Object entry : bucket.entrySet().toArray())
				result.add(entry);
		return result;
	}
	
	@Override
	public int size() {
		return size;
	}
	
	private Map getBucket(Object key) {
		return buckets[Math.floorMod(key.hashCode(), buckets.length)];
	}
	
	@Override
	public boolean containsKey(Object object) {
		return getBucket(object).containsKey(object);
	}
	
	@Override
	public Object get(Object key) {
		return getBucket(key).get(key);
	}
	
	@Override
	public void put(Object key, Object value) {
		getBucket(key).put(key, value);
	}
	
	@Override
	public void remove(Object key) {
		getBucket(key).remove(key);
	}

}
