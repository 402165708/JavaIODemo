package ali;

import java.util.*;

/**
 * Created by chenzhitao on 2018/9/12
 */
public class Main001 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String str = in.nextLine();

        int index = searchLastOneCharNoRepeat(str);
        if (index ==-1){
            System.out.println("NULL");
        }else {
            System.out.println(str.charAt(index));
        }
    }

    /**
     * 查询最后出现的无重复的字符
     * @param str
     * @return
     */
    private static int searchLastOneCharNoRepeat(final String str) {
        if (str == null || str == ""){
            return -1;
        }
        //  字符 - > 字符串下标
        Map<Character,Integer> eleMap = new HashMap<>(str.length());

        for (int i = str.length()-1; i >= 0 ; i--) {
            if (eleMap.containsKey(str.charAt(i))){
                //存在重复，丢弃指针
                eleMap.remove(str.charAt(i));
            }else {
                eleMap.put(str.charAt(i),i);
            }
        }

        int index = -1;
        for (Map.Entry<Character, Integer> entry : eleMap.entrySet()) {
            if (entry.getValue() > index){
                index = entry.getValue();
            }
        }
        return index;
    }
}
