//import java.util.Scanner;
//
//public class Main {
//
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt();
//        sc.nextLine();
//        String[] ss = new String[n];
//        for (int i = 0; i < n; i++) {
//            ss[i] = sc.nextLine();
//        }
//        for (int i = 0; i < n; i++) {
//            if (judge(ss[i])) {
//                System.out.println("Yeah");
//            } else {
//                System.out.println("Sad");
//            }
//        }
//    }
//
//
//    public static boolean judge(String s) {
//        boolean flag = false;
//        if (s.length() == 1) {
//            return true;
//        }
//        for (int i = 0; i < s.length() || flag == false; i++) {
//            for (int k = 1; k <= s.length() / 2; k++) {
//                int left = i - k < 0 ? s.length() - k + i : i - k;
//                int right = i + k > s.length() - 1 ? (i + k) % s.length() : i + k;
//                if (s.charAt(left) == s.charAt(right)) {
//                    if (k == s.length() / 2) {
//                        flag = true;
//                    }
//                    continue;
//                } else {
//                    break;
//                }
//            }
//        }
//        return flag;
//    }
//
//
//}
