package leetcode.dynamicprogramming.matrix;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class UniquePath2 {

    @Test
    public void testUniquePath2() {
        int[][] grid = {
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}
        };

        assertEquals(2, uniquePathsWithObstacles(grid));
    }

    @Test
    public void testUniquePath1() {
        int[][] grid = {{1}};

        assertEquals(0, uniquePathsWithObstacles(grid));
    }

    /* ATTEMPT
        - dp(i, j) => ret num of paths to i, j

        - RR dp(i, j) => dp(i + 1, j) + dp(i, j +1) as long as i,j != obstacle

        - base case => dp(0,0) = 1

    */
    // top down
    int[][] mem;
    int m;
    int n;
    int[][] grid;

    public int uniquePathsWithObstaclesTD(int[][] obstacleGrid) {
        m = obstacleGrid.length;
        n = obstacleGrid[0].length;
        grid = obstacleGrid;

        mem = new int[m][n];

        for(int[] m : mem){
            Arrays.fill(m, -1);
        }

        return dp(m - 1, n - 1);
    }

    public int dp(int row, int col) {
        // base case
        if (row + col == 0) {
            // is obstacle???
            if (grid[row][col] == 1) {
                return 0; // cant be reach
            }
            return 1;
        }

        if (mem[row][col] != -1) {
            return mem[row][col];
        }

        int ans = 0;
        // is obstacle???
        if (grid[row][col] != 1) {
            if (row > 0) {
                ans += dp(row - 1, col);
            }
            if (col > 0) {
                ans += dp(row, col - 1);
            }
        }

        mem[row][col] = ans;

        return mem[row][col];

    }

    // bot up
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        m = obstacleGrid.length;
        n = obstacleGrid[0].length;
        grid = obstacleGrid;

        int[][] dp = new int[m][n];

        // base case
        if(grid[0][0] == 1){
            return 0;  //cant be reached
        }
        else {
            dp[0][0] = 1;
        }

        for (int row = 0; row < m; row++){
            for(int col = 0; col < n; col++){
                if(row + col == 0){
                    continue; // skipping base case
                }

                // no obstacle
                if(grid[row][col] != 1){
                    int ans = 0;

                    if(row > 0){
                        dp[row][col] += dp[row - 1][col];
                    }
                    if(col > 0){
                        dp[row][col] += dp[row][col - 1];
                    }
                }
            }
        }

        return dp[m - 1 ][n - 1];
    }
}
