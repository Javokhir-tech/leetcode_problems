package multithreading.async2sync;

public class Executor {
  public void asynchronousExecution(Callback callback) throws Exception {

    Thread t = new Thread(() -> {
      // Do some useful work
      try {
        Thread.sleep(5000);
      } catch (InterruptedException ie) {
        throw new RuntimeException();
      }
      callback.done();
    });
    t.start();
  }
}

interface Callback {
  void done();
}