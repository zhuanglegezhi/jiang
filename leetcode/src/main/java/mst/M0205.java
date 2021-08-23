package mst;

/**
 * Created by zz on 2021/8/23.
 */
public class M0205 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode cur1 = l1;
        ListNode cur2 = l2;
        ListNode dummy = new ListNode(-1);
        ListNode cur3 = dummy;
        boolean carry = false;
        while (cur1 != null || cur2 != null || carry) {
            int v = 0;
            if (cur1 != null) {
                v += cur1.val;
                cur1 = cur1.next;
            }

            if (cur2 != null) {
                v += cur2.val;
                cur2 = cur2.next;
            }

            if (carry) {
                v += 1;
            }

            carry = v >= 10;
            cur3.next = new ListNode(v % 10);
            cur3 = cur3.next;
        }
        return dummy.next;
    }


    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
