package app.src.test.java.leetcode.binarysearch;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class SpellsAndPotions {

    @Test
    public void testSpellsAndPotions() {
        int[] spells = new int[]{5,1,3};
        int[] potions = new int[]{1,2,3,4,5};

        int[] expected = new int[]{4,0,3};
        assertArrayEquals(expected, successfulPairs(spells, potions, 7));
    }

    @Test
    public void testSpellsAndPotions_Case2() {
        int[] spells = new int[]{3, 1, 2};
        int[] potions = new int[]{8, 5, 8};
        int success = 16;

        int[] expected = new int[]{2, 0, 2};
        assertArrayEquals(expected, successfulPairs(spells, potions, success));
    }

    // sort potions and iterate over spells
    // x = strength of spells
    // potion is compatible with spell if it has a strength of success/ x
    // search for insertion point of this stren in potions and
    //    you will have the amount of potions that can be paired
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        int m = potions.length;
        Arrays.sort(potions);
        int[] ans = new int[spells.length];


        for(int i = 0; i < spells.length; i++){
            int insertionPoint = binarySearch(potions, success / (double)spells[i]);
            ans[i] = m - insertionPoint;
        }

        return ans;
    }

    public int binarySearch(int[] potions, double target){
        int left = 0;
        int right = potions.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (potions[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }
}
