package lc;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by zz on 2021/11/11.
 */
public class L870 {

    public int[] advantageCount(int[] nums1, int[] nums2) {
        int n = nums1.length;
        /**
         * 0: idx 1:num
         */
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[1] - o1[1];
            }
        });
        for (int i = 0; i < nums2.length; i++) {
            priorityQueue.offer(new int[]{i, nums2[i]});
        }

        Arrays.sort(nums1);

        int left = 0;
        int right = n - 1;
        int res[] = new int[n];
        while (!priorityQueue.isEmpty()) {
            int[] node = priorityQueue.poll();
            int idx = node[0];
            int maxInNum2 = node[1];
            if (nums1[right] > maxInNum2) {
                res[idx] = nums1[right];
                right--;
            } else {
                res[idx] = nums1[left];
                left++;
            }
        }
        return res;
    }


}
