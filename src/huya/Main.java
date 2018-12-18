//package huya;
//
//import java.util.Arrays;
//
///**
// * Created by chenzhitao on 2018/9/19
// */
//
//
///**
// * 自定义堆栈
// *
// * @param <E>
// */
//public class Main <E> {
//
//
//    private static final int capacities = 16;
//    private Object[] data;
//    private int eleCount = -1;
//
//    public MyStack() {
//    }
//
//    /**
//     * 入栈
//     *
//     * @param node
//     */
//    public synchronized void E push(E e) {
//        if (data == null || data.length == 0) {
//            data = new Object[capacities];
//        } else {
//            ensureCapacity(eleCount + 1);
//        }
//        data[++eleCount] = e;
//    }
//
//    /**
//     * 出栈
//     *
//     * @return
//     */
//    public synchronized void <E> pop() {
//        if (isEmpty()) {
//            return null;
//        }
//        return data[eleCount--];
//    }
//
//    public synchronized boolean isEmpty() {
//        return eleCount == -1 ? true : false;
//    }
//
//    /**
//     * 扩容
//     *
//     * @param maximum
//     */
//    private synchronized void ensureCapacity(int maximum) {
//        if (maximum < data.length) {
//            return;
//        }
//        int tmpSize = eleCount << 2 + 1;
//        if (tmpSize < maximum && (maximum < (Integer.MAX_VALUE - 8))) {
//            tmpSize = maximum;
//        } else if (maximum > Integer.MAX_VALUE - 8) {
//            tmpSize = Integer.MAX_VALUE - 8;
//        }
//        data = Arrays.copyOf(data, tmpSize);
//
//    }
//
//}
