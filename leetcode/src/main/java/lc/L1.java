package lc;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zz on 2021/8/21.
 */
public class L1 {

    public int[] twoSum(int[] nums, int target) {
        if (nums == null) return null;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            Integer idx = map.get(target - nums[i]);
            if (idx != null && idx != i) {
                return new int[]{i, idx};
            }
        }
        return null;
    }
}
