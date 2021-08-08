package lc;

/**
 * Created by zz on 2021/8/8.
 */
public class L543 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private int ans = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        maxDepth(root);
        return ans;
    }

    /**
     * @param root
     * @return root的深度
     */
    private int maxDepth(TreeNode root) {
        if (root == null) return 0;
        // 这里注意，不能把空直接给递归处理，为空的情况下深度为0
        int l = root.left == null ? 0 : maxDepth(root.left) + 1;
        int r = root.right == null ? 0 : maxDepth(root.right) + 1;
        ans = Math.max(ans, l + r);
        return Math.max(l, r);
    }
}
