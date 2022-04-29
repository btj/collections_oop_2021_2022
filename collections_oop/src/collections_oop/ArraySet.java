package collections_oop;

public class ArraySet implements Set {
	
	/**
	 * @invar | elements != null
	 * @invar | elements.stream().distinct().count() == elements.size()
	 * @representationObject
	 */
	private ArrayList elements;
	
	@Override
	public Object[] toArray() {
		return elements.toArray();
	}
	
	@Override
	public int size() {
		return elements.size();
	}
	
	@Override
	public boolean contains(Object object) {
		return elements.contains(object);
	}
	
	/**
	 * @post | size() == 0
	 */
	public ArraySet() {
		elements = new ArrayList();
	}
	
	@Override
	public void add(Object object) {
		if (!contains(object))
			elements.add(object);
	}
	
	@Override
	public void remove(Object object) {
		elements.remove(object);
	}

}
