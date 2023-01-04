package cracking_the_coding_interview.stack_n_queues;

public class Main {
    public static void main(String[] args) {
        var stack = new Stack<Integer>();
        stack.push(2);
        stack.push(5);
        stack.push(3);
        stack.push(8);
        stack.pop();
        stack.pop();
        System.out.println(stack.pop());
        stack.pop();
    }
}
