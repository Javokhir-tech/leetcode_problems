package multithreading.deferred_callback;

import java.util.HashSet;
import java.util.Set;

public class Main {
  public static void main(String[] args) throws InterruptedException {
    runLateThenEarlyCallback();
  }


  public static void runLateThenEarlyCallback() throws InterruptedException {
    final DeferredCallbackExecutor deferredCallbackExecutor = new DeferredCallbackExecutor();

    Thread service = new Thread(() -> {
      try {
        deferredCallbackExecutor.start();
      } catch (InterruptedException ie) {
      }
    });

    service.start();

    Thread lateThread = new Thread(() -> {
      DeferredCallbackExecutor.CallBack
        cb = new DeferredCallbackExecutor.CallBack(8, "Hello this is the callback submitted first");
      deferredCallbackExecutor.registerCallback(cb);
    });
    lateThread.start();

    Thread.sleep(3000);

    Thread earlyThread = new Thread(() -> {
      DeferredCallbackExecutor.CallBack
        cb = new DeferredCallbackExecutor.CallBack(1, "Hello this is callback sumbitted second");
      deferredCallbackExecutor.registerCallback(cb);
    });
    earlyThread.start();

    lateThread.join();
    earlyThread.join();
  }

  public static void runTestTenCallbacks() throws InterruptedException {
    Set<Thread> allThreads = new HashSet<Thread>();
    final DeferredCallbackExecutor deferredCallbackExecutor = new DeferredCallbackExecutor();

    Thread service = new Thread(() -> {
      try {
        deferredCallbackExecutor.start();
      } catch (InterruptedException ie) {

      }
    });

    service.start();

    for (int i = 0; i < 10; i++) {
      Thread thread = new Thread(() -> {
        DeferredCallbackExecutor.CallBack
          cb = new DeferredCallbackExecutor.CallBack(1, "Hello this is " + Thread.currentThread().getName());
        deferredCallbackExecutor.registerCallback(cb);
      });
      thread.setName("Thread_" + (i + 1));
      thread.start();
      allThreads.add(thread);
      Thread.sleep(1000);
    }

    for (Thread t : allThreads) {
      t.join();
    }
  }
}
