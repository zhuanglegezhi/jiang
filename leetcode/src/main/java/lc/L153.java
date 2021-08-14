package lc;

/**
 * Created by zz on 2021/8/14.
 */
public class L153 {

    public int findMin(int[] nums) {
        if (nums == null) return -1;
        int l = 0;
        int r = nums.length - 1;
        while (l < r) {
            if (nums[l] < nums[r]) {
                return nums[l];
            }

            int mid = l + (r - l) / 2;
            if (nums[mid] > nums[r]) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return nums[l];
    }
}
