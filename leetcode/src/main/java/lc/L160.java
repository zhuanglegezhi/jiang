package lc;

/**
 * Created by zz on 2021/8/22.
 */
public class L160 {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode curA = headA;
        ListNode curB = headB;
        while (curA != curB) {
            curA = curA.next;
            curB = curB.next;
            if (curA == null && curB == null) {
                return null;
            }
            if (curA == null) {
                curA = headB;
            }
            if (curB == null) {
                curB = headA;
            }
        }
        return curA;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
