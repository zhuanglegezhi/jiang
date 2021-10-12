package lc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zz on 2021/10/13.
 */
public class L18 {

    private List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                twoSum(nums, target, j + 1, nums[i], nums[j]);
                while (j < len - 1 && nums[j + 1] == nums[j]) j++;
            }
            while (i < len - 1 && nums[i + 1] == nums[i]) i++;
        }
        return ans;
    }

    private void twoSum(int[] nums, int final_target, int start, int one, int two) {
        int l = start, r = nums.length - 1;
        int target = final_target - one - two;
        while (l < r) {
            int left = nums[l], right = nums[r];
            int sum = left + right;
            if (sum < target) {
                while (l < r && nums[l] == left) l++;
            } else if (sum > target) {
                while (l < r && nums[r] == right) r--;
            } else {
                List<Integer> list = new ArrayList<>(Arrays.asList(one, two, left, right));
                ans.add(list);
                while (l < r && nums[l] == left) l++;
                while (l < r && nums[r] == right) r--;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new L18().fourSum(new int[]{2, 2, 2, 2, 2}, 8));
    }

}
