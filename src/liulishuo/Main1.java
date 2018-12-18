package liulishuo;

import java.util.Scanner;

/**
 * Created by chenzhitao on 2018/9/17
 */
class Main {

    public static void main(String args[]) {
        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt();
        int[] elementArr = new int[n];

        int k =0;
        while (k< n) {
            //注意while处理多个case
            int a = cin.nextInt();
            elementArr[k++] = a;
        }

        int[][] value2 = new int[n][n];

        int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sum += ((elementArr[i] - elementArr[j]) > 0
                        ? elementArr[i] - elementArr[j]
                        : (elementArr[j] - elementArr[i]));
            }
        }

        System.out.println(sum * 2);
    }
}
