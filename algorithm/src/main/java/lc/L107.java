package lc;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by zz on 2021/8/15.
 */
public class L107 {

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

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        List<List<Integer>> tmp = new ArrayList<>();

        if (root == null) return ans;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> list = new ArrayList<>(levelSize);
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            tmp.add(list);
        }
        for (int i = tmp.size() - 1; i >= 0; i--) {
            ans.add(tmp.get(i));
        }
        return ans;
    }
}
