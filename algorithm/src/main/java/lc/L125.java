package lc;

/**
 * Created by zz on 2021/8/29.
 */
public class L125 {

    public boolean isPalindrome(String s) {
        int i = 0, j = s.length() - 1;
        char[] arr = s.toLowerCase().toCharArray();

        while (i < j) {
            if (!isNeedCheck(arr[i])) {
                i++;
            } else if (!isNeedCheck(arr[j])) {
                j--;
            } else if (arr[i] != arr[j]) {
                return false;
            } else {
                i++;
                j--;
            }
        }
        return true;
    }

    private boolean isNeedCheck(char c) {
        return (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9');
    }
}
