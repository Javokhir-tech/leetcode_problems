package multithreading.exchanger;

import java.util.concurrent.*;

class Demonstration {

  static Exchanger<String> exchanger = new Exchanger<>();

  public static void main( String args[] ) throws InterruptedException {
    ExecutorService executorService = Executors.newFixedThreadPool(10);

    try {
      for (int i = 0; i < 10; i++) {
        executorService.submit(() -> work());
      }

    } finally {
      executorService.shutdown();
      executorService.awaitTermination(1, TimeUnit.HOURS);
    }
  }

  static void work() {
    String name = Thread.currentThread().getName();
    String received = "";

    try {
      received = exchanger.exchange(name);
    } catch (InterruptedException ie) {
      // ignore for now
    }

    System.out.println("I am thread " + name + " and I received the string " + received);
  }
}
