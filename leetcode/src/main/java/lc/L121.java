package lc;

/**
 * Created by zz on 2021/8/28.
 */
public class L121 {

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 1) return 0;
        int max = 0;
        int minPrice = prices[0];
        for (int i = 1; i < prices.length; i++) {
            max = Math.max(prices[i] - minPrice, max);
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            }
        }
        return max;
    }


    public int maxProfitV2(int[] prices) {
        if (prices == null || prices.length < 2) return 0;
        int[][] dp = new int[prices.length][2];     // 0: 不持有 1：持有
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
        }
        return dp[prices.length - 1][0];
    }

}
