package multithreading.blocking_queue.semaphore;

public class CountingSemaphore {

  int count;
  int usedPermits = 0;

  public CountingSemaphore(int count) {
    this.count = count;
  }

  public CountingSemaphore(int count, int initPermits) {
    this.count = count;
    this.usedPermits = count - initPermits;
  }

  public synchronized void acquire() throws InterruptedException {
    while(usedPermits == count) {
      wait();
    }
    notify();
    usedPermits++;
  }

  public synchronized void release() throws InterruptedException {
    while (usedPermits == 0) {
      wait();
    }
    usedPermits--;
    notify();
  }
}
