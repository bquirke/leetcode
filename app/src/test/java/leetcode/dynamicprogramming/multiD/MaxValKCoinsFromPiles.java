package leetcode.dynamicprogramming.multiD;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class MaxValKCoinsFromPiles {

    @Test
    public void testMaxValKCoinsFromPiles() {
        List<List<Integer>> piles = new ArrayList<>();

        // Add the [100] piles
        for (int i = 0; i < 6; i++) {
            piles.add(new ArrayList<>(Arrays.asList(100)));
        }

        // Add the [1,1,1,1,1,1,700] pile
        piles.add(new ArrayList<>(Arrays.asList(1, 1, 1, 1, 1, 1, 700)));

        assertEquals(706, maxValueOfCoins(piles, 7));
    }

    /* DSA course
        - dp(i, remain) => ret highest possible val given it piles and remain coins left

        - dp(i, remain) => max(skip, take)
            * skip = dp(i + 1, remain)

            * take = max(sum(pile[i][j]) + dp(i + 1, remain - j - 1)
                        for j 0, min(remain, piles.len))

        - base case => remain == 0 || i > piles.len
    */

    // top down
    int n;
    int[][] mem;
    List<List<Integer>> piles;
    public int maxValueOfCoins(List<List<Integer>> piles, int k) {
        n = piles.size();
        mem = new int[n][k + 1];

        for(int i = 0; i < n; i++){
            Arrays.fill(mem[i], -1);
        }

        this.piles = piles;

        return dp(0, k);
    }

    public int dp(int i, int remain){
        // base cases
        if(i == n || remain == 0){
            return 0;
        }

        // memorisation
        if(mem[i][remain] != -1){
            return mem[i][remain];
        }

        // skip ... default
        int ans = dp(i + 1, remain);

        // int current pile cum
        int curr = 0;

        for(int j = 0; j < Math.min(remain, piles.get(i).size()); j++){
            curr += piles.get(i).get(j);
            ans = Math.max(ans, curr + dp(i + 1, remain - j - 1));
        }

        mem[i][remain] = ans;

        return mem[i][remain];
    }
}
