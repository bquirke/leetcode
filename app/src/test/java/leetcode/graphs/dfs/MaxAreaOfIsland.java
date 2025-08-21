package leetcode.graphs.dfs;

public class MaxAreaOfIsland {

    public static void main(String[] args){
        int[][] grid = {
                {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}
        };

        System.out.println(maxAreaOfIsland(grid));
    }

    static int n;
    static int m;
    static boolean[][] seen;

    static int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    static int maxArea = 0;
    static int currArea = 0;

    // need a directions 2d array  and an isvalid func to check bounds and if its land
    // iterate through the grid and check each squares neightbours
    // if we hit land the dfs will check all neighbours that are also land and mark them as seen
    // each outer loop of grid can eval the currArea vs maxArea
    public static int maxAreaOfIsland(int[][] grid) {
        m = grid.length;
        n = grid[0].length;

        seen = new boolean[m][n];

        for(int row = 0; row < m; row++){
            for(int col = 0; col < n; col++){
                if(!seen[row][col] && grid[row][col] == 1){
                    // land ahoy!
                    // reset currArea
                    currArea = 1;
                    seen[row][col] = true;
                    dfs(row, col, grid);

                    maxArea = Math.max(maxArea, currArea);
                }
            }
        }

        return maxArea;

    }

    public static void dfs(int row, int col, int[][] grid){
        for(int[] direction: directions){
            int nextRow = row + direction[0];
            int nextCol = col + direction[1];

            // check if its valid and if it has been seen
            if(isValid(nextRow, nextCol, grid) ){
                if(!seen[nextRow][nextCol]){
                    seen[nextRow][nextCol] = true;
                    // update curr area
                    currArea++;
                    dfs(nextRow, nextCol, grid);
                }

            }
        }
    }

    public static boolean isValid(int row, int col, int[][] grid){
        return 0 <= row && row < m && 0 <= col && col < n && grid[row][col] == 1;
    }
}
