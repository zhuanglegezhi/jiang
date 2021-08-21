package lc;

/**
 * Created by zz on 2021/8/21.
 */
public class L26 {

    public int removeDuplicates(int[] nums) {
        if (nums == null) return 0;
        int slow = 0;
        for (int fast = 1; fast < nums.length; fast++) {
            if (nums[fast] != nums[slow]) {
                slow++;
                nums[slow] = nums[fast];
            }
        }
        return slow + 1;
    }
}
