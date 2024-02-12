package multithreading.blocking_queue.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueueLock<T> {

  T[] array;
  Lock lock = new ReentrantLock();
  int size = 0;
  int capacity;
  int tail = 0;
  int head = 0;

  @SuppressWarnings("unchecked")
  public BlockingQueueLock(int capacity) {
    this.array = (T[]) new Object[capacity];
    this.capacity = capacity;
  }

  public void enqueue(T item) {

    lock.lock();
    while(size == capacity) {
      // Release the mutex to give other threads
      lock.unlock();
      // Reacquire the mutex before checking the
      // condition
      lock.lock();
    }

    if (tail == capacity) {
      tail = 0;
    }

    array[tail] = item;
    size++;
    tail++;

    lock.unlock();
  }

  public T dequeue() {
    lock.lock();
    while (size == 0) {
      lock.unlock();
      lock.lock();
    }

    if (head == capacity) {
      head = 0;
    }

    var item = array[head];
    array[head] = null;
    head++;
    size--;
    lock.unlock();

    return item;
  }
}
