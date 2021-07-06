package lc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zz on 2021/7/6.
 */
public class L40 {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> ret = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return ret;
        }
        Arrays.sort(candidates);
        boolean[] used = new boolean[candidates.length];
        dfs(candidates, target, ret, new ArrayList<>(), used, 0);
        return ret;
    }

    private void dfs(int[] candidates, int target, List<List<Integer>> ret, List<Integer> path, boolean[] used, int index) {
        if (target == 0) {
            ret.add(new ArrayList<>(path));
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            if (i > index && candidates[i] == candidates[i - 1]) {
                continue;
            }
            if (candidates[i] > target) {
                break;
            }
            used[i] = true;
            path.add(candidates[i]);
            dfs(candidates, target - candidates[i], ret, path, used, i + 1);
            // backtrack
            used[i] = false;
            path.remove(path.size() - 1);
        }

    }
}
