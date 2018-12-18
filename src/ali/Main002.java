package ali;

import java.util.Scanner;

/**
 * Created by chenzhitao on 2018/9/12
 */
public class Main002 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String str = in.nextLine();

        String[] strArr = str.split("\\ ");

        for (int i = 0; i < strArr.length; i++) {
            strArr[i] = reverse(strArr[i]);
        }

        for (String s : strArr) {
            System.out.print(s + " ");
        }
        System.out.println();
    }

    /**
     * 字符串翻转
     * @param string
     * @return
     */
    private static String reverse(final String string) {
        String value  = string;
        if (value == null){
            return null;
        }
        if ("".equals(value.trim())){
            return string;
        }
        char[] valueArr = string.toCharArray();
        int i = 0,j=valueArr.length-1;
        while (i < j){
            char tmp = valueArr[i];
            valueArr[i] = valueArr[j];
            valueArr[j] = tmp;
            ++i;--j;
        }
        return new String(valueArr);

    }
}
