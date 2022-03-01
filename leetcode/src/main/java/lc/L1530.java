package lc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zz on 2021/8/7.
 */
public class L1530 {

    private int ans = 0;

    public int countPairs(TreeNode root, int distance) {
        dfs(root, distance);
        return ans;
    }

    private List<Integer> dfs(TreeNode root, int distance) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;
        if (root.left == null && root.right == null) {
            list.add(1);
            return list;
        }

        // 后续遍历
        List<Integer> left = dfs(root.left, distance);
        List<Integer> right = dfs(root.right, distance);

        for (int len1 : left) {
            for (int len2 : right) {
                if (len1 + len2 <= distance) {
                    ans++;
                }
            }
        }

        for (int len1 : left) {
            len1++;
            if (len1 > distance) {
                continue;
            }
            list.add(len1);
        }
        for (int len1 : right) {
            len1++;
            if (len1 > distance) {
                continue;
            }
            list.add(len1);
        }
        return list;
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
