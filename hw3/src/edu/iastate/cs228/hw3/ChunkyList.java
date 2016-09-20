package edu.iastate.cs228.hw3;

import java.nio.channels.IllegalSelectorException;
import java.util.AbstractSequentialList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * @author Yuxiang Chen Implementation of the list interface based on linked
 *         nodes that store multiple items per node. Rules for adding and
 *         removing elements ensure that each node (except possibly the last
 *         one) is at least half full.
 */
public class ChunkyList<E extends Comparable<? super E>> extends
		AbstractSequentialList<E> {
	/**
	 * Default number of elements that may be stored in each node.
	 */
	private static final int DEFAULT_NODESIZE = 4;

	/**
	 * Number of elements that can be stored in each node.
	 */
	private final int nodeSize;

	/**
	 * Dummy node for head. It should be private but set to public here only for
	 * grading purpose. In practice, you should always make the head of a linked
	 * list a private instance variable.
	 */
	public Node head;

	/**
	 * Dummy node for tail.
	 */
	private Node tail;

	/**
	 * Number of elements in the list.
	 */
	private int size;

	/**
	 * Constructs an empty list with the default node size.
	 */
	public ChunkyList() {
		this(DEFAULT_NODESIZE);
	}

	/**
	 * Constructs an empty list with the given node size.
	 * 
	 * @param nodeSize
	 *            number of elements that may be stored in each node, must be an
	 *            even number
	 */
	public ChunkyList(int nodeSize) {
		if (nodeSize <= 0 || nodeSize % 2 != 0)
			throw new IllegalArgumentException();

		// dummy nodes
		head = new Node();
		tail = new Node();
		head.next = tail;
		tail.previous = head;
		this.nodeSize = nodeSize;
	}

	/**
	 * Constructor for grading only. Fully implemented.
	 * 
	 * @param head
	 * @param tail
	 * @param nodeSize
	 * @param size
	 */
	public ChunkyList(Node head, Node tail, int nodeSize, int size) {
		this.head = head;
		this.tail = tail;
		this.nodeSize = nodeSize;
		this.size = size;
	}

	/**
	 * Returns the size of the ChunkyList object
	 * 
	 * @return size of the ChunkyList, or number of elements stored in the
	 *         ChunkyList.
	 */
	@Override
	public int size() {
		if (size > Integer.MAX_VALUE)
			return Integer.MAX_VALUE;
		return size;
	}

	/**
	 * Add the item at the end of the ChunkyList. The item should not be null.
	 * Always return true, since it is always possible to add new item to the
	 * ChunkyList.
	 * 
	 * @param item
	 *            item to be added to the end.
	 * @throws NullPointerException
	 *             if item is null.
	 * @return true.
	 */
	@Override
	public boolean add(E item) throws NullPointerException {
		if (item == null)
			throw new NullPointerException();
		add(size, item);
		return true;
	}

	/**
	 * Inserts item to the specified position, pos.
	 * 
	 * @param pos
	 *            the specified position.
	 * @param item
	 *            the item to be inserted.
	 * @throws NullPointerException
	 *             if item is null.
	 * @throws IndexOutOfBoundsException
	 *             if pos is bigger than size or smaller than 0.
	 */
	@Override
	public void add(int pos, E item) {
		if (item == null)
			throw new NullPointerException();
		if (pos < 0 || pos > size)
			throw new IndexOutOfBoundsException();
		NodeInfo nodeInfo = find(pos);
		add(nodeInfo.node, nodeInfo.offset, item);
	}

	/**
	 * Remove the item at logic index pos of the ChunkyList.
	 * 
	 * @param pos
	 *            the index of the element being removed.
	 * @throws IndexOutOfBoundsException
	 *             if pos is less than 0 or greater than size
	 * @return the element previously at the specified position
	 */
	@Override
	public E remove(int pos) {
		if (pos < 0 || pos > size)
			throw new IndexOutOfBoundsException();
		NodeInfo nodeInfo = find(pos);
		return remove(nodeInfo);
	}

	/**
	 * Sort all elements in the chunky list in the NON-DECREASING order. You may
	 * do the following. Traverse the list and copy its elements into an array,
	 * deleting every visited node along the way. Then, sort the array by
	 * calling the insertionSort() method. (Note that sorting efficiency is not
	 * a concern for this project.) Finally, copy all elements from the array
	 * back to the chunky list, creating new nodes for storage. After sorting,
	 * all nodes but (possibly) the last one must be full of elements.
	 * 
	 * Comparator<E> must have been implemented for calling insertionSort().
	 */
	public void sort() {
		Iterator<E> iter = iterator();
		E[] arrayOfChunkyList = (E[]) new Comparable[size()];
		for (int i = 0; i < size; i++) {
			arrayOfChunkyList[i] = iter.next();
		}
		head.next = tail;
		tail.previous = head;
		size = 0;
		Comparator<E> comp = new genericComparator();
		insertionSort(arrayOfChunkyList, comp);
	}

	/**
	 * Sort all elements in the chunky list in the NON-INCREASING order. Call
	 * the bubbleSort() method. After sorting, all but (possibly) the last nodes
	 * must be filled with elements.
	 * 
	 * Comparable<? super E> must be implemented for calling bubbleSort().
	 */
	public void sortReverse() {
		Iterator<E> iter = iterator();
		E[] arrayOfChunkyList = (E[]) new Comparable[size];
		for (int i = 0; i < size; i++) {
			arrayOfChunkyList[i] = iter.next();
		}
		head.next = tail;
		tail.previous = head;
		size = 0;
		bubbleSort(arrayOfChunkyList);
		for(int i = 0 ; i < arrayOfChunkyList.length; i++){
			this.add(arrayOfChunkyList[i]);
		}
	}

	/**
	 * Returns an iterator over the elements in this list (in proper sequence).
	 * This implementation merely returns a list iterator over the list.
	 * 
	 * @return an iterator over the elements in this list (in proper sequence)
	 */
	@Override
	public Iterator<E> iterator() {
		return listIterator();
	}

	/**
	 * Returns a list iterator over the elements,start at logic index 0, in this
	 * list (in proper sequence).
	 * 
	 * @return a list iterator over the elements,start at logic index 0, in this
	 *         list (in proper sequence).
	 */
	@Override
	public ListIterator<E> listIterator() {
		return listIterator(0);
	}

	/**
	 * Returns a list iterator over the elements,start at a specified logic
	 * index, in this list (in proper sequence).
	 * 
	 * @param index
	 *            index of first element to be returned from the list iterator
	 *            (by a call to the next method)
	 * @return Returns a list iterator over the elements,start at a specified
	 *         logic index, in this list (in proper sequence).
	 */
	@Override
	public ListIterator<E> listIterator(int index)
			throws IndexOutOfBoundsException {
		if (index < 0 || index > size)
			throw new IndexOutOfBoundsException();
		ChunkyListIterator iter = new ChunkyListIterator(index);
		return iter;
	}

	/**
	 * Returns a string representation of this list showing the internal
	 * structure of the nodes.
	 */
	public String toStringInternal() {
		return toStringInternal(null);
	}

	/**
	 * Returns a string representation of this list showing the internal
	 * structure of the nodes and the position of the iterator.
	 * 
	 * @param iter
	 *            an iterator for this list
	 */
	public String toStringInternal(ListIterator<E> iter) {
		int count = 0;
		int position = -1;
		if (iter != null) {
			position = iter.nextIndex();
		}

		StringBuilder sb = new StringBuilder();
		sb.append('[');
		Node current = head.next;
		while (current != tail) {
			sb.append('(');
			E data = current.data[0];

			for (int i = 0; i < nodeSize; ++i) {
				if (i != 0)
					sb.append(", ");
				data = current.data[i];
				if (data == null) {
					sb.append("-");
				} else {
					if (position == count) {
						sb.append("| ");
						position = -1;
					}
					sb.append(data.toString());
					++count;

					// iterator at end
					if (position == size && count == size) {
						sb.append(" |");
						position = -1;
					}
				}
			}
			sb.append(')');
			current = current.next;
			if (current != tail)
				sb.append(", ");
		}
		sb.append("]");
		return sb.toString();
	}

	/**
	 * Node type for this list. Each node holds a maximum of nodeSize elements
	 * in an array. Empty slots are null.
	 */
	private class Node {
		/**
		 * Array of actual data elements.
		 */
		// Unchecked warning unavoidable.
		public E[] data = (E[]) new Comparable[nodeSize];

		/**
		 * Link to next node.
		 */
		public Node next;

		/**
		 * Link to previous node;
		 */
		public Node previous;

		/**
		 * Index of the next available offset in this node, also equal to the
		 * number of elements in this node.
		 */
		public int count;

		/**
		 * Adds an item to this node at the first available offset.
		 * Precondition: count < nodeSize
		 * 
		 * @param item
		 *            element to be added
		 */
		void addItem(E item) {
			if (count >= nodeSize) {
				return;
			}
			data[count++] = item;
			// useful for debugging
			// System.out.println("Added " + item.toString() + " at index " +
			// count + " to node " + Arrays.toString(data));
		}

		/**
		 * Adds an item to this node at the indicated offset, shifting elements
		 * to the right as necessary.
		 * 
		 * Precondition: count < nodeSize
		 * 
		 * @param offset
		 *            array index at which to put the new element
		 * @param item
		 *            element to be added
		 */
		void addItem(int offset, E item) {
			if (count >= nodeSize) {
				return;
			}
			for (int i = count - 1; i >= offset; --i) {
				data[i + 1] = data[i];
			}
			++count;
			data[offset] = item;
			// useful for debugging
			// System.out.println("Added " + item.toString() + " at index " +
			// offset + " to node: " + Arrays.toString(data));
		}

		/**
		 * Deletes an element from this node at the indicated offset, shifting
		 * elements left as necessary. Precondition: 0 <= offset < count
		 * 
		 * @param offset
		 */
		void removeItem(int offset) {
			E item = data[offset];
			for (int i = offset + 1; i < nodeSize; ++i) {
				data[i - 1] = data[i];
			}
			data[count - 1] = null;
			--count;
		}
	}

	/**
	 * Implementation of the ListIterator of the ChunkyList class.
	 * 
	 * @author yuxiang
	 *
	 */
	private class ChunkyListIterator implements ListIterator<E> {
		// constants you possibly use ...

		// instance variables ...
		// the logic index of cursor.
		private int cursor;
		// The NodeInfo, which contains the Node and the offset, of the last
		// element returned by next() or previous().
		private NodeInfo lastElement;
		// State variable. Only be true after calling next() or previous().
		private boolean canRemove;

		/**
		 * Default constructor
		 */
		public ChunkyListIterator() {
			cursor = 0;
			lastElement = null;
			canRemove = false;
		}

		/**
		 * Constructor finds node at a given position.
		 * 
		 * @param pos
		 *            given position, the index of the first element which will
		 *            be returned after calling next();
		 */
		public ChunkyListIterator(int pos) {
			if (pos < 0 || pos > size)
				throw new IndexOutOfBoundsException();
			cursor = pos;
			lastElement = null;
			canRemove = false;
		}

		/**
		 * Returns true if this list iterator has more elements when traversing
		 * the list in the forward direction. (In other words, returns true if
		 * next() would return an element rather than throwing an exception.)
		 * 
		 * @return true if the list iterator has more elements when traversing
		 *         the list in the forward direction
		 */
		@Override
		public boolean hasNext() {
			return cursor < size;
		}

		/**
		 * Returns the next element in the list and advances the cursor
		 * position. This method may be called repeatedly to iterate through the
		 * list, or intermixed with calls to previous() to go back and forth.
		 * (Note that alternating calls to next and previous will return the
		 * same element repeatedly.)
		 * 
		 * @return the next element in the list
		 * @throws NoSuchElementException
		 *             if the iteration has no next element
		 */
		@Override
		public E next() throws NoSuchElementException {
			if (hasNext()) {
				NodeInfo n = find(cursor++);
				lastElement = n;
				canRemove = true;
				return n.node.data[n.offset];
			}
			throw new NoSuchElementException();
		}

		/**
		 * Removes from the list the last element that was returned by next() or
		 * previous() (optional operation). This call can only be made once per
		 * call to next or previous. It can be made only if add(E) has not been
		 * called after the last call to next or previous.
		 * 
		 * @throws IllegalStateException
		 *             if neither next nor previous have been called, or remove
		 *             or add have been called after the last call to next or
		 *             previous
		 */
		@Override
		public void remove() {
			if (!canRemove)
				throw new IllegalStateException();
			NodeInfo cursorInfo = find(cursor);
			if (lastElement.node == cursorInfo.node
					&& lastElement.offset < cursorInfo.offset
					|| lastElement.node != cursorInfo.node)
				cursor--;
			ChunkyList.this.remove(lastElement);
			lastElement = null;
			canRemove = false;
		}

		/**
		 * Returns true if this list iterator has more elements when traversing
		 * the list in the reverse direction. (In other words, returns true if
		 * previous() would return an element rather than throwing an
		 * exception.)
		 * 
		 * @return true if the list iterator has more elements when traversing
		 *         the list in the reverse direction
		 */
		@Override
		public boolean hasPrevious() {
			return cursor > 0;
		}

		/**
		 * Returns the previous element in the list and moves the cursor
		 * position backwards. This method may be called repeatedly to iterate
		 * through the list backwards, or intermixed with calls to next() to go
		 * back and forth. (Note that alternating calls to next and previous
		 * will return the same element repeatedly.)
		 * 
		 * @return the previous element in the list
		 * @throws NoSuchElementException
		 *             if the iteration has no previous element
		 */
		@Override
		public E previous() throws NoSuchElementException {
			if (hasPrevious()) {
				NodeInfo n = find(--cursor);
				lastElement = n;
				canRemove = true;
				return n.node.data[n.offset];
			}
			throw new NoSuchElementException();
		}

		/**
		 * Returns the index of the element that would be returned by a
		 * subsequent call to next(). (Returns list size if the list iterator is
		 * at the end of the list.)
		 * 
		 * @return the index of the element that would be returned by a
		 *         subsequent call to next, or list size if the list iterator is
		 *         at the end of the list
		 */
		@Override
		public int nextIndex() {
			return cursor;
		}

		/**
		 * Returns the index of the element that would be returned by a
		 * subsequent call to previous(). (Returns -1 if the list iterator is at
		 * the beginning of the list.)
		 * 
		 * @return the index of the element that would be returned by a
		 *         subsequent call to previous, or -1 if the list iterator is at
		 *         the beginning of the list
		 */
		@Override
		public int previousIndex() {
			return cursor - 1;
		}

		/**
		 * Replaces the last element returned by next() or previous() with the
		 * specified element (optional operation). This call can be made only if
		 * neither remove() nor add(E) have been called after the last call to
		 * next or previous.
		 * 
		 * @param e
		 *            the element with which to replace the last element
		 *            returned by next or previous
		 * @throws NullPointerException
		 *             if e is null
		 * @throws IllegalStateException
		 *             if neither next nor previous have been called, or remove
		 *             or add have been called after the last call to next or
		 *             previous
		 */
		@Override
		public void set(E e) {
			if (e == null)
				throw new NullPointerException();
			if (!canRemove)
				throw new IllegalStateException();
			lastElement.node.data[lastElement.offset] = e;
		}

		/**
		 * Inserts the specified element into the list (optional operation). The
		 * element is inserted immediately before the element that would be
		 * returned by next(), if any, and after the element that would be
		 * returned by previous(), if any. (If the list contains no elements,
		 * the new element becomes the sole element on the list.) The new
		 * element is inserted before the implicit cursor: a subsequent call to
		 * next would be unaffected, and a subsequent call to previous would
		 * return the new element. (This call increases by one the value that
		 * would be returned by a call to nextIndex or previousIndex.)
		 * 
		 * @param e
		 *            the element to insert
		 * @throws NullPointerException
		 *             if e is null.
		 */
		@Override
		public void add(E e) {
			if (e == null)
				throw new NullPointerException();
			canRemove = false;
			ChunkyList.this.add(cursor++, e);
		}

		// Other methods you may want to add or override that could possibly
		// facilitate
		// other operations, for instance, addition, access to the previous
		// element, etc.
		//
		// ...
		//

	}

	/**
	 * Sort an array arr[] using the insertion sort algorithm in the
	 * NON-DECREASING order.
	 * 
	 * @param arr
	 *            array storing elements from the list
	 * @param comp
	 *            comparator used in sorting
	 */
	private void insertionSort(E[] arr, Comparator<? super E> comp) {

		for (int i = 1; i < arr.length; i++) {
			E temp = arr[i];
			int j = i - 1;
			while(j > -1 && comp.compare(arr[j], temp) > 0) {
				arr[j + 1] = arr[j];
				j--;
			}
			arr[j + 1] = temp;
		}
		for(int i = 0 ; i < arr.length; i++){
			this.add(arr[i]);
		}
	}

	/**
	 * Sort arr[] using the bubble sort algorithm in the NON-INCREASING order.
	 * For a description of bubble sort please refer to Section 6.1 in the
	 * project description. You must use the compareTo() method from an
	 * implementation of the Comparable interface by the class E or ? super E.
	 * 
	 * @param arr
	 *            array holding elements from the list
	 */
	private void bubbleSort(E[] arr) {
		boolean swapped = false;
		for (int i = 1; i < arr.length; i++) {
			if (arr[i - 1].compareTo(arr[i]) < 0) {
				E temp = arr[i - 1];
				arr[i - 1] = arr[i];
				arr[i] = temp;
				swapped = true;
			}
		}
		if (!swapped) {
			return;
		} else {
			bubbleSort(arr);
		}
	}

	/**
	 * NodeInfo contains the Node and the specific offset of a logic index.
	 */
	private class NodeInfo {
		/**
		 * the node which contains the logic index.
		 */
		public Node node;
		/**
		 * the corresponding offset of the logic index in the node which
		 * contains the logic index
		 */
		public int offset;

		/**
		 * Constructor of NodeInfo
		 * 
		 * @param node
		 *            the node which contains the logic index.
		 * @param offset
		 *            the corresponding offset of the logic index in the node
		 *            which contains the logic index
		 */
		public NodeInfo(Node node, int offset) {
			this.node = node;
			this.offset = offset;
		}
	}

	/**
	 * returns the node, which contains the logical index,and the offset,
	 * correspond to the logical index in the node,for the given logical index
	 * by a NodeInfo object.
	 * 
	 * @param pos
	 *            the logical index we are looking for.
	 * @return a NodeInfo object which contains the node, which contains the
	 *         logical index,and the offset, correspond to the logical index in
	 *         the node,for the given logical index
	 */
	NodeInfo find(int pos) {
		if (pos == -1)
			return new NodeInfo(head, 0);
		if (pos == size)
			return new NodeInfo(tail, 0);

		Node curr = head.next;
		int index = curr.count - 1;
		while (curr != tail && pos > index) {
			curr = curr.next;
			index += curr.count;
		}
		int offset = curr.count + pos - index - 1;
		return new NodeInfo(curr, offset);
	}

	/**
	 * helper to the add(int,E) method.
	 * 
	 * @param n
	 *            the node which the the item should be added to.
	 * @param offset
	 *            the insert position in node n of item.
	 * @param item
	 *            element being added
	 * @return the NodeInfo contains the information about the current position
	 *         of item.
	 */
	private NodeInfo add(Node n, int offset, E item) {
		if (item == null)
			throw new NullPointerException();
		NodeInfo returnNodeInfo = null;
		if (size == 0) {
			Node newNode = new Node();
			newNode.addItem(item);
			link(head, newNode);
			returnNodeInfo = new NodeInfo(newNode, 0);
		} else if (offset == 0 && n.previous.count < nodeSize
				&& n.previous != head) {
			n.previous.addItem(item);
			returnNodeInfo = new NodeInfo(n.previous, n.previous.count - 1);
		} else if (offset == 0 && n == tail && n.previous.count == nodeSize) {
			Node newNode = new Node();
			newNode.addItem(item);
			link(tail.previous, newNode);
			returnNodeInfo = new NodeInfo(newNode, 0);

		} else if (n.count < nodeSize) {
			n.addItem(offset, item);
			returnNodeInfo = new NodeInfo(n, offset);
		} else {
			Node newNode = new Node();
			link(n, newNode);
			for (int i = nodeSize - 1; i >= nodeSize - nodeSize / 2; i--) {
				newNode.addItem(0, n.data[i]);
				n.removeItem(i);
			}
			if (offset <= nodeSize / 2) {
				n.addItem(offset, item);
				returnNodeInfo = new NodeInfo(n, offset);
			} else {
				newNode.addItem(offset - nodeSize / 2, item);
				returnNodeInfo = new NodeInfo(newNode, offset - nodeSize / 2);
			}
		}
		size++;
		return returnNodeInfo;
	}

	/**
	 * Helper method to remove() methods.
	 * 
	 * @param nodeInfo
	 *            the position information of the element which is being
	 *            removed.
	 * @return the element which has been removed.
	 */
	private E remove(NodeInfo nodeInfo) {
		E returnE = nodeInfo.node.data[nodeInfo.offset];
		if (nodeInfo.node.next == tail && nodeInfo.node.count == 1) {
			unlink(nodeInfo.node);
		} else if (nodeInfo.node.next == tail
				|| nodeInfo.node.count > nodeSize / 2) {
			nodeInfo.node.removeItem(nodeInfo.offset);
		} else if (nodeInfo.node.count <= nodeSize / 2) {
			nodeInfo.node.removeItem(nodeInfo.offset);
			if (nodeInfo.node.next.count > nodeSize / 2) {
				nodeInfo.node.addItem(nodeInfo.node.next.data[0]);
				nodeInfo.node.next.removeItem(0);
			} else {
				for (E e : nodeInfo.node.next.data) {
					if (e != null)
						nodeInfo.node.addItem(e);
				}
				unlink(nodeInfo.node.next);
			}
		}
		size--;
		return returnE;
	}

	/**
	 * Add a node right after the current node.
	 * 
	 * @param current
	 *            node before the newNode which is being linked to the
	 *            ChunkyList.
	 * @param newNode
	 *            the new node which is being inserted.
	 */
	private void link(Node current, Node newNode) {
		newNode.previous = current;
		newNode.next = current.next;
		current.next.previous = newNode;
		current.next = newNode;
	}

	/**
	 * unlink the node from the whole link.
	 * 
	 * @param current
	 *            node being unlinked.
	 */
	private void unlink(Node current) {
		current.previous.next = current.next;
		current.next.previous = current.previous;
	}

	/**
	 * This class creates a generic comparator. It defines the behavior of the
	 * comparator of a generic type.
	 * 
	 * @author Yuxiang Chen
	 *
	 * @param <E>
	 */
	private class genericComparator<E extends Comparable<? super E>> implements
			Comparator {

		/**
		 * It defines the behavior of the comparator of a generic type
		 * 
		 * @param o1
		 *            the first object being compared.
		 * @param o2
		 *            the second object being compared.
		 * @return negative number if o1 is less than o2, 0 if o1 equals o2,
		 *         positive number if o1 is greater than o2.
		 */
		@Override
		public int compare(Object o1, Object o2) {
			E first = (E) o1;
			E second = (E) o2;
			return first.compareTo(second);
		}
	}

}
