package multithreading.ocp;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class Test5 {
    public static void main(String[] args) {
        var value1 = new AtomicLong(0);
        final long[] value2 = {0};
        IntStream.iterate(1, i -> 1).limit(100).parallel()
                .forEach(i -> value1.incrementAndGet());

        IntStream.iterate(1, i -> 1).limit(100).parallel()
                .forEach(i -> ++value2[0]);

        System.out.println(value1+" "+value2[0]);
    }

    static class Test13 {
        public static void main(String[] args) {
            System.out.print(List.of("duck","flamingo","pelican")
                    .parallelStream().parallel() // q1
                    .reduce(0,
                            (c1, c2) -> c1 + c2.length(), // q2
                            Integer::sum)); // q3
        }
    }

    static class Test19 {
            public static void main(String[] args) {
                var s = Executors.newScheduledThreadPool(10);
                DoubleStream.of(3.14159, 2.71828) // b1
                        .forEach(c -> s.submit( // b2
                                () -> System.out.println(10 * c))); // b3
                s.execute(() -> System.out.println("Printed"));
            }
    }

    static class Test25 {
        public static void performCount(int animal) {
            // IMPLEMENTATION OMITTED
            throw new RuntimeException();
        }
        public static void printResults(Future<?> f) {
            try {
                System.out.println(f.get(1, TimeUnit.DAYS)); // o1
            } catch (Exception e) {
                System.out.println("Exception!");
            }
        }
        public static void main(String[] args) throws Exception {
            final var r = new ArrayList<Future<?>>();
            ExecutorService s = Executors.newSingleThreadExecutor();
            try {
                for(int i = 0; i < 10; i++) {
                    final int animal = i;
                    r.add(s.submit(() -> performCount(animal))); // o2
                }
                r.forEach(Test25::printResults);
            } finally { s.shutdown(); }
        }
    }
}
