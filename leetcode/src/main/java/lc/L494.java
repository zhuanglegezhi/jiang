package lc;

/**
 * Created by zz on 2021/11/14.
 */
public class L494 {

    private int ans = 0;

    public int findTargetSumWays(int[] nums, int target) {
        return backtrack(nums, target);
    }

    private int backtrack(int[] nums, int target) {
        doBacktrack(nums, 0, target);
        return ans;
    }

    private void doBacktrack(int[] nums, int i, int rest) {
        if (i == nums.length) {
            if (rest == 0) {
                ans++;
            }
            return;
        }

        doBacktrack(nums, i + 1, rest + nums[i]);
        doBacktrack(nums, i + 1, rest - nums[i]);
    }

}
