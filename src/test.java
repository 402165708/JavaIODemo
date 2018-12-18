import java.util.*;

/**
 * Created by chenzhitao on 2018/1/12
 */
public class test {

    /**
     * 把字符串的空字符替换成"%20"
     *
     * @param str
     * @return
     */
    public static String replaceSpace(StringBuffer str) {
        StringBuilder sb = new StringBuilder();
        if (str != null && str.length() > 0) {
            for (int i = 0; i < str.length(); i++) {
                if (' ' == str.charAt(i)) {
                    sb.append("%20");
                } else {
                    sb.append(str.charAt(i));
                }
            }
        }
        return sb.toString();
    }

    /**
     * 提供一个从左到右，从上到下 有序递增的二维数组，验证target是否存在
     *
     * @param target
     * @param array
     * @return
     */
    public boolean Find(int target, int[][] array) {
        int k = array.length;
        int g = array[0].length;
        int i = k - 1;
        int j = 0;
        while ((i >= -1 && i < k) && (j >= -1 && j < g)) {
            if (target == array[i][j]) {
                return true;
            } else if (target < array[i][j]) {
                i--;
            } else if (target > array[i][j]) {
                j++;
            }
        }
        return false;
    }

    /**
     * 遍历链表，输入倒序的数组
     *
     * @param listNode
     * @return
     */
//    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
//        if (listNode == null) {
//            return new ArrayList<>();
//        }
//        Stack<Integer> stack = new Stack<>();
//        while (listNode.next != null) {
//            stack.push(listNode.val);
//            listNode = listNode.next;
//        }
//        ArrayList<Integer> list = new ArrayList<>();
//        if (!stack.isEmpty()) {
//            for (Integer value : stack) {
//                list.add(value);
//            }
//        }
//        return list;
//    }

    /**
     * 现在要求输入一个整数n，请你输出斐波那契数列的第n项（从0开始，第0项为0）
     * 斐波拉契数列 f(n)= f(n-1) + f (n-2)
     *
     * @param n
     * @return
     */
    public int Fibonacci(int n) {
        int k = 0, g = 1;
        while (n-- > 0) {
            g += k;
            k = g - k;
        }
        return k;
    }

    /**
     * 一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
     * 解法： 第一次跳，可以跳一个，或两个，f(n)= f(n-1) + f (n-2),类似 斐波那契数列数列
     *
     * @param target
     * @return
     */
    public int JumpFloor(int target) {
        int k = 0, g = 1;
        while (target-- > 0) {
            g += k;
            k = g - k;
        }
        return g;
    }

    /**
     * 输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
     * 使用1 进行异或比较
     * @param n
     * @return
     */
    public int NumberOf1(int n) {
        int count = 0;
        int flag = 1;
        while (flag != 0) {
            if ((n & flag) != 0) {
                count++;
            }
            flag = flag << 1;
        }
        return count;
    }

    /**
     * 顺时针打印二维数组。
     * 定义四个标识变量，记录左上右下的打印范围
     * 如果行row或者column列是偶数，则最后只会剩下两列或者行；奇数则只剩一行
     *
     * @param matrix
     * @return
     */
    public ArrayList<Integer> printMatrix(int [][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;

        ArrayList<Integer> resultList = new ArrayList<>();
        // 输入的二维数组非法，返回空的数组
        if (row == 0 || col == 0)  {return new ArrayList<>();}

        // 定义四个关键变量，表示左上右下的打印范围
        int left = 0, top = 0, right = col - 1, bottom = row - 1;
        while (left <= right && top <= bottom)
        {
            // left to right
            for (int i = left; i <= right; ++i) {
                resultList.add(matrix[top][i]);
            }
            // top to bottom
            for (int i = top + 1; i <= bottom; ++i) {
                resultList.add(matrix[i][right]);
            }
            // right to left
            if (top != bottom) {
                for (int i = right - 1; i >= left; --i) {
                    resultList.add(matrix[bottom][i]);
                }
            }
            // bottom to top
            if (left != right) {
                for (int i = bottom - 1; i > top; --i) {
                    resultList.add(matrix[i][left]);
                }
            }
            // 最后更新状态位
            left++; right--; top++; bottom--;
        }
        return resultList;
    }

    /**
     * 定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数（时间复杂度应为O（1））。
     * 使用数组记录数据，每次输入的最小值。
     *
     */
    public List<Integer> dataList = new ArrayList<>();
    public List<Integer> minList = new ArrayList<>();
    int min = Integer.MAX_VALUE;

    public void push(int node) {
        if (node < min) {
            min = node;
        }
        dataList.add(node);
        minList.add(min);
    }

    public void pop() {
        dataList.remove(dataList.size()-1);
        minList.remove(minList.size()-1);

    }

    public int top() {
        return dataList.get(dataList.size()-1);

    }

    public int min() {
        return minList.get(minList.size()-1);
    }


    /**
     * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否可能为该栈的弹出顺序。
     * 模拟入栈
     * @param pushA
     * @param popA
     * @return
     */
    public static boolean IsPopOrder(int [] pushA,int [] popA) {

        if (popA.length == 0 || pushA.length == 0){
            return false;
        }
        Stack<Integer> stack = new Stack<>();
        int popAIndex = 0, pushAIndex = 0;
        while (popAIndex < popA.length){
            if (stack.peek() != popA[popAIndex] ){
                if (pushAIndex == pushA.length) {
                    return false;
                }
                // 栈顶元素与出栈元素不同，入栈
                stack.push(pushA[pushAIndex]);

            }else {
                //相同，出栈
                stack.pop();
                popAIndex ++;
            }
            pushAIndex ++;

        }
        return true;

    }

//    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
//        ArrayList<Integer> list = new ArrayList<Integer>();
//        if(root == null) return list;
//        List<TreeNode> dataList = new LinkedList<TreeNode>();
//
//        dataList.add(root);
//        while(!dataList.isEmpty()){
//            TreeNode t = dataList.removeFirst();
//            list.add(t.val);
//            if(t.left != null) dataList.add(t.left);
//            if(t.right != null) dataList.add(t.right);
//        }
//        return list;
//    }


    /**
     *  main
     * @param args
     */
    public static void main(String[] args) {
//        String s = "chen";
//        String s1 = "zhitao";
//
//        String s2 = s + s1;
//        String s3 = "chen" + "zhitao";
//
//
//        System.out.println("chenzhi");
//
//        Integer num = 17;
//        System.out.println(Integer.valueOf("17"));
//        System.out.println(Integer.valueOf("17", 8));
//        System.out.println(Integer.valueOf("17", 16));
//
//        System.out.println("==================");
//        Map<String, Map> map = new HashMap<>();
//
//        Map<Integer, String> eleMap = new HashMap<>();
//        eleMap.put(1, "chen");
//        eleMap.put(2, "zhi");
//        eleMap.put(3, "tao");
//
//        map.put("name", eleMap);
//
//        Map<Integer, String> valueMap = map.get("name");
//        valueMap.remove(1);
//
//        System.out.println(map);
//
//        System.out.println(replaceSpace(new StringBuffer("We Are Hapy.")));

//        int[] pushA = {1,2,3,4,5};
//        int[] popA = {4,5,3,2,1};
//        System.out.println(pushA[5]);

//        String[] arr = string.split("\\|\\|\\|");
//        String[] arr1 = string1.split("u007Cu007Cu007C");

//        System.out.println(arr[0] + "   " +arr[1]);
//        System.out.println(arr1[0] + "   " +arr1[1]);

        String string = new StringBuilder("计算机").append("软件").toString();
        System.out.println(string == string.intern());

        String string1 = new StringBuilder("ja").append("va").toString();
        System.out.println(string1 == string1.intern());



//        System.out.println(IsPopOrder(pushA,popA));

    }





}
