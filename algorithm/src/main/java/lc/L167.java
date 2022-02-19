package lc;

/**
 * Created by zz on 2021/8/29.
 */
public class L167 {

    public int[] twoSum(int[] numbers, int target) {
        int i = 0;
        int j = numbers.length - 1;
        while (i < j) {
            int tmp = numbers[i] + numbers[j];
            if (tmp == target) {
                return new int[]{i + 1, j + 1};
            }
            if (tmp < target) {
                i++;
            } else {
                j--;
            }
        }
        return null;
    }

}
