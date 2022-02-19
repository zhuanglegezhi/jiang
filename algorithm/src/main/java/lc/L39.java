package lc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zz on 2021/7/4.
 */
public class L39 {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ret = new ArrayList<>();
        Arrays.sort(candidates);
        dfs(candidates, target, ret, 0, new ArrayList<>());
        return ret;
    }

    private void dfs(int[] candidates, int target, List<List<Integer>> ret, int start, List<Integer> path) {
        if (target == 0) {
            ret.add(new ArrayList<>(path));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            if (candidates[i] > target) {
                break;
            }
            path.add(candidates[i]);
            dfs(candidates, target - candidates[i], ret, i, path);
            path.remove(path.size() - 1);
        }
    }

}
