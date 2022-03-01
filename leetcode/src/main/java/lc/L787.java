package lc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by zz on 2021/11/22.
 */
public class L787 {

    private Map<Integer, List<int[]>> map = new HashMap<>();
    int[][] dp;

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        k++;
        for (int[] flight : flights) {
            int from = flight[0];
            int to = flight[1];
            int price = flight[2];

            map.putIfAbsent(to, new LinkedList<>());
            map.get(to).add(new int[]{from, price});
        }

        dp = new int[n][k + 1];
        for (int[] i : dp) {
            Arrays.fill(i, -222);
        }
        return dp(dst, k, src, dst);
    }

    private int dp(int s, int k, int src, int dst) {
        if (s == src) {
            return 0;
        }
        if (k == 0) {
            return -1;
        }

        if (dp[s][k] != -222) {
            return dp[s][k];
        }

        int res = Integer.MAX_VALUE;
        if (map.containsKey(s)) {
            for (int[] flight : map.get(s)) {
                int from = flight[0];
                int price = flight[1];

                int pre = dp(from, k - 1, src, dst);

                if (pre != -1) {
                    res = Math.min(res, pre + price);
                }
            }
        }

        dp[s][k] = res == Integer.MAX_VALUE ? -1 : res;
        return dp[s][k];
    }

}
