package pinduoduo;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by chenzhitao on 2018/9/21
 */
public class Main2 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        //
        int n = in.nextInt();
        
        Map<Integer, Integer> ele2CountMap = new HashMap<>();
        int preEle = -1;
        for (int i = 0; i < n; i++) {
            int memValue = in.nextInt();
            if (preEle == -1) {
                preEle = memValue;
                ele2CountMap.put(memValue,1);
                continue;
            }
            if (!ele2CountMap.containsKey(memValue)) {
                //新加入的元素，同时使前面的原始加一
                ele2CountMap.put(memValue, 1);
                ele2CountMap.put(preEle, ele2CountMap.get(preEle) + 1);
            }
            preEle = memValue;
        }

        //遍历求最大值
        int maxSize = 0;
        for (Map.Entry<Integer, Integer> entry : ele2CountMap.entrySet()) {
            if (entry.getValue() > maxSize) {
                maxSize = entry.getValue();
            }
        }
        System.out.println(maxSize-1);
    }

}
