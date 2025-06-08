package leetcodes.ds.algos.search;

import java.util.ArrayList;
import java.util.List;

public class TreeMap {
  private static class TreeNode {
    int key;
    int value;
    TreeNode left;
    TreeNode right;
    TreeNode(int key, int value) {
      this.key = key;
      this.value = value;
    }
  }
  private TreeNode root;

  public TreeMap() {}

  public void insert(int key, int val) {
    TreeNode node = insert(root, key, val);
    if (root == null) {
      root = node;
    }
  }

  private TreeNode insert(TreeNode root, int key, int val) {
    if (root == null) {
      return new TreeNode(key, val);
    }
    if (key > root.key) {
      root.right = insert(root.right, key, val);
    } else if (key < root.key) {
      root.left = insert(root.left, key, val);
    } else {
      root.value = val;
    }
    return root;
  }

  public int get(int key) {
    return get(root, key);
  }

  private int get(TreeNode root, int key) {
    if (root == null) {
      return -1;
    }
    if (key > root.key) {
      return get(root.right, key);
    } else if (key < root.key) {
      return get(root.left, key);
    }
    return root.value;
  }

  public int getMin() {
    if (root == null) {
      return -1;
    }
    return min(root).value;
  }

  private TreeNode min(TreeNode root) {
    TreeNode curr = root;
    while (curr.left != null) {
      curr = curr.left;
    }
    return curr;
  }

  public int getMax() {
    if (root == null) {
      return -1;
    }
    return max(root);
  }

  private int max(TreeNode root) {
    TreeNode curr = root;
    while (curr.right != null) {
      curr = curr.right;
    }
    return curr.value;
  }

  public void remove(int key) {
    root = remove(root, key);
  }

  private TreeNode remove(TreeNode root, int key) {
    if (root == null) {
      return null;
    }
    if (key > root.key) {
      root.right = remove(root.right, key);
    } else if (key < root.key) {
      root.left = remove(root.left, key);
    } else {
      if (root.left == null) {
        return root.right;
      } else if (root.right == null) {
        return root.left;
      } else {
        TreeNode min = min(root.right);
        root.key = min.key;
        root.right = remove(root.right, min.key);
      }
    }
    return root;
  }

  public List<Integer> getInorderKeys() {
    List<Integer> keys = new ArrayList<>();
    return getInorderKeys(root, keys);
  }

  private List<Integer> getInorderKeys(TreeNode root, List<Integer> keys) {
    if (root != null) {
      getInorderKeys(root.left, keys);
      keys.add(root.key);
      getInorderKeys(root.right, keys);
    }
    return keys;
  }

  public static void main(String[] args) {
    TreeMap map = new TreeMap();
    map.insert(1, 10);
    System.out.println(map.getMin());
    map.insert(4, 0);
    System.out.println(map.getMax());
  }
}
