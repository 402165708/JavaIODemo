//
//import java.util.Scanner;
//
///**
// * Created by chenzhitao on 2018/9/8
// */
//public class Main {
//
//    private static final char W = 'w';
//    private static final char B = 'b';
//
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        String s1 = scanner.nextLine();
//
//        // 新数组
//        String str = new String(s1.toCharArray());
//        int maxLen = 1,tmpMaxLen = 1, startIndex = 0;
//        //遍历字符字符串
//        boolean reverseStatus = false;
//        while (startIndex < str.length() - 1) {
//            if ((W == str.charAt(startIndex) && B == str.charAt(startIndex + 1))
//                    || (B == str.charAt(startIndex) && W == str.charAt(startIndex + 1))) {
//                startIndex++;
//                tmpMaxLen++;
//                reverseStatus= false;
//            } else if (!reverseStatus){
//                //反转数组,没有反转过
//                str = reverse(str.substring(0, startIndex + 1))
//                        + reverse(str.substring(startIndex + 1, str.length()));
//                reverseStatus = true;
//            }else {
//                // 已经反转过,重新计算最长值
//                if (tmpMaxLen > maxLen){
//                    maxLen = tmpMaxLen;
//                }
//                tmpMaxLen = 0;
//                reverseStatus= false;
//            }
//
//        }
//        System.out.println(maxLen<tmpMaxLen ?tmpMaxLen :maxLen);
//
//
//
//    }
//
//    /**
//     * 数组反转
//     *
//     * @param str
//     * @return
//     */
//    public static String reverse(final String str) {
//        char[] value = str.toCharArray();
//        boolean hasSurrogates = false;
//        int n = value.length - 1;
//        for (int j = (n - 1) >> 1; j >= 0; j--) {
//            int k = n - j;
//            char cj = value[j];
//            char ck = value[k];
//            value[j] = ck;
//            value[k] = cj;
//            if (Character.isSurrogate(cj) ||
//                    Character.isSurrogate(ck)) {
//                hasSurrogates = true;
//            }
//        }
//        if (hasSurrogates) {
//            reverseAll(value);
//        }
//        return new String(value);
//    }
//
//    /**
//     * @param value
//     */
//    private static void reverseAll(char[] value) {
//        for (int i = 0; i < value.length - 1; i++) {
//            char c2 = value[i];
//            if (Character.isLowSurrogate(c2)) {
//                char c1 = value[i + 1];
//                if (Character.isHighSurrogate(c1)) {
//                    value[i++] = c1;
//                    value[i] = c2;
//                }
//            }
//        }
//    }
//
//
//
//
//
//}
//
