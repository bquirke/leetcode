package leetcode.mockinterview.sean0209;

import leetcode.helpers.*;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class ReverseOnlyLetters {

    @Test
    public void testReverseOnlyLetters() {
        assertEquals("ab-cd", reverseOnlyLetters("dc-ba"));
    }

    @Test
    public void testReverseOnlyLetters2() {
        assertEquals("j-Ih-gfE-dCba", reverseOnlyLetters("a-bC-dEf-ghIj"));
    }

    // ATTEMPT
    public String reverseOnlyLetters(String s) {
        int left = 0;
        int right = s.length() - 1;
        char[] word = s.toCharArray();

        while (left < right) {
            char l = word[left];
            char r = word[right];

            boolean isCharL = isAsciiLetter(l);
            boolean isCharR = isAsciiLetter(r);

            if(isCharL && isCharR){
                word[left++] = r;
                word[right--] = l;
            } else if (isCharL) {
                right--;
            }
            else if (isCharR){
                left++;
            }
            else {
                left++;
                right--;
            }
        }

        return new String(word);
    }

    public static boolean isAsciiLetter(char c) {
        return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z');
    }
}
