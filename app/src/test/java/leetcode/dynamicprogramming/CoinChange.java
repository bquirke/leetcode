package leetcode.dynamicprogramming;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class CoinChange {
    /// top down
    @Test
    public void testCoinChangeTD() {
        assertEquals(3, coinChangeTD(new int[]{1,2,5}, 11));
    }


    // bottom up
    @Test
    public void testCoinChangeBU() {
        assertEquals(3, coinChangeBU(new int[]{1,2,5}, 11));
    }

    /* ATTEMPT .... but had to look at editorial for Solution
    https://leetcode.com/problems/coin-change/

        - f(s) => ret min number of coins to make change for amt S using coin denoms given
        - RR => F(s) = min(F(S - ci) + 1) while S - ci >= 0
        - base case => F(0) = 0 && F(S<0) = -1
    */

    // Top - d
    Map<Integer, Integer> mem =  new HashMap<>();
    public int coinChangeTD(int[] coins, int amount) {
        if (amount < 1) {
            return 0;
        }

        return dp(amount, coins);
    }

    public int dp(int remaining, int[] coins){
        // base case
        if(remaining == 0){
            return 0;
        }
        if(remaining < 0){
            return -1;
        }

        // cache check
        if(mem.containsKey(remaining)){
            return mem.get(remaining);
        }

        int min = Integer.MAX_VALUE;
        for(int coin : coins){

            // get remaining after taking away coin denom
            int result = dp(remaining - coin, coins);

            // check recurrnce relation bounds
            if(result >= 0 && result < min){
                min = result + 1;
            }
        }

        mem.put(remaining, (min == Integer.MAX_VALUE) ? -1 : min);

        return mem.get(remaining);
    }


    // bottom up
    public int coinChangeBU(int[] coins, int amount) {
        int max = amount + 1; //  + 1 to allow base case && [1, amount] states states

        int[] dp = new int[max];
        Arrays.fill(dp, max);

        // base case
        dp[0] = 0;
        // i is every possible amn between 1 and amount
        for(int i = 1; i <= amount; i++){
            for(int j = 0; j < coins.length; j++){
                int coin = coins[j];
                if(coin <= i){
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }

            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }

}
