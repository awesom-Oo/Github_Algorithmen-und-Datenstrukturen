package ueb04;

import java.util.*;

public class ListSet<E extends Comparable<E>> implements Set<E>, Iterable<E> {

	private static class SetNode<T> {
		private T item;
		private SetNode<T> next;

		/**
		 * constructor to build a node with no successor
		 *
		 * @param element
		 *            the value to be stored by this node
		 */
		private SetNode(T element) {
			item = element;
			next = null;
		}

		/**
		 * constructor to build a node with specified (maybe null) successor
		 *
		 * @param element
		 *            the value to be stored by this node
		 * @param reference
		 *            the next field for this node
		 */
		private SetNode(T element, SetNode<T> reference) {
			item = element;
			next = reference;
		}
	}

	// This is the start of the linked list representing this set.
	// If the set is empty, it is null
	private SetNode<E> head = null;

	@Override
	public boolean add(E element) {
		SetNode<E> newNode = new SetNode<>(element);
		SetNode<E> currentNode = head;
		SetNode<E> previousNode = null;

		if(!this.contains(element)) {
			while (currentNode != null && element.compareTo(currentNode.item) > 0) {

				previousNode = currentNode;
				currentNode = currentNode.next;

			}

			if (previousNode == null) {
				head = newNode;
			}

			else {
				previousNode.next = newNode;
			}

			newNode.next = currentNode;
			return true;
		}
		return false;
	}


	@Override
	public boolean remove(E element) {
		if (head.item.compareTo(element) == 0) {
			head = head.next;
			return true;
		}

		SetNode<E> currentNode = head;
		while (currentNode.next != null) {
			if(currentNode.next.item.compareTo(element) == 0) {
				if (currentNode.next.next != null) {
					currentNode.next = currentNode.next.next;
					return true;
				}

				currentNode.next = null;
				return true;
			}

			currentNode = currentNode.next;
		}

		return false;
	}

	@Override
	public boolean contains(E element) {
		return contains(head, element);
	}

	/**
	 * recursive private method, called by the public wrapper method
	 *
	 * @param node
	 *            the head of the remaining list, maybe null
	 * @param element
	 *            the value to be searched
	 * @return search is succesful
	 */
	private boolean contains(SetNode<E> node, E element) {
		if (node == null)
			return false;
		return node.item.compareTo(element) == 0 || contains(node.next, element);
	}


	@Override
	public boolean isEmpty() {
		return head == null;
	}


	@Override
	public void union(Set<E> set) {
		for (E element: set) {
			add(element);
		}
	}

	public void worstCaseUnion(Set<E> set) {

		Iterator<E> thisIter = this.iterator();
		Iterator<E> setIter = set.iterator();

		E thisNext = thisIter.hasNext() ? thisIter.next() : null;
		E setNext = setIter.hasNext() ? setIter.next() : null;

		while (thisNext != null && setNext != null) {
			// thisNext < setNext
			if (thisNext.compareTo(setNext) < 0) {
				thisNext = thisIter.hasNext() ? thisIter.next() : null;
			}
			else {
				//thisNext > setNext
				this.add(setNext);
				setNext = setIter.hasNext() ? setIter.next() : null;
			}
		}
	}

	@Override
	public void intersect(Set<E> set) {
		for (E e: this) {
			if(!set.contains(e)) {
				this.remove(e);
			}
		}
	}

	@Override
	public void subtract(Set<E> set) {
		for (E e: this) {
			if(set.contains(e)) {
				this.remove(e);
			}
		}
	}

	/**
	 * recursive private method, called by the public wrapper method
	 *
	 * @param node the head of the list (may be null if we are at the end)
	 * @return the string representing the list
	 */
	private String toString(SetNode<E> node) {
		if (node == null) {
			return "";
		}
		if (node.next == null) {
			return node.item.toString();
		}
		return node.item.toString() + " -> " + toString(node.next);
	}

	@Override
	public String toString() {
		return toString(head);
	}

	public Iterator<E> iterator() {
		return new Iterator<E>() {
			SetNode<E> node = head;

			@Override
			public boolean hasNext() {
				return node != null;
			}

			@Override
			public E next() {
				E element = node.item;
				node = node.next;
				return element;
			}
		};
	}


	public static void main(String[] args) {
		ListSet<Integer> list1 = new ListSet<Integer>();
		ListSet<Integer> list2 = new ListSet<Integer>();

		list1.add(991);
		list1.add(919);
		list1.add(939);
		list1.add(199);
		list1.add(969);

		list2.add(421);
		list2.add(411);
		list2.add(401);
		list2.add(321);
		list2.add(423);

		list1.union(list2);
		System.out.println(list1.toString());
	}
}