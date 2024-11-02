package multithreading.multithreaded_fizzbuzz;

public class FizzBuzz {
  private int n;
  private int num = 1;

  public FizzBuzz(int n) {
    this.n = n;
  }

  public synchronized void fizz() throws InterruptedException {
    while (num <= n) {
      if (num % 3 == 0 && num % 5 != 0) {
        System.out.println("Fizz");
        num++;
        notifyAll();
      } else {
        wait();
      }
    }
  }

  public synchronized void buzz() throws InterruptedException {
    while (num <= n) {
      if (num % 5 == 0 && num % 3 != 0) {
        System.out.println("Buzz");
        num++;
        notifyAll();
      } else {
        wait();
      }
    }
  }

  public synchronized void fizzbuzz() throws InterruptedException {
    while (num <= n) {
      if (num % 3 == 0 && num % 5 == 0) {
        System.out.println("fizzbuzz");
        num++;
        notifyAll();
      } else {
        wait();
      }
    }
  }

  public synchronized void number() throws InterruptedException {
    while (num <= n) {
      if (num % 3 != 0 && num % 5 != 0) {
        System.out.println(num);
        num++;
        notifyAll();
      } else {
        wait();
      }
    }
  }

  static class FizzBuzzThread extends Thread {
    private FizzBuzz fizzBuzz;
    String method;

    public FizzBuzzThread(FizzBuzz fizzBuzz, String method) {
      this.fizzBuzz = fizzBuzz;
      this.method = method;
    }

    public void run() {
      if ("Fizz".equals(method)) {
        try {
          fizzBuzz.fizz();
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      } else if ("Buzz".equals(method)) {
        try {
          fizzBuzz.buzz();
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      } else if ("FizzBuzz".equals(method)) {
        try {
          fizzBuzz.fizzbuzz();
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      } else {
        try {
          fizzBuzz.number();
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    }
  }

  public static void main(String[] args) {
    FizzBuzz obj = new FizzBuzz(15);

    Thread t1 = new FizzBuzzThread(obj,"Fizz");
    Thread t2 = new FizzBuzzThread(obj,"Buzz");
    Thread t3 = new FizzBuzzThread(obj,"FizzBuzz");
    Thread t4 = new FizzBuzzThread(obj,"Number");

    t2.start();
    t1.start();
    t4.start();
    t3.start();
  }
}
