package lc;

/**
 * Created by zz on 2021/9/2.
 */
public class L263 {

    public boolean isUgly(int n) {
        if (n < 1) return false;
        if (n == 1) return true;
        while (n % 2 == 0) {
            n = n / 2;
        }
        while (n % 3 == 0) {
            n = n / 3;
        }
        while (n % 5 == 0) {
            n = n / 5;
        }
        return n == 1;
    }

    public static void main(String[] args) {
        new L263().isUgly(6);
    }
}
