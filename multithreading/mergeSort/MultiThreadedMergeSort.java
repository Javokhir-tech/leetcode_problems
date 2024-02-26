package multithreading.mergeSort;

import java.util.Random;

public class MultiThreadedMergeSort {

  private static int MAX_SIZE = 25;
  private static int[] array = new int[MAX_SIZE];
  private int[] scratch = new int[MAX_SIZE];
  private static Random random = new Random(10000);
  
  public static void addFillTestData() {
    for (int i = 0; i < MAX_SIZE; i++) {
      array[i] = random.nextInt();
    }
  }

  public static void printArray(int[] array) {
    System.out.println();
    for (int i = 0; i < array.length; i++)
      System.out.print(" " + array[i] + " ");
    System.out.println();
  }

  public static void main(String args[]) {
    addFillTestData();

    System.out.println("Unsorted Array");
    printArray(array);
    long start = System.currentTimeMillis();
    (new MultiThreadedMergeSort()).mergeSort(0, array.length - 1, array);
    long end = System.currentTimeMillis();
    System.out.println("\n\nTime taken to sort = " + (end - start) + " milliseconds");
    System.out.println("Sorted Array");
    printArray(array);
  }

  public void mergeSort(int start, int end, int[] array) {

    if (end == start) {
      return;
    }

    final int mid = start + ((end - start) / 2);

    Thread worker1 = new Thread(() -> mergeSort(start, mid, array));
    Thread worker2 = new Thread(() -> mergeSort(mid + 1, end, array));

    worker1.start();
    worker2.start();

    try {
      worker2.join();
      worker1.join();
    } catch (InterruptedException ie) { }

    int i = start;
    int j = mid + 1;
    int k;
    for (k = start; k <= end; k++) {
      scratch[k] = array[k];
    }

    k = start;
    while (k <= end) {
      if (i <= mid && j <= end) {
        array[k] = Math.min(scratch[i], scratch[j]);

        if (array[k] == scratch[i]) {
          i++;
        } else {
          j++;
        }
      } else if (i <= mid && j > end) {
        array[k] = scratch[i];
        i++;
      } else {
        array[k] = scratch[j];
        j++;
      }
      k++;
    }
  }
}
