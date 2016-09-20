package edu.iastate.cs228.hw4;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class NodeTest {
	private Node<Integer> root;

	private static final boolean TEST_NULL = true;

	@Before
	public void setupRoot() {
		root = new Node<Integer>(new Integer(50));
	}

	@Test
	public void testGetSuccessor() throws Exception {
		if (TEST_NULL)
			assertEquals(
					"root.getPredecessor() didn't return null when it had no successor",
					null, root.getSuccessor());

		Node<Integer> node = new Node<Integer>(new Integer(75));

		root.setRight(node);
		node.setParent(root);
		assertEquals("root.getSuccessor() didn't return 75", 75, root
				.getSuccessor().getData().intValue());

		node.setRight(new Node<Integer>(new Integer(80)));
		node.getRight().setParent(node);
		assertEquals("root.getSuccessor() followed the tree too far right", 75,
				root.getSuccessor().getData().intValue());

		node.setLeft(new Node<Integer>(new Integer(70)));
		node.getLeft().setParent(node);
		assertEquals(
				"root.getSuccessor() didn't follow the tree far enough to the left",
				70, root.getSuccessor().getData().intValue());

		node = node.getLeft();

		node.setRight(new Node<Integer>(new Integer(73)));
		node.getRight().setParent(node);
		assertEquals("root.getSuccessor() zigzagged along the tree", 70, root
				.getSuccessor().getData().intValue());

		node.setLeft(new Node<Integer>(new Integer(60)));
		node.getLeft().setParent(node);
		assertEquals(
				"root.getSuccessor() didn't follow the tree far enough to the left",
				60, root.getSuccessor().getData().intValue());

		root.setLeft(new Node<Integer>(new Integer(40)));
		root.getLeft().setParent(root);
		assertEquals("root.getSuccessor() went left of the root node", 60, root
				.getSuccessor().getData().intValue());

		root.setLeft(null);
		root.setRight(null);

		node = new Node<Integer>(new Integer(25));

		node.setRight(root);
		root.setParent(node);
		assertEquals(
				"root.getSuccessor() returned its parent and not null when there was no successor",
				null, root.getSuccessor());

		node.setParent(new Node<Integer>(new Integer(75)));
		node.getParent().setLeft(node);
		assertEquals(
				"root.getSuccessor() didn't traverse far enough through its parent nodes",
				75, root.getSuccessor().getData().intValue());

		node = node.getParent();
		node.setRight(new Node<Integer>(new Integer(80)));
		node.getRight().setParent(node);
		assertEquals("root.getSuccessor() followed too far to the right", 75,
				root.getSuccessor().getData().intValue());

		node.setParent(new Node<Integer>(80));
		node.getParent().setRight(node);
		assertEquals("root.getSuccessor() followed too many parents", 75, root
				.getSuccessor().getData().intValue());
	}

	@Test
	public void testGetPredecessor() throws Exception {
		if (TEST_NULL)
			assertEquals(
					"root.getPredecessor() didn't return null when it had no predecessor",
					null, root.getPredecessor());

		Node<Integer> node = new Node<Integer>(new Integer(25));

		root.setLeft(node);
		node.setParent(root);
		assertEquals("root.getPredecessor() didn't return 25", 25, root
				.getPredecessor().getData().intValue());

		node.setLeft(new Node<Integer>(new Integer(20)));
		node.getLeft().setParent(node);
		assertEquals("root.getPredecessor() followed the tree too far left",
				25, root.getPredecessor().getData().intValue());

		node.setRight(new Node<Integer>(new Integer(30)));
		node.getRight().setParent(node);
		assertEquals(
				"root.getPredecessor() didn't follow the tree far enough to the right",
				30, root.getPredecessor().getData().intValue());

		node = node.getRight();

		node.setLeft(new Node<Integer>(new Integer(27)));
		node.getLeft().setParent(node);
		assertEquals("root.getPredecessor() zigzagged along the tree", 30, root
				.getPredecessor().getData().intValue());

		node.setRight(new Node<Integer>(new Integer(40)));
		node.getRight().setParent(node);
		assertEquals(
				"root.getPredecessor() didn't follow the tree far enough to the right",
				40, root.getPredecessor().getData().intValue());

		root.setRight(new Node<Integer>(new Integer(60)));
		root.getRight().setParent(root);
		assertEquals("root.getPredecessor() went right of the root node", 40,
				root.getPredecessor().getData().intValue());

		root.setLeft(null);
		root.setRight(null);

		node = new Node<Integer>(new Integer(75));

		node.setLeft(root);
		root.setParent(node);
		assertEquals(
				"root.getPredecessor() returned its parent and not null when there was no predecessor",
				null, root.getPredecessor());

		node.setParent(new Node<Integer>(new Integer(25)));
		node.getParent().setRight(node);
		assertEquals(
				"root.getPredecessor() didn't traverse far enough through its parent nodes",
				25, root.getPredecessor().getData().intValue());

		node = node.getParent();
		node.setLeft(new Node<Integer>(new Integer(20)));
		node.getLeft().setParent(node);
		assertEquals("root.getPredecessor() followed too far to the left", 25,
				root.getPredecessor().getData().intValue());

		node.setParent(new Node<Integer>(80));
		node.getParent().setLeft(node);
		assertEquals("root.getPredecessor() followed too many parents", 25,
				root.getPredecessor().getData().intValue());
	}

	private void testFail(String method) {
		fail(String.format("Required methods for %s() not tested", method));
	}
}