package lc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zz on 2021/7/4.
 */
public class L842 {

    public List<Integer> splitIntoFibonacci(String num) {
        List<Integer> ret = new ArrayList<>();
        if (num == null) return ret;
        dfs(num.toCharArray(), ret, 0);
        return ret;
    }

    private boolean dfs(char[] arr, List<Integer> list, int start) {
        if (start == arr.length && list.size() >= 3) {
            return true;
        }
        for (int i = start; i < arr.length; i++) {
            // 0开头不合法
            if (arr[start] == '0' && i > start) {
                break;
            }
            // 截取start到i之间的数
            long num = toNum(arr, start, i);
            // 大小校验
            if (num > Integer.MAX_VALUE) break;
            int size = list.size();
            // 太大了就不继续截取了，没必要
            if (size >= 2 && num > list.get(size - 1) + list.get(size - 2)) {
                break;
            }
            // 找到了，或者是前两个，那就进行回溯
            if (size < 2 || num == list.get(size - 1) + list.get(size - 2)) {
                list.add((int) num);
                if (dfs(arr, list, i + 1)) {
                    return true;
                }
                list.remove(list.size() - 1);
            }
            // 走到这里，没取到，会i++，继续找
        }

        return false;
    }

    private long toNum(char[] arr, int start, int end) {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i <= end; i++) {
            sb.append(arr[i]);
        }
        return Long.parseLong(sb.toString());
    }

}
