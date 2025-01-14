package leetcodes.ds.algos;

public class FindMax {

  public static int findMax(int[] arr) {
    if (arr == null || arr.length == 0)
      return -1;
    return findMax(arr, 0, arr.length - 1);
  }

  private static int findMax(int[] arr, int l, int r) {
    if (l == r) {
      return arr[l];
    }

    int m = (l + r) / 2;

    return Math.max(findMax(arr, l, m), findMax(arr, m + 1, r));
  }

  public static void main(String[] args) {
    int[] arr = {4, 2, 5, 7, 0, 9, 1, 3, -10};
    System.out.println(findMax(arr));
  }
}
