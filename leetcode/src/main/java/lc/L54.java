package lc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zz on 2022/6/6.
 */
public class L54 {

    private final int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        List<Integer> result = new ArrayList<>();
        boolean[][] visited = new boolean[m][n];
        int dir = 0;
        for (int i = 0, j = 0, count = 0; count < m * n; count++) {
            result.add(matrix[i][j]);
            visited[i][j] = true;
            int tmpI = directions[dir][0] + i;
            int tmpJ = directions[dir][1] + j;

            if (tmpI >= 0 && tmpI < m && tmpJ >= 0 && tmpJ < n && !visited[tmpI][tmpJ]) {

            } else {
                dir = (1 + dir) % 4;
            }
            i = directions[dir][0] + i;
            j = directions[dir][1] + j;
        }
        return result;
    }
}
