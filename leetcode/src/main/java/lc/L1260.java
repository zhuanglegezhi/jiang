package lc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zz on 2021/9/5.
 */
public class L1260 {

    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        for (int i = 0; i < k; i++) {
            grid = transfer(grid);
        }
        List<List<Integer>> ans = new ArrayList<>();
        for (int[] subGrid : grid) {
            List<Integer> list = new ArrayList<>();
            for (int num : subGrid) {
                list.add(num);
            }
            ans.add(list);
        }
        return ans;
    }

    private int[][] transfer(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] ret = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == m - 1 && j == n - 1) {
                    ret[0][0] = grid[i][j];
                } else if (j == n - 1) {
                    ret[i + 1][0] = grid[i][j];
                } else {
                    ret[i][j + 1] = grid[i][j];
                }
            }
        }
        return ret;
    }

}
