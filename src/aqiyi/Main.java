package aqiyi;

import java.util.Scanner;

/**
 * Created by chenzhitao on 2018/9/15
 */
public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String string = sc.nextLine();

        System.out.println(action(string));

    }

    private static int action(String string) {
        int before = 0, after =0;
        for (int i = 0; i < string.length(); i++) {
            if (i < (string.length() / 2)) {
                before += (string.charAt(i)-'0');
            } else {
                after += (string.charAt(i)-'0');
            }
        }
        int sub =0;
        int startIndex, count =0;
        if (before> after){
            startIndex = string.length()/2;
            sub = before-after;
        } else if (before<after){
            startIndex = 0;
            sub= after-before;
        } else {
           return 0;
        }


        //一个元素比较
        for (int i = startIndex; i < (startIndex +(string.length()/2)); i++) {
            if ((string.charAt(i)-'0' +sub) < 10) {
                return 1;
            }
        }

        for (int i = startIndex; i < (startIndex +(string.length()/2)-1); i++) {
            for (int j = i+1; j < (startIndex +(string.length()/2)); j++) {
                if ((string.charAt(i)-'0' + string.charAt(j)-'0' +sub) < 10) {
                    return 2;
                }
            }
        }
        if ((string.charAt(startIndex)-'0' + string.charAt(startIndex+1)-'0'+string.charAt(startIndex+2)-'0' +sub) < 10) {
            return 3;
        }

        return -1;

    }


}
