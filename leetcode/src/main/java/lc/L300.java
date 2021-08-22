package lc;

/**
 * Created by zz on 2021/8/14.
 */
public class L300 {

    public int lengthOfLIS(int[] nums) {
        if (nums == null) return 0;
        int dp[] = new int[nums.length];
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(dp[i], max);
        }
        return max;
    }
}