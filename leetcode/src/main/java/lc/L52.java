package lc;

import java.util.Arrays;

/**
 * Created by zz on 2021/10/5.
 */
public class L52 {

    private int cnt = 0;

    public int totalNQueens(int n) {
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            char[] row = new char[n];
            Arrays.fill(row, '.');
            board[i] = row;
        }

        backtrack(board, 0);
        return cnt;
    }

    private void backtrack(char[][] board, int row) {
        int N = board.length;

        if (row == N) {
            cnt++;
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
