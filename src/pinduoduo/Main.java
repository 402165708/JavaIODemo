package pinduoduo;

import java.util.*;

/**
 * Created by chenzhitao on 2018/9/21
 */
public class Main {

        public static void main(String[] args) {
            Scanner in = new Scanner(System.in);

            String inputStr = in.nextLine();

            if (inputStr == null || "".equals(inputStr.trim())) {
                System.out.print("");
            }

            //判断是否包含特殊的标点
            StringBuilder stringBuilder  = new StringBuilder();
            for (int i = 0; i < inputStr.length(); i++) {
                if ((inputStr.charAt(i) >= 'A' && inputStr.charAt(i) <= 'Z')
                        || (inputStr.charAt(i) >= 'a' && inputStr.charAt(i) <= 'z')) {
                    stringBuilder.append(inputStr.charAt(i));
                } else {
                    stringBuilder.append(' ');
                }
            }

            int maxCount= 0;
            Map<String,Integer> valueMap = new TreeMap<>();
            String[] strArr = stringBuilder.toString().split("\\ ");
            //遍历统计最大的函数
            for (String str : strArr) {
                if (str == null || "".equals(str.trim())) {
                    continue;
                }
                //存入
                if (!valueMap.containsKey(str.toLowerCase())) {
                    //不存在
                    valueMap.put(str.toLowerCase(),1);
                    if (maxCount < 1) {
                        maxCount = 1;
                    }
                }else {
                    int tmpMax = valueMap.get(str.toLowerCase());
                    if (maxCount < (tmpMax + 1)) {
                        maxCount = tmpMax + 1;
                    }
                    valueMap.put(str.toLowerCase(), tmpMax + 1);
                }
            }

            //打印
            for (Map.Entry<String, Integer> entry : valueMap.entrySet()) {
                if (entry.getValue() == maxCount) {
                    System.out.print(entry.getKey() + " ");
                }
            }
            System.out.print("");
    }
}
