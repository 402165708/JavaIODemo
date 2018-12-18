package suanfadaolun;

/**
 * @Description :
 * @Author : chenzhitao
 * @Date : Created in 20:58 2018/11/22
 */
public class NotCompareSort {

    public static void main(String[] args) {
        NotCompareSort sort = new NotCompareSort();
        int[] arr = {1, 2, 4, 3, 2, 6, 8};
        int[] outArr = new int[7];
        sort.countingSort(arr, outArr, 8, false);
        for (int i : outArr) {
            System.out.print(i +" ");
        }

        System.out.println();
    }


    /**
     * 计数排序法，排序n个数组，每个元素都是0到k范围内的整数
     *
     * @param srcArr 输入整形数组
     * @param desArr 输出整形数组
     * @param range  数组的值范围从0到range
     * @param isDesc 是否降序
     */
    public void countingSort(int[] srcArr, int[] desArr, int range, boolean isDesc) {
        if (srcArr == null || srcArr.length == 0) {
            throw new NullPointerException("srcArr is null");
        }
        if (desArr == null || desArr.length == 0) {
            throw new NullPointerException("desArr is null");
        }
        if (range <= 0) {
            throw new IllegalArgumentException("range not more than 0");
        }
        if (srcArr.length != desArr.length) {
            throw new IllegalArgumentException("srcArr.length not equal than desArr.length");
        }

        int[] countArr = new int[range + 1];
        for (int i = 0; i < srcArr.length; i++) {
            //统计出现次数
            countArr[srcArr[i]] = countArr[srcArr[i]] + 1;
        }

        for (int i = 1; i < countArr.length; i++) {
            // 累加元素的出现次数
            countArr[i] = countArr[i] + countArr[i - 1];
        }

        for (int i = srcArr.length - 1; i >= 0; i--) {
            // 出现次数
            desArr[countArr[srcArr[i]]-1] = srcArr[i];
            countArr[srcArr[i]]--;
        }
    }

    private void buckSort(int[] array,int a ){}
}
