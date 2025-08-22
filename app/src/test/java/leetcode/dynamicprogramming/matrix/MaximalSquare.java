package leetcode.dynamicprogramming.matrix;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class MaximalSquare {

    @Test
    public void testMaximalSquare() {
        char[][] matrix = {
                {'1','0','1','0','0'},
                {'1','0','1','1','1'},
                {'1','1','1','1','1'},
                {'1','0','0','1','0'}
        };
        assertEquals(4, maximalSquare(matrix));
    }

    /* Seen walkthrough in bottom up solution https://www.youtube.com/watch?v=Ti5vfu9arXQ&list=PLqOMFZ3ISeXU7CJSUnlZ671_i9AXzJ7li&index=25&t=797s

    - dp(i) => return largest sq i,j can produce from it and its neighbours

    - RR => min(left, down, diag) where i,j = 1

    - base case => dp(i, j) = 0 where i, j is zero
    */

    // top down
    int m;
    int n;
    int[][] mem;
    char[][] grid;
    public int maximalSquare(char[][] matrix) {
        m = matrix.length;
        n = matrix[0].length;
        grid = matrix;
        mem = new int[m][n];
        for(int[] m : mem){
            Arrays.fill(m, -1);
        }

        int max = 0;

        // Loop over every cell .... memorisation helps with the perf here
        for(int i = 0; i < m; i++){
            for(int j = 0; j< n; j++){
                max = Math.max(max, dp(i, j));
            }
        }

        return max * max;
    }

    public int dp(int i, int j){
        // out of bounds
        if(i >= m || j >= n){
            return 0;
        }
        // base case
        if(grid[i][j] == '0'){
            return 0;
        }

        if(mem[i][j] != -1){
            return mem[i][j];
        }

        // check neighbours
        int down = dp(i + 1, j);
        int right = dp(i, j + 1);
        int diag = dp(i + 1, j + 1);

        // can start a square with min of these heights plus a new height
        int ans = Math.min(down, Math.min(right, diag)) + 1;

        mem[i][j] = ans;
        return ans;

    }
}
