class Solution {
    int[][][] dp;
    int[][] arr;
    public int findMaxForm(String[] strs, int m, int n) {
       arr = new int[strs.length][2];
       for (int i = 0; i < strs.length; i++) {
            for (char c : strs[i].toCharArray()) {
                arr[i][c - '0']++;
            }
       } 
       dp = new int[strs.length][m + 1][n + 1];
       for (int i = 0; i < strs.length; i++) {
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }
       return dfs(0, m, n); 
    }

    private int dfs(int i, int m, int n) {
        if (i == arr.length) {
            return 0;
        }

        if (dp[i][m][n] != -1) {
            return dp[i][m][n];
        }

        dp[i][m][n] = dfs(i + 1, m, n);
        int mCnt = arr[i][0]; // count 0s
        int nCnt = arr[i][1]; // count 1
        if (mCnt <= m && nCnt <= n) {
            dp[i][m][n] = Math.max(dfs(i + 1, m, n), 
                1 + dfs(i + 1, m - mCnt, n - nCnt)
            );
        }
        return dp[i][m][n];
    }
}