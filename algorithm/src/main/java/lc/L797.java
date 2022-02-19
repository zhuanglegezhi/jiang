package lc;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by zz on 2021/11/7.
 */
public class L797 {

    private List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        dfs(graph, 0, new LinkedList<>());
        return ans;
    }

    private void dfs(int[][] graph, int i, LinkedList<Integer> path) {
        path.addLast(i);

        if (i == graph.length - 1) {
            ans.add(new ArrayList<>(path));
            path.removeLast();
            return;
        }

        for (int v : graph[i]) {
            dfs(graph, v, path);
        }

        path.removeLast();
    }

}
