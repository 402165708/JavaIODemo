package ali;

import java.util.Scanner;

/**
 * Created by chenzhitao on 2018/9/17
 */
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);


        // 长度n
        int n = in.nextInt();
        int[] valueArr = new int[n];
        int i = 0;
        while (i < n) {
            //注意while处理多个case
            int a = in.nextInt();
            valueArr[i++] = a;
        }
        System.out.println(maxSum(valueArr));

    }

    public static int maxSum(int[] valueArr) {
        int sum = 0, b = 0;
        for (int i = 0; i < valueArr.length; i++) {
            if ((sum + b) > b) {
                b += valueArr[i];
            } else {
                b = valueArr[i];
            }
            if (b > sum) {
                sum = b;
            }
        }
        return sum;

    }

}
