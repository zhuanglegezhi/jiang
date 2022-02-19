package lc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zz on 2021/7/26.
 */
public class L234 {

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

    public boolean isPalindrome(ListNode head) {
        if (head == null) return true;
        ListNode cur = head;
        List<ListNode> list = new ArrayList<>();
        while (cur != null) {
            list.add(cur);
            cur = cur.next;
        }
        for (int i = 0, j = list.size() - 1; i < j; i++, j--) {
            if (list.get(i).val != list.get(j).val) {
                return false;
            }
        }
        return true;
    }
}
