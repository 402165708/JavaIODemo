import java.util.Scanner;

public class Main01 {

    public static void judge(int n, int k){

        if(n < 3){
            System.out.println(0 + " " + 0);
        } else if(n >= 3){

            if(k <= 1 || k == n){
                System.out.println(0 + " " + 0);
            }else if(k > n / 2){
                System.out.println(0 + " " + (n - k));
            }else{
                System.out.println(0 + " " + (k-1));
            }
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        //测试用例个数
        int t = sc.nextInt();

        for(int i = 0;i < t;i++){

            int n = sc.nextInt();
            int k = sc.nextInt();

            judge(n, k);
        }

        sc.close();
    }
}
