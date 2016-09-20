package edu.iastate.cs228.hw3;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.ListIterator;

import org.junit.Test;

/**
 * 
 * @author Ryan Krause
 *
 *         Using the example figures given from the PDF, this test class does a
 *         series of add and remove calls to match the figures shown. This class
 *         will compare what you return with what should actually be returned.
 */
public class PdfExample {

	public static void main(String[] args) {
		ChunkyList<String> chunk = new ChunkyList<String>();
		chunk.add("A");
		chunk.add("B");
		chunk.add("X");
		chunk.add("X");
		chunk.add("C");
		chunk.add("D");
		chunk.add("E");
		ListIterator<String> iter = chunk.listIterator();
		chunk.remove(2); // these are so we can have spaces in nodes.
		chunk.remove(2); // don't really know another way to do that.
		System.out.println("Your starting list:   " + chunk.toStringInternal(iter));
		System.out
				.println("Actual starting list: [(| A, B, -, -), (C, D, E, -)]");

		chunk.add("V");
		// System.out.println("Add V");
		System.out.println("\nYour list after adding V:   "
				+ chunk.toStringInternal(iter));
		System.out
				.println("Actual list after adding V: [(| A, B, -, -), (C, D, E, V)]");

		chunk.add("W");
		// System.out.println("Add W");
		System.out.println("\nYour list after adding W:   "
				+ chunk.toStringInternal(iter));
		System.out
				.println("Actual list after adding W: [(| A, B, -, -), (C, D, E, V), (W, -, -, -)]");

		chunk.add(2, "X");
		// System.out.println("Add X at Pos 2");
		System.out.println("\nYour list after adding X to pos 2:   "
				+ chunk.toStringInternal(iter));
		System.out
				.println("Actual list after adding X to pos 2: [(| A, B, X, -), (C, D, E, V), (W, -, -, -)]");

		chunk.add(2, "Y");
		// System.out.println("Add Y at Pos 2");
		System.out.println("\nYour list after adding Y to pos 2:   "
				+ chunk.toStringInternal(iter));
		System.out
				.println("Actual list after adding Y to pos 2: [(| A, B, Y, X), (C, D, E, V), (W, -, -, -)]");

		chunk.add(2, "Z");
		// System.out.println("Add Z at Pos 2");
		System.out.println("\nYour list after adding Z to pos 2:   "
				+ chunk.toStringInternal(iter));
		System.out
				.println("Actual list after adding Z to pos 2: [(| A, B, Z, -), (Y, X, -, -), (C, D, E, V), (W, -, -, -)]");

		chunk.remove(9);
		// System.out.println("Removed the W at pos 9 (remove node)");
		System.out.println("\nYour list after removing Z from pos 9:   "
				+ chunk.toStringInternal(iter));
		System.out
				.println("Actual list after removing Z from pos 9: [(| A, B, Z, -), (Y, X, -, -), (C, D, E, V)]");

		chunk.remove(3);
		// System.out.println("Removed the Y at pos 3 (mini-merge)");
		System.out.println("\nYour list after removing Y from pos 3:   "
				+ chunk.toStringInternal(iter));
		System.out
				.println("Actual list after removing Y from pos 3: [(| A, B, Z, -), (X, C, -, -), (D, E, V, -)]");

		chunk.remove(3);
		// System.out.println("Removed the X at pos 3 (mini-merge)");
		System.out.println("\nYour list after removing X from pos 3:   "
				+ chunk.toStringInternal(iter));
		System.out
				.println("Actual list after removing X from pos 3: [(| A, B, Z, -), (C, D, -, -), (E, V, -, -)]");

		chunk.remove(5);
		// System.out.println("Removed the E at pos 5 (no merge, as last node)");
		System.out.println("\nYour list after removing E from pos 5:   "
				+ chunk.toStringInternal(iter));
		System.out
				.println("Actual list after removing E from pos 5: [(| A, B, Z, -), (C, D, -, -), (V, -, -, -)]");

		chunk.remove(3);
		// System.out.println("Removed the C at pos 3 (full-merge)");
		System.out.println("\nYour list after removing C from pos 3:   "
				+ chunk.toStringInternal(iter));
		System.out
				.println("Actual list after removing C from pos 3: [(| A, B, Z, -), (D, V, -, -)]");

	}

}