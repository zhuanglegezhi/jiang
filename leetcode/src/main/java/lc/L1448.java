package lc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zz on 2021/8/5.
 */
public class L1448 {

    private int count = 0;

    public int goodNodes(TreeNode root) {
        dfs(root, new ArrayList<>());
        return count;
    }

    private void dfs(TreeNode root, List<Integer> path) {
        if (root == null) return;
        boolean good = true;
        for (int i = 0; i < path.size(); i++) {
            if (path.get(i) > root.val) {
                good = false;
                break;
            }
        }
        if (good) {
            count++;
        }
        path.add(root.val);
        dfs(root.left, path);
        dfs(root.right, path);
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
