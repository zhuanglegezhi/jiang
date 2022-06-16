package lc;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zz on 2022/6/9.
 */
public class L205 {

    public boolean isIsomorphic(String s, String t) {
        final Map<Character, Character> s2t = new HashMap<>();
        final Map<Character, Character> t2s = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char x = s.charAt(i);
            char y = t.charAt(i);

            if ((s2t.containsKey(x) && s2t.get(x) != y) || (t2s.containsKey(y) && t2s.get(y) != x)) {
                return false;
            } else {
                s2t.put(x, y);
                t2s.put(y, x);
            }
        }
        return true;
    }

}
