package lc;

import java.util.Arrays;

/**
 * Created by zz on 2021/8/29.
 */
public class L169 {

    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        for (int s = 0, j = 1; j < nums.length; ) {
            if (nums[j] != nums[j - 1]) {
                s = j;
            } else {
                if (j - s + 1 > nums.length / 2) {
                    return nums[j];
                }
            }
            j++;
        }
        return nums[0];
    }

}
