package lc;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Created by zz on 2021/10/5.
 */
public class L752 {

    private static final String INIT = "0000";

    public int openLock(String[] deadends, String target) {
        if (target.equals(INIT)) return 0;
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        for (String dead : deadends) {
            if (dead.equals(INIT)) {
                return -1;
            }
            visited.add(dead);
        }
        queue.offer(INIT);
        visited.add(INIT);
        int step = 0;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                String cur = queue.poll();
                if (target.equals(cur)) return step;

                for (int j = 0; j < 4; j++) {
                    String plus = plus(cur, j);
                    if (!visited.contains(plus)) {
                        queue.offer(plus);
                        visited.add(plus);
                    }

                    String minus = minus(cur, j);
                    if (!visited.contains(minus)) {
                        queue.offer(minus);
                        visited.add(minus);
                    }
                }
            }
            step++;
        }
        return -1;
    }

    private String minus(String cur, int i) {
        char[] arr = cur.toCharArray();
        if (arr[i] == '0') {
            arr[i] = '9';
        } else {
            arr[i] -= 1;
        }
        return new String(arr);
    }

    private String plus(String cur, int i) {
        char[] arr = cur.toCharArray();
        if (arr[i] == '9') {
            arr[i] = '0';
        } else {
            arr[i] += 1;
        }
        return new String(arr);
    }

}
