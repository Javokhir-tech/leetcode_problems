package multithreading.superman_singleton.lazy_init;

public class Superman {

  private static Superman superman;

  private Superman() { }

  public static Superman getInstance() {
    return SupermanHolder.superman;
  }

  private static class SupermanHolder {
    static Superman superman = new Superman();
  }

  public void fly() {
    System.out.println("I am Superman & I can fly !");
  }

  public static void main(String[] args) {
    for (int i = 0; i < 10; i++) {
      var superman = getInstance();
      superman.fly();
      System.out.println(superman);
    }
  }
}
