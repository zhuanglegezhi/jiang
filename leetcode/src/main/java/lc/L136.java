package lc;

import java.util.Arrays;

/**
 * Created by zz on 2021/8/29.
 */
public class L136 {
    public int singleNumber(int[] nums) {
        Arrays.sort(nums);
        for (int i = nums.length - 1; i >= 0; ) {
            if (i == 0) return nums[0];
            if (nums[i] == nums[i - 1]) {
                i = i - 2;
            } else {
                return nums[i];
            }
        }
        return nums[0];
    }
}
