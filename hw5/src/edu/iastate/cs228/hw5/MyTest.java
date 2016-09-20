package edu.iastate.cs228.hw5;
 
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import edu.iastate.cs228.hw5.MotionPlanning.IntegerPair;
 
public class MyTest {
 
        public static void main(String[] args) throws IOException {
               
                File graphsFolder = new File("project5data");
                File testDataFolder = new File("project5data/testData");
                test(graphsFolder, testDataFolder);
               
                /*
                 * Graph<Integer> g = MotionPlanning
                 * .readFloorPlan("project5data/square.txt");
                 * System.out.println("Square:");
                 * System.out.println(MotionPlanning.schedule(g, 1, 1, 2, 3, 4));
                 *
                 * System.out.println("SquareMesh:"); g =
                 * MotionPlanning.readFloorPlan("project5data/4SidedMesh.txt");
                 * Graph<IntegerPair> h = MotionPlanning.square(g);
                 * System.out.println("Square of g:\n" + h);
                 * MotionPlanning.configSpace(g, h, 1);
                 * System.out.println("configSpace of g:\n" + h);
                 * System.out.println(MotionPlanning.schedule(g, 1, 1, 2, 3, 4));
                 */
               
                int r = 1;
                //set "project5data" to wherever you are keeping the test files.
                File graphsFolder1 = new File("project5data");
                for (File f : graphsFolder1.listFiles(new FileFilter() {
                       
                        @Override
                        public boolean accept(File pathname) {
                                return (pathname.isFile() && pathname.getName().endsWith(".txt"));
                        }
                })) {
                        Graph<Integer> g = MotionPlanning
                                        .readFloorPlan(f.getCanonicalPath());
                        System.out.printf("%s:\n", f.getName());
                        String pathString = MotionPlanning.schedule(g, r, 1, 2, 3, 4);
                        System.out.println(pathString);
                        System.out.println("\tThe path is " + (checkPath(g, pathString, r) ? "valid." : "invalid."));
                }
              for (int sides = 4; sides < 8; sides++) {
                        Graph<Integer> g = MotionPlanning
                                        .readFloorPlan(makeLoopFile(sides));
                        System.out.printf("%d-SidedLoop:\n", sides);
                        String pathString = MotionPlanning.schedule(g, r, 1, 2, 3, 4);
                        System.out.println(pathString);
                        System.out.println("\tThe path is " + (checkPath(g, pathString, r) ? "valid." : "invalid."));
 
                        g = MotionPlanning.readFloorPlan(makeMeshFile(sides));
                        System.out.printf("%d-SidedMesh:\n", sides);
                        pathString = MotionPlanning.schedule(g, r, 1, 2, 3, 4);
                        System.out.println(pathString);
                        System.out.println("\tThe path is " + (checkPath(g, pathString, r) ? "valid." : "invalid."));
                }
                Graph<Integer> g = MotionPlanning
                                .readFloorPlan("project5data/SpecFloorPlan.txt");
                System.out.println("SpecFloorPlan:");
                String pathString = MotionPlanning.schedule(g, r, 1, 2, 3, 4);
                System.out.println(pathString);
                System.out.println("\tThe path is " + (checkPath(g, pathString, r) ? "valid." : "invalid."));
        }
 
        private static String makeLoopFile(int sides) throws IOException {
                if (sides <= 0)
                        throw new IllegalArgumentException();
                File f = new File(String.format("project5data/%dSidedLoop.txt", sides));
                PrintWriter pw = new PrintWriter(f);
                for (int i = 1; i < sides + 1; i++) {
                        pw.print(String.format("%d %d\n", i, (i % (sides)) + 1));
                }
                pw.close();
                return f.getCanonicalPath();
        }
 
        private static String makeMeshFile(int sides) throws IOException {
                if (sides <= 0)
                        throw new IllegalArgumentException();
                File f = new File(String.format("project5data/%dSidedMesh.txt", sides));
                PrintWriter pw = new PrintWriter(f);
                for (int i = 1; i < sides + 1; i++) {
                        for (int j = i + 1; j < sides + 1; j++)
                                pw.print(String.format("%d %d\n", i, j));
                }
                pw.close();
                return f.getCanonicalPath();
        }
 
        public static boolean checkPath(Graph<Integer> g, String pathString, int r) {
              if (pathString.matches("^(\\d+, ?\\d+)->(\\d+, ?\\d+)")) {
                        Scanner path = new Scanner(pathString);
                        path.useDelimiter("->");
                        String pairString = path.next();
                        IntegerPair old = parseIP(pairString);
                        if (distanceApart(g, old) < r)
                                return false;
                        if (!g.hasVertex(old.first) || !g.hasVertex(old.second))
                                return false;
                        while (path.hasNext()) {
                                IntegerPair newPair = parseIP(path.next());
                                if (distanceApart(g, newPair) < r)
                                        return false;
                                if (!g.hasVertex(old.first) || !g.hasVertex(old.second))
                                        return false;
                                if (old.equals(newPair))
                                        return false;
                                if (old.first != newPair.first && old.second != newPair.second)
                                        return false;
                                if (old.first == newPair.first) {
                                        if (!g.hasEdge(old.second, newPair.second))
                                                return false;
                                } else if (old.second == newPair.second) {
                                        if (!g.hasEdge(old.first, newPair.first))
                                                return false;
                                }
                                old = newPair;
                        }
                        path.close();
                        return true;
              }
              return false;
        }
 
        public static IntegerPair parseIP(String pair) {
              if (pair.matches("^([\\d]+,[ ]?[\\d]+)$")) {
                        String[] parts = pair.substring(1, pair.length() - 1)
                                        .split(",[ ]?");
                        int first = Integer.parseInt(parts[0]);
                        int second = Integer.parseInt(parts[1]);
                        return new IntegerPair(first, second);
              } else {
                      return null;
              }
        }
 
        private static int distanceApart(Graph<Integer> g, IntegerPair verticies) {
                Map<Integer, Integer> distances = GraphUtil.getDistancesFrom(g,
                                verticies.first);
                return distances.get(verticies.second);
        }
       
        private static void test(File graphsFolder, File testDataFolder) {
                if (graphsFolder.exists() && testDataFolder.exists()) {
                        File[] graphFiles = graphsFolder.listFiles(new FileFilter() {
                               
                                @Override
                                public boolean accept(File pathname) {
                                        return !pathname.isDirectory() && pathname.getName().endsWith(".txt");
                                }
                        });
                        File[] testDataFiles = testDataFolder.listFiles(new FileFilter() {
                               
                                @Override
                                public boolean accept(File pathname) {
                                        return !pathname.isDirectory() && pathname.getName().endsWith(".txt");
                                }
                        });
                        for (File graphFile : graphFiles) {
                                File testData = null;
                                for (File testDataFile : testDataFiles) {
                                        if (testDataFile.getName().equals(graphFile.getName()))
                                                testData = testDataFile;
                                }
                                if (testData == null)
                                        continue;
                                try {
                                        Scanner testDataScanner = new Scanner(testData);
                                        String testInfo = testDataScanner.nextLine();
                                        testDataScanner.close();
                                        // Order of items:                r      s1      s2      f1      f2
                                        if (!testInfo.matches("^\\d \\d \\d \\d \\d$"))
                                                continue;
                                        Scanner test = new Scanner(testInfo);
                                        int r = test.nextInt();
                                        int s1 = test.nextInt();
                                        int s2 = test.nextInt();
                                        int f1 = test.nextInt();
                                        int f2 = test.nextInt();
                                        test.close();
                                        Graph<Integer> g = MotionPlanning.readFloorPlan(graphFile.getCanonicalPath());
                                        Graph<IntegerPair> h = MotionPlanning.square(g);
                                        MotionPlanning.configSpace(g, h, r);
                                        String path = getShortPath(h, new IntegerPair(s1, s2), new IntegerPair(f1, f2));
                                        boolean valid = checkPath(g, path, r);
                                        if (valid) {
                                                System.out.printf("The path given for \"%s\" was valid.\n", graphFile.getName());
                                        } else {
                                                System.out.printf("!-!-!-The path given for \"%s\" was invalid.-!-!-!\n", graphFile.getName());
                                        }
                                } catch (FileNotFoundException e) {
                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                } catch (IOException e) {
                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                }
                        }
                }
        }
 
        /**
         * Calculates the shortest simple path from vertex s to vertex f in g using a
         * breadth-first-search. Returns the result as a String.
         *
         * @param g
         *            - the Graph to traverse
         * @param s
         *            - the vertex to start the traversal from
         * @param f
         *                        - the vertex to look for and route to.
         * @return a String with the shortest simple path from the start node to the end node.
         */
        public static <V> String getShortPath(Graph<V> g, V s,
                        V f) {
                if (!g.hasVertex(s))
                        return null;
                if (!g.hasVertex(f))
                        return null;
 
                LinkedQueue<V> queue = new LinkedQueue<>();
 
                // green - unreached, red - reached but not processed, black - processed
                HashMap<V, String> color = new HashMap<>();
                HashMap<V, V> pred = new HashMap<>();
 
                for (V w : g.vertices()) {
                        if (!w.equals(s)) {
                                color.put(w, "green");
                        }
                }
 
                color.put(s, "red");
                queue.enqueue(s);
 
                boolean foundPath = false;
                while (!queue.isEmpty()) {
                        V c = queue.front();
                        for (V w : g.adjacentTo(c))
                                if (color.get(w).equals("green")) {
                                        pred.put(w, c);
                                        color.put(w, "red");
                                        queue.enqueue(w);
                                        if (w.equals(f)) {
                                                foundPath = true;
                                                f = w;
                                                break;
                                        }
                                }
                        if (foundPath)
                                break;
 
                        queue.dequeue();
                        color.put(c, "black");
                }
                V lastOnPath = f;
                String path = lastOnPath.toString();
                while (pred.containsKey(lastOnPath)) {
                        path = pred.get(lastOnPath).toString() + "->" +path;
                        lastOnPath = pred.get(lastOnPath);
                }
 
                return path;
        }
}