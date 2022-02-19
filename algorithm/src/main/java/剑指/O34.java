package 剑指;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zz on 2021/7/30.
 */
public class O34 {

    private List<List<Integer>> ret = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int target) {
        if (root == null) return ret;
        dfs(root, target, 0, new ArrayList<>());
        return ret;
    }

    private void dfs(TreeNode node, int target, int sum, List<Integer> path) {
        if (node == null) return;
        sum += node.val;
        path.add(node.val);
        if (sum == target && node.left == null && node.right == null) {
            ret.add(new ArrayList<>(path));
            // todo 这里不能return！！要进行回溯，清除path
//            return;
        }

        dfs(node.left, target, sum, path);
        dfs(node.right, target, sum, path);
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
