import java.util.Scanner;

public class Main4 {

    public static void main(String[] args) {
        // int[] input = {10,3,7,5,10,3,7,5,10,3,7,5};

        Scanner sc = new Scanner(System.in);
        String[] input1 = sc.nextLine().split("[ ]");

        int n = Integer.parseInt(input1[0]);
        int m = Integer.parseInt(input1[1]);


        String input2 = sc.nextLine();
        String input = "";
        String k = " ";
        for (int i = 0; i < m; i++) {
            if(i == (m - 1)){ k = "";}
            input = input + input2 + k;
        }

        String[] strings = input.split("[ ]");
        int[] numArr = new int[n * m];
        for (int i = 0; i < strings.length; i++) {
            numArr[i] = Integer.parseInt(strings[i]);
        }


        System.out.println(getLIS(numArr));
    }

    // 最长上升子序列
    public static int getLIS(int[] numArr) {
        if (numArr == null || numArr.length == 0) {
            return 0;
        }
        int max = 1;
        int[] dp = new int[numArr.length];
        for (int i = 0; i < numArr.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (numArr[j] < numArr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    max = Math.max(dp[i], max);
                }
            }
        }
        return max;
    }
}
