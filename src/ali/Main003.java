package ali;

import java.util.Scanner;

/**
 * Created by chenzhitao on 2018/9/12
 *
 * 求大数的乘积
 */
public class Main003 {
    
    private static final  char ADD_FLAG = '+';
    private static final  char SUBSTRACE_FLAG = '-';
    private static final  char ZERO = '0';



    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String first = in.nextLine();
        String second = in.nextLine();

        System.out.println(bigNumberSimpleMulti(first,second));
    }

    public static String bigNumberSimpleMulti(final String f, final String s) {
//        System.out.print("乘法：\n" + f + "*" + s + "=");
        String numFirst = new String(f.toCharArray());
        String numSec =  new String(s.toCharArray());
        // 获取首字符，判断是否是符号位
        char signA = numFirst.charAt(0);
        char signB = numSec.charAt(0);
        char sign = ADD_FLAG;
        if (signA == ADD_FLAG || signA == SUBSTRACE_FLAG) {
            sign = signA;
            numFirst = numFirst.substring(1);
        }
        if (signB == ADD_FLAG || signB == SUBSTRACE_FLAG) {
            if (sign == signB) {
                sign = ADD_FLAG;
            } else {
                sign = SUBSTRACE_FLAG;
            }
            numSec = numSec.substring(1);
        }
        // 将大数翻转并转换成字符数组
        char[] firstArr = new StringBuilder(numFirst).reverse().toString().toCharArray();
        char[] secondArr = new StringBuffer(numSec).reverse().toString().toCharArray();
        // 计算最终的最大长度
        int[] result = new int[firstArr.length +secondArr.length];
        // 计算结果集合
        for (int i = 0; i < firstArr.length; i++) {
            for (int j = 0; j < secondArr.length; j++) {
                result[i + j] += (int) (firstArr[i] - ZERO) * (int) (secondArr[j] - ZERO);
            }
        }
        // 处理结果集合，如果是大于10的就向前一位进位，本身进行除10取余
        for (int i = 0; i < result.length; i++) {
            if (result[i] > 10) {
                result[i + 1] += result[i] / 10;
                result[i] %= 10;
            }
        }
        StringBuffer sb = new StringBuffer();
        // 该字段用于标识是否有前置0，如果是0就不需要打印或者存储下来
        boolean flag = true;
        for (int i = result.length - 1; i >= 0; i--) {
            if (result[i] == 0 && flag) {
                continue;
            } else {
                flag = false;
            }
            sb.append(result[i]);
        }
        if (!"".equals(sb.toString())) {
            if (sign == SUBSTRACE_FLAG) {
                sb.insert(0, sign);
            }
        } else {
            sb.append(0);
        }
        // 返回最终结果
        return sb.toString();
    }

    /**
     * 字符串翻转
     * @param string
     * @return
     */
    private static char[] reverse(final String string) {
        String value  = string;
        if (value == null){
            return new char[0];
        }
        
        char[] valueArr = string.toCharArray();
        int i = 0,j=valueArr.length-1;
        while (i < j){
            char tmp = valueArr[i];
            valueArr[i] = valueArr[j];
            valueArr[j] = tmp;
            ++i;--j;
        }
        return valueArr;

    }

}
