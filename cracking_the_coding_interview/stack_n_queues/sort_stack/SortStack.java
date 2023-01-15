package cracking_the_coding_interview.stack_n_queues.sort_stack;

import java.util.Stack;

/**
 * Write a program to sort a stack such that the smallest items are on the top. You can use
 * an additional temporary stack, but you may not copy the elements into any other data structure
 * (such as an array). The stack supports the following operations: push, pop, peek, and is Empty.
 * */
public class SortStack {
    /**
     * Create a temporary stack say tmpStack.
     * While input stack is NOT empty do this:
     *  Pop an element from input stack call it temp
     *  while temporary stack is NOT empty and top of temporary stack is greater than temp,
     *      top from temporary stack and push it to the input stack
     *      push temp in temporary stack
     * The sorted numbers are in tmpStack
     */
    public static Stack<Integer> sort(Stack<Integer> stack) {
        var temporary = new Stack<Integer>();
        while (!stack.isEmpty()) {
            var temp = stack.pop();
            while (!temporary.isEmpty() && temporary.peek() > temp) {
                stack.push(temporary.pop());
            }
            temporary.push(temp);
        }
        return temporary;
    }

    public static void print(Stack<Integer> stack) {
        for (var s : stack) {
            System.out.println(s);
        }
    }

    public static void main(String[] args) {
        var stack = new Stack<Integer>();
        stack.push(4);
        stack.push(2);
        stack.push(5);
        stack.push(9);
        stack.push(3);
        stack.push(1);
        stack.push(6);
        stack.push(7);
        var sorted = sort(stack);
        print(sorted);
    }
}
