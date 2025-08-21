package leetcode.dynamicprogramming.multiD;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class BuySellStock4 {

    @Test
    public void testSelling1() {
        assertEquals(2, maxProfit(2, new int[]{2,4,1}));
    }

    @Test
    public void testSelling2() {
        assertEquals(7, maxProfit(2, new int[]{3,2,6,5,0,3}));
    }

    /* DAS Course
        - dp(i, holding, remain) rets the max profit for ith day with holding bool
            and remain trans left

        - RR => max(skip, buy , sell) options
            * skip = dp(i + 1, holding, remain)
            * buy = dp(i + 1, true, remain)
            * sell = dp(i + 1, false, remain -1)

        - base case => dp(prices.length) = dp(remain = 0) = 0
    */

    int n;
    int[][][] mem;
    int[] prices;

    //top down
    public int maxProfitTD(int k, int[] prices) {
        n = prices.length;
        this.prices = prices;

        mem = new int[n][2][k + 1];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < 2; j++){
                Arrays.fill(mem[i][j], -1);
            }
        }

        return dp(0, 0, k);
    }

    public int dp(int i, int holding, int remain){
        // base case
        if(i == n || remain == 0){
            return 0;
        }

        if(mem[i][holding][remain] != -1){
            return mem[i][holding][remain];
        }

        // skip
        int ans = dp(i + 1, holding, remain);

        if(holding == 0){
            // buy
            ans = Math.max(ans, dp(i + 1, 1, remain) - prices[i]);
        }
        else {
            // sell
            ans = Math.max(ans, dp(i + 1, 0, remain - 1) + prices[i]);
        }

        mem[i][holding][remain] = ans;

        return ans;
    }

    public int maxProfit(int k, int[] prices) {
        n = prices.length;
        int[][][] dp = new int[n + 1][2][k + 1];

        for(int i = n -1; i >= 0; i--){
            for(int remain = 1; remain <= k; remain++){
                for(int holding = 0; holding < 2; holding++){
                    // skip ...default
                    int ans  = dp[i + 1][holding][remain];

                    // buying
                    if(holding == 0){
                        ans = Math.max(ans, dp[i + 1][1][remain] - prices[i]);
                    }
                    //selling
                    else {
                        ans = Math.max(ans, dp[i + 1][0][remain - 1] + prices[i]);
                    }

                    dp[i][holding][remain] = ans;
                }
            }
        }

        return dp[0][0][k];
    }
}
