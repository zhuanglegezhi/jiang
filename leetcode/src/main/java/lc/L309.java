package lc;

/**
 * Created by zz on 2021/8/28.
 */
public class L309 {

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int len = prices.length;
        /**
         * 代表该天的最大收益
         * 0:有未卖出的 1:刚卖，下一天冷冻期  2：无限制
         */
        int dp[][] = new int[len][3];

        dp[0][0] = -prices[0];
        for (int i = 1; i < len; i++) {
            // 可能这个股票在i-1天时就没卖出；可能时第i天刚买入
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][2] - prices[i]);
            // 当天卖的情况
            dp[i][1] = dp[i - 1][0] + prices[i];
            // 可能前一天冷冻期；可能前一天也无限制
            dp[i][2] = Math.max(dp[i - 1][1], dp[i - 1][2]);
        }
        return Math.max(dp[len - 1][1], dp[len - 1][2]);
    }

}
