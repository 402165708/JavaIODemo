package huya;

import java.util.*;

/**
 * Created by chenzhitao on 2018/9/19
 */
public class Main4 {

    public static void main(String [] args){
        int[] a = { 1, 4, 3, 8, 9, 3, 4, 1, 8, 3 };
        sortAndGroup(a);
    }

    private static int[] parseSampleInput() {
        Scanner sc = new Scanner(System.in);

        List<Integer> list = new ArrayList<>();
        while (sc.hasNextInt()){
            int value = sc.nextInt();
            list.add(value);
        }

        if (list == null || list.size() == 0){
            return new int[0];
        }

        int[] valueArr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            valueArr[i] = list.get(i);
        }

        return valueArr;
    }

    public static void sortAndGroup(int[] a) {
        int start = 0;
        int end = 0;
        System.out.print("[div]");
        while (start < a.length) {
            int min = Integer.MAX_VALUE;
            for (int i = start; i < a.length; i++) {
                if (min > a[i]) {
                    min = a[i];
                    swap(a, start, i);
                    end = start;
                } else if (min == a[i]) {
                    end = end + 1;
                    swap(a, end, i);
                }
            }
            System.out.print("[p]");
            for (int j = start; j <= end; j++) {
                System.out.print(min);
            }
            System.out.print("[/p]");
            start = end + 1;
            end = start;
        }
        System.out.print("[/div]");
    }

    private static void swap(int[] a, int start, int i) {
        int temp = a[start];
        a[start] = a[i];
        a[i] = temp;
    }
}
