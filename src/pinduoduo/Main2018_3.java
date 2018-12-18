package pinduoduo;

import java.util.Scanner;

/**
 * Created by chenzhitao on 2018/9/21
 */
public class Main2018_3 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //点数
        int count = sc.nextInt();
        Point[] pointArr = new Point[count];
        int i = 0;
        while (sc.hasNext()) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            pointArr[i++] = new Point(x, y);
        }
        System.out.println(calSum(pointArr));
    }

    private static int calSum(Point[] pointArr) {
        int sum = 0;
        if (pointArr == null || pointArr.length == 0) {
            return sum;
        }

        for (int i = 0; i < pointArr.length - 2; i++) {
            for (int j = i + 1; j < pointArr.length - 1; j++) {
                for (int k = j + 1; k < pointArr.length; k++) {
                    if (actionCal(pointArr[i], pointArr[j], pointArr[k])) {
                        sum++;
                    }
                }
            }
        }
        return sum;


    }

    private static boolean actionCal(Point point, Point point1, Point point2) {
        if (point == null || point1 == null || point2 == null) {
            return false;
        }
        // 判断三个点是否共线
        if (point.getX() == point1.getX()) {
            return point.getX() == point2.getX();
        } else if (point1.getX() == point2.getX()) {
            return point.getX() == point1.getX();
        }
        return ((point.getY() - point1.getY()) / (point.getX() - point1.getX())) == ((point1.getY() - point2.getY()) / (point1.getX() - point2.getX()));


    }

    static class Point {
        private double x;
        private double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public double getX() {
            return x;
        }

        public void setX(double x) {
            this.x = x;
        }

        public double getY() {
            return y;
        }

        public void setY(double y) {
            this.y = y;
        }
    }
}
