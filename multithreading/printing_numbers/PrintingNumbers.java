package multithreading.printing_numbers;

import java.util.concurrent.Semaphore;

public class PrintingNumbers {

    private int n;
    private Semaphore zeroSem, oddSem, evenSem;

    public PrintingNumbers(int n) {
        this.n = n;
        this.zeroSem = new Semaphore(1);
        this.oddSem = new Semaphore(0);
        this.evenSem = new Semaphore(0);
    }

    public void printZero() {
        for (int i = 0; i < n; ++i) {
            try {
                zeroSem.acquire();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.print("0");
            (i % 2 == 0 ? oddSem : evenSem).release();
        }
    }

    public void printOdd() {
        for (int i = 1; i < n; i += 2) {
            try {
                oddSem.acquire();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.print(i);
            zeroSem.release();
        }
    }

    public void printEven() {
        for (int i = 2; i < n; i += 2) {
            try {
                evenSem.acquire();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.print(i);
            zeroSem.release();
        }
    }

    static class PrintNumberSeriesThread extends Thread {

        PrintingNumbers zeo;
        String method;

        public PrintNumberSeriesThread(PrintingNumbers zeo, String method) {
            this.zeo = zeo;
            this.method = method;
        }

        public void run() {
            if ("zero".equals(method)) {
                try {
                    zeo.printZero();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } else if ("even".equals(method)) {
                try {
                    zeo.printEven();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } else if ("odd".equals(method)) {
                try {
                    zeo.printOdd();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void main(String[] args) {
        PrintingNumbers zeo = new PrintingNumbers(5);

        Thread t1 = new PrintNumberSeriesThread(zeo,"zero");
        Thread t2 = new PrintNumberSeriesThread(zeo,"even");
        Thread t3 = new PrintNumberSeriesThread(zeo,"odd");

        t2.start();
        t1.start();
        t3.start();
    }
}
