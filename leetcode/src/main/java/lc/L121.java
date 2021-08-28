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

}
