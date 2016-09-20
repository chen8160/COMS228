package edu.iastate.cs228.hw3;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.ListIterator;

import org.junit.Test;

public class Set1 {

	@Test
	public void addMethods(){
		ChunkyList<String> cl = new ChunkyList<String>();
		cl.add("one");
		cl.add("two");
		cl.add("three");
		cl.add("four");
		cl.add("five");
		cl.add("six");
		cl.add("seven");
		cl.add("eight");
		String msg = "Somethings wrong with your normal add method: ";
		assertEquals(msg, "[(one, two, three, four), (five, six, seven, eight)]", cl.toStringInternal());
		cl.add("nine");
		assertEquals(msg, "[(one, two, three, four), (five, six, seven, eight), (nine, -, -, -)]", cl.toStringInternal());
		cl.add(3, "3.5");
		msg = "Something's wrong with your add at index method: ";
		assertEquals(msg, "[(one, two, -, -), (three, 3.5, four, -), (five, six, seven, eight), (nine, -, -, -)]", cl.toStringInternal());
		cl.add(1, "1.5");
		assertEquals(msg, "[(one, 1.5, two, -), (three, 3.5, four, -), (five, six, seven, eight), (nine, -, -, -)]", cl.toStringInternal());
		cl.add(6, "4.5");
		assertEquals(msg, "[(one, 1.5, two, -), (three, 3.5, four, 4.5), (five, six, seven, eight), (nine, -, -, -)]", cl.toStringInternal());
		cl.add(0, "zero");
		assertEquals(msg, "[(zero, one, 1.5, two), (three, 3.5, four, 4.5), (five, six, seven, eight), (nine, -, -, -)]", cl.toStringInternal());
		cl.add(13, "TEN");
		assertEquals(msg, "[(zero, one, 1.5, two), (three, 3.5, four, 4.5), (five, six, seven, eight), (nine, TEN, -, -)]", cl.toStringInternal());
		cl.add(0, "first");
		assertEquals(msg, "[(first, zero, one, -), (1.5, two, -, -), (three, 3.5, four, 4.5), (five, six, seven, eight), (nine, TEN, -, -)]", cl.toStringInternal());
	}
	@Test
	public void addMethods2(){
		ChunkyList<String> cl = new ChunkyList<String>(6);
		String msg = "With the non-default constructor, your add methods suck: ";
		cl.add("one" + 1);
		assertEquals(msg, "[(one1, -, -, -, -, -)]", cl.toStringInternal());
		cl.add("two"  + 1);
		assertEquals(msg, "[(one1, two1, -, -, -, -)]", cl.toStringInternal());
		cl.add("three" + 1);
		assertEquals(msg, "[(one1, two1, three1, -, -, -)]", cl.toStringInternal());
		cl.add("four" + 1);
		assertEquals(msg, "[(one1, two1, three1, four1, -, -)]", cl.toStringInternal());
		cl.add("five" + 1);
		assertEquals(msg, "[(one1, two1, three1, four1, five1, -)]", cl.toStringInternal());
		cl.add("six" + 1);
		assertEquals(msg, "[(one1, two1, three1, four1, five1, six1)]", cl.toStringInternal());
		cl.add("one" + 2);
		assertEquals(msg, "[(one1, two1, three1, four1, five1, six1), (one2, -, -, -, -, -)]", cl.toStringInternal());
		cl.add("two"  + 2);
		assertEquals(msg, "[(one1, two1, three1, four1, five1, six1), (one2, two2, -, -, -, -)]", cl.toStringInternal());
		cl.add("three" + 2);
		assertEquals(msg, "[(one1, two1, three1, four1, five1, six1), (one2, two2, three2, -, -, -)]", cl.toStringInternal());
		cl.add("four" + 2);
		assertEquals(msg, "[(one1, two1, three1, four1, five1, six1), (one2, two2, three2, four2, -, -)]", cl.toStringInternal());
		cl.add("five" + 2);
		assertEquals(msg, "[(one1, two1, three1, four1, five1, six1), (one2, two2, three2, four2, five2, -)]", cl.toStringInternal());
		cl.add("six" + 2);
		assertEquals(msg, "[(one1, two1, three1, four1, five1, six1), (one2, two2, three2, four2, five2, six2)]", cl.toStringInternal());
		cl.add(5, "five1.5");
		assertEquals(msg, "[(one1, two1, three1, -, -, -), (four1, five1, five1.5, six1, -, -), (one2, two2, three2, four2, five2, six2)]", cl.toStringInternal());
		cl.add(8, "one2.5");
		assertEquals(msg, "[(one1, two1, three1, -, -, -), (four1, five1, five1.5, six1, -, -), (one2, one2.5, two2, three2, -, -), (four2, five2, six2, -, -, -)]", cl.toStringInternal());
		cl.add(3, "four0.5");
		assertEquals(msg, "[(one1, two1, three1, four0.5, -, -), (four1, five1, five1.5, six1, -, -), (one2, one2.5, two2, three2, -, -), (four2, five2, six2, -, -, -)]", cl.toStringInternal());
		cl.add(3, "red");
		assertEquals(msg, "[(one1, two1, three1, red, four0.5, -), (four1, five1, five1.5, six1, -, -), (one2, one2.5, two2, three2, -, -), (four2, five2, six2, -, -, -)]", cl.toStringInternal());
		cl.add(3, "green");
		assertEquals(msg, "[(one1, two1, three1, green, red, four0.5), (four1, five1, five1.5, six1, -, -), (one2, one2.5, two2, three2, -, -), (four2, five2, six2, -, -, -)]", cl.toStringInternal());
		cl.add(3, "blue");
		assertEquals(msg, "[(one1, two1, three1, blue, -, -), (green, red, four0.5, -, -, -), (four1, five1, five1.5, six1, -, -), (one2, one2.5, two2, three2, -, -), (four2, five2, six2, -, -, -)]", cl.toStringInternal());
		cl.add(3, "yellow");
		assertEquals(msg, "[(one1, two1, three1, yellow, blue, -), (green, red, four0.5, -, -, -), (four1, five1, five1.5, six1, -, -), (one2, one2.5, two2, three2, -, -), (four2, five2, six2, -, -, -)]", cl.toStringInternal());
		cl.add("end");
		assertEquals(msg, "[(one1, two1, three1, yellow, blue, -), (green, red, four0.5, -, -, -), (four1, five1, five1.5, six1, -, -), (one2, one2.5, two2, three2, -, -), (four2, five2, six2, end, -, -)]", cl.toStringInternal());
		cl.add(0, "begin");
		assertEquals(msg, "[(begin, one1, two1, three1, yellow, blue), (green, red, four0.5, -, -, -), (four1, five1, five1.5, six1, -, -), (one2, one2.5, two2, three2, -, -), (four2, five2, six2, end, -, -)]", cl.toStringInternal());
		cl.add(0, "veryest beginning");
		assertEquals(msg, "[(veryest beginning, begin, one1, two1, -, -), (three1, yellow, blue, -, -, -), (green, red, four0.5, -, -, -), (four1, five1, five1.5, six1, -, -), (one2, one2.5, two2, three2, -, -), (four2, five2, six2, end, -, -)]", cl.toStringInternal());
	}
	
	@Test
	public void ChunkyIterator(){
		ChunkyList<String> cl = new ChunkyList<String>();
		cl.add("one");
		cl.add("two");
		cl.add("three");
		cl.add("four");
		cl.add("five");
		cl.add("six");
		cl.add("seven");
		cl.add("eight");
		ListIterator<String> iter = cl.listIterator();
		String msg1 = "Your iterator failed: ";
		String msg2 = "Your iterator needs to have next: ";
		cl.toStringInternal(iter);
		assertTrue(msg2, iter.hasNext());
		assertEquals(msg1, "one", iter.next());
		cl.toStringInternal(iter);
		assertTrue(msg2, iter.hasNext());
		assertEquals(msg1, "two", iter.next());
		cl.toStringInternal(iter);
		assertTrue(msg2, iter.hasNext());
		assertEquals(msg1, "three", iter.next());
		cl.toStringInternal(iter);
		assertTrue(msg2, iter.hasNext());
		assertEquals(msg1, "four", iter.next());
		cl.toStringInternal(iter);
		assertTrue(msg2, iter.hasNext());
		assertEquals(msg1, "five", iter.next());
		cl.toStringInternal(iter);
		assertTrue(msg2, iter.hasNext());
		assertEquals(msg1, "six", iter.next());
		cl.toStringInternal(iter);
		assertTrue(msg2, iter.hasNext());
		assertEquals(msg1, "seven", iter.next());
		cl.toStringInternal(iter);
		assertTrue(msg2, iter.hasNext());
		assertEquals(msg1, "eight", iter.next());
		cl.toStringInternal(iter);
		msg2 = "Your iterator should not have next: ";
		assertFalse(msg2, iter.hasNext());
	}

}
