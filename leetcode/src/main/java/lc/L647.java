package lc;

/**
 * Created by zz on 2022/5/16.
 */
public class L647 {

    public int countSubstrings(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            count += check(s, i, i);
            count += check(s, i, i + 1);
        }
        return count;
    }

    private int check(String str, int l, int r) {
        int count = 0;
        while (l >= 0 && r <= str.length() - 1 && str.charAt(l) == str.charAt(r)) {
            count++;
            l--;
            r++;
        }
        return count;
    }

}
