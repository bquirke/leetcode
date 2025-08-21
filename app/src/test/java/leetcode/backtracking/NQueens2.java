package app.src.test.java.leetcode.backtracking;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class NQueens2 {

    @Test
    public void testNQueens2() {
        assertEquals(2, totalNQueens(4));
    }

    /* DSA Course
        - for every row we will backtrack and iterate over cols
        - base case return a 1
            = we can aggregate this
        - for each iteration
            = we calc diags with trick
                - l - r diags all share row - col val
                - r - l diag all share row+ col val
            = Keep sets for cols and diags
            = check have each got a queen already
            = if not add .... if do continue (dont place queen)
        -
    */
    int size;
    public int totalNQueens(int n) {
        size = n;
        return backtrack(0, new HashSet<>(), new HashSet<>(), new HashSet<>());
    }

    public int backtrack(int row, Set<Integer> cols, Set<Integer> diags, Set<Integer> aDiags){
        if(row == size){
            return 1;
        }

        int solutions = 0;

        for(int col = 0; col < size; col++){
            int diag = row - col;
            int aDiag = row + col;

            if(! (cols.contains(col) || diags.contains(diag) || aDiags.contains(aDiag))){
                cols.add(col);
                diags.add(diag);
                aDiags.add(aDiag);

                solutions += backtrack(row + 1, cols, diags, aDiags);

                cols.remove(col);
                diags.remove(diag);
                aDiags.remove(aDiag);
            }
        }

        return solutions;
    }
}
