package lc;

/**
 * Created by zz on 2021/8/11.
 */
public class L33 {

    public int search(int[] nums, int target) {
        if (nums == null) return -1;
        if (nums.length == 1) return nums[0] == target ? 0 : -1;
        int l = 0;
        int r = nums.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            //左边有序
            if (nums[0] <= nums[mid]) {
                if (target < nums[mid] && target >= nums[0]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                if (target > nums[mid] && target <= nums[nums.length - 1]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return -1;
    }

}
