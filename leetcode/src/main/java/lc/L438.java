package lc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zz on 2021/10/6.
 */
public class L438 {

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> list = new ArrayList<>();
        if (p.length() > s.length()) {
            return list;
        }

        Map<Character, Integer> window = new HashMap<>();
        Map<Character, Integer> need = new HashMap<>();
        for (char c : p.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }
        int l = 0;
        int r = 0;
        int valid = 0;

        while (r < s.length()) {
            char rc = s.charAt(r);
            r++;

            if (need.containsKey(rc)) {
                window.put(rc, window.getOrDefault(rc, 0) + 1);
                if (window.get(rc).equals(need.get(rc))) {
                    valid++;
                }
            }

            while (r - l >= p.length()) {
                if (valid == need.size()) {
                    list.add(l);
                }

                char lc = s.charAt(l);
                l++;

                if (need.containsKey(lc)) {
                    if (window.get(lc).equals(need.get(lc))) {
                        valid--;
                    }
                    window.put(lc, window.getOrDefault(lc, 0) - 1);
                }
            }
        }
        return list;
    }

}
