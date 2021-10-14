package lc;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by zz on 2021/10/14.
 */
public class L23 {

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        PriorityQueue<ListNode> deque = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        for (ListNode node : lists) {
            if (node != null) {
                deque.add(node);
            }
        }

        ListNode dummy = new ListNode();
        ListNode cur = dummy;
        while (!deque.isEmpty()) {
            ListNode top = deque.poll();

            cur.next = new ListNode(top.val);
            cur = cur.next;
            if (top.next != null) {
                deque.add(top.next);
            }
        }
        return dummy.next;
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
