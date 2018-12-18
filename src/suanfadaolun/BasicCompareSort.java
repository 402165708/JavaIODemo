package suanfadaolun;

/**
 * @Description :
 * @Author : chenzhitao
 * @Date : Created in 16:28 2018/11/21
 */
public class BasicCompareSort {


    public static void main(String[] args) {
        BasicCompareSort basicSort = new BasicCompareSort();
//        int[] arr = {3, 1, 9, 5, 19, 10, 2, 13, 11};
        int[] arr = new int[10];
        arr[2] = 2;
        arr[3] = 2;
        arr[4] = 2;
        basicSort.quickSort(arr, 0, arr.length - 1, true);

        for (int i : arr) {
            System.out.print(i + " ");
        }

    }


    /**
     * 二路归并排序
     *
     * @param array
     * @param startIndex 开始下标
     * @param endIndex   截止下标（包括）
     * @param isDesc     是否降序
     */
    public void mergeSort(int[] array, int startIndex, int endIndex, boolean isDesc) {
        if (array == null || array.length == 0) {
            throw new NullPointerException("array is null");
        }
        if (startIndex < 0 || startIndex >= array.length) {
            throw new IndexOutOfBoundsException("startIndex is illegal");
        }
        if (endIndex < 0 || endIndex >= array.length) {
            throw new IndexOutOfBoundsException("endIndex is illegal");
        }

        if (startIndex < endIndex) {
            //数组拆分，直到元素只有一个
            int midIndex = (startIndex + endIndex) / 2;

            mergeSort(array, startIndex, midIndex, isDesc);
            mergeSort(array, midIndex + 1, endIndex, isDesc);
            //执行合并排序
            actionMerge(array, startIndex, midIndex, endIndex, isDesc);
        }

    }

    /**
     * 执行归并
     *
     * @param array
     * @param startIndex
     * @param midIndex
     * @param endIndex
     * @param isDesc
     */
    private void actionMerge(int[] array, int startIndex, int midIndex, int endIndex, boolean isDesc) {
        //计算长度
        int lLen = midIndex - startIndex + 1;
        int rLen = endIndex - midIndex;
        //申请数组
        int[] lArray = new int[lLen];
        for (int i = 0; i < lLen; i++) {
            lArray[i] = array[startIndex + i];
        }
        int[] rArray = new int[rLen];
        for (int i = 0; i < rLen; i++) {
            rArray[i] = array[midIndex + 1 + i];
        }
        //合并
        int lIndex = 0, rIndex = 0;
        for (int k = startIndex; k <= endIndex; k++) {
            if (lIndex >= lLen && rIndex < rLen) {
                //左数组遍历完成，直接遍历右数组
                array[k] = rArray[rIndex++];
            } else if (rIndex >= rLen && lIndex < lLen) {
                //右数组遍历完成，直接遍历左数组
                array[k] = lArray[lIndex++];
            } else {
                if (!isDesc && lArray[lIndex] <= rArray[rIndex]) {
                    //如果是升序，左数组的元素较小，先加入
                    array[k] = lArray[lIndex++];
                } else if ((isDesc && lArray[lIndex] >= rArray[rIndex])) {
                    //如果是降序，左数组的元素较大，先加入
                    array[k] = lArray[lIndex++];
                } else {
                    array[k] = rArray[rIndex++];
                }
            }
        }
    }

    /**
     * 快速排序
     *
     * @param array      数组
     * @param startIndex 开始下标
     * @param endIndex   截止下标
     * @param isDesc     是否降序
     */
    public void quickSort(int[] array, int startIndex, int endIndex, boolean isDesc) {
        if (array == null || array.length == 0) {
            throw new NullPointerException("array is null");
        }
        if (startIndex < 0 || startIndex >= array.length) {
            throw new IndexOutOfBoundsException("startIndex is illegal");
        }
        if (endIndex < 0 || endIndex >= array.length) {
            throw new IndexOutOfBoundsException("endIndex is illegal");
        }

        if (startIndex < endIndex) {
            //数组拆分，直到元素只有一个
            int separateIndex = partition(array, startIndex, endIndex, isDesc);
            if ((separateIndex - 1) >= 0 && separateIndex <= array.length) {
                quickSort(array, startIndex, separateIndex - 1, isDesc);
            }
            if (separateIndex >= 0 && (separateIndex + 1) < array.length) {
                quickSort(array, separateIndex + 1, endIndex, isDesc);
            }
        }
    }

    /**
     * 切分数组，采用了两个变量i和j，i记录的是左边的被交换的位置下标，最开始等于startIndex；j是遍历数组的游标
     * 以最右边的元素作为基础比较量，遍历startIndex到endIndex-1的数组元素
     *
     * @param array
     * @param startIndex
     * @param endIndex
     * @param isDesc
     * @return
     */
    private int partition(int[] array, int startIndex, int endIndex, boolean isDesc) {
        int baseVal = array[endIndex];
        //值下标
        int i = startIndex - 1;
        //遍历剩余的数组，从startIndex 到 endIndex-1
        for (int j = startIndex; j < endIndex; j++) {
            if (!isDesc) {
                // 如果是升序，交换小的值到左边
                if (array[j] <= baseVal) {
                    swapValue(array, ++i, j);
                }
            } else {
                // 如果是降序，交换大的值到左边
                if (array[j] >= baseVal) {
                    swapValue(array, ++i, j);
                }
            }
        }
        // 最后交换base值
        swapValue(array, ++i, endIndex);
        return i;
    }

    /**
     * hoare设计的切分方法（早期版本）
     *
     * @param array
     * @param startIndex
     * @param endIndex
     * @param isDesc
     * @return
     */
    private int hoarePartition(int[] array, int startIndex, int endIndex, boolean isDesc) {
        int baseVal = array[endIndex];
        //值下标
        int i = startIndex - 1;
        int j = endIndex + 1;
        if (!isDesc) {
            //升序
            while (true) {
                do {
                    j--;
                } while (array[j] >= baseVal && j > i);
                do {
                    i++;
                } while (array[i] <= baseVal && j > i);
                //交换
                if (i < j) {
                    swapValue(array, i, j);
                } else {
//                    swapValue(array, j, endIndex);
                    return j;
                }
            }
        } else {
            //降序，大值放左边
            while (true) {
                //从右到左，j--循环；直到出现大于等于baseVal值，停止循环
                do {
                    j--;
                } while (array[j] <= baseVal && j > i);

                //从左到右，i++ 循环；直到出现小于等于baseVal值，停止循环
                do {
                    i++;
                } while (array[i] >= baseVal && i < j);
                //交换
                if (i < j) {
                    swapValue(array, i, j);
                } else {
//                    swapValue(array, j, endIndex);
                    return j;
                }
            }
        }
    }


    /**
     * 快速排序,随机版本，保证的快速排序的切分不平衡性，满足随机性概率。
     *
     * @param array      数组
     * @param startIndex 开始下标
     * @param endIndex   截止下标
     * @param isDesc     是否降序
     */
    public void randomQuickSort(int[] array, int startIndex, int endIndex, boolean isDesc) {
        if (array == null || array.length == 0) {
            throw new NullPointerException("array is null");
        }
        if (startIndex < 0 || startIndex >= array.length) {
            throw new IndexOutOfBoundsException("startIndex is illegal");
        }
        if (endIndex < 0 || endIndex >= array.length) {
            throw new IndexOutOfBoundsException("endIndex is illegal");
        }

        if (startIndex < endIndex) {
            //数组拆分，直到元素只有一个
            int separateIndex = randomPartition(array, startIndex, endIndex, isDesc);
            if ((separateIndex - 1) >= 0 && separateIndex <= array.length) {
                randomQuickSort(array, startIndex, separateIndex - 1, isDesc);
            }
            if (separateIndex >= 0 && (separateIndex + 1) < array.length) {
                randomQuickSort(array, separateIndex + 1, endIndex, isDesc);
            }
        }


    }

    /**
     * 随机版的快排切分函数
     *
     * @param array
     * @param startIndex
     * @param endIndex
     * @param isDesc
     * @return
     */
    private int randomPartition(int[] array, int startIndex, int endIndex, boolean isDesc) {
        int randomIndex = random(startIndex, endIndex);
        // 交换随机下标值和endIndex的下标值
        swapValue(array, randomIndex, endIndex);
        //调用原始的partition函数
        return partition(array, startIndex, endIndex, isDesc);
    }


    /**
     * 计算var0之间var1的随机数，包括var0或者var1
     *
     * @param var0
     * @param var1
     * @return
     */
    private int random(int var0, int var1) {
        if (var0 == var1) {
            return var0;
        } else {
            return var0 + (int) (Math.random() * (var1 - var0));
        }
    }

    /**
     * 交换值
     *
     * @param array
     * @param index
     * @param index1
     */
    private void swapValue(int[] array, int index, int index1) {
        if (index < 0 || index >= array.length || index1 < 0 || index1 >= array.length) {
            return;
        }
        if (array[index] == array[index1]) {
            // 相同不交换
            return;
        } else {
            //交换值
            array[index] = array[index] ^ array[index1];
            array[index1] = array[index] ^ array[index1];
            array[index] = array[index] ^ array[index1];
        }
    }

}
