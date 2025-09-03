package leetcode.graphs.dfs;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PerimeterOfIsland_Practice1 {

    // https://leetcode.com/problems/island-perimeter/?envType=problem-list-v2&envId=ajc6bx5i

    @Test
    public void testPerimeterOfIsland() {
        int[][] grid = {
                {0, 1}
        };

        assertEquals(4, islandPerimeter(grid));
    }

    @Test
    public void testPerimeterOfIsland2() {
        int[][] grid = {
                {0, 1, 0, 0},
                {1, 1, 1, 0},
                {0, 1, 0, 0},
                {1, 1, 0, 0}
        };

        assertEquals(16, islandPerimeter(grid));
    }

    // ATTEMPT
    // an island in a graph matrix can be described as a connected component
    // if we find land, we transverse it via dfs and compute permimeter as we go
    int m;
    int n;
    int[][] grid;

    boolean[][] seen;
    int ans;

    int[][] directions = new int[][] { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

    public int islandPerimeter(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        this.grid = grid;

        seen = new boolean[m][n];

        ans = 0;
        // find land
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    // start dfs
                    seen[i][j] = true;
                    // dfs
                    dfs(i, j);
                    return ans;
                }
            }
        }

        return ans;
    }

    public void dfs(int row, int col) {
        for (int[] neighbour : directions) {
            int nextRow = row + neighbour[0];
            int nextCol = col + neighbour[1];

            if (isValid(nextRow, nextCol)) {
                if (grid[nextRow][nextCol] == 1) {
                    if (!seen[nextRow][nextCol]) {
                        seen[nextRow][nextCol] = true;
                        dfs(nextRow, nextCol);
                    }
                } else {
                    // is water
                    ans++;
                }

            } else {
                ans++; // out of bounds
            }

        }
    }

    // within bound
    public boolean isValid(int row, int col) {
        return 0 <= row && row < m && 0 <= col && col < n;
    }
}
