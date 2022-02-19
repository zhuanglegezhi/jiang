package lc;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zz on 2021/8/2.
 */
public class L662 {

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

    private Map<Integer, Integer> map = new HashMap<>();
    private int max = 0;

    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        dfs(root, 0, 1);
        return max;

    }

    private void dfs(TreeNode root, int depth, int pos) {
        if (root == null) return;
        if (!map.containsKey(depth)) {
            map.put(depth, pos);
        }
        max = Math.max(max, pos - map.get(depth) + 1);
        dfs(root.left, depth + 1, 2 * pos);
        dfs(root.right, depth + 1, 2 * pos + 1);
    }
}
