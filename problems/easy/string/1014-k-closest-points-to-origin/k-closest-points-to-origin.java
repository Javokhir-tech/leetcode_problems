class Solution {
    private class EuclideanDistance {
        private int distance;
        private int[] coordinates;
        private EuclideanDistance(int distance, int[] coordinates) {
            this.distance = distance;
            this.coordinates = coordinates;
        }
    }

    private int calculateDistance(int[] pairs) {
        return pairs[0] * pairs[0] + pairs[1] * pairs[1];
    }

    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<EuclideanDistance> maxHeap = new PriorityQueue<>(
            (o1, o2) -> o2.distance - o1.distance);

        for (int[] pairs : points) {
            int distance = calculateDistance(pairs);
            maxHeap.add(new EuclideanDistance(distance, pairs));
            if (maxHeap.size() > k) {
                maxHeap.poll(); // remove biggest
            }
        }
        int[][] pairs = new int[k][2]; 
        for (int i = 0; i < k; i++) {
            pairs[i] = maxHeap.poll().coordinates;
        }
        return pairs;
    }
}