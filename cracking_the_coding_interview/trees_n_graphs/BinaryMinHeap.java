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
            heap[x] = heap[parent(x)];  // swap elements | set child to parent
            x = parent(x);              // move to next kid
        }
        heap[x] = temp;
    }

    /**
     * delete element by index
     * */
    public int delete(int i) {
        if (isEmpty()) throw new EmptyStackException();
        var temp = heap[i];
        heap[i] = heap[heapSize - 1];
        heapSize--;
        heapifyDown(i);
        return temp;
    }

    public void heapifyDown(int i) {
        int child;
        int top = heap[i];
        while(kThElement(i, 1) < heapSize) {    // iterate while parent have no more kids
            child = minElement(i);                  // get the smallest kid
            if (top > heap[child]) { heap[i] = heap[child]; }
            else break;
            i = child;  // move to next kid
        }
        heap[i] = top;
    }

    private int minElement(int i) {
        var leftChild = kThElement(i, 1);
        var rightChild = kThElement(i, 2);
        return heap[leftChild] < heap[rightChild] ? leftChild : rightChild;
    }

    private int kThElement(int i, int k) {
        return 2 * i + k;
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
        minHeap.add(8);         // 1 2 3 6 4 8

        minHeap.delete(1);
//        minHeap.delete(0);  // 2 4 3 6 8
        minHeap.print();
    }
}
