package leetcode.dynamicprogramming.matrix;

import leetcode.helpers.TestResourceUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.*;

import static org.junit.Assert.*;

public class MinFallingPathSum {

    @Test
    public void testMinFallingPathSum() {
        int[][] grid = {
                {2, 1, 3},
                {6, 5, 4},
                {7, 8, 9}
        };

        assertEquals(13 , minFallingPathSum(grid));
    }

    @Test
    public void testMinFallingPathSum2() {
        int[][] grid = {
                {-19, 57},
                {-40, -5}
        };

        assertEquals(-59 , minFallingPathSum(grid));
    }

    @Test
    public void testLargeArray() throws IOException {
        int[][] grid = TestResourceUtils.loadMatrix("/fixtures/largeArr.json");

        assertEquals(-1, minFallingPathSum(grid));
    }

    /* ATTEMPT
        - dp(i, j) => ret min falling sum till i, j

        - RR => min(n, nw, ne) + grid[i][j]

        - base case => dp(0, j) = grid[0][j]
    */
    // top down
    int m;
    int n;
    int[][] grid;

    // memorisation
    int[][] mem;
    public int minFallingPathSumTD(int[][] matrix) {
        m = matrix.length;
        n = matrix[0].length;
        grid = matrix;

        mem = new int[m][n];

        for(int[] m: mem){
            Arrays.fill(m, -101);
        }

        // need to eval all bot rows
        int ans = Integer.MAX_VALUE;
        for(int j = 0; j < n; j++){
            ans = Math.min(ans, dp(m - 1, n - 1 - j));
        }

        return ans;
    }

    public int dp(int i, int j){
        // base case
        if(i == 0){
            return grid[i][j];
        }

        // mem
        if(mem[i][j] != -101){
            return mem[i][j];
        }

        int ans = Integer.MAX_VALUE;

        // not top row
        if(i > 0){
            // north
            ans = Math.min(ans, dp(i - 1, j));

            // north west
            if(j > 0){
                ans = Math.min(ans, dp(i - 1, j -1));
            }

            // north east
            if(j < n - 1){
                ans = Math.min(ans, dp(i - 1, j + 1));
            }

        }

        // add on current squares val
        ans += grid[i][j];


        mem[i][j] = ans;

        return mem[i][j];
    }

    // bot up
    public int minFallingPathSum(int[][] matrix) {
        m = matrix.length;
        n = matrix[0].length;

        int dp[][] = new int[m][n];

        // base cases
        for(int j = 0; j < n; j++){
            dp[0][ n - 1 - j] = matrix[0][n - 1 - j];
        }

        // can start from second row
        for(int i = 1; i < m; i++){
            for(int j = 0; j < n; j++){
                int ans = Integer.MAX_VALUE;

                // not bot row
                if(i < m){
                    // north
                    ans = Math.min(ans, dp[i - 1][j]);

                    if(j > 0){
                        // north west
                        ans = Math.min(ans, dp[i - 1][ j - 1]);
                    }

                    if(j < n -1){
                        // north east
                        ans = Math.min(ans, dp[i - 1][j + 1]);
                    }
                }

                dp[i][j] = ans + matrix[i][j];
            }
        }

        int ans = Integer.MAX_VALUE;

        // iterate over bot row and return lowest
        for(int j = 0; j < n; j++){
            ans = Math.min(ans, dp[m - 1][ n - 1 - j]);
        }

        return ans;
    }
}
