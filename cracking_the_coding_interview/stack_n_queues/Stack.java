package cracking_the_coding_interview.stack_n_queues;

import java.util.EmptyStackException;

public class Stack<T> {
    public static class StackNode<T> {
        private T data;
        private StackNode<T> next;

        public StackNode(T data) {
            this.data = data;
        }
    }

    private StackNode<T> top;

    public T pop() {
        var data = top.data;
        top = top.next;
        if (top == null) throw new EmptyStackException();
        return data;
    }

    public void push(T item) {
        var t = new StackNode<T>(item);
        t.next = top;
        top = t;
    }

    public void print() {
        var t = top;
        while (t != null) {
            System.out.println(t.data);
            t = t.next;
        }
    }
}
