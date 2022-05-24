package 剑指;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zz on 2022/5/23.
 */
public class O7 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 前序：
     * ｜根｜----- 左子树 -----｜----- 右子树-----｜
     * <p>
     * preL                                 preR
     * 中序：
     * ｜----- 左子树 -----｜根｜----- 右子树-----｜
     * inL                index                inR
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            inOrderindexMap.put(inorder[i], i);
        }
        return buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    private TreeNode buildTree(int[] preorder, int preL, int preR,
                               int[] inorder, int inL, int inR) {
        if (preL > preR || inL > inR) {
            return null;
        }

        int rootVal = preorder[preL];
        TreeNode treeNode = new TreeNode(rootVal);

        int index = inOrderindexMap.get(rootVal);
        treeNode.left = buildTree(preorder, preL + 1, preL + index - inL, inorder, inL, index - 1);
        treeNode.right = buildTree(preorder, preL + index - inL + 1, preR, inorder, index + 1, inR);
        return treeNode;
    }

    private Map<Integer, Integer> inOrderindexMap = new HashMap<>();


}
