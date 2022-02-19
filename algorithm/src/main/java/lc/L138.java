package lc;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zz on 2021/7/21.
 */
public class L138 {
    public Node copyRandomList(Node head) {
        Map<Node, Node> map = new HashMap<>();
        return dfs(map, head);
    }

    private Node dfs(Map<Node, Node> map, Node cur) {
        if (cur == null) return null;
        Node cloned = map.get(cur);
        if (cloned == null) {
            cloned = new Node(cur.val);
            map.put(cur, cloned);
        } else {
            // 生成过就不重新生成了
            return cloned;
        }
        cloned.next = dfs(map, cur.next);
        cloned.random = dfs(map, cur.random);

        return cloned;
    }

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}
