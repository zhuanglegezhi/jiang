package lc;

/**
 * Created by zz on 2021/8/22.
 */
public class L152 {

    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int len = nums.length;
        int[] maxDp = new int[len];
        int[] minDp = new int[len];
        maxDp[0] = nums[0];
        minDp[0] = nums[0];
        int max = maxDp[0];

        for (int i = 1; i < len; i++) {
            maxDp[i] = Math.max(Math.max(maxDp[i - 1] * nums[i], minDp[i - 1] * nums[i]), nums[i]);
            minDp[i] = Math.min(Math.min(maxDp[i - 1] * nums[i], minDp[i - 1] * nums[i]), nums[i]);
            max = Math.max(max, maxDp[i]);
        }
        return max;
    }
}
