package lc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zz on 2021/7/26.
 */
public class L34 {
    private List<List<Integer>> ret = new ArrayList<>();
    private List<Integer> path = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int target) {
        dfs(root, 0, target);
        return ret;
    }

    private void dfs(TreeNode node, int sum, int target) {
        if (node == null) return;
        sum = sum + node.val;
        path.add(node.val);
        if (sum == target && node.left == null && node.right == null) {
            ret.add(new ArrayList<>(path));
        }
        dfs(node.left, sum, target);
        dfs(node.right, sum, target);
        path.remove(path.size() - 1);
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
