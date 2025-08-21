package leetcode.graphs.dfs;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PerimeterOfIsland {

    // https://leetcode.com/problems/island-perimeter/?envType=problem-list-v2&envId=ajc6bx5i

    @Test
    public void testPerimeterOfIsland() {
        int[][] grid = {
                {0, 1, 0, 0},
                {1, 1, 1, 0},
                {0, 1, 0, 0},
                {1, 1, 0, 0}
        };

        assertEquals(16, islandPerimeter(grid));
    }

    // so one island means one connected component
    // if we dfs the cc we can transverse whole island
    // agg the sides touching water
    int[][] directions = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    int ans;
    int m;
    int n;
    boolean[][] seen;
    public int islandPerimeter(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        seen = new boolean[m][n];

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                // seen check not really needed
                if(!seen[i][j] && grid[i][j] == 1){
                    seen[i][j] = true;
                    dfs(i, j, grid);

                    // we can return here as q states only one island.... a dfs will visit whole island
                    return ans;
                }
            }
        }

        // cant happen due to problme  statement
        return -1;
    }

    public void dfs(int row, int col, int[][] grid){
        // add current grid locations permim
        ans += calculatePerimeter(row, col, grid);

        // create neighbours
        for(int[] neighbours: directions){
            int nextRow = row + neighbours[0];
            int nextCol = col + neighbours[1];

            if(isValid(nextRow, nextCol) && !seen[nextRow][nextCol] && grid[nextRow][nextCol] == 1){
                seen[nextRow][nextCol] = true;
                dfs(nextRow, nextCol, grid);
            }
        }
    }

    private int calculatePerimeter(int row, int col, int[][] grid) {
        int p = 0;

        for(int[] neighbours: directions){
            int nextRow = row + neighbours[0];
            int nextCol = col + neighbours[1];

            // if its not valid it is beside water or the edge of grid.... meaning we can add to perimeter
            if(isValid(nextRow, nextCol)){
                if(grid[nextRow][nextCol] == 0) {
                    p++;
                }
            }
            else {
                p++;
            }
        }
        return p;
    }

    public boolean isValid(int row, int col){
        return 0 <= row && row < m && 0 <= col && col < n;
    }
}
