package lc;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zz on 2021/10/10.
 */
public class L337 {
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

    private Map<TreeNode, Integer> map = new HashMap<>();

    public int rob(TreeNode root) {
        if (root == null) return 0;
        if (map.containsKey(root)) {
            return map.get(root);
        }

        int no = rob(root.left) + rob(root.right);
        int yes = root.val
                + (root.left == null ? 0 : (rob(root.left.left) + rob(root.left.right)))
                + (root.right == null ? 0 : (rob(root.right.left) + rob(root.right.right)));
        int value = Math.max(yes, no);
        map.put(root, value);
        return value;
    }

}
