package suanfadaolun;

/**
 * @Description :
 * @Author : chenzhitao
 * @Date : Created in 21:04 2018/11/23
 */
public class BasicSelect {


    public static void main(String[] args) {

        BasicSelect basicSelect = new BasicSelect();
        int[] arr = {2, 5, 7, 1, 3, 8, 10, 3, 5, 0};
//        int[] arr = {2, 5, 7, 1, 3, 8, 10, 3, 5};
//        int[] arr = {2, 5};
//        int[] arr = {5};

//        int[] resultArr = basicSelect.getMaxAndMinValue(arr);
//        System.out.println("max=" + resultArr[0] + ", min=" + resultArr[1]);

        System.out.println(basicSelect.randomizedSelect(arr,0,arr.length-1,4));
    }


    /**
     * 同时获得最大和最小值，通过（3n/2)次比较，非2n次比较
     *
     * @param array
     * @return
     */
    public int[] getMaxAndMinValue(int[] array) {
        if (array == null || array.length == 0) {
            throw new NullPointerException("array  is null!");
        }
        int[] maxAndMinArr = new int[2];
        if (array.length == 1) {
            //一个元素
            maxAndMinArr[0] = maxAndMinArr[1] = array[0];
        } else if (array.length == 2) {
            //两个元素
            if (array[0] > array[1]) {
                maxAndMinArr[0] = array[0];
                maxAndMinArr[1] = array[1];
            } else {
                maxAndMinArr[0] = array[1];
                maxAndMinArr[1] = array[0];
            }
        } else {
            // 多个元素
            int tmpMax, tmpMin;
            int startIndex = 0;
            if ((array.length & 1) == 0) {
                //偶数个元素,取两个值
                if (array[0] > array[1]) {
                    tmpMax = array[0];
                    tmpMin = array[1];
                } else {
                    tmpMax = array[1];
                    tmpMin = array[0];
                }
                //开始遍历元素
                startIndex = 2;
            } else {
                //奇数个元素，取第一个值
                tmpMax = array[0];
                tmpMin = array[0];
                startIndex = 1;
            }
            for (int i = startIndex; i < array.length; i += 2) {
                //自增2，直接取两个元素比较，先比较取出的元素，然后再和保存的最大值和最小值比较
                //这样子就比较了三次
                if (array[i] > array[i + 1]) {
                    //
                    if (array[i] > tmpMax) {
                        tmpMax = array[i];
                    }
                    if (array[i + 1] < tmpMin) {
                        tmpMin = array[i + 1];
                    }
                } else {
                    if (array[i + 1] > tmpMax) {
                        tmpMax = array[i + 1];
                    }
                    if (array[i] < tmpMin) {
                        tmpMin = array[i];
                    }
                }
            }
            maxAndMinArr[0] = tmpMax;
            maxAndMinArr[1] = tmpMin;
        }
        return maxAndMinArr;
    }


    /**
     * 计算顺序位的值，适用于计算求第k值。分治法。计算中位数
     * 类似于随机快排，不同点是快排是递归处理划分的数组两边；随机选择是只要处理划分后的一边。
     * 所以它的期望时间复杂度是O(N)
     *
     * @param array
     * @param startIndex
     * @param endIndex
     * @param kth
     * @return
     */
    public int randomizedSelect(int[] array, int startIndex, int endIndex, int kth) {
        if (array == null || array.length == 0) {
            throw new NullPointerException("array is null");
        }
        if (startIndex < 0 || startIndex >= array.length) {
            throw new IndexOutOfBoundsException("startIndex is illegal！");
        }
        if (endIndex < 0 || endIndex >= array.length) {
            throw new IndexOutOfBoundsException("endIndex is illegal！");
        }
        if (kth <= 0 || kth > array.length) {
            throw new IllegalArgumentException("param.kth is illegal!");
        }

        if (startIndex > endIndex) {
            throw new IndexOutOfBoundsException("startIndex more than endIndex！");
        }
        //执行
        if (startIndex == endIndex) {
            //相同
            return array[startIndex];
        } else {
            //随机切分，获得切分下标
            int partIndex = randomPartition(array, startIndex, endIndex);
            // 计算切分下标的相对k值，相对startIndex位移
            int tmpKth = partIndex - startIndex + 1;
            if (tmpKth == kth) {
                return array[partIndex];
            } else if (tmpKth > kth) {
                //第K大值在数组的左边，partIndex的左边
                return randomizedSelect(array, startIndex, partIndex - 1, kth);
            } else {
                // 第k大值在数组的右边，partIndex的右边
                return randomizedSelect(array, partIndex + 1, endIndex, (kth - tmpKth));
            }
        }
    }


    /**
     * 随机版的快排切分函数
     *
     * @param array
     * @param startIndex
     * @param endIndex
     * @return
     */
    private int randomPartition(int[] array, int startIndex, int endIndex) {
        int randomIndex = random(startIndex, endIndex);
        // 交换随机下标值和endIndex的下标值
        swapValue(array, randomIndex, endIndex);
        //调用原始的partition函数
        return partition(array, startIndex, endIndex);
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

    /**
     * 切分数组，采用了两个变量i和j，i记录的是左边的被交换的位置下标，最开始等于startIndex；j是遍历数组的游标
     * 以最右边的元素作为基础比较量，遍历startIndex到endIndex-1的数组元素
     *
     * @param array
     * @param startIndex
     * @param endIndex
     * @return
     */
    private int partition(int[] array, int startIndex, int endIndex) {
        int baseVal = array[endIndex];
        //值下标
        int i = startIndex - 1;
        //遍历剩余的数组，从startIndex 到 endIndex-1
        for (int j = startIndex; j < endIndex; j++) {
            // 如果是升序，交换小的值到左边
            if (array[j] <= baseVal) {
                swapValue(array, ++i, j);
            }
        }
        // 最后交换base值
        swapValue(array, ++i, endIndex);
        return i;
    }


}
