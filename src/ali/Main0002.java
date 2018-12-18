package ali;

import java.util.HashSet;
import java.util.Scanner;

/**
 * Created by chenzhitao on 2018/9/16
 */
public class Main0002 {


        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            String[] input = sc.nextLine().split("\\ ");
            int n = Integer.parseInt(input[0]);
            int m = Integer.parseInt(input[1]);

            int[][] graph = new int[n][n];
            for (int i = 0; i < m; i++) {
                String[] in = sc.nextLine().split("\\ ");
                int r = Integer.parseInt(in[0]) - 1;
                int c = Integer.parseInt(in[1]) - 1;
                graph[r][c] = 1;
            }


            
            HashSet<Integer>[] resultSet = new HashSet[n];
            for (int i = 0; i < n; i++) {
                resultSet[i] = new HashSet<>();
                dfs(i, graph, resultSet[i]);
                resultSet[i].remove(i);
            }

            int[] in = new int[n];
            int[] out = new int[n];
            for (int i = 0; i < n; i++) {
                out[i] = resultSet[i].size();
                for (Integer p : resultSet[i]) {
                    in[p]++;
                }
            }

            int res = 0;
            for (int i = 0; i < n; i++) {
                if(in[i] > out[i]) {
                    res++;
                }
            }

            System.out.println(res);




        }

    /**
     * 深度优先遍历
     * @param start
     * @param graph
     * @param set
     */
        public static void dfs(int start, int[][] graph, HashSet<Integer> set) {

            set.add(start);
            for (int i = 0; i < graph.length; i++) {
                if (graph[start][i] == 1 && !set.contains(i)) {
                    set.add(i);
                    dfs(i, graph, set);
                }
            }
            return;
        }
}
