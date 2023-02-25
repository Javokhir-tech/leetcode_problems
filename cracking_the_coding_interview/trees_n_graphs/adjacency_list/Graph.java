package cracking_the_coding_interview.trees_n_graphs.adjacency_list;

import java.util.*;

public class Graph<T> {

    public int size;
    public Map<T, Node<T>> vertices;

    public Graph(int size) {
        this.size = size;
        vertices = new HashMap<>(size);
    }

    public void add(T s, T d) {
        // get index of node with value s
        if (!vertices.containsKey(s)) {
            var node = new Node<T>(s);
            node.children = new HashSet<>();
            vertices.put(s, node);
        }
        vertices.get(s).children.add(new Node<T>(d));
    }

    public void print() {
        for (var set: vertices.entrySet()) {
            System.out.println("vertex: " + set.getKey());
            for (var child: set.getValue().children) {
                System.out.println("-> " + child.value);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        var graph = new Graph<String>(2);
        graph.add("Kim", "Jim");
        graph.add("Jim", "Tony");
        graph.add("Tony", "Kim");
        graph.add("Tony", "Lee");
        graph.add("Lee", "Tony");

        graph.add("Jack", "George");
        graph.add("John", "Jack");
        graph.add("George", "John");

        graph.print();
    }
    static class Node<T> {
        private T value;
        private HashSet<Node<T>> children;

        public Node(T value) {
            this.value = value;
        }

        public Node() {}
    }
}
