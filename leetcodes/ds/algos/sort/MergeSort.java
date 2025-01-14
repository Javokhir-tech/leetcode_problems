package leetcodes.ds.algos.sort;

import java.util.Arrays;

public class MergeSort {

  private static int[] mergeSort(int[] arr) {
    if (arr == null)
      return new int[]{ };
    return mergeSort(arr, 0, arr.length - 1);
  }

  private static int[] mergeSort(int[] arr, int l, int r) {
    if (l < r) {
      int m = (l + r) / 2;          // find middle
      mergeSort(arr, l, m);         // sort left side
      mergeSort(arr, m + 1, r);  // sort right side
      merge(arr, l, m, r);          // merge left right sides
    }
    System.out.println(Arrays.toString(arr));
    return arr;
  }

  private static void merge(int[] arr, int l, int m, int r) {
    int len1 = m - l + 1;
    int len2 = r - m;
    int[] L = new int[len1];      // create temp arrays
    int[] R = new int[len2];

    for (int i = 0; i < len1; i++) {  // copy values into temp arrays
      L[i] = arr[l + i];
    }

    for (int j = 0; j < len2; j++) {
      R[j] = arr[m + 1 + j];
    }

    int i = 0, j = 0, k = l;

    while (i < len1 && j < len2) {  // compare values in temp arrays, set smallest first
      if (L[i] < R[j]) {
        arr[k++] = L[i++];
      } else {
        arr[k++] = R[j++];
      }
    }

    while (i < len1) {  // copy remaining
      arr[k++] = L[i++];
    }

    while (j < len2) {
      arr[k++] = R[j++];
    }
  }

  public static void main(String[] args) {
    int[] arr = {4, 2, 7, 5, 9, 0, 3, 1};
    int[] sorted = mergeSort(arr);
    for (int num : sorted) {
      System.out.print(num + " ");
    }
  }
}
