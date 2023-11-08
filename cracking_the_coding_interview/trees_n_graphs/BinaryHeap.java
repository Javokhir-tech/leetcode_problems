package cracking_the_coding_interview.trees_n_graphs;

import java.util.Arrays;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;

public class BinaryHeap {
    private static final int d = 2;
    private int[] heap;
    private int heapSize;

    /**
     * This will initialize our heap with default size.
     */
    public BinaryHeap(int capacity) {
        heapSize = 0;
        heap = new int[capacity + 1];
        Arrays.fill(heap, -1);

    }

    /**
     *  This will check if the heap is empty or not
     *  Complexity: O(1)
     */
    public boolean isEmpty() {
        return heapSize == 0;
    }

    /**
     *  This will check if the heap is full or not
     *  Complexity: O(1)
     */
    public boolean isFull() {
        return heapSize == heap.length;
    }


    private int parent(int i) {
        return (i - 1) / d;
    }

    private int kthChild(int i, int k) {
        return d * i + k;
    }

    /**
     *  This will insert new element in to heap
     *  Complexity: O(log N)
     *  As worst case scenario, we need to traverse till the root
     */
    public void insert(int x) {
        if(isFull())
            throw new NoSuchElementException("Heap is full, No space to insert new element");
        heap[heapSize++] = x;
        heapifyUp(heapSize - 1);
    }

    /**
     *  This will delete element at index x
     *  Complexity: O(log N)
     *
     */
    public int delete(int x) {
        if(isEmpty())
            throw new NoSuchElementException("Heap is empty, No element to delete");
        int key = heap[x];
        heap[x] = heap[heapSize - 1];
        heapSize--;
        heapifyDown(x);
        return key;
    }

    /**
     *  This method used to maintain the heap property while inserting an element.
     *
     */
    private void heapifyUp(int i) {
        int temp = heap[i];
        while(i > 0 && temp > heap[parent(i)]) {
            heap[i] = heap[parent(i)];
            i = parent(i);
        }
        heap[i] = temp;
    }

    /**
     *  This method used to maintain the heap property while deleting an element.
     *
     */
    private void heapifyDown(int i) {
        int child;
        int temp = heap[i];
        while(kthChild(i, 1) < heapSize) {
            child = maxChild(i);
            if (temp < heap[child]) { heap[i] = heap[child]; }
            else break;
            i = child;
        }
        heap[i] = temp;
    }
    private int maxChild(int i) {
        int leftChild = kthChild(i, 1);
        int rightChild = kthChild(i, 2);
        return heap[leftChild] > heap[rightChild] ? leftChild : rightChild;
    }

    /**
     *  This method used to print all element of the heap
     *
     */
    public void printHeap() {
        System.out.print("nHeap = ");
        for (int i = 0; i < heapSize; i++)
            System.out.print(heap[i] + " ");
        System.out.println();
    }

    /**
     *  This method returns the max element of the heap.
     *  complexity: O(1)
     */
    public int findMax() {
        if(isEmpty())
            throw new NoSuchElementException("Heap is empty.");
        return heap[0];
    }


    /**
     * A Binary Heap is a data structure used to efficiently store and retrieve data, based on a tree-like structure.
     * It is a complete binary tree, meaning all levels, except possibly the last one [1], are completely filled.
     * The heap ordering property is also satisfied [2], which states that for any node in the heap,
     * the value of the parent node is either greater than or equal to (for a max heap) or less than or equal to (for a min heap) the values of its children.
     * Binary heaps can be used to implement priority queues and can be used for sorting algorithms.
     * */

    public static void usingPriorityQ() {
        // Create a Max Heap
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        // Add elements to the heap
        maxHeap.add(1);
        maxHeap.add(5);
        maxHeap.add(3);
        maxHeap.add(8);
        maxHeap.add(6);

        // Get the maximum element from the heap
        int maxElement = maxHeap.peek();
        System.out.println(maxElement);
    }

    public static void main(String[] args) {

        BinaryHeap maxHeap = new BinaryHeap(10);
        maxHeap.insert(4);
        maxHeap.insert(10);
        maxHeap.insert(9);
        maxHeap.insert(1);
        maxHeap.insert(7);
        maxHeap.insert(5);
        maxHeap.insert(3);

        maxHeap.printHeap();
    }
}
