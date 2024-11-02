package multithreading.AtomicInteger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * To demonstrate the performance of AtomicInteger we can construct a crude test,
 * where a counter is incremented a million times by ten threads to reach a total of ten million.
 * We’ll time the run for an AtomicInteger counter and an ordinary int counter.
 * */
public class AtomicIntegerPerfTest {
  static AtomicInteger counter = new AtomicInteger(0);
  static int simpleCounter = 0;

  public static void main( String args[] ) throws Exception {
    test(true);
    test(false);
  }

  synchronized static void incrementSimpleCounter() {
    simpleCounter++;
  }

  static void test(boolean isAtomic) throws Exception {
    ExecutorService executorService = Executors.newFixedThreadPool(10);
    long start = System.currentTimeMillis();

    try {
      for (int i = 0; i < 10; i++) {
        executorService.submit(() -> {
          for (int i1 = 0; i1 < 1000000; i1++) {
            if (isAtomic) {
              counter.incrementAndGet();
            } else {
              incrementSimpleCounter();
            }
          }
          System.out.println(Thread.currentThread().getName() + " finished");
        });
      }
    } finally {
      executorService.shutdown();
      executorService.awaitTermination(1, TimeUnit.HOURS);
    }

    long timeTaken = System.currentTimeMillis() - start;
    System.out.println("Time taken by " + (isAtomic ? "atomic integer counter " : "integer counter ") + timeTaken + " milliseconds.");
  }
}
