package multithreading.blocking_queue.monitor;

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

    // wait for queue to have space
    while(size == capacity) {
      wait();
    }

    // reset tail to the beginning if the tail is already
    // at the end of the backing array
    if (tail == capacity) {
      tail = 0;
    }

    // place the item in the array
    array[tail] = item;
    size++;
    tail++;

    // don't forget to notify any other threads waiting on
    // a change in value of size. There might be consumers
    // waiting for the queue to have atleast one element
    notifyAll();
  }

  public synchronized T dequeue() throws InterruptedException {
    // wait for atleast one item to be enqueued
    while (size == 0) {
      wait();
    }

    // reset head to start of array if its past the array
    if (head == capacity) {
      head = 0;
    }

    // store the reference to the object being dequeued
    // and overwrite with null
    var item = array[head];
    array[head] = null;
    head++;
    size--;

    // don't forget to call notify, there might be another thread
    // blocked in the enqueue method.
    notifyAll();

    return item;
  }
}
