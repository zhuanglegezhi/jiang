package 剑指;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by zz on 2022/5/17.
 */
public class O3 {
    public int findRepeatNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i : nums) {
            if (set.contains(i)) {
                return i;
            } else {
                set.add(i);
            }
        }
        return -1;
    }
}
