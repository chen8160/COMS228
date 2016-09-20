package edu.iastate.cs228.hw5;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

import edu.iastate.cs228.hw5.MotionPlanning.IntegerPair;

public class MotionPlanningTest {

	@Test
	public void testReadFloorPlan() throws FileNotFoundException {

		Graph<Integer> g = MotionPlanning
				.readFloorPlan("SpecFloorPlan.txt");
		assertTrue("G should not be null.", g != null);
		assertEquals("There should be five vertexes.", 5, g.numV());
		assertEquals("There should be five edges.", 5, g.numE());
		assertTrue("Should have vertex 1.", g.hasVertex(new Integer(1)));
		assertTrue("Should have vertex 2.", g.hasVertex(new Integer(2)));
		assertTrue("Should have vertex 3.", g.hasVertex(new Integer(3)));
		assertTrue("Should have vertex 4.", g.hasVertex(new Integer(4)));
		assertTrue("Should have vertex 5.", g.hasVertex(new Integer(5)));
		assertTrue("Should have edge 1 2.",
				g.hasEdge(new Integer(1), new Integer(2)));
		assertTrue("Should have edge 1 2.",
				g.hasEdge(new Integer(3), new Integer(2)));
		assertTrue("Should have edge 1 2.",
				g.hasEdge(new Integer(4), new Integer(3)));
		assertTrue("Should have edge 1 2.",
				g.hasEdge(new Integer(5), new Integer(4)));
		assertTrue("Should have edge 1 2.",
				g.hasEdge(new Integer(1), new Integer(5)));

	}

	@Test
	public void testSquare() throws FileNotFoundException {
		Graph<Integer> g = MotionPlanning
				.readFloorPlan("SpecFloorPlan.txt");
		Graph<IntegerPair> square = MotionPlanning.square(g);

		assertEquals("Should have 25 vertexes.", 25, square.numV());
		assertEquals("Should have 50 edges.", 50, square.numE());

		// Test the existance of each Vertex.
		assertTrue("Should have vertex 1, 1.",
				square.hasVertex(new IntegerPair(1, 1)));
		assertTrue("Should have vertex 1, 2.",
				square.hasVertex(new IntegerPair(1, 2)));
		assertTrue("Should have vertex 1, 3.",
				square.hasVertex(new IntegerPair(1, 3)));
		assertTrue("Should have vertex 1, 4.",
				square.hasVertex(new IntegerPair(1, 4)));
		assertTrue("Should have vertex 1, 5.",
				square.hasVertex(new IntegerPair(1, 5)));
		assertTrue("Should have vertex 2, 1.",
				square.hasVertex(new IntegerPair(2, 1)));
		assertTrue("Should have vertex 2, 2.",
				square.hasVertex(new IntegerPair(2, 2)));
		assertTrue("Should have vertex 2, 3.",
				square.hasVertex(new IntegerPair(2, 3)));
		assertTrue("Should have vertex 2, 4.",
				square.hasVertex(new IntegerPair(2, 4)));
		assertTrue("Should have vertex 2, 5.",
				square.hasVertex(new IntegerPair(2, 5)));
		assertTrue("Should have vertex 3, 1.",
				square.hasVertex(new IntegerPair(3, 1)));
		assertTrue("Should have vertex 3, 2.",
				square.hasVertex(new IntegerPair(3, 2)));
		assertTrue("Should have vertex 3, 3.",
				square.hasVertex(new IntegerPair(3, 3)));
		assertTrue("Should have vertex 3, 4.",
				square.hasVertex(new IntegerPair(3, 4)));
		assertTrue("Should have vertex 3, 5.",
				square.hasVertex(new IntegerPair(3, 5)));
		assertTrue("Should have vertex 4, 1.",
				square.hasVertex(new IntegerPair(4, 1)));
		assertTrue("Should have vertex 4, 2.",
				square.hasVertex(new IntegerPair(4, 2)));
		assertTrue("Should have vertex 4, 3.",
				square.hasVertex(new IntegerPair(4, 3)));
		assertTrue("Should have vertex 4, 4.",
				square.hasVertex(new IntegerPair(4, 4)));
		assertTrue("Should have vertex 4, 5.",
				square.hasVertex(new IntegerPair(4, 5)));
		assertTrue("Should have vertex 5, 1.",
				square.hasVertex(new IntegerPair(5, 1)));
		assertTrue("Should have vertex 5, 2.",
				square.hasVertex(new IntegerPair(5, 2)));
		assertTrue("Should have vertex 5, 3.",
				square.hasVertex(new IntegerPair(5, 3)));
		assertTrue("Should have vertex 5, 4.",
				square.hasVertex(new IntegerPair(5, 4)));
		assertTrue("Should have vertex 5, 5.",
				square.hasVertex(new IntegerPair(5, 5)));

		assertTrue("Should have edge (1,1) (2,1).",
				square.hasEdge(new IntegerPair(1, 1), new IntegerPair(2, 1)));
		assertTrue("Should have edge (1,1) (1,2).",
				square.hasEdge(new IntegerPair(1, 1), new IntegerPair(1, 2)));
		assertTrue("Should have edge (1,1) (5,1).",
				square.hasEdge(new IntegerPair(1, 1), new IntegerPair(5, 1)));
		assertTrue("Should have edge (1,1) (1,5).",
				square.hasEdge(new IntegerPair(1, 1), new IntegerPair(1, 5)));

		assertTrue("Should have edge (1,2) (1,3).",
				square.hasEdge(new IntegerPair(1, 2), new IntegerPair(1, 3)));
		assertTrue("Should have edge (1,2) (2,2).",
				square.hasEdge(new IntegerPair(1, 2), new IntegerPair(2, 2)));
		assertTrue("Should have edge (1,2) (5,2).",
				square.hasEdge(new IntegerPair(1, 2), new IntegerPair(5, 2)));

		assertTrue("Should have edge (1,3) (1,4).",
				square.hasEdge(new IntegerPair(1, 3), new IntegerPair(1, 4)));
		assertTrue("Should have edge (1,3) (2,3).",
				square.hasEdge(new IntegerPair(1, 3), new IntegerPair(2, 3)));
		assertTrue("Should have edge (1,3) (5,3).",
				square.hasEdge(new IntegerPair(1, 3), new IntegerPair(5, 3)));

		assertTrue("Should have edge (1,4) (2,4).",
				square.hasEdge(new IntegerPair(1, 4), new IntegerPair(2, 4)));
		assertTrue("Should have edge (1,4) (5,4).",
				square.hasEdge(new IntegerPair(1, 4), new IntegerPair(5, 4)));
		assertTrue("Should have edge (1,4) (1,5).",
				square.hasEdge(new IntegerPair(1, 4), new IntegerPair(1, 5)));

		assertTrue("Should have edge (1,5) (2,5).",
				square.hasEdge(new IntegerPair(1, 5), new IntegerPair(2, 5)));
		assertTrue("Should have edge (1,5) (5,5).",
				square.hasEdge(new IntegerPair(1, 5), new IntegerPair(5, 5)));

		assertTrue("Should have edge (2,1) (3,1).",
				square.hasEdge(new IntegerPair(2, 1), new IntegerPair(3, 1)));
		assertTrue("Should have edge (2,1) (2,5).",
				square.hasEdge(new IntegerPair(2, 1), new IntegerPair(2, 5)));
		assertTrue("Should have edge (2,1) (2,2).",
				square.hasEdge(new IntegerPair(2, 1), new IntegerPair(2, 2)));
		assertTrue("Should have edge (2,2) (3,2).",
				square.hasEdge(new IntegerPair(2, 2), new IntegerPair(3, 2)));
		assertTrue("Should have edge (2,2) (2,3).",
				square.hasEdge(new IntegerPair(2, 2), new IntegerPair(2, 3)));
		assertTrue("Should have edge (2,3) (3,3).",
				square.hasEdge(new IntegerPair(2, 3), new IntegerPair(3, 3)));
		assertTrue("Should have edge (2,3) (2,4).",
				square.hasEdge(new IntegerPair(2, 3), new IntegerPair(2, 4)));
		assertTrue("Should have edge (2,4) (3,4).",
				square.hasEdge(new IntegerPair(2, 4), new IntegerPair(3, 4)));
		assertTrue("Should have edge (2,4) (2,5).",
				square.hasEdge(new IntegerPair(2, 4), new IntegerPair(2, 5)));
		assertTrue("Should have edge (2,5) (3,5).",
				square.hasEdge(new IntegerPair(2, 5), new IntegerPair(3, 5)));

		assertTrue("Should have edge (3,1) (4,1).",
				square.hasEdge(new IntegerPair(3, 1), new IntegerPair(4, 1)));
		assertTrue("Should have edge (3,1) (3,5).",
				square.hasEdge(new IntegerPair(3, 1), new IntegerPair(3, 5)));
		assertTrue("Should have edge (3,1) (3,2).",
				square.hasEdge(new IntegerPair(3, 1), new IntegerPair(3, 2)));
		assertTrue("Should have edge (3,2) (4,2).",
				square.hasEdge(new IntegerPair(3, 2), new IntegerPair(4, 2)));
		assertTrue("Should have edge (3,2) (3,3).",
				square.hasEdge(new IntegerPair(3, 2), new IntegerPair(3, 3)));
		assertTrue("Should have edge (3,3) (4,3).",
				square.hasEdge(new IntegerPair(3, 3), new IntegerPair(4, 3)));
		assertTrue("Should have edge (3,3) (3,4).",
				square.hasEdge(new IntegerPair(3, 3), new IntegerPair(3, 4)));
		assertTrue("Should have edge (3,4) (4,4).",
				square.hasEdge(new IntegerPair(3, 4), new IntegerPair(4, 4)));
		assertTrue("Should have edge (3,4) (3,5).",
				square.hasEdge(new IntegerPair(3, 4), new IntegerPair(3, 5)));
		assertTrue("Should have edge (3,5) (4,5).",
				square.hasEdge(new IntegerPair(3, 5), new IntegerPair(4, 5)));

		assertTrue("Should have edge (4,1) (5,1).",
				square.hasEdge(new IntegerPair(4, 1), new IntegerPair(5, 1)));
		assertTrue("Should have edge (4,1) (4,5).",
				square.hasEdge(new IntegerPair(4, 1), new IntegerPair(4, 5)));
		assertTrue("Should have edge (4,1) (4,2).",
				square.hasEdge(new IntegerPair(4, 1), new IntegerPair(4, 2)));
		assertTrue("Should have edge (4,2) (5,2).",
				square.hasEdge(new IntegerPair(4, 2), new IntegerPair(5, 2)));
		assertTrue("Should have edge (4,2) (4,3).",
				square.hasEdge(new IntegerPair(4, 2), new IntegerPair(4, 3)));
		assertTrue("Should have edge (4,3) (5,3).",
				square.hasEdge(new IntegerPair(4, 3), new IntegerPair(5, 3)));
		assertTrue("Should have edge (4,3) (4,4).",
				square.hasEdge(new IntegerPair(4, 3), new IntegerPair(4, 4)));
		assertTrue("Should have edge (4,4) (5,4).",
				square.hasEdge(new IntegerPair(4, 4), new IntegerPair(5, 4)));
		assertTrue("Should have edge (4,4) (4,5).",
				square.hasEdge(new IntegerPair(4, 4), new IntegerPair(4, 5)));
		assertTrue("Should have edge (4,5) (5,5).",
				square.hasEdge(new IntegerPair(4, 5), new IntegerPair(5, 5)));

		assertTrue("Should have edge (5,1) (5,5).",
				square.hasEdge(new IntegerPair(5, 1), new IntegerPair(5, 5)));
		assertTrue("Should have edge (5,1) (5,2).",
				square.hasEdge(new IntegerPair(5, 1), new IntegerPair(5, 2)));
		assertTrue("Should have edge (5,2) (5,3).",
				square.hasEdge(new IntegerPair(5, 2), new IntegerPair(5, 3)));
		assertTrue("Should have edge (5,3) (5,4).",
				square.hasEdge(new IntegerPair(5, 3), new IntegerPair(5, 4)));
		assertTrue("Should have edge (5,4) (5,5).",
				square.hasEdge(new IntegerPair(5, 4), new IntegerPair(5, 5)));

	}

	@Test
	public void testConfigSpace() throws FileNotFoundException {
		Graph<Integer> g = MotionPlanning
				.readFloorPlan("SpecFloorPlan.txt");
		Graph<IntegerPair> square = MotionPlanning.square(g);

		try {
			MotionPlanning.configSpace(g, square, -1);
			fail("ConfigSpace should have thrown an illegal argument exception based on r being negative.");
		} catch (IllegalArgumentException e) {
		}

		MotionPlanning.configSpace(g, square, 0);

		assertEquals("Should have 25 vertexes.", 25, square.numV());
		assertEquals("Should have 50 edges.", 50, square.numE());

		// Test the existance of each Vertex.
		assertTrue("Should have vertex 1, 1.",
				square.hasVertex(new IntegerPair(1, 1)));
		assertTrue("Should have vertex 1, 2.",
				square.hasVertex(new IntegerPair(1, 2)));
		assertTrue("Should have vertex 1, 3.",
				square.hasVertex(new IntegerPair(1, 3)));
		assertTrue("Should have vertex 1, 4.",
				square.hasVertex(new IntegerPair(1, 4)));
		assertTrue("Should have vertex 1, 5.",
				square.hasVertex(new IntegerPair(1, 5)));
		assertTrue("Should have vertex 2, 1.",
				square.hasVertex(new IntegerPair(2, 1)));
		assertTrue("Should have vertex 2, 2.",
				square.hasVertex(new IntegerPair(2, 2)));
		assertTrue("Should have vertex 2, 3.",
				square.hasVertex(new IntegerPair(2, 3)));
		assertTrue("Should have vertex 2, 4.",
				square.hasVertex(new IntegerPair(2, 4)));
		assertTrue("Should have vertex 2, 5.",
				square.hasVertex(new IntegerPair(2, 5)));
		assertTrue("Should have vertex 3, 1.",
				square.hasVertex(new IntegerPair(3, 1)));
		assertTrue("Should have vertex 3, 2.",
				square.hasVertex(new IntegerPair(3, 2)));
		assertTrue("Should have vertex 3, 3.",
				square.hasVertex(new IntegerPair(3, 3)));
		assertTrue("Should have vertex 3, 4.",
				square.hasVertex(new IntegerPair(3, 4)));
		assertTrue("Should have vertex 3, 5.",
				square.hasVertex(new IntegerPair(3, 5)));
		assertTrue("Should have vertex 4, 1.",
				square.hasVertex(new IntegerPair(4, 1)));
		assertTrue("Should have vertex 4, 2.",
				square.hasVertex(new IntegerPair(4, 2)));
		assertTrue("Should have vertex 4, 3.",
				square.hasVertex(new IntegerPair(4, 3)));
		assertTrue("Should have vertex 4, 4.",
				square.hasVertex(new IntegerPair(4, 4)));
		assertTrue("Should have vertex 4, 5.",
				square.hasVertex(new IntegerPair(4, 5)));
		assertTrue("Should have vertex 5, 1.",
				square.hasVertex(new IntegerPair(5, 1)));
		assertTrue("Should have vertex 5, 2.",
				square.hasVertex(new IntegerPair(5, 2)));
		assertTrue("Should have vertex 5, 3.",
				square.hasVertex(new IntegerPair(5, 3)));
		assertTrue("Should have vertex 5, 4.",
				square.hasVertex(new IntegerPair(5, 4)));
		assertTrue("Should have vertex 5, 5.",
				square.hasVertex(new IntegerPair(5, 5)));

		assertTrue("Should have edge (1,1) (2,1).",
				square.hasEdge(new IntegerPair(1, 1), new IntegerPair(2, 1)));
		assertTrue("Should have edge (1,1) (1,2).",
				square.hasEdge(new IntegerPair(1, 1), new IntegerPair(1, 2)));
		assertTrue("Should have edge (1,1) (5,1).",
				square.hasEdge(new IntegerPair(1, 1), new IntegerPair(5, 1)));
		assertTrue("Should have edge (1,1) (1,5).",
				square.hasEdge(new IntegerPair(1, 1), new IntegerPair(1, 5)));
		assertTrue("Should have edge (1,2) (1,3).",
				square.hasEdge(new IntegerPair(1, 2), new IntegerPair(1, 3)));
		assertTrue("Should have edge (1,2) (2,2).",
				square.hasEdge(new IntegerPair(1, 2), new IntegerPair(2, 2)));
		assertTrue("Should have edge (1,2) (5,2).",
				square.hasEdge(new IntegerPair(1, 2), new IntegerPair(5, 2)));
		assertTrue("Should have edge (1,3) (1,4).",
				square.hasEdge(new IntegerPair(1, 3), new IntegerPair(1, 4)));
		assertTrue("Should have edge (1,3) (2,3).",
				square.hasEdge(new IntegerPair(1, 3), new IntegerPair(2, 3)));
		assertTrue("Should have edge (1,3) (5,3).",
				square.hasEdge(new IntegerPair(1, 3), new IntegerPair(5, 3)));
		assertTrue("Should have edge (1,4) (2,4).",
				square.hasEdge(new IntegerPair(1, 4), new IntegerPair(2, 4)));
		assertTrue("Should have edge (1,4) (5,4).",
				square.hasEdge(new IntegerPair(1, 4), new IntegerPair(5, 4)));
		assertTrue("Should have edge (1,4) (1,5).",
				square.hasEdge(new IntegerPair(1, 4), new IntegerPair(1, 5)));
		assertTrue("Should have edge (1,5) (2,5).",
				square.hasEdge(new IntegerPair(1, 5), new IntegerPair(2, 5)));
		assertTrue("Should have edge (1,5) (5,5).",
				square.hasEdge(new IntegerPair(1, 5), new IntegerPair(5, 5)));
		assertTrue("Should have edge (2,1) (3,1).",
				square.hasEdge(new IntegerPair(2, 1), new IntegerPair(3, 1)));
		assertTrue("Should have edge (2,1) (2,5).",
				square.hasEdge(new IntegerPair(2, 1), new IntegerPair(2, 5)));
		assertTrue("Should have edge (2,1) (2,2).",
				square.hasEdge(new IntegerPair(2, 1), new IntegerPair(2, 2)));
		assertTrue("Should have edge (2,2) (3,2).",
				square.hasEdge(new IntegerPair(2, 2), new IntegerPair(3, 2)));
		assertTrue("Should have edge (2,2) (2,3).",
				square.hasEdge(new IntegerPair(2, 2), new IntegerPair(2, 3)));
		assertTrue("Should have edge (2,3) (3,3).",
				square.hasEdge(new IntegerPair(2, 3), new IntegerPair(3, 3)));
		assertTrue("Should have edge (2,3) (2,4).",
				square.hasEdge(new IntegerPair(2, 3), new IntegerPair(2, 4)));
		assertTrue("Should have edge (2,4) (3,4).",
				square.hasEdge(new IntegerPair(2, 4), new IntegerPair(3, 4)));
		assertTrue("Should have edge (2,4) (2,5).",
				square.hasEdge(new IntegerPair(2, 4), new IntegerPair(2, 5)));
		assertTrue("Should have edge (2,5) (3,5).",
				square.hasEdge(new IntegerPair(2, 5), new IntegerPair(3, 5)));
		assertTrue("Should have edge (3,1) (4,1).",
				square.hasEdge(new IntegerPair(3, 1), new IntegerPair(4, 1)));
		assertTrue("Should have edge (3,1) (3,5).",
				square.hasEdge(new IntegerPair(3, 1), new IntegerPair(3, 5)));
		assertTrue("Should have edge (3,1) (3,2).",
				square.hasEdge(new IntegerPair(3, 1), new IntegerPair(3, 2)));
		assertTrue("Should have edge (3,2) (4,2).",
				square.hasEdge(new IntegerPair(3, 2), new IntegerPair(4, 2)));
		assertTrue("Should have edge (3,2) (3,3).",
				square.hasEdge(new IntegerPair(3, 2), new IntegerPair(3, 3)));
		assertTrue("Should have edge (3,3) (4,3).",
				square.hasEdge(new IntegerPair(3, 3), new IntegerPair(4, 3)));
		assertTrue("Should have edge (3,3) (3,4).",
				square.hasEdge(new IntegerPair(3, 3), new IntegerPair(3, 4)));
		assertTrue("Should have edge (3,4) (4,4).",
				square.hasEdge(new IntegerPair(3, 4), new IntegerPair(4, 4)));
		assertTrue("Should have edge (3,4) (3,5).",
				square.hasEdge(new IntegerPair(3, 4), new IntegerPair(3, 5)));
		assertTrue("Should have edge (3,5) (4,5).",
				square.hasEdge(new IntegerPair(3, 5), new IntegerPair(4, 5)));
		assertTrue("Should have edge (4,1) (5,1).",
				square.hasEdge(new IntegerPair(4, 1), new IntegerPair(5, 1)));
		assertTrue("Should have edge (4,1) (4,5).",
				square.hasEdge(new IntegerPair(4, 1), new IntegerPair(4, 5)));
		assertTrue("Should have edge (4,1) (4,2).",
				square.hasEdge(new IntegerPair(4, 1), new IntegerPair(4, 2)));
		assertTrue("Should have edge (4,2) (5,2).",
				square.hasEdge(new IntegerPair(4, 2), new IntegerPair(5, 2)));
		assertTrue("Should have edge (4,2) (4,3).",
				square.hasEdge(new IntegerPair(4, 2), new IntegerPair(4, 3)));
		assertTrue("Should have edge (4,3) (5,3).",
				square.hasEdge(new IntegerPair(4, 3), new IntegerPair(5, 3)));
		assertTrue("Should have edge (4,3) (4,4).",
				square.hasEdge(new IntegerPair(4, 3), new IntegerPair(4, 4)));
		assertTrue("Should have edge (4,4) (5,4).",
				square.hasEdge(new IntegerPair(4, 4), new IntegerPair(5, 4)));
		assertTrue("Should have edge (4,4) (4,5).",
				square.hasEdge(new IntegerPair(4, 4), new IntegerPair(4, 5)));
		assertTrue("Should have edge (4,5) (5,5).",
				square.hasEdge(new IntegerPair(4, 5), new IntegerPair(5, 5)));
		assertTrue("Should have edge (5,1) (5,5).",
				square.hasEdge(new IntegerPair(5, 1), new IntegerPair(5, 5)));
		assertTrue("Should have edge (5,1) (5,2).",
				square.hasEdge(new IntegerPair(5, 1), new IntegerPair(5, 2)));
		assertTrue("Should have edge (5,2) (5,3).",
				square.hasEdge(new IntegerPair(5, 2), new IntegerPair(5, 3)));
		assertTrue("Should have edge (5,3) (5,4).",
				square.hasEdge(new IntegerPair(5, 3), new IntegerPair(5, 4)));
		assertTrue("Should have edge (5,4) (5,5).",
				square.hasEdge(new IntegerPair(5, 4), new IntegerPair(5, 5)));

		g = MotionPlanning.readFloorPlan("SpecFloorPlan.txt");
		square = MotionPlanning.square(g);
		MotionPlanning.configSpace(g, square, 1);

		assertEquals("Should have 20 vertexes.", 20, square.numV());
		assertEquals("Should have 30 edges.", 30, square.numE());

		// Test the existance of each Vertex.
		assertTrue("Should have vertex 1, 2.",
				square.hasVertex(new IntegerPair(1, 2)));
		assertTrue("Should have vertex 1, 3.",
				square.hasVertex(new IntegerPair(1, 3)));
		assertTrue("Should have vertex 1, 4.",
				square.hasVertex(new IntegerPair(1, 4)));
		assertTrue("Should have vertex 1, 5.",
				square.hasVertex(new IntegerPair(1, 5)));
		assertTrue("Should have vertex 2, 1.",
				square.hasVertex(new IntegerPair(2, 1)));
		assertTrue("Should have vertex 2, 3.",
				square.hasVertex(new IntegerPair(2, 3)));
		assertTrue("Should have vertex 2, 4.",
				square.hasVertex(new IntegerPair(2, 4)));
		assertTrue("Should have vertex 2, 5.",
				square.hasVertex(new IntegerPair(2, 5)));
		assertTrue("Should have vertex 3, 1.",
				square.hasVertex(new IntegerPair(3, 1)));
		assertTrue("Should have vertex 3, 2.",
				square.hasVertex(new IntegerPair(3, 2)));
		assertTrue("Should have vertex 3, 4.",
				square.hasVertex(new IntegerPair(3, 4)));
		assertTrue("Should have vertex 3, 5.",
				square.hasVertex(new IntegerPair(3, 5)));
		assertTrue("Should have vertex 4, 1.",
				square.hasVertex(new IntegerPair(4, 1)));
		assertTrue("Should have vertex 4, 2.",
				square.hasVertex(new IntegerPair(4, 2)));
		assertTrue("Should have vertex 4, 3.",
				square.hasVertex(new IntegerPair(4, 3)));
		assertTrue("Should have vertex 4, 5.",
				square.hasVertex(new IntegerPair(4, 5)));
		assertTrue("Should have vertex 5, 1.",
				square.hasVertex(new IntegerPair(5, 1)));
		assertTrue("Should have vertex 5, 2.",
				square.hasVertex(new IntegerPair(5, 2)));
		assertTrue("Should have vertex 5, 3.",
				square.hasVertex(new IntegerPair(5, 3)));
		assertTrue("Should have vertex 5, 4.",
				square.hasVertex(new IntegerPair(5, 4)));

		assertTrue("Should have edge (1,2) (1,3).",
				square.hasEdge(new IntegerPair(1, 2), new IntegerPair(1, 3)));
		assertTrue("Should have edge (1,2) (5,2).",
				square.hasEdge(new IntegerPair(1, 2), new IntegerPair(5, 2)));
		assertTrue("Should have edge (1,3) (1,4).",
				square.hasEdge(new IntegerPair(1, 3), new IntegerPair(1, 4)));
		assertTrue("Should have edge (1,3) (2,3).",
				square.hasEdge(new IntegerPair(1, 3), new IntegerPair(2, 3)));
		assertTrue("Should have edge (1,3) (5,3).",
				square.hasEdge(new IntegerPair(1, 3), new IntegerPair(5, 3)));
		assertTrue("Should have edge (1,4) (2,4).",
				square.hasEdge(new IntegerPair(1, 4), new IntegerPair(2, 4)));
		assertTrue("Should have edge (1,4) (5,4).",
				square.hasEdge(new IntegerPair(1, 4), new IntegerPair(5, 4)));
		assertTrue("Should have edge (1,4) (1,5).",
				square.hasEdge(new IntegerPair(1, 4), new IntegerPair(1, 5)));
		assertTrue("Should have edge (1,5) (2,5).",
				square.hasEdge(new IntegerPair(1, 5), new IntegerPair(2, 5)));
		assertTrue("Should have edge (2,1) (3,1).",
				square.hasEdge(new IntegerPair(2, 1), new IntegerPair(3, 1)));
		assertTrue("Should have edge (2,1) (2,5).",
				square.hasEdge(new IntegerPair(2, 1), new IntegerPair(2, 5)));
		assertTrue("Should have edge (2,3) (2,4).",
				square.hasEdge(new IntegerPair(2, 3), new IntegerPair(2, 4)));
		assertTrue("Should have edge (2,4) (3,4).",
				square.hasEdge(new IntegerPair(2, 4), new IntegerPair(3, 4)));
		assertTrue("Should have edge (2,4) (2,5).",
				square.hasEdge(new IntegerPair(2, 4), new IntegerPair(2, 5)));
		assertTrue("Should have edge (2,5) (3,5).",
				square.hasEdge(new IntegerPair(2, 5), new IntegerPair(3, 5)));
		assertTrue("Should have edge (3,1) (4,1).",
				square.hasEdge(new IntegerPair(3, 1), new IntegerPair(4, 1)));
		assertTrue("Should have edge (3,1) (3,5).",
				square.hasEdge(new IntegerPair(3, 1), new IntegerPair(3, 5)));
		assertTrue("Should have edge (3,1) (3,2).",
				square.hasEdge(new IntegerPair(3, 1), new IntegerPair(3, 2)));
		assertTrue("Should have edge (3,2) (4,2).",
				square.hasEdge(new IntegerPair(3, 2), new IntegerPair(4, 2)));
		assertTrue("Should have edge (3,4) (3,5).",
				square.hasEdge(new IntegerPair(3, 4), new IntegerPair(3, 5)));
		assertTrue("Should have edge (3,5) (4,5).",
				square.hasEdge(new IntegerPair(3, 5), new IntegerPair(4, 5)));
		assertTrue("Should have edge (4,1) (5,1).",
				square.hasEdge(new IntegerPair(4, 1), new IntegerPair(5, 1)));
		assertTrue("Should have edge (4,1) (4,5).",
				square.hasEdge(new IntegerPair(4, 1), new IntegerPair(4, 5)));
		assertTrue("Should have edge (4,1) (4,2).",
				square.hasEdge(new IntegerPair(4, 1), new IntegerPair(4, 2)));
		assertTrue("Should have edge (4,2) (5,2).",
				square.hasEdge(new IntegerPair(4, 2), new IntegerPair(5, 2)));
		assertTrue("Should have edge (4,2) (4,3).",
				square.hasEdge(new IntegerPair(4, 2), new IntegerPair(4, 3)));
		assertTrue("Should have edge (4,3) (5,3).",
				square.hasEdge(new IntegerPair(4, 3), new IntegerPair(5, 3)));
		assertTrue("Should have edge (5,1) (5,2).",
				square.hasEdge(new IntegerPair(5, 1), new IntegerPair(5, 2)));
		assertTrue("Should have edge (5,2) (5,3).",
				square.hasEdge(new IntegerPair(5, 2), new IntegerPair(5, 3)));
		assertTrue("Should have edge (5,3) (5,4).",
				square.hasEdge(new IntegerPair(5, 3), new IntegerPair(5, 4)));

		g = MotionPlanning.readFloorPlan("SpecFloorPlan.txt");
		square = MotionPlanning.square(g);
		MotionPlanning.configSpace(g, square, 2);

		assertEquals("Should have 10 vertexes.", 10, square.numV());
		assertEquals("Should have 10 edges.", 10, square.numE());

		// Test the existance of each Vertex.
		assertTrue("Should have vertex 1, 3.",
				square.hasVertex(new IntegerPair(1, 3)));
		assertTrue("Should have vertex 1, 4.",
				square.hasVertex(new IntegerPair(1, 4)));
		assertTrue("Should have vertex 2, 4.",
				square.hasVertex(new IntegerPair(2, 4)));
		assertTrue("Should have vertex 2, 5.",
				square.hasVertex(new IntegerPair(2, 5)));
		assertTrue("Should have vertex 3, 1.",
				square.hasVertex(new IntegerPair(3, 1)));
		assertTrue("Should have vertex 3, 5.",
				square.hasVertex(new IntegerPair(3, 5)));
		assertTrue("Should have vertex 4, 1.",
				square.hasVertex(new IntegerPair(4, 1)));
		assertTrue("Should have vertex 4, 2.",
				square.hasVertex(new IntegerPair(4, 2)));
		assertTrue("Should have vertex 5, 2.",
				square.hasVertex(new IntegerPair(5, 2)));
		assertTrue("Should have vertex 5, 3.",
				square.hasVertex(new IntegerPair(5, 3)));

		assertTrue("Should have edge (1,3) (1,4).",
				square.hasEdge(new IntegerPair(1, 3), new IntegerPair(1, 4)));
		assertTrue("Should have edge (1,3) (5,3).",
				square.hasEdge(new IntegerPair(1, 3), new IntegerPair(5, 3)));
		assertTrue("Should have edge (1,4) (2,4).",
				square.hasEdge(new IntegerPair(1, 4), new IntegerPair(2, 4)));
		assertTrue("Should have edge (2,4) (2,5).",
				square.hasEdge(new IntegerPair(2, 4), new IntegerPair(2, 5)));
		assertTrue("Should have edge (2,5) (3,5).",
				square.hasEdge(new IntegerPair(2, 5), new IntegerPair(3, 5)));
		assertTrue("Should have edge (3,1) (4,1).",
				square.hasEdge(new IntegerPair(3, 1), new IntegerPair(4, 1)));
		assertTrue("Should have edge (3,1) (3,5).",
				square.hasEdge(new IntegerPair(3, 1), new IntegerPair(3, 5)));
		assertTrue("Should have edge (4,1) (4,2).",
				square.hasEdge(new IntegerPair(4, 1), new IntegerPair(4, 2)));
		assertTrue("Should have edge (4,2) (5,2).",
				square.hasEdge(new IntegerPair(4, 2), new IntegerPair(5, 2)));
		assertTrue("Should have edge (5,2) (5,3).",
				square.hasEdge(new IntegerPair(5, 2), new IntegerPair(5, 3)));

	}

	@Test
	public void testSchedule() throws FileNotFoundException {
		Graph<Integer> g = MotionPlanning
				.readFloorPlan("SpecFloorPlan.txt");
		assertEquals(
				"Did not get expected R=0 results.",
				"(1,3)->(2,3)->(2,2)->(2,1)->(2,5)->(1,5)->(5,5)->(4,5)->(4,1)->(4,2)->(4,3)->(3,3)->(3,4)->(3,5)->(3,1)",
				MotionPlanning.schedule(g, 0, 1, 3, 3, 1));
		assertEquals(
				"Did not get expected R=1 results.",
				"(1,3)->(2,3)->(2,4)->(2,5)->(1,5)->(1,4)->(5,4)->(5,3)->(4,3)->(4,2)->(4,1)->(4,5)->(3,5)->(3,1)",
				MotionPlanning.schedule(g, 1, 1, 3, 3, 1));
		assertEquals("Did not get expected R=2 results.",
				"(1,3)->(1,4)->(2,4)->(2,5)->(3,5)->(3,1)",
				MotionPlanning.schedule(g, 2, 1, 3, 3, 1));
		assertEquals("Did not get expected R=3 results.", "NoScheduleExists",
				MotionPlanning.schedule(g, 3, 1, 3, 3, 1));

		try {
			MotionPlanning.schedule(g, -1, 1, 3, 3, 1);
			fail("Illegal argument for r passed, should have thrown an IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
		}

		try {
			MotionPlanning.schedule(g, 1, 6, 3, 3, 1);
			fail("Illegal argument for r passed, should have thrown an IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
		}

		try {
			MotionPlanning.schedule(g, 1, 1, 7, 3, 1);
			fail("Illegal argument for r passed, should have thrown an IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
		}

		try {
			MotionPlanning.schedule(g, 1, 1, 3, 0, 1);
			fail("Illegal argument for r passed, should have thrown an IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
		}

		try {
			MotionPlanning.schedule(g, 1, 1, 3, 3, 10);
			fail("Illegal argument for r passed, should have thrown an IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
		}

	}

}