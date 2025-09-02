package leetcode.graphs.bfs;

import leetcode.helpers.State;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

import static org.junit.Assert.assertEquals;

public class SnakesLadders {


    @Test
    public void testSnakesAndLadder1(){
        int[][] board = {
                {-1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, 35, -1, -1, 13, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, 15, -1, -1, -1, -1}
        };
        assertEquals(4, snakesAndLadders(board));
    }

    @Test
    public void testSnakesAndLadder2(){
        int[][] board = {
                {-1, -1, -1},
                {-1,  9,  8},
                {-1,  8,  9}
        };
        assertEquals(1, snakesAndLadders(board));
    }

    @Test
    public void testSnakesAndLadder3(){
        int[][] board = {
                {-1,  7, -1},
                {-1,  6,  9},
                {-1, -1,  2}
        };

        assertEquals(1, snakesAndLadders(board));
    }

    @Test
    public void testSnakesAndLadder4(){
        int[][] board = {
                {-1, 11,  6, -1},
                {-1, 15, 16, -1},
                {-1,  7, -1,  8},
                {-1, -1, -1,  8}
        };


        assertEquals(2, snakesAndLadders(board));
    }


    int n;
    boolean[][] seen;
    public int snakesAndLadders(int[][] board) {
        n = board.length;
        int endCol = n % 2 == 0 ? 0: n -1;

        seen = new boolean[n][n];

        Queue<State> queue = new LinkedList<>();
        queue.add(new State(n-1, 0, 0));
        seen[n-1][0] =true;
        // do we need a seen???

        while(!queue.isEmpty()){
            State cell = queue.remove();
            int row = cell.row, col = cell.col, steps = cell.steps;

            // we've made it to the end..... its flipped for both row and col
            if(row == 0 && col == endCol){
                return steps;
            }

            // neighbours - range(curr +1, min(goal, curr + 6))
            int diceRoll = 1;
            while (diceRoll <= 6){
                // even so l -r
                 if((n -1 - row) % 2== 0){
                    // try incrementing col first
                    if(col + diceRoll < n ){
                        int nextCol = col + diceRoll;

                        if(isValid(row, col)){
                            State step = isSnakeOrLadder(row, nextCol, steps + 1, board);
                            if(!seen[step.row][step.col]){
                                queue.add(step);
                                seen[step.row][step.col] = true;
                            }

                        }
                    }
                    // its hit the boundary
                    // decrement row and zig-zag
                    else {
                        int nextRow = row - 1;
                        int nextCol = n - 1 - (col + diceRoll - n) ;


                        if(isValid(nextRow, nextCol)){
                            State step = isSnakeOrLadder(nextRow, nextCol, steps + 1, board);
                            if(!seen[step.row][step.col]){
                                queue.add(step);
                                seen[step.row][step.col] = true;
                            }

                        }
                    }
                }
                // odd row so r -l
                else {
                    // try decrementing col first
                    if(col - diceRoll >= 0 ){
                        int nextCol = col - diceRoll;
                        if(isValid(row, nextCol)){
                            State step = isSnakeOrLadder(row, nextCol, steps + 1, board);
                            if(!seen[step.row][step.col]){
                                queue.add(step);
                                seen[step.row][step.col] = true;
                            }

                        }
                    }

                    // its hit a boundary
                    // decrement row and zig-zag back to l -r
                    else {
                        int nextRow = row - 1;
                        int nextCol = diceRoll - col - 1; // -1 here for the used step going from another row

                        if(isValid(nextRow, nextCol)){
                            State step = isSnakeOrLadder(nextRow, nextCol, steps + 1, board);
                            if(!seen[step.row][step.col]){
                                queue.add(step);
                                seen[step.row][step.col] = true;
                            }

                        }
                    }

                }
                diceRoll++;
            }

        }


        return -1;
    }

    private State isSnakeOrLadder(int row, int col, int steps, int[][] board) {
        if(board[row][col] == -1){
            return new State(row, col, steps);
        }
        else {
            int cellNumber  = board[row][col];
            int zeroIndex = cellNumber - 1;
            int rowFromTop = zeroIndex / n; // find the row in a trad 2d array
            int nextCol = zeroIndex % n;

            int nextRow = n - 1 - rowFromTop; // bottom up 2d so reverse

            ///  trad row is even
            if(rowFromTop % 2 == 1){
                nextCol = n -1 - nextCol;
            }
            return new State(nextRow, nextCol, steps);
        }
    }

    // check its within the board
    private boolean isValid(int row, int col) {
        return 0 <= row && row < n && 0<= col && col < n;
    }
}
