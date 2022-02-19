package lc;

/**
 * Created by zz on 2021/8/16.
 * <p>
 * "babad" -> "aba"
 */
public class L5 {

    public String longestPalindrome(String s) {
        if (s == null) return null;
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        int max = 0;
        int start = -1;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j <= i; j++) {
                if (i == j) {
                    dp[i][j] = true;
                }
                if (s.charAt(i) != s.charAt(j)) {
                    dp[i][j] = false;
                } else {
                    if (i - j < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i - 1][j + 1];
                    }
                }

                if (dp[i][j] && i - j + 1 > max) {
                    max = i - j + 1;
                    start = j;
                }
            }
        }
        return s.substring(start, start + max);
    }

    public static void main(String[] args) {
        String s = new L5().longestPalindrome("babad");
        int a = 0;
    }
}
