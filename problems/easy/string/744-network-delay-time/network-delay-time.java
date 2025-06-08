class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, List<int[]>> adj = new HashMap<>();
        for (int i = 1; i < n + 1; i++) {
            adj.put(i, new ArrayList<>());
        }

        for (int[] edge : times) {
            int src = edge[0], dst = edge[1], w = edge[2];
            adj.get(src).add(new int[] {dst, w});
        }
        Queue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        Map<Integer,Integer> shortest = new HashMap<>(); // vertex -> weight
        minHeap.add(new int[]{0, k});
        int shortestPath = 0;
        while (!minHeap.isEmpty()) {
            int[] curr = minHeap.poll();
            int w1 = curr[0], d1 = curr[1];
            if (shortest.containsKey(d1)) {
                continue;
            }
            shortest.put(d1, w1);
            shortestPath = w1;
            for (int[] pair : adj.get(d1)) {
                int d2 = pair[0], w2 = pair[1];
                if (!shortest.containsKey(d2)) {
                    minHeap.add(new int[] {w2 + w1, d2});
                }
            }
        }
        boolean unreachableNodeExists = false;
        for (int i = 1; i < n + 1; i++) {
            if (!shortest.containsKey(i)) {
                shortest.put(i, -1);
                unreachableNodeExists = true;
            }
        }

        return unreachableNodeExists ? -1 : shortestPath;
    }
}