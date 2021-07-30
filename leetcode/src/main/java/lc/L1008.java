package lc;

/**
 * Created by zz on 2021/7/30.
 */
public class L1008 {

    public TreeNode bstFromPreorder(int[] preorder) {
        if (preorder == null || preorder.length == 0) return null;
        return build(preorder, 0, preorder.length - 1);
    }

    private TreeNode build(int[] preorder, int start, int end) {
        if (start > end || end >= preorder.length || start < 0) return null;
        TreeNode node = new TreeNode(preorder[start]);
        int idx = findIdx(preorder, start, end);
        if (idx >= 0) {
            node.left = build(preorder, start + 1, idx - 1);
            node.right = build(preorder, idx, end);
        } else {
            node.left = build(preorder, start + 1, end);
        }
        return node;
    }

    private int findIdx(int[] preorder, int start, int end) {
        int target = preorder[start];
        for (int i = start + 1; i <= end && i < preorder.length; i++) {
            if (preorder[i] > target) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        L1008 l1008 = new L1008();
        int[] arr = new int[]{4, 2};
        TreeNode node = l1008.bstFromPreorder(arr);
        int a = 0;
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
