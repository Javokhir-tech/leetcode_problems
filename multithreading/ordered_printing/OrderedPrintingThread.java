package multithreading.ordered_printing;

public class OrderedPrintingThread extends Thread {
  private OrderedPrinting obj;
  private String method;

  public OrderedPrintingThread(OrderedPrinting obj, String method) {
    this.method = method;
    this.obj = obj;
  }
  public void run() {
    //for printing "First"
    if ("first".equals(method)) {
      obj.printFirst();
    }
    //for printing "Second"
    else if ("second".equals(method)) {
      try {
        obj.printSecond();
      }
      catch(InterruptedException e) {

      }
    }
    //for printing "Third"
    else if ("third".equals(method)) {
      try {
        obj.printThird();
      }
      catch(InterruptedException e) {

      }
    }
  }

  public static void main(String[] args) {
    OrderedPrinting obj = new OrderedPrinting();

    OrderedPrintingThread t1 = new OrderedPrintingThread(obj, "first");
    OrderedPrintingThread t2 = new OrderedPrintingThread(obj, "second");
    OrderedPrintingThread t3 = new OrderedPrintingThread(obj, "third");

    t2.start();
    t3.start();
    t1.start();
  }
}
