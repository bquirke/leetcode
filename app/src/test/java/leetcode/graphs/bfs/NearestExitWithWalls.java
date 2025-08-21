package leetcode.graphs.bfs;

import leetcode.graphs.bfs.helpers.State;
import org.junit.Test;
import java.util.LinkedList;
import java.util.Queue;

import static org.junit.Assert.assertEquals;

public class NearestExitWithWalls {

    @Test
    public void testNearestExit() {
        NearestExitWithWalls solver = new NearestExitWithWalls();
        char[][] maze = {
                {'+','+','.','+'},
                {'.','.','.','+'},
                {'+','+','+','.'}
        };

//        char[][] maze = {
//                {'+', '+', '+'},
//                {'.', '.', '.'},
//                {'+', '+', '+'}
//        };
        int[] entrance = {1, 2};
        int result = solver.nearestExit(maze, entrance);

        assertEquals(1, result);
    }

    int[][] directions = new  int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    int m;
    int n;

    boolean[][] seen;
    public int nearestExit(char[][] maze, int[] entrance) {
        m = maze.length;
        n = maze[0].length;

        seen =  new boolean[m][n];

        Queue<State> queue = new LinkedList<>();
        queue.add(new State(entrance[0], entrance[1], 0));
        seen[entrance[0]][entrance[1]] = true;

        while (!queue.isEmpty()) {
            State curr = queue.remove();
            int row = curr.row, col = curr.col, steps = curr.steps;

            for(int[] neighbour: directions){
                int nextRow =  row + neighbour[0];
                int nextCol = col + neighbour[1];

                if(!(row == entrance[0] && col == entrance[1]) && isExit(nextRow, nextCol, maze)){
                    return steps;
                }
                else if (isValid(nextRow, nextCol, maze) && !seen[nextRow][nextCol] ){
                    seen[nextRow][nextCol] = true;
                    queue.add(new State(nextRow, nextCol, steps + 1));
                }

            }
        }

        return -1;
    }

    boolean isValid(int row, int col, char[][] maze){
        return 0 <= row && row < m && 0 <= col && col < n && maze[row][col] == '.';
    }

    private boolean isExit(int row, int col, char[][] maze){
        return  row < 0 || row >= m || col < 0 || col >= n;
    }
}
