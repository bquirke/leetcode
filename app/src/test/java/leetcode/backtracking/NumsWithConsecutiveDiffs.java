package leetcode.backtracking;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.*;

public class NumsWithConsecutiveDiffs {

    @Test
    public void testNumsWithConsecutiveDiffs() {
        int[] expected = {181,292,707,818,929};
        Integer[] expectedBoxed = Arrays.stream(expected).boxed().toArray(Integer[]::new);

        int[] actual = numsSameConsecDiff(3, 7);
        Integer[] actualBoxed = Arrays.stream(actual).boxed().toArray(Integer[]::new);

        assertThat(Arrays.asList(actualBoxed), containsInAnyOrder(expectedBoxed));
    }

    @Test
    public void testNumsWithConsecutiveDiffs2() {
        int[] expected = {11,22,33,44,55,66,77,88,99};
        Integer[] expectedBoxed = Arrays.stream(expected).boxed().toArray(Integer[]::new);

        int[] actual = numsSameConsecDiff(2, 0);
        Integer[] actualBoxed = Arrays.stream(actual).boxed().toArray(Integer[]::new);

        assertThat(Arrays.asList(actualBoxed), containsInAnyOrder(expectedBoxed));
    }

    /* Attempt
        - backtracking
        - iterate over 1,9
        - adding or subtracting k
        - check its in bounds
    */
    int size;
    public int[] numsSameConsecDiff(int n, int k) {
        size = n;
        Set<Integer> ans = new HashSet<>();

        for(int i = 1; i < 10; i++){
            StringBuilder curr = new StringBuilder();
            curr.append(i);
            backtrack(curr, i, k, ans);
        }

        return ans.stream().mapToInt(Integer::intValue).toArray();
    }

    public void backtrack(StringBuilder curr, int num, int k, Set<Integer> ans){
        if(curr.length() == size){
            ans.add(Integer.parseInt(curr.toString()));
            return;
        }

        for(int option : new int[]{-k, k}){
            int newNum = num + option;
            // if the new digit is within bounds
            if(newNum >= 0 && newNum < 10){
                curr.append(newNum);
                backtrack(curr, newNum, k, ans);
                curr.deleteCharAt(curr.length() - 1);
            }
        }
    }
}
