package lc;

/**
 * Created by zz on 2021/7/13.
 */
public class L92 {

    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode cur = head;
        ListNode pre = dummy;
        int count = 1;

        while (count < n) {
            if (count < m) {
                pre = cur;
                cur = cur.next;
            } else {
                ListNode next = cur.next;
                cur.next = next.next;
                next.next = pre.next;
                pre.next = next;
            }
            count++;
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
