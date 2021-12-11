package lc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zz on 2021/12/11.
 */
public class L22 {

    private List<String> ans = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
        backtrack("", n, n);
        return ans;
    }

    private void backtrack(String path, int remain_l, int remain_r) {
        if (remain_l == 0 && remain_r == 0) {
            ans.add(path);
            return;
        }

        if (remain_l > 0) {
            backtrack(path + '(', remain_l - 1, remain_r);
        }

        if (remain_r > 0 && remain_l < remain_r) {
            backtrack(path + ')', remain_l, remain_r - 1);
        }
    }

}
