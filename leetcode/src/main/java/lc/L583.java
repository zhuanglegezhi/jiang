package lc;

/**
 * Created by zz on 2021/11/17.
 */
public class L583 {

    public int minDistance(String word1, String word2) {
        int lcs = calLCS(word1, word2);
        return word1.length() - lcs + word2.length() - lcs;
    }

    /**
     * 计算最大公共字串
     */
    private int calLCS(String s1, String s2) {
        if (s1 == null || s2 == null) return 0;

        int l1 = s1.length();
        int l2 = s2.length();
        int dp[][] = new int[l1 + 1][l2 + 1];
        for (int i = 1; i <= l1; i++) {
            for (int j = 1; j <= l2; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[l1][l2];
    }

}
