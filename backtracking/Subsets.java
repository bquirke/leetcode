package backtracking;

import helpers.*;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class Subsets {

    @Test
    public void testSubsets() {
        int[] nums = new int[]{1,2,3};

        List<List<Integer>> expected = Arrays.asList(
                Collections.emptyList(),
                Arrays.asList(1),
                Arrays.asList(2),
                Arrays.asList(1, 2),
                Arrays.asList(3),
                Arrays.asList(1, 3),
                Arrays.asList(2, 3),
                Arrays.asList(1, 2, 3)
        );


        assertEquals(expected, subsets(nums));
    }

    /* DSA Course
        - Back track while passing idx
        - This way we never reuse an elem
        - because its subset all will start with earliest ith and
            n - 1 will be in a subset beginning by itself with itself ... as 123 is the same subset as 321
    */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        backtrack(new ArrayList<>(), 0, nums, ans);
        return ans;
    }

    public void backtrack(List<Integer> curr, int i, int[] nums, List<List<Integer>> ans){
        // we've reached the end of the array
        if(i > nums.length){
            return;
        }

        // looks after empty case too
        ans.add(new ArrayList<>(curr));

        for(int j = i; j < nums.length; j++){
            curr.add(nums[j]);
            backtrack(curr, j + 1, nums, ans);
            curr.remove(curr.size() - 1);
        }
    }
}
