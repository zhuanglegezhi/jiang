package lc;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zz on 2021/10/6.
 */
public class L567 {

    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) return false;
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        for (char c : s1.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }
        int l = 0;
        int r = 0;
        int valid = 0;

        while (r < s2.length()) {
            char rc = s2.charAt(r);
            r++;

            if (need.containsKey(rc)) {
                window.put(rc, window.getOrDefault(rc, 0) + 1);
                if (window.get(rc).equals(need.get(rc))) {
                    valid++;
                }
            }

            while (r - l >= s1.length()) {
                if (valid == need.size()) {
                    return true;
                }

                char lc = s2.charAt(l);
                l++;

                if (need.containsKey(lc)) {
                    if (window.get(lc).equals(need.get(lc))) {
                        valid--;
                    }
                    window.put(lc, window.getOrDefault(lc, 0) - 1);
                }
            }
        }
        return false;
    }

}
