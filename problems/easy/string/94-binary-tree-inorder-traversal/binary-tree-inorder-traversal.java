/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> nodes = new ArrayList<>();
        inOrder(root, nodes);
        return nodes;
    }

    private void inOrder(TreeNode root, List<Integer> nodes) {
        if (root != null) {
            inOrder(root.left, nodes);
            nodes.add(root.val);
            inOrder(root.right, nodes);
        }
    }
}