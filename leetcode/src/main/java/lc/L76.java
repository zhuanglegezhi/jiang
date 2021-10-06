package lc;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zz on 2021/10/6.
 */
public class L76 {

    public String minWindow(String s, String t) {
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        for (char c : t.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }
        int valid = 0;
        int l = 0;
        int r = 0;          // [l,r)
        int start = 0;
        int min = Integer.MAX_VALUE;

        // 向右走
        while (r < s.length()) {
            // 窗口扩张
            char rChar = s.charAt(r);
            r++;
            if (need.containsKey(rChar)) {
                window.put(rChar, window.getOrDefault(rChar, 0) + 1);
                if (need.get(rChar).equals(window.get(rChar))) {
                    valid++;
                }
            }

            // 收缩窗口
            while (valid == need.size()) {
                if (r - l < min) {
                    start = l;
                    min = r - l;
                }

                char leftChar = s.charAt(l);
                l++;

                if (need.containsKey(leftChar)) {
                    // 只有原来相等的情况，-1会不满足，进而进行更新valid
                    if (window.get(leftChar).equals(need.get(leftChar))) {
                        valid--;
                    }
                    window.put(leftChar, window.get(leftChar) - 1);
                }
            }
        }

        return min == Integer.MAX_VALUE ? "" : s.substring(start, start + min);
    }

    public static void main(String[] args) {
        String s = new L76().minWindow("aaaaaaaaaaaabbbbbcdd", "abcdd");
        int a = 0;
    }

}
