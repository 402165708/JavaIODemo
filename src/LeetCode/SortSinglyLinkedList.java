package LeetCode;

/**
 * @Description :Sort a linked list in O(n log n) time using constant space complexity.
 * 单链表排序，时间复杂度O(n log n)，常量空间 ----> 快排, 排序过程交换value，不交换位置
 * @Author : chenzhitao
 * @Date : Created in 20:21 2018/12/25
 */
public class SortSinglyLinkedList {

    public static void main(String[] args) {
        SortSinglyLinkedList demo = new SortSinglyLinkedList();

        ListNode head = new ListNode(2);
        ListNode one = new ListNode(1);
        head.next = one;

        demo.sortList(head);


    }


    public ListNode sortList(ListNode head) {

        if (head == null) {
            throw new NullPointerException("Param is null!");
        }
        ListNode pStart = head, pEnd = null;
        quickSort(head, pStart, pEnd);

        return head;

    }


    private void quickSort(ListNode head, ListNode pStart, ListNode pEnd) {
        if (head == null) {
            return;
        }
        if (pStart.next == pEnd) {
            // 单个元素
            return;
        }

        ListNode pMid = partitionInEndExclude(head, pStart, pEnd);
        if (pMid != pStart) {
            quickSort(head, pStart, pMid);
        }
        if (pMid.next != pEnd) {
            quickSort(head, pMid.next, pEnd);
        }

    }

    /**
     * 切分方法
     *
     * @param head
     * @param pStart include
     * @param pEnd   exclude
     * @return
     */
    private ListNode partitionInEndExclude(ListNode head, ListNode pStart, ListNode pEnd) {
        ListNode baseNode = pStart;
        ListNode index = pStart.next;
        // 记录中间位置
        ListNode tailIndex = pEnd;
        // TODO: 2018/12/25 未完成 
        while (index != pEnd.next) {

            ListNode nextNode = index.next;
            if (index.val >= baseNode.val) {
                // 升序，如果值较大，交换值到左边
                addNodeTail(index, tailIndex);
                tailIndex = index;
            }
            index = nextNode;
        }
        addNodeTail(baseNode,pEnd);
        return null;

    }

    /**
     * 添加到单链接表尾
     *
     * @param index
     * @param tailIndex
     */
    private void addNodeTail(ListNode index, ListNode tailIndex) {
        if (index == tailIndex) {
            return;
        } else {
            ListNode next = tailIndex.next;
            tailIndex.next = index;
            index.next = next;
            return;
        }

    }

    static class ListNode {
        private int val;
        private ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }


}
