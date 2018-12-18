import java.util.*;

public class Main3 {

    public static void main(String args[]) {

        // 输入数据
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < n; i++) {
            int m = Integer.parseInt(sc.nextLine());
            String[] input = new String[m];
            for (int j = 0; j < m; j++) {
                input[j] = sc.nextLine();
            }

            if (calculate(input)) {
                System.out.println("Yeah");
            } else {
                System.out.println("Sad");
            }

        }


    }

    // 批次判断
    public static boolean calculate(String[] input) {
        for (int i = 0; i < input.length - 1; i++) {
            for (int j = i + 1; j < input.length; j++) {
                if(solution(input[i], input[j])) {return true;}

            }
        }
        return false;
    }

    // 双生词判断
    public static boolean solution(String a, String b) {
        if (a.length() != b.length()) {
            return false;
        }
        String c = a + a;
        String rc = new StringBuilder(c).reverse().toString();
        if (c.contains(b)||rc.contains(b)) {
            return true;
        }
        return false;
    }

//    private  static boolean getResult(String valueStr, String targetStr) {
//        if (valueStr == null || valueStr.length() == 0 || targetStr == null || targetStr.length() == 0) {
//            return false;
//        }
//        if (valueStr.length() != targetStr.length()) {
//            return false;
//        }
//
//        // 获取目标字符串首字母
//        String tarStartChar = targetStr.substring(0, 1);
//        // valueStr 不包含target字符的首字母
//        if (!valueStr.contains(tarStartChar)) {
//            return false;
//        }
//
//        String[] valueCharArray = valueStr.split("");
//        int size = valueCharArray.length;
//
//        // 去除切割后首个字符串为空
//        String[] valueCharArrays = new String[valueCharArray.length - 1];
//        for (int i = 0; i < size - 1; i++) {
//            valueCharArrays[i] = valueCharArray[i + 1];
//        }
//
//        // 存储目标字符首字符在valueStr所在下标
//        List<Integer> targetStartOfValue = new ArrayList<>();
//        for (int i = 0; i < valueCharArrays.length; i++) {
//            if (tarStartChar.equals(valueCharArrays[i])) {
//                targetStartOfValue.add(i);
//            }
//        }
//
//        for (int i = 0; i < targetStartOfValue.size(); i++) {
//            String arrayStr = getArrayStr(valueCharArrays, targetStartOfValue.get(i), true);
//            String arrayStrAnti = getArrayStr(valueCharArrays, targetStartOfValue.get(i), false);
//            if (targetStr.equals(arrayStr) || targetStr.equals(arrayStrAnti)) {
//                return true;
//            }
//        }
//
//        return false;
//    }
//
//    /**
//     * h e l l o w o r l d  3   10
//     *
//     * @param startPos start from 0
//     * @return string
//     */
//    private  static String getArrayStr(String[] valueCharArray, int startPos, boolean isAnti) {
//        if (valueCharArray == null || valueCharArray.length == 0 || startPos < 0 || startPos > valueCharArray.length - 1) {
//            return "";
//        }
//
//        int valueSize = valueCharArray.length;
//        StringBuilder string = new StringBuilder();
//
//        if (!isAnti) {
//            // left to right
//            for (int i = startPos; i < valueSize; i++) {
//                string.append(valueCharArray[i]);
//            }
//
//            for (int i = 0; i < startPos; i++) {
//                string.append(valueCharArray[i]);
//            }
//        } else {
//            // right to left
//            for (int i = 0; i <= startPos; i++) {
//                string.append(valueCharArray[startPos - i]);
//            }
//
//            for (int i = valueSize - 1; i > startPos; i--) {
//                string.append(valueCharArray[i]);
//            }
//        }
//
//        return string.toString();
//    }

}
