package lc;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by zz on 2022/6/16.
 */
public class L128 {

    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int max = 0;
        for (int num : nums) {
            if (set.contains(num - 1)) {
                continue;
            }
            int end = num;
            while (set.contains(end)) {
                end++;
            }
            max = Math.max(max, end - num);
        }
        return max;

    }
}
