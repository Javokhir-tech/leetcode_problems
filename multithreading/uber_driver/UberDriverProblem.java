package multithreading.uber_driver;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class UberDriverProblem {
  private int republicans = 0;
  private int democrats = 0;

  private Semaphore demsWaiting = new Semaphore(0);
  private Semaphore repubsWaiting = new Semaphore(0);

  CyclicBarrier barrier = new CyclicBarrier(4);
  ReentrantLock lock = new ReentrantLock();

  void seatDemocrat() throws InterruptedException, BrokenBarrierException {
    boolean rideLeader = false;
    lock.lock();

    democrats++;

    if (democrats == 4) {
      // Seat all the democrats in the Uber ride.
      demsWaiting.release(3);
      democrats -= 4;
      rideLeader = true;
    } else if (democrats == 2 && republicans >= 2) {
      // Seat 2 democrats & 2 republicans
      demsWaiting.release(1);
      repubsWaiting.release(2);
      rideLeader = true;
      democrats -= 2;
      republicans -= 2;
    } else {
      lock.unlock();
      demsWaiting.acquire();
    }

    seated();
    barrier.await();

    if (rideLeader) {
      drive();
      lock.unlock();
    }
  }

  void seatRepublican() throws InterruptedException, BrokenBarrierException {
    boolean rideLeader = false;
    lock.lock();

    republicans++;

    if (republicans == 4) {
      // Seat all the republicans in the Uber ride.
      repubsWaiting.release(3);
      republicans -= 4;
      rideLeader = true;
    } else if (republicans == 2 && democrats >= 2) {
      // Seat 2 democrats & 2 republicans
      repubsWaiting.release(1);
      demsWaiting.release(2);
      rideLeader = true;
      republicans -= 2;
      democrats -= 2;
    } else {
      lock.unlock();
      repubsWaiting.acquire();
    }

    seated();
    barrier.await();

    if (rideLeader) {
      drive();
      lock.unlock();
    }
  }

  void seated() {
    System.out.println(Thread.currentThread().getName() + "  seated");
    System.out.flush();
  }

  void drive() {
    System.out.println("Uber Ride on Its wayyyy... with ride leader " + Thread.currentThread().getName());
    System.out.flush();
  }

  public static void main(String[] args) throws InterruptedException {
    final UberDriverProblem uberSeatingProblem = new UberDriverProblem();
    Set<Thread> allThreads = new HashSet<>();

    for (int i = 0; i < 10; i++) {

      Thread thread = new Thread(() -> {
        try {
          uberSeatingProblem.seatDemocrat();
        } catch (InterruptedException | BrokenBarrierException ie) {
          System.out.println("We have a problem");
        }
      });
      thread.setName("Democrat_" + (i + 1));
      allThreads.add(thread);

      Thread.sleep(50);
    }

    for (int i = 0; i < 14; i++) {
      Thread thread = new Thread(() -> {
        try {
          uberSeatingProblem.seatRepublican();
        } catch (InterruptedException | BrokenBarrierException ie) {
          System.out.println("We have a problem");
        }
      });
      thread.setName("Republican_" + (i + 1));
      allThreads.add(thread);
      Thread.sleep(20);
    }

    for (Thread t : allThreads) {
      t.start();
    }

    for (Thread t : allThreads) {
      t.join();
    }
  }
}
