package cracking_the_coding_interview.trees_n_graphs.adjacency_list;

import java.util.*;

// Adjascency List representation in Java
public class Graph2 {

    // Add edge
    static void addEdge(ArrayList<ArrayList<Integer>> am, int s, int d) {
        am.get(s).add(d);
        am.get(d).add(s);
    }

    static void delete(ArrayList<ArrayList<Integer>> am, int s, int d) {
        for (int i = 0; i < am.get(d).size(); i++) {
            if (am.get(d).get(i).equals(s)) {
                am.get(d).remove(i);
                break;
            }
        }
        for (int i = 0; i < am.get(s).size(); i++) {
            if (am.get(s).get(i).equals(d)) {
                am.get(s).remove(i);
                break;
            }
        }
    }

    public static void main(String[] args) {

        // Create the graph
        int V = 7;
        ArrayList<ArrayList<Integer>> am = new ArrayList<>(V);

        for (int i = 0; i < V; i++)
            am.add(new ArrayList<>());

        // Add edges
        addEdge(am, 0, 1);
        addEdge(am, 1, 2);
        addEdge(am, 2, 3);
        addEdge(am, 2, 0);
        addEdge(am, 3, 2);

        addEdge(am, 4, 6);
        addEdge(am, 6, 5);

        delete(am, 0, 1);
        printGraph(am);
    }

    // Print the graph
    static void printGraph(ArrayList<ArrayList<Integer>> am) {
        for (int i = 0; i < am.size(); i++) {
            System.out.println("\nVertex " + i + ":");
            for (int j = 0; j < am.get(i).size(); j++) {
                System.out.print(" -> " + am.get(i).get(j));
            }
            System.out.println();
        }
    }
}
