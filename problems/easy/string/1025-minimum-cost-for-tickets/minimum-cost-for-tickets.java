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
        return dfs(0, days.length, days, costs);
    }

    private int dfs(int i, int n, int[] days, int[] costs) {
        if (i == n) {
            return 0;
        }
        if (cache[i] != -1) {
            return cache[i];
        }
        int res = costs[0] + dfs(i + 1, n, days, costs);
        int j = i;
        while (j < n && days[j] < days[i] + 7) {
            j++;
        }
        res = Math.min(res, costs[1] + dfs(j, n, days, costs));
        j = i;
        while (j < n && days[j] < days[i] + 30) {
            j++;
        }
        res = Math.min(res, costs[2] + dfs(j, n, days, costs));
        cache[i] = res;
        return res;
    }
}