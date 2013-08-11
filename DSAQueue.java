import java.util.Iterator;
public class DSAQueue implements Iterable {
	private DSALinkedList list;
	public DSAQueue() {
		this.list = new DSALinkedList();
	}
	public void enqueue(Object thing) {
		this.list.insertLast(thing);
	}
	public Object dequeue() {
		if (this.isEmpty())
			throw new RuntimeException(
				"can't dequeue from an empty queue"
			);
		return this.list.removeFirst();
	}
	public Object peek() {
		if (this.isEmpty())
			throw new RuntimeException(
				"can't view the top element of an empty stack"
			);
		return this.list.peekFirst();
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
