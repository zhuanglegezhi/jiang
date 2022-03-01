package lc;

/**
 * Created by zz on 2021/7/31.
 * <p>
 * //todo 后面在自己写一遍
 */
public class L450 {

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;
        if (root.val < key) {
            root.right = deleteNode(root.right, key);
        } else if (root.val > key) {
            root.left = deleteNode(root.left, key);
        } else {
            if (root.right == null && root.left == null) {
                return null;
            } else if (root.right != null) {
                root.val = minRight(root);
                root.right = deleteNode(root.right, root.val);
            } else {
                root.val = maxLeft(root);
                root.left = deleteNode(root.left, root.val);
            }
        }
        return root;
    }

    private int maxLeft(TreeNode node) {
        node = node.left;
        while (node.right != null) node = node.right;
        return node.val;
    }

    private int minRight(TreeNode node) {
        node = node.right;
        while (node.left != null) node = node.left;
        return node.val;
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
