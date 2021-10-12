package lc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zz on 2021/10/13.
 */
public class L15 {

    private List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i - 1 >= 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            twoSum(nums, -nums[i], i + 1);
        }
        return ans;
    }

    private void twoSum(int[] nums, int target, int start) {
        int l = start, r = nums.length - 1;
        while (l < r) {
            int left = nums[l], right = nums[r];
            int sum = left + right;
            if (sum < target) {
                while (l < r && nums[l] == left) l++;
            } else if (sum > target) {
                while (l < r && nums[r] == right) r--;
            } else {
                List<Integer> list = new ArrayList<>(Arrays.asList(-target, left, right));
                ans.add(list);
                while (l < r && nums[l] == left) l++;
                while (l < r && nums[r] == right) r--;
            }
        }
    }

    public static void main(String[] args) {
        new L15().threeSum(new int[]{-1, 0, 1, 2, -1, -4});
    }
}
