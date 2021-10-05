package 面试题;

/**
 * Created by zz on 2021/9/6.
 */
public class M1709 {

    public int getKthMagicNumber(int k) {
        int[] dp = new int[k];
        dp[0] = 1;
        int p1 = 0, p2 = 0, p3 = 0;
        for (int i = 1; i < k; i++) {
            int v1 = dp[p1] * 3;
            int v2 = dp[p2] * 5;
            int v3 = dp[p3] * 7;

            dp[i] = Math.min(v1, Math.min(v2, v3));
            if (v1 == dp[i]) p1++;
            if (v2 == dp[i]) p2++;
            if (v3 == dp[i]) p3++;
        }
        return dp[k - 1];
    }

}
