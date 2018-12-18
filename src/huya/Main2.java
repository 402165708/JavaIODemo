package huya;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by chenzhitao on 2018/9/19
 */
public class Main2 {

    public static void main(String[] args) {
        String[] a = {"A", "B", "C", "D", "#", "F", "G", "H"};
        order(a);
    }


    private static void order(String[] a) {
        if (a == null || a.length == 0) {
            return;
        }
        List<String> preOderResult = new LinkedList<>();

        MyStack stack = new MyStack();

        int i = 0;
        Node node = new Node(i, a[i]);
        stack.push(node);
        while (!stack.isEmpty()) {
            // 出栈，记录
            Node cur = stack.pop();
            preOderResult.add(cur.value);

            if (a.length > (2 * i + 2) && a[2 * i + 2] != "#") {
//            if (a.length > (2*i +2)  ) {
                //右孩子入栈
                node = new Node(2 * i + 2, a[2 * i + 2]);
                stack.push(node);
            }

            if (a.length > (2 * i + 1) && a[2 * i + 1] != "#") {
//            if (a.length > (2*i +1)  ) {
                // 左孩子入栈
                node = new Node(2 * i + 1, a[2 * i + 1]);
                stack.push(node);
            }
            i++;
        }
        for (String ele : preOderResult) {
            System.out.print(ele + " ");
        }
    }


    /**
     * 节点元素信息
     */
    static class Node {
        private int index;
        private String value;

        public Node() {
        }

        public Node(int index, String value) {
            this.index = index;
            this.value = value;
        }
    }

    static class MyStack {
        private static final int capacities = 16;
        private Node[] eleData;
        private int eleCount = -1;

        /**
         * 入栈
         *
         * @param node
         */
        public synchronized void push(Node node) {
            if (eleData == null || eleData.length == 0) {
                eleData = new Node[capacities];
            } else {
                ensureCapacity(eleCount + 1);
            }
            eleData[++eleCount] = node;
        }

        /**
         * 出栈
         *
         * @return
         */
        public synchronized Node pop() {
            if (isEmpty()) {
                return null;
            }
            return eleData[eleCount--];
        }

        public synchronized boolean isEmpty() {
            return eleCount == -1 ? true : false;
        }

        /**
         * 扩容
         *
         * @param maximum
         */
        private synchronized void ensureCapacity(int maximum) {
            if (maximum < eleData.length) {
                return;
            }
            int tmpSize = eleCount << 2 + 1;
            if (tmpSize < maximum && (maximum < (Integer.MAX_VALUE - 8))) {
                tmpSize = maximum;
            } else if (maximum > Integer.MAX_VALUE - 8) {
                tmpSize = Integer.MAX_VALUE - 8;
            }
            eleData = Arrays.copyOf(eleData, tmpSize);

        }


    }
}
