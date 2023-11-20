package effective_java.item_8_finalizer;


public class FinalizerAttackExample {
  public static void main(String[] args) throws InterruptedException {
    try {
      new MaliciousSubclass();
    } catch(SecurityException ex) {
      System.out.println("wouldn't get hands on a ResourceClass instance");
    }
    System.gc();
    Thread.sleep(2000);
  }

  static class ResourceClass {
    ResourceClass() {
      if(!checkCaller()) throw new SecurityException();
    }
    public void criticalAction() {
      System.out.println("ResourceClass.criticalAction()");
    }
  }

  /** For our demonstration, all callers are invalid */
  static boolean checkCaller() {
    return false;
  }

  static class MaliciousSubclass extends ResourceClass {
    @Override
    protected void finalize() {
      System.out.println("see, I got hands on " + this);
      criticalAction();
    }
  }
}
