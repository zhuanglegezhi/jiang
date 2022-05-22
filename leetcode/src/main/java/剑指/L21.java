package 剑指;

/**
 * Created by zz on 2022/5/22.
 */
public class L21 {

    public int[] exchange(int[] nums) {
        int l = 0, j = nums.length - 1;
        while (l < j) {
            while (nums[l] % 2 == 1 && l < j) {
                l++;
            }
            while (nums[j] % 2 == 0 && l < j) {
                j--;
            }
            if (l < j && nums[l] % 2 == 0 && nums[j] % 2 == 1) {
                int tmp = nums[l];
                nums[l] = nums[j];
                nums[j] = tmp;
                l++;
                j--;
            }
        }
        return nums;
    }

    public static void main(String[] args) {
        new L21().exchange(new int[]{1, 2, 3, 4});
    }

}
