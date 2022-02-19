package lc;

/**
 * Created by zz on 2021/7/12.
 */
public class L82 {

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return null;
        ListNode pre = null;
        ListNode cur = head;
        ListNode next = cur.next;

        ListNode retHead = new ListNode();
        ListNode retCurr = retHead;

        while (cur != null) {
            if (pre == null) {
                if (next == null || cur.val != next.val) {
                    retCurr.next = new ListNode(cur.val);
                    retCurr = retCurr.next;
                }
            } else {
                if (pre.val != cur.val && (next == null || next.val != cur.val)) {
                    retCurr.next = new ListNode(cur.val);
                    retCurr = retCurr.next;
                }
            }
            pre = cur;
            cur = next;
            next = next == null ? null : next.next;
        }
        return retHead.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(3, new ListNode(4, new ListNode(4, new ListNode(5)))))));
        ListNode merged = new L82().deleteDuplicates(head);
        int a = 0;
    }

    public static class ListNode {
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
