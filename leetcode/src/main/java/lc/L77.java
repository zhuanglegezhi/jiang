package lc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zz on 2021/12/5.
 */
public class L77 {

    private List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = i + 1;
        }
        backtrack(nums, k, new ArrayList<>(), 0);
        return ans;
    }

    private void backtrack(int[] nums, int k, List<Integer> path, int start) {
        if (k == 0) {
            ans.add(new ArrayList<>(path));
            return;
        }

        for (int i = start; i < nums.length; i++) {
            path.add(nums[i]);
            backtrack(nums, k - 1, path, i + 1);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        new L77().combine(4, 2);
    }

}
