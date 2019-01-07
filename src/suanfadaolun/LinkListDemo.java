package suanfadaolun;

/**
 * @Description :
 * @Author : chenzhitao
 * @Date : Created in 17:51 2018/12/26
 */
public class LinkListDemo {

    public static void main(String[] args) {

        LinkListDemo demo = new LinkListDemo();
        Node head = new Node(1);
        Node node1 = new Node(2);
        Node node2 = new Node(3);
        Node node3 = new Node(4);
        Node node4 = new Node(4);

        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        Node resust = demo.merge(head, node2);
        System.out.println();


    }


    /**
     * 交叉合并
     *
     * @param head
     * @param head1
     * @return
     */
    public Node merge(Node head, Node head1) {
        if (head == null) {
            return head1;
        }
        if (head1 == null) {
            return head;
        }
        // 记录中间节点起始位
        Node recordHead = head1;

        Node resultHead = null;
        Node index = null;
        while (head != recordHead || head1 != null) {
            //第一次
            if (resultHead == null) {
                resultHead = head;
                index = resultHead;
                head = head.next;
            } else {
                if (head != null && head != recordHead) {
                    index.next = head;
                    index = head;
                    head = head.next;

                }
            }
            // 第二次
            if (head1 != null) {
                index.next = head1;
                index = head1;
                head1 = head1.next;
            }
        }
        return resultHead;
    }


    public Node reverse(Node head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return head;
        }

        Node next = head.next;
        while (next != null) {
            Node tmp = next.next;
            if (head.next == next) {
                //第一个开始的节点
                head.next = null;
            }
            next.next = head;
            head = next;
            next = tmp;
        }

        return head;
    }


    static class Node {
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }

}
