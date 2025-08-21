package leetcode.dynamicprogramming.matrix;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class MinPathSum {

    @Test
    public void testMinPathSum() {
        int[][] grid = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };

        assertEquals(7, minPathSum(grid));
    }

    /* DAS course but attempted
        - dp(i, j) - ret paths to reach grid[i][j]

        - RR => dp(i, j) = min(dp( i - 1, j), dp(i, j -1)) + grid[i][j]

        - base case => dp(0, 0) = grid[0][0]
    */

    // top down
    int m;
    int n;
    int[][] grid;
    int[][] mem;
    public int minPathSum(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        this.grid = grid;

        mem = new int[m][n];

        for(int i = 0; i < m; i++){
            Arrays.fill(mem[i], -1);
        }

        return dp(m - 1, n - 1);
    }

    public int dp(int row, int col){
        // base case
        if(row + col == 0){
            return grid[row][col];
        }

        if(mem[row][col] != -1){
            return mem[row][col];
        }

        int ans = Integer.MAX_VALUE;
        // down
        if(row > 0){
            ans = Math.min(ans, dp(row - 1, col));
        }
        // left
        if(col > 0){
            ans = Math.min(ans, dp(row, col - 1));
        }
        // add grid itselfs val
        mem[row][col] = ans + grid[row][col];

        return mem[row][col];
    }
}
