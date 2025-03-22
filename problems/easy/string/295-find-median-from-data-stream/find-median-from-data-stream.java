class MedianFinder {
    private PriorityQueue<Integer> small;   // max heap
    private PriorityQueue<Integer> large;   // min heap

    public MedianFinder() {
        this.small = new PriorityQueue<>(Collections.reverseOrder());
        this.large = new PriorityQueue<>();
    }
    
    public void addNum(int num) {
        small.add(num);
        int val;
        if (!small.isEmpty() && !large.isEmpty() && small.peek() > large.peek()) {
            val = small.poll();
            large.add(val);
        }
        if (small.size() > large.size() + 1) {
            val = small.poll();
            large.add(val);
        }
        if (large.size() > small.size() + 1) {
            val = large.poll();
            small.add(val);
        }
    }
    
    public double findMedian() {
        if (small.size() > large.size()) {
            return (double) small.peek();
        } else if (small.size() < large.size()) {
            return (double) large.peek();
        }
        return (double) (small.peek() + large.peek()) / 2;
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */