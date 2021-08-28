package lc;

/**
 * Created by zz on 2021/8/28.
 */
public class L122 {

    public int maxProfit(int[] prices) {
        int max = 0;
        if (prices == null) return max;
        int i = 0;
        int j = 1;
        while (i < j && j < prices.length) {
            if (prices[i] >= prices[j]) {
                i++;
                j++;
            } else {
                if (j + 1 < prices.length && prices[j] < prices[j + 1]) {
                    j++;
                } else {
                    max += prices[j] - prices[i];
                    i = j + 1;
                    j = i + 1;
                }
            }
        }
        return max;
    }
}
