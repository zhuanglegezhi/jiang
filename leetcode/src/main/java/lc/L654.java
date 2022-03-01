package lc;

/**
 * Created by zz on 2021/7/30.
 */
public class L654 {

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return construct(nums, 0, nums.length);
    }

    public TreeNode construct(int[] nums, int l, int r) {
        if (l == r) return null;
        int idx = max(nums, l, r);
        TreeNode root = new TreeNode(nums[idx]);
        root.left = construct(nums, l, idx);
        root.right = construct(nums, idx + 1, r);
        return root;
    }

    public int max(int[] nums, int l, int r) {
        int idx = l;
        for (int i = l; i < r; i++) {
            if (nums[idx] < nums[i])
                idx = i;
        }
        return idx;
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
