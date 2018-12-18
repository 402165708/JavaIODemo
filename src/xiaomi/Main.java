package xiaomi;

import java.util.*;

/**
 * Created by chenzhitao on 2018/9/20
 */
public class Main {

    public static void  main(String [] args){

        List<String> input = parseInput();
        // 存放异数input数组中value -> index
        Map<Integer,Integer> resultMap= new LinkedHashMap<>();
        getDiffNumber(input,resultMap);
        if (resultMap.size()>0){
            for (Map.Entry<Integer, Integer> entry : resultMap.entrySet()) {
                System.out.println(input.get(entry.getValue()));
            }
        }else {
            System.out.println("None");
        }



    }
    private static List<String> parseInput() {
        Scanner sc = new Scanner(System.in);

        List<String> list = new ArrayList<>();

        while (sc.hasNext()){
            String in = sc.next();
            if ("END".equals(in)){
                break;
            }
            list.add(in);
        }
        return list;

    }

    private static void getDiffNumber(List<String> input, Map<Integer, Integer> resultMap) {
        if (input == null || input.size() ==0){
            return;
        }

        Set<Integer> valueSet = new HashSet<>();
        for (int i = 0; i < input.size(); i++) {

            if (input.get(i) != null && input.get(i).contains("#")) {
                String[] radix2value = input.get(i).split("#");
                if (radix2value.length != 2) {
                    continue;
                }
                int value = Integer.parseInt(radix2value[1], Integer.parseInt(radix2value[0]));
                if (!valueSet.contains(value)){
                    resultMap.put(value,i);
                    valueSet.add(value);
                }else {
                    resultMap.remove(value);
                }
            }

        }

    }

}
