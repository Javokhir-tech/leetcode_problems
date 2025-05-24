class Solution {
    Map<Integer, Integer> cache;
    public int coinChange(int[] coins, int amount) {
        this.cache = new HashMap<>();
        int minCoin = dfs(coins, amount);
        return (minCoin >= 1e9) ? -1 : minCoin;
    }

    private int dfs(int[] coins, int amount) {
        if (amount == 0) return 0;

        if (cache.containsKey(amount)) {
            return cache.get(amount);
        }

        int res = (int)1e9;
        for (int coin : coins) {
            if (amount - coin >= 0) {
                res = Math.min(1 + dfs(coins, amount - coin), 
                    res);
            }
        }
        cache.put(amount, res);
        return res;
    }
}