package edu.iastate.cs228.hw4;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.Assert.*;

public class BinarySearchTreeTest {
	BinarySearchTree<Integer> tree;

	private static final boolean TEST_NULL = true;

	private static final boolean ADD_E = true;
	private static final boolean REMOVE_E = true;
	private static final boolean GET_E = true;
	private static final boolean CONTAINS_OBJECT = true;
	private static final boolean CLEAR = true;
	private static final boolean ISEMPTY = true;
	private static final boolean SIZE = true;
	private static final boolean ITERATOR = true;

	@Before
	public void setTree() {
		tree = new BinarySearchTree<Integer>();
	}

	@Test
	public void testAdd() throws Exception {
		testFail("testAdd", ADD_E, GET_E || CONTAINS_OBJECT);

		for (int i = 0; i < 50; i++) {
			add("add(" + i + ") returned false", true, i);
			contains("add(" + i + ") didn't add the value", true, i);
			get("add(" + i + ") added an incorrect value", i);
		}
		for (int i = 0; i < 50; i++)
			add("add(" + i + ") returned true when the tree already had " + i,
					false, i);

		for (int i = 0; i < 25; i++) {
			contains("add(" + i + ") didn't correctly add the value", true, i);
			get("add(" + i + ") added an incorrect value", i);
		}

		if (TEST_NULL) {
			try {
				add("add(null) returned true", false, null);
				fail("IllegalArgumentException not thrown");
			} catch (IllegalArgumentException e) {

			} catch (Exception e) {
				fail("Wrong exception thrown");
			}
		}
	}

	@Test
	public void testRemove() throws Exception {
		testFail("testRemove", REMOVE_E, ADD_E, CONTAINS_OBJECT);

		remove("remove(5) returned true on an empty tree", false, 5);
		size("remove(5) decremented size after it returned false", 0);

		ArrayList<Integer> data = new ArrayList<Integer>(20);
		for (int i = 0; i < 20; i++) {
			int value = new Integer((int) (Math.random() * Integer.MAX_VALUE));
			data.add(value);
			add(value);
		}

		while (data.size() > 0) {
			int toRemove = data.remove((int) (Math.random() * data.size()))
					.intValue();

			contains("contains(" + toRemove
					+ ") returned false when the tree supposedly had it", true,
					toRemove);
			remove("remove(" + toRemove
					+ ") returned false when the tree had it", true, toRemove);
			contains("contains(" + toRemove + ") returned true after remove("
					+ toRemove + ") was called", false, toRemove);
			size("remove(" + toRemove + ") didn't decrement the size",
					data.size());
		}

		if (TEST_NULL) {
			assertFalse("remove(null) returned true", tree.remove(null));
			size("remove(null) decremented the size after it returned false", 0);
		}
	}

	@Test
	public void testGet() throws Exception {
		testFail("testGet", GET_E, ADD_E);

		assertNull("get(5) returned non-null on an empty tree",
				tree.get(new Integer(5)));

		for (int i = 0; i < 50; i++)
			add(2 * i);

		for (int i = 0; i < 100; i++) {
			if (i % 2 == 0)
				get("get(" + i + ") returned the wrong output", i);
			else
				assertNull("get(" + i + ") returned non null when " + i
						+ " didn't exist in the tree", tree.get(new Integer(i)));
		}

		tree = new BinarySearchTree<Integer>();

		ArrayList<Integer> values = new ArrayList<Integer>(50);
		for (int i = 0; i < 50; i++) {
			Integer value = new Integer(
					(int) (Math.random() * Integer.MAX_VALUE));
			values.add(value);
			tree.add(value);
		}

		for (Integer I : values)
			assertTrue("get(" + I + ") returned the wrong reference",
					I == tree.get(I));

		if (TEST_NULL)
			assertNull("get(null) returned non null", tree.get(null));
	}

	@Test
	public void testContains() throws Exception {
		testFail("testContains", CONTAINS_OBJECT, ADD_E);

		ArrayList<Integer> values = new ArrayList<Integer>(50);

		for (int i = 0; i < 50; i++) {
			values.add(new Integer((int) (Math.random() * Integer.MAX_VALUE)));
			add(values.get(values.size() - 1));
		}

		for (Integer I : values) {
			contains("contains(" + I.intValue() + ") returned false", true, I);
			contains("contains(new Integer(" + I.intValue()
					+ ")) returned false", true, new Integer(I.intValue()));
		}

		for (int i = 0; i < 50; i++) {
			int value = (int) (Math.random() * Integer.MAX_VALUE);

			if (!values.contains(value))
				contains("contains(" + value + ") returned true", false, value);
			else
				contains("contains(" + value + ") returned false", true, value);
		}

		if (TEST_NULL)
			contains("contains(null) returned true", false, null);
	}

	@Test
	public void testClear() throws Exception {
		testFail("testClear", ADD_E, CLEAR, SIZE);

		clear();
		size("clear() resulted in a nonzero size on an empty tree", 0);

		for (int i = 0; i < 100; i++)
			add(i);

		clear();
		size("clear() resulted in a nonzero size on a tree of size 100", 0);

		if (CONTAINS_OBJECT) {
			for (int i = 0; i < 100; i++)
				contains("contains(" + i
						+ ") returned true after calling clear()", false, i);
		}
	}

	@Test
	public void testIsEmpty() throws Exception {
		testFail("testIsEmpty", ISEMPTY);

		isEmpty("isEmpty() returned false on an empty tree", true);

		if (!ADD_E)
			return;

		add(5);

		isEmpty("isEmpty() returned true on a non empty tree", false);

		if (CLEAR)
			clear();
		else if (REMOVE_E)
			remove("remove(5) returend false", true, 5);

		isEmpty("isEmpty() returned false after clearing the tree", true);
	}

	@Test
	public void testSize() throws Exception {
		testFail("testSize", SIZE);

		size("size() returned a non zero value on an empty tree", 0);

		if (!ADD_E)
			return;

		for (int i = 0; i < 100; i++) {
			add(i);
			size("size() didn't increment with adding", i + 1);
		}

		if (REMOVE_E) {
			for (int i = 0; i < 25; i++) {
				remove("remove(" + i + ") returned false when it had " + i,
						true, i);
				size("size() didn't decrement with removing", 99 - i);
			}
		}

		if (CLEAR) {
			clear();
			size("size() didn't return 0 after clear()", 0);
		}
	}

	@Test
	public void testIterator() throws Exception {
		testFail("testIterator", ITERATOR, ADD_E, CONTAINS_OBJECT);

		Iterator<Integer> iterator;

		iterator = tree.iterator();
		assertFalse("iterator.hasNext() returned true on an empty tree",
				iterator.hasNext());

		for (int i = 0; i < 100; i++)
			add(i);

		iterator = tree.iterator();

		try {
			iterator.remove();
			fail("IllegalStateException expected but not thrown");
		} catch (IllegalStateException e) {
			iterator = tree.iterator();
		} catch (Exception e) {
			fail("Wrong exception thrown");
		}

		/*
		 * Testing hasNext and next
		 */
		for (int i = 0; i < 100; i++) {
			assertTrue("iterator.hasNext() returned false", iterator.hasNext());
			assertEquals("iterator.next() was not equal to " + i, i, iterator
					.next().intValue());
			assertEquals("iterator.hasNext() returned the wrong value", i < 99,
					iterator.hasNext());
		}

		try {
			iterator.next();
			fail("IllegalStateException expected but not thrown");
		} catch (IllegalStateException e) {
			iterator = tree.iterator();
		} catch (Exception e) {
			fail("Wrong exception thrown");
		}

		for (int i = 0; i <= 10; i++)
			iterator.next();

		iterator.remove();
		contains("10 wasn't removed from the tree after remove()", false, 10);

		try {
			iterator.remove();
			fail("IllegalStateException expected but not thrown");
		} catch (IllegalStateException e) {
			iterator = tree.iterator();
		} catch (Exception e) {
			fail("Wrong exception thrown");
		}
	}

	@Test
	public void testGetInorderTraversal() throws Exception {
		for (int i = 0; i < 100; i++)
			tree.add((int) (Math.random() * Integer.MAX_VALUE));

		int last = 0;
		for (Integer I : tree.getInorderTraversal())
			assertTrue("getInorderTraversal returned out of order data",
					last <= I);
	}

	private void add(int i) {
		add("add(E) returned false", true, i);
	}

	private void add(String message, boolean expected, Integer I) {
		if (ADD_E)
			assertEquals(message, expected, tree.add(I));
	}

	public void contains(String message, boolean expected, Integer data) {
		if (CONTAINS_OBJECT)
			assertEquals(message, expected, tree.contains(data));
	}

	public void get(String message, int expected) {
		if (GET_E)
			assertEquals(message, expected, tree.get(new Integer(expected))
					.intValue());
	}

	private void remove(String message, boolean expected, int toRemove) {
		if (REMOVE_E)
			assertEquals(message, expected, tree.remove(new Integer(toRemove)));
	}

	private void size(String message, int expected) {
		if (SIZE)
			assertEquals(message, expected, tree.size());
	}

	private void clear() {
		if (CLEAR)
			tree.clear();
	}

	private void isEmpty(String message, boolean expected) {
		if (ISEMPTY && SIZE)
			assertEquals(message, expected, tree.isEmpty());
	}

	private static void testFail(String method, boolean... tests) {
		test: {
			for (boolean b : tests)
				if (!b)
					break test;
			return;
		}

		fail(String.format("Required methods for %s() not tested", method));
	}
}