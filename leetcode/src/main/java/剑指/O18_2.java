package 剑指;

/**
 * Created by zz on 2021/12/19.
 */
public class O18_2 {

    public ListNode deleteNode(ListNode head, int val) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode pre = dummy;
        ListNode cur = head;
        while (cur != null && cur.val != val) {
            pre = pre.next;
            cur = cur.next;
        }
        if (cur != null) {
            pre.next = cur.next;
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
