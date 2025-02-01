class Solution {
    int rows;
    int cols;
    public int orangesRotting(int[][] grid) {
        rows = grid.length;
        cols = grid[0].length;
        Queue<int[]> queue = new ArrayDeque<>();

        int minutes = 0;
        int fresh = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 1) {
                    fresh++;
                } if (grid[r][c] == 2) {
                    queue.add(new int[] {r, c});
                }
            }
        }
        int[] result = bfs(grid, queue, fresh, minutes);
        minutes = result[1];
        fresh = result[0];
        return (fresh > 0) ? -1 : minutes;
    }

    private int[] bfs(int[][] grid, Queue<int[]> queue, int fresh, int minutes) {
        while (fresh > 0 && !queue.isEmpty()) {
            int queueLen = queue.size();
            for (int i = 0; i < queueLen; i++) {
                int[] pair = queue.poll();
                int r = pair[0], c = pair[1];
                
                int[][] neighbours = {{r, c + 1}, {r, c - 1}, {r + 1, c}, {r - 1, c}};
                for (int j = 0; j < 4; j++) {
                    int newR = neighbours[j][0], newC = neighbours[j][1];
                    if (Math.min(newR, newC) < 0 || newR >= rows || newC >= cols || 
                        grid[newR][newC] == 2 || grid[newR][newC] == 0) {
                        continue;
                    }
                    grid[newR][newC] = 2; // mark visited
                    fresh--; // decrease fresh fruit count
                    queue.add(neighbours[j]);
                }
            }
            minutes++;
        }
        return new int[] {fresh, minutes};
    }
}