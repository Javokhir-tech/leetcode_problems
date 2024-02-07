package random.jvm;

import java.sql.DriverManager;
import java.util.ArrayList;

public class PrintClassLoaders {

  public static void main(String args[]) {

    System.out.println("java.ext.dirs: "
      + System.getProperty("java.ext.dirs")); // removed from in java v9+.

    System.out.println("Classloader of ArrayList: "
      + ArrayList.class.getClassLoader()); // bootstrap

    System.out.println("Classloader of String: "
      + String.class.getClassLoader()); // bootstrap

    System.out.println("Classloader of this class: "
      + PrintClassLoaders.class.getClassLoader()); //jdk.internal.loader.ClassLoaders$AppClassLoader@1d44bcfa

    System.out.println("Classloader of DriverManager: "
      + DriverManager.class.getClassLoader()); // jdk.internal.loader.ClassLoaders$PlatformClassLoader@3abfe836

    System.out.println("Classloader of com.sun.nio.zipfs.ZipInfo: "
      + com.sun.nio.file.ExtendedCopyOption.class.getClassLoader()); // boot

  }
}
