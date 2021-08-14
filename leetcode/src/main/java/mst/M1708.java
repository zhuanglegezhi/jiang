package mst;

import java.util.Arrays;

/**
 * Created by zz on 2021/8/14.
 */
public class M1708 {

    public int bestSeqAtIndex(int[] height, int[] weight) {
        int len = height.length;
        int[][] person = new int[len][2];
        for (int i = 0; i < height.length; i++) {
            person[i] = new int[]{height[i], weight[i]};
        }
        Arrays.sort(person, (o1, o2) -> {
            if (o1[0] == o2[0])
                return Integer.compare(o1[1], o2[1]);
            else
                return Integer.compare(o1[0], o2[0]);
        });

        int dp[] = new int[len];
        int max = 0;
        for (int i = 0; i < len; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (person[i][1] > person[j][1] && person[i][0] > person[j][0]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(dp[i], max);
        }
        return max;
    }
}
