package 剑指;

/**
 * Created by zz on 2021/6/29.
 */
public class O10_1 {

    public int fib(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;

        int[] arr = new int[n + 1];
        arr[0] = 0;
        arr[1] = 1;
        for (int i = 2; i <= n; i++) {
            arr[i] = (arr[i - 1] + arr[i - 2]) % 1000000007;
        }
        return arr[n];
    }

}
