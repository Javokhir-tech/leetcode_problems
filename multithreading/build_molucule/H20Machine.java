package multithreading.build_molucule;

import java.util.Arrays;
import java.util.Collections;

public class H20Machine {
  private Object sync;
  private String[] molecule;
  private int count;

  public H20Machine() {
    this.sync = new Object();
    this.molecule = new String[3];
    this.count = 0;
  }

  public void hydrogenAtom() {
    synchronized (sync) {
      while (Collections.frequency(Arrays.asList(molecule), "H") == 2) {
        try {
          sync.wait();
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
      molecule[count] = "H";
      count++;

      if (count == 3) {
        for (String element: molecule) {
          System.out.print(element);
        }
        Arrays.fill(molecule, null);
        count = 0;
      }
      sync.notifyAll();
    }
  }

  public void oxygenAtom() {
    synchronized (sync) {
      while (Collections.frequency(Arrays.asList(molecule), "O") == 1) {
        try {
          sync.wait();
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
      molecule[count] = "O";
      count++;

      if (count == 3) {
        for (String element: molecule) {
          System.out.print(element);
        }
        Arrays.fill(molecule, null);
        count = 0;
      }
      sync.notifyAll();
    }
  }

  static class WaterThread extends Thread {
    private H20Machine h20Machine;
    private String atom;

    public WaterThread(H20Machine h20Machine, String atom) {
      this.h20Machine = h20Machine;
      this.atom = atom;
    }

    public void run() {
      if ("H".equals(atom)) {
        h20Machine.hydrogenAtom();
      }
      else if ("O".equals(atom)) {
        h20Machine.oxygenAtom();
      }
    }
  }

  public static void main(String[] args) {
    H20Machine h20Machine = new H20Machine();
    Thread t1 = new WaterThread(h20Machine,"H");
    Thread t2 = new WaterThread(h20Machine,"O");
    Thread t3 = new WaterThread(h20Machine,"H");
    Thread t4 = new WaterThread(h20Machine,"O");

    t2.start();
    t1.start();
    t4.start();
    t3.start();
  }
}
