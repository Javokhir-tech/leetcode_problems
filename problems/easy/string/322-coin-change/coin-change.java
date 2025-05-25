class Solution {
    Map<Integer, Integer> cache;
    public int coinChange(int[] coins, int amount) {
        this.cache = new HashMap<>();
        int minCoin = dfs(coins, amount);
        return (minCoin >= 1e9) ? -1 : minCoin; 
    }

    private int dfs(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        else if (amount < 0) {
            return Integer.MAX_VALUE;
        }
        if (cache.containsKey(amount)) {
            return cache.get(amount);
        }
        int res = (int) 1e9;
        for (int coin : coins) {
            if (amount - coin >= 0) {
                int result = dfs(coins, amount - coin);
                if (result != Integer.MAX_VALUE) {
                    res = Math.min(1 + result, res);
                }
            }
        }
        cache.put(amount, res);
        return res;
    }
}