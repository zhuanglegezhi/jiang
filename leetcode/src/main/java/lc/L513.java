package lc;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by zz on 2021/7/26.
 */
public class L513 {

    public int findBottomLeftValue(TreeNode root) {
        if (root == null) return -1;
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int tmp = -1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.pop();
                if (i == 0) {
                    tmp = node.val;
                }
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }
        return tmp;
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
