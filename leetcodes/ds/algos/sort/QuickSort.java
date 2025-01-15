package leetcodes.ds.algos.sort;

import java.util.Arrays;

public class QuickSort {

  private static int[] quickSort(int[] arr) {
    if (arr == null)
      return new int[] { };
    return quickSort(arr, 0, arr.length - 1);
  }

  private static int[] quickSort(int[] arr, int l, int r) {
    if (l < r) {     // base case if l is greater or equal to r pointer
      int pivotIndex = partition(arr, l, r);

      quickSort(arr, l, pivotIndex - 1);    // do not include pivot when sorting left/right
      quickSort(arr, pivotIndex + 1, r);
    }
    return arr;
  }

  private static int partition(int[] arr, int l, int r) {
    int left = l;     // set left var to l
    int pivot = arr[r]; // set pivot as last
    for (int i = l; i < r; i++) {
      if (arr[i] < pivot) {   // set less elements from pivot where the left pointer is
        swap(arr, left, i);
        left++;
      }
    }
    swap(arr, left, r);   // swap pivot with value at index left
    return left;
  }

  private static void swap(int[] arr, int l, int r) {
    int temp = arr[l];
    arr[l] = arr[r];
    arr[r] = temp;
  }

  public static void main(String[] args) {
    int[] arr = {4, 2, 7, 10, 5, -10, 9, 0, 1, 3};
    System.out.println(Arrays.toString(quickSort(arr)));
  }
}
