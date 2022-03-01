package lc;

/**
 * Created by zz on 2021/8/29.
 */
public class L172 {

    public int trailingZeroes(int n) {
        int count_5 = 0;
        for (int i = 1; i <= n; i++) {
            int tmp = i;
            while (tmp > 0) {
                if (tmp % 5 == 0) {
                    tmp = tmp / 5;
                    count_5++;
                } else {
                    break;
                }
            }
        }
        return count_5;
    }
}
