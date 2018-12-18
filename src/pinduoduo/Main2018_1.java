package pinduoduo;

import java.util.Scanner;

/**
 * Created by chenzhitao on 2018/9/21
 */

//      在商城的某个位置有一个商品列表，该列表是由L1、L2两个子列表拼接而成。当用户浏览并翻页时，需要从列表L1、L2中获取商品进行展示。展示规则如下：
//
//        1. 用户可以进行多次翻页，用offset表示用户在之前页面已经浏览的商品数量，比如offset为4，表示用户已经看了4个商品
//
//        2. n表示当前页面需要展示的商品数量
//
//        3. 展示商品时首先使用列表L1，如果列表L1长度不够，再从列表L2中选取商品
//
//        4. 从列表L2中补全商品时，也可能存在数量不足的情况
//
//        请根据上述规则，计算列表L1和L2中哪些商品在当前页面被展示了


//    输入描述:
//            每个测试输入包含1个测试用例，包含四个整数，分别表示偏移量offset、元素数量n，列表L1的长度l1，列表L2的长度l2。
//
//    输出描述：
//            在一行内输出四个整数分别表示L1和L2的区间start1，end1，start2，end2，每个数字之间有一个空格。
//            注意，区间段使用半开半闭区间表示，即包含起点，不包含终点。如果某个列表的区间为空，使用[0, 0)表示，如果某个列表被跳过，使用[len, len)表示，len表示列表的长度。

public class Main2018_1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            System.out.println(splice(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt()));
        }
    }

    private static String splice(int offSet, int n, int l1, int l2) {
        // 获得第一个数组的起始位，依据的是offset信息
        int start1 = Math.min(l1, Math.max(0, offSet));
        // 第一个数组的结束位，依据的是offset和n信息
        int end1 = Math.max(0, Math.min(l1, offSet + n));
        // 获得第二个数组的起始位，依据的是offset信息
        int start2 = Math.min(l2,Math.max(0,offSet-l1));
        // 第二个数组的结束位，依据的是offset( (end1 - start1))和n信息
        int end2 = Math.max(0, Math.min(l2, n - (end1 - start1)));

        return start1 + " " + end1 + " " + start2 + " " + end2;

    }


}
