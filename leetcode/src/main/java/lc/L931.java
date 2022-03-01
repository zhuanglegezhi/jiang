package lc;

import java.util.Arrays;

/**
 * Created by zz on 2021/11/13.
 */
public class L931 {

    int[][] dp;

    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            min = Math.min(min, calDp(matrix, n - 1, i));
        }
        return min;

    }

    private int calDp(int[][] matrix, int i, int j) {
        int n = matrix.length;
        if (i < 0 || j < 0 || i >= n || j >= n) return Integer.MAX_VALUE;

        if (i == 0) {
            // 第一行
            return matrix[i][j];
        }

        if (dp[i][j] != Integer.MAX_VALUE) {
            return dp[i][j];
        }

        dp[i][j] = matrix[i][j] +
                Math.min(calDp(matrix, i - 1, j), Math.min(calDp(matrix, i - 1, j - 1), calDp(matrix, i - 1, j + 1)));
        return dp[i][j];
    }

}
