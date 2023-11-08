package cracking_the_coding_interview.stack_n_queues.stack_min;

import java.util.Stack;

/**
 * How would you design a stack which, in addition to push and pop, has a function min
 * which returns the minimum element? Push, pop and min should all operate in 0(1) time.
 * */
public class StackMin extends Stack<StackMin.NodeMin> {

    public void push(int value) {
        int newMin = Math.min(value, min());
        super.push(new NodeMin(value, newMin));
    }

    public int min() {
        if (this.isEmpty()) {
            return Integer.MAX_VALUE;
        }
        return peek().min;
    }

    /**
     * By saving state of min for each value
     * */
   static class NodeMin {
       private int value;
       private int min;
       public NodeMin(int value, int min) {
           this.value = value;
           this.min = min;
       }
   }

    public static void main(String[] args) {
        var stack = new StackMin();
        stack.push(3);
        stack.push(5);
        stack.push(9);
        stack.push(8);
        stack.push(2);
        stack.pop();
        System.out.println(stack.min());
    }
}
