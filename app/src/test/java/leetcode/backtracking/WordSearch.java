package app.src.test.java.leetcode.backtracking;

import org.junit.Test;

import static org.junit.Assert.*;

public class WordSearch {

    @Test
    public void testWordSearch() {
        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };

        assertTrue(exist(board, "ABCCED"));
    }


    @Test
    public void testWordSearchSEE() {
        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };

        assertTrue(exist(board, "SEE"));
    }


    /* DSA Course
        - iterate over matrix to find word[0]
        - backtrack from here ... dfs esque
        - use a seen not to re-use path letters
        - evaluate neighbours -> are they the next letter?
        - if we dont find the word we undo what we did prebacktrack
    */
    int m;
    int n;
    String target;
    int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    boolean[][] seen;
    public boolean exist(char[][] board, String word) {
        m = board.length;
        n = board[0].length;
        seen = new boolean[m][n];

        for(int x = 0; x < m; x++){
            for(int y= 0; y < n; y++){
                if(board[x][y] == word.charAt(0)){
                    // start backtrack-dfs
                    seen[x][y] = true;

                    // idx = 1 as we found 0
                    if(backtrack(x, y, 1, board, word)){
                        return true;
                    }
                    // not found undo ops
                    seen[x][y] = false;
                }
            }
        }

        return false;
    }

    public boolean backtrack(int x, int y, int idx, char[][] board, String word){
        if(idx == word.length()){
            // we've found all chars
            return true;
        }

        for(int[] dir: directions){
            int nextRow = x + dir[0];
            int nextCol = y + dir[1];

            if(isValid(nextRow, nextCol, idx, board, word)){
                if(!seen[nextRow][nextCol]){
                    seen[nextRow][nextCol] = true;
                    if(backtrack(nextRow, nextCol, idx + 1, board, word)){
                        return true;
                    }
                    seen[nextRow][nextCol] = false;
                }
            }
        }

        return false;
    }

    public boolean isValid(int x, int y, int idx, char[][] board, String word){
        return 0 <= x && x < m && 0 <= y && y < n && board[x][y] == word.charAt(idx);
    }
}
