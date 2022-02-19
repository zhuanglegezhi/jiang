package lc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zz on 2021/7/6.
 */
public class L90 {

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        if (nums == null) return ret;
        Arrays.sort(nums);
        ret.add(new ArrayList<>());
        dfs(ret, nums, new ArrayList<>(), 0);
        return ret;
    }

    private void dfs(List<List<Integer>> ret, int[] nums, List<Integer> path, int start) {
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            path.add(nums[i]);
            ret.add(new ArrayList<>(path));

            dfs(ret, nums, path, i + 1);
            path.remove(path.size() - 1);
        }
    }

}
