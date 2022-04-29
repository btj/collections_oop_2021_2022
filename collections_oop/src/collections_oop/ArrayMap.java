package collections_oop;

import java.util.Objects;

public class ArrayMap implements Map {
	
	/**
	 * @invar | entries != null
	 * @invar | entries.stream().allMatch(e ->
	 *        |     e instanceof Entry)
	 * @invar No two entries have equal keys.
	 *        | entries.stream().map(e -> ((Entry)e).getKey()).distinct().count()
	 *        | == entries.size()
	 * @representationObject
	 */
	private ArrayList entries = new ArrayList();
	
	@Override
	public Set entrySet() {
		HashSet result = new HashSet(entries.size());
		for (int i = 0; i < entries.size(); i++)
			result.add(entries.get(i));
		return result;
	}
	
	@Override
	public int size() {
		return entries.size();
	}
	
	@Override
	public boolean containsKey(Object object) {
		for (int i = 0; i < entries.size(); i++)
			if (((Entry)entries.get(i)).getKey().equals(object))
				return true;
		return false;
	}
	
	@Override
	public Object get(Object key) {
		for (int i = 0; i < entries.size(); i++)
			if (((Entry)entries.get(i)).getKey().equals(key))
				return ((Entry)entries.get(i)).getValue();
		return null;
	}
	
	@Override
	public void remove(Object key) {
		for (int i = 0; i < entries.size(); i++)
			if (((Entry)entries.get(i)).getKey().equals(key))
				entries.remove(i);
	}
	
	private static class MyEntry implements Entry {
		private final Object key;
		private final Object value;
		public MyEntry(Object key, Object value) {
			this.key = key;
			this.value = value;
		}
		@Override
		public Object getKey() {
			return key;
		}
		@Override
		public Object getValue() {
			// TODO Auto-generated method stub
			return value;
		}
		@Override
		public boolean equals(Object obj) {
			return obj instanceof MyEntry e && key.equals(e.key) && value.equals(e.value);
		}
		@Override
		public int hashCode() {
			return Objects.hash(key, value);
		}
	}
	
	@Override
	public void put(Object key, Object value) {
		remove(key);
		entries.add(new MyEntry(key, value));
	}
	
	/**
	 * @post | size() == 0
	 */
	public ArrayMap() {}

}
