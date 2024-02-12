package multithreading.rate_limiting.daemon;

public class TokenBucketFilter {

  private final int MAX_TOKENS;
  private final int ONE_SEC = 1000;
  private long possibleTokens = 0;

  private TokenBucketFilter(int maxTokens) {
    MAX_TOKENS = maxTokens;
  }

  public void getToken() throws InterruptedException {
    synchronized (this) {
      while (possibleTokens == 0) {
        wait();
      }
      possibleTokens--;
    }
    System.out.println(
      "Granting " + Thread.currentThread().getName() + " token at " + System.currentTimeMillis() / ONE_SEC);
  }

  private void daemonThread() {
    while (true) {
      synchronized (this) {
        if (possibleTokens < MAX_TOKENS) {
          possibleTokens++;
        }
        notify();
      }
      try {
        Thread.sleep(ONE_SEC);
      } catch (InterruptedException ie) {
        throw new RuntimeException(ie.getMessage());
      }
    }
  }

  void initialize() {
    // Never start a thread in a constructor
    Thread dt = new Thread(this::daemonThread);
    dt.setDaemon(true);
    dt.start();
  }

  public static TokenBucketFilter makeTokenBucketFilter(int capacity) {
    TokenBucketFilter tbf = new TokenBucketFilter(capacity);
    tbf.initialize();
    return tbf;
  }
}
