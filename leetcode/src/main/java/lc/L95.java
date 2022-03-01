package lc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zz on 2021/11/1.
 */
public class L95 {

    public List<TreeNode> generateTrees(int n) {
        return generate(1, n);
    }

    private List<TreeNode> generate(int min, int max) {
        List<TreeNode> ans = new ArrayList<>();
        if (min > max) {
            ans.add(null);
            return ans;
        }
        for (int i = min; i <= max; i++) {
            List<TreeNode> lefts = generate(min, i - 1);
            List<TreeNode> rights = generate(i + 1, max);
            // 乘法原理
            for (TreeNode left : lefts) {
                for (TreeNode right : rights) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    ans.add(root);
                }
            }
        }
        return ans;
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
