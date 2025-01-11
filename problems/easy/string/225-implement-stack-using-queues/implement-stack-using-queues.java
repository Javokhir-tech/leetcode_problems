class MyStack {
    private Queue<Integer> queue;
    private Queue<Integer> topQueue;

    public MyStack() {
        this.queue = new ArrayDeque<>();
        this.topQueue = new ArrayDeque<>();
    }
    
    public void push(int x) {
        queue.add(x);
        if (!topQueue.isEmpty()) {
            topQueue.poll();
        }
        topQueue.add(x); // add top element
    }
    
    public int pop() {
        int top = topQueue.poll();
        for (int i = 0; i < queue.size() - 1; i++) {
            int element = queue.poll();
            queue.add(element);
            if (i == queue.size() - 2) {
                topQueue.add(element);
            }
        }
        queue.poll();
        
        return top;
    }
    
    public int top() {
        return topQueue.peek();
    }
    
    public boolean empty() {
        return queue.isEmpty();
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */