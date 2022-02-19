package lc;

/**
 * Created by zz on 2021/10/7.
 */
public class L188 {

    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        if (n < 2) return 0;
        int[][][] dp = new int[n][k + 1][2];
        for (int _k = 0; _k <= k; _k++) {
            dp[0][_k][0] = 0;
            dp[0][_k][1] = -prices[0];
        }

        for (int i = 1; i < n; i++) {
            for (int _k = 1; _k <= k; _k++) {
                dp[i][_k][0] = Math.max(dp[i - 1][_k][0], dp[i - 1][_k][1] + prices[i]);
                dp[i][_k][1] = Math.max(dp[i - 1][_k][1], dp[i - 1][_k - 1][0] - prices[i]);
            }
        }
        return dp[n - 1][k][0];
    }

    public static void main(String[] args) {
        new L188().maxProfit(2, new int[]{2, 4, 1});
    }

}
