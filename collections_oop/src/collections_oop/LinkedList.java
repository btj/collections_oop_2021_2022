package collections_oop;

public class LinkedList implements List {
	
	private class Node {
		/**
		 * @invar | next != null
		 * @invar | previous != null
		 * @invar | this.next.previous == this
		 * @invar | this.previous.next == this
		 * @invar | (value == null) == (this == sentinel)
		 * 
		 * @peerObject
		 */
		private Node previous;
		private Object value;
		/**
		 * @peerObject
		 */
		private Node next;
		
		private int getLength() {
			return this == sentinel ? 0 : 1 + next.getLength();
		}
	}
	
	/**
	 * @invar | sentinel != null
	 * @invar | size == sentinel.next.getLength()
	 * 
	 * @representationObject
	 */
	private Node sentinel;
	private int size;
	
	@Override
	public Object[] toArray() {
		Object[] result = new Object[size];
		Node n = sentinel.next;
		for (int i = 0; i < size; i++) {
			result[i] = n.value;
			n = n.next;
		}
		return result;
	}
	
	@Override
	public int size() {
		return size;
	}
	
	private Node getNode(int index) {
		if (index < size/2) {
			Node n = sentinel.next;
			while (index > 0) {
				n = n.next;
				index--;
			}
			return n;
		} else {
			Node n = sentinel;
			while (index < size) {
				n = n.previous;
				index++;
			}
			return n;
		}
		
	}
	
	@Override
	public Object get(int index) {
		return getNode(index).value;
	}
	
	@Override
	public int indexOf(Object object) {
		int i = 0;
		Node n = sentinel.next;
		while (n != sentinel) {
			if (n.value.equals(object))
				return i;
			n = n.next;
			i++;
		}
		return -1;
	}
	
	/**
	 * @post | size() == 0
	 */
	public LinkedList() {
		sentinel = new Node();
		sentinel.next = sentinel;
		sentinel.previous = sentinel;
	}
	
	@Override
	public void add(int index, Object object) {
		Node next = getNode(index);
		Node newNode = new Node();
		newNode.next = next;
		newNode.previous = next.previous;
		newNode.value = object;
		newNode.next.previous = newNode;
		newNode.previous.next = newNode;
		size++;
	}
	
	@Override
	public void remove(int index) {
		Node n = getNode(index);
		n.next.previous = n.previous;
		n.previous.next = n.next;
		size--;
	}

}
