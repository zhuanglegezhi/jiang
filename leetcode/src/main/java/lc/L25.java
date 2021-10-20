package lc;

/**
 * Created by zz on 2021/10/20.
 */
public class L25 {

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) return null;
        ListNode a = head, b = head;
        for (int i = 0; i < k; i++) {
            if (b == null) return a;
            b = b.next;
        }
        ListNode reversed = reversePart(a, b);
        a.next = reverseKGroup(b, k);
        return reversed;
    }

    private ListNode reversePart(ListNode a, ListNode b) {
        ListNode pre = null, cur = a;
        while (cur != b) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
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
