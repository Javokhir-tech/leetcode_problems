package multithreading.read_write_lock;

public class ReadWriteLock {

  private boolean isWriteLocked = false;
  private int readers = 0;

  ReadWriteLock() { }

  public synchronized  void acquireReadLock() throws InterruptedException {
    while (isWriteLocked) {
      wait();
    }
    readers++;
  }

  public synchronized void releaseReadLock() {
    readers--;
    notify();
  }

  public synchronized void acquireWriteLock() throws InterruptedException {
    while (!isWriteLocked || readers != 0) {
      wait();
    }
    isWriteLocked = true;
  }

  public synchronized void releaseWriteLock() {
    isWriteLocked = false;
    notify();
  }

  public static void main(String[] args) throws InterruptedException {
    var rwl = new ReadWriteLock();

    var w1 = new Thread(() -> {
      try {
        System.out.println("Attempting to acquire write lock in w1: " + System.currentTimeMillis());
        rwl.acquireWriteLock();
        System.out.println("write lock acquired w1: " + +System.currentTimeMillis());

        // Simulates write lock being held indefinitely
        for (; ; ) {
          Thread.sleep(500);
        }
      } catch (InterruptedException ie) { }
    });
    var w2 = new Thread(() -> {
      try {
        System.out.println("Attempting to acquire write lock in w2: " + System.currentTimeMillis());
        rwl.acquireWriteLock();
        System.out.println("write lock acquired w2: " + System.currentTimeMillis());
      } catch (InterruptedException ie) { }
    });
    var r1 = new Thread(() -> {
      try {
        rwl.acquireReadLock();
        System.out.println("Read lock acquired: " + System.currentTimeMillis());

      } catch (InterruptedException ie) {

      }
    });
    var r2 = new Thread(() -> {
      System.out.println("Read lock about to release: " + System.currentTimeMillis());
      rwl.releaseReadLock();
      System.out.println("Read lock released: " + System.currentTimeMillis());
    });

    r1.start();
    w1.start();
    Thread.sleep(3000);
    r2.start();
    Thread.sleep(1000);
    w2.start();
    r1.join();
    r2.join();
    w2.join();
  }
}
