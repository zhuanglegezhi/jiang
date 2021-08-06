package lc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zz on 2021/8/6.
 * <p>
 * 利用好二叉搜索树中序遍历是升序的特性
 */
public class L783 {

    private List<Integer> list = new ArrayList<>();

    public int minDiffInBST(TreeNode root) {
        inOrder(root);
        return findMin();
    }

    private int findMin() {
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < list.size(); i++) {
            min = Math.min(min, list.get(i) - list.get(i - 1));
        }
        return min;
    }

    private void inOrder(TreeNode root) {
        if (root == null) return;
        inOrder(root.left);
        list.add(root.val);
        inOrder(root.right);
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
