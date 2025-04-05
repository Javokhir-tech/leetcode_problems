class Solution {

    public int minCostConnectPoints(int[][] points) {
        Queue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]); // cost, pointIdx
        int n = points.length;
        Set<Integer> visited = new HashSet<>();
        minHeap.add(new int[]{0, 0});
        int totalCost = 0;

        while (visited.size() < n) {
            int[] curr = minHeap.poll();
            int cost = curr[0], idx = curr[1];
            if (visited.contains(idx)) {
                continue;
            }
            visited.add(idx);
            totalCost += cost;
            for (int i = 0; i < n; i++) {
                if (!visited.contains(i)) {
                    int dist = calculateDistance(points[idx], points[i]);
                    minHeap.add(new int[]{dist, i});
                }
            }
        }
        return totalCost;
    }

    private int calculateDistance(int[] p1, int[] p2) {
        return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
    }
}