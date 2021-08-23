package lc;

/**
 * Created by zz on 2021/8/23.
 */
public class L88 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] tmp = new int[m];
        for (int i = 0; i < m; i++) {
            tmp[i] = nums1[i];
        }
        int i = 0;
        int j = 0;
        int cur = 0;
        while (true) {
            if (i < m && j < n) {
                if (tmp[i] <= nums2[j]) {
                    nums1[cur++] = tmp[i++];
                } else {
                    nums1[cur++] = nums2[j++];
                }
            } else if (i < m) {
                nums1[cur++] = tmp[i++];
            } else if (j < n) {
                nums1[cur++] = nums2[j++];
            } else {
                break;
            }
        }
    }
}
