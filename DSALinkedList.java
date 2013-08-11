// DOUBLE ended, SINGLY linked list.
public class DSALinkedList {
	private class DSAListNode {
		private Object thing;
		private DSAListNode next;
		public DSAListNode(Object thing) {
			this.thing = thing;
			this.next = null;
		}
	}
	private DSAListNode head;
	private DSAListNode tail;
	public DSALinkedList() {
		this.head = null;
		this.tail = null;
	}
	public boolean isEmpty() {
		return (head == null) && (tail == null);
	}
	public Object peekFirst() {
		if (this.isEmpty())
			throw new IllegalStateException(
				"can't peek the first element of empty list"
			);
		return this.head.thing;
	}
	public Object peekLast() {
		if (this.isEmpty())
			throw new IllegalStateException(
				"can't peek the last element of empty list"
			);
		return this.tail.thing;
	}
	public void insertFirst(Object thing) {
		DSAListNode node = new DSAListNode(thing);
		if (this.isEmpty())
			this.tail = node;
		else
			node.next = this.head;
		this.head = node;
	}
	public void insertLast(Object thing) {
		DSAListNode node = new DSAListNode(thing);
		if (this.isEmpty())
			this.head = node;
		else
			this.tail.next = node;
		this.tail = node;
	}
	public Object removeFirst() {
		if (this.isEmpty())
			throw new IllegalStateException(
				"can't remove the first element of empty list"
			);
		if (this.head == this.tail)
			this.tail = null;
		Object thing = this.head.thing;
		this.head = this.head.next;
		return thing;
	}
	public Object removeLast() {
		if (this.isEmpty())
			throw new IllegalStateException(
				"can't remove the last element of empty list"
			);
		Object thing = this.tail.thing;
		if (this.head == this.tail)
			this.head = this.tail = null;
		else for (
			DSAListNode cur = this.head;
			cur != null;
			cur = cur.next
		)
			if (cur.next == this.tail)
				this.tail = cur;
		return thing;
	}
}
