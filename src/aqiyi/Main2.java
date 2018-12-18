package aqiyi;

import java.util.Scanner;

/**
 * Created by chenzhitao on 2018/9/15
 */
public class Main2 {

    public static void main(String args[]) {
        Scanner cin = new Scanner(System.in);
        int N, M, P;
//        while (cin.hasNext()) {
            N = cin.nextInt();
            M = cin.nextInt();
            P = cin.nextInt();

            int[] value = new int[N];
            for (int i = 0; i < N; ++i) {
                int a = cin.nextInt();
                value[i] = a;
            }

            for (int i = 0; i < M; ++i) {
                char ch = (char) cin.next().charAt(0);
                int index = cin.nextInt();
                if (ch == 'A') {
                    value[index - 1]++;
                } else {
                    value[index - 1]--;
                }
            }


            int rank = 0;
            for (int i = 0; i < N; ++i) {
                int index = P - 1;
                if (value[i] > value[index]) {
                    rank++;
                }
            }
            System.out.println(rank);
        }
//    }
}

