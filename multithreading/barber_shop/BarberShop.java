package multithreading.barber_shop;

import java.util.concurrent.locks.ReentrantLock;

public class BarberShop {

  final int CHAIRS = 3;
  int waitingCustomers = 0;
  ReentrantLock lock = new ReentrantLock();

  void customerWalksIn() throws InterruptedException {

  }

  void barber() throws InterruptedException {
  }
}
