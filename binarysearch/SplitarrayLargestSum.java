package binarysearch;

import helpers.*;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class SplitarrayLargestSum {

    @Test
    public void testSplitarrayLargestSum() {
        int[] nums = new int[]{
                1,4, 4
        };

        assertEquals(4, splitArray(nums, 3));
    }

    @Test
    public void testSplitarrayLargestSum2() {
        int[] nums = new int[]{
                7,2,5,10,8
        };

        assertEquals(18, splitArray(nums, 2));
    }

    /* Used NEETCODE to help
    - https://www.youtube.com/watch?v=YUF3_eBdzsk

    - bineary search between l -> largest minimised subarray which is min(nums)
        and sum(nums) which is the largest that can be

    - check if subarray can be split m times with mid being the minimised subarray sum
*/
    public int splitArray(int[] nums, int k) {
        int min = Integer.MAX_VALUE;
        int sum = 0;

        for(int num : nums){
            min = Math.min(min, num);
            sum += num;
        }

        int left = min;
        int right = sum;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if(check(mid, nums, k)){
                right = mid - 1;
            }
            else {
                left = mid + 1;
            }
        }

        return left;
    }

    public boolean check(int subSum, int[] nums, int k){
        int currentSum = 0;
        int splits = 0;

        for(int num : nums){
            if(currentSum + num > subSum){
                currentSum = num;
                splits++;
            }
            else {
                currentSum += num;
            }
        }

        return splits + 1 <= k; // splits + 1 for last split
    }
}
