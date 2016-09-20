package edu.iastate.cs228.hw4;

import java.nio.channels.IllegalSelectorException;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Extension of the AbstractCollection class based on a Binary Search Tree.
 * Efficiencies may vary by implementation, but all methods should have at least
 * the worst case runtimes of a standard Tree.
 * 
 * @author Yuxiang Chen
 */
public class BinarySearchTree<E extends Comparable<? super E>> extends
		AbstractCollection<E> {

	/**
	 * Member variables to support the tree: - A Node referencing the root of
	 * the tree - An int specifying the element count
	 */

	/**
	 * The root node of the binary search tree.
	 */
	private Node<E> root;

	/**
	 * The size of the binary search tree.
	 */
	private int size;

	/**
	 * Constructs an empty BinarySearchTree
	 */
	public BinarySearchTree() {
		root = null;
		size = 0;
	}

	/**
	 * Constructs a new BinarySearchTree whose root is exactly the given Node.
	 * (For testing purposes, set the root to the given Node, do not clone it)
	 * 
	 * @param root
	 *            - The root of the new tree
	 * @param size
	 *            - The number of elements already contained in the new tree
	 */
	public BinarySearchTree(Node<E> root, int size) {
		this.root = root;
		this.size = size;
	}

	/**
	 * Adds the given item to the tree if it is not already there.
	 * 
	 * @return false if item already exists in the tree and true otherwise.
	 * @param item
	 *            - Item to be added to the tree
	 * @throws IllegalArgumentException
	 *             - If item is null
	 */
	@Override
	public boolean add(E item) throws IllegalArgumentException {
		if (item == null) {
			throw new IllegalArgumentException();
		}
		if (root == null) {
			root = new Node<E>(item);
			size++;
			return true;
		}
		if (contains(item)) {
			return false;
		}
		Node<E> temp = findEntry(item);
		temp.setData(item);
		size++;
		return true;
	}

	/**
	 * Removes the given item from the tree if it is there. Because the item is
	 * an Object it will need to be cast to an E type. To verify that this is a
	 * safe cast, compare its class to the class of the root Node's data.
	 * 
	 * @return false if the list is empty or item does not exist in the tree,
	 *         true otherwise
	 * @param item
	 *            - The item to be removed from the tree
	 */
	@Override
	public boolean remove(Object item) {
		if (root == null)
			return false;
		if (item == null || item.getClass() != root.getData().getClass())
			return false;
		E key = (E) item;
		Node<E> n = findEntry(key);
		if (n.getData() == null)
			return false;
		unlinkNode(n);
		size--;
		return true;
	}

	/**
	 * Retrieves data of the Node in the tree that contains item. i.e. the data
	 * such that Node.data.equals(item) is true
	 * 
	 * @return null if item does not exist in the tree, otherwise the data
	 *         stored at the Node that meets the condition above.
	 * @param item
	 *            - The item to be retrieved
	 */
	public E get(E item) {
		if(item == null)
			return null;
		return findEntry(item).getData();
	}

	/**
	 * Tests whether or not item exists in the tree. i.e. this should only
	 * return true if a Node exists in the tree such that Node.data.equals(item)
	 * is true
	 * 
	 * @return false if item does not exist in the tree, otherwise true
	 * @param item
	 *            - The item check
	 */
	@Override
	public boolean contains(Object item) {
		if(item == null)
			return false;
		E key = (E) item;
		return findEntry(key).getData() != null;
	}

	/**
	 * Removes all elements from the tree
	 */
	@Override
	public void clear() {
		root = null;
		size = 0;
	}

	/**
	 * Tests whether or not the tree contains any elements.
	 * 
	 * @return false if the tree contains at least one element, true otherwise.
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Retrieves the number of elements in the tree.
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Returns a new BSTIterator instance.
	 */
	@Override
	public Iterator<E> iterator() {
		return new BSTIterator();
	}

	/**
	 * Returns an ArrayList containing all elements in the tree in the order
	 * given by a preorder traversal of the tree.
	 * 
	 * @return an ArrayList of elements from the traversal.
	 */
	public ArrayList<E> getPreorderTraversal() {
		ArrayList<E> returnList = new ArrayList<E>();
		preOrder(root, returnList);
		return returnList;
	}

	/**
	 * Returns an ArrayList containing all elements in the tree in the order
	 * given by a postorder traversal of the tree.
	 * 
	 * @return an ArrayList of elements from the traversal.
	 */
	public ArrayList<E> getPostOrderTraversal() {
		ArrayList<E> returnList = new ArrayList<E>();
		postOrder(root, returnList);
		return returnList;
	}

	/**
	 * Returns an ArrayList containing all elements in the tree in the order
	 * given by a inorder traversal of the tree.
	 * 
	 * @return an ArrayList of elements from the traversal.
	 */
	public ArrayList<E> getInorderTraversal() {
		ArrayList<E> returnList = new ArrayList<E>();
		inOrder(root, returnList);
		return returnList;
	}

	/**
	 * Implementation of the Iterator interface which returns elements in the
	 * order of an inorder traversal using Nodes predecessor and successor.
	 * 
	 * @author Yuxiang Chen
	 */
	private class BSTIterator implements Iterator<E> {

		private Node<E> curr;
		private boolean removable;

		public BSTIterator() {
			removable = false;
			curr = root;
			while (curr != null && curr.getLeft() != null) {
				curr = curr.getLeft();
			}
		}

		/**
		 * Returns true if more elements exist in the inorder traversal, false
		 * otherwise.
		 */
		@Override
		public boolean hasNext() {
			return curr != null;
		}

		/**
		 * Returns the next item in the inorder traversal.
		 * 
		 * @return the next item in the traversal.
		 * @throws IllegalStateException
		 *             - if no more elements exist in the traversal.
		 */
		@Override
		public E next() throws IllegalStateException {
			if (!hasNext())
				throw new IllegalStateException();
			E temp = curr.getData();
			curr = curr.getSuccessor();
			return temp;
		}

		/**
		 * Removes the last item that was returned by calling next().
		 * 
		 * @throws IllegalStateException
		 *             - if next() has not been called yet or remove() is called
		 *             multiple times in a row.
		 */
		@Override
		public void remove() throws IllegalStateException {
			if (!removable)
				throw new IllegalSelectorException();
			BinarySearchTree.this.remove(curr.getPredecessor().getData());
		}

	}

	/**
	 * Find the node wich contains the given element.
	 * 
	 * @param key
	 *            The element we need to find.
	 * @return the node which contains the element, if the element is in the
	 *         tree. Otherwise, return a node with nulls.
	 */
	private Node<E> findEntry(E key) {
		if(key == null)
			return null;
		Node<E> current = root;
		if (current == null) {
			current = new Node<E>(null, null, null, null);
		}
		Node<E> parent = null;
		while (current.getData() != null) {
			int comp = current.getData().compareTo(key);
			if (comp == 0) {
				return current;
			} else if (comp > 0) {
				parent = current;
				current = current.getLeft();
				if (current == null) {
					current = new Node<E>(null, null, parent, null);
					parent.setLeft(current);
				}
			} else {
				parent = current;
				current = current.getRight();
				if (current == null) {
					current = new Node<E>(null, null, parent, null);
					parent.setRight(current);
				}
			}
		}
		return current;
	}

	/**
	 * Add the elements in the tree to an array in preorder.
	 * 
	 * @param node
	 *            The node currently being visited.
	 * @param arr
	 *            The array which will contains all the elements in the tree
	 *            with preorder.
	 */
	private void preOrder(Node<E> node, ArrayList<E> arr) {
		if (node == null)
			return;
		arr.add(node.getData());
		preOrder(node.getLeft(), arr);
		preOrder(node.getRight(), arr);
	}

	/**
	 * Add the elements in the tree to an array in postorder.
	 * 
	 * @param node
	 *            The node currently being visited.
	 * @param arr
	 *            The array which will contains all the elements in the tree
	 *            with postorder.
	 */
	private void postOrder(Node<E> node, ArrayList<E> arr) {
		if (node == null)
			return;
		postOrder(node.getLeft(), arr);
		postOrder(node.getRight(), arr);
		arr.add(node.getData());
	}

	/**
	 * Add the elements in the tree to an array in inorder.
	 * 
	 * @param node
	 *            The node currently being visited.
	 * @param arr
	 *            The array which will contains all the elements in the tree
	 *            with inorder.
	 */
	private void inOrder(Node<E> node, ArrayList<E> arr) {
		if (node == null)
			return;
		inOrder(node.getLeft(), arr);
		arr.add(node.getData());
		inOrder(node.getRight(), arr);
	}

	/**
	 * Unlike the given node from the tree.
	 * 
	 * @param n
	 *            the node which will be unlinked from the tree.
	 */
	private void unlinkNode(Node<E> n) {
		if (n.getLeft() != null && n.getRight() != null) {
			Node<E> s = n.getSuccessor();
			n.setData(s.getData());
			n = s;
		}
		Node<E> replacement = null;
		if (n.getLeft() != null)
			replacement = n.getLeft();
		if (n.getRight() != null)
			replacement = n.getRight();
		if (n == root) {
			root = replacement;
		} else {
			if (n == n.getParent().getLeft())
				n.getParent().setLeft(replacement);
			else
				n.getParent().setRight(replacement);
		}
		if (replacement != null)
			replacement.setParent(n.getParent());
	}
}
