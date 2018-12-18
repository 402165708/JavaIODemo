import java.util.LinkedList;
import java.util.Scanner;

public class Main1 {




    public static void main(String[] args) {

        int i = 128;
        Integer i2 = 128;
        Integer i3 = new Integer(128);
        System.out.println(i == i2); //Integer会自动拆箱为int，所以为true
        System.out.println(i == i3); //true，理由同上
        Integer i4 = 127;//编译时被翻译成：Integer i4 = Integer.valueOf(127);
        Integer i5 = 127;
        System.out.println(i4 == i5);//true
        Integer i6 = 128;
        Integer i7 = 128;
        System.out.println(i6 == i7);//false
        Integer i8 = new Integer(127);
        System.out.println(i5 == i8); //false
        Integer i9 = new Integer(128);
        Integer i10 = new Integer(123);
        System.out.println(i9 == i10);

//        String a = "hello2";
//        final String b = "hello";
//        String d = "hello";
//        String c = b + 2;
//        String e = d + 2;
//        System.out.println((a == c));
//        System.out.println((a == e));



//        Scanner in = new Scanner(System.in);
//
//        int num = in.nextInt();
//;
//        int[][] linkedArr = new int[num + 1][num + 1];
//
//        boolean[] isVisited = new boolean[num + 1];
//
//        in.nextLine();
//        int i = 1;
//        while (in.hasNext()) {
//            //注意while处理多个case
//            String s = in.nextLine();
//            String[] ss = s.split(" ");
//            for (int j = 0; j < ss.length; j++) {
//                int value = Integer.parseInt(ss[j]);
//                if (value == 0){
//                    break;
//                }
//                linkedArr[i][value] = 1;
//
//            }
//            i++;
////
////            while (in.hasNextInt()) {
////                int link = in.nextInt();
////
////            }
//
    }

//        for (int[] ints : linkedArr) {
//            for (int anInt : ints) {
//                System.out.println(anInt);
//            }
//        }
//
//        int m = calculate(linkedArr, isVisited);


    /**
     * 统计分组数
     *
     * @param linkedArr
     * @param isVisited
     * @return
     */
    private static int calculate(int[][] linkedArr, boolean[] isVisited) {
        int m = 0, i = 1;
        while (i < linkedArr.length) {
            if (!isVisited[i]) {
                broadFirstSearch(linkedArr, i, isVisited);
                m++;
            }
            i++;
        }
        return m;
    }


    /**
     * 获取某个顶点的第一个邻接点
     */

    private static int getFirstNeighbor(int[][] linkedArr, int index, int length) {
        for (int j = 1; j < length; j++) {
            if (linkedArr[index][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    /**
     * 根据前一个邻接点的下标来取得下一个邻接点
     */
    private static int getNextNeighbor(int[][] linkArr, int v, int index, int length) {
        for (int j = index + 1; j < length; j++) {
            if (linkArr[v][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    /**
     * 实现广度优先遍历
     *
     * @param i
     */
    public static void broadFirstSearch(int[][] linkArr, int i, boolean[] isVisited) {
        int u, w;
        LinkedList<Integer> queue = new LinkedList<Integer>();
        isVisited[i] = true;
        //第一次把v0加到队列
        queue.add(i);
        while (!queue.isEmpty()) {
            u = (Integer) (queue.removeFirst()).intValue();
            w = getFirstNeighbor(linkArr, u, linkArr.length);
            while (w != -1) {
                if (!isVisited[w]) {
                    isVisited[w] = true;
                    queue.add(w);
                }
                w = getNextNeighbor(linkArr, u, w, linkArr.length);
            }
        }

    }
}
