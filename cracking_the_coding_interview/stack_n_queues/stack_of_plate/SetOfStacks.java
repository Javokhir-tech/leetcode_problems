package cracking_the_coding_interview.stack_n_queues.stack_of_plate;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

/**
 * Imagine a (literal) stack of plates. If the stack gets too high, it might topple.
 * Therefore, in real life, we would likely start a new stack when the previous stack exceeds some
 * threshold. Implement a data structure SetOfStacks that mimics this. SetO-fStacks should be
 * composed of several stacks and should create a new stack once the previous one exceeds capacity.
 * SetOfStacks. push() and SetOfStacks. pop() should behave identically to a single stack
 * (that is, pop () should return the same values as it would if there were just a single stack).
 * */
public class SetOfStacks {

    private List<Integer> values;
    private int top;
    private Integer size;

    public SetOfStacks(Integer size) {
        values = new ArrayList<>(size);
        top = -1;
    }

    public void push(Integer item) {
        values.add(++top, item);
    }

    public Integer pop() {
        if (isEmpty()) throw new EmptyStackException();
        return values.remove(top--);
    }

    public Integer popAt(int index) {
        top--;
        return values.remove(index);
    }

    public void print() {
        for (Integer value : values) {
            System.out.println(value);
        }
    }

    public Integer peek() {
        return values.get(top);
    }
    public boolean isEmpty() {
        return top == -1;
    }

    public static void main(String[] args) {
        var stackSets = new SetOfStacks(4);
        stackSets.push(2);
        stackSets.push(5);
        stackSets.push(12);
        stackSets.push(55);
        stackSets.push(21);
        stackSets.push(1);
        stackSets.pop();
        stackSets.pop();
        stackSets.popAt(2);
        System.out.println("top: " + stackSets.peek());
        stackSets.print();
    }
}
