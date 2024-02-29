package multithreading.non_blockins_stack;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Demonstration {
  public static void main( String args[] ) throws Exception {

    NonBlockingStack<Integer> stack = new NonBlockingStack<>();
    ExecutorService executorService = Executors.newFixedThreadPool(20);
    int numThreads = 2;
    CyclicBarrier barrier = new CyclicBarrier(numThreads);

    long start = System.currentTimeMillis();
    Integer testValue = 51;

    try {
      for (int i = 0; i < numThreads; i++) {
        executorService.submit(() -> {
          for (int i1 = 0; i1 < 10000; i1++) {
            stack.push(testValue);
          }

          try {
            barrier.await();
          } catch (InterruptedException | BrokenBarrierException ex) {
            System.out.println("ignoring exception");
            //ignore both exceptions
          }

          for (int i1 = 0; i1 < 10000; i1++) {
            stack.pop();
          }
        });
      }
    } finally {
      executorService.shutdown();
      executorService.awaitTermination(1, TimeUnit.HOURS);
    }

    System.out.println("Number of elements in the stack = " + stack.size());
  }
}
