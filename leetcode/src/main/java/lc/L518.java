package lc;

/**
 * Created by zz on 2021/8/16.
 */
public class L518 {

    public int change(int amount, int[] coins) {
        return v1(amount, coins);
    }

    private int v1(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] = dp[i] + dp[i - coin];
            }
        }
        return dp[amount];
    }


    private int v2(int amount, int[] coins) {
        int len = coins.length;
        int[][] dp = new int[len + 1][amount + 1];
        for (int i = 0; i <= len; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= len; i++) {
            for (int j = 1; j <= amount; j++) {
                int coin = coins[i - 1];

                if (coin <= j) {
                    // 不选 + 选
                    dp[i][j] = dp[i - 1][j] + dp[i][j - coin];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }

            }
        }
        return dp[len][amount];
    }
}
