package lc;

/**
 * Created by zz on 2021/9/2.
 */
public class L342 {

    public boolean isPowerOfFour(int n) {
        if (n == 0) return false;
        while (Math.abs(n) > 1) {
            if (n % 4 != 0) {
                return false;
            }
            n = n / 4;
        }
        return n == 1;
    }

}
