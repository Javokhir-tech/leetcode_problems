class Solution {

    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
            (a, b) -> Integer.compare(b[0] * b[0] + b[1] * b[1], 
                a[0] * a[0] + a[1] * a[1]));

        for (int[] pairs : points) {
            maxHeap.add(pairs);
            if (maxHeap.size() > k) {
                maxHeap.poll(); // remove biggest
            }
        }
        int[][] pairs = new int[k][2]; 
        for (int i = 0; i < k; i++) {
            pairs[i] = maxHeap.poll();
        }
        return pairs;
    }
}