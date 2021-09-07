package mst;

/**
 * Created by zz on 2021/9/6.
 * <p>
 * 从左到右从上到下扫描一次矩阵。
 * 如果格子值是 0，则分别向上和向左扫描直到第一个不是 0 的格子，那么最大方阵的上界就是 min(向左延伸的长度, 向上延伸的长度)。
 * 逐步尝试[1, 上界] 直到无法形成方阵，最后可行的方阵就是以当前点能 形成的最大方阵。
 * 扫描过程维护最大值，最后返回最大值以及顶点信息即可。
 * 现在的难点只剩下第三点部分如何逐步尝试[1, 上界]。实际上这个也不难，只需要：
 * 在向左延伸的同时向上探测
 * 在向上延伸的同时向左探测
 */
public class M1723 {

    public int[] findSquare(int[][] matrix) {
        int[] res = new int[0];
        int[][][] dp = new int[2][matrix.length + 1][matrix[0].length + 1];
        int max = 0;
        for (int i = 1; i <= matrix.length; i++) {
            for (int j = 1; j <= matrix[0].length; j++) {
                if (matrix[i - 1][j - 1] == 0) {
                    dp[0][i][j] = dp[0][i - 1][j] + 1;
                    dp[1][i][j] = dp[1][i][j - 1] + 1;
                    int bound = Math.min(dp[0][i][j], dp[1][i][j]);
                    for (int k = 0; k < bound; k++) {
                        if (dp[1][i - k][j] >= k + 1 && dp[0][i][j - k] >= k + 1) {
                            if (k + 1 > max) {
                                res = new int[3];
                                max = k + 1;
                                res[0] = i - k - 1;
                                res[1] = j - k - 1;
                                res[2] = max;
                            }
                        }
                    }
                }
            }
        }
        return res;
    }
}
