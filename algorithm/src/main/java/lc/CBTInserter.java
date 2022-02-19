package lc;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by zz on 2021/7/30.
 * <p>
 * // leetcode-894
 */
public class CBTInserter {

    private TreeNode root;
    // 保存可以插入的节点的双端队列
    private Deque<TreeNode> deque;

    public CBTInserter(TreeNode root) {
        this.root = root;
        this.deque = new LinkedList<>();

        // bfs使用
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left == null || node.right == null) {
                deque.offerLast(node);
            }
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }

    public int insert(int v) {
        TreeNode node = deque.peekFirst();
        TreeNode newNode = new TreeNode(v);
        deque.offerLast(newNode);
        if (node.left == null) {
            node.left = newNode;
        } else {
            node.right = newNode;
            deque.pollFirst();
        }
        return node.val;
    }

    public TreeNode get_root() {
        return root;
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
