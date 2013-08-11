import java.util.Iterator;
public class DSAQueue<E> implements Iterable<E> {
	private DSALinkedList<E> list;
	public DSAQueue() {
		this.list = new DSALinkedList<E>();
	}
	public void enqueue(E thing) {
		this.list.insertLast(thing);
	}
	public E dequeue() {
		if (this.isEmpty())
			throw new RuntimeException(
				"can't dequeue from an empty queue"
			);
		return this.list.removeFirst();
	}
	public E peek() {
		if (this.isEmpty())
			throw new RuntimeException(
				"can't view the top element of an empty stack"
			);
		return this.list.peekFirst();
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
