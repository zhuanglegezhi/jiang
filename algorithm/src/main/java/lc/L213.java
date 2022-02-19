package lc;

/**
 * Created by zz on 2021/10/10.
 */
public class L213 {

    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];
        return Math.max(rob(nums, 0, n - 2), rob(nums, 1, n - 1));
    }

    private int rob(int[] nums, int start, int end) {
        if (start < 0 || end < 0 || start >= nums.length || end >= nums.length) return 0;

        int len = end - start + 1;
        int[] dp = new int[len];
        dp[0] = nums[start];
        for (int i = 1; i < len; i++) {
            if (i == 1) {
                dp[i] = Math.max(nums[start], nums[start + 1]);
            } else {
                dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[start + i]);
            }
        }
        return dp[len - 1];
    }

}
