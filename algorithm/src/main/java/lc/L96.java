package lc;

/**
 * Created by zz on 2021/11/1.
 */
public class L96 {

    private int[][] dp;

    public int numTrees(int n) {
        dp = new int[n + 1][n + 1];
        return count(1, n);
    }

    private int count(int min, int max) {
        if (min > max) return 1;
        if (dp[min][max] != 0) return dp[min][max];
        int ans = 0;
        for (int i = min; i <= max; i++) {
            int left = count(min, i - 1);
            int right = count(i + 1, max);
            ans += left * right;        // 乘法原理
        }
        dp[min][max] = ans;
        return ans;
    }

}
