package leetcodes.ds;

public class DynamicArray {
  private int capacity;
  private int size;
  private int[] arr;

  // O(n) space
  public DynamicArray(int capacity) {
    this.capacity = capacity;
    this.arr = new int[capacity];
    this.size = 0;
  }

  // O(n) avg amortized time O(1)
  public void pushBack(int value) {
    if (size == capacity) {
      resize(); // increasing the capacity by 2x
    }
    arr[size++] = value;
  }

  // O(1)
  public int pop() {
    validateDelete();
    int item = arr[--size];
    arr[size] = 0;
    return item;
  }

  // O(1)
  public void replaceAt(int index, int value) {
    validateInput(index);
    arr[index] = value;
  }

  // O(n)
  public int deleteAt(int index) {
    validateInput(index);
    validateDelete();
    int item = arr[index];
    unShift(index);
    size--;
    return item;
  }

  public void insertAt(int index, int value) {
    validateInput(index);

    if (size >= capacity) {
      resize(); // increasing the capacity by 2x
    }
    size++;
    shift(index);
    arr[index] = value;

  }

  private void validateInput(int index) {
    if (index >= size || index < 0) {
      throw new IllegalArgumentException("size: " + size  + " index: " + index);
    }
  }

  private void validateDelete() {
    if (size == 0) {
      throw new NegativeArraySizeException("no item left to pop out");
    }
  }

  private void shift(int index) { // from left to right
    for (int i = size - 1; i >= index; i--) {
      arr[i] = arr[i - 1];
    }
  }

  private void unShift(int index) { // from right to left
    for (int i = index; i < size - 1; i++) {
      arr[i] = arr[i + 1];
    }
  }

  public int getCapacity() {
    return this.capacity;
  }

  public int getSize() {
    return this.size;
  }

  private void resize() {
    this.capacity = capacity * 2;
    int[] new_array = new int[capacity];
    for (int i = 0; i < size; i++) {
      new_array[i] = arr[i];
    }
    arr = new_array;
  }

  public void printValues() {
    for (int i = 0; i < this.size; i++) {
      System.out.println(arr[i]);
    }
  }

  public static void main(String[] args) {
    DynamicArray array = new DynamicArray(6);
    int[] values = {1, 4, 3, 7, 6, 8};
    for (int value : values) {
      array.pushBack(value);
    }
    array.insertAt(2, 9);
    array.insertAt(5, 20);
    array.insertAt(7, 10);
    array.printValues();
  }



}
