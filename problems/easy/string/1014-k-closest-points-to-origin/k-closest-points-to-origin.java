class Solution {
    private class EuclideanDistance {
        private double distance;
        private int[] coordinates;
        private EuclideanDistance(double distance, int[] coordinates) {
            this.distance = distance;
            this.coordinates = coordinates;
        }
    }

    private double calculateDistance(int[] pairs) {
        return Math.sqrt(Math.pow(pairs[0], 2) + Math.pow(pairs[1], 2));
    }

    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<EuclideanDistance> maxHeap = new PriorityQueue<>(
            (o1, o2) -> Double.compare(o2.distance, o1.distance));
        for (int[] pairs : points) {
            double distance = calculateDistance(pairs);
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