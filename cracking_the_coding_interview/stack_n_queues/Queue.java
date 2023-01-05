package cracking_the_coding_interview.stack_n_queues;

import java.util.EmptyStackException;

public class Queue<T> {

    public static class QueueNode<T> {
        private T data;
        private QueueNode<T> next;
        public QueueNode(T data) {
            this.data = data;
        }
    }

    private QueueNode<T> first;
    private QueueNode<T> last;

    public T remove() {
        if (first == null) throw new EmptyStackException();
        var data = first.data;
        first = first.next;
        if (first == null) {
            last = null;
        }
        return data;
    }

    public void add(T item) {
        var node = new QueueNode<T>(item);
        if (last != null) {
            last.next = node;
        }
        last = node;
        if (first == null) {
            first = last;
        }
    }

    public void print() {
        var t = first;
        while (t != null) {
            System.out.println(t.data);
            t = t.next;
        }
    }

    public T peek() {
        if (last == null) throw new EmptyStackException();
        return last.data;
    }

    public boolean isEmpty() {
        return last == null;
    }
}
