package multithreading.print_foo_bar;

public class FooBar {

  private int n;
  private int flag = 0;

  public FooBar(int n) {
    this.n = n;
  }

  public void printFoo() {
    for (int i = 1; i <= n;  i++) {
      synchronized (this) {
        while (flag == 0) {
          try {
            this.wait();
          } catch (InterruptedException ie) {
            System.out.println("interrupted");
          }
        }
        System.out.print("Foo");
        flag = 0;
        this.notifyAll();
      }
    }
  }

  public void printBar() {
    for (int i = 1; i <= n;  i++) {
      synchronized (this) {
        while (flag == 1) {
          try {
            this.wait();
          } catch (InterruptedException ie) {
            System.out.println("interrupted");
          }
        }
        System.out.print("Bar");
        flag = 1;
        this.notifyAll();
      }
    }
  }

  public static class FooBarThread extends Thread {
    FooBar fooBar;
    String method;

    public FooBarThread(FooBar fooBar, String method){
      this.fooBar = fooBar;
      this.method = method;
    }

    @Override
    public void run() {
      if ("foo".equals(method)) {
        fooBar.printFoo();
      }
      else if ("bar".equals(method)) {
        fooBar.printBar();
      }
    }
  }

  public static void main(String[] args) {
    FooBar fooBar = new FooBar(5);

    Thread t1 = new FooBarThread(fooBar,"foo");
    Thread t2 = new FooBarThread(fooBar,"bar");

    t2.start();
    t1.start();
  }
}
