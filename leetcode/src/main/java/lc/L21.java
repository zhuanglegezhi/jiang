package lc;

/**
 * Created by zz on 2021/7/12.
 */
public class L21 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) return null;
        ListNode head = new ListNode();
        ListNode cur = head;

        while (l1 != null || l2 != null) {
            ListNode node = new ListNode();
            cur.next = node;
            if (l1 != null && l2 != null) {
                if (l1.val <= l2.val) {
                    node.val = l1.val;
                    l1 = l1.next;
                } else {
                    node.val = l2.val;
                    l2 = l2.next;
                }
            } else if (l1 != null) {
                node.val = l1.val;
                l1 = l1.next;
            } else if (l2 != null) {
                node.val = l2.val;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        return head.next;
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
