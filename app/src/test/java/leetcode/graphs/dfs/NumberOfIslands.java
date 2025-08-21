package leetcode.graphs.dfs;


public class NumberOfIslands {

    public static void main(String[] args){
//        char[][] grid = {
//                {'1', '1', '1', '1', '0'},
//                {'1', '1', '0', '1', '0'},
//                {'1', '1', '0', '0', '0'},
//                {'0', '0', '0', '0', '0'}
//        };

        char[][] grid = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };



        System.out.println(numIslands(grid));
    }

    // again we just look for land and dfs it while checking have we seen it before...
    // if we dfs and havent seen it we know its a connected component
    static int m;
    static int n;
    static boolean[][] seen;
    static int[][] directions = {{0, 1}, {1, 0}, {0,-1}, {-1, 0}};

    public static int numIslands(char[][] grid) {
        int ans = 0;

        // build seen 2d array
        m = grid.length;
        n = grid[0].length;
        seen = new boolean[m][n];

        // iterate through matrix
        for(int row = 0; row < m; row++){
            for (int col = 0; col < n; col++){
                if(grid[row][col] == '1' && !seen[row][col]){
                    ans++;
                    seen[row][col] = true;
                    dfs(row, col, grid);
                }
            }
        }
        return ans;
    }

    // check each neighbour.... it is r/d/l/u according to problem statement
    // if its valid and not in seen... mark it as seen
    private static void dfs(int row, int col, char[][] grid) {
        for(int[] directions: directions){
            int nextRow = row + directions[0];
            int nextCol = col + directions[1];
            if(isValid(nextRow, nextCol, grid) && !seen[nextRow][nextCol]){
                seen[nextRow][nextCol] = true;
                dfs(nextRow, nextCol, grid);
            }
        }
    }

    // a land sq is valid if it isnt out of bound and is land... if it water we ignore it
    private static boolean isValid(int row, int col, char[][] grid) {
        return 0 <= row && row < m && 0 <= col && col < n && grid[row][col] == '1';
    }
}
