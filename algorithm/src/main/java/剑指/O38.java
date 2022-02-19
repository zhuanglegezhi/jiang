package 剑指;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by zz on 2021/6/29.
 */
public class O38 {

    public String[] permutation(String s) {
        Set<String> set = new HashSet<>();
        boolean[] visited = new boolean[s.length()];
        dfs(set, s, "", visited);
        return set.toArray(new String[set.size()]);
    }

    private void dfs(Set<String> set, String s, String tmp, boolean[] visited) {
        if (tmp.length() == s.length()) {
            set.add(tmp);
            return;
        }
        for (int i = 0; i < s.length(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(set, s, tmp + s.charAt(i), visited);
                visited[i] = false;
            }
        }
    }


}
