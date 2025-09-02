package leetcode.graphs.bfs;



import leetcode.helpers.State;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathMatrix {

    public static void main(String[] args){
//        int[][] edges = {
//                {0, 1},
//                {1, 0}
//        };
        int[][] edges = {
                {0, 0, 0},
                {1, 1, 0},
                {1, 1, 0}
        };


        System.out.println(shortestPathBinaryMatrix(edges));
    }


    /*
    - To use bfs..
        - use a queue tp store a state class
        - Iterate over it and remove a state
        - check if the state.row and state.col are at the right corner
        - if not add the neighbours(8 directionally) to queue */
    static int n;
    static boolean[][] seen;
    public static int shortestPathBinaryMatrix(int[][] grid) {
        if(grid[0][0] != 0){
            return -1;
        }
        n = grid.length;
        seen = new boolean[n][n];
        Queue<State> queue = new LinkedList<>();
        int[][] directions = new int[][]{{-1,0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1,-1}};

        queue.add(new State(0, 0, 1));
        seen[0][0] = true;

        while (!queue.isEmpty()) {
            State node = queue.remove();
            int x = node.row, y = node.col, steps = node.steps;

            if(x == n - 1 && y == n - 1){
                return steps;
            }

            for(int[] dir : directions){
                int nextRow = x +dir[0];
                int nextCol = y + dir[1];

                if(isValid(nextRow, nextCol, grid) && !seen[nextRow][nextCol]){
                    seen[nextRow][nextCol] = true;

                    queue.add(new State(nextRow, nextCol, steps + 1));
                }
            }
        }

        return -1;
    }

    static boolean isValid(int row, int col, int[][] grid){
        boolean debug = 0 <= row && row < n && 0 <= col && col < n && grid[row][col] == 0;
        return debug;
    }
}
