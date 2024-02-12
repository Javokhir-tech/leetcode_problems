package multithreading.blocking_queue.monitor;

public class Main {

  public static void main(String[] args) throws InterruptedException {

    BlockingQueue<Integer> blockingQueue = new BlockingQueue<>(5);

    Thread thread1 = new Thread(() -> {
      for (int i = 0; i < 10; i++) {
        try {
          blockingQueue.enqueue(i);
          System.out.println("enqueued: " + i);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    });

    Thread thread2 = new Thread(() -> {
      for (int i = 0; i < 3; i++) {
        try {
          var item = blockingQueue.dequeue();
          System.out.println("thread 2 dequeud: " + item);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    });

    Thread thread3 = new Thread(() -> {
      for (int i = 0; i < 7; i++) {
        try {
          var item = blockingQueue.dequeue();
          System.out.println("thread 3 dequeud: " + item);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    });

    thread1.start();
    Thread.sleep(3000);
    thread2.start();
    thread2.join();
    Thread.sleep(1000);
    thread3.start();
    thread1.join();
    thread3.join();
  }
}
