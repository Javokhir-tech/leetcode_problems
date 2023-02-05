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
     * in-order traversal
     * left -> root -> right
     * */
    public static void inOrder(BTreeNode treeNode) {
        if (treeNode != null){
            inOrder(treeNode.left);
            System.out.println(treeNode.data);
            inOrder(treeNode.right);
        }
    }

    public static void main(String[] args) {
        var tree = new BTreeNode(5);
        tree.left = new BTreeNode(3);
        tree.right = new BTreeNode(6);
        tree.left.left = new BTreeNode(1);
        tree.left.right = new BTreeNode(2);

        tree.right.left = new BTreeNode(7);
        tree.right.right = new BTreeNode(8);
        inOrder(tree);
    }
}
