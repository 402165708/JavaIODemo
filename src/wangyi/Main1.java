//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//import java.util.Set;
//
//public class Main {
//
//    public static void main(String[] args) {
//
//        Scanner sc = new Scanner(System.in);
//
//        while (sc.hasNext()) {
//            //数组元素用例个数
//            String valueInput = sc.nextLine();
//            int target = sc.nextInt();
//
//            if (valueInput == null || "".equals(valueInput.trim())) {
//                return;
//            }
//
//            String[] valueArr = valueInput.split("\\|");
//            if (valueArr == null || valueArr.length == 0) {
//                return;
//            }
//            String[] eleArr = valueArr[0].split("\\, ");
//            int[][] eleList = new int[valueArr.length][eleArr.length];
//            for (int i = 0; i < valueArr.length; i++) {
//                String[] valArr = valueArr[i].split("\\, ");
//                for (int j = 0; j < valArr.length; j++) {
//                    eleList[i][j] = Integer.parseInt(valArr[j].trim());
//                }
//            }
//
//            boolean exited = findExit(eleList, eleList.length, eleList[0].length, target);
//            System.out.println(exited);
//
//        }
//    }
//
//
//
//    public List<String> wordBreak(String s, Set<String> dict) {
//        List<String> list = new ArrayList<String>();
//        List<String> ret = new ArrayList<String>();
//        action(s, dict, list, ret);
//        return ret;
//    }
//
//    public void action(String s, Set<String> dict, List<String> list, List<String> ret) {
//        if(!isBreak(s, dict)){
//            return;
//        }
//        if(s.length() == 0) {
//            String concat = "";
//            for(int i=0; i<list.size(); i++) {
//                concat += list.get(i);
//                if(i != list.size()-1) {
//                    concat += " ";
//                }
//            }
//            ret.add(concat);
//            return;
//        }
//
//        for(String cur : dict) {
//            if(cur.length() > s.length()) {
//                continue;
//            }
//            String substr = s.substring(0, cur.length());
//            if(substr.equals(cur)) {
//                list.add(substr);
//                rec(s.substring(cur.length()), dict, list, ret);
//                list.remove(list.size()-1);
//            }
//        }
//    }
//
//    public boolean isBreak(String s, Set<String> dict) {
//        boolean[] canBreak = new boolean[s.length()+1];
//        canBreak[0] = true;
//
//        for(int i=1; i<=s.length(); i++) {
//            boolean flag = false;
//            for(int j=0; j<i; j++) {
//                if(canBreak[j] && dict.contains(s.substring(j,i))) {
//                    flag = true;
//                    break;
//                }
//            }
//            canBreak[i] = flag;
//        }
//        return canBreak[s.length()];
//    }
//}
