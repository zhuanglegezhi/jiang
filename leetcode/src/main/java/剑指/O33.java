package 剑指;

/**
 * Created by zz on 2021/12/19.
 */
public class O33 {

    public boolean verifyPostorder(int[] postorder) {
        return verify(postorder, 0, postorder.length - 1);
    }

    private boolean verify(int[] postorder, int i, int j) {
        if (i >= j) {
            return true;
        }

        int idx = i;
        while (postorder[idx] < postorder[j]) {
            idx++;
        }
        // m是左右子树的分界点，左子树都小于postorder[j]
        int m = idx;

        // 右子树都大于postorder[j]
        while (postorder[idx] > postorder[j]) {
            idx++;
        }

        return idx == j && verify(postorder, i, m - 1) && verify(postorder, m, j - 1);
    }
}
