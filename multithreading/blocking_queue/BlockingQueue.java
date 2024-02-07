package multithreading.blocking_queue;

public class BlockingQueue<T> {

  T[] array;
  int size = 0;
  int capacity;
  int tail = 0;
  int head = 0;

  @SuppressWarnings("unchecked")
  public BlockingQueue(int capacity) {
    this.array = (T[]) new Object[capacity];
    this.capacity = capacity;
  }

  public synchronized void enqueue(T item) throws InterruptedException {

    while(size == capacity) {
      wait();
    }

    if (tail == capacity) {
      tail = 0;
    }

    array[tail] = item;
    size++;
    tail++;
    notifyAll();
  }

  public synchronized T dequeue() throws InterruptedException {
    while (size == 0) {
      wait();
    }

    if (head == capacity) {
      head = 0;
    }

    var item = array[head];
    array[head] = null;
    head++;
    size--;
    notifyAll();
    return item;
  }
}
