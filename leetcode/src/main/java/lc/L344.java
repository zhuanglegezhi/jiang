package lc;

/**
 * Created by zz on 2021/11/11.
 */
public class L344 {

    public void reverseString(char[] s) {
        if (s == null) return;
        int i = 0, j = s.length - 1;
        while (i < j) {
            char tmp = s[i];
            s[i] = s[j];
            s[j] = tmp;
            i++;
            j--;
        }
    }
}
