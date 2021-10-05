package mst;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zz on 2021/10/5.
 */
public class M0812 {

    List<List<String>> ans = new ArrayList<>();


    public List<List<String>> solveNQueens(int n) {
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            char[] row = new char[n];
            Arrays.fill(row, '.');
            board[i] = row;
        }

        backtrack(board, 0);
        return ans;
    }

    private void backtrack(char[][] board, int row) {
        int N = board.length;

        if (row == N) {
            List<String> solution = new ArrayList<>(N);
            for (char[] arr : board) {
                StringBuilder sb = new StringBuilder();
                for (char c : arr) {
                    sb.append(c);
                }
                solution.add(sb.toString());
            }

            ans.add(solution);
            return;
        }

        for (int col = 0; col < N; col++) {
            if (!isValid(board, row, col)) {
                continue;
            }

            board[row][col] = 'Q';
            backtrack(board, row + 1);
            board[row][col] = '.';
        }
    }

    private boolean isValid(char[][] path, int row, int col) {
        // 同列不行
        for (int i = 0; i < row; i++) {
            if (path[i][col] == 'Q')
                return false;
        }

        // 左上方判断
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (path[i][j] == 'Q')
                return false;
        }

        // 右上方判断
        for (int i = row - 1, j = col + 1; i >= 0 && j < path.length; i--, j++) {
            if (path[i][j] == 'Q')
                return false;
        }

        return true;
    }

}
