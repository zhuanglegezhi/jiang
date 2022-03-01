package lc;

/**
 * Created by zz on 2021/11/20.
 */
public class L64 {

    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];

        for (int i = 0; i < n; i++) {
            if (i == 0) {
                dp[0][i] = grid[0][i];
            } else {
                dp[0][i] = grid[0][i] + dp[0][i - 1];
            }
        }

        for (int i = 0; i < m; i++) {
            if (i == 0) {
                dp[i][0] = grid[i][0];
            } else {
                dp[i][0] = grid[i][0] + dp[i - 1][0];
            }
        }


        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }

        return dp[m - 1][n - 1];
    }
}
