package teamproject;

import java.util.*;
import java.util.stream.Stream;

public class CustomList<T> implements Iterable<T> {

	private Object[] elements;
	private int size;

	public CustomList() {
		this(10);
	}

	public CustomList(int initialCapacity) {
		if (initialCapacity < 0) {
			throw new IllegalArgumentException("initialCapacity < 0");
		}
		this.elements = new Object[initialCapacity];
		this.size = 0;
	}

	public void add(T element) {
		ensureCapacity(size + 1);
		elements[size++] = element;
	}

	public T get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index:" + index + ", Size: " + size);
		}
		return (T) elements[index];
	}

	public void set(int index, T element) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		}
		elements[index] = element;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public Stream<T> stream() {
		return Arrays.stream(elements, 0, size).map(e -> (T) e);
	}

	@Override
	public Iterator<T> iterator() {
		return new CustomListIterator();
	}

	private class CustomListIterator implements Iterator<T> {
		private int cursor = 0;

		@Override
		public boolean hasNext() {
			return cursor < size;
		}

		@SuppressWarnings("unchecked")
		@Override
		public T next() {
			if (cursor >= size) {
				throw new NoSuchElementException();
			}
			return (T) elements[cursor++];
		}
	}

	private void ensureCapacity(int minCapacity) {
		if (minCapacity > elements.length) {
			grow(minCapacity);
		}
	}

	private void grow(int minCapacity) {
		int oldCapacity = elements.length;
		int newCapacity = oldCapacity + (oldCapacity >> 1);
		if (newCapacity < minCapacity) {
			newCapacity = minCapacity;
		}
		elements = Arrays.copyOf(elements, newCapacity);
	}

	@Override
	public String toString() {
		if (size == 0)
			return "[]";
		StringBuilder sb = new StringBuilder();
		sb.append('[');
		for (int i = 0; i < size; i++) {
			if (i > 0)
				sb.append(", ");
			sb.append(elements[i]);
		}
		return sb.append(']').toString();
	}
}
