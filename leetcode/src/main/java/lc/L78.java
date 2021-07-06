package lc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zz on 2021/7/6.
 */
public class L78 {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        if (nums == null) return ret;
        ret.add(new ArrayList<>());
        dfs(ret, nums, new ArrayList<>(), 0);
        return ret;
    }

    private void dfs(List<List<Integer>> ret, int[] nums, List<Integer> path, int start) {
        for (int i = start; i < nums.length; i++) {
            path.add(nums[i]);
            ret.add(new ArrayList<>(path));

            dfs(ret, nums, path, i + 1);
            path.remove(path.size() - 1);
        }
    }
}
