package lc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zz on 2021/8/5.
 */
public class L1022 {

    private int sum = 0;

    public int sumRootToLeaf(TreeNode root) {
        dfs(root, new ArrayList<>());
        return sum;
    }

    private void dfs(TreeNode root, List<Integer> path) {
        if (root == null) {
            return;
        }
        path.add(root.val);
        if (root.left == null && root.right == null) {
            sum += calculate(path);
        } else {
            dfs(root.left, path);
            dfs(root.right, path);
        }
        path.remove(path.size() - 1);
    }

    private int calculate(List<Integer> path) {
        int ret = 0;
        for (int i = 0; i < path.size(); i++) {
            ret += path.get(i) * Math.pow(2, path.size() - i - 1);
        }
        return ret;
    }


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
