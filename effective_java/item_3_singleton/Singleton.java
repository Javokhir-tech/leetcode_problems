package effective_java.item_3_singleton;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Constructor;

class Singleton implements Serializable {
  public static final Singleton INSTANCE = new Singleton();
  private Singleton() {}

  // implement readResolve method to maintain uniqueness in serialization
  protected Object readResolve() { return INSTANCE; }
}

/**
 * Breaking a singleton pattern with reflection.
 * Solution: use single enums
 */
class MainReflection {
  public static void main(String[] args) {
    Singleton instance = Singleton.INSTANCE;
    Singleton instance2 = null;

    try {
      Constructor[] constructors = Singleton.class.getDeclaredConstructors();
      for (Constructor constructor : constructors) {
        constructor.setAccessible(true);  // with this method we can make break singleton pattern
        instance2 = (Singleton) constructor.newInstance();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("instance 1 hashcode: " + instance.hashCode());
    System.out.println("instance 2 hashcode: " + instance2.hashCode());
  }
}

/**
 * Breaking a singleton pattern with serialization.
 * Solution: implement readResolve() in singleton class
 */
class MainSerializable {
  public static void main(String[] args) {
    try {
      Singleton singleton = Singleton.INSTANCE;
      var out = new ObjectOutputStream(new FileOutputStream("file.txt"));
      out.writeObject(singleton);
      out.close();

      // de-serialize
      var in = new ObjectInputStream(new FileInputStream("file.txt"));
      Singleton singleton2 = (Singleton) in.readObject();
      in.close();

      System.out.println("singleton 1 hashcode: " + singleton.hashCode());
      System.out.println("singleton 2 hashcode: " + singleton2.hashCode());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
