//package ali;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by chenzhitao on 2018/9/7
// */
//public class Test002 {
//
//    static class Point2D{
//        double x;
//        double y;
//
//        public Point2D(double x, double y) {
//            this.x = x;
//            this.y = y;
//        }
//    }
//
//
//    // 测试一个点是否在多边形内
//    public static void main(String[] args) {
//
//
//
//        Point2D point = new Point2D(116.404072, 39.916605);
//
//        List<Point2D> pts = new ArrayList<Point2D>();
//        pts.add(new Point2D(116.395, 39.910));
//        pts.add(new Point2D(116.394, 39.914));
//        pts.add(new Point2D(116.403, 39.920));
//        pts.add(new Point2D(116.402, 39.914));
//        pts.add(new Point2D(116.410, 39.913));
//
//
//
//        if(IsPtInPoly(point, pts)){
//            System.out.println("点在多边形内");
//        }else{
//            int minLenth = Integer.MAX_VALUE,tmpMinLenth = Integer.MAX_VALUE ;
//            Point2D minPoint1 = null;
//            Point2D minPoint2 = null;
//
//            for (int i = 0; i < pts.size(); i++) {
//                if (i < pts.size()-1) {
//                    (tmpMinLenth = pointToLine(pts[i], pts[i+1], point));
//                }else {
//                    pointToLine(pts[i], pts[0], point);
//                }
//                if (tmpMinLenth <minLenth){
//                    minLenth = tmpMinLenth;
//                    minPoint1 = pts[i];
//                    minPoint1 = pts[i+1];
//                }
//            }
//            System.out.println("点在多边形外");
//        }
//    }
//
//
//    /**
//     * 判断点是否在多边形内
//     * @param point 检测点
//     * @param pts   多边形的顶点
//     * @return      点在多边形内返回true,否则返回false
//     */
//    public static boolean IsPtInPoly(Point2D point, List<Point2D> pts){
//
//        int N = pts.size();
//        boolean boundOrVertex = true; //如果点位于多边形的顶点或边上，也算做点在多边形内，直接返回true
//        int intersectCount = 0;//cross points count of x
//        double precision = 2e-10; //浮点类型计算时候与0比较时候的容差
//        Point2D p1, p2;//neighbour bound vertices
//        Point2D p = point; //当前点
//
//        p1 = pts.get(0);//left vertex
//        for(int i = 1; i <= N; ++i){//check all rays
//            if(p.equals(p1)){
//                return boundOrVertex;//p is an vertex
//            }
//
//            p2 = pts.get(i % N);//right vertex
//            if(p.x < Math.min(p1.x, p2.x) || p.x > Math.max(p1.x, p2.x)){//ray is outside of our interests
//                p1 = p2;
//                continue;//next ray left point
//            }
//
//            if(p.x > Math.min(p1.x, p2.x) && p.x < Math.max(p1.x, p2.x)){//ray is crossing over by the algorithm (common part of)
//                if(p.y <= Math.max(p1.y, p2.y)){//x is before of ray
//                    if(p1.x == p2.x && p.y >= Math.min(p1.y, p2.y)){//overlies on a horizontal ray
//                        return boundOrVertex;
//                    }
//
//                    if(p1.y == p2.y){//ray is vertical
//                        if(p1.y == p.y){//overlies on a vertical ray
//                            return boundOrVertex;
//                        }else{//before ray
//                            ++intersectCount;
//                        }
//                    }else{//cross point on the left side
//                        double xinters = (p.x - p1.x) * (p2.y - p1.y) / (p2.x - p1.x) + p1.y;//cross point of y
//                        if(Math.abs(p.y - xinters) < precision){//overlies on a ray
//                            return boundOrVertex;
//                        }
//
//                        if(p.y < xinters){//before ray
//                            ++intersectCount;
//                        }
//                    }
//                }
//            }else{//special case when ray is crossing through the vertex
//                if(p.x == p2.x && p.y <= p2.y){//p crossing over p2
//                    Point2D p3 = pts.get((i+1) % N); //next vertex
//                    if(p.x >= Math.min(p1.x, p3.x) && p.x <= Math.max(p1.x, p3.x)){//p.x lies between p1.x & p3.x
//                        ++intersectCount;
//                    }else{
//                        intersectCount += 2;
//                    }
//                }
//            }
//            p1 = p2;//next ray left point
//        }
//
//        if(intersectCount % 2 == 0){//偶数在多边形外
//            return false;
//        } else { //奇数在多边形内
//            return true;
//        }
//    }
//
//    /**
//     *  点到直线的最短距离的判断 点（x0,y0） 到由两点组成的线段（x1,y1） ,( x2,y2 )
//     */
//    public static double pointToLine(Point2D p1, Point2D p2, Point2D p0) {
//        double space = 0;
//        double a, b, c;
//        a = lineSpace(p1.x, p1.y, p2.x, p2.y);// 线段的长度
//        b = lineSpace(p1.x, p1.y, p0.x, p0.y);// (x1,y1)到点的距离
//        c = lineSpace(p2.x, p2.y,  p0.x, p0.y);// (x2,y2)到点的距离
//        if (c+b == a) {//点在线段上
//            space = 0;
//            return space;
//        }
//        if (a <= 0.000001) {//不是线段，是一个点
//            space = b;
//            return space;
//        }
//        if (c * c >= a * a + b * b) { //组成直角三角形或钝角三角形，(x1,y1)为直角或钝角
//            space = b;
//            return space;
//        }
//        if (b * b >= a * a + c * c) {//组成直角三角形或钝角三角形，(x2,y2)为直角或钝角
//            space = c;
//            return space;
//        }
//        //组成锐角三角形，则求三角形的高
//        double p = (a + b + c) / 2;// 半周长
//        double s = Math.sqrt(p * (p - a) * (p - b) * (p - c));// 海伦公式求面积
//        space = 2 * s / a;// 返回点到线的距离（利用三角形面积公式求高）
//        return space;
//    }
//
//    /**
//     * 计算两点之间的距离
//     */
//    public static double lineSpace(double x1, double y1, double x2, double y2) {
//        double lineLength = 0;
//        lineLength = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
//        return lineLength;
//    }
//
//
//
//
//}
