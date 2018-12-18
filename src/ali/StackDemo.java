package ali;

import java.util.*;

/**
 * Created by chenzhitao on 2018/9/13
 */
public class StackDemo {

    private static final int ZERO = '0';
    private static final int NINE = '9';


    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);

        String str = sc.nextLine();
        List<String> infixExpList = toInfixExpression(str);
        for (String s : infixExpList) {
            System.out.print(s + " ");
        }
        System.out.println();
        List<String> suffixExpList = getReversePolish(infixExpList);
        System.out.println(suffixExpList);
        for (String s : suffixExpList) {
            System.out.print(s +" ");
        }

        System.out.println("calculate:" +calculateBySuffixExp(suffixExpList));

    }

    /**
     * 根据逆波兰表达式计算结果，使用异常机制判断数值位
     * @param suffixExp
     * @return
     */
    public static int calculateBySuffixExp(List<String> suffixExp){
        int result = 0;
        if (suffixExp == null || suffixExp.size() == 0){
            return result;
        }
        Stack<Integer> numStack = new Stack<>();
        for (String  valueStr: suffixExp) {
            try{
                int num  = Integer.parseInt(valueStr);
                numStack.push(num);
            }catch (NumberFormatException e){
                //  遇到操作符，出栈两个元素
                int a = numStack.pop();
                int b = numStack.pop();
                result = actionCalculate(a,b,valueStr);
                numStack.push(result);
            }
        }
        return result;
    }

    /**
     * 通过逆波兰表达式计算结果，使用
     *
     * 1)正则判断数值
     *
     * @param ls
     * @return
     */
    public static int calculateBySuffixExp1(List<String> ls) {
        Stack<String> s=new Stack<String>();
        for (String str : ls) {
            if (str.matches("\\d+")) {
                s.push(str);
            } else {
                int b = Integer.parseInt(s.pop());
                int a = Integer.parseInt(s.pop());
                int result=0;
                if (str.equals("+")) {
                    result = a + b;
                } else if (str.equals("-")) {
                    result = a - b;
                } else if (str.equals("*")) {
                    result = a * b;
                } else if (str.equals("\\")) {
                    result = a / b;
                }
                s.push("" + result);
            }
        }
        System.out.println(s.peek());
        return Integer.parseInt(s.pop());
    }


    private static int actionCalculate(int a, int b, String operationSign) {
        int result = 0;
        if (operationSign == null ){
            return result;
        }
        if (SignEnum.ADD.getOperationSign().equals(operationSign)) {
            result = a + b;
        } else if (SignEnum.SUBTRACTION.getOperationSign().equals(operationSign)) {
            result = a - b;
        } else if (SignEnum.MULTIPLICATION.getOperationSign().equals(operationSign)) {
            result = a * b;
        } else if (SignEnum.DIVISION.getOperationSign().equals(operationSign)) {
            result = a / b;
        }
        return result;
    }

    /**
     * 把字符串转换成中序表达式(数组)
     *
     * @param s
     * @return
     */
    public static List<String> toInfixExpression(String s) {
        //存储中序表达式
        List<String> ls = new ArrayList<>();
        if (s == null || "".equals(s.trim())){
            return ls;
        }
        int i = 0;
        String str;
        char c;
        do {
            if ((c = s.charAt(i)) < ZERO || (c = s.charAt(i)) > NINE) {
                ls.add("" + c);
                ++i;
            } else {
                str = "";
                while (i < s.length() && (c = s.charAt(i)) >= ZERO
                        && (c = s.charAt(i)) <= NINE) {
                    str += c;
                    ++i;
                }
                ls.add(str);
            }

        } while (i < s.length());
        return ls;
    }


    /**
     * 把中缀表达式转成后缀表达式(逆波兰表达式)
     * <p>
     * 1)遍历表达式，如果是数字输出(采用异常捕获)，如果是操作符则入栈
     * 2)操作符入栈规则函数：isStackPushByCompareSign()，4种情况
     * a、第一个操作符一定入栈；
     * b、如果左括号入栈；
     * c、如果栈顶元素是左括号，入栈；
     * d、如果栈顶元素的优先级低于当前操作符，入栈，优先级： ( > *,/ > +,- > )，左括号优先级最高，右括号优先级最低
     * 3)不满足入栈条件，则出栈，
     * a、如果当前操作符是右括号，循环出栈，直到栈顶左括号
     * b、栈顶出栈，再入栈
     * <p>
     * 4)输出最后一个栈元素
     *
     * @param srcList
     * @return
     */
    public static List<String> getReversePolish(final List<String> srcList) {

        List<String> reversePolishList = new LinkedList<>();
        Stack<String> operationStack = new Stack<>();

        for (String ele : srcList) {
            try {
                //如果是数字，则输出
                int num = Integer.parseInt(ele);
                reversePolishList.add(num + "");
            } catch (NumberFormatException e) {
                //操作符，则入栈
                //比较，高优先级入栈, ( > *,/ > +,- > )
                if (isStackPushByCompareSign(operationStack, ele)) {
                    operationStack.push(ele);
                } else {
                    //出栈
                    if (ele.equals(SignEnum.CLOSE_PAREN.getOperationSign())) {
                        // 如果是右括号，则出栈，直到遇到左括号为止
                        while (!operationStack.peek().equals(SignEnum.OPEN_PAREN.getOperationSign())) {
                            // 出栈
                            reversePolishList.add(operationStack.pop());
                        }
                        operationStack.pop();
                    } else {
                        // 先出栈，再入栈
                        reversePolishList.add(operationStack.pop());
                        operationStack.push(ele);
                    }
                }
            }
        }
        reversePolishList.add(operationStack.pop());
        return reversePolishList;
    }


    /**
     * 判断是否可以入栈
     *
     * @param operationStack
     * @param curSign
     * @return
     */
    private static boolean isStackPushByCompareSign(Stack<String> operationStack, String curSign) {

        if (curSign.equals(SignEnum.OPEN_PAREN.getOperationSign())) {
            //左括号入栈
            return true;
        } else if (curSign.equals(SignEnum.CLOSE_PAREN.getOperationSign())) {
            //右括号不入栈
            return false;
        }
        if (operationStack.isEmpty()) {
            //第一个操作符直接入栈
            return true;
        }

        String preSign = operationStack.peek();
        if (preSign.equals(SignEnum.OPEN_PAREN.getOperationSign())) {
            //栈顶元素是左括号, 入栈
            return true;
        }
        // 比较优先级
        if (SignEnum.getPriorityBySign(curSign) > SignEnum.getPriorityBySign(preSign)) {
            //高优先级入栈, 只有大于才入栈
            return true;
        } else {
            // 不入栈
            return false;
        }
    }

    static enum SignEnum {

        ADD("+", 1),
        SUBTRACTION("-", 1),
        MULTIPLICATION("*", 2),
        DIVISION("/", 2),

        // 左括号优先级最高
        OPEN_BRACE("{", 5),
        // 右括号优先级最低
        CLOSE_BRACE("}", 0),

        OPEN_BRACKET("[", 5),
        CLOSE_BRACKET("]", 0),

        OPEN_PAREN("(", 5),
        CLOSE_PAREN(")", 0);

        private String operationSign;
        private int priority;

        SignEnum() {
        }

        SignEnum(String operationSign, int priority) {
            this.operationSign = operationSign;
            this.priority = priority;
        }

        /**
         * 根据操作符，获得优先级
         *
         * @param operationSign
         * @return
         */
        public static int getPriorityBySign(String operationSign) {
            int priority = -1;
            if (operationSign != null) {
                if (operationSign.equals(ADD.getOperationSign())) {
                    priority = ADD.getPriority();
                } else if (operationSign.equals(SUBTRACTION.getOperationSign())) {
                    priority = SUBTRACTION.getPriority();
                } else if (operationSign.equals(MULTIPLICATION.getOperationSign())) {
                    priority = MULTIPLICATION.getPriority();
                } else if (operationSign.equals(DIVISION.getOperationSign())) {
                    priority = DIVISION.getPriority();
                } else if (operationSign.equals(OPEN_PAREN.getOperationSign())) {
                    priority = OPEN_PAREN.getPriority();
                } else if (operationSign.equals(OPEN_BRACKET.getOperationSign())) {
                    priority = OPEN_BRACKET.getPriority();
                } else if (operationSign.equals(OPEN_BRACE.getOperationSign())) {
                    priority = OPEN_BRACE.getPriority();
                } else if (operationSign.equals(CLOSE_PAREN.getOperationSign())) {
                    priority = CLOSE_PAREN.getPriority();
                } else if (operationSign.equals(CLOSE_BRACKET.getOperationSign())) {
                    priority = CLOSE_BRACKET.getPriority();
                } else if (operationSign.equals(CLOSE_BRACE.getOperationSign())) {
                    priority = CLOSE_BRACE.getPriority();
                }
            }
            return priority;
        }

        public String getOperationSign() {
            return operationSign;
        }

        public void setOperationSign(String operationSign) {
            this.operationSign = operationSign;
        }

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }
    }
}
