import java.util.Scanner;

/**
 * 腾讯2019年，笔试第三题
 *
 *
 * 给定 A,B,C,三个数，求给一种方案，多个数是A的倍数，组合的和对B取模的值，等于C
 *
 *
 * 如果存在这种方案，返回YES;否则返回NO
 *
 */
public class Main0003 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] arrays= new int[n][3];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < 3; j++){
                int x = sc.nextInt();
                arrays[i][j] = x;
            }
        }
        action(arrays, n);
    }

    /**
     *  sum = ai + ... + an = a*1 + ...+ a*n
     *
     *  c = sum % b
     *    = (a*1 + ...+ a*n) % b
     *    = a % b * (1 % b) + ... + a % b * (n % b)
     *
     *
     * @param arrays
     * @param n
     */

    private static void action(int[][] arrays, int n){

        for (int i=0; i<n; i++){
            int a = arrays[i][0];
            int b = arrays[i][1];
            int c = arrays[i][2];

            int diff = a % b;
            boolean flag = false;
            for (int j=1; j<=b; j++){
                int sum = diff*j;
                if (sum % b == c){
                    flag = true;
                    break;
                }
            }
            if (flag){
                System.out.println("YES");
            }else {
                System.out.println("NO");
            }
        }
    }
}
