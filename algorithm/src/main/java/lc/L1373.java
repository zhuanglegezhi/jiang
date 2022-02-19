package lc;

/**
 * Created by zz on 2021/11/3.
 */
public class L1373 {
    int ans = 0;

    public int maxSumBST(TreeNode root) {
        traverse(root);
        return ans;
    }

    // 后序遍历二叉树，递归
    // 返回以root为根的二叉树[是否是二叉搜索树，最小节点值，最大节点值，节点值之和]
    public int[] traverse(TreeNode root) {
        if (root == null) {
            return new int[]{1, Integer.MAX_VALUE, Integer.MIN_VALUE, 0};
        }

        int[] leftTree = traverse(root.left);
        int[] rightTree = traverse(root.right);

        int[] res = new int[4];

        // 判断以root为根节点的二叉树是否是二叉搜索树
        if (leftTree[0] == 1 && rightTree[0] == 1 && root.val > leftTree[2] && root.val < rightTree[1]) {
            res[0] = 1;
            res[1] = Math.min(root.val, leftTree[1]);
            res[2] = Math.max(root.val, rightTree[2]);
            res[3] = root.val + leftTree[3] + rightTree[3];
            ans = Math.max(res[3], ans);
        }
        // 如果不是搜索二叉树，直接返回res默认初始化值[0,0,0,0]即可，因为用不到了
        return res;
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
