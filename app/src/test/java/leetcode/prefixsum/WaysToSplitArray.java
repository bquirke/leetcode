package app.src.test.java.leetcode.prefixsum;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WaysToSplitArray {

    @Test
    public void testSplitArrays(){
        int[] nums = new int[]{10,4,-8,7};
        assertEquals(2, waysToSplitArray(nums));
    }


    public int waysToSplitArray(int[] nums) {
        int ans = 0;
        int[] prefix = new int[nums.length];
        prefix[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            prefix[i] = prefix[i - 1] + nums[i];
        }

        for (int i = 0; i < prefix.length -2; i++) {

            int lhs = prefix[i + 1];

            int rhs = prefix[prefix.length - i - 1] - prefix[i] + nums[i];

            ans = lhs >= rhs ? ans + 1 : ans;


        }

        return ans;
    }
}
