package lc;

/**
 * Created by zz on 2021/8/9.
 */
public class L875 {

    public int minEatingSpeed(int[] piles, int h) {
        int max = 0;
        for (int pile : piles) {
            max = Math.max(pile, max);
        }
        int l = 1;
        int r = max;
        while (l < r) {
            int mid = (r - l) / 2 + l;
            if (total(piles, mid) > h) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }

    private int total(int[] piles, int mid) {
        int sum = 0;
        for (int pile : piles) {
            sum += (pile - 1) / mid + 1;
        }
        return sum;
    }

}

