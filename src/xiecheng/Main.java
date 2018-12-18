package xiecheng;

import java.util.Scanner;

/**
 * Created by chenzhitao on 2018/9/27
 */
public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            //数组元素用例个数
            String valueInput = sc.nextLine();
            int target = sc.nextInt();

            if (valueInput == null || "".equals(valueInput.trim())) {
                return;
            }

            String[] valueArr = valueInput.split("\\|");
            if (valueArr == null || valueArr.length == 0) {
                return;
            }
            String[] eleArr = valueArr[0].split("\\, ");
            int[][] eleList = new int[valueArr.length][eleArr.length];
            for (int i = 0; i < valueArr.length; i++) {
                String[] valArr = valueArr[i].split("\\, ");
                for (int j = 0; j < valArr.length; j++) {
                    eleList[i][j] = Integer.parseInt(valArr[j].trim());
                }
            }

            boolean exited = findExit(eleList, eleList.length, eleList[0].length, target);
            System.out.println(exited);

        }
    }

    private static boolean findExit(int[][] eleList, int m, int n, int target) {

        if (eleList[0][0] > target || eleList[m-1][n-1] < target){
            return false;
        }

        int i = m-1,j=0;

        while (i >= 0 && j < n){

            if (target > eleList[i][j] ){
                j++;
            }else if (target < eleList[i][j]){
                i--;
            }else {
                return true;
            }
        }
        return false;
    }


}
