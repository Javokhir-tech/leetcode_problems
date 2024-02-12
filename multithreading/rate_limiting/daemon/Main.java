package multithreading.rate_limiting.daemon;

import java.util.HashSet;
import java.util.Set;

public class Main {
  public static void main(String[] args) throws InterruptedException {
    Set<Thread> allThreads = new HashSet<>();
    var tokenBucketFilter = TokenBucketFilter.makeTokenBucketFilter(4);

    for (int i = 0; i < 10; i++) {
      Thread thread = new Thread(() -> {
        try {
          tokenBucketFilter.getToken();
        } catch (InterruptedException ie) {
          System.out.println("We have a problem");
        }
      });
      thread.setName("Thread_" + (i + 1));
      allThreads.add(thread);
    }

    for (Thread t : allThreads) {
      t.start();
    }

    for (Thread t : allThreads) {
      t.join();
    }
  }
}
