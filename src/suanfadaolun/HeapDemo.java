package suanfadaolun;

import java.util.Arrays;

/**
 * @Description : 模拟堆，下标从0开始
 * @Author : chenzhitao
 * @Date : Created in 14:52 2018/11/20
 */
public class HeapDemo {

    // 默认值
    private final static int DEFAULT_SIZE = 1 << 3;
    private final static int MAX_SIZE = 0x7fffffff;
    // 数组
    private int[] heapData = null;
    //是否大根堆
    private boolean isMaxHeap;
    //堆大小
    private int heapSize = 0;
    private int heapTop = -1;


    public HeapDemo(boolean isMaxHeap) {
        this.heapData = new int[DEFAULT_SIZE];
        this.isMaxHeap = isMaxHeap;
    }


    public HeapDemo(int[] heapData, boolean isMaxHeap) {
        if (isMaxHeap) {
            buildMaxHeap(heapData);
        } else {
            buildMinHeap(heapData);
        }
        this.heapData = heapData;
        this.isMaxHeap = isMaxHeap;
        if (heapData != null && heapData.length > 0) {
            heapSize = heapData.length;
            heapTop = 0;
        }
    }

    public static void main(String[] args) {

//        int[] array = {2, 3, 19, 10, 84, 17, 6, 22, 9};
        int[] array = {2, 2, 2, 1, 1, 1, 1, 2, 1};
        HeapDemo heapDemo = new HeapDemo(false);
        heapDemo.addAll(array);

//        for (int i = 0; i < 400000000; i++) {
//            long startTime = System.currentTimeMillis();
//            int value = (int) (Math.random() * 400000000);
//            heapDemo.add(value);
//            while (i % 1000000 == 0) {
//                System.out.println((System.currentTimeMillis() - startTime));
//            }
//        }

        for (int i = 0; i < 10; i++) {
            long startTime = System.currentTimeMillis();
            heapDemo.removeTop();
//            System.out.println(System.currentTimeMillis()-startTime);

            System.out.print(heapDemo.removeTop() + " ");
        }

//        for (int i : array) {
//            System.out.print( heapDemo.removeTop() + " ");
//        }
        System.out.println();

//        heapDemo.buildMinHeap(array);
//        for (int i : array) {
//            System.out.print(i + " ");
//        }

    }

    /**
     * 构建大根堆
     *
     * @param array
     */
    private void buildMaxHeap(int[] array) {
        if (array == null || array.length <= 0) {
            return;
        }
        for (int i = parentIndex(array.length - 1); i >= 0; i--) {
            heapify(array, i, heapSize, true);
        }
    }

    /**
     * 构建小根堆
     *
     * @param array
     */
    public void buildMinHeap(int[] array) {
        if (array == null || array.length <= 0) {
            return;
        }
        for (int i = parentIndex(array.length - 1); i >= 0; i--) {
            heapify(array, i, heapSize, false);
        }
    }

    /**
     * 保证堆的稳定性
     *
     * @param array     堆数组
     * @param index     下标
     * @param heapSize  堆大小
     * @param isMaxHeap 是否大根堆
     */
    private void heapify(int[] array, int index, int heapSize, boolean isMaxHeap) {
        if (array == null || heapSize <= 0 || array.length <= 0) {
            throw new NullPointerException("param.array is null!");
        }
        if (index < 0) {
            throw new IndexOutOfBoundsException("param.index illegal!");
        }
        if (index >= heapSize) {
            return;
        }

        int left = leftChildIndex(index);
        int right = rightChildIndex(index);
        int tmpIndex = index;
        //左右孩子值，比较获得下标
        tmpIndex = getIndexByCompareHeapValue(array, tmpIndex, left, heapSize, isMaxHeap);
        tmpIndex = getIndexByCompareHeapValue(array, tmpIndex, right, heapSize, isMaxHeap);


        if (index != tmpIndex) {
            //交换下标对应的值
            swapValue(array, index, tmpIndex);
            heapify(array, tmpIndex, heapSize, isMaxHeap);
        }
    }

    /**
     * 交换堆值
     *
     * @param array
     * @param index
     * @param index1
     */
    private void swapValue(int[] array, int index, int index1) {
        if (index < 0 || index > array.length || index1 < 0 || index1 > array.length) {
            return;
        }
        //交换值
        array[index] = array[index] ^ array[index1];
        array[index1] = array[index] ^ array[index1];
        array[index] = array[index] ^ array[index1];

    }

    /**
     * 堆的当前下标和其孩子下标比较。如果孩子下标不存在，直接返回当前下标；如果是大根堆，返回较大值的下标；如果是小根堆，返回较小值的下标
     *
     * @param array      堆数组
     * @param index      下标
     * @param childIndex 孩子下标
     * @param heapSize   堆大小
     * @param isMaxHeap  是否大根堆
     * @return
     */
    private int getIndexByCompareHeapValue(int[] array, int index, int childIndex, int heapSize, boolean isMaxHeap) {
        if (index >= heapSize) {
            throw new IndexOutOfBoundsException("index more than heapSize");
        }

        if (childIndex < 0 || childIndex >= heapSize) {
            return index;
        }

        int resultIndex = index;
        if (isMaxHeap) {
            // 大根堆
            if (array[childIndex] > array[index]) {
                resultIndex = childIndex;
            }
        } else {
            // 小根堆
            if (array[childIndex] < array[index]) {
                resultIndex = childIndex;
            }
        }
        return resultIndex;
    }

    /**
     * 添加
     *
     * @param data
     */
    public void add(int data) {
        if (heapData == null) {
            initHeap();
        }
        if (heapSize + 1 >= heapData.length) {
            grow(heapSize + 1);
        }
        heapData[heapSize++] = data;
        if (heapTop == -1) {
            heapTop = 0;
        }

        //从其父节点一直往上迭代，直到根节点
        int parentIndex = parentIndex(heapSize - 1);
        while (parentIndex >= 0) {
            heapify(heapData, parentIndex, heapSize, isMaxHeap);
            parentIndex = parentIndex(parentIndex);
        }

    }

    /**
     * 批量添加
     *
     * @param array
     */
    public synchronized void addAll(int[] array) {
        addAll(array, 0, array.length);
    }

    public void addAll(int[] array, int startIndex) {
        addAll(array, startIndex, array.length);
    }

    public void addAll(int[] array, int startIndex, int length) {
        if (array == null || array.length == 0) {
            return;
        }
        if (startIndex < 0 || startIndex >= array.length) {
            throw new IndexOutOfBoundsException("starIndex is illegal");
        }
        if (length == 0) {
            return;
        }
        if (length < 0 || (startIndex + length) > array.length) {
            throw new IndexOutOfBoundsException(" startIndex or length is illegal");
        }

        //初始化
        if (heapData == null) {
            initHeap();
        }
        //扩容
        if ((heapSize + length) >= heapData.length) {
            grow(heapSize + length);
        }
        //复制数组到原数组中
        System.arraycopy(array, startIndex, heapData, heapSize, length);
        heapSize += length;
        if (heapTop == -1) {
            heapTop = 0;
        }
        //维持稳定
        if (length > (heapSize >> 1)) {
            // 全局调整
            for (int i = parentIndex(heapSize - 1); i >= 0; i--) {
                heapify(heapData, i, heapSize, isMaxHeap);

            }
        }
    }

    /**
     * 初始化堆
     */
    private void initHeap() {
        heapData = new int[DEFAULT_SIZE];

    }

    /**
     * 扩容数组空间
     *
     * @param needCap
     */
    private void grow(int needCap) {

        int newCap = (heapData.length << 1) + 1;

        int cap = newCap;
        if (newCap < needCap) {
            cap = needCap;
        }
        if (cap > MAX_SIZE) {
            newCap = MAX_SIZE;
        }
        heapData = Arrays.copyOf(heapData, newCap);

    }

    /**
     * 移除堆顶元素
     *
     * @return
     */
    public Integer removeTop() {
        int topVal;
        if (heapData == null || heapData.length == 0 || heapSize <= 0) {
            return null;
        } else {
            topVal = getHeapTopValue();
            if (heapSize == 1) {
                // 清空数组
                heapData = null;
                heapSize = 0;
                heapTop = -1;
            } else {
                // 把堆顶元素和最后一位元素交换位置
                swapValue(heapData, 0, heapSize - 1);
                heapSize--;
                if (heapSize > 0) {
                    heapify(heapData, 0, heapSize, isMaxHeap);
                }

            }
        }
        return topVal;


    }

    /**
     * 获得左孩子的下标,下标从0，1....
     *
     * @param index
     * @return
     */
    private int leftChildIndex(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("index illegal!");
        }
        return (index << 1) + 1;
    }

    /**
     * 获得右孩子的下标,下标从0，1....
     *
     * @param index
     * @return
     */
    private int rightChildIndex(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("index illegal");
        }
        return (index << 1) + 2;
    }

    /**
     * 获得父下标，下标从0，1....
     *
     * @param index
     * @return
     */
    private int parentIndex(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("index illegal!");
        }
        return (index - 1) >> 1;
    }

    public boolean isMaxHeap() {
        return isMaxHeap;
    }

    public int getHeapSize() {
        return heapSize;
    }

    public boolean isEmpty() {
        return heapSize <= 0 ? true : false;
    }

    /**
     * 获得堆顶元素
     *
     * @return
     */
    public Integer getHeapTopValue() {
        if (!isEmpty()) {
            return heapData[heapTop];
        } else {
            return null;
        }
    }
}
