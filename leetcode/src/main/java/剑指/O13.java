package 剑指;

/**
 * Created by zz on 2022/5/26.
 */
public class O13 {

    private final int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private int count = 0;

    public int movingCount(int m, int n, int k) {
        boolean[][] visited = new boolean[m][n];
        dfs(0, 0, visited, m, n, k);
        return count;
    }

    private void dfs(int i, int j, boolean[][] visited, int m, int n, int k) {
        if (i < 0 || j < 0 || i >= m || j >= n || visited[i][j]) {
            return;
        }

        if (canVisit(i, j, k)) {
            visited[i][j] = true;
            count++;
            for (int[] direction : directions) {
                dfs(i + direction[0], j + direction[1], visited, m, n, k);
            }
        }
    }

    private boolean canVisit(int i, int j, int k) {
        int a = 0;
        while (i != 0) {
            a += i % 10;
            i = i / 10;
        }
        int b = 0;
        while (j != 0) {
            b += j % 10;
            j = j / 10;
        }
        return a + b <= k;
    }

    public static void main(String[] args) {
        System.out.println(new O13().movingCount(1, 2, 1));
    }
}
