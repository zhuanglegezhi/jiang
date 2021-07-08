package lc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zz on 2021/7/8.
 */
public class L131 {

    private List<List<String>> ret = new ArrayList<>();

    public List<List<String>> partition(String s) {
        if (s == null || s.length() == 0) return ret;
        char[] arr = s.toCharArray();
        dfs(arr, new ArrayList<>(), 0);
        return ret;
    }

    private void dfs(char[] arr, List<String> path, int start) {
        if (start == arr.length) {
            ret.add(new ArrayList<>(path));
            return;
        }

        for (int i = start; i < arr.length; i++) {
            String tmp = concatValid(arr, start, i);
            if (tmp == null) {
                continue;
            } else {
                path.add(tmp);
                dfs(arr, path, i + 1);
                path.remove(path.size() - 1);
            }

        }
    }

    private String concatValid(char[] arr, int start, int end) {
        StringBuilder sb = new StringBuilder();
        for (int i = start, j = end; i <= end; i++, j--) {
            if (arr[i] != arr[j]) {
                return null;
            }
            sb.append(arr[i]);
        }
        return sb.toString();
    }

}