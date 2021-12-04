package lc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zz on 2021/10/5.
 */
public class L51 {

    private static final char Q = 'Q';
    private static final char NOT_Q = '.';
    private final List<List<String>> ans = new ArrayList<>();


    public List<List<String>> solveNQueens(int n) {
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            char[] row = new char[n];
            Arrays.fill(row, NOT_Q);
            board[i] = row;
        }
        backtrack(board, 0);
        return ans;
    }

    private void backtrack(char[][] board, int row) {
        int n = board.length;
        if (row == n) {
            List<String> solution = new ArrayList<>(n);
            for (char[] arr : board) {
                StringBuilder sb = new StringBuilder();
                for (char c : arr) {
                    sb.append(c);
                }
                solution.add(sb.toString());
            }

            ans.add(solution);
            // 加结果
            return;
        }

        for (int col = 0; col < n; col++) {
            if (valid(board, row, col)) {
                board[row][col] = Q;
                backtrack(board, row + 1);
                board[row][col] = NOT_Q;
            }
        }
    }

    private boolean valid(char[][] board, int row, int col) {
        int n = board.length;
        // 同列不行
        for (int i = 0; i < row; i++) {
            if (board[i][col] == Q) {
                return false;
            }
        }

        // 左上方判断
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == Q)
                return false;
        }

        // 右上方判断
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == Q)
                return false;
        }

        return true;
    }


    public static void main(String[] args) {
        List<List<String>> ans = new L51().solveNQueens(4);
        int a = 0;
    }

}
