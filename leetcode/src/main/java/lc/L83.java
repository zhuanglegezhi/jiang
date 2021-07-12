package lc;

/**
 * Created by zz on 2021/7/12.
 */
public class L83 {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return null;
        ListNode dummy = new ListNode(0);
        ListNode dummyCurr = dummy;
        ListNode pre = head;
        ListNode curr = pre.next;


        while (pre != null) {
            if (curr != null) {
                if (pre.val != curr.val) {
                    dummyCurr.next = new ListNode(pre.val);
                    dummyCurr = dummyCurr.next;
                    pre = curr;
                }
                curr = curr.next;
            } else {
                dummyCurr.next = new ListNode(pre.val);
                dummyCurr = dummyCurr.next;
                pre = null;
            }
        }
        return dummy.next;
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
