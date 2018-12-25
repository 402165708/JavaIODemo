package jianzi_offer;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @Description :
 * @Author : chenzhitao
 * @Date : Created in 11:03 2018/12/18
 */
public class Coding {

    private Stack inStack = new Stack();
    private Stack outStack = new Stack();

    private Queue inQueue = new LinkedList();
    private Queue outQueue = new LinkedList();

    public static void main(String[] args) {

        Coding coding = new Coding();
        int[][] arr = {{1, 2, 8, 9}, {2, 4, 9, 12}, {4, 7, 10, 13}, {6, 8, 11, 15}};
//        System.out.println(coding.findInSortArray(arr,4,4,0));
//        System.out.println(coding.findInSortArray(arr,4,4,16));
//        System.out.println(coding.findInSortArray(arr, 4, 4, 4));

        //测试替换空格
        String string = " hello world ! ";
//        System.out.println(coding.replaceBlank(string));

//        System.out.println(coding.powder(3, -3));
//        System.out.println(coding.powder(3, 0));
//        System.out.println(coding.powder(3, 3));
//        System.out.println(coding.powder(-3, -3));
//        System.out.println(coding.powder(0, -3));

        coding.printToMaxNDigits(0);
        coding.printToMaxNDigits(2);
        coding.printToMaxNDigits(-2);
        coding.printToMaxNDigits(2 ^ 31);

    }


    // --------  递增二维数组查找  值 --------------

    /**
     * 查找二维数组是否存在值。二维数组是从左到右，从上到下是递增数组
     * <p>
     * 测试用例关注:
     * 1、查找值大于最大值和最小值情况；查找值介于最大值和最小值之间情况
     * 2、数组参数空输入，越界输入
     *
     * @param arr
     * @param rows    行数
     * @param columns 列数
     * @param target  目标值
     * @return
     */
    public boolean findInSortArray(int[][] arr, int rows, int columns, int target) {
        if (arr == null || arr.length == 0 || arr[0] == null || arr[0].length == 0) {
            throw new NullPointerException("Param.arr is null!");
        }
        if (rows < 0 || rows > arr.length) {
            throw new IndexOutOfBoundsException("Param.rows = " + rows);
        }
        if (columns < 0 || columns > arr[0].length) {
            throw new IndexOutOfBoundsException("Param.columns = " + columns);
        }

        if ((target < arr[0][0]) || target > arr[arr.length - 1][arr[0].length - 1]) {
            // 目标数小于最小值，或大于最大值，不存在
            return false;
        }
        if ((target == arr[0][0]) || target == arr[arr.length - 1][arr[0].length - 1]) {
            // 目标数等于最小值或最大值，存在
            return true;
        }

        int row = 0, column = columns - 1;
        while (row < rows && column >= 0) {
            if (target == arr[row][column]) {
                return true;
            } else if (target > arr[row][column]) {
                // 比较下边
                row++;
            } else {
                // 比较左边
                column--;
            }
        }
        return false;
    }

    // ------- 链表操作 --------------

    /**
     * 查找二维数组是否存在值。二维数组是从左到右，从上到下是递增数组
     *
     * @param matrix  一维数组表示二维数组
     * @param rows    行数
     * @param columns 列数
     * @param target  目标值
     * @return
     */
    public boolean findInOneMatrix(int[] matrix, int rows, int columns, int target) {
        if (matrix == null || matrix.length == 0) {
            throw new NullPointerException("Param.matrix is null!");
        }
        if (rows < 0 || rows >= matrix.length) {
            throw new IndexOutOfBoundsException("Param.rows = " + rows);
        }
        if (columns < 0 || (rows * columns) > matrix.length) {
            throw new IndexOutOfBoundsException("Param.columns = " + columns);
        }

        if (target < matrix[0] || target > matrix[rows * columns - 1]) {
            // 目标数小于最小值，或大于最大值，不存在
            return false;
        }
        if (target == matrix[0] || target == matrix[rows * columns - 1]) {
            // 目标数小于最小值，或大于最大值，不存在
            return true;
        }

        int row = 0, column = columns - 1;
        while (row < rows && column >= 0) {
            //
            if (target == matrix[row * columns + column]) {
                return true;
            } else if (target > matrix[row * columns + column]) {
                // 比较下边
                row++;
            } else {
                // 比较左边
                column--;
            }
        }
        return false;
    }


    //---------  字符串替换空格  -------

    /**
     * 使用 "%20"，替代字符串中的空格
     *
     * @param str 输入字符串
     * @return
     */
    public String replaceBlank(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        // 统计空格数
        int blankCount = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ') {
                blankCount++;
            }
        }
        if (blankCount == 0) {
            // 无空格
            return str;
        }

        // 替换
        char[] newChar = new char[str.length() + blankCount * 2];
        int index = 0;
        // 替换空格
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ') {
                newChar[index++] = '%';
                newChar[index++] = '2';
                newChar[index++] = '0';
            } else {
                newChar[index++] = str.charAt(i);
            }
        }
        return new String(newChar);
    }

    /**
     * 单链表，头插法，逆序插入
     *
     * @param head
     * @param value
     */
    public synchronized void addToHead(ListNode head, int value) {
        ListNode node = new ListNode();
        node.value = value;
        node.next = null;
        if (head == null) {
            head = node;
        } else {
            node.next = head;
            head = node;
        }
    }

    /**
     * 尾插法，顺序插入
     *
     * @param head
     * @param value
     */
    public synchronized void addToTail(ListNode head, int value) {
        ListNode node = new ListNode();
        node.value = value;
        node.next = null;
        if (head == null) {
            head = node;
        } else {
            ListNode pre = head;
            // 遍历到链表末尾
            while (pre.next != null) {
                pre = pre.next;
            }
            // 插入
            pre.next = node;

        }
    }

    /**
     * 移除第一次出现的这个值的节点
     * <p>
     * 节点删除会出现两种情况
     * 1、删除的节点是头节点，需要后节点来替换头节点信息；
     * 2、非头节点，遍历链表，使用pre节点记录删除节点的前一节点；
     * 如果找到，使用pre.next = pre.next.next完成;
     * 没有找到，不错删除处理
     *
     * @param head
     * @param value
     */
    public synchronized void removeFirstNode(ListNode head, int value) {
        if (head == null) {
            throw new NullPointerException("Param.head is null");
        }

        ListNode preNode = head;
        if (preNode.value == value) {
            //删除第一个节点
            head = preNode.next;
        } else {
            while (preNode.next != null && preNode.next.value != value) {
                //查找
                preNode = preNode.next;
            }
            if (preNode.next == null) {
                // 没有找到节点
            } else {
                // 找到节点
                preNode.next = preNode.next.next;
            }
        }
        return;
    }


    //---------- 树 -----------------

    /**
     * 从头到尾打印单链表, 借助栈，先进后出的思路
     *
     * @param head
     */
    public void printListReverse(ListNode head) {
        if (head == null) {
            throw new NullPointerException("Param.head is null!");
        }
        // 只有一个元素，直接输出
        if (head.next == null) {
            System.out.println(head.value);
        } else {
            Stack<Integer> nodeStack = new Stack<>();
            ListNode node = head;
            while (node.next != null) {
                nodeStack.push(node.value);
                node = node.next;
            }
            while (!nodeStack.isEmpty()) {
                System.out.print(nodeStack.pop() + " <-");
            }
        }
    }

    // ----- 使用两个栈实现一个队列 -----

    /**
     * 入队列
     *
     * @param value
     * @param <T>
     */
    public synchronized <T> void addQueueTail(T value) {
        inStack.push(value);
    }

    /**
     * 出队列
     *
     * @param <T>
     * @return
     * @throws Exception
     */
    public synchronized <T> T deQueueHead() throws Exception {
        //
        if (outStack.isEmpty()) {
            while (!inStack.isEmpty()) {
                T value = (T) inStack.pop();
                outStack.push(value);
            }
        }
        if (outStack.isEmpty()) {
            throw new Exception(" queue is empty");
        }
        return (T) outStack.pop();
    }

    // -------  使用两个队列实现一个栈 -------------

    /**
     * 入栈
     *
     * @param value
     * @param <T>
     */
    public synchronized <T> void stackPush(T value) {
        inQueue.offer(value);
    }

    public synchronized <T> T stackPop() throws Exception {
        if (inQueue.size() >= 1) {
            while (inQueue.size() == 1) {
                T head = (T) inQueue.poll();
                outQueue.offer(head);
            }
        } else {
            // 入队列为空
            if (outQueue.size() == 0) {
                throw new Exception("stack is Empty");
            } else {
                while (outQueue.size() == 1) {
                    T head = (T) outQueue.poll();
                    inQueue.offer(head);
                }
                //交换队列名称
                Queue tmp = inQueue;
                inQueue = outQueue;
                outQueue = tmp;
            }
        }
        return (T) inQueue.poll();
    }

    // ------------ 求旋转数组的最小值，原数组是递增数组，被旋转若干个元素 ---------

    /**
     * 求旋转数组的最小值.核心是采用二分法处理
     * 如果中间元素等于起始和末端元素，只能顺序遍历查找，无法使用二分查找；
     * 如果中间元素大于等于起始元素，最小值后半段；如果中间元素小于等于末端元素，最小值在前半段；
     * 时间复杂度是O(lgN)，特殊情况是O(N)
     * <p>
     * 关注情况：
     * 1、旋转数组，旋转了0个元素；或者旋转了多个元素；
     * 2、旋转数组中存在重复元素，比如（1，0，1，1，1）或者（1，1，1，0，1），都是（0，1，1，1，1）的旋转
     * 3、数组元素只存在1个元素，多个元素和异常空元素
     * <p>
     * 处理方法：
     * 循环处理过程中，需要比较元素值是否递增，如果是，则结束比较；
     * <p>
     * 功能测试：（1、输入数组是升序数组的旋转，2、数组中存在重复数字和无重复数字）
     * 边界测试：（1、输入数组还好是一个升序数组，2、包含一个元素的数组）
     * 特殊测试：null
     *
     * @param arr
     * @return
     */
    public int minInRotationArray(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new NullPointerException("Param.arr is null!");
        }

        if (arr.length == 1) {
            //存在一个元素，直接返回
            return arr[0];
        }
        int startIndex = 0, endIndex = arr.length - 1;
        int midIndex = startIndex;
        while (arr[startIndex] >= arr[endIndex]) {
            // 旋转过的数组才循环
            if ((startIndex + 1) == endIndex) {
                // 两个元素；
                midIndex = endIndex;
                break;
            }
            midIndex = (startIndex + endIndex) >> 1;
            if (arr[midIndex] == arr[startIndex] && arr[midIndex] == arr[endIndex]) {
                // 如果下标startIndex、midIndex和endIndex指向的三个数字相同
                // 则只能选择顺序查找
                return minOrderSearch(arr, startIndex, endIndex);
            } else if (arr[midIndex] >= arr[startIndex]) {
                // 最小值在后半段
                startIndex = midIndex;
            } else if (arr[midIndex] <= arr[endIndex]) {
                // 最小值在前半段
                endIndex = midIndex;
            }

        }
        return arr[midIndex];
    }

    // --------- 斐波那契数列的求解方法----------------

    /**
     * 计算斐波那契数列，f(n) = f(n-1)+f(n-2) n>=2;  f(0) = 0; f(1)=1
     * <p>
     * 最简答方法是使用递归，但是存在重复计算过程。
     * 实用解法是迭代计算
     *
     * @param n
     * @return
     */
    public long fibonacci(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Param.n not more than 0");
        } else if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            // 使用两个变量存放
            long one = 0, two = 1;
            while (n >= 2) {
                two = two + one;
                one = two - one;
                n--;
            }
            return two;
        }
    }

    /**
     * 跳台阶，青蛙一次跳一个或者两个台阶，n个台阶，请问存在多少种跳法？
     * f(n) = f(n-1) + f(n-2) n>=2; f(1) = 1,f(2)= 2,假设f(0)=1
     * 达到满足  斐波那契数列的特性
     */
    public long jumpStep(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Param.n less than or equal with 0");
        } else if (n == 1) {
            return 1;
        } else {
            long one = 1, two = 1;
            while (n >= 2) {
                two = two + one;
                one = two - one;
                n--;
            }
            return two;

        }
    }

    /**
     * 顺序查找
     *
     * @param arr
     * @param startIndex
     * @param endIndex
     * @return
     */
    private int minOrderSearch(int[] arr, int startIndex, int endIndex) {
        int result = arr[startIndex];
        for (int i = startIndex + 1; i <= endIndex; i++) {
            if (result > arr[i]) {
                result = arr[i];
            }
        }
        return result;
    }


    // ------- 位运算 ----------
    // 位与（都为1才能等于1）、或、异或运算，左移、右移和无符号右移


    /**
     * 计算n中，二进制表示数 1的个数
     * 使用位与运算，初始化flag=1，左移一次，然后计算位与是否等于1
     * <p>
     * 测试用例：
     * 1、正数（1，0x7fffffff）和
     * 2、负数(0x80000000、0xffffffff)
     * 3、0
     *
     * @param n
     * @return
     */
    public int numberCount(int n) {
        int count = 0;
        int flag = 1;
        while (n != 0) {
            if ((n & flag) == 1) {
                count++;
            }
            flag <<= 1;
        }
        return count;
    }

    /**
     * 计算n中，二进制表示数 1的个数
     * <p>
     * 思路：二进制任意数，每次减一，会把数最左边的1，转换成0；
     * 然后和本身做位与后，结果是，0位点左边的数都不变，右边的数都变成0；
     * <p>
     * 比如：
     * 数减一：011110000 - 1 = 011101111,
     * 与本身位与结果：，011110000 & 011101111 = 011100000
     * <p>
     * 测试用例：
     * 1、正数（1，0x7fffffff）
     * 2、负数(0x80000000、0xffffffff)
     * 3、0
     *
     * @param n
     * @return
     */
    public int numberCount1(int n) {
        int count = 0;
        while (n != 0) {
            count++;
            n = n & (n - 1);
        }
        return count;
    }

    /**
     * 判断数是否是2的整数次方。2的整数次方的二进制数只存在一个1
     * <p>
     * n & (n-1) == 0
     *
     * @param n
     * @return
     */
    public boolean checkNumber(int n) {
        return ((n & (n - 1)) == 0) && (n != 0);
    }

    /**
     * 计算改变m的二进制表示中的多少位才能等于n
     * <p>
     * 思路：
     * 1、 统计m 和n的不同位数（异或）结果
     * 2、统计异或结果的1中的个数
     *
     * @param m
     * @param n
     * @return
     */
    public int countChangeNum(int m, int n) {
        // 异或
        int result = m ^ n;
        // 统计异或结果的1的个数
        return numberCount1(result);

    }

    //----------  计算一个数的整数次方 ---------

    /**
     * 计算一个数的整数次方,浮点数，不考虑大数问题
     *
     * @param base     输入值可能是0.0，正数和负数
     * @param exponent 输入数可能是0，正数和负数
     * @return
     */
    public double powder(double base, int exponent) {
        if (equalsDouble(base, 0.0) && exponent < 0) {
            throw new IllegalArgumentException("Param.base = " + base + ", param.exponent = " + exponent + ", illegal!");
        }
        double result;
//        if (exponent == 0) {
//            return 1;
//        } else {
        int unsignedExp = ((exponent ^ exponent >> 31) - (exponent >> 31));

//            result = powderByUnsignedExp(base, unsignedExp);
        result = powderByUnsignedExpFast(base, unsignedExp);

        if (exponent < 0) {
            return 1 / result;
        }
        return result;
//        }
    }

    /**
     * 采用迭代计算  整数次方。
     * <p>
     * 时间复杂度是O(n),不是性能最优。。如果是32次，需要迭代32次，如果是2^31次方呢，
     *
     * @param base
     * @param unsignedExp
     * @return
     */
    private double powderByUnsignedExp(double base, int unsignedExp) {
        if (unsignedExp == 0) {
            return 1;
        } else if (unsignedExp == 1) {
            return base;
        } else {
            double result = 1;
            for (int i = 0; i < unsignedExp; i++) {
                result *= base;
            }
            return result;
        }
    }

    /**
     * 采用二分法，计算整数次方法，降低计算次数。
     * 时间复杂度是O(lg)
     * <p>
     * 比如：unsignedExp = 32，可以通过两个的 unsignedExp=16计算得到
     * 这里需要考虑奇 偶数的两个情况
     *
     * @param base
     * @param unsignedExp
     * @return
     */
    private double powderByUnsignedExpFast(double base, int unsignedExp) {
        if (unsignedExp == 0) {
            return 1;
        } else if (unsignedExp == 1) {
            return base;
        } else {
            double result = powderByUnsignedExpFast(base, unsignedExp >> 1);
            result *= result;
            if ((unsignedExp & 1) == 1) {
                // 奇数
                result *= base;
            }
            return result;

        }
    }

    private boolean equalsDouble(double var1, double var2) {
        if ((var1 - var2 > -0.0000001) && (var1 - var2 < 0.0000001)) {
            return true;
        } else {
            return false;
        }

    }

    //---------- 打印1到最大的n位数 ----------

    /**
     * 使用字符串模拟数自增，打印数字输出
     * <p>
     * 比如： 出现大数情况，必须选用字符串
     *
     * @param n
     */
    public void printToMaxNDigits(int n) {
        if (n <= 0) {
//            throw new IllegalArgumentException("Param.n is illegal!");
            return;
        }

        char[] number = new char[n];
        initNumber(number, '0');
        while (incrementSucc(number)) {
            printNumber(number);
        }
    }

    /**
     * @param number
     * @param c
     */
    private void initNumber(char[] number, char c) {
        for (int i = 0; i < number.length; i++) {
            number[i] = c;
        }
    }

    /**
     * 自动加一，成功后返回true；越界返回false
     *
     * @param number
     * @return
     */
    private boolean incrementSucc(char[] number) {
        int nTakeOver = 0;
        for (int i = number.length - 1; i >= 0; i--) {
            int nSum = number[i] - '0' + nTakeOver;
            if (i == number.length - 1) {
                nSum++;
            }
            if (nSum >= 10) {
                // 向前进位
                if (i == 0) {
                    return false;
                } else {
                    nSum -= 10;
                    nTakeOver = 1;
                    number[i] = (char) ('0' + nSum);
                }
            } else {
                number[i] = (char) (nSum + '0');
                break;
            }
        }
        return true;
    }

    /**
     * 打印
     *
     * @param number
     */
    private void printNumber(char[] number) {
        int startIndex = 0, i = 0;
        while (number[i] == '0') {
            startIndex++;
            i++;
        }
        for (int j = startIndex; j < number.length; j++) {
            System.out.print(number[j]);
        }
        System.out.println();

    }

    //---------- 在O(1)时间下删除单链表节点 --------------

    /**
     * 单链表的删除操作，需要把被删除节点的next信息，更新到前一个节点中。
     * 这样需要顺序遍历查找被删除节点的前一个节点，时间复杂度是O(n)。
     * <p>
     * 还有另一种思路就是把被删除节点p的value和next, 更新为p->next->value和p->next->next
     * <p>
     * 关注情况：
     * 1、被删除节点是非尾节点，直接更新；
     * 2、链表只存在一个节点；（删除节点和头节点相同）
     * 3、链表多于两个节点，且被删除节点是尾节点，需要顺序遍历查找前一个节点；
     * 这种情况存在概率是1/n。
     * <p>
     * 所有整体的时间复杂度是O(1)
     * <p>
     * 这种思路其实会有问题，那就是被删除节点的内存地址其实未删除，删除的后一节点的信息。
     *
     * @param head
     * @param delNode
     */
    public void deleteNode(ListNode head, ListNode delNode) {
        if (head == null || delNode == null) {
            return;
        }

        if (delNode.next != null) {
            // 被删除节点是非尾节点
            // 把被删除节点的后一个节点信息更新到删除节点中
            delNode.value = delNode.next.value;
            delNode.next = delNode.next.next;
            delNode.next.next = null;
        } else if (head == delNode && delNode.next == null) {
            head = delNode = null;
        } else {
            ListNode preNode = head;
            while (preNode.next != delNode) {
                preNode = preNode.next;
            }
            preNode.next = null;
            delNode = null;
        }
    }

    /**
     * 采用两个指针表示节点下标，考察  健壮性
     * 节点A先移动k-1次，然后节点B一起移动，直到节点A->next == null.
     * <p>
     * 情况：
     * 1、单链表的节点长度小于k，返回null
     * 2、单链表的节点长度大于等于k
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode findKthToTail(ListNode head, int k) {
        if (k <= 0) {
            throw new IllegalArgumentException("Param.k less than or equal 0");
        }
        if (head == null) {
            return null;
        }
        //记录节点下标的
        ListNode headNode = head;
        ListNode tailNode = head;
        // 先移动k-1次
        while (k > 1 && headNode.next != null) {
            headNode = headNode.next;
            k--;
        }

        if (k > 1) {
            //单链表的长度小于k
            return null;
        } else {
            while (headNode.next != null) {
                headNode = headNode.next;
                tailNode = tailNode.next;
            }
            return tailNode;
        }
    }

    /**
     * 获得单链表的中间节点，奇数个节点返回中间节点，偶数个节点返回两个中的任意一节点
     * ，考察  健壮性
     * <p>
     * 时间复杂度是O(n)
     *
     * @param head
     * @return
     */
    public ListNode searchMidNode(ListNode head) {
        if (head == null) {
            return null;
        } else if (head.next == null) {
            // 单节点链表
            return head;
        } else {
            ListNode oneStepNode = head;
            ListNode twoStepNode = head;
            while (twoStepNode.next != null && twoStepNode.next.next != null) {
                // 采用跳一步和跳两步的方式，
                oneStepNode = oneStepNode.next;
                twoStepNode = twoStepNode.next.next;
            }
            return oneStepNode;
        }
    }

    /**
     * 检查单链表中是否存在环。
     * <p>
     * 思路：采用两个下标表示，一个下标一次走一步，另一个下标一次走两步。当两个下标相遇时，表明存在环
     *
     * @param head
     * @return
     */
    public boolean checkListExistedirculeC(ListNode head) {
        if (head == null) {
            return false;
        } else if (head.next == null) {
            // 单节点链表
            return false;
        } else {
            ListNode oneStepNode = head;
            ListNode twoStepNode = head;
            while (twoStepNode.next != null && twoStepNode.next.next != null) {
                // 采用跳一步和跳两步的方式，
                oneStepNode = oneStepNode.next;
                twoStepNode = twoStepNode.next.next;
                if (oneStepNode == twoStepNode) {
                    // 相等则存在环
                    return true;
                }
            }
            return false;
        }
    }


    //-------  判读序列是否是栈的出栈序列 --------

    public boolean isPopOrder(int[] inputArr, int[] outputArr, int length) {
        if ((inputArr == null && outputArr == null)
                || (inputArr.length == 0 && outputArr.length == 0)) {
            return true;
        } else if ((inputArr == null || outputArr == null)
                || (inputArr.length == 0 || outputArr.length == 0)) {
            return false;
        }
        if (length < 0) {
            throw new IllegalArgumentException("Param.length less than 0");
        }
        if (inputArr.length < length || outputArr.length < length) {
            return false;
        }

        //判断
        Stack dataStack = new Stack();
        int inIndex = 0, outIndex = 0;
        while (inIndex < length || !dataStack.isEmpty()) {
//            if (dataStack.isEmpty()) {
//                dataStack.push(inputArr[inIndex++]);
//            }
            // 优先出栈
            while (!dataStack.isEmpty() && dataStack.peek().equals(outputArr[outIndex])) {
                // 相同则出栈，
                dataStack.pop();
                outIndex++;
            }
            if (inIndex < length) {
                dataStack.push(inputArr[inIndex++]);
            } else {
                break;
            }
        }
        if (!dataStack.isEmpty()) {
            return false;
        }
        return true;
    }


    /**
     * 判断序列是否是二叉搜索树的后序遍历，左右根，
     * 这样子序列 小部分，大部分，根（中间）。
     * 以根的值为界限，从左到右遍历，找到一个大于根的值，这是左右子树的分界线
     * 然后，先遍历右子树序列中是否出现小于根的值，如果存在，直接返回false；
     * 继续，如果存在左子树，递归校验左子树；如果存在右子树，递归遍历右子树；
     * 最后返回左右子树的校验结果
     *
     * @param sequence
     * @param startIndex
     * @param endIndex
     * @return
     */
    public boolean verifySequenceOfBST(int[] sequence, int startIndex, int endIndex) {
        if (sequence == null || sequence.length == 0) {
            throw new IllegalArgumentException("Param.sequence is null!");
        }
        if (startIndex < 0 || startIndex >= sequence.length) {
            throw new IndexOutOfBoundsException("Param.startIndex is illegal");
        }
        if (endIndex < startIndex || endIndex >= sequence.length) {
            throw new IndexOutOfBoundsException("Param.endIndex is illegal");
        }

        if (startIndex == endIndex) {
            // 只有一个节点值
            return true;
        } else {
            // 大于等于两个节点值
            int root = sequence[endIndex];
            // 查找第一大于根的值
            int i = startIndex;
            for (; i < endIndex; i++) {
                if (sequence[i] > root) {
                    break;
                }
            }
            int j = i;
            // 默认剩下部分为右子树，遍历，依据是右子树的节点值大于根节点值
            // 如果出现小于根的值，则不是二叉搜索树
            for (; j < endIndex; j++) {
                if (sequence[j] < root) {
                    return false;
                }
            }
            boolean left = true, right = true;
            // 判断左子树的序列是否是二叉搜索树
            if (i > startIndex) {
                left = verifySequenceOfBST(sequence, startIndex, i - 1);
            }
            // 判断右子树的序列是否是二叉搜索树
            if (i < endIndex) {
                right = verifySequenceOfBST(sequence, i, endIndex - 1);
            }
            return left && right;
        }
    }


    /**
     * --------------  包含min函数的栈，或者是 在O(1)获得栈中的最小值 ------
     */
    class StackWithMin<T> {
        /**
         * 采用两个栈，一个记录实际的栈值，
         * 一个记录当前的最小值辅助栈,只有输入把栈顶元素更小的值，才把更小值入辅助栈；反之把数据栈顶值入辅助栈
         */
        private Stack dataStack = new Stack();
        private Stack minStack = new Stack();

        private Comparator comparator = null;

        public StackWithMin(Comparator comparator) {
            this.comparator = comparator;
        }

        /**
         * 入栈
         *
         * @param val
         */
        public synchronized void push(T val) {
            if (val == null) {
                return;
            }
            dataStack.push(val);
            if (minStack.isEmpty() || comparator.compare(val, minStack.peek()) < 0) {
                // 只有当辅助栈为空，或者输入值小于辅助栈的栈顶值，才入辅助栈
                minStack.push(val);
            } else {
                // 记录数据栈的栈顶值
                minStack.push(dataStack.peek());
            }
        }

        /**
         * 出栈
         */
        public synchronized T pop() {
            if (dataStack.isEmpty()) {
                return null;
            }
            minStack.pop();
            return (T) dataStack.pop();
        }

        public T getMinValueInStack() {
            if (dataStack.isEmpty()) {
                return null;
            }
            return (T) minStack.peek();
        }

        public boolean isEmpty() {
            return dataStack.size() == 0;
        }

        public int size(){
            return dataStack.size();
        }
    }

    /**
     * 双链接节点
     */
    class ListNode {
        private int value;
        private ListNode pre;
        private ListNode next;

    }


    /**
     * 树节点
     */
    class TreeNode {
        private int value;
        private TreeNode left;
        private TreeNode right;

    }


}
