package pinduoduo;

import java.util.*;

/**
 * Created by chenzhitao on 2018/9/21
 */
public class Main3 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        //
        int n = in.nextInt();
        int m = in.nextInt();
        int[] costArr = new int[n];
        for (int i = 0; i < n; i++) {
            costArr[i] = in.nextInt();
        }
        //快排
        quickSort(costArr,0,costArr.length-1);

        Set<Ticket> ticketSet = new TreeSet<>(new Ticket.CustomComparator());
        int j = 0;
        while (in.hasNext() && j < m){
            String input = in.nextLine();
            if ("".equals(input.trim())){
                continue;
            }
            String[] inputArr = input.split("\\ ");
            if (inputArr != null || inputArr.length == 2) {
                Ticket ticket = new Ticket(Integer.parseInt(inputArr[0]), Integer.parseInt(inputArr[1]));
                ticketSet.add(ticket);
                j++;
            }
        }

        int k = costArr.length-1;
        int maxCost = 0;
        for (Ticket ticket : ticketSet) {
            if (k == -1){
                break;
            }
            if (ticket.xi < costArr[k]){
               maxCost += (costArr[k] - ticket.yi);
               k --;
            }
        }
        if (k >= 0) {
            for (int i = k; i >= 0; i--) {
                maxCost += costArr[k];
            }
        }

        System.out.println(maxCost);

    }


    public static void quickSort(int[] arr,int low, int high) {
        if(low<high) {
            int partition = partition(arr,low,high);
            quickSort(arr,low, partition-1);
            quickSort(arr,partition+1, high);
        }

    }
    public static int partition(int[] arr,int low,int high) {
        while(low<high) {
            while(arr[high]>=arr[low]&&low<high){
                high--;
            }
            Swap(arr,high,low);
            while(arr[low]<=arr[high]&&low<high) {
                low++;
            }
            Swap(arr,high,low);
        }
        return low;
    }
    public static void Swap(int[] arr,int high,int low) {
        int temp = arr[low];
        arr[low] =arr[high];
        arr[high] = temp;
    }


   static class Ticket {
        private int xi;
        private int yi;

        public Ticket( int xi, int yi) {
            this.xi = xi;
            this.yi = yi;
        }

        /**
         * 根据 agent的部署情况排序, 降序
         */
        public static class CustomComparator implements Comparator {

            @Override
            public int compare(Object arg0, Object arg1) {
                Ticket l1 = (Ticket) arg0;
                Ticket l2 = (Ticket) arg1;
                if (l1.xi > l2.xi) {
                    return -1;
                } else if (l1.xi < l2.xi) {
                    return 1;
                } else {

                    if (l1.yi > l2.yi){
                        return -1;
                    }else {
                        return 1;
                    }

                }
            }
        }
    }
}
