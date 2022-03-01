package lc;

import java.util.Arrays;

/**
 * Created by zz on 2021/9/5.
 */
public class L455 {

    public int findContentChildren(int[] g, int[] s) {
        int count = 0;
        if (g == null || s == null) return 0;
        Arrays.sort(g);
        Arrays.sort(s);
        int i = 0;
        int j = 0;
        while (i < g.length && j < s.length) {
            if (g[i] <= s[j]) {
                count++;
                i++;
            }
            j++;
        }
        return count;
    }
}
