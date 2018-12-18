package ali;

import java.util.Scanner;

/**
 * Created by chenzhitao on 2018/9/16
 */
public class Main0001{

        public static void main(String args[]) {
            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt();
            System.out.println(getMaxNumber(n));
        }

        public static int acl(int[] a) {
            int index = 0;
            for (int i = 0; i < a.length; i++) {
                if (a[i] != 0) {
                    index++;
                }
            }
            int max = 0;
            for (int i = 0; i < index; i++) {
                if (max < a[i]) {
                    max = a[i];
                }
            }

            for (; ;) {
                boolean b = true;
                for (int j = 0; j < index; j++) {
                    if (max % a[j] != 0) {
                        b = false;
                    }
                }
                max++;
                if (b) {
                    return max - 1;
                }
            }
        }

        public static int getMaxNumber(int n) {

            int[] a = new int[10000];
            int[] b = new int[10000];
            for (int i = 0; i < n; i++) {
                b[i] = i + 1;
            }
            int aid = 0;
            int bid = n;

            for (int i = 0; i < Integer.MAX_VALUE-8; i++) {

                n = n + 1;
                a[aid++] = n;
                b[bid++] = n;

                if (acl(a) == acl(b)) {
                    return n;
                }
            }
            return -1;
        }
}
