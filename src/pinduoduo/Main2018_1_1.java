package pinduoduo;

import java.util.Scanner;

/**
 * Created by chenzhitao on 2018/9/21
 * <p>
 * 给定一个无序数组，包含正数、负数和0，要求从中找出3个数的乘积，使得乘积最大，要求时间复杂度：O(n)，空间复杂度：O(1)
 */


public class Main2018_1_1 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n =  sc.nextInt();
        long[] valueArr = new long[n];
        for (int i = 0; i < n; i++) {
            valueArr[i] = sc.nextLong();
        }

        System.out.println(getMaximum(valueArr));

    }

    /**
     * 记录最大的三个值和最小的两个值，其他大小顺序分别为max1 > max2 > max3 > min2 > min1
     * 依次从大到小比较，交换值，保持顺序。这种方式是为了保证负数最大值（负负为正）
     *
     * 结果返回最大值 max1 * max2 * max3 , max1 * min2 * min1,其中一个，
     *
     * @param valueArr
     * @return
     */
    private static long getMaximum(long[] valueArr) {

        long max1=0,max2=0,max3=0,min1= 0,min2=0;
        if (valueArr == null || valueArr.length == 0){
            return 0;
        }
        for (long val : valueArr) {
           if (val > max1){
               max3 = max2;
               max2 = max1;
               max1 =val;
           }else if (val> max2){
               max3 = max2;
               max2 = val;
           }else if (val > max3){
               max3 = val;
           }else if (val< min1){
               min2 = min1;
               min1 = val;
           }else if (val < min2){
               min2 = val;
           }
        }
        return Math.max(max1*max2 *max3 , max1*min2*min1);
    }

}
