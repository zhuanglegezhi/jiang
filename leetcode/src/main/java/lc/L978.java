package lc;

/**
 * Created by zz on 2021/8/22.
 */
public class L978 {

    public int maxTurbulenceSize(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        int[] dp = new int[arr.length];
        dp[0] = 1;
        int max = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == arr[i - 1]) {
                dp[i] = 1;
                continue;
            }
            if (i == 1) {
                dp[1] = 2;
            } else {
                if (dp[i - 1] == 1) {
                    dp[i] = 2;
                } else {
                    dp[i] = arr[i] > arr[i - 1] ? (arr[i - 1] < arr[i - 2] ? dp[i - 1] + 1 : 2) :
                            (arr[i - 1] > arr[i - 2] ? dp[i - 1] + 1 : 2);
                }

            }
            max = Math.max(dp[i], max);
        }
        return max;
    }

}
