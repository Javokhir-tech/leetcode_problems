package cracking_the_coding_interview.stack_n_queues;

import cracking_the_coding_interview.stack_n_queues.linked_list.Queue;
import cracking_the_coding_interview.stack_n_queues.linked_list.Stack;

public class Main {
    public static void main(String[] args) {
        var stack = new Stack<Integer>();
        System.out.println(stack.isEmpty());
        stack.push(2);
        stack.push(5);
        stack.push(3);
        stack.push(8);
        stack.pop();
        stack.pop();

        var queue = new Queue<Integer>();
        queue.add(4);
        queue.add(5);
        queue.add(9);
        queue.add(6);
        queue.print();
        System.out.println(stack.isEmpty());
    }
}
