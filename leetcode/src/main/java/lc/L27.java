package lc;

/**
 * Created by zz on 2021/11/12.
 */
public class L27 {

    public int removeElement(int[] nums, int val) {
        int slow = 0;
        int fast = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[fast] != val) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow + 1;
    }

}
