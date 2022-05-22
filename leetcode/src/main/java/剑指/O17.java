package 剑指;

/**
 * Created by zz on 2022/5/22.
 */
public class O17 {

    public int[] printNumbers(int n) {
        int size = (int) (Math.pow(10, n) - 1);
        int[] arr = new int[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        return arr;
    }

}
