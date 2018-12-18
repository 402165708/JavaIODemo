//
//import java.util.Scanner;
//
///**
// * Created by chenzhitao on 2018/8/11
// */
//public class Main {
//
//
//
//
//
//    // n 塔数，k 移动数
//    // a[i] n个数据标识塔高
//    // 排序，求平局数，
//    // 输出  s 最大值，m移动数
//
//    // m行，表示从ai  到aj的一次操作
//
//    public static void main(String [] args){
//        Scanner scanner = new Scanner(System.in);
//
//        int n = scanner.nextInt();
//        int k = scanner.nextInt();
//
//        int[] values = new int[n];
//
//        for (int i = 0; i < n; i++) {
//            values[i] = scanner.nextInt();
//        }
//
//        action(n,k,values);
//    }
//
//    private static void action(int n, int k, int[] values) {
//        int sum = 0;
//        for (int val: values) {
//            sum += val;
//        }
//        int avg = sum/n;
//        //排序
//        int [] indexArr = new int[n];
//        quickSort(values,0,values.length-1,indexArr);
//
//        // 记录步数
//        int i = 0,j=values.length-1;
//        int[][] moveRord = new int[values.length][values.length];
//        int s = Integer.MIN_VALUE;  // 最大稳定值
//        int m = 0 ; //移动步数
//        while (i<j && k > m){
//            int outJ = 0,inI=0;
//            if (outJ == 0 && inI == 0){
//                outJ = values[j] -avg;
//                inI = avg - values[i];
//            }else  if (outJ > 0){
//                inI = avg - values[i];
//            }else  if (inI > 0){
//                outJ = values[j] -avg;
//            }
//            if (outJ == inI){
//                moveRord[j][i] = outJ;
//                if (s <  Math.abs(((values[j]-avg)-(values[i]-avg)))){
//                    s =  Math.abs(((values[j]-avg)-(values[i]-avg)));
//                }
//                i++;j--;
//                m += outJ;
//
//            }else if (outJ > inI){
//                moveRord[j][i] = inI;
//                if (s <  Math.abs(((values[j]-avg)-(values[i]-avg)))){
//                    s =  Math.abs(((values[j]-avg)-(values[i]-avg)));
//                }
//                outJ -= inI;
//                m += inI;
//                i ++;
//            }else if (outJ < inI){
//                moveRord[j][i] = outJ;
//                if (s <  Math.abs(((values[j]-avg)-(values[i]-avg)))){
//                    s =  Math.abs(((values[j]-avg)-(values[i]-avg)));
//                }
//                inI -=outJ;
//                m += outJ;
//                j--;
//            }
//        }
//
//        while (i < j){
//            if (s <  Math.abs(((values[j]-avg)-(values[i]-avg)))){
//                s =  Math.abs(((values[j]-avg)-(values[i]-avg)));
//            }
//
//
//
//
//        }
//
//        System.out.println(m + " " + s);
//
//        //打印moveRecord
//        for (int l = 0; l <moveRord.length; l++) {
//            for (int o = 0; o < moveRord[l].length; o++) {
//                int value = moveRord[l][o];
//                while (value <=0){
//                    System.out.println(indexArr[l] + " "+ indexArr[o]);
//                }
//            }
//        }
//
//    }
//
//
//    public static void quickSort(int[] array,int lo ,int hi ,int[] indexArr){
//        if(lo>=hi){
//            return ;
//        }
//        int index=partition(array,lo,hi,indexArr);
//        quickSort(array,lo,index-1,indexArr);
//        quickSort(array,index+1,hi,indexArr);
//    }
//
//    public static int partition(int [] array,int lo,int hi,int[] indexArr){
//        //固定的切分方式
//        int key=array[lo];
//        int keyIndex = lo;
//        while(lo<hi){
//            while(array[hi]>=key&&hi>lo){
//                //从后半部分向前扫描
//                hi--;
//            }
//            array[lo]=array[hi];
//            indexArr[lo] = hi;
//            while(array[lo]<=key&&hi>lo){
//                //从前半部分向后扫描
//                lo++;
//            }
//            array[hi]=array[lo];
//            indexArr[hi] = lo;
//
//        }
//        array[hi]=key;
//        indexArr[hi] = keyIndex;
//        return hi;
//    }
//}
