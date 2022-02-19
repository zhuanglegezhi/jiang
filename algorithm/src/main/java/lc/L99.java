package lc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zz on 2021/8/1.
 */
public class L99 {

    public void recoverTree(TreeNode root) {
        List<Integer> inorder = new ArrayList<>();
        inorder(root, inorder);
        int[] pair = findTarget(inorder);
        doRecover(root, pair[0], pair[1], 2);
    }

    private void doRecover(TreeNode root, int first, int second, int count) {
        if (root == null || count == 0) return;
        if (root.val == first || root.val == second) {
            root.val = root.val == first ? second : first;
            count--;
            if (count == 0) {
                return;
            }
        }
        doRecover(root.left, first, second, count);
        doRecover(root.right, first, second, count);
    }


    private int[] findTarget(List<Integer> inorder) {
        int first = -1;
        int second = -1;
        for (int i = 0; i < inorder.size() - 1; i++) {
            if (inorder.get(i + 1) < inorder.get(i)) {
                second = inorder.get(i + 1);
                if (first == -1) {
                    first = inorder.get(i);
                } else {
                    break;
                }
            }

        }
        return new int[]{first, second};
    }

    private void inorder(TreeNode node, List<Integer> inorder) {
        if (node == null) return;
        inorder(node.left, inorder);
        inorder.add(node.val);
        inorder(node.right, inorder);
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
