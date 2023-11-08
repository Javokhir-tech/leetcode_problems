package cracking_the_coding_interview.trees_n_graphs;

import java.util.ArrayList;
import java.util.List;

public class TreeNode<T> {
    public T root;
    private TreeNode<T> parent;
    public List<TreeNode<T>> children;

    public TreeNode(T data) {
        this.root = data;
        children = new ArrayList<>();
    }

    public TreeNode<T> add(T data) {
        var child = new TreeNode<>(data);
        child.parent = this;
        this.children.add(child);
        return child;
    }

    public T getRoot() {
        return root;
    }

    public boolean isRoot() {
        return parent == null;
    }

    public boolean isLeaf() {
        return children.size() == 0;
    }

    public int getLevel() {
        if (this.isRoot())
            return 0;
        else
            return parent.getLevel() + 1;
    }

    public void inOrderTraversal(TreeNode<T> tree) {
        if (tree.children == null) return;
        System.out.println(tree.root);
        for (var child : tree.children) {
            inOrderTraversal(child);
        }
    }

    public static void main(String[] args) {
        var tree = new TreeNode<>(6);
        var child1 = tree.add(3);
        var child2 = tree.add(8);

        child1.add(2);
        child1.add(4);

        child2.add(7);
        child2.add(9);

        tree.inOrderTraversal(tree);
    }
}
