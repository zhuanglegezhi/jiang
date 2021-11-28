package lc;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by zz on 2021/11/28.
 */
public class L435 {

    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        int count = 0;
        int x_end = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            int[] interval = intervals[i];

            int start = interval[0];
            if (start >= x_end) {
                x_end = interval[1];
            } else {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        new L435().eraseOverlapIntervals(new int[][]{{1, 2}, {2, 3}, {3, 4}, {1, 3}});
    }
}
