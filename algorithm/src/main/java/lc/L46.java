package lc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zz on 2021/6/29.
 */
public class L46 {

    private List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        boolean[] used = new boolean[nums.length];
        dfs(nums, used, new ArrayList<>());
        return ans;
    }

    private void dfs(int[] nums, boolean[] used, List<Integer> path) {
        if (path.size() == nums.length) {
            ans.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                used[i] = true;
                path.add(nums[i]);
                dfs(nums, used, path);

                used[i] = false;
                path.remove(path.size() - 1);
            }
        }
    }

}
