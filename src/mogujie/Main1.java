package mogujie;

import java.util.Scanner;

/**
 * 有一个X*Y 网格, 小团要从左上角走到右下角，只能走格点且向下走和向右走。给定一个算法求小团有多少种走法。
 * 给定两个整数 x 和 y(取值范围[0,10])，返回走法,
 *
 * 输入：两个数 x 和 y
 * 输出：走法数
 *
 *
 *
 * Created by chenzhitao on 2018/9/27
 */
public class Main1 {

        public static void main(String[] args) {

            Scanner sc = new Scanner(System.in);
            String s = sc.nextLine();
            sc.close();
            String[] arrays = s.split(" ");
            int X = Integer.parseInt(arrays[0]);
            int Y = Integer.parseInt(arrays[1]);

            System.out.println(getCount(X, Y));


        }

    /**
     * 获得走法数，通过num[i][j] = num[i-1][j] + num[i][j-1], 0<=i<=x; 0 <=j<=y
     * @param x
     * @param y
     * @return
     */
    private static int getCount(int x, int y) {
        int[][] num = new int[11][11];
        for(int j=0; j<=y; j++){
            num[0][j] = 1;
        }
        for(int i=0; i<=x; i++){
            num[i][0] = 1;
        }
        for(int i=1; i<=x; i++){
            for(int j=1; j<=y; j++){
                num[i][j] = num[i-1][j] + num[i][j-1];
            }
        }
        return num[x][y];
    }

}
