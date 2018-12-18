package xiaomi;

import java.util.Scanner;

public class Main2 {


    /*请完成下面这个函数，实现题目要求的功能
    当然，你也可以不按照下面这个模板来作答，完全按照自己的想法来 ^-^
    ******************************开始写代码******************************/
    static int findMaxCapacity(int[] array, int m) {
        if (array.length == 0) {
            return 0;
        }
        int i = 0, j = array.length - 1;
        int maxCap = 0;
        while (i <= j) {
            int high = (array[i] + array[j] + m) / 2;
            int tmpCap = actionFind(array, i, j, high);
            if (tmpCap > maxCap) {
                maxCap = tmpCap;
            }
            if (array[i] < array[j]) {
                i++;
            } else {
                j--;
            }
        }

        return maxCap;


    }

    private static int actionFind(int[] array, int i, int j, int high) {
        if (array == null || array.length == 0){
            return 0;
        }
        if (i < 0 || i>=j || j> array.length){
            return 0;
        }
        int cap = 0;
        for (int k = i + 1; k < j; k++) {
            if (high - array[i] > 0) {
                cap += (high - array[i]);
            }
        }
        return cap;

    }


    /******************************结束写代码******************************/


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int res;

        int _array_size = 0;
        _array_size = Integer.parseInt(in.nextLine().trim());
        int[] _array = new int[_array_size];
        int _array_item;
        for (int _array_i = 0; _array_i < _array_size; _array_i++) {
            _array_item = Integer.parseInt(in.nextLine().trim());
            _array[_array_i] = _array_item;
        }

        int _m;
        _m = Integer.parseInt(in.nextLine().trim());

        res = findMaxCapacity(_array, _m);
        System.out.println(String.valueOf(res));

    }

}
