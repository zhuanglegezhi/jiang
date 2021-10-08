package lc;

/**
 * Created by zz on 2021/8/28.
 */
public class L123 {

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        /**
         * 0:没操作过
         * 1:买入一次
         * 2:一买一卖
         * 3:二买一卖
         * 4:二买二卖
         */
        int[][] dp = new int[prices.length][5];
        dp[0][1] = -prices[0];
        dp[0][3] = -prices[0];          // 要初始化，虽然不可能

        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = dp[i - 1][0];
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
            dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] + prices[i]);
            dp[i][3] = Math.max(dp[i - 1][3], dp[i - 1][2] - prices[i]);
            dp[i][4] = Math.max(dp[i - 1][4], dp[i - 1][3] + prices[i]);
        }
        return dp[prices.length - 1][4];
    }

    public static void main(String[] args) {
        int max = new L123().maxProfit(new int[]{1, 2, 3, 4, 5});
    }


    public int maxProfitV2(int[] prices) {
        if (prices == null || prices.length < 2) return 0;
        int[][][] dp = new int[prices.length][3][2];
        for (int _k = 0; _k <= 2; _k++) {
            dp[0][_k][0] = 0;
            dp[0][_k][1] = -prices[0];
        }

        for (int i = 1; i < prices.length; i++) {
            for (int k = 1; k <= 2; k++) {
                dp[i][k][0] = Math.max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i]);
                dp[i][k][1] = Math.max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i]);
            }
        }
        return dp[prices.length - 1][2][0];
    }

}
