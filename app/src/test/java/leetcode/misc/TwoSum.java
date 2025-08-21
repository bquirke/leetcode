package app.src.test.java.leetcode.misc;

import java.sql.Array;
import java.util.*;

public class TwoSum {

    public static void main(String[] args) {
        Map<int[], Integer> examples = new HashMap<int[], Integer>();
        examples.put(new int[]{2, 7, 11, 15}, 9);
        examples.put(new int[]{3,2,4}, 6);
        examples.put(new int[]{3,3}, 6);

        for(Map.Entry<int[], Integer> exam: examples.entrySet()){
            int[] result = new Solution().twoSum(exam.getKey(), exam.getValue());

            System.out.println(Arrays.toString(result));
        }
    }

    static class Solution {
        public int[] twoSum(int[] nums, int target) {
            int[] result = new int[2];
            for (int left = 0; left <= nums.length - 1; left++){
                for(int right = left + 1; right <= nums.length -1; right++){
                    if((nums[left]  + nums[right]) == target){
                        result[0] = left;
                        result[1] = right;
                    }
                }
            }
            return result;
        }

        public int[] twoSum_Quick(int[] nums, int target){
            int[] res = new int[2];
            for (int i = 1; i < nums.length; i++) {
                for (int j = 0; j+i<nums.length; j++) {
                    if(nums[j]+nums[j+i]==target) {
                        res[0]=j;
                        res[1]=j+i;
                        return res;
                    }
                }
            }
            return res;
        }
    }

}
