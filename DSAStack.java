import java.util.Iterator;
public class DSAStack<E> implements Iterable<E> {
	private DSALinkedList<E> list;
	public DSAStack() {
		this.list = new DSALinkedList<E>();
	}
	public void push(E thing) {
		this.list.insertLast(thing);
	}
	public E pop() {
		if (this.isEmpty())
			throw new RuntimeException(
				"can't pop from an empty stack"
			);
		return this.list.removeLast();
	}
	public E top() {
		if (this.isEmpty())
			throw new RuntimeException(
				"can't view the top element of an empty stack"
			);
		return this.list.peekLast();
	}
	public boolean isEmpty() {
		try {
			E thing = this.list.peekFirst();
			return false;
		} catch (IllegalStateException e) {
			return true;
		}
	}
	public Iterator<E> iterator() {
		return list.iterator();
	}
}
