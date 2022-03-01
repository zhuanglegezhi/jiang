package lc;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by zz on 2021/11/28.
 */
public class L452 {

    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        int count = 1;
        int x_end = points[0][1];
        for (int i = 1; i < points.length; i++) {
            int[] point = points[i];

            int start = point[0];
            if (start > x_end) {
                count++;
                x_end = point[1];
            }
        }

        return count;
    }
}
