package cracking_the_coding_interview.stack_n_queues;

import java.util.EmptyStackException;

/*
* internally uses linked list
* */
public class Stack<T> {
    public static class StackNode<T> {
        private T data;
        private StackNode<T> next;

        public StackNode(T data) {
            this.data = data;
        }
    }

    private StackNode<T> top;

    /*
    * removes from top
    * */
    public T pop() {
        if (top == null) throw new EmptyStackException();
        var data = top.data;
        top = top.next;
        return data;
    }

    /*
    * adds to top
    * */
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

    /*
    * checks if empty
    * */
    public boolean isEmpty() {
        return top == null;
    }

    /*
    * returns top element
    * */
    public T peek() {
        if (top == null) throw new EmptyStackException();
        return top.data;
    }
}
