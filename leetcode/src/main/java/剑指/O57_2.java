package 剑指;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zz on 2021/12/18.
 */
public class O57_2 {

    public int[][] findContinuousSequence(int target) {
        List<int[]> list = new ArrayList<>();
        for (int l = 1, r = 2; l < r; ) {
            int sum = (l + r) * (r - l + 1) / 2;

            if (sum < target) {
                r++;
            } else if (sum > target) {
                l++;
            } else {
                int[] sub = new int[r - l + 1];
                for (int i = 0; i < sub.length; i++) {
                    sub[i] = i + l;
                }
                list.add(sub);
                l++;
            }
        }

        return list.toArray(new int[list.size()][]);
    }
}
