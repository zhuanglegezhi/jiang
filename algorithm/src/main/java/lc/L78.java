package lc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zz on 2021/7/6.
 */
public class L78 {

    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        if (nums == null) return ans;
        ans.add(new ArrayList<>());
        backtrack(nums, new ArrayList<>(), 0);
        return ans;
    }

    private void backtrack(int[] nums, List<Integer> path, int start) {
        for (int i = start; i < nums.length; i++) {
            path.add(nums[i]);
            ans.add(new ArrayList<>(path));

            backtrack(nums, path, i + 1);

            path.remove(path.size() - 1);
        }
    }
}
