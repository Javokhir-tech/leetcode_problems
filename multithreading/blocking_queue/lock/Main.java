package multithreading.blocking_queue.lock;

public class Main {

  public static void main(String[] args) throws InterruptedException {
    BlockingQueueLock<Integer> blockingQueue = new BlockingQueueLock<>(5);

    Thread thread1 = new Thread(() -> {
      for (int i = 0; i < 100; i++) {
        blockingQueue.enqueue(i);
        System.out.println("enqueued: " + i);
      }
    });

    Thread thread2 = new Thread(() -> {
      for (int i = 0; i < 30; i++) {
        var item = blockingQueue.dequeue();
        System.out.println("thread 2 dequeued: " + item);
      }
    });

    Thread thread3 = new Thread(() -> {
      for (int i = 0; i < 70; i++) {
        var item = blockingQueue.dequeue();
        System.out.println("thread 3 dequeued: " + item);
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
