package leetcode.dynamicprogramming;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class PascalsTriangle {

    @Test
    public void testPascalsTriangle() {
        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(Arrays.asList(1));
        triangle.add(Arrays.asList(1, 1));
        triangle.add(Arrays.asList(1, 2, 1));
        triangle.add(Arrays.asList(1, 3, 3, 1));
        triangle.add(Arrays.asList(1, 4, 6, 4, 1));

        assertEquals(triangle, generate(5));
    }

    /* ATTEMPT
        -
    */

    int n;
    Map<Integer, List<Integer>> mem = new HashMap<>();
    List<List<Integer>> ans = new ArrayList<>();
    public List<List<Integer>> generate(int numRows) {
        n = numRows;

        dp(n -1);

        return ans;
    }

    public List<Integer> dp(int i){
        List<Integer> curr = new ArrayList<>();
        // base case
        if(i == 1){
            ans.add(0, Arrays.asList(1));
            ans.add(1, Arrays.asList(1, 1));

            return new ArrayList<>(Arrays.asList(1, 1));
        }

        if(mem.containsKey(i)){
            return mem.get(i);
        }

        List<Integer> lastRow = dp(i - 1);
        curr.add(1);
        for(int j = 1; j <= i - 1; j++){

            int sum  = lastRow.get(j - 1) + lastRow.get(j);
            curr.add(sum);
        }
        curr.add(1);

        mem.put(i, curr);
        ans.add(curr);

        return mem.get(i);

    }
}
