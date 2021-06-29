package lc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zz on 2021/6/29.
 */
public class L46 {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        dfs(ret, nums, used, new ArrayList<>());
        return ret;
    }

    private void dfs(List<List<Integer>> ret, int[] nums, boolean[] used, List<Integer> tmp) {
        if (tmp.size() == nums.length) {
            ret.add(new ArrayList<>(tmp));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                used[i] = true;
                tmp.add(nums[i]);
                dfs(ret, nums, used, tmp);

                used[i] = false;
                tmp.remove(tmp.size() - 1);
            }
        }
    }
}
