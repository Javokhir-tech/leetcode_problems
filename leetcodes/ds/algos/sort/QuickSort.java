package leetcodes.ds.algos.sort;

import java.util.Arrays;

public class QuickSort {

  private static int[] quickSort(int[] arr) {
    return quickSort(arr, 0, arr.length - 1);
  }

  private static int[] quickSort(int[] arr, int l, int r) {
    if (l >= r) {     // base case if left is greater or equal to right
      return arr;
    }
    int left = l;     // set left var to l
    int pivot = arr[r]; // set pivot as last
    for (int i = l; i < r; i++) {
      if (arr[i] < pivot) {   // set less elements from pivot where the left pointer is
        swap(arr, left, i);
        left++;
      }
    }
    arr[r] = arr[left]; // swap pivot with element at left pointer
    arr[left] = pivot;

    quickSort(arr, l, left - 1);    // do not include pivot when sorting left/right
    quickSort(arr, left + 1, r);
    return arr;
  }

  private static void swap(int[] arr, int l, int r) {
    int temp = arr[l];
    arr[l] = arr[r];
    arr[r] = temp;
  }

  public static void main(String[] args) {
    int[] arr = {4, 2, 7, 10, 5, 9, 0, 1, 3};
    System.out.println(Arrays.toString(quickSort(arr)));
  }
}
