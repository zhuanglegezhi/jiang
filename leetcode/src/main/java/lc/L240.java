package lc;

/**
 * Created by zz on 2021/8/14.
 */
public class L240 {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        int row = matrix.length;
        int clum = matrix[0].length;
        int i = row - 1;
        int j = 0;
        while (i >= 0 && j < clum) {
            if (matrix[i][j] > target) {
                i--;
            } else if (matrix[i][j] < target) {
                j++;
            } else {
                return true;
            }
        }
        return false;
    }
}
