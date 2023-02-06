package cracking_the_coding_interview.trees_n_graphs;

public class BTreeNode {
    private int data;
    private BTreeNode left;
    private BTreeNode right;

    public BTreeNode(int value) {
        data = value;
        left = null;
        right = null;
    }

    /**
     * in-order traversal (ascending in order)
     * left -> root -> right
     * */
    public static void inOrder(BTreeNode treeNode) {
        if (treeNode != null){
            inOrder(treeNode.left);
            System.out.println(treeNode.data);
            inOrder(treeNode.right);
        }
    }

    /**
     * pre-order (visit current before child)
     * root -> left -> right
     * */
    public static void preOrder(BTreeNode treeNode) {
        if (treeNode != null){
            System.out.println(treeNode.data);
            preOrder(treeNode.left);
            preOrder(treeNode.right);
        }
    }

    /**
     * post-order (visit child before root)
     * left -> right -> root
     * */
    public static void postOrder(BTreeNode treeNode) {
        if (treeNode != null){
            preOrder(treeNode.left);
            preOrder(treeNode.right);
            System.out.println(treeNode.data);
        }
    }

    /**
     * recursively add values into b-tree
     * if value grater than current node it goes right, otherwise goes left
     * */
    public static BTreeNode add(BTreeNode tree, int data) {
        if (tree == null) {
            return new BTreeNode(data);
        }

        if (tree.data > data) {
            tree.left = add(tree.left, data);
        } else if (tree.data < data) {
            tree.right = add(tree.right, data);
        }
        return tree;
    }

    public static void main(String[] args) {
        var tree = new BTreeNode(5);
//        tree.left = new BTreeNode(3);
//        tree.right = new BTreeNode(6);
//        tree.left.left = new BTreeNode(1);
//        tree.left.right = new BTreeNode(2);
//
//        tree.right.left = new BTreeNode(7);
//        tree.right.right = new BTreeNode(8);
        add(tree, 3);
        add(tree, 6);
        add(tree, 4);
        add(tree, 2);
        System.out.println("---- in order ----");
        inOrder(tree);
        System.out.println("---- pre order ----");
        preOrder(tree);
    }
}
