package cracking_the_coding_interview.trees_n_graphs.adjacency_list;

import java.util.*;

public class Graph<T> { // directed generic

    public Map<T, Node<T>> vertices;

    public Graph() {
        vertices = new HashMap<>();
    }

    public void add(T s, T d) {
        // get index of node with value s
        if (!vertices.containsKey(s)) {
            var node = new Node<>(s);
            node.children = new HashSet<>();
            vertices.put(s, node);
        }
        vertices.get(s).children.add(new Node<>(d));
    }

    public void delete(T s, T d) {
        for (var vertex : vertices.entrySet()) {
            if (vertex.getKey().equals(s)) {
                vertex.getValue().children.removeIf(child -> child.data.equals(d));
            }
        }
        if (vertices.get(s).children.isEmpty()) vertices.remove(s);
    }

    public void dfs(T start) {
        var visited = new HashMap<T, Boolean>();
        dfsRecursive(start, visited);
    }

    private void dfsRecursive(T current, Map<T, Boolean> isVisited) {
        isVisited.put(current, true);
        System.out.print(current + " -> ");
        for (var dest : vertices.entrySet()) {
            for (var val: dest.getValue().children)
                if (!isVisited.containsKey(val.data)) {
                    dfsRecursive(val.data, isVisited);
                }
        }
    }

    public void print() {
        for (var set: vertices.entrySet()) {
            System.out.println("\nvertex: " + set.getKey());
            for (var child: set.getValue().children) {
                System.out.print("-> " + child.data);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        var graph = new Graph<String>();
        graph.add("Kim", "Jim");
        graph.add("Kim", "Jacob");

        graph.add("Jim", "Tony");
        graph.add("Tony", "Kim");
        graph.add("Tony", "Lee");
        graph.add("Lee", "Tony");

        graph.add("Jack", "George");
        graph.add("John", "Jack");
        graph.add("George", "John");

//        graph.delete("Kim", "Jim");

        graph.dfs("Kim");
//        graph.print();
    }
    static class Node<T> {
        private T data;
        private HashSet<Node<T>> children;

        public Node(T data) {
            this.data = data;
        }
    }
}

class Graph3<T> {
    public int size;
    public ArrayList<Node<T>> vertices;

    public Graph3(int size) {
        this.size = size;
        vertices = new ArrayList<>(size);
    }

    public void add(T s, T d) {
        if (!containsName(vertices, s)) {
            var node = new Node<>(s);
            node.children = new ArrayList<>();
            vertices.add(node);
        }
        var values = vertices.stream().map(Node::getValue).toList();    // O(n)
        var index = values.indexOf(s);
        vertices.get(index).children.add(new Node<>(d));
    }
    private boolean containsName(final ArrayList<Node<T>> list, final T value) {    // O(n)
        return list.stream().map(Node::getValue).anyMatch(value::equals);
    }

    public void print() {
        for (var vertex : vertices) {
            System.out.println("\nvertex: " + vertex.value);
            for (var child : vertex.children) {
                System.out.print(" -> " + child.value);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        var graph = new Graph3<String>(2);
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
        private ArrayList<Node<T>> children;

        public Node(T value) {
            this.value = value;
        }

        public T getValue() {
            return value;
        }
    }
}
