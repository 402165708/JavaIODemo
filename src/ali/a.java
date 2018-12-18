//import java.text.DecimalFormat;
//import java.util.Scanner;
//
//public class Main {
//
//    public static void main(String[] args) {
//
//        Scanner scanner = new Scanner(System.in);
//        String s1 = scanner.nextLine();
//        String s2 = scanner.nextLine();
//        scanner.close();
//
//        String[] arr1 = s1.split(",");
//        Point target = new Main().new Point(Double.parseDouble(arr1[0]), Double.parseDouble(arr1[1]));
//
//        String[] arr2 = s2.split(",");
//        Point[] ps2 = new Point[arr2.length / 2];
//
//        int t = 0;
//        for (int i = 0; i < arr2.length; i = i + 2) {
//            ps2[t++] = new Main().new Point(Double.parseDouble(arr2[i]), Double.parseDouble(arr2[i + 1]));
//        }
//
//        boolean flag = isPtInPoly(target.getX(), target.getY(), ps2);
//
//        if (flag) {
//            System.out.println("yes,0");
//        } else {  //不在多边形内，计算距离
//            System.out.println("no,2");
//        }
//
//    }
//
//
//    //计算点到多边形的距离
//    public static double getLength(double ALon, double ALat, Point[] ps) {
//        return 2.0;
//    }
//
//    public static boolean isPtInPoly(double ALon, double ALat, Point[] ps) {
//        int iSum, iCount, iIndex;
//        double dLon1 = 0, dLon2 = 0, dLat1 = 0, dLat2 = 0, dLon;
//        if (ps.length < 3) {
//            return false;
//        }
//        iSum = 0;
//        iCount = ps.length;
//        for (iIndex = 0; iIndex < iCount; iIndex++) {
//            if (iIndex == iCount - 1) {
//                dLon1 = ps[iIndex].getX();
//                dLat1 = ps[iIndex].getY();
//                dLon2 = ps[0].getX();
//                dLat2 = ps[0].getY();
//            } else {
//                dLon1 = ps[iIndex].getX();
//                dLat1 = ps[iIndex].getY();
//                dLon2 = ps[iIndex + 1].getX();
//                dLat2 = ps[iIndex + 1].getY();
//            }
//            // 以下语句判断A点是否在边的两端点的水平平行线之间，在则可能有交点，开始判断交点是否在左射线上
//            if (((ALat >= dLat1) && (ALat < dLat2)) || ((ALat >= dLat2) && (ALat < dLat1))) {
//                if (Math.abs(dLat1 - dLat2) > 0) {
//                    // 得到 A点向左射线与边的交点的x坐标：
//                    dLon = dLon1 - ((dLon1 - dLon2) * (dLat1 - ALat)) / (dLat1 - dLat2);
//                    // 如果交点在A点左侧（说明是做射线与 边的交点），则射线与边的全部交点数加一：
//                    if (dLon < ALon) {
//                        iSum++;
//                    }
//                }
//            }
//        }
//        if ((iSum % 2) != 0) {
//            return true;
//        }
//        return false;
//    }
//
//
//    public class Point {
//        private Double x;
//        private Double y;
//
//        public Point(Double x, Double y) {
//            this.x = x;
//            this.y = y;
//        }
//
//        public Double getX() {
//            return x;
//        }
//
//        public void setX(Double x) {
//            this.x = x;
//        }
//
//        public Double getY() {
//            return y;
//        }
//
//        public void setY(Double y) {
//            this.y = y;
//        }
//    }
//
//}
