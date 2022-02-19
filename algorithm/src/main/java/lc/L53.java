package lc;

/**
 * Created by zz on 2021/8/22.
 */
public class L53 {

    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = dp[0];
        for (int i = 1; i < nums.length; i++) {
            if (dp[i - 1] < 0) {
                dp[i] = nums[i];
            } else {
                dp[i] = nums[i] + dp[i - 1];
            }
            max = Math.max(dp[i], max);
        }
        return max;
    }

}
