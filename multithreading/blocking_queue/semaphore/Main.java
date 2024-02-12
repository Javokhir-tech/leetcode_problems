package multithreading.blocking_queue.semaphore;


public class Main {

  public static void main(String[] args) throws InterruptedException {
    BlockingQueueSemaphore<Integer> blockingQueue = new BlockingQueueSemaphore<>(5);

    Thread producer = new Thread(() -> {
      for (int i = 0; i < 100; i++) {
        try {
          blockingQueue.enqueue(i);
          System.out.println("enqueued: " + i);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    });

    Thread consumer2 = new Thread(() -> {
      for (int i = 0; i < 30; i++) {
        try {
          System.out.println("thread 2 dequeued: " + blockingQueue.dequeue());
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    });

    Thread consumer3 = new Thread(() -> {
      for (int i = 0; i < 70; i++) {
        try {
          System.out.println("thread 3 dequeued: " + blockingQueue.dequeue());
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    });

    producer.start();
    Thread.sleep(3000);
    consumer2.start();
    consumer2.join();
    Thread.sleep(1000);
    consumer3.start();
    producer.join();
    consumer3.join();
  }
}

