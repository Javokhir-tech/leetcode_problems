package cracking_the_coding_interview.stack_n_queues.queue_via_stacks;

import java.util.Stack;

/**
 * Implement a MyQueue class which implements a queue using two stacks.
 * */
public class MyQueue<T> {

    private Stack<T> enqueueStack = new Stack<>();
    private Stack<T> dequeueStack = new Stack<>();

    public void enqueue(T item) {
        enqueueStack.push(item);
    }

    public T dequeue() {
        if (dequeueStack.isEmpty()) {
            while (!enqueueStack.isEmpty()) {
                dequeueStack.push(enqueueStack.pop());
            }
        }
        return dequeueStack.pop();
    }

    public static void main(String[] args) {
        var q = new MyQueue<Integer>();
        q.enqueue(2);
        q.enqueue(4);
        System.out.println(q.dequeue());
    }
}
