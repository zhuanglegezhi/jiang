package lc;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by zz on 2021/8/29.
 */
public class L219 {

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                return true;
            }
            set.add(nums[i]);
            if (set.size() > k) {
                set.remove(nums[i - k]);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        new L219().containsNearbyDuplicate(new int[]{1, 2, 3, 1}, 3);
    }
}
