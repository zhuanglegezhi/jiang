package lc;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by zz on 2021/9/2.
 */
public class L349 {

    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) {
            return null;
        }
        Set<Integer> set = new HashSet<>();
        for (int num : nums1) {
            set.add(num);
        }
        Set<Integer> set1 = new HashSet<>();
        for (int num : nums2) {
            if (set.contains(num)) {
                set1.add(num);
            }
        }
        int[] re = new int[set1.size()];
        int i = 0;
        for (Integer n : set1) {
            re[i] = n;
            i++;
        }
        return re;
    }
}
