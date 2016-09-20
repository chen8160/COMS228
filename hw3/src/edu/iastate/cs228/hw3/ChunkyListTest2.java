package edu.iastate.cs228.hw3;

import org.junit.Test;

import java.util.ListIterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class ChunkyListTest2 {
	private ChunkyList<String> createList(String in) {
		ChunkyList<String> list = new ChunkyList<String>();
		String[] items = in.split("(?!^)");
		for (String item : items) {
			list.add(item);
		}
		return list;
	}

	@Test
	public void testFull() {
		ChunkyList<String> list = createList("ABCDEFGH");
		list.add(4, "Z");
		assertEquals("[(A, B, C, D), (Z, E, F, -), (G, H, -, -)]",
				list.toStringInternal());
	}

	@Test
	public void testRemove1() {
		ChunkyList<String> list = createList("ABCDE");
		list.remove(3);
		assertEquals("[(A, B, C, -), (E, -, -, -)]", list.toStringInternal());
	}

	@Test
	public void testFillPrev() {
		ChunkyList<String> list = createList("ABCDE");
		list.remove(3);
		// Is "[(A, B, C, -), (E, -, -, -)]"
		list.add(3, "Z");
		assertEquals("[(A, B, C, Z), (E, -, -, -)]", list.toStringInternal());
	}

	@Test
	public void addToEmpty() {
		ChunkyList<String> list = createList("A");
		assertEquals("[(A, -, -, -)]", list.toStringInternal());
	}

	@Test
	public void iterIdx() {
		ChunkyList<String> list = createList("ABCDE");
		ListIterator<String> iter = list.listIterator(2);
		assertEquals("C", iter.next());
	}

	@Test
	public void addCursorLoc() {
		ChunkyList<String> list = createList("ABCDE");
		ListIterator<String> iter = list.listIterator();
		iter.next();
		iter.next();
		iter.add("Z");
		assertEquals("C", iter.next());
	}

	@Test
	public void addCheck() {
		ChunkyList<String> list = createList("ABCDE");
		ListIterator<String> iter = list.listIterator();
		iter.next();
		iter.next();
		iter.add("Z");
		assertEquals("Z", iter.previous());
	}

	@Test
	public void addCursorLoc2() {
		ChunkyList<String> list = createList("ABCDE");
		ListIterator<String> iter = list.listIterator();
		iter.next();
		iter.next();
		iter.next();
		iter.next();
		iter.add("Z");
		assertEquals("E", iter.next());
	}

	@Test
	public void removeCursorLoc() {
		ChunkyList<String> list = createList("ABCDE");
		ListIterator<String> iter = list.listIterator();
		iter.next();
		iter.next();
		iter.next();
		iter.next();
		iter.remove();
		assertEquals("[(A, B, C, -), (E, -, -, -)]", list.toStringInternal());
	}

	private ChunkyList<String> fragmentedList() {
		ChunkyList<String> list = createList("ABZ0YX00CDEVW");
		list.remove(3);
		list.remove(5);
		list.remove(5);
		return list;
	}

	@Test
	public void removeMultiple() {
		ChunkyList<String> list = fragmentedList();
		assertEquals(
				"[(A, B, Z, -), (Y, X, -, -), (C, D, E, V), (W, -, -, -)]",
				list.toStringInternal());
	}

	@Test
	public void removeFragmented1() {
		ChunkyList<String> list = fragmentedList();
		list.remove(9);
		assertEquals("[(A, B, Z, -), (Y, X, -, -), (C, D, E, V)]",
				list.toStringInternal());
	}

	@Test
	public void removeFragmented2() {
		ChunkyList<String> list = fragmentedList();
		list.remove(9);
		list.remove(3);
		assertEquals("[(A, B, Z, -), (X, C, -, -), (D, E, V, -)]",
				list.toStringInternal());
	}

	@Test
	public void removeFragmented3() {
		ChunkyList<String> list = fragmentedList();
		list.remove(9);
		list.remove(3);
		list.remove(3);
		assertEquals("[(A, B, Z, -), (C, D, -, -), (E, V, -, -)]",
				list.toStringInternal());
	}

	@Test
	public void removeFragmented4() {
		ChunkyList<String> list = fragmentedList();
		list.remove(9);
		list.remove(3);
		list.remove(3);
		assertEquals("[(A, B, Z, -), (C, D, -, -), (E, V, -, -)]",
				list.toStringInternal());
	}

	@Test
	public void removeFragmented5() {
		ChunkyList<String> list = fragmentedList();
		list.remove(9);
		list.remove(3);
		list.remove(3);
		list.remove(5);
		assertEquals("[(A, B, Z, -), (C, D, -, -), (V, -, -, -)]",
				list.toStringInternal());
	}

	@Test
	public void removeFragmented6() {
		ChunkyList<String> list = fragmentedList();
		list.remove(9);
		list.remove(3);
		list.remove(3);
		list.remove(5);

		list.remove(3);
		assertEquals("[(A, B, Z, -), (D, V, -, -)]", list.toStringInternal());
	}

	private ChunkyList<String> exampleAdd() {
		ChunkyList<String> list = createList("AB00CDE");
		list.remove(2);
		list.remove(2);
		return list;
	}

	@Test
	public void exampleAddVerify() {
		ChunkyList<String> list = exampleAdd();
		assertEquals("[(A, B, -, -), (C, D, E, -)]", list.toStringInternal());
	}

	@Test
	public void exampleAdd1() {
		ChunkyList<String> list = exampleAdd();
		list.add("V");
		assertEquals("[(A, B, -, -), (C, D, E, V)]", list.toStringInternal());
	}

	@Test
	public void exampleAdd2() {
		ChunkyList<String> list = exampleAdd();
		list.add("V");
		list.add("W");
		assertEquals("[(A, B, -, -), (C, D, E, V), (W, -, -, -)]",
				list.toStringInternal());
	}

	@Test
	public void exampleAdd3() {
		ChunkyList<String> list = exampleAdd();
		list.add("V");
		list.add("W");
		list.add(2, "X");
		assertEquals("[(A, B, X, -), (C, D, E, V), (W, -, -, -)]",
				list.toStringInternal());
	}

	@Test
	public void exampleAdd4() {
		ChunkyList<String> list = exampleAdd();
		list.add("V");
		list.add("W");
		list.add(2, "X");
		list.add(2, "Y");
		assertEquals("[(A, B, Y, X), (C, D, E, V), (W, -, -, -)]",
				list.toStringInternal());
	}

	@Test
	public void exampleAdd5() {
		ChunkyList<String> list = exampleAdd();
		list.add("V");
		list.add("W");
		list.add(2, "X");
		list.add(2, "Y");
		list.add(2, "Z");
		assertEquals(
				"[(A, B, Z, -), (Y, X, -, -), (C, D, E, V), (W, -, -, -)]",
				list.toStringInternal());
	}

	@Test
	public void listIter1() {
		ChunkyList<String> list = createList("ABCDEFGH");
		ListIterator<String> iter = list.listIterator();
		try {
			iter.previous();
		} catch (NoSuchElementException e) {
			assertTrue(true);
			return;
		}
		assertTrue(false);
	}

	@Test
	public void listIter2() {
		ChunkyList<String> list = createList("ABCDEFGH");
		ListIterator<String> iter = list.listIterator();
		for (int i = 0; i < 8; i++) {
			iter.next();
		}
		assertFalse(iter.hasNext());
	}

	@Test
	public void listIter3() {
		ChunkyList<String> list = createList("ABCDEFGH");
		ListIterator<String> iter = list.listIterator();
		for (int i = 0; i < 4; i++) {
			iter.next();
		}
		iter.set("Z");
		assertEquals("[(A, B, C, Z), (E, F, G, H)]", list.toStringInternal());
	}

	@Test
	public void listIter4() {
		ChunkyList<String> list = createList("ABCDEFGH");
		ListIterator<String> iter = list.listIterator();
		for (int i = 0; i < 4; i++) {
			iter.next();
		}
		iter.add("Z");
		assertEquals("[(A, B, C, D), (Z, E, F, -), (G, H, -, -)]",
				list.toStringInternal());
	}

	@Test
	public void iterRemove() {
		ChunkyList<String> list = createList("ABCDEFGH");
		ListIterator<String> iter = list.listIterator();
		for (int i = 0; i < 4; i++) {
			iter.next();
		}
		iter.remove();
		iter.next();
		iter.remove();
		assertEquals("C", iter.previous());
	}

	@Test
	public void iterRemove2() {
		ChunkyList<String> list = createList("ABCD");
		ListIterator<String> iter = list.listIterator();
		iter.next();
		iter.remove();
		assertEquals("B", iter.next());
	}

	@Test
	public void iterRemove3() {
		ChunkyList<String> list = createList("ABCD");
		ListIterator<String> iter = list.listIterator();
		for (int i = 0; i < 4; i++) {
			iter.next();
		}
		iter.remove();
		assertEquals("C", iter.previous());
	}

	@Test
	public void iterRemove4() {
		ChunkyList<String> list = createList("AB");
		ListIterator<String> iter = list.listIterator();
		try {
			iter.remove();
			assertTrue(false);
		} catch (IllegalStateException e) {
			assertTrue(true);
		}
	}

	@Test
	public void remove1() {
		ChunkyList<String> list = createList("AB00CD");
		list.remove(2);
		list.remove(2);
		list.remove(1);
		assertEquals("[(A, C, D, -)]", list.toStringInternal());
	}

	@Test
	public void differentSize() {
		ChunkyList<String> list = new ChunkyList<String>(6);
		String s = "ABCDEFGH";
		for (int i = 0; i < 8; i++) {
			list.add(s.substring(i, i + 1));
		}
		list.remove(2);
		assertEquals("[(A, B, D, E, F, -), (G, H, -, -, -, -)]",
				list.toStringInternal());
	}

	@Test
	public void sort() {
		ChunkyList<String> list = createList("ljbkftdnr");
		list.sort();
		assertEquals("[(b, d, f, j), (k, l, n, r), (t, -, -, -)]",
				list.toStringInternal());
	}

	@Test
	public void reverseSort() {
		ChunkyList<String> list = createList("ljbkftdnr");
		list.sortReverse();
		assertEquals("[(t, r, n, l), (k, j, f, d), (b, -, -, -)]",
				list.toStringInternal());
	}
}