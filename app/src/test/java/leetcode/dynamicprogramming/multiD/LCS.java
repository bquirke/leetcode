package leetcode.dynamicprogramming.multiD;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class LCS {

    @Test
    public void testLCS() {
        assertEquals(3, longestCommonSubsequence("abcde", "ace"));
    }

    /* DSA Course
        - dp(i,j) ret LCS for str1 starting at i and str2 starting at j

        - RR => 1. str1[i] == str2[j] .... 1 + dp(i + 1, j + 1)
                2. str1[i] != str2[j]..... max(dp(i + 1, j), dp(i, j + 1))

        - base case => i == str1.len || j == str2.len
    */

    // top down
    // Map<Integer, Integer> mem = new HashMap<>(); ... large grid and densely packed

    int[][] mem;

    // doing class vars to make it more readable
    String text1;
    String text2;
    int m;
    int n;
    public int longestCommonSubsequence(String text1, String text2) {
        this.m = text1.length();
        this.n = text2.length();
        this.text1 = text1;
        this.text2 = text2;

        this.mem = new int[m][n];

        // init mem
        for(int i = 0; i < m; i++){
            Arrays.fill(mem[i], -1);
        }

        return dp(0, 0);
    }

    public int dp(int i, int j){

        // base case
        if(i == m || j == n){
            return 0; // no more sub sequences
        }

        if(mem[i][j] != -1){
            return mem[i][j];
        }

        if(text1.charAt(i) == text2.charAt(j)){
            mem[i][j] = dp(i + 1, j + 1) + 1;
        }
        else {
            mem[i][j] = Math.max(dp(i + 1, j), dp(i, j + 1));
        }

        return mem[i][j];
    }

}
