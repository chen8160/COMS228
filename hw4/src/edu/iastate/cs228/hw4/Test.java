package edu.iastate.cs228.hw4;

import java.util.ArrayList;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
		ArrayList<Integer> data = new ArrayList<Integer>(5);
		for (int i = 0; i < 5; i++) {
			int value = new Integer(5-i);
			data.add(value);
			tree.add(value);
		}

		while (data.size() > 0) {
			int toRemove = data.remove((int) (Math.random() * data.size()))
					.intValue();

			tree.contains(toRemove);
			tree.remove(toRemove);
			tree.contains(toRemove);
		}
	}

}
