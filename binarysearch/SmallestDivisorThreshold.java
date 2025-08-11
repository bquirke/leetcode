package binarysearch;

import helpers.*;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class SmallestDivisorThreshold {

    @Test
    public void testSmallestDivisorThreshold() {
        int[] nums = new int[]{1,2,5,9};

        assertEquals(5, smallestDivisor(nums, 6));
    }

    /* ATTEMPT
        - check will keep a running sum of ceil(num / k)
        - i if sum <= threshold we are true
        - else false .... can check this every iteration and exit early
    */
    public int smallestDivisor(int[] nums, int threshold) {
        int left = 1;
        int right = (int) Math.pow(10, 6);

        while(left <= right){
            int mid = left + (right - left) / 2;

            if(check(mid, nums, threshold)){
                // looking for min and was successful so shrink downwards and look for better
                right = mid - 1;
            }
            else {
                left = mid + 1;
            }
        }

        return left;
    }

    public boolean check(int k, int[] nums, int threshold){
        int sum  = 0;

        for(double num : nums){
            sum += Math.ceil(num/k);

            if(sum > threshold){
                return false;
            }
        }
        return true;
    }
}
