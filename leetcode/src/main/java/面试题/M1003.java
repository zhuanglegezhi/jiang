package 面试题;

/**
 * Created by zz on 2021/8/11.
 */
public class M1003 {
    public int search(int[] arr, int target) {
        if (arr == null || arr.length == 0) return -1;
        int len = arr.length;
        int l = 0;
        int r = len - 1;
        int ans = -1;
        while (l <= r) {
            if (arr[l] == target) return l;
            int mid = l + (r - l) / 2;
            if (arr[l] == arr[mid]) {
                l++;
            } else if (arr[l] < arr[mid]) {
                // 左<中

                if (arr[l] > target || arr[mid] < target) {
                    l = mid;
                } else {
                    l = l + 1;
                    r = mid;
                }

            } else {
                // 左>中

                if (arr[l] > target && arr[mid] < target) {
                    l = mid;
                } else {
                    l = l + 1;
                    r = mid;
                }
            }
        }
        return ans;
    }
}
