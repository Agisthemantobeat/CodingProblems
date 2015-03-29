package com.coderevisited.queue;

import java.lang.reflect.Array;
import java.util.Iterator;

/**
 * 
 * @author sureshsajja
 * 
 * @param <E>
 */
public class LinkedListQueue<E> implements Queue<E> {

	private LinkedQueueNode<E> head;
	private LinkedQueueNode<E> tail;
	private int size;

	private static class LinkedQueueNode<E> {

		private E item;
		private LinkedQueueNode<E> next;

		public LinkedQueueNode(E item, LinkedQueueNode<E> next) {
			this.item = item;
			this.next = next;
		}
	}

	public LinkedListQueue() {
		assert check();
	}

	@Override
	public Iterator<E> iterator() {
		return new ListIterator();
	}

	@Override
	public void enqueue(E item) {
		LinkedQueueNode<E> newNode = new LinkedQueueNode<>(item, null);
		if (isEmpty()) {
			tail = newNode;
			head = tail;
		} else {
			tail.next = newNode;
			tail = newNode;
		}
		size++;
		assert check();

	}

	private boolean check() {
		if (size == 0) {
			if (head != null || tail != null)
				return false;
		} else if (size == 1) {
			if (head == null || tail == null || head != tail
					|| head.next != null)
				return false;
		} else {
			if (head == null || tail == null || head == tail
					|| head.next == null || tail.next != null)
				return false;

			LinkedQueueNode<E> temp = head;
			int tempSize = 0;
			while (temp != null) {
				tempSize++;
				temp = temp.next;
			}

			if (tempSize != size)
				return false;

			temp = head;
			while (temp.next != null) {
				temp = temp.next;
			}

			if (tail != temp) {
				return false;
			}

		}
		return true;
	}

	@Override
	public E dequeue() {
		E item = null;
		if (!isEmpty()) {
			item = head.item;
			head = head.next;
			size--;
		}
		if (isEmpty())
			tail = head;
		assert check();
		return item;
	}

	@Override
	public boolean isEmpty() {
		return head == null;
	}

	@Override
	public int size() {
		return size;
	}

	private class ListIterator implements Iterator<E> {

		private LinkedQueueNode<E> current = head;

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public E next() {
			if (current == null)
				throw new RuntimeException();
			E item = current.item;
			current = current.next;
			return item;

		}

		@Override
		public void remove() {
			throw new RuntimeException();

		}

	}

	@Override
	public boolean isFull() {
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T[] toArray(T[] a) {
		if(a.length < size){
			a = (T[]) Array.newInstance(a.getClass().getComponentType(), size);
		}
		int i = 0;
		for (LinkedQueueNode<E> temp = head; temp != null; temp = temp.next) {
			a[i] = (T) temp.item;
			i++;
		}
		if(a.length > size){
			a[size] = null;
		}
		return a;
	}

}
