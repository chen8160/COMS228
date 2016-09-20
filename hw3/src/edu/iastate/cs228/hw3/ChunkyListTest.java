package edu.iastate.cs228.hw3;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.junit.Test;

public class ChunkyListTest {

	@Test
	public void testChunkyListInt() {
		ChunkyList<String> l = new ChunkyList<String>(6);
		assertEquals("Did not add item correctly.", "[]", l.toStringInternal());
		l.add("A");
		assertEquals("Did not add item correctly.", "[(A, -, -, -, -, -)]",
				l.toStringInternal());
		l.add("B");
		assertEquals("Did not add item correctly.", "[(A, B, -, -, -, -)]",
				l.toStringInternal());
		l.add("C");
		assertEquals("Did not add item correctly.", "[(A, B, C, -, -, -)]",
				l.toStringInternal());
		l.add("D");
		assertEquals("Did not add item correctly.", "[(A, B, C, D, -, -)]",
				l.toStringInternal());
		l.add("E");
		assertEquals("Did not add item correctly.", "[(A, B, C, D, E, -)]",
				l.toStringInternal());
		l.add("F");
		assertEquals("Did not add item correctly.", "[(A, B, C, D, E, F)]",
				l.toStringInternal());
		l.add("G");
		assertEquals("Did not add item correctly.",
				"[(A, B, C, D, E, F), (G, -, -, -, -, -)]",
				l.toStringInternal());
		l.add("H");
		assertEquals("Did not add item correctly.",
				"[(A, B, C, D, E, F), (G, H, -, -, -, -)]",
				l.toStringInternal());
		l.add("I");
		assertEquals("Did not add item correctly.",
				"[(A, B, C, D, E, F), (G, H, I, -, -, -)]",
				l.toStringInternal());
		l.add("J");
		assertEquals("Did not add item correctly.",
				"[(A, B, C, D, E, F), (G, H, I, J, -, -)]",
				l.toStringInternal());
		l.add("K");
		assertEquals("Did not add item correctly.",
				"[(A, B, C, D, E, F), (G, H, I, J, K, -)]",
				l.toStringInternal());
		l.add("L");
		assertEquals("Did not add item correctly.",
				"[(A, B, C, D, E, F), (G, H, I, J, K, L)]",
				l.toStringInternal());
		l.add("M");
		assertEquals("Did not add item correctly.",
				"[(A, B, C, D, E, F), (G, H, I, J, K, L), (M, -, -, -, -, -)]",
				l.toStringInternal());

		l.remove(12);
		assertEquals("Did not add item correctly.",
				"[(A, B, C, D, E, F), (G, H, I, J, K, L)]",
				l.toStringInternal());

		l.remove(0);
		assertEquals("Did not add item correctly.",
				"[(B, C, D, E, F, -), (G, H, I, J, K, L)]",
				l.toStringInternal());

		l.remove(0);
		assertEquals("Did not add item correctly.",
				"[(C, D, E, F, -, -), (G, H, I, J, K, L)]",
				l.toStringInternal());

		l.remove(0);
		assertEquals("Did not add item correctly.",
				"[(D, E, F, -, -, -), (G, H, I, J, K, L)]",
				l.toStringInternal());

		l.remove(0);
		assertEquals("Did not add item correctly.",
				"[(E, F, G, -, -, -), (H, I, J, K, L, -)]",
				l.toStringInternal());

		l.remove(0);
		assertEquals("Did not add item correctly.",
				"[(F, G, H, -, -, -), (I, J, K, L, -, -)]",
				l.toStringInternal());

		l.remove(0);
		assertEquals("Did not add item correctly.",
				"[(G, H, I, -, -, -), (J, K, L, -, -, -)]",
				l.toStringInternal());

		l.remove(0);
		assertEquals("Did not add item correctly.", "[(H, I, J, K, L, -)]",
				l.toStringInternal());

		l.remove(0);
		assertEquals("Did not add item correctly.", "[(I, J, K, L, -, -)]",
				l.toStringInternal());

		l.add("M");
		l.add("N");
		assertEquals("Did not add item correctly.", "[(I, J, K, L, M, N)]",
				l.toStringInternal());

		l.add(4, "O");
		assertEquals("Did not add item correctly.",
				"[(I, J, K, -, -, -), (L, O, M, N, -, -)]",
				l.toStringInternal());

		l.add("P");
		l.add("Q");
		assertEquals("Did not add item correctly.",
				"[(I, J, K, -, -, -), (L, O, M, N, P, Q)]",
				l.toStringInternal());

		l.add(3, "S");
		assertEquals("Did not add item correctly.",
				"[(I, J, K, S, -, -), (L, O, M, N, P, Q)]",
				l.toStringInternal());

		l.add(7, "R");
		assertEquals("Did not add item correctly.",
				"[(I, J, K, S, -, -), (L, O, M, R, -, -), (N, P, Q, -, -, -)]",
				l.toStringInternal());
	}

	@Test
	public void testAddE() {
		ChunkyList<String> l = new ChunkyList<String>();
		assertEquals("Did not add item correctly.", "[]", l.toStringInternal());
		l.add("A");
		assertEquals("Did not add item correctly.", "[(A, -, -, -)]",
				l.toStringInternal());
		l.add("B");
		assertEquals("Did not add item correctly.", "[(A, B, -, -)]",
				l.toStringInternal());
		l.add("C");
		assertEquals("Did not add item correctly.", "[(A, B, C, -)]",
				l.toStringInternal());
		l.add("D");
		assertEquals("Did not add item correctly.", "[(A, B, C, D)]",
				l.toStringInternal());
		l.add("E");
		assertEquals("Did not add item correctly.",
				"[(A, B, C, D), (E, -, -, -)]", l.toStringInternal());
		l.add("F");
		assertEquals("Did not add item correctly.",
				"[(A, B, C, D), (E, F, -, -)]", l.toStringInternal());
		l.add("G");
		assertEquals("Did not add item correctly.",
				"[(A, B, C, D), (E, F, G, -)]", l.toStringInternal());
		l.add("H");
		assertEquals("Did not add item correctly.",
				"[(A, B, C, D), (E, F, G, H)]", l.toStringInternal());
		l.add("I");
		assertEquals("Did not add item correctly.",
				"[(A, B, C, D), (E, F, G, H), (I, -, -, -)]",
				l.toStringInternal());
		l.add("J");
		assertEquals("Did not add item correctly.",
				"[(A, B, C, D), (E, F, G, H), (I, J, -, -)]",
				l.toStringInternal());

	}

	@Test
	public void testAddIntEtestRemoveInttestSize() {

		ChunkyList<String> l = new ChunkyList<String>();
		assertEquals("Size not returning expected result.", 0, l.size());
		assertEquals("Did not add item correctly.", "[]", l.toStringInternal());
		l.add(0, "A");
		assertEquals(
				"Did not add item correctly to position 0 with empty array.",
				"[(A, -, -, -)]", l.toStringInternal());
		assertEquals("Size not returning expected result.", 1, l.size());
		l.add("B");
		assertEquals("Did not add item correctly.", "[(A, B, -, -)]",
				l.toStringInternal());
		assertEquals("Size not returning expected result.", 2, l.size());
		l.add("C");
		assertEquals("Did not add item correctly.", "[(A, B, C, -)]",
				l.toStringInternal());
		assertEquals("Size not returning expected result.", 3, l.size());
		l.add("D");
		assertEquals("Did not add item correctly.", "[(A, B, C, D)]",
				l.toStringInternal());
		assertEquals("Size not returning expected result.", 4, l.size());
		l.add("E");
		assertEquals("Did not add item correctly.",
				"[(A, B, C, D), (E, -, -, -)]", l.toStringInternal());
		assertEquals("Size not returning expected result.", 5, l.size());
		l.add("F");
		assertEquals("Did not add item correctly.",
				"[(A, B, C, D), (E, F, -, -)]", l.toStringInternal());
		assertEquals("Size not returning expected result.", 6, l.size());
		l.add("G");
		assertEquals("Did not add item correctly.",
				"[(A, B, C, D), (E, F, G, -)]", l.toStringInternal());
		assertEquals("Size not returning expected result.", 7, l.size());
		l.add("H");
		assertEquals("Did not add item correctly.",
				"[(A, B, C, D), (E, F, G, H)]", l.toStringInternal());
		assertEquals("Size not returning expected result.", 8, l.size());
		l.add("I");
		assertEquals("Did not add item correctly.",
				"[(A, B, C, D), (E, F, G, H), (I, -, -, -)]",
				l.toStringInternal());
		assertEquals("Size not returning expected result.", 9, l.size());
		l.add("J");
		assertEquals("Did not add item correctly.",
				"[(A, B, C, D), (E, F, G, H), (I, J, -, -)]",
				l.toStringInternal());
		assertEquals("Size not returning expected result.", 10, l.size());

		// l.add(3, "Z");
		l.add(10, "K");
		assertEquals("Test adding by position to the end of the list.",
				"[(A, B, C, D), (E, F, G, H), (I, J, K, -)]",
				l.toStringInternal());
		assertEquals("Size not returning expected result.", 11, l.size());
		l.add(11, "L");
		assertEquals("Test adding by position to the end of the list.",
				"[(A, B, C, D), (E, F, G, H), (I, J, K, L)]",
				l.toStringInternal());
		assertEquals("Size not returning expected result.", 12, l.size());
		l.add(12, "M");
		assertEquals("Test adding by position to the end of the list.",
				"[(A, B, C, D), (E, F, G, H), (I, J, K, L), (M, -, -, -)]",
				l.toStringInternal());
		assertEquals("Size not returning expected result.", 13, l.size());
		l.add(13, "N");
		assertEquals("Test adding by position to the end of the list.",
				"[(A, B, C, D), (E, F, G, H), (I, J, K, L), (M, N, -, -)]",
				l.toStringInternal());
		assertEquals("Size not returning expected result.", 14, l.size());
		l.add(14, "O");
		assertEquals("Test adding by position to the end of the list.",
				"[(A, B, C, D), (E, F, G, H), (I, J, K, L), (M, N, O, -)]",
				l.toStringInternal());
		assertEquals("Size not returning expected result.", 15, l.size());
		l.add(15, "P");
		assertEquals("Test adding by position to the end of the list.",
				"[(A, B, C, D), (E, F, G, H), (I, J, K, L), (M, N, O, P)]",
				l.toStringInternal());
		assertEquals("Size not returning expected result.", 16, l.size());
		l.add(16, "Q");
		assertEquals(
				"Test adding by position to the end of the list.",
				"[(A, B, C, D), (E, F, G, H), (I, J, K, L), (M, N, O, P), (Q, -, -, -)]",
				l.toStringInternal());
		assertEquals("Size not returning expected result.", 17, l.size());

		l.add(1, "Z");
		assertEquals(
				"Test a split of the first node. Z should be added to node 1.",
				"[(A, Z, B, -), (C, D, -, -), (E, F, G, H), (I, J, K, L), (M, N, O, P), (Q, -, -, -)]",
				l.toStringInternal());
		assertEquals("Size not returning expected result.", 18, l.size());
		l.add(5, "Y");
		assertEquals(
				"Test adding to a partly full node 2. Y should be in node 2.",
				"[(A, Z, B, -), (C, D, Y, -), (E, F, G, H), (I, J, K, L), (M, N, O, P), (Q, -, -, -)]",
				l.toStringInternal());
		assertEquals("Size not returning expected result.", 19, l.size());
		l.add(8, "X");
		assertEquals(
				"Test adding X to the full third node. It should be added to the fourth node after the third node is split.",
				"[(A, Z, B, -), (C, D, Y, -), (E, F, X, -), (G, H, -, -), (I, J, K, L), (M, N, O, P), (Q, -, -, -)]",
				l.toStringInternal());
		assertEquals("Size not returning expected result.", 20, l.size());
		l.add(11, "W");
		assertEquals(
				"Test adding W at position 11. Current position 11 is is node 5 offset 1. Should add to prior node.",
				"[(A, Z, B, -), (C, D, Y, -), (E, F, X, -), (G, H, W, -), (I, J, K, L), (M, N, O, P), (Q, -, -, -)]",
				l.toStringInternal());
		assertEquals("Size not returning expected result.", 21, l.size());

		l = new ChunkyList<String>();
		l.add("A");
		l.add("B");
		l.add("C");
		l.add("E");
		l.add(3, "D");
		assertEquals("Initial conditions for project spec add tests.",
				"[(A, B, -, -), (C, D, E, -)]", l.toStringInternal());
		assertEquals("Size not returning expected result.", 5, l.size());

		l.add("V");
		assertEquals(
				"Partly completed example adding from project specification.",
				"[(A, B, -, -), (C, D, E, V)]", l.toStringInternal());
		assertEquals("Size not returning expected result.", 6, l.size());

		l.add("W");
		assertEquals(
				"Partly completed example adding from project specification.",
				"[(A, B, -, -), (C, D, E, V), (W, -, -, -)]",
				l.toStringInternal());
		assertEquals("Size not returning expected result.", 7, l.size());

		l.add(2, "X");
		assertEquals(
				"Partly completed example adding from project specification.",
				"[(A, B, X, -), (C, D, E, V), (W, -, -, -)]",
				l.toStringInternal());
		assertEquals("Size not returning expected result.", 8, l.size());

		l.add(2, "Y");
		assertEquals(
				"Partly completed example adding from project specification.",
				"[(A, B, Y, X), (C, D, E, V), (W, -, -, -)]",
				l.toStringInternal());
		assertEquals("Size not returning expected result.", 9, l.size());

		l.add(2, "Z");
		assertEquals(
				"Final test of example adding from project specification.",
				"[(A, B, Z, -), (Y, X, -, -), (C, D, E, V), (W, -, -, -)]",
				l.toStringInternal());
		assertEquals("Size not returning expected result.", 10, l.size());

		boolean throwTest = false;

		try {
			l.add(null);
		} catch (NullPointerException e) {
			throwTest = true;
			assertEquals(
					"Size not returning expected result after add exception.",
					10, l.size());
		}

		assertTrue(
				"Add(int, Item) did not throw null exception when passed a null item.",
				throwTest);

		throwTest = false;
		try {
			l.add(l.size() + 1, "S");
		} catch (IndexOutOfBoundsException e) {
			throwTest = true;
			assertEquals(
					"Size not returning expected result after add exception.",
					10, l.size());
		}

		assertTrue(
				"Add(int, Item) did not throw and illegal argument exception when passing a position that is too large.",
				throwTest);

		throwTest = false;
		try {
			l.add(-1, "S");
		} catch (IndexOutOfBoundsException e) {
			throwTest = true;
			assertEquals(
					"Size not returning expected result after add exception.",
					10, l.size());
		}

		assertTrue(
				"Add(int, Item) did not throw and illegal argument exception when passing a position that is too small.",
				throwTest);

		/*
		 * Now testing the remove examples... Since we needed the exact same set
		 * of adds to get here.
		 */

		l.remove(9);
		assertEquals(
				"Final test of example adding from project specification.",
				"[(A, B, Z, -), (Y, X, -, -), (C, D, E, V)]",
				l.toStringInternal());
		assertEquals("Size not returning expected result.", 9, l.size());

		l.remove(3);
		assertEquals(
				"Final test of example adding from project specification.",
				"[(A, B, Z, -), (X, C, -, -), (D, E, V, -)]",
				l.toStringInternal());
		assertEquals("Size not returning expected result.", 8, l.size());

		l.remove(3);
		assertEquals(
				"Final test of example adding from project specification.",
				"[(A, B, Z, -), (C, D, -, -), (E, V, -, -)]",
				l.toStringInternal());
		assertEquals("Size not returning expected result.", 7, l.size());

		l.remove(5);
		assertEquals(
				"Final test of example adding from project specification.",
				"[(A, B, Z, -), (C, D, -, -), (V, -, -, -)]",
				l.toStringInternal());
		assertEquals("Size not returning expected result.", 6, l.size());

		l.remove(3);
		assertEquals(
				"Final test of example adding from project specification.",
				"[(A, B, Z, -), (D, V, -, -)]", l.toStringInternal());
		assertEquals("Size not returning expected result.", 5, l.size());

		// This completes the specification based remove examples.

	}

	@Test
	public void testIterator() {

		// Note, only testing basic Iterator funcationality. Edge cases tested
		// in ListIterator tests.
		// This code assumes that Iterator is just a cast ChunkyListIterator.

		Iterator<String> i;

		ChunkyList<String> l = new ChunkyList<String>();
		l.add("A");
		l.add("B");
		l.add("C");
		l.add("D");
		l.add("E");
		l.add("F");
		l.add("G");
		l.add("H");
		l.add("I");
		l.add("J");
		assertEquals("Did not initialize ChunkyList correctly.",
				"[(A, B, C, D), (E, F, G, H), (I, J, -, -)]",
				l.toStringInternal());

		i = l.iterator();

		assertTrue("HasNext() should have returned true.", i.hasNext());
		assertEquals("Next() should have returned 'A'.", "A", i.next());

		assertTrue("HasNext() should have returned true.", i.hasNext());
		assertEquals("Next() should have returned 'B'.", "B", i.next());

		assertTrue("HasNext() should have returned true.", i.hasNext());
		assertEquals("Next() should have returned 'C'.", "C", i.next());

		assertTrue("HasNext() should have returned true.", i.hasNext());
		assertEquals("Next() should have returned 'D'.", "D", i.next());

		assertTrue("HasNext() should have returned true.", i.hasNext());
		assertEquals("Next() should have returned 'E'.", "E", i.next());

		assertTrue("HasNext() should have returned true.", i.hasNext());
		assertEquals("Next() should have returned 'F'.", "F", i.next());

		assertTrue("HasNext() should have returned true.", i.hasNext());
		assertEquals("Next() should have returned 'G'.", "G", i.next());

		assertTrue("HasNext() should have returned true.", i.hasNext());
		assertEquals("Next() should have returned 'H'.", "H", i.next());

		assertTrue("HasNext() should have returned true.", i.hasNext());
		assertEquals("Next() should have returned 'I'.", "I", i.next());

		assertTrue("HasNext() should have returned true.", i.hasNext());
		assertEquals("Next() should have returned 'J'.", "J", i.next());

		assertFalse("HasNext() should have returned false at end of list.",
				i.hasNext());

		i = l.iterator();

		assertTrue("HasNext() should have returned true.", i.hasNext());
		assertEquals("Next() should have returned 'A'.", "A", i.next());
		i.remove();

		assertTrue("HasNext() should have returned true.", i.hasNext());
		assertEquals("Next() should have returned 'B'.", "B", i.next());
		i.remove();

		assertTrue("HasNext() should have returned true.", i.hasNext());
		assertEquals("Next() should have returned 'C'.", "C", i.next());
		i.remove();

		assertTrue("HasNext() should have returned true.", i.hasNext());
		assertEquals("Next() should have returned 'D'.", "D", i.next());
		i.remove();

		assertTrue("HasNext() should have returned true.", i.hasNext());
		assertEquals("Next() should have returned 'E'.", "E", i.next());
		i.remove();

		assertTrue("HasNext() should have returned true.", i.hasNext());
		assertEquals("Next() should have returned 'F'.", "F", i.next());
		i.remove();

		assertTrue("HasNext() should have returned true.", i.hasNext());
		assertEquals("Next() should have returned 'G'.", "G", i.next());
		i.remove();

		assertTrue("HasNext() should have returned true.", i.hasNext());
		assertEquals("Next() should have returned 'H'.", "H", i.next());
		i.remove();

		assertTrue("HasNext() should have returned true.", i.hasNext());
		assertEquals("Next() should have returned 'I'.", "I", i.next());
		i.remove();

		assertTrue("HasNext() should have returned true.", i.hasNext());
		assertEquals("Next() should have returned 'J'.", "J", i.next());
		i.remove();

		assertFalse("HasNext() should have returned false at end of list.",
				i.hasNext());

		assertEquals("Shouldn't have any items left.", 0, l.size());
	}

	@Test
	public void testListIterator() {
		boolean didThrowException = false;
		ListIterator<String> i;

		ChunkyList<String> l = new ChunkyList<String>();
		l.add("A");
		l.add("B");
		l.add("C");
		l.add("D");
		l.add("E");
		l.add("F");
		l.add("G");
		l.add("H");
		l.add("I");
		l.add("J");
		assertEquals("Did not initialize ChunkyList correctly.",
				"[(A, B, C, D), (E, F, G, H), (I, J, -, -)]",
				l.toStringInternal());

		i = l.listIterator();
		assertEquals(
				"Did not initialize iterator to the apropriate starting index.",
				"[(| A, B, C, D), (E, F, G, H), (I, J, -, -)]",
				l.toStringInternal(i));
		assertEquals("NextIndex not correct.", 0, i.nextIndex());
		assertEquals("PreviousIndex not correct.", -1, i.previousIndex());
		assertTrue("HasNext() should have returned true.", i.hasNext());
		assertFalse("HasPrevious() should have returned false.",
				i.hasPrevious());

		try {
			i.set("TEST");
		} catch (IllegalStateException e) {
			didThrowException = true;
		}

		assertTrue(
				"Should have thrown IllegalStateException when attempting to set() before doing a previous or next.",
				didThrowException);
		didThrowException = false;

		try {
			i.remove();
		} catch (IllegalStateException e) {
			didThrowException = true;
		}

		assertTrue(
				"Should have thrown IllegalStateException when attempting to set() before doing a previous or next.",
				didThrowException);
		didThrowException = false;

		try {
			i.previous();
		} catch (NoSuchElementException e) {
			didThrowException = true;
		}

		assertTrue(
				"Should have thrown NoSuchElementException when attempting to go to previous when there is no previous element.",
				didThrowException);
		didThrowException = false;

		// Add should work without a next() or previous() being called
		i.add("K");
		assertEquals("Add preformed correctly on ChunkyList correctly.",
				"[(K, | A, B, -), (C, D, -, -), (E, F, G, H), (I, J, -, -)]",
				l.toStringInternal(i));
		assertEquals("NextIndex not correct.", 1, i.nextIndex());
		assertEquals("PreviousIndex not correct.", 0, i.previousIndex());
		assertTrue("HasNext() should have returned true.", i.hasNext());
		assertTrue("HasPrevious() should have returned true.", i.hasPrevious());

		// Test basic next and previous functionality
		assertEquals("Next() did not return expected element.", "A", i.next());
		assertEquals("Add preformed correctly on ChunkyList correctly.",
				"[(K, A, | B, -), (C, D, -, -), (E, F, G, H), (I, J, -, -)]",
				l.toStringInternal(i));
		assertEquals("NextIndex not correct.", 2, i.nextIndex());
		assertEquals("PreviousIndex not correct.", 1, i.previousIndex());
		assertTrue("HasNext() should have returned true.", i.hasNext());
		assertTrue("HasPrevious() should have returned true.", i.hasPrevious());

		assertEquals("Previous() did not return expected element.", "A",
				i.previous());
		assertEquals("Add preformed correctly on ChunkyList correctly.",
				"[(K, | A, B, -), (C, D, -, -), (E, F, G, H), (I, J, -, -)]",
				l.toStringInternal(i));
		assertEquals("NextIndex not correct.", 1, i.nextIndex());
		assertEquals("PreviousIndex not correct.", 0, i.previousIndex());
		assertTrue("HasNext() should have returned true.", i.hasNext());
		assertTrue("HasPrevious() should have returned true.", i.hasPrevious());

		assertEquals("Next() did not return expected element.", "A", i.next());
		assertEquals("Add preformed correctly on ChunkyList correctly.",
				"[(K, A, | B, -), (C, D, -, -), (E, F, G, H), (I, J, -, -)]",
				l.toStringInternal(i));
		assertEquals("NextIndex not correct.", 2, i.nextIndex());
		assertEquals("PreviousIndex not correct.", 1, i.previousIndex());
		assertTrue("HasNext() should have returned true.", i.hasNext());
		assertTrue("HasPrevious() should have returned true.", i.hasPrevious());

		assertEquals("Next() did not return expected element.", "B", i.next());
		assertEquals("Add preformed correctly on ChunkyList correctly.",
				"[(K, A, B, -), (| C, D, -, -), (E, F, G, H), (I, J, -, -)]",
				l.toStringInternal(i));
		assertEquals("NextIndex not correct.", 3, i.nextIndex());
		assertEquals("PreviousIndex not correct.", 2, i.previousIndex());
		assertTrue("HasNext() should have returned true.", i.hasNext());
		assertTrue("HasPrevious() should have returned true.", i.hasPrevious());

		assertEquals("Next() did not return expected element.", "C", i.next());
		assertEquals("Add preformed correctly on ChunkyList correctly.",
				"[(K, A, B, -), (C, | D, -, -), (E, F, G, H), (I, J, -, -)]",
				l.toStringInternal(i));
		assertEquals("NextIndex not correct.", 4, i.nextIndex());
		assertEquals("PreviousIndex not correct.", 3, i.previousIndex());
		assertTrue("HasNext() should have returned true.", i.hasNext());
		assertTrue("HasPrevious() should have returned true.", i.hasPrevious());

		assertEquals("Next() did not return expected element.", "D", i.next());
		assertEquals("Add preformed correctly on ChunkyList correctly.",
				"[(K, A, B, -), (C, D, -, -), (| E, F, G, H), (I, J, -, -)]",
				l.toStringInternal(i));
		assertEquals("NextIndex not correct.", 5, i.nextIndex());
		assertEquals("PreviousIndex not correct.", 4, i.previousIndex());
		assertTrue("HasNext() should have returned true.", i.hasNext());
		assertTrue("HasPrevious() should have returned true.", i.hasPrevious());

		assertEquals("Next() did not return expected element.", "E", i.next());
		assertEquals("Add preformed correctly on ChunkyList correctly.",
				"[(K, A, B, -), (C, D, -, -), (E, | F, G, H), (I, J, -, -)]",
				l.toStringInternal(i));
		assertEquals("NextIndex not correct.", 6, i.nextIndex());
		assertEquals("PreviousIndex not correct.", 5, i.previousIndex());
		assertTrue("HasNext() should have returned true.", i.hasNext());
		assertTrue("HasPrevious() should have returned true.", i.hasPrevious());

		assertEquals("Next() did not return expected element.", "F", i.next());
		assertEquals("Add preformed correctly on ChunkyList correctly.",
				"[(K, A, B, -), (C, D, -, -), (E, F, | G, H), (I, J, -, -)]",
				l.toStringInternal(i));
		assertEquals("NextIndex not correct.", 7, i.nextIndex());
		assertEquals("PreviousIndex not correct.", 6, i.previousIndex());
		assertTrue("HasNext() should have returned true.", i.hasNext());
		assertTrue("HasPrevious() should have returned true.", i.hasPrevious());

		assertEquals("Next() did not return expected element.", "G", i.next());
		assertEquals("Add preformed correctly on ChunkyList correctly.",
				"[(K, A, B, -), (C, D, -, -), (E, F, G, | H), (I, J, -, -)]",
				l.toStringInternal(i));
		assertEquals("NextIndex not correct.", 8, i.nextIndex());
		assertEquals("PreviousIndex not correct.", 7, i.previousIndex());
		assertTrue("HasNext() should have returned true.", i.hasNext());
		assertTrue("HasPrevious() should have returned true.", i.hasPrevious());

		assertEquals("Next() did not return expected element.", "H", i.next());
		assertEquals("Add preformed correctly on ChunkyList correctly.",
				"[(K, A, B, -), (C, D, -, -), (E, F, G, H), (| I, J, -, -)]",
				l.toStringInternal(i));
		assertEquals("NextIndex not correct.", 9, i.nextIndex());
		assertEquals("PreviousIndex not correct.", 8, i.previousIndex());
		assertTrue("HasNext() should have returned true.", i.hasNext());
		assertTrue("HasPrevious() should have returned true.", i.hasPrevious());

		assertEquals("Next() did not return expected element.", "I", i.next());
		assertEquals("Add preformed correctly on ChunkyList correctly.",
				"[(K, A, B, -), (C, D, -, -), (E, F, G, H), (I, | J, -, -)]",
				l.toStringInternal(i));
		assertEquals("NextIndex not correct.", 10, i.nextIndex());
		assertEquals("PreviousIndex not correct.", 9, i.previousIndex());
		assertTrue("HasNext() should have returned true.", i.hasNext());
		assertTrue("HasPrevious() should have returned true.", i.hasPrevious());

		assertEquals("Next() did not return expected element.", "J", i.next());
		assertEquals("Add preformed correctly on ChunkyList correctly.",
				"[(K, A, B, -), (C, D, -, -), (E, F, G, H), (I, J |, -, -)]",
				l.toStringInternal(i));
		assertEquals("NextIndex not correct.", 11, i.nextIndex());
		assertEquals("PreviousIndex not correct.", 10, i.previousIndex());
		assertFalse("HasNext() should have returned false.", i.hasNext());
		assertTrue("HasPrevious() should have returned true.", i.hasPrevious());

		try {
			i.next();
		} catch (NoSuchElementException e) {
			didThrowException = true;
		}

		assertTrue(
				"Should have thrown NoSuchElementException when attempting to go to next() when there is no next element.",
				didThrowException);
		didThrowException = false;

		assertEquals("Previous() did not return expected element.", "J",
				i.previous());
		assertEquals("Add preformed correctly on ChunkyList correctly.",
				"[(K, A, B, -), (C, D, -, -), (E, F, G, H), (I, | J, -, -)]",
				l.toStringInternal(i));
		assertEquals("NextIndex not correct.", 10, i.nextIndex());
		assertEquals("PreviousIndex not correct.", 9, i.previousIndex());
		assertTrue("HasNext() should have returned true.", i.hasNext());
		assertTrue("HasPrevious() should have returned true.", i.hasPrevious());

		assertEquals("Previous() did not return expected element.", "I",
				i.previous());
		assertEquals("Add preformed correctly on ChunkyList correctly.",
				"[(K, A, B, -), (C, D, -, -), (E, F, G, H), (| I, J, -, -)]",
				l.toStringInternal(i));
		assertEquals("NextIndex not correct.", 9, i.nextIndex());
		assertEquals("PreviousIndex not correct.", 8, i.previousIndex());
		assertTrue("HasNext() should have returned true.", i.hasNext());
		assertTrue("HasPrevious() should have returned true.", i.hasPrevious());

		assertEquals("Previous() did not return expected element.", "H",
				i.previous());
		assertEquals("Add preformed correctly on ChunkyList correctly.",
				"[(K, A, B, -), (C, D, -, -), (E, F, G, | H), (I, J, -, -)]",
				l.toStringInternal(i));
		assertEquals("NextIndex not correct.", 8, i.nextIndex());
		assertEquals("PreviousIndex not correct.", 7, i.previousIndex());
		assertTrue("HasNext() should have returned true.", i.hasNext());
		assertTrue("HasPrevious() should have returned true.", i.hasPrevious());

		assertEquals("Previous() did not return expected element.", "G",
				i.previous());
		assertEquals("Add preformed correctly on ChunkyList correctly.",
				"[(K, A, B, -), (C, D, -, -), (E, F, | G, H), (I, J, -, -)]",
				l.toStringInternal(i));
		assertEquals("NextIndex not correct.", 7, i.nextIndex());
		assertEquals("PreviousIndex not correct.", 6, i.previousIndex());
		assertTrue("HasNext() should have returned true.", i.hasNext());
		assertTrue("HasPrevious() should have returned true.", i.hasPrevious());

		assertEquals("Previous() did not return expected element.", "F",
				i.previous());
		assertEquals("Add preformed correctly on ChunkyList correctly.",
				"[(K, A, B, -), (C, D, -, -), (E, | F, G, H), (I, J, -, -)]",
				l.toStringInternal(i));
		assertEquals("NextIndex not correct.", 6, i.nextIndex());
		assertEquals("PreviousIndex not correct.", 5, i.previousIndex());
		assertTrue("HasNext() should have returned true.", i.hasNext());
		assertTrue("HasPrevious() should have returned true.", i.hasPrevious());

		assertEquals("Previous() did not return expected element.", "E",
				i.previous());
		assertEquals("Add preformed correctly on ChunkyList correctly.",
				"[(K, A, B, -), (C, D, -, -), (| E, F, G, H), (I, J, -, -)]",
				l.toStringInternal(i));
		assertEquals("NextIndex not correct.", 5, i.nextIndex());
		assertEquals("PreviousIndex not correct.", 4, i.previousIndex());
		assertTrue("HasNext() should have returned true.", i.hasNext());
		assertTrue("HasPrevious() should have returned true.", i.hasPrevious());

		assertEquals("Previous() did not return expected element.", "D",
				i.previous());
		assertEquals("Add preformed correctly on ChunkyList correctly.",
				"[(K, A, B, -), (C, | D, -, -), (E, F, G, H), (I, J, -, -)]",
				l.toStringInternal(i));
		assertEquals("NextIndex not correct.", 4, i.nextIndex());
		assertEquals("PreviousIndex not correct.", 3, i.previousIndex());
		assertTrue("HasNext() should have returned true.", i.hasNext());
		assertTrue("HasPrevious() should have returned true.", i.hasPrevious());

		assertEquals("Previous() did not return expected element.", "C",
				i.previous());
		assertEquals("Add preformed correctly on ChunkyList correctly.",
				"[(K, A, B, -), (| C, D, -, -), (E, F, G, H), (I, J, -, -)]",
				l.toStringInternal(i));
		assertEquals("NextIndex not correct.", 3, i.nextIndex());
		assertEquals("PreviousIndex not correct.", 2, i.previousIndex());
		assertTrue("HasNext() should have returned true.", i.hasNext());
		assertTrue("HasPrevious() should have returned true.", i.hasPrevious());

		assertEquals("Previous() did not return expected element.", "B",
				i.previous());
		assertEquals("Add preformed correctly on ChunkyList correctly.",
				"[(K, A, | B, -), (C, D, -, -), (E, F, G, H), (I, J, -, -)]",
				l.toStringInternal(i));
		assertEquals("NextIndex not correct.", 2, i.nextIndex());
		assertEquals("PreviousIndex not correct.", 1, i.previousIndex());
		assertTrue("HasNext() should have returned true.", i.hasNext());
		assertTrue("HasPrevious() should have returned true.", i.hasPrevious());

		assertEquals("Previous() did not return expected element.", "A",
				i.previous());
		assertEquals("Add preformed correctly on ChunkyList correctly.",
				"[(K, | A, B, -), (C, D, -, -), (E, F, G, H), (I, J, -, -)]",
				l.toStringInternal(i));
		assertEquals("NextIndex not correct.", 1, i.nextIndex());
		assertEquals("PreviousIndex not correct.", 0, i.previousIndex());
		assertTrue("HasNext() should have returned true.", i.hasNext());
		assertTrue("HasPrevious() should have returned true.", i.hasPrevious());

		assertEquals("Previous() did not return expected element.", "K",
				i.previous());
		assertEquals("Add preformed correctly on ChunkyList correctly.",
				"[(| K, A, B, -), (C, D, -, -), (E, F, G, H), (I, J, -, -)]",
				l.toStringInternal(i));
		assertEquals("NextIndex not correct.", 0, i.nextIndex());
		assertEquals("PreviousIndex not correct.", -1, i.previousIndex());
		assertTrue("HasNext() should have returned true.", i.hasNext());
		assertFalse("HasPrevious() should have returned false.",
				i.hasPrevious());

		try {
			i.previous();
		} catch (NoSuchElementException e) {
			didThrowException = true;
		}

		assertTrue(
				"Should have thrown NoSuchElementException when attempting to go to previous when there is no previous element.",
				didThrowException);
		didThrowException = false;

		// End basic iterator movement tests

		// Test remove after both previous() and next()
		i.next();
		i.next();
		i.next();
		i.next();

		assertEquals("Not in the right place before testing further.",
				"[(K, A, B, -), (C, | D, -, -), (E, F, G, H), (I, J, -, -)]",
				l.toStringInternal(i));
		i.remove();
		assertEquals(
				"Should have removed the C and the next item to remove should be the D with next().remove() test.",
				"[(K, A, B, -), (| D, E, -, -), (F, G, H, -), (I, J, -, -)]",
				l.toStringInternal(i));
		i.next();

		i.previous();
		assertEquals("Not in the right place before testing further.",
				"[(K, A, B, -), (| D, E, -, -), (F, G, H, -), (I, J, -, -)]",
				l.toStringInternal(i));

		i.remove();
		assertEquals(
				"Should have removed the D and the next item to remove should be the E with previous().remove() test.",
				"[(K, A, B, -), (| E, F, -, -), (G, H, -, -), (I, J, -, -)]",
				l.toStringInternal(i));

		// verify we can't remove twice
		try {
			i.remove();
		} catch (IllegalStateException e) {
			didThrowException = true;
		}

		assertTrue(
				"Should have thrown IllegalStateException when attempting to go to remove a second time without a next() or previous() call.",
				didThrowException);
		didThrowException = false;

		// Done testing basic remove stuff

		// Testing set stuff... For fun and profit.

		i.next();
		i.next();
		i.next();
		i.set("L");
		assertEquals("Did not set L correctly when testing set after next().",
				"[(K, A, B, -), (E, F, -, -), (L, | H, -, -), (I, J, -, -)]",
				l.toStringInternal(i));
		i.set("M");
		assertEquals("Did not set M correctly when testing set after next().",
				"[(K, A, B, -), (E, F, -, -), (M, | H, -, -), (I, J, -, -)]",
				l.toStringInternal(i));

		i.previous();
		i.previous();

		i.set("N");
		assertEquals(
				"Did not set M correctly when testing set after previous().",
				"[(K, A, B, -), (E, | N, -, -), (M, H, -, -), (I, J, -, -)]",
				l.toStringInternal(i));
		i.set("O");
		assertEquals(
				"Did not set M correctly when testing set after previous().",
				"[(K, A, B, -), (E, | O, -, -), (M, H, -, -), (I, J, -, -)]",
				l.toStringInternal(i));

		i.remove();
		assertEquals(
				"Did not set M correctly when testing set after previous().",
				"[(K, A, B, -), (E, | M, H, -), (I, J, -, -)]",
				l.toStringInternal(i));

		// verify we can't remove twice
		try {
			i.set("P");
			;
		} catch (IllegalStateException e) {
			didThrowException = true;
		}

		assertTrue(
				"Should have thrown IllegalStateException when attempting to go to set after a remove call.",
				didThrowException);
		didThrowException = false;

		i.next();
		i.add("Q");
		assertEquals("Did not add Q to the array successfully.",
				"[(K, A, B, -), (E, M, Q, | H), (I, J, -, -)]",
				l.toStringInternal(i));

		try {
			i.set("R");
			;
		} catch (IllegalStateException e) {
			didThrowException = true;
		}

		assertTrue(
				"Should have thrown IllegalStateException when attempting to go to set after an add call.",
				didThrowException);
		didThrowException = false;

		i.next();

		// verify we can't remove twice
		try {
			i.set(null);
		} catch (NullPointerException e) {
			didThrowException = true;
		}

		assertTrue(
				"Should have thrown NullPointerException when attempting to set a null value.",
				didThrowException);
		didThrowException = false;

		try {
			i.add(null);
		} catch (NullPointerException e) {
			didThrowException = true;
		}

		assertTrue(
				"Should have thrown NullPointerException when attempting to add a null value.",
				didThrowException);
		didThrowException = false;

		l = new ChunkyList();
		i = l.listIterator();

		i.add("A");
		i.add("B");
		i.add("C");
		i.add("D");

		assertEquals("Add examples from recitation.", "[(A, B, C, D |)]",
				l.toStringInternal(i));

		i.previous();
		i.previous();
		i.previous();
		i.remove();

		try {
			i.remove();
		} catch (IllegalStateException e) {
			didThrowException = true;
		}

		assertTrue(
				"Should have thrown IllegalStateException when attempting to remove twice.",
				didThrowException);
		didThrowException = false;

		assertEquals("Add examples from recitation.", "[(A, | C, D, -)]",
				l.toStringInternal(i));

		System.out.println(l.toStringInternal(i));

		// System.out.println(l.toStringInternal(i));

		// assertEquals("Did not initialize ChunkyList correctly.",
		// "[(A, B, C, D), (E, F, G, H), (I, J, -, -)]", l.toStringInternal(i));
	}

	@Test
	public void testListIteratorInt() {

		boolean didThrowException = false;
		ListIterator<String> i;

		ChunkyList<String> l = new ChunkyList<String>();
		l.add("A");
		l.add("B");
		l.add("C");
		l.add("D");
		l.add("E");
		l.add("F");
		l.add("G");
		l.add("H");
		l.add("I");
		l.add("J");
		assertEquals("Did not initialize ChunkyList correctly.",
				"[(A, B, C, D), (E, F, G, H), (I, J, -, -)]",
				l.toStringInternal());

		i = l.listIterator(0);
		assertEquals(
				"Did not initialize iterator to the apropriate starting index.",
				"[(| A, B, C, D), (E, F, G, H), (I, J, -, -)]",
				l.toStringInternal(i));

		i = l.listIterator(1);
		assertEquals(
				"Did not initialize iterator to the apropriate starting index.",
				"[(A, | B, C, D), (E, F, G, H), (I, J, -, -)]",
				l.toStringInternal(i));

		i = l.listIterator(2);
		assertEquals(
				"Did not initialize iterator to the apropriate starting index.",
				"[(A, B, | C, D), (E, F, G, H), (I, J, -, -)]",
				l.toStringInternal(i));

		i = l.listIterator(3);
		assertEquals(
				"Did not initialize iterator to the apropriate starting index.",
				"[(A, B, C, | D), (E, F, G, H), (I, J, -, -)]",
				l.toStringInternal(i));

		i = l.listIterator(4);
		assertEquals(
				"Did not initialize iterator to the apropriate starting index.",
				"[(A, B, C, D), (| E, F, G, H), (I, J, -, -)]",
				l.toStringInternal(i));

		i = l.listIterator(5);
		assertEquals(
				"Did not initialize iterator to the apropriate starting index.",
				"[(A, B, C, D), (E, | F, G, H), (I, J, -, -)]",
				l.toStringInternal(i));

		i = l.listIterator(6);
		assertEquals(
				"Did not initialize iterator to the apropriate starting index.",
				"[(A, B, C, D), (E, F, | G, H), (I, J, -, -)]",
				l.toStringInternal(i));

		i = l.listIterator(7);
		assertEquals(
				"Did not initialize iterator to the apropriate starting index.",
				"[(A, B, C, D), (E, F, G, | H), (I, J, -, -)]",
				l.toStringInternal(i));

		i = l.listIterator(8);
		assertEquals(
				"Did not initialize iterator to the apropriate starting index.",
				"[(A, B, C, D), (E, F, G, H), (| I, J, -, -)]",
				l.toStringInternal(i));

		i = l.listIterator(9);
		assertEquals(
				"Did not initialize iterator to the apropriate starting index.",
				"[(A, B, C, D), (E, F, G, H), (I, | J, -, -)]",
				l.toStringInternal(i));

		i = l.listIterator(10);
		assertEquals(
				"Did not initialize iterator to the apropriate starting index.",
				"[(A, B, C, D), (E, F, G, H), (I, J |, -, -)]",
				l.toStringInternal(i));

		try {
			i = l.listIterator(11);
		} catch (IndexOutOfBoundsException e) {
			didThrowException = true;
		}

		assertTrue(
				"Threw exception when index is larger than the size() of the ChunkyList.",
				didThrowException);
		didThrowException = false;

		try {
			i = l.listIterator(-1);
		} catch (IndexOutOfBoundsException e) {
			didThrowException = true;
		}

		assertTrue("Threw exception when index is less than zero.",
				didThrowException);
		didThrowException = false;

		l = new ChunkyList<String>();

		try {
			i = l.listIterator(0);
		} catch (Exception e) {
			didThrowException = true;
		}

		assertFalse(
				"Should not have thrown exception when creating an interator wtih index zero on an empyt ChunkyList.",
				didThrowException);
		didThrowException = false;

	}

}