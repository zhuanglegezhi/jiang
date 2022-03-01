package 剑指;

/**
 * Created by zz on 2021/10/6.
 */
public class O53 {

    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return 0;
        int l = left(nums, target);
        int r = right(nums, target);
        if (l == -1 || r == -1) {
            return 0;
        } else {
            return r - l + 1;
        }
    }

    private int right(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target) {
                l = mid + 1;
            } else if (nums[mid] < target) {
                l = mid + 1;
            } else if (nums[mid] > target) {
                r = mid - 1;
            }
        }

        if (r < 0 || nums[r] != target)
            return -1;

        return r;
    }

    private int left(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target) {
                r = mid - 1;
            } else if (nums[mid] < target) {
                l = mid + 1;
            } else if (nums[mid] > target) {
                r = mid - 1;
            }
        }

        if (l > nums.length - 1 || nums[l] != target)
            return -1;

        return l;
    }

}
