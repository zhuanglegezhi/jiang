package 剑指;

/**
 * Created by zz on 2021/8/8.
 */
public class O55_1 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    private int max = 0;

    public int maxDepth(TreeNode root) {
        dfs(root, 0);
        return max;
    }

    private void dfs(TreeNode root, int depth) {
        if (root == null) return;
        ;
        depth++;
        max = Math.max(max, depth);
        dfs(root.left, depth);
        dfs(root.right, depth);
    }
}
