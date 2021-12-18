package 剑指;

/**
 * Created by zz on 2021/12/18.
 */
public class O68_1 {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root.val > p.val && root.val > q.val) {
            //  在左子树
            return lowestCommonAncestor(root.left, p, q);
        } else if (root.val < p.val && root.val < q.val) {
            //  在右子树
            return lowestCommonAncestor(root.right, p, q);
        } else {
            // 一左一右
            return root;
        }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
