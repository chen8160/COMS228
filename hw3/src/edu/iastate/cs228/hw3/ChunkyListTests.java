package edu.iastate.cs228.hw3;

import static org.junit.Assert.*;

import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 *
 * @author Ryan Krause
 *
 *         A lot of unit tests for the project that I'm sure you all are working
 *         super hard on and don't have time to write unit tests. Enjoy!
 *
 */
public class ChunkyListTests {

	ChunkyList<String> chunk;
	ChunkyList<String> chunk6;
	ChunkyList<String> chunkBad;
	ListIterator<String> chunkIter;
	ListIterator<String> chunkIterPos;
	ListIterator<String> chunkIterEnd;
	ListIterator<String> chunkIterBad;
	String msg;

	@Before
	public void setup() {
		chunk = new ChunkyList<String>();
		chunk.add("A");
		chunk.add("B");
		chunk.add("X");
		chunk.add("X");
		chunk.add("C");
		chunk.add("D");
		chunk.add("E");
		chunk.remove(2); // these are so we can have spaces in nodes.
		chunk.remove(2); // don't really know another way to do that.

		chunk6 = new ChunkyList<String>(6);

		chunkIter = chunk.listIterator();
		chunkIterPos = chunk.listIterator(2);
		chunkIterEnd = chunk.listIterator(5);

	}

	@After
	public void cleanup() {
		chunk = null;
		chunk6 = null;
		chunkBad = null;
		msg = null;
		chunkIter = null;
		chunkIterPos = null;
		chunkIterBad = null;
		chunkIterEnd = null;
	}

	@Test
	public void setupTest() {
		msg = "Your setup method is broken, you should check out my PDFexample on blackboard.";
		assertEquals(msg, "[(A, B, -, -), (C, D, E, -)]",
				chunk.toStringInternal());
	}

	@Test
	public void bubbleSortTest() {
		msg = "When sort reverse is called, the items are sorted decreasingly";
		chunk.sortReverse();
		assertEquals(msg, "[(E, D, C, B), (A, -, -, -)]",
				chunk.toStringInternal());
	}

	@Test
	public void insertionSortTest() {
		msg = "When sort is called, the terms should be sorted increasingly.";
		chunk.sort();
		assertEquals(msg, "[(A, B, C, D), (E, -, -, -)]",
				chunk.toStringInternal());
	}

	@Test(expected = IllegalArgumentException.class)
	public void ChunkyListOddConstructorTest() {
		// msg =
		// "A chunky list must be constructed with M elements were M is an even positive number";
		chunkBad = new ChunkyList<String>(5);
	}

	@Test
	public void checkSize() {
		msg = "The current size of the list is 5 elements";
		assertEquals(msg, 5, chunk.size());
	}

	@Test
	public void createListIterNoArg() {
		msg = "List iterator with no arguments should have a cursor at pos 0.";
		assertEquals(msg, "[(| A, B, -, -), (C, D, E, -)]",
				chunk.toStringInternal(chunkIter));
	}

	@Test
	public void createListIterWithArg() {
		msg = "List iterator with a pos argument of 2 should have a cursor at cursor pos 2.";
		assertEquals(msg, "[(A, B, -, -), (| C, D, E, -)]",
				chunk.toStringInternal(chunkIterPos));
	}

	@Test
	public void createListIterWithArgEnd() {
		msg = "List iterator with a pos argument of 5 should have a cursor at cursor pos 5.";
		assertEquals(msg, "[(A, B, -, -), (C, D, E |, -)]",
				chunk.toStringInternal(chunkIterEnd));
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void createListIterOutOfBounds() {
		msg = "Your listIterator(super big number) should throw an outofbounds exception.";
		chunkIterBad = chunk.listIterator(500);
	}

	@Test
	public void hasNextTest0() {
		msg = "When starting at pos 0, has next should return true";
		assertTrue(msg, chunkIter.hasNext());
	}

	@Test
	public void hasNextTest2() {
		msg = "When starting at pos 2, has next should return true";
		assertTrue(msg, chunkIter.hasNext());
	}

	@Test
	public void hasNextEndTest() {
		msg = "When starting at pos 5, has next should return false";
		assertFalse(msg, chunkIterEnd.hasNext());
	}

	@Test
	public void hasPreviousStartTest() {
		msg = "When starting at pos 0, has previous should return false";
		assertFalse(msg, chunkIter.hasPrevious());
	}

	@Test
	public void hasPreviousTest2() {
		msg = "When starting at pos 2, hasprevious should return true";
		assertTrue(msg, chunkIterPos.hasPrevious());
	}

	@Test
	public void hasPreviousTestEnd() {
		msg = "When starting at the end of the list, has previous should return True";
		assertTrue(msg, chunkIterEnd.hasPrevious());
	}

	@Test
	public void nextIterStartTest() {
		msg = "When starting at pos 0, next should return 'A'";
		assertEquals(msg, "A", chunkIter.next());
	}

	@Test
	public void nextIterPos2Test() {
		msg = "When starting at pos 2, next should return 'C'";
		assertEquals(msg, "C", chunkIterPos.next());
	}

	@Test(expected = NoSuchElementException.class)
	public void nextIterEndTest() {
		msg = "When starting at the end of the list, next should throw a NoSuchElementException";
		chunkIterEnd.next();
	}

	@Test
	public void nextIndexIter2Test() {
		msg = "When starting at pos 2, nextIndex should return 2";
		assertEquals(msg, 2, chunkIterPos.nextIndex());
	}

	@Test
	public void nextIndexIterStartTest() {
		msg = "When starting at pos end, nextIndex should return end";
		assertEquals(msg, 5, chunkIterEnd.nextIndex());
	}

	@Test
	public void prevousIndexIter2Test() {
		msg = "When starting at pos 2, nextIndex should return 1";
		assertEquals(msg, 1, chunkIterPos.previousIndex());
	}

	@Test
	public void prevousIndexIterStartTest() {
		msg = "When starting at pos 0, nextIndex should return -1";
		assertEquals(msg, -1, chunkIter.previousIndex());
	}

	@Test(expected = NoSuchElementException.class)
	public void prevIterStartTest() {
		msg = "When starting at the start of the list, previous should throw a NoSuchElementException";
		chunkIter.previous();
	}

	@Test
	public void prevIterPos2Test() {
		msg = "When starting at pos 2, previous should return 'B'";
		assertEquals(msg, "B", chunkIterPos.previous());
	}

	@Test
	public void prevtIterEndTest() {
		msg = "When starting at the end of the list, previous should return 'E'";
		assertEquals(msg, "E", chunkIterEnd.previous());
	}

	@Test(expected = IllegalStateException.class)
	public void setIterfailTest() {
		msg = "when calling set on a new list, IllegalStateException is thrown";
		chunkIter.set("false");
	}

	@Test
	public void setIterNextTest() {
		msg = "When calling next then set, the item replaces the item just returned by next";
		chunkIter.next();
		chunkIter.set("a");
		assertEquals(msg, "[(a, | B, -, -), (C, D, E, -)]",
				chunk.toStringInternal(chunkIter));
	}

	@Test
	public void setIterPreviousTest() {
		msg = "When calling previous then set, the item replaces the item just returned by prevous";
		chunkIterEnd.previous();
		chunkIterEnd.set("e");
		assertEquals(msg, "[(A, B, -, -), (C, D, | e, -)]",
				chunk.toStringInternal(chunkIterEnd));
	}

	@Test
	public void addStartTest() {
		msg = "When adding using the iterator at pos 0, the item should be added at the start of the list and the cursor moved";
		chunkIter.add("X");
		assertEquals(msg, "[(X, | A, B, -), (C, D, E, -)]",
				chunk.toStringInternal(chunkIter));
	}

	@Test
	public void addIterPos2Test() {
		msg = "When adding using the iterator at pos 2, the item should be added before the cursor";
		chunkIterPos.add("X");
		assertEquals(msg, "[(A, B, X, -), (| C, D, E, -)]",
				chunk.toStringInternal(chunkIterPos));
	}

	@Test
	public void addIterEndTest() {
		msg = "When adding using the iterator at pos end, the item should be added to the end of the list";
		chunkIterEnd.add("X");
		assertEquals(msg, "[(A, B, -, -), (C, D, E, X |)]",
				chunk.toStringInternal(chunkIterEnd));
	}

	@Test(expected = IllegalStateException.class)
	public void removeErrorTest() {
		msg = "When calling a remove function without calling next or previous, it should throw an IllegalStateException";
		chunkIter.remove();
	}

	@Test
	public void removeTest1() {
		msg = "When calling next and then remove from pos 0, remove the element that was returned from next";
		chunkIter.next();
		chunkIter.remove();
		assertEquals(msg, "[(| B, C, -, -), (D, E, -, -)]",
				chunk.toStringInternal(chunkIter));
	}

	@Test
	public void removeTest2() {
		msg = "When calling next and then remove from pos 2, remove the element that was returned from next";
		chunkIterPos.next();
		chunkIterPos.remove();
		assertEquals(msg, "[(A, B, -, -), (| D, E, -, -)]",
				chunk.toStringInternal(chunkIterPos));
	}

	@Test
	public void ExamplePDFIteratorTest() {
		// non traditional unit test sorry... gotta go step by step.
		ChunkyList<String> chunk = new ChunkyList<String>();
		ListIterator<String> chunkIter = chunk.listIterator();
		chunkIter.add("A");
		chunkIter.add("B");
		chunkIter.add("X");
		chunkIter.add("X");
		chunkIter.add("C");
		chunkIter.add("D");
		chunkIter.add("E");
		chunkIter.previous();
		chunkIter.previous();
		chunkIter.previous();
		chunkIter.previous();
		chunkIter.remove();
		chunkIter.previous();
		chunkIter.remove();
		assertEquals("[(A, B, -, -), (| C, D, E, -)]",
				chunk.toStringInternal(chunkIter));

		chunkIter.next();
		chunkIter.next();
		chunkIter.next();
		chunkIter.add("V");
		assertEquals("[(A, B, -, -), (C, D, E, V |)]",
				chunk.toStringInternal(chunkIter));

		chunkIter.add("W");
		assertEquals("[(A, B, -, -), (C, D, E, V), (W |, -, -, -)]",
				chunk.toStringInternal(chunkIter));

		chunkIter.previous();
		chunkIter.previous();
		chunkIter.previous();
		chunkIter.previous();
		chunkIter.previous();
		chunkIter.add("X");
		assertEquals("[(A, B, X, -), (| C, D, E, V), (W, -, -, -)]",
				chunk.toStringInternal(chunkIter));

		chunkIter.previous();
		chunkIter.add("Y");
		assertEquals("[(A, B, Y, | X), (C, D, E, V), (W, -, -, -)]",
				chunk.toStringInternal(chunkIter));

		chunkIter.previous();
		chunkIter.add("Z");
		assertEquals(
				"[(A, B, Z, -), (| Y, X, -, -), (C, D, E, V), (W, -, -, -)]",
				chunk.toStringInternal(chunkIter));

		// this is so tediuous...

		chunkIter.next();
		chunkIter.next();
		chunkIter.next();
		chunkIter.next();
		chunkIter.next();
		chunkIter.next();
		chunkIter.next();
		chunkIter.remove();
		assertEquals("[(A, B, Z, -), (Y, X, -, -), (C, D, E, V |)]",
				chunk.toStringInternal(chunkIter));

		chunkIter.previous();
		chunkIter.previous();
		chunkIter.previous();
		chunkIter.previous();
		chunkIter.previous();
		chunkIter.previous();
		chunkIter.remove();
		assertEquals("[(A, B, Z, -), (| X, C, -, -), (D, E, V, -)]",
				chunk.toStringInternal(chunkIter));

		chunkIter.next();
		chunkIter.remove();
		assertEquals("[(A, B, Z, -), (| C, D, -, -), (E, V, -, -)]",
				chunk.toStringInternal(chunkIter));

		chunkIter.next();
		chunkIter.next();
		chunkIter.next();
		chunkIter.remove();
		assertEquals("[(A, B, Z, -), (C, D, -, -), (| V, -, -, -)]",
				chunk.toStringInternal(chunkIter));

		chunkIter.previous();
		chunkIter.previous();
		chunkIter.remove();
		assertEquals("[(A, B, Z, -), (| D, V, -, -)]",
				chunk.toStringInternal(chunkIter));

	}

}