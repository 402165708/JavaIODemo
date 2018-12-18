package LeetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers, find two numbers such that they add up to a specific target number.
 * The function twoSum should return indices of the two numbers such that they add up to the target,
 * where index1 must be less than index2. Please note that your returned answers (both index1 and index2)
 * are not zero-based.
 *
 * 给定一个数组，找出两个数，使得和等于target值，并返回这两个数的下标。要求第一个下标小于第二个下标
 *
 * Created by chenzhitao on 2018/7/31
 */
public class TwoSumMethod {

    /**
     * 解法1：先使用快速排序，另一个数组里面记录改变的序号。然后使用两个指针，从前后遍历排序后的数组。
     * 小于目标值，前指针后移；大于目标值，后指针前移
     *
     * 时间复杂度O（NlnN）
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum(int[] nums, int target){

        int[] index = new int[nums.length];
        //遍历数组，记录
        for (int i = 0; i < nums.length; i++) {
            index[i] = i+1;
        }
        quickSort(nums,index,0,nums.length-1);

        int left = 0,right = nums.length-1;
        while (left < right){
            if (target == nums[left] + nums[right]) {
                int [] resultIndex = new int[2];
                if (index[left] > index[right]) {
                    resultIndex[0] = index[right];
                    resultIndex[1] = index[left];
                }else {
                    resultIndex[0] = index[left];
                    resultIndex[1] = index[right];
                }
                return resultIndex;
            }else if (target > nums[left] + nums[right]){
                right ++;
            }else {
                left ++;
            }
        }
        return null;

    }

    // 快排
    private static void quickSort(int[] nums, int[] index, int left, int right) {
        if (left < right) {

        }
    }

    /**
     * 解法2： 使用map，保存value和index；遍历数组，如果target减去当前值的差，存在于map，返回map和i;不存在就添加到map中
     *
     * 时间复杂度 O（n）
     */
    public static int[] twoSum1(int[] nums, int target){
        Map<Integer,Integer> value2IndexMap = new HashMap(nums.length);
        int[] indexs = new int[2];
        for (int i = 0; i < nums.length; i++) {
            if (value2IndexMap.containsKey(target-nums[i])){
                indexs[0] = value2IndexMap.get(target-nums[i]);
                indexs[1] = i;
                break;
            }else {
                value2IndexMap.put(nums[i],i);
            }
        }
        return indexs;



    }
    public static void main(String [] args){
        int[] nums = {2, 7, 11, 15};
        int target = 13;
        int[] index = twoSum1(nums,target);
        System.out.println(index[0]);
        System.out.println(index[1]);

    }


}
