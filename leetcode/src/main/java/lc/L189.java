package lc;

/**
 * Created by zz on 2021/9/5.
 */
public class L189 {

    public void rotate(int[] nums, int k) {
        int mid = k % nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, mid - 1);
        reverse(nums, mid, nums.length - 1);
    }

    private void reverse(int[] nums, int l, int r) {
        while (l < r) {
            int tmp = nums[l];
            nums[l] = nums[r];
            nums[r] = tmp;
            l++;
            r--;
        }
    }
}
