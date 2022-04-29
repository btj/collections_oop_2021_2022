package collections_oop;

import java.util.Objects;

public interface Map {
	
	/**
	 * @invar | getKey() != null
	 * @invar | getValue() != null
	 */
	interface Entry {
		Object getKey();
		Object getValue();
	}
	
	/**
	 * @inspects | this
	 * @post | result != null
	 * @post | result.stream().allMatch(e -> e instanceof Entry)
	 * @post The entry set does not have two entries with equal keys
	 *     | result.stream().map(e -> ((Entry)e).getKey()).distinct().count() == result.size()
	 * @creates | result
	 */
	Set entrySet();
	
	/**
	 * @inspects | this
	 * @post | result == entrySet().size()
	 */
	int size();

	/**
	 * @pre | object != null
	 * @inspects | this
	 * @post | result == entrySet().stream().anyMatch(e -> ((Entry)e).getKey().equals(object))
	 */
	boolean containsKey(Object object);
	
	/**
	 * @pre | key != null
	 * @inspects | this
	 * @post | result == entrySet().stream().filter(e -> ((Entry)e).getKey().equals(key)).findFirst().orElse(null)
	 */
	Object get(Object key);
	
	/**
	 * @pre | key != null
	 * @pre | value != null
	 * @mutates | this
	 * @post | Objects.equals(get(key), value)
	 * @post | old(entrySet()).stream().allMatch(e -> ((Entry)e).getKey().equals(key) || entrySet().contains(e))
	 * @post | entrySet().stream().allMatch(e -> ((Entry)e).getKey().equals(key) && ((Entry)e).getValue().equals(value)
	 *       | || old(entrySet()).contains(e))
	 */
	void put(Object key, Object value);
	
	/**
	 * @pre | key != null
	 * @mutates | this
	 * @post | old(entrySet()).stream().allMatch(e -> ((Entry)e).getKey().equals(key) || entrySet().contains(e))
	 * @post | entrySet().stream().allMatch(e -> !((Entry)e).getKey().equals(key) && old(entrySet()).contains(e))
	 */
	void remove(Object key);

}
