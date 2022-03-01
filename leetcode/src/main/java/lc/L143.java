package lc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zz on 2021/7/26.
 */
public class L143 {

    public void reorderList(ListNode head) {
        if (head == null) return;
        List<ListNode> nodes = new ArrayList<>();
        ListNode cur = head;
        while (cur != null) {
            nodes.add(cur);
            cur = cur.next;
        }
        for (int i = 0, j = nodes.size() - 1; j - i > 1; i++, j--) {
            nodes.get(j - 1).next = null;
            nodes.get(j).next = nodes.get(i).next;
            nodes.get(i).next = nodes.get(j);
        }
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
