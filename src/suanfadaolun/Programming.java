package suanfadaolun;

/**
 * @Description :动态规划算法
 * @Author : chenzhitao
 * @Date : Created in 17:38 2018/12/22
 */
public class Programming {

    public static void main(String[] args) {
        Programming pro = new Programming();

        char[] xArr = {' ', 'A', 'B', 'C', 'B', 'D', 'A', 'B'};
        char[] yArr = {' ', 'B', 'D', 'C', 'A', 'B', 'A'};

        int[][] resultMatrix = new int[xArr.length][yArr.length];
        char[][] recordMatrix = new char[xArr.length][yArr.length];

//        pro.LCSLength(xArr, yArr, resultMatrix, recordMatrix);
//        pro.printCommonChar(recordMatrix, xArr, xArr.length - 1, yArr.length - 1);
//        System.out.println();

        int[][] weight = {{0, 1, 0, 5, 0, 0,}, {0, 1, 0, 0, 0, 0}
                , {0, 0, 0, 1, 0, 0}, {0, 0, 0, 0, 1, 2}, {0, 0, 0, 0, 0, 1}, {0, 0, 0, 0, 0, 0}};

        int[] path = {-1, -1, -1, -1, -1, -1};

        pro.findLongestPathInGragh(weight, 0, 5, path);

    }


    /**
     * 计算最长公共子序列
     *
     * @param xArr
     * @param yArr
     * @param resultMatrix
     * @param recordMatrix
     */
    public void LCSLength(char[] xArr, char[] yArr, int[][] resultMatrix, char[][] recordMatrix) {
        if (xArr == null || xArr.length == 0) {
            return;
        }
        if (yArr == null || yArr.length == 0) {
            return;
        }

        int xLen = xArr.length;
        int yLen = yArr.length;
        for (int i = 0; i < xLen; i++) {
            resultMatrix[i][0] = 0;
        }
        for (int i = 0; i < yLen; i++) {
            resultMatrix[0][i] = 0;
        }

        for (int i = 1; i < xLen; i++) {
            for (int j = 1; j < yLen; j++) {
                if (xArr[i] == yArr[j]) {
                    resultMatrix[i][j] = resultMatrix[i - 1][j - 1] + 1;
                    recordMatrix[i][j] = '\\';
                } else if (resultMatrix[i - 1][j] >= resultMatrix[i][j - 1]) {
                    resultMatrix[i][j] = resultMatrix[i - 1][j];
                    recordMatrix[i][j] = '|';
                } else {
                    resultMatrix[i][j] = resultMatrix[i][j - 1];
                    recordMatrix[i][j] = '-';
                }
            }
        }

    }

    /**
     * 打印最长的公共子序列
     *
     * @param recordMatrix
     * @param xArr
     * @param xIndex
     * @param yIndex
     */
    public void printCommonChar(char[][] recordMatrix, char[] xArr, int xIndex, int yIndex) {
        if (xArr == null || xArr.length == 0) {
            return;
        }

        if (recordMatrix == null || recordMatrix.length == 0 || recordMatrix[0].length == 0) {
            return;
        }
        if (xIndex < 0 || yIndex < 0) {
            return;
        }
        if (recordMatrix[xIndex][yIndex] == '\\') {
            printCommonChar(recordMatrix, xArr, xIndex - 1, yIndex - 1);
            System.out.print(xArr[xIndex]);
        } else if (recordMatrix[xIndex][yIndex] == '|') {
            printCommonChar(recordMatrix, xArr, xIndex - 1, yIndex);
        } else {
            printCommonChar(recordMatrix, xArr, xIndex, yIndex - 1);
        }
    }

    public void printCommonChar(int[][] resultMatrix, char[] xArr, int xIndex, int yIndex) {
        if (xArr == null || xArr.length == 0) {
            return;
        }
        if (resultMatrix == null | resultMatrix.length == 0 || resultMatrix[0].length == 0) {
            return;
        }

        if (xIndex < 0 || yIndex < 0) {
            return;
        }

    }

    /**
     * 查找有向无权、无环图的最长简单路径
     *
     * @param weight
     * @param k
     * @param t
     * @param path
     * @return
     */
    public int findLongestPathInGragh(int[][] weight, int k, int t, int[] path) {

        checkParam(weight, k, t, path);
        return dynamicFindPathInGraph(weight, k, t, path);
    }

    /**
     * 检查参数
     *
     * @param weight
     * @param k
     * @param t
     * @param path
     */
    private void checkParam(int[][] weight, int k, int t, int[] path) {
        if (weight == null || weight.length == 0 || weight[0] == null || weight[0].length == 0) {
            throw new NullPointerException("Param.weight is null!");
        }
        if (k < 0 || k > weight.length - 1) {
            throw new IndexOutOfBoundsException("Param.k is illegal");
        }
        if (t < 0 || t > weight.length - 1) {
            throw new IndexOutOfBoundsException("Param.t is illegal");
        }
        if (path == null || path.length != weight.length) {
            throw new IllegalArgumentException("Param.path is illegal, path.length not equal than weight.length!");
        }
    }

    /**
     * 动态规划查找最长路径
     *
     * @param weight
     * @param k
     * @param t
     * @param nextPath
     * @return
     */
    private int dynamicFindPathInGraph(int[][] weight, int k, int t, int[] nextPath) {
        if (k == t) {
            nextPath[k] = t;
            return 0;
        }
        int val = weight[k][t];
        if (weight[k][t] > 0) {
            nextPath[k] = t;
        }
        for (int i = 0; i < weight.length; i++) {
            if (weight[k][i] <= 0) {
                continue;
            }
            int tmpMax = maxVal(val, weight[k][i] + dynamicFindPathInGraph(weight, i, t, nextPath));
            if (tmpMax > val) {
                // 获得当前
                val = tmpMax;
                nextPath[k] = i;
            }
        }
        return val;

    }

    private int maxVal(int var1, int var2) {
        return var1 > var2 ? var1 : var2;
    }


}
