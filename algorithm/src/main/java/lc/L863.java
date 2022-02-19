package lc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zz on 2021/8/1.
 */
public class L863 {

    private Map<TreeNode, TreeNode> map = new HashMap<>();
    private List<Integer> ret = new ArrayList<>();

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        fillMap(root, null);
        dfs(target, null, 0, k);
        return ret;
    }

    private void dfs(TreeNode node, TreeNode from, int depth, int k) {
        if (node == null) return;
        if (depth == k) {
            ret.add(node.val);
            return;
        }

        if (node.left != from) {
            dfs(node.left, node, depth + 1, k);
        }

        if (node.right != from) {
            dfs(node.right, node, depth + 1, k);
        }

        if (map.get(node) != from) {
            dfs(map.get(node), node, depth + 1, k);
        }
    }

    private void fillMap(TreeNode node, TreeNode parent) {
        if (node == null) return;
        map.put(node, parent);
        fillMap(node.left, node);
        fillMap(node.right, node);
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
