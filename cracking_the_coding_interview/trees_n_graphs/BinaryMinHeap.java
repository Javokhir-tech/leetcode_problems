package cracking_the_coding_interview.trees_n_graphs;

import java.util.Arrays;
import java.util.EmptyStackException;

public class BinaryMinHeap {

    private int heapSize;
    private int[] heap;

    public BinaryMinHeap(int capacity) {
        heapSize = 0;
        heap = new int[capacity + 1];
        Arrays.fill(heap, -1);
    }

    public void add(int value) {
        if (isFull()) throw new StackOverflowError();
        heap[heapSize++] = value;
        heapifyUp(heapSize - 1);
    }

    public void print() {
        for (int i = 0; i < heapSize; i++) {
            System.out.print(heap[i] + " ");
        }
    }

    /**
     * returns parents index
     * */
    public int parent(int x) {
        return (x - 1) / 2;
    }

    /**
     * compare child with its parent if its less than parent then swap
     * */
    public void heapifyUp(int x) {
        var temp = heap[x];
        while (x != 0 && heap[parent(x)] > temp) {
            heap[x] = heap[parent(x)];  // swap elements
            x = parent(x);              // swap roots
        }
        heap[x] = temp;
    }

    public int delete() {
        if (isEmpty()) throw new EmptyStackException();
        return heap[0];
    }

    public boolean isFull() {
        return heapSize == heap.length;
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    public static void main(String[] args) {
        var minHeap = new BinaryMinHeap(5);
        minHeap.add(3);
        minHeap.add(6);
        minHeap.add(2);         // 2 6 3
        minHeap.add(4);         // 2 4 3 6
        minHeap.add(1);         // 1 2 3 6 4
        minHeap.add(8);
        minHeap.print();
    }
}
