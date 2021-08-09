package 面试题;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zz on 2021/8/9.
 */
public class M0406 {

    private List<TreeNode> list = new ArrayList<>();

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        inOrder(root);
        for (int i = 0; i < list.size(); i++) {
            if (p.val == list.get(i).val) {
                if (i + 1 < list.size()) {
                    return list.get(i + 1);
                }
            }
        }
        return null;
    }

    private void inOrder(TreeNode root) {
        if (root == null) return;
        inOrder(root.left);
        list.add(root);
        inOrder(root.right);
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
