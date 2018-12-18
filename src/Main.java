//import java.util.LinkedList;
//import java.util.Scanner;
//
//public class Main {
//
//
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//
//        int num = in.nextInt();
//
//        int[][] linkedArr = new int[num + 1][num + 1];
//
//        boolean[] isVisited = new boolean[num + 1];
//
//        int i = 1;
//        while (in.hasNextInt()) {
//            //注意while处理多个case
//            while (in.hasNextInt()) {
//                int link = in.nextInt();
//                linkedArr[i][link] = 1;
//            }
//            i++;
//        }
//
//        for (int[] ints : linkedArr) {
//            for (int anInt : ints) {
//                System.out.println(anInt);
//            }
//        }
//
//        int m = calculate(linkedArr, isVisited);
//    }
//
//    /**
//     * 统计分组数
//     *
//     * @param linkedArr
//     * @param isVisited
//     * @return
//     */
//    private static int calculate(int[][] linkedArr, boolean[] isVisited) {
//        int m = 0, i = 1;
//        while (i < linkedArr.length) {
//            if (!isVisited[i]) {
//                broadFirstSearch(linkedArr, i, isVisited);
//                m++;
//            }
//            i++;
//        }
//        return m;
//    }
//
//
//    /**
//     * 获取某个顶点的第一个邻接点
//     */
//
//    private static int getFirstNeighbor(int[][] linkedArr, int index, int length) {
//        for (int j = 1; j < length; j++) {
//            if (linkedArr[index][j] > 0) {
//                return j;
//            }
//        }
//        return -1;
//    }
//
//    /**
//     * 根据前一个邻接点的下标来取得下一个邻接点
//     */
//    private static int getNextNeighbor(int[][] linkArr, int v, int index, int length) {
//        for (int j = index + 1; j < length; j++) {
//            if (linkArr[v][j] > 0) {
//                return j;
//            }
//        }
//        return -1;
//    }
//
//    /**
//     * 实现广度优先遍历
//     *
//     * @param i
//     */
//    public static void broadFirstSearch(int[][] linkArr, int i, boolean[] isVisited) {
//        int u, w;
//        LinkedList<Integer> queue = new LinkedList<Integer>();
//        isVisited[i] = true;
//        //第一次把v0加到队列
//        queue.add(i);
//        while (!queue.isEmpty()) {
//            u = (Integer) (queue.removeFirst()).intValue();
//            w = getFirstNeighbor(linkArr, u, linkArr.length);
//            while (w != -1) {
//                if (!isVisited[w]) {
//                    isVisited[w] = true;
//                    queue.add(w);
//                }
//                w = getNextNeighbor(linkArr, u, w, linkArr.length);
//            }
//        }
//
////
////
//    }
//}
