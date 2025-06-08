package leetcodes.ds.algos.search;

public class BinarySearch {
  public static int search(int[] nums, int target) {
    if (nums == null) {
      return -1;
    }
    return search(nums, target, 0, nums.length - 1);
  }

  private static int search(int[] nums, int target, int l, int r) {
    if (l <= r) {
      int m = (l + r) / 2;
      if (target < nums[m]) {
        return search(nums, target, l, m - 1);
      } else if (target > nums[m]) {
        return search(nums, target, m + 1, r);
      } else {
        return m;
      }
    }
    return -1;
  }

  public static void main(String[] args) {
    int [] arr = {1 , 2, 2, 4, 6, 7, 8};
    System.out.println(search(arr, 10));
  }
}
