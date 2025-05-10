class Solution {
    int[][][] dp;

    public int findMaxForm(String[] strs, int m, int n) {
       // include or not include str
       dp = new int[strs.length][m + 1][n + 1];
       for (int i = 0; i < strs.length; i++) {
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }
       return dfs(0, m, n, strs); 
    }

    private int dfs(int i, int m, int n, String[] strs) {
        if (i == strs.length) {
            return 0;
        }

        if (dp[i][m][n] != -1) {
            return dp[i][m][n];
        }

        dp[i][m][n] = dfs(i + 1, m, n, strs);
        int mCnt = (int) strs[i].chars().filter(c -> c == '0').count(); // count 0s
        int nCnt = (int) strs[i].chars().filter(c -> c == '1').count(); // count 1
        if (mCnt <= m && nCnt <= n) {
            dp[i][m][n] = Math.max(dfs(i + 1, m, n, strs), 
                1 + dfs(i + 1, m - mCnt, n - nCnt, strs)
            );
        }
        return dp[i][m][n];
    }
}