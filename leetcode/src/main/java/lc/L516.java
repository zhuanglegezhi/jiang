package lc;

/**
 * Created by zz on 2021/11/19.
 */
public class L516 {

    public int longestPalindromeSubseq(String s) {
        if (s == null) return 0;
        char[] arr = s.toCharArray();
        int len = arr.length;
        int[][] dp = new int[len][len];
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i; j < len; j++) {
                if (i == j) {
                    dp[i][j] = 1;
                } else {
                    if (arr[i] == arr[j]) {
                        dp[i][j] = dp[i + 1][j - 1] + 2;
                    } else {
                        dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
                    }

                }
            }
        }

        return dp[0][len - 1];
    }

}
