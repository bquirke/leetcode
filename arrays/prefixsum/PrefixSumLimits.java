package arrays.prefixsum;

public class PrefixSumLimits {


    public static void main(String[] args){
        // Define the nums array
        int[] nums = {1, 6, 3, 2, 7, 2};

        // Define the queries array as a 2D array
        int[][] queries = {
                {0, 3},
                {2, 5},
                {2, 4}
        };
        for(boolean i: Solution.answerQueries(nums, queries, 13)){
            System.out.println(i);
        }

    }

    public static class Solution {
        public static boolean[] answerQueries(int[] nums, int[][] queries, int limit) {
            int[] prefix = new int[nums.length];
            prefix[0] = nums[0];

            for (int i = 1; i < nums.length; i++) {
                prefix[i] = prefix[i - 1] + nums[i];
            }

            boolean[] ans = new boolean[queries.length];
            for (int i = 0; i < queries.length; i++) {
                int x = queries[i][0], y = queries[i][1];
                int curr = prefix[y] - prefix[x] + nums[x];
                ans[i] = curr < limit;
            }

            return ans;
        }
    }


}
