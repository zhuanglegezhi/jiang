package 剑指;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zz on 2022/6/7.
 */
public class O50 {

    public char firstUniqChar(String s) {
        if (s == null || s.length() == 0) {
            return ' ';
        }
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            Integer cnt = map.get(c);
            if (cnt == null) {
                map.put(c, 1);
            } else {
                map.put(c, cnt + 1);
            }
        }

        for (char c : s.toCharArray()) {
            Integer cnt = map.get(c);
            if (cnt == 1) {
                return c;
            }
        }
        return ' ';
    }

}
