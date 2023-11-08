package cracking_the_coding_interview.stack_n_queues.array;

import java.util.EmptyStackException;

public class Stack {

    private int top;
    private int values[];
    private int size;

    public Stack(int size) {
        this.size = size;
        values = new int[size];
        top = -1;
    }

    public void push(int value) {
        if (isFull()) throw new StackOverflowError();
        values[++top] = value;
    }

    public int pop() {
        if (isEmpty()) throw new EmptyStackException();
        return values[top--];
    }

    public boolean isFull() {
        return top == size - 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.println(values[i]);
        }
    }

    public static void main(String[] args) {
        var stack = new Stack(4);
        stack.push(3);
        stack.push(2);
        stack.push(8);
        stack.push(5);
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.print();
    }
}
