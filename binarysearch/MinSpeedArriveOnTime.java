package binarysearch;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MinSpeedArriveOnTime {

    @Test
    public void testMinSpeedArriveOnTime() {
        int[] dist = {1, 3, 2};
        double hour = 6;
        int expected = 1;

        assertEquals(expected, minSpeedOnTime(dist, hour));
    }

    @Test
    public void testMinSpeedArriveOnTime2() {
        int[] dist = {1, 3, 2};
        double hour = 2.7;
        int expected = 3;

        assertEquals(expected, minSpeedOnTime(dist, hour));
    }


    /* ATTEMPT but verified by DSA course
        - Use binary search for speeds between 1 and description gave us max of pow(10, 7)
        - the check fuction sums all d / speed and rounds up for all hours except last and verifies <= h
        - -1 when the arr.len is larger than h
    */
    public int minSpeedOnTime(int[] dist, double hour) {

        if(dist.length > Math.ceil(hour)){
            return -1;
        }

        int left = 1;
        int right = (int) Math.pow(10, 7);

        while(left <= right){
            int mid = left + (right - left) / 2;

            if(check(mid, dist, hour)){
                right = mid - 1;
            }
            else {
                left = mid + 1;
            }
        }

        return left;
    }

    public boolean check(int speed, int[] dist, double hour){
        double total = 0;

        for(double d : dist){
            total = Math.ceil(total); // ceiling here as the last train doesnt need to wait till the hour
            total +=  d / speed;
        }

        return total <= hour;
    }
}
