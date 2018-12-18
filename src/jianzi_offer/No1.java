package jianzi_offer;

/**
 * Created by chenzhitao on 2018/10/24
 */

import java.util.Arrays;

/**
 *
 */
public class No1 {

    public static void main(String[] args) {
//
//        int target = 7;
//
//        int[][] array = {{1, 2, 8, 9}, {2, 4, 9, 12}, {4, 7, 10, 13}, {6, 8, 11, 15}};
//        // 有序二维数组查找目标值
//        System.out.println(find(target, array));
//        // 替换空格
//        System.out.println(replaceSpace1(new StringBuffer("hello world")));
//
//        printCheck(3, new Pig());
//
//        printCheck(10, new Cat());
//
//        BitSet bit = new BitSet();
//        bit.set(-1000000011);
////        System.out.println(1L << 1000);
//
//
        int i = 111;
        System.out.println((~i+1));

    }


    /**
     * 在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，
     * 每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，
     * 判断数组中是否含有该整数。
     * <p>
     * <p>
     * 二维数组是从左到右，从上到下，递增，使用二维数组的左下角元素开始比较
     *
     * @param array
     * @return
     * @pam target
     */
    public static boolean find(int target, int[][] array) {
        if (array == null || array.length == 0 || array[0].length == 0) {
            // 不存在元素
            return false;
        }
        if (target < array[0][0]
                || target > array[array.length - 1][array[0].length - 1]) {
            // 小于二维数组的最小值，或大于二维数组的最大值
            return false;
        } else if (target == array[0][0]
                || target == array[array.length - 1][array[0].length - 1]) {
            // 等于二维数组的最小值，或等于二维数组的最大值
            return true;
        } else {
            // 从左下角元素开始比较
            int i = array.length - 1, j = 0;
            while ((i > -1 && i < array.length)
                    && (j > -1 && j < array[0].length)) {
                if (target == array[i][j]) {
                    return true;
                } else if (target < array[i][j]) {
                    i--;
                } else if (target > array[i][j]) {
                    j++;
                }
            }
        }
        return false;
    }

    /**
     * 使用copy数组的方法。统计空格数，然后从新建数组，采用从末尾后移的填充
     *
     * @param str
     * @return
     */
    public static String replaceSpace1(StringBuffer str) {
        if (str == null) {
            return new String(str);
        }

        int spaceCount = 0;
        for (int i = 0; i < str.length(); i++) {
            if (' ' == str.charAt(i)) {
                // 统计空格数
                spaceCount++;
            }
        }


        char[] strArray = new char[str.length() + spaceCount * 2];
        int index = strArray.length - 1;
        for (int i = str.length() - 1; i >= 0; i--) {
            if (' ' == str.charAt(i)) {
                strArray[index--] = '0';
                strArray[index--] = '2';
                strArray[index--] = '%';
            } else {
                strArray[index--] = str.charAt(i);
            }
        }

        return new String(strArray);
    }

    /**
     * 封装方法，按照实际的类，选择执行不同的方法
     *
     * @param param
     * @param animal
     */
    public static void printCheck(int param, Animal animal) {
        animal.print(param);
    }

    /**
     * 请实现一个函数，将一个字符串中的每个空格替换成“%20”。
     * 例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
     *
     * @param str
     * @return
     */
    public String replaceSpace(StringBuffer str) {
        StringBuilder sb = new StringBuilder();
        if (str != null && str.length() > 0) {
            for (int i = 0; i < str.length(); i++) {
                if (' ' == str.charAt(i)) {
                    sb.append("%20");
                } else {
                    sb.append(str.charAt(i));
                }
            }
        }
        return sb.toString();
    }

    public boolean VerifySquenceOfBST(int[] sequence) {
        if (sequence.length == 0) {
            return false;
        }
        return isTreeBST(sequence, 0, sequence.length - 1);
    }

    /**
     * 执行检查是否为二叉搜索树的后序遍历，后序遍历的输出是左右根，小大中
     *
     * @param sequence
     * @param start
     * @param end
     * @return
     */
    public boolean isTreeBST(int[] sequence, int start, int end) {
        if (end <= start) {
            return true;
        }
        int i = start;
        //左子树的节点小于根节点
        for (; i < end; i++) {
            if (sequence[i] > sequence[end]) {
                break;
            }
        }
        // 右子树的节点大于根根节点
        for (int j = i; j < end; j++) {
            if (sequence[j] < sequence[end]) {
                return false;
            }

        }
        // 遍历左右子树
        return isTreeBST(sequence, start, i - 1) && isTreeBST(sequence, i, end - 1);
    }

    /**
     * 执行检查是否为二叉搜索树的后序遍历。
     *
     * @param sequence 数组
     * @param start    起始位，校验合法输入
     * @param length   比较长度，如果等于0或者1，返回true
     * @return
     */
    public boolean isTreeBST1(int[] sequence, int start, int length) {
        if (sequence == null || sequence.length == 0) {
            throw new IllegalArgumentException("Arrays is null or arrays length is 0!");
        }

        if (start < 0 || length < 0 || start > sequence.length - 1 || (start + length > sequence.length)) {
            throw new IndexOutOfBoundsException("Param.start or param.length value input ILLEGAL! ");
        }

        if (length == 0 || length == 1) {
            return true;
        }

        //左子树的节点小于根节点
        int root = sequence[start + length - 1];
        int i = start;
        for (; i < (start + length - 1); i++) {
            if (sequence[i] > root) {
                break;
            }
        }
        // 右子树的节点大于根根节点
        int j = i;
        for (; j < (start + length - 1); j++) {
            if (sequence[j] < root) {
                return false;
            }
        }
        // 遍历左右子树
        return isTreeBST1(sequence, start, i - start) && isTreeBST1(sequence, i, j - i);
    }


    /**
     * 接口，提供一个公共的方法
     */
    interface Animal {

        public void print(int var0);

    }

    /**
     * Cat类，实现Animal的公共方法
     */
    static class Cat implements Animal {

        public Cat() {
        }

        @Override
        public void print(int var0) {
            System.out.println("cat number = " + var0);
        }
    }

    /**
     * Pig类，实现Animal的公共方法
     */
    static class Pig implements Animal {
        public Pig() {
        }

        @Override
        public void print(int var0) {
            System.out.println("pig number = " + var0);
        }
    }
    //***********************工厂方法***************************


    public class BitSetDemo {


        private final static int DEFAULT_CAP = 1 << 4;
        private final static int BULK_SIZE = 1 << 6;
        private final static int CONTINUE = 0;
        private final static int TRUE = 1;
        private final static int FALSE = 2;
        private final static long LONG_FLAG_DEFAULT = 0x8000000000000000L;


        private int resultBitArraysIndex = 0;
        private long[] resultBitArrays = new long[DEFAULT_CAP];

        private int zeroHideIndex = 0;
        private int[] zeroHideCountArrays = new int[DEFAULT_CAP];


        /**
         * 检查 是否存在
         *
         * @param target
         * @return
         */
        public boolean check(int target) {
            int zeroSum = 0;
            int zeroCount = 0;
            for (int i = 0; i < resultBitArrays.length; i++) {
                if (resultBitArrays[i] == 0) {
                    return false;
                }
                int searchRes = this.actionSearch(resultBitArrays[i], i, zeroSum, zeroCount, target);
                if (TRUE == searchRes) {
                    return true;
                } else if (FALSE == searchRes) {
                    return false;
                }

            }
            return false;

        }

        /**
         * 执行查询
         *
         * @param word       数组元素
         * @param arrayIndex 数组元素的索引
         * @param zeroSum    之前累计的隐藏0 个数
         * @param zeroCount  之前累计的0出现个数，第一次出现默认为0，然后是1，2...
         * @param target     查询的目标值
         * @return FALSE 表示不存在，TRUE表示存在，CONTINUE表示继续查询
         */
        private int actionSearch(long word, int arrayIndex, int zeroSum, int zeroCount, int target) {
            if (target < 0) {
                return FALSE;
            }

            long flag = LONG_FLAG_DEFAULT;
            // 遍历数组元素二进制，获得隐藏 0 的个数
            for (int i = 0; i < BULK_SIZE; i++) {
                if (1 == (word & flag)) {
                    //等于1
                    if (target == (i + 1 + (arrayIndex * BULK_SIZE) + zeroSum)) {
                        return TRUE;
                    } else {
                        continue;
                    }

                } else {
                    // 等于0
                    if (target >= (i + 1 + (arrayIndex * BULK_SIZE) + zeroSum)
                            && target <= (i + 1 + (arrayIndex * BULK_SIZE) + zeroSum + zeroHideCountArrays[zeroCount])) {
                        return FALSE;
                    } else {
                        //
                        zeroCount++;
                        zeroSum += zeroHideCountArrays[zeroCount];
                    }
                }
                flag >>>= 1;
            }
            return CONTINUE;

        }

        /**
         * 压缩方法
         *
         * @param originBitArrays
         */
        public void resetCompress(long[] originBitArrays) {
            if (originBitArrays == null || originBitArrays.length == 0) {
                throw new IllegalArgumentException("Param.originBitArrays  == null or originBitArrays.length = 0");
            }

            StringBuilder seqStr = new StringBuilder();
            for (int i = 0; i < originBitArrays.length; i++) {
                long word = originBitArrays[i];
                seqStr.append(calculateHideZeroCount(word));
                if (seqStr.length() >= BULK_SIZE) {
                    growLongArrays(resultBitArrays, resultBitArraysIndex++);
                    resultBitArrays[resultBitArraysIndex++] = Long.getLong(seqStr.substring(0, BULK_SIZE));
                    if (seqStr.length() == BULK_SIZE) {
                        seqStr = new StringBuilder();
                    }
                    seqStr = new StringBuilder(seqStr.substring(BULK_SIZE));
                }
            }
        }

        /**
         * 统计0隐藏的个数
         *
         * @param word
         * @return
         */
        private String calculateHideZeroCount(long word) {

            StringBuilder seqStr = new StringBuilder();
            long flag = LONG_FLAG_DEFAULT;
            int count = 0;
            // 遍历数组元素二进制，获得隐藏 0 的个数
            for (int i = 0; i < BULK_SIZE; i++) {
                if (1 == (word & flag)) {
                    //等于1
                    if (count > 0) {
                        // 减一，隐藏的0 个数
                        growIntArrays(zeroHideCountArrays, zeroHideIndex + 1);
                        zeroHideCountArrays[zeroHideIndex++] = count - 1;
                        count = 0;
                    }
                    seqStr.append('1');
                } else {
                    // 等于0
                    if (count > 0) {
                        count++;
                    } else {
                        seqStr.append('0');
                        count++;
                    }
                }
                flag >>>= 1;
            }

            return seqStr.toString();
        }


        /**
         * 构建bitset
         *
         * @param input
         * @return
         */
        public long[] buildBitSet(int input) {
            if (input < 0) {
                throw new IllegalArgumentException("Param.input < 0");
            }
            //初始化
            int needBulkSize = input % BULK_SIZE > 0 ? (input / BULK_SIZE + 1) : (input / BULK_SIZE);
            long[] bitArrays = new long[needBulkSize];

            //执行操作
//            for (int i = 0; i < needBulkSize; i++) {
//                bitArrays[i] = input & 0xFFFFFFFF;
//                input >>= BULK_SIZE;
//            }
            bitArrays[input >> 6] |= (LONG_FLAG_DEFAULT >>> (input & (6 - 1)));
            return bitArrays;
        }

        /**
         * 扩容
         *
         * @param array
         * @param needLength
         * @return
         */
        public void growIntArrays(int[] array, int needLength) {
            if (needLength < DEFAULT_CAP) {
                return;
            }
            int newLen = Math.max(array.length * 2, needLength);
            zeroHideCountArrays = Arrays.copyOf(array, newLen);

        }

        /**
         * 扩容
         *
         * @param array
         * @param needLength
         * @return
         */
        public void growLongArrays(long[] array, int needLength) {
            if (needLength < DEFAULT_CAP) {
                return;
            }
            int newLen = Math.max(array.length * 2, needLength);
            resultBitArrays = Arrays.copyOf(array, newLen);
        }


    }


}
