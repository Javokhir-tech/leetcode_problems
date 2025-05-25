class Solution {
    // days = 1...365 given days for travel, 
    //costs prices for 1,7,30 day passes, len fixed = 3
    /**
    at each day, we can make up to 3 choices
     */
    int[] cache;
    public int mincostTickets(int[] days, int[] costs) {
        cache = new int[days.length];
        Arrays.fill(cache, -1);
        return dfs(0, days, costs);
    }

    private int dfs(int i, int[] days, int[] costs) {
        if (i == days.length) {
            return 0;
        }
        if (cache[i] != -1) {
            return cache[i];
        }
        int j = i, idx = 0;
        cache[i] = Integer.MAX_VALUE;
        for (int d : new int[]{1, 7, 30}) {
            while (j < days.length && days[j] < days[i] + d) {
                j++;
            }
            cache[i] = Math.min(cache[i], costs[idx] + dfs(j, days, costs));
            idx++;
        }
        return cache[i];
    }
}