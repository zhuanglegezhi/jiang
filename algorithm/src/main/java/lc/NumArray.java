package lc;

/**
 * Created by zz on 2021/11/8.
 */
public class NumArray {

    private int[] preSum;

    public NumArray(int[] nums) {
        preSum = new int[nums.length];
        preSum[0] = nums[0];
        for (int i = 1; i < preSum.length; i++) {
            preSum[i] = preSum[i - 1] + nums[i];
        }
    }

    public int sumRange(int i, int j) {
        if (i == 0) {
            return preSum[j];
        } else {
            return preSum[j] - preSum[i - 1];
        }
    }

}
