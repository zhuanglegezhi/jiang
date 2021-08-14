package mst;

/**
 * Created by zz on 2021/8/4.
 */
public class M0412 {

    private int count = 0;

    public int pathSum(TreeNode root, int sum) {
        int depth = deep(root);
        int[] arr = new int[depth];
        doSum(root, sum, arr, 0);
        return count;
    }

    private void doSum(TreeNode root, int sum, int[] arr, int level) {
        if (root == null) return;
        arr[level] = root.val;
        int tmp = 0;
        for (int i = level; i >= 0; i--) {
            tmp += arr[i];
            if (tmp == sum) {
                count++;
            }
        }

        doSum(root.left, sum, arr, level + 1);
        doSum(root.right, sum, arr, level + 1);

        arr[level] = Integer.MIN_VALUE;
    }

    private int deep(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(deep(root.right), deep(root.left)) + 1;
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
