package multithreading.dining_philosophers;

import java.util.Random;
import multithreading.semaphore.Semaphore;

public class DiningPhilosophers {

  // This random variable is used for test purporses only
  private static Random random = new Random(System.currentTimeMillis());
  // Five semaphore represent the five forks.
  private Semaphore[] forks = new Semaphore[5];
  private Semaphore maxDiners = new Semaphore(4);

  // Initializing the semaphores with a permit of 1
  public DiningPhilosophers() {
    forks[0] = new Semaphore(1);
    forks[1] = new Semaphore(1);
    forks[2] = new Semaphore(1);
    forks[3] = new Semaphore(1);
    forks[4] = new Semaphore(1);
  }

  // Represents how a philosopher lives his life
  public void lifecycleOfPhilosopher(int id) throws InterruptedException {
    while (true) {
      contemplate();
      eat(id);
    }
  }

  // We can sleep the thread when the philosopher is thinking
  void contemplate() throws InterruptedException {
    Thread.sleep(random.nextInt(500));
  }

  // This method will have the meat of the solution, where the
  // philosopher is trying to eat.
  void eat(int id) throws InterruptedException {
    // maxDiners allows only 4 philosphers to
    // attempt picking up forks.
    maxDiners.acquire();

    forks[id].acquire();
    forks[(id + 1) % 5].acquire();
    System.out.println("Philosopher " + id + " is eating");
    forks[id].release();
    forks[(id + 1) % 5].release();

    maxDiners.release();
  }

  static void startPhilosoper(DiningPhilosophers dp, int id) {
    try {
      dp.lifecycleOfPhilosopher(id);
    } catch (InterruptedException ie) { }
  }

  public static void main(String[] args) throws InterruptedException {
    final DiningPhilosophers dp = new DiningPhilosophers();

    Thread p1 = new Thread(() -> startPhilosoper(dp, 0));
    Thread p2 = new Thread(() -> startPhilosoper(dp, 1));
    Thread p3 = new Thread(() -> startPhilosoper(dp, 2));
    Thread p4 = new Thread(() -> startPhilosoper(dp, 3));
    Thread p5 = new Thread(() -> startPhilosoper(dp, 4));

    p1.start();
    p2.start();
    p3.start();
    p4.start();
    p5.start();

    p1.join();
    p2.join();
    p3.join();
    p4.join();
    p5.join();
  }
}
