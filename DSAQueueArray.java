public class DSAQueueArray {
	private static int DEFAULT_CAPACITY = 100;
	private int count;
	private Object[] array;
	public DSAQueueArray() {
		this.count = 0;
		this.array = new Object[DEFAULT_CAPACITY];
	}
	public int getCount() {
		return this.count;
	}
	public void enqueue(Object thing) {
		if (this.isFull())
			throw new RuntimeException(
				"can't enqueue onto a full queue"
			);
		array[count++] = thing;
	}
	public Object dequeue() {
		if (this.isEmpty())
			throw new RuntimeException(
				"can't dequeue from an empty queue"
			);
		Object thing = array[0];
		for (int i = 0; i < count - 1; i++)
			array[i] = array[i + 1];
		--count;
		return thing;
	}
	public Object peek() {
		if (this.isEmpty())
			throw new RuntimeException(
				"can't view the top element of an empty queue"
			);
		return array[0];
	}
	public boolean isEmpty() {
		return this.count == 0;
	}
	public boolean isFull() {
		return this.count == DEFAULT_CAPACITY;
	}
}
