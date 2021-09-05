package lc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zz on 2021/9/5.
 */
public class L821 {
    public int[] shortestToChar(String s, char c) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == c) {
                list.add(i);
            }
        }

        int[] ans = new int[s.length()];
        int idx = 0;
        for (int i = 0; i < s.length(); ) {
            if (idx + 1 == list.size()) {
                ans[i] = Math.abs(i - list.get(idx));
            } else {
                ans[i] = Math.min(Math.abs(i - list.get(idx)),
                        Math.abs(i - list.get(idx + 1)));
            }
            i++;
            if (idx + 1 < list.size() && i >= list.get(idx + 1)) {
                idx++;
            }
        }
        return ans;
    }
}
