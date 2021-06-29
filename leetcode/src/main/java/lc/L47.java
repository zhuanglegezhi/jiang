package lc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zz on 2021/6/29.
 */
public class L47 {

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        Arrays.sort(nums);
        dfs(ret, nums, used, new ArrayList<>());
        return ret;
    }

    private void dfs(List<List<Integer>> ret, int[] nums, boolean[] used, List<Integer> path) {
        if (nums.length == path.size()) {
            ret.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i] || (i > 0 && nums[i] == nums[i - 1] && !used[i - 1])) {
                continue;
            }

            used[i] = true;
            path.add(nums[i]);
            dfs(ret, nums, used, path);
            used[i] = false;
            path.remove(path.size() - 1);
        }
    }
}
