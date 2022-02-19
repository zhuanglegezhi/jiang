package lc;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by zz on 2021/8/8.
 */
public class L101 {

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

    public boolean isSymmetric(TreeNode root) {
        return check(root, root);
    }

    private boolean check(TreeNode node1, TreeNode node2) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(node1);
        queue.add(node2);
        while (!queue.isEmpty()) {
            node1 = queue.poll();
            node2 = queue.poll();

            if (node1 == null && node2 == null) {
                continue;
            } else if (node1 == null || node2 == null || node1.val != node2.val) {
                return false;
            }

            queue.add(node1.left);
            queue.add(node2.right);

            queue.add(node1.right);
            queue.add(node2.left);
        }
        return true;
    }
}
