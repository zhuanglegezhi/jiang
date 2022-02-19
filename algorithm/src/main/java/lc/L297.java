package lc;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by zz on 2021/11/6.
 */
public class L297 {

    public String serialize(TreeNode root) {
        return dfs(root, new StringBuilder()).toString();
    }

    public TreeNode deserialize(String data) {
        List<String> dataList = new LinkedList<>(Arrays.asList(data.split(",")));
        return rdeserialize(dataList);
    }

    public StringBuilder dfs(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("None,");
        } else {
            sb.append(root.val).append(",");

            sb = dfs(root.left, sb);
            sb = dfs(root.right, sb);
        }
        return sb;
    }

    public TreeNode rdeserialize(List<String> dataList) {
        if (dataList.get(0).equals("None")) {
            dataList.remove(0);
            return null;
        }

        TreeNode root = new TreeNode(Integer.valueOf(dataList.get(0)));
        dataList.remove(0);
        root.left = rdeserialize(dataList);
        root.right = rdeserialize(dataList);

        return root;
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
