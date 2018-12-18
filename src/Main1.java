//import java.util.Scanner;
//
//public class Main1 {
//    static int minPathLength = Integer.MAX_VALUE;
//
//    final static Point START = new Point(0,0);
//
//    public static void calculate(Point start, Point[] points, int sum,int count) {
//
//        for (int i = 0; i < points.length; i++) {
//            if (points[i].isVisited == false) {
//                points[i].isVisited = true;
//                count++;
//                sum += start.getLength(points[i]);
//                if (count == points.length){
//                    sum+=points[i].getLength(START);
//                    if (sum<minPathLength){
//                        minPathLength = sum;
//                    }
//                }
//                calculate(points[i], points, sum,count);
//                points[i].isVisited = false;
//                count--;
//                sum -= start.getLength(points[i]);
//            }
//        }
//
//    }
//
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int num = Integer.parseInt(scanner.nextLine().trim());
//        Point[] points = new Point[num];
//        for (int i = 0; i < num; i++) {
//            String[] locations = scanner.nextLine().trim().split(",");
//            points[i] = new Point(Integer.parseInt(locations[0]), Integer.parseInt(locations[1]));
//        }
//        calculate(new Point(0,0),points,0,0);
//        System.out.println(minPathLength);
//    }
//}
//
//
//class Point {
//    int x, y;
//    boolean isVisited;
//
//    public Point(int x, int y) {
//        this.x = x;
//        this.y = y;
//        this.isVisited = false;
//    }
//
//    public int getLength(Point point) {
//        return Math.abs(this.x - point.x) + Math.abs(this.y - point.y);
//    }
//}
