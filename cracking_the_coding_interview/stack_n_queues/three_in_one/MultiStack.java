package cracking_the_coding_interview.stack_n_queues.three_in_one;

import java.util.EmptyStackException;

/**
* Describe how you could use a single array to implement three stacks.
 * */
public class MultiStack {

    /** values in stack
     * */
    private final int[] values;
    /** array to keep top indexes of stacks
     * */
    private final int[] tops;
    /** size of each stack
     * */
    private final int stackSize;
    private static final int numberOfStacks = 3;

    public MultiStack(int stackSize) {
        this.stackSize = stackSize;
        values = new int[stackSize * numberOfStacks]; // create whole array to have equal length for each stack
        tops = new int[numberOfStacks];
        for (int i = 0; i < tops.length; i++) { // set initial values for top indexes of stacks
            tops[i] = (stackSize) * i - 1;
        }
    }

    public void push(int stackNum, int item) {
        if (isFull(stackNum)) throw new StackOverflowError();   // check if each stack is full
        var top = getTopIndex(stackNum);    // get top index for given stack
        values[++top] = item;   // push item to stack
        tops[stackNum - 1] = top;   // update top index value in tops arr
    }

    public int getTopIndex(int stackNum) {
        return tops[stackNum - 1];
    }

    public int pop(int stackNum) {
        if (isEmpty(stackNum)) throw new EmptyStackException();
        var top = getTopIndex(stackNum);
        var deletedTop = top;
        var data = values[top--];   // get value from stack, then decrease top
        values[deletedTop] = 0; // remove element from stack
        tops[stackNum - 1] = top; // update top index value in tops arr
        return data;
    }

    public void print(int stackNum) {
        var index = stackNum * stackSize;
        for (int i = index - stackSize; i < index; i++) {
            System.out.println(values[i]);
        }
    }
    public boolean isFull(int stackNum) {   // check if stack is full
        var index = stackNum * stackSize;
        return tops[stackNum - 1] == index - 1;
    }

    public boolean isEmpty(int stackNum) { // check if stack is empty
        var index = stackNum - 1;
        return tops[index] == index * stackSize - 1;
    }

    public static void main(String[] args) {
        var stack = new MultiStack(4);
        stack.push(1, 3);
        stack.push(1, 4);
        stack.push(1, 2);
        stack.push(1, 5);

        stack.push(2, 6);
        stack.push(2, 5);

        stack.push(3, 9);
        stack.push(3, 18);
        stack.push(3, 9);
        stack.push(3, 18);
//        stack.push(3, 9);
        System.out.println("removed");
        System.out.println(stack.pop(1));
        System.out.println(stack.pop(2));
        System.out.println(stack.pop(2));
        System.out.println(stack.pop(3));

        System.out.println("stack 2");
        stack.push(2, 7);
        stack.push(2, 12);
        stack.print(2);
        System.out.println("stack 1");
        stack.print(1);
        System.out.println("stack 3");
        stack.print(3);
    }
}
