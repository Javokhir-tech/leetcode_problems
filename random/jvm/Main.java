package random.jvm;

public class Main {

  public static void main(String[] args) {
    // Get the Application Class Loader
    ClassLoader appClassLoader = ClassLoader.getSystemClassLoader();
    // Load a class using the Application Class Loader
//    ModuleLayer

    try {

      Class<?> clazz = appClassLoader.loadClass("java.util.ArrayList");
      System.out.println("Loaded class: " + clazz.getName());
    } catch (ClassNotFoundException e) {
      System.out.println("Class not found: " + e.getMessage());
    }
  }

  public class BootstrapClassLoaderExample {

    public static void main(String[] args) {
      // Get the class loader for the String class, loaded by the Bootstrap Class Loader
      ClassLoader loader = String.class.getClassLoader();

      // Print the class loader's name
      System.out.println("Class loader for String class: " + loader);
    }
  }

  public class ExtensionClassLoaderExample {
    public static void main(String[] args) {
      // javax.swing.JFrame class is loaded by the Extension Class Loader
      ClassLoader loader = javax.swing.JFrame.class.getClassLoader();
      System.out.println("Class loader for JFrame class: " + loader);

      // org.xml.sax.helpers.DefaultHandler class is also loaded by the Extension Class Loader
      ClassLoader loader2 = org.xml.sax.helpers.DefaultHandler.class.getClassLoader();
      System.out.println("Class loader for DefaultHandler class: " + loader2);
    }
  }

  public class ApplicationClassLoaderExample {
    public static void main(String[] args) {
      // Get the Application Class Loader
      ClassLoader appClassLoader = ClassLoader.getSystemClassLoader();

      // Load a class using the Application Class Loader
      try {
        Class<?> clazz = appClassLoader.loadClass("java.util.ArrayList");
        System.out.println("Loaded class: " + clazz.getName());
      } catch (ClassNotFoundException e) {
        System.out.println("Class not found: " + e.getMessage());
      }
    }
  }

  public abstract class Person implements Comparable<Person> {

    int age;
    String firstName;
    String lastName;
    String middleName;

    public Person() {

    }

    public Person(int age, String firstName, String middleName, String lastName) {
      this.age = age;
      this.firstName = firstName;
      this.middleName = middleName;
      this.lastName = lastName;
    }

    // Abstract method must be implemented by the subclass.
    abstract void printFullName();

    // Default implementation for defaultAge
    void printAge() {
      System.out.println();
    }
  }

  public class Uzbek extends Person {

    @Override
    public int compareTo(Person o) {
      return 0;
    }

    @Override
    void printFullName() {
      System.out.println("name");
    }
  }
}


