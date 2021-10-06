package lc;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zz on 2021/10/6.
 */
public class L3 {

    public int lengthOfLongestSubstring(String s) {
        // c->count
        Map<Character, Integer> window = new HashMap<>();
        int max = 0;
        int l = 0;
        int r = 0;
        while (r < s.length()) {
            char rc = s.charAt(r);
            r++;

            window.put(rc, window.getOrDefault(rc, 0) + 1);

            while (window.get(rc) > 1) {
                char lc = s.charAt(l);
                l++;
                // lc一定在窗口内
                window.put(lc, window.getOrDefault(lc, 0) - 1);
            }
            max = Math.max(max, r - l);
        }
        return max;
    }

    public static void main(String[] args) {
        new L3().lengthOfLongestSubstring("abba");
    }
}
