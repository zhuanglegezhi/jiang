package mst;

/**
 * Created by zz on 2021/8/21.
 */
public class M1712 {

    TreeNode head = new TreeNode(-1);   // 为了返回单向链表的头节点而多设的一个节点
    TreeNode pre = null;               // 指向当前节点的前一个节点

    public TreeNode convertBiNode(TreeNode root) {
        dfs(root);
        return head.right;
    }

    public void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.left);
        if (pre == null) {
            pre = root;
            head.right = root;
        } else {
            pre.right = root;
            pre = root;
        }
        root.left = null;
        dfs(root.right);
    }


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {

    }
}
