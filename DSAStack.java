import java.util.Iterator;
public class DSAStack implements Iterable {
	private DSALinkedList list;
	public DSAStack() {
		this.list = new DSALinkedList();
	}
	public void push(Object thing) {
		this.list.insertLast(thing);
	}
	public Object pop() {
		if (this.isEmpty())
			throw new RuntimeException(
				"can't pop from an empty stack"
			);
		return this.list.removeLast();
	}
	public Object top() {
		if (this.isEmpty())
			throw new RuntimeException(
				"can't view the top element of an empty stack"
			);
		return this.list.peekLast();
	}
	public boolean isEmpty() {
		try {
			Object thing = this.list.peekFirst();
			return false;
		} catch (IllegalStateException e) {
			return true;
		}
	}
	public Iterator iterator() {
		return list.iterator();
	}
}
