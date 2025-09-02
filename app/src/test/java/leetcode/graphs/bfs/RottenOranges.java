package leetcode.graphs.bfs;

import leetcode.helpers.State;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

import static org.junit.Assert.assertEquals;

public class RottenOranges {

    @Test
    public void testRottenOranges1() {
        int[][] grid = {
                {2, 1, 1},
                {0, 1, 1},
                {1, 0, 1}
        };

        assertEquals(-1, orangesRotting(grid));
    }

    @Test
    public void testRottenOranges2() {
        int[][] grid = {
                {2, 1, 1},
                {1, 1, 0},
                {0, 1, 1}
        };

        assertEquals(4, orangesRotting(grid));
    }

    @Test
    public void testRottenOranges3() {
        int[][] grid = {
                {1, 2}
        };

        assertEquals(1, orangesRotting(grid));
    }

    // start at all rotten oranges and bfs them
    // min steps to all fresh oranges is ans
    // how to check we've seen all oranges?
    // iterate through seen and check all false entries for fresh oranges?
    boolean[][] seen;
    int[][] directions = new int[][]{{-1, 0}, {0, 1}, {1,0}, {0,-1}};

    int m;
    int n;
    public int orangesRotting(int[][] grid) {
        int ans = 0;
        m = grid.length;
        n = grid[0].length;

        seen = new boolean[m][n];

        Queue<State> queue = new LinkedList<>();

        // find all rotten oranges

        int numberOfFreshOranges = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 2){
                    queue.add(new State(i, j, 0));
                    seen[i][j] = true;
                }
                else if(grid[i][j] == 1){
                    numberOfFreshOranges++;
                }
            }
        }

        if(!queue.isEmpty() && numberOfFreshOranges == 0){
            return 0;
        }
        else if(queue.isEmpty() && numberOfFreshOranges > 0){
            return -1;
        }


        while(!queue.isEmpty()){
            State curr = queue.remove();
            int row = curr.row, col = curr.col, steps = curr.steps;

            ans = Math.max(ans, steps);

            for(int[] dir : directions){
                int nextRow = row + dir[0];
                int nextCol = col + dir[1];

                if(isValid(nextRow, nextCol, grid) && !seen[nextRow][nextCol]){
                    numberOfFreshOranges--;
                    seen[nextRow][nextCol] = true;
                    queue.add(new State(nextRow, nextCol, steps + 1));
                    ans = Math.max(ans, steps + 1);
                }
            }
        }

        return numberOfFreshOranges == 0 ? ans : -1;
    }

    private boolean isValid(int row, int col, int[][] grid) {
        return 0<= row && row < m && 0 <= col && col < n && grid[row][col] == 1;
    }
}
