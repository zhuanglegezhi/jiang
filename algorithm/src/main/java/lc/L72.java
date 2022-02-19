package lc;

import java.util.Arrays;

/**
 * Created by zz on 2021/11/14.
 */
public class L72 {

    char[] c1;
    char[] c2;
    int dp[][];

    public int minDistance(String word1, String word2) {
        c1 = word1.toCharArray();
        c2 = word2.toCharArray();
        dp = new int[c1.length][c2.length];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        return calDp(c1.length - 1, c2.length - 1);
    }

    int calDp(int i, int j) {
        if (i < 0) return j + 1;
        if (j < 0) return i + 1;

        if (dp[i][j] != Integer.MAX_VALUE) {
            return dp[i][j];
        }

        int rs = calDp(i - 1, j - 1);
        if (c1[i] == c2[j]) {
            return rs;
        } else {
            rs = min(
                    calDp(i, j - 1) + 1,   //add
                    calDp(i - 1, j) + 1,   // delete
                    rs + 1    // replace
            );
        }

        dp[i][j] = rs;
        return rs;
    }

    private int min(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }

    public static void main(String[] args) {
        int ans = new L72().minDistance("horse", "ros");
        System.out.println(ans);
    }

}
