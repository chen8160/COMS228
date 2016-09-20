package edu.iastate.cs228.hw5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class MotionPlanning {

	/**
	 * Returns a floor plan graph built from an input file floorPlanFile, which
	 * is in the following format:
	 * 
	 * Each line, which indicates exactly one edge of the graph should contain
	 * two integers, Ui and Vi, that are the endpoints of the edge.
	 * 
	 * Each edge (u, v) must appear exactly once in the input file and must not
	 * be repeated as (v, u).
	 * 
	 * Poorly formed lines of the floor plan (i.e. lines that do not have
	 * exactly 2 integers) should simply be skipped
	 * 
	 * @param floorPlanString
	 *            - the file containing the floor plan
	 * @return a Graph constructed from the floor plan, null if the given file
	 *         doesn't exist.
	 * @throws FileNotFoundException
	 * 
	 */
	public static Graph<Integer> readFloorPlan(String floorPlanString)
			throws FileNotFoundException {
		Graph<Integer> g = new Graph<Integer>();
		File f = new File(floorPlanString);
		Scanner scan = new Scanner(f);
		while (scan.hasNextLine()) {
			ArrayList<Integer> arr = new ArrayList<Integer>();
			Scanner line = new Scanner(scan.nextLine());
			while (line.hasNextInt()) {
				arr.add(line.nextInt());
			}
			if (arr.size() != 2)
				continue;
			g.addEdge(arr.get(0), arr.get(1));
		}
		return g;
	}

	/**
	 * Returns the square of the (floor plan) graph g.
	 * 
	 * @param g
	 *            - the Graph to create the square of
	 * @return the square of g
	 */
	public static Graph<IntegerPair> square(Graph<Integer> g) {
		Graph<IntegerPair> h = new Graph<IntegerPair>();
		Iterator<Integer> iter1 = g.vertices().iterator();
		while (iter1.hasNext()) {
			int temp = iter1.next();
			Iterator<Integer> iter2 = g.vertices().iterator();
			while (iter2.hasNext()) {
				h.addVertex(new IntegerPair(temp, iter2.next()));
			}
		}
		Iterator<IntegerPair> intpairIter = h.vertices().iterator();
		while (intpairIter.hasNext()) {
			IntegerPair curr = intpairIter.next();
			int u = curr.first;
			int v = curr.second;
			Iterator<Integer> iter = g.adjacentTo(u).iterator();
			while (iter.hasNext()) {
				h.addEdge(curr, new IntegerPair(iter.next(), v));
			}
			iter = g.adjacentTo(v).iterator();
			while (iter.hasNext()) {
				h.addEdge(curr, new IntegerPair(u, iter.next()));
			}
		}
		return h;
	}

	/**
	 * The arguments are the square graph h for some floor plan graph, and an
	 * integer r >= 0. The method deletes nodes and edges from h to obtain the
	 * configuration-space graph. This method must be implemented as described
	 * on page 6 of the spec.
	 * 
	 * @param g
	 *            - the floor plan graph
	 * @param h
	 *            - the graph to reduce
	 * @param r
	 *            - the minimum allowed distance between vertices in g
	 * @throws IllegalArgumentException
	 *             - if r is negative
	 */
	public static void configSpace(Graph<Integer> g, Graph<IntegerPair> h, int r)
			throws IllegalArgumentException {
		if (r < 0)
			throw new IllegalArgumentException();
		Iterator<Integer> vertices1 = g.vertices().iterator();
		while (vertices1.hasNext()) {
			int u = vertices1.next();
			Map<Integer, Integer> map = GraphUtil.getDistancesFrom(g, u);
			Iterator<Integer> vertices2 = g.vertices().iterator();
			while (vertices2.hasNext()) {
				int v = vertices2.next();
				if (map.get(v) < r) {
					h.removeVertex(new IntegerPair(u, v));
				}
			}
		}
	}

	/**
	 * Returns a String representation of an interference-free schedule for two
	 * robots on graph g, where robots 1 and 2 start at nodes s1 and s2,
	 * respectively, and end at nodes f1 and f2, respectively, and at every step
	 * of the schedule, robots 1 and 2 are at distance at least r from each
	 * other. If there is no interference-free schedule, the String returned
	 * should be "NoScheduleExists". The method must use the algorithm described
	 * on page 6. That is, it must (1) build the square of g using square(), (2)
	 * use configSpace() to build the configuration-space graph using the square
	 * of g, and (3) use depth- or breadth-first search to determine if there is
	 * a path from the starting configuration to the final configuration in the
	 * configuration-space graph.
	 * 
	 * @param g
	 *            - the floor plan graph
	 * @param r
	 *            - the minimum allowed distance between vertices in g
	 * @param s1
	 *            - the start vertex of Robot 1
	 * @param f1
	 *            - the final vertex of Robot 1
	 * @param s2
	 *            - the start vertex of Robot 2
	 * @param f2
	 *            - the final vertex of Robot 2
	 * @return a String as formatted above if a path exists, "NoScheduleExists"
	 *         otherwise
	 * @throws IllegalArgumentException
	 *             - if r is negative or s1, f1, s2, or f2 are not in g
	 */
	public static String schedule(Graph<Integer> g, int r, int s1, int f1,
			int s2, int f2) throws IllegalArgumentException {
		if (r < 0 || !g.hasVertex(s1) || !g.hasVertex(s2) || !g.hasVertex(f1)
				|| !g.hasVertex(f2)) {
			throw new IllegalArgumentException();
		}
		Graph<IntegerPair> h = square(g);
		configSpace(g, h, r);
		IntegerPair s = new IntegerPair(s1, s2);
		IntegerPair t = new IntegerPair(f1, f2);
		Iterable<IntegerPair> path = GraphUtil.getPath(h, s, t);
		if (path == null) {
			return "NoScheduleExists";
		}
		Iterator<IntegerPair> iter = path.iterator();
		String returnString = iter.next().toString();
		while (iter.hasNext()) {
			returnString += "->" + iter.next().toString();
		}
		return returnString;
	}

	/**
	 * Helper class to store data for a floor plan's square
	 */
	public static class IntegerPair {

		/*
		 * Instance variable for the stored Integers
		 */
		int first, second;

		/**
		 * Stores the given ints
		 */
		public IntegerPair(int first, int second) {
			this.first = first;
			this.second = second;
		}

		/**
		 * Returns true if o is an IntegerPair and the ints this and o store are
		 * the same, and in the same order.
		 */
		@Override
		public boolean equals(Object o) {
			if (o == null || o.getClass() != this.getClass())
				return false;
			IntegerPair ip = (IntegerPair) o;
			if (this.first == ip.first && this.second == ip.second)
				return true;
			return false;
		}

		/**
		 * Returns the stored ints enclosed in parenthesis separated by a comma.
		 */
		public String toString() {
			return "(" + first + "," + second + ")";
		}

		/**
		 * This is an example hashCode() method. You are welcome to write your
		 * own, but this method but always retain the characteristic:
		 * 
		 * if a.equals(b) then a.hashCode() == b.hashCode()
		 * 
		 * (Note, the converse of this is not always true)
		 */
		@Override
		public int hashCode() {
			return 100003 * first + second;
		}
	}
}
