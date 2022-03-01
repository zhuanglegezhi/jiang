package lc;

/**
 * Created by zz on 2021/9/5.
 */
public class L665 {

    public boolean checkPossibility(int[] nums) {
        if (nums.length <= 2) return true;
        int dp[] = new int[nums.length];                // 0:可以，没变过 1:可以，之前变过 2:不可以
        dp[0] = 0;
        dp[1] = nums[0] <= nums[1] ? 0 : 1;
        for (int i = 2; i < nums.length; i++) {
            if (nums[i] >= nums[i - 1]) {
                dp[i] = dp[i - 1];
            } else {
                if (dp[i - 1] != 0) {
                    dp[i] = 2;
                } else {
                    dp[i] = 1;
                    if (nums[i] >= nums[i - 2]) {
                        nums[i - 1] = nums[i - 2];
                    } else {
                        nums[i] = nums[i - 1];
                    }
                }
            }
        }
        return dp[nums.length - 1] != 2;
    }

    public static void main(String[] args) {
        new L665().checkPossibility(new int[]{-1, 4, 2, 3});
    }
}
