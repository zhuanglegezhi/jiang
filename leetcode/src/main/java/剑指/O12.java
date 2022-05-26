package 剑指;

/**
 * Created by zz on 2022/5/25.
 */
public class O12 {

    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                boolean flag = check(board, word, visited, i, j, 0);
                if (flag) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean check(char[][] board, String word, boolean[][] visited, int i, int j, int k) {
        if (board[i][j] != word.charAt(k)) {
            return false;
        } else if (k == word.length() - 1) {
            return true;
        } else {
            boolean result = false;
            visited[i][j] = true;
            for (int[] direction : directions) {
                int _i = i + direction[0];
                int _j = j + direction[1];
                if (_i >= 0 && _i < board.length && _j >= 0 && _j < board[0].length) {
                    if (!visited[_i][_j]) {
                        boolean flag = check(board, word, visited, _i, _j, k + 1);
                        if (flag) {
                            result = true;
                            break;
                        }
                    }
                }
            }

            // 回溯还原
            visited[i][j] = false;
            return result;
        }
    }

    private final int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

}
