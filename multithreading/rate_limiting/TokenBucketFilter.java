package multithreading.rate_limiting;

import java.util.HashSet;
import java.util.Set;

public class TokenBucketFilter {

    private final int MAX_TOKENS;
    // variable to note down the latest token request.
    private long lastRequestTime = System.currentTimeMillis();
    private long possibleTokens = 0;
    private final int rate;

    public TokenBucketFilter(int rate, int maxTokens) {
        MAX_TOKENS = maxTokens;
        this.rate = rate;
    }

    synchronized void getToken() throws InterruptedException {
        long currentTime = System.currentTimeMillis();
        long timeSinceLastRequest = currentTime - lastRequestTime;

        // Calculate the number of tokens to add, based on the time since the last request
        possibleTokens += (timeSinceLastRequest / 1000) * rate;

        if (possibleTokens > MAX_TOKENS) {
            possibleTokens = MAX_TOKENS;
        }

        if (possibleTokens == 0) {
            Thread.sleep(1000 - (timeSinceLastRequest % 1000)); // Wait until the next token is added
        } else {
            possibleTokens--;
        }
        lastRequestTime = currentTime; // Update the last request time

        System.out.println("Granting " + Thread.currentThread().getName() + " token at " + (currentTime / 1000));
    }

    public static void main(String[] args) throws InterruptedException {
        Set<Thread> allThreads = new HashSet<>();
        final TokenBucketFilter tokenBucketFilter = new TokenBucketFilter(2, 12);

        Thread.sleep(4000);

        for (int i = 0; i < 12; i++) {
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
