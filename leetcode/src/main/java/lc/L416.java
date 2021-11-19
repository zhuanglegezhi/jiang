package lc;

/**
 * Created by zz on 2021/11/19.
 */
public class L416 {

    public boolean canPartition(int[] nums) {
        if (nums == null) return false;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 == 1) return false;
        sum = sum / 2;
        int len = nums.length;

        boolean dp[][] = new boolean[len + 1][sum + 1];
        for (int i = 0; i <= len; i++)
            dp[i][0] = true;

        for (int i = 1; i <= len; i++) {
            for (int j = 1; j <= sum; j++) {
                if (j - nums[i - 1] < 0) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                }
            }
        }

        return dp[len][sum];
    }

}
