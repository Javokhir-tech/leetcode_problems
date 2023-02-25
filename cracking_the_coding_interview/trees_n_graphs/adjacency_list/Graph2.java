package cracking_the_coding_interview.trees_n_graphs.adjacency_list;

import java.util.*;

// Adjascency List representation in Java undirected
public class Graph2 {

    private int size;
    private ArrayList<ArrayList<Integer>> vertices;

    public Graph2(int size) {
        this.size = size;
        vertices = new ArrayList<>(size);
        for (int i = 0; i < size; i++)
            vertices.add(new ArrayList<>());
    }

    // Add edge
    public void addEdge(int s, int d) {
        vertices.get(s).add(d);
        vertices.get(d).add(s);
    }

    public void delete(int s, int d) {
        for (int i = 0; i < vertices.get(d).size(); i++) {
            if (vertices.get(d).get(i).equals(s)) {
                vertices.get(d).remove(i);
                break;
            }
        }
        for (int i = 0; i < vertices.get(s).size(); i++) {
            if (vertices.get(s).get(i).equals(d)) {
                vertices.get(s).remove(i);
                break;
            }
        }
    }

    public static void main(String[] args) {

        var graph = new Graph2(7);

        // Add edges
        graph.addEdge( 0, 1);
        graph.addEdge( 1, 2);
        graph.addEdge( 2, 3);
        graph.addEdge( 2, 0);
        graph.addEdge( 3, 2);

        graph.addEdge( 4, 6);
        graph.addEdge( 6, 5);

//        graph.delete( 0, 1);
        graph.printGraph();
    }

    // Print the graph
    public void printGraph() {
        for (int i = 0; i < vertices.size(); i++) {
            System.out.println("\nVertex " + i + ":");
            for (int j = 0; j < vertices.get(i).size(); j++) {
                System.out.print(" -> " + vertices.get(i).get(j));
            }
            System.out.println();
        }
    }
}
// directed
class Graph4 {

    private int size;
    private ArrayList<ArrayList<Integer>> vertices;

    public Graph4(int size) {
        this.size = size;
        vertices = new ArrayList<>(size);
        for (int i = 0; i < size; i++)
            vertices.add(new ArrayList<>());
    }

    // Add edge
    public void addEdge(int s, int d) {
        vertices.get(s).add(d);
    }

    public void delete(int s, int d) {
        for (int i = 0; i < vertices.get(s).size(); i++) {
            if (vertices.get(s).get(i).equals(d)) {
                vertices.get(s).remove(i);
                break;
            }
        }
    }

    public void dfs(int start) {
        boolean[] isVisited = new boolean[vertices.size()];
        dfsRecursive(start, isVisited);
    }

    private void dfsRecursive(int current, boolean[] isVisited) {
        isVisited[current] = true;
        System.out.print(current + " -> ");
        for (int dest : vertices.get(current)) {
            if (!isVisited[dest])
                dfsRecursive(dest, isVisited);
        }
    }

    public static void main(String[] args) {

        var graph = new Graph4(10);

        // Add edges
        graph.addEdge( 0, 1);
        graph.addEdge( 1, 2);
        graph.addEdge( 2, 3);
        graph.addEdge( 2, 0);
        graph.addEdge( 3, 2);

        graph.addEdge( 4, 6);
        graph.addEdge( 5, 4);

        graph.addEdge( 6, 5);

        graph.addEdge(0, 7);
        graph.addEdge(0, 8);
        graph.addEdge(8, 9);

//        graph.delete(0, 1);
        graph.dfs(0);
//        graph.printGraph();
    }

    // Print the graph
    public void printGraph() {
        for (int i = 0; i < vertices.size(); i++) {
            System.out.println("\nVertex " + i + ":");
            for (int j = 0; j < vertices.get(i).size(); j++) {
                System.out.print(" -> " + vertices.get(i).get(j));
            }
            System.out.println();
        }
    }
}
