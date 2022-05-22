package 剑指;

/**
 * Created by zz on 2022/5/22.
 */
public class O4 {
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        int row = matrix.length;
        if (row == 0) {
            return false;
        }

        int collum = matrix[0].length;
        int i = row - 1, j = 0;
        while (i >= 0 && j < collum) {
            if (target == matrix[i][j]) {
                return true;
            } else if (target > matrix[i][j]) {
                j++;
            } else {
                i--;
            }
        }
        return false;
    }
}
