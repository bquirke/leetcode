package leetcode.backtracking;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class kthLexiHappyString {

    @Test
    public void testkthLexiHappyString() {
        assertEquals("c", getHappyString(1, 3));
    }

    @Test
    public void testkthLexiHappyString2() {
        assertEquals("", getHappyString(1, 4));
    }

    /* LICC ATTEMPT - https://leetcode.com/problems/the-k-th-lexicographical-string-of-all-happy-strings-of-length-n/?envType=problem-list-v2&envId=ajcgkew2
        Backtrack
        - Have a,b,c as root of backtracks
        - base is curr.len == n
        - iterate over a,b,c in backtrack but check last of curr != new char
        - ans.has(k) ? ans.get(k) : ""

        - lexicographical order should be maintained by correct tree transversal
    */

    // FASTER THAN EDITORIAL !!
    // Adding optimisation to stop generating at k

    int limit;
    String result = "";
    public String getHappyString(int n, int k) {
        limit = k;
        List<String> ans = new ArrayList<>();
        backtrack(new StringBuilder(), n, ans);
        return result;
    }

    public void backtrack(StringBuilder curr, int n, List<String> ans) {
        if (curr.length() == n) {
            ans.add(curr.toString());
            if(ans.size() == limit){
                result = ans.get(limit - 1);
            }
            return;
        }

        for (Character newChar : new char[] { 'a', 'b', 'c' }) {
            if (!(curr.length() == 0)) {
                if (curr.charAt(curr.length() - 1) == newChar) {
                    continue;
                }
            }
            curr.append(newChar);
            backtrack(curr, n, ans);

            // check if we've found result
            if(!result.isEmpty()){
                return;
            }
            curr.deleteCharAt(curr.length() - 1);

        }
    }

    // /*
    //     Updated from editorial .... ABOVE IS ACTUALLY FASTER
    // */
    // public String getHappyString(int n, int k) {
    //     List<String> ans = new ArrayList<>();
    //     backtrack(new String(), n, ans);
    //     return ans.size() < k ? "" : ans.get(k - 1);
    // }

    // public void backtrack(String curr, int n, List<String> ans) {
    //     if (curr.length() == n) {
    //         ans.add(curr);
    //         return;
    //     }

    //     for (char currentChar = 'a'; currentChar <= 'c'; currentChar++) {
    //         if (curr.length() > 0) {
    //             if (curr.charAt(curr.length() - 1) == currentChar) {
    //                 continue;
    //             }
    //         }

    //         backtrack(curr + currentChar, n, ans);
    //         // dont need to undo! ... can keep going
    //     }
    // }
}
