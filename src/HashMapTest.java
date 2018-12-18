import com.sun.scenario.effect.impl.state.LinearConvolveRenderState;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by chenzhitao on 2018/6/11
 */
public class HashMapTest {


    public static void main(String [] args){

        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
    }



     public int getSameElemnt(List<String> baseList, List<String> otherList ){
        if (baseList ==null || baseList.size() == 0 ){
            return 0;
        }
        if (otherList ==null || otherList.size() == 0 ){
             return 0;
        }
        Set<String> baseSet = new HashSet<>(baseList.size());

         for (String s : baseList) {
             baseSet.add(s);
         }
         int num = 0;
         Set<String> otherSet = new HashSet<>(otherList.size());
         for (String str : otherList) {
             if (str != null){
                 if (baseSet.contains(str) && !otherSet.contains(str)){
                     num++;
                     otherSet.add(str);
                 }
             }
         }
         return num;

     }


}
