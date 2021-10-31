package lc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zz on 2021/10/31.
 */
public class L652 {

    private final Map<String, Integer> map = new HashMap<>();
    private final List<TreeNode> ans = new ArrayList<>();
    private static final String SEP = ",";

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        calPath(root);
        return ans;
    }

    private String calPath(TreeNode root) {
        if (root == null) return "#";
        String left = calPath(root.left);
        String right = calPath(root.right);
        String path = root.val + SEP + left + SEP + right;
        Integer count = map.getOrDefault(path, 0);
        if (count == 1) {
            ans.add(root);
        }
        map.put(path, count + 1);
        return path;
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
