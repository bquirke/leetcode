package leetcode.binarysearch;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class MinEffort {

    @Test
    public void testMinEffort() {
        int[][] heights = {
                {1, 2, 2},
                {3, 8, 2},
                {5, 3, 5}
        };

        assertEquals(2, minimumEffortPath(heights));
    }

    class Cell {
        int row;
        int col;

        Cell(int row, int col){
            this.row = row;
            this.col = col;
        }
    }

    /* DSA course
        - the bineary search space is effort ... 0 - max height
        - do a dfs through the grid and check each path depending on effort
        - return true if we can get to (rows-1, columns-1)
    */

    int n;
    int m;

    int[][] dirs = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int minimumEffortPath(int[][] heights) {
        m = heights.length;
        n = heights[0].length;


        int left = 0; // min effort
        int right = 0;

        for(int[] efforts: heights){
            for(int effort: efforts){
                right = Math.max(right, effort);
            }
        }

        while (left <= right){
            int mid = left + (right - left) / 2;

            if(dfsCheck(mid, heights)){
                right = mid - 1;
            }
            else {
                left = mid + 1;
            }
        }

        return left;
    }

    boolean dfsCheck(int effort, int[][] heights){
        // iterative dfs
        Stack<Cell> stack = new Stack<>();
        boolean[][] seen = new boolean[m][n];
        seen[0][0] = true;
        stack.push(new Cell(0, 0));

        while (!stack.isEmpty()) {
            Cell curr = stack.pop();
            int row = curr.row, col = curr.col;

            if(row == m -1 && col == n -1){
                // we're at end
                return true;
            }

            for(int[] ngh : dirs){
                int nextRow = row + ngh[0];
                int nextCol = col + ngh[1];

                if(isValid(nextRow, nextCol) && !seen[nextRow][nextCol]){
                    if(Math.abs(heights[row][col] - heights[nextRow][nextCol]) <= effort){
                        seen[nextRow][nextCol] = true;
                        stack.push(new Cell(nextRow, nextCol));
                    }
                }
            }
        }

        return false;
    }

    public boolean isValid(int row, int col){
        return 0 <= row && row < m && 0 <= col && col < n;
    }
}
