package 剑指;

/**
 * Created by zz on 2021/6/28.
 */
public class O25 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) return null;
        ListNode curr = new ListNode(-1);
        ListNode head = curr;
        while (l1 != null || l2 != null) {
            int value;
            if (l1 != null && l2 != null) {
                if (l1.val < l2.val) {
                    value = l1.val;
                    l1 = l1.next;
                } else {
                    value = l2.val;
                    l2 = l2.next;
                }
            } else if (l1 != null) {
                value = l1.val;
                l1 = l1.next;
            } else {
                value = l2.val;
                l2 = l2.next;
            }
            curr.next = new ListNode(value);
            curr = curr.next;
        }

        return head.next;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
