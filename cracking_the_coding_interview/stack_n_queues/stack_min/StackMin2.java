package cracking_the_coding_interview.stack_n_queues.stack_min;

import java.util.Stack;

/**
 * Using stack for keep track of min stack by decreasing order to the top
 * */
public class StackMin2 extends Stack<Integer> {
    private Stack<Integer> min;

    public StackMin2() {
        min = new Stack<>();
    }

    public void push(int item) {
        if (item <= min()) min.push(item);

        super.push(item);
    }

    public Integer pop() {
        var value = super.pop();
        if (value == min()) min.pop();

        return value;
    }

    public int min() {
        if (min.isEmpty()) return Integer.MAX_VALUE;

        return min.peek();
    }

    public static void main(String[] args) {
        var stack = new StackMin2();
        stack.push(3);
        stack.push(5);
        stack.push(9);
        stack.push(8);
        stack.push(2);
        stack.pop();
        System.out.println(stack.min());
    }
}
