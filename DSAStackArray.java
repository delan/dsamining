public class DSAStackArray {
	private static int DEFAULT_CAPACITY = 100;
	private int count;
	private Object[] array;
	public DSAStackArray() {
		this.count = 0;
		this.array = new Object[DEFAULT_CAPACITY];
	}
	public int getCount() {
		return this.count;
	}
	public void push(Object thing) {
		if (this.isFull())
			throw new RuntimeException(
				"can't push onto a full stack"
			);
		array[count++] = thing;
	}
	// QUESTION: without "deleting" the object reference, will the object
	// be prevented from being GC'd, and if so, is this a problem?
	public Object pop() {
		if (this.isEmpty())
			throw new RuntimeException(
				"can't pop from an empty stack"
			);
		return array[--count];
	}
	public Object top() {
		if (this.isEmpty())
			throw new RuntimeException(
				"can't view the top element of an empty stack"
			);
		return array[count - 1];
	}
	public boolean isEmpty() {
		return this.count == 0;
	}
	public boolean isFull() {
		return this.count == DEFAULT_CAPACITY;
	}
}
