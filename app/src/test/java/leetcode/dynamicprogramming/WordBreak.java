package leetcode.dynamicprogramming;


import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class WordBreak {

    @Test
    public void testWordBreak() {
        String s = "leetcode";
        List<String> dict = new ArrayList<>(Arrays.asList("leet","code"));

        assertTrue(wordBreak(s, dict));
    }

    @Test
    public void testWordBreak2() {
        String s = "catsandog";
        List<String> dict = new ArrayList<>(Arrays.asList("cats","dog","sand","and","cat"));

        assertFalse(wordBreak(s, dict));
    }

    @Test
    public void testWordBreak3() {
        String s = "aaaaaaa";
        List<String> dict = new ArrayList<>(Arrays.asList("aaaa","aaa"));

        assertTrue(wordBreak(s, dict));
    }

    // top down // WRONG
    boolean[][] mem;
    int n;
    String text;
    List<String> wordDict;
    public boolean wordBreak(String s, List<String> wordDict) {
        n = s.length();
        text = s;
        this.wordDict = wordDict;

        mem = new boolean[n + 1][n + 1];
//        for(int[] m: mem){
//            Arrays.fill(m, -1);
//        }
        return dp(n - 1, n, false);
    }

    public boolean dp(int i, int j, boolean lastHit){
        // base case
        if(i > j || i < 0){
            return lastHit; // was our last word in dict
        }

        if(mem[i][j]){
            return true;
        }

        String toSearch = text.substring(i, j);

        // found it
        if(wordDict.contains(toSearch)){
            mem[i][j] = true;
            return dp(i - 1, i, true); // search next word
        }
        else {
            mem[i][j] = false;
            return dp(i - 1, j, false); // move down a letter
        }

    }
}
