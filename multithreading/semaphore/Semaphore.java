package multithreading.semaphore;

public class Semaphore {

  private int usedPermits = 0;
  private final int maxPermits;

  public Semaphore(int maxPermits) {
    this.maxPermits = maxPermits;
  }

  public synchronized void acquire() throws InterruptedException {
    while (usedPermits == maxPermits) {
//      System.out.println("acquire wait");
    }

    usedPermits++;
    notify();
  }

  public synchronized void release() throws InterruptedException {
    while (usedPermits == 0) {
      System.out.println("wait");
    }

    usedPermits--;
    notify();
  }

  public static void main(String[] args) throws InterruptedException {
    var semaphore = new Semaphore(1);

    var ping = new Thread(()-> {
      for (int i = 0; i < 5; i++) {
        try {
          semaphore.acquire();
          System.out.println("Ping " + i);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    });

    var pong = new Thread(() -> {
      for (int i = 0; i < 5; i++) {
        try {
          semaphore.release();
          System.out.println("Pong " + i);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    });

    ping.start();
    pong.start();

    ping.join();
    pong.join();
  }
}
