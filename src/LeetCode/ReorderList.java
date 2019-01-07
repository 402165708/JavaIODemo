package LeetCode;

/**
 * @Description :Given a singly linked list L: L0→L1→…→Ln-1→Ln,
 * reorder it to: L0→Ln →L1→Ln-1→L2→Ln-2→…
 * <p>
 * You must do this in-place without altering the nodes' values.
 * <p>
 * For example,
 * Given{1,2,3,4}, reorder it to{1,4,2,3}.
 * @Author : chenzhitao
 * @Date : Created in 23:09 2018/12/25
 */
public class ReorderList {

    public void reorderList(ListNode head) {
        if (head == null) {
            return;
        }

        int count = countInList(head);
        if (count ==1 || count == 2){
            return;
        }else {
            ListNode midNode = null;
            //逆转后半部分
//            reverseHalfLastList(head,count/2,)
        }




    }

    private int countInList(ListNode head) {
        int eleCount = 1;
        ListNode index = head;
        while (index.next != null) {
            index = index.next;
            eleCount++;
        }
        return eleCount;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
