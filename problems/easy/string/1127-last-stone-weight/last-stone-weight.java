class Solution {
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((i1, i2) -> i2 - i1);
        for (int stone : stones) {
            maxHeap.add(stone);
        }
        while (maxHeap.size() > 1) {
            int y = maxHeap.poll();
            int x = maxHeap.poll();
            if (y - x > 0) {
                maxHeap.add(y - x);
            } 
        }
        return (maxHeap.isEmpty()) ? 0 : maxHeap.peek();
    }
}