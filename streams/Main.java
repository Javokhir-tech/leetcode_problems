package streams;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static streams.Main.TransactionType.CREDIT;
import static streams.Main.TransactionType.DEBIT;

public class Main {

    public static void main(String[] args) {
//        groupTransactionsByTypeAndCalculateAverageAmount();
//        groupStudentsByGradeThenCount();
        groupEmployeesByDepartments();
    }

    private static void groupTransactionsByTypeAndCalculateAverageAmount() {
        // Group transactions by type and calculate the average transaction amount per type.
        var t1 = new Transaction(DEBIT, 10.50);
        var t2 = new Transaction(CREDIT, 20.0);
        var t3 = new Transaction(DEBIT, 12.50);
        var t4 = new Transaction(DEBIT, 1.00);
        List<Transaction> transactions = List.of(t1, t2, t3, t4);
        Map<TransactionType, Double> averageAmountByTransactionType = transactions.stream()
                .collect(Collectors.groupingBy(Transaction::type,
                        Collectors.averagingDouble(Transaction::amount)));
        System.out.println("averageAmountByTransactionType:: " + averageAmountByTransactionType);
    }

    record Transaction(TransactionType type, double amount) {}
    enum TransactionType {
        DEBIT, CREDIT
    }

    record Student(double grade, String name) {}

    private static void groupStudentsByGradeThenCount() {
        // Group a list of students by grade and count how many students in each.
        var s1 = new Student(4.5, "John");
        var s2 = new Student(2.2, "Doe");
        var s3 = new Student(3.4, "Jack");
        var s4 = new Student(4.5, "John");
        List<Student> students = List.of(s1, s2, s3, s4);

        Map<Double, Long> gradeCounts = students.stream()
                .collect(Collectors.groupingBy(Student::grade, Collectors.counting()));
        System.out.println("groupStudentsByGradeThenCount:: " + gradeCounts);
    }

    record Employee(String name, String department, int salary) {}

    private static void groupEmployeesByDepartments() {

        List<Employee> employees = List.of(
                new Employee("Alice", "HR", 50000),
                new Employee("Bob", "IT", 70000),
                new Employee("Carol", "HR", 55000),
                new Employee("Dave", "IT", 80000)
        );

        Map<String, String> employeesByDept = employees.stream()
                .collect(Collectors.groupingBy(Employee::department,
                        Collectors.mapping(Employee::name, Collectors.joining(", "))
                ));
        System.out.println("groupEmployeesByDepartments:: " + employeesByDept);
//        Comparator.comparingDouble()
    }
}
