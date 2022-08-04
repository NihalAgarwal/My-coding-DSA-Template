/***
 *
 * 1. Best Time to Buy and Sell Stock
 * 2. Best Time to Buy and Sell Stock II
 * 3. Best Time to Buy and Sell Stock III
 * 4. Best Time to Buy and Sell Stock IV
 * 5. Best Time to Buy and Sell Stock with Cooldown
 * 6. Best Time to Buy and Sell Stock with Transaction Fee
 *
 */

/***
 *
 * Recursion with Memoization
 */

class BuyAndSellStockUsingRecursion {

    /***
     *
     * @param prices An array prices where prices[i] is the price of a given stock on the ith day.
     * @param fee representing a transaction fee
     * @param k number of transaction allowed
     */
    public BuyAndSellStockUsingRecursion(int[] prices, int fee, int k )
    {
        int n = prices.length;
        Integer[][] memo = new Integer[n][2];

        BuyAndSellStocksII(0, n, prices, true, memo);
        BuyAndSellStocksWithCooldown(0, n, prices, true, memo);
        BuyAndSellStocksWithTransactionFee(0, n, prices, fee, true, memo);
        BuyAndSellStocksIII( 0, n, prices, true,2, new Integer[n][2][3] );
        BuyAndSellStocksIV(prices, k );
    }

    /***
     *
     * Best Time to Buy and Sell Stock II
     *
     */
    public int BuyAndSellStocksII(int ind, int n, int[] prices, boolean toBuy, Integer[][] memo)
    {
        if (ind >= n) {
            return 0;
        }

        if (memo[ind][toBuy ? 0 : 1] != null)
            return memo[ind][toBuy ? 0 : 1];

        int profit;

        if (toBuy) {
            profit = Math.max(-prices[ind] + BuyAndSellStocksII(ind + 1, n, prices, false, memo),
                    BuyAndSellStocksII(ind + 1, n, prices, true, memo));
        } else {
            profit = Math.max(prices[ind] + BuyAndSellStocksII(ind + 1, n, prices, true, memo),
                    BuyAndSellStocksII(ind + 1, n, prices, false, memo));
        }

        return memo[ind][toBuy ? 0 : 1] = profit;
    }

    /***
     *
     * Another way to solve - Best Time to Buy and Sell Stock II
     *
     */
    public int BuyAndSellStocksII_2(int ind, int n, int[] prices, int canBuy, Integer[][] memo)
    {
        if( canBuy == 4 || ind == n )
            return 0;

        if( memo[ind][canBuy] != null )
            return memo[ind][canBuy];

        int profit;

        if( canBuy % 2 == 0 )
        {
            profit = Math.max( -prices[ind] + BuyAndSellStocksII_2( ind + 1, n, prices, canBuy + 1, memo ),
                                              BuyAndSellStocksII_2( ind + 1, n, prices, canBuy, memo ) );
        }

        else
        {
            profit = Math.max( prices[ind] + BuyAndSellStocksII_2( ind + 1, n, prices, canBuy + 1, memo ),
                                             BuyAndSellStocksII_2( ind + 1, n, prices, canBuy, memo ) );
        }

        return memo[ind][canBuy] = profit;
    }

    /***
     *
     * Best Time to Buy and Sell Stock with Cooldown
     *
     */
    public int BuyAndSellStocksWithCooldown(int ind, int n, int[] prices, boolean toBuy, Integer[][] memo)
    {
        if (ind >= n) {
            return 0;
        }

        if (memo[ind][toBuy ? 0 : 1] != null)
            return memo[ind][toBuy ? 0 : 1];

        int profit;

        if (toBuy) {
            profit = Math.max(-prices[ind] + BuyAndSellStocksWithCooldown(ind + 1, n, prices, false, memo),
                    BuyAndSellStocksWithCooldown(ind + 1, n, prices, true, memo));
        } else {
            profit = Math.max(prices[ind] + BuyAndSellStocksWithCooldown(ind + 2, n, prices, true, memo),
                    BuyAndSellStocksWithCooldown(ind + 1, n, prices, false, memo));
        }

        return memo[ind][toBuy ? 0 : 1] = profit;
    }

    /***
     *
     * Best Time to Buy and Sell Stock with Transaction Fee
     *
     */
    public int BuyAndSellStocksWithTransactionFee(int ind, int n, int[] prices, int fee, boolean canBuy, Integer[][] memo)
    {
        if (ind == n)
            return 0;

        if (memo[ind][canBuy ? 0 : 1] != null)
            return memo[ind][canBuy ? 0 : 1];

        int profit;

        if (canBuy) {
            profit = Math.max(-prices[ind] + BuyAndSellStocksWithTransactionFee(ind + 1, n, prices, fee, false, memo),
                    BuyAndSellStocksWithTransactionFee(ind + 1, n, prices, fee, true, memo));
        } else {
            profit = Math.max(prices[ind] - fee + BuyAndSellStocksWithTransactionFee(ind + 1, n, prices, fee, true, memo),
                    BuyAndSellStocksWithTransactionFee(ind + 1, n, prices, fee, false, memo));
        }

        return memo[ind][canBuy ? 0 : 1] = profit;
    }

    /***
     *
     * Best Time to Buy and Sell Stock III
     *
     */
    public int BuyAndSellStocksIII( int ind, int n, int[] prices, boolean canBuy, int c, Integer[][][] memo )
    {
        if( c == 0 || ind == n )
            return 0;

        if( memo[ind][canBuy ? 0 : 1][c] != null )
            return memo[ind][canBuy ? 0 : 1][c];

        int profit;

        if( canBuy )
        {
            profit = Math.max( -prices[ind] + BuyAndSellStocksIII( ind + 1, n, prices, false, c, memo ),
                    BuyAndSellStocksIII( ind + 1, n, prices, true,  c, memo ) );
        }

        else
        {
            profit = Math.max( prices[ind] + BuyAndSellStocksIII( ind + 1, n, prices, true, c - 1, memo ),
                    BuyAndSellStocksIII( ind + 1, n, prices, false,  c, memo ) );
        }

        return memo[ind][canBuy ? 0 : 1][c] = profit;
    }

    /***
     *
     * Best Time to Buy and Sell Stock IV
     *
     */
    public int BuyAndSellStocksIV(int[] prices, int k )
    {
        // TODO
        return 0;
    }
}

/***
 *
 * Tabulation
 */

class BuyAndSellStockUsingTabulation {

    /***
     *
     * @param prices An array prices where prices[i] is the price of a given stock on the ith day.
     * @param fee representing a transaction fee
     * @param k number of transaction allowed
     */
    public BuyAndSellStockUsingTabulation(int[] prices, int fee, int k)
    {
        BuyAndSellStocksII(prices);
        BuyAndSellStocksWithCooldown(prices);
        BuyAndSellStocksWithTransactionFee(prices, fee);
        BuyAndSellStocksIII(prices);
        BuyAndSellStocksIV(prices, k);
    }

    /***
     *
     * Best Time to Buy and Sell Stock II
     *
     */
    public int BuyAndSellStocksII(int[] prices)
    {
        int n = prices.length;
        int[][] memo = new int[n][2];
        memo[0][0] = -prices[0];

        for (int i = 1; i < n; i++) {
            memo[i][0] = Math.max(memo[i - 1][1] - prices[i], memo[i - 1][0]);
            memo[i][1] = Math.max(memo[i - 1][0] + prices[i], memo[i - 1][1]);
        }

        return memo[n - 1][1];
    }

    /***
     *
     * Best Time to Buy and Sell Stock with Cooldown
     *
     */
    public int BuyAndSellStocksWithCooldown(int[] prices)
    {
        int n = prices.length;
        int[][] memo = new int[n][2];
        memo[0][0] = -prices[0];

        for (int i = 1; i < n; i++) {
            memo[i][0] = Math.max((i == 1 ? 0 : memo[i - 2][1]) - prices[i], memo[i - 1][0]);
            memo[i][1] = Math.max(memo[i - 1][0] + prices[i], memo[i - 1][1]);
        }

        return memo[n - 1][1];
    }

    /***
     *
     * Best Time to Buy and Sell Stock with Transaction Fee
     *
     */
    public int BuyAndSellStocksWithTransactionFee(int[] prices, int fee)
    {
        int n = prices.length;
        int old_buy = -prices[0], old_sell = 0;

        for (int i = 1; i < n; i++) {
            int new_buy = Math.max(old_sell - prices[i], old_buy);
            int new_sell = Math.max(old_buy + prices[i] - fee, old_sell);

            old_buy = new_buy;
            old_sell = new_sell;
        }

        return old_sell;
    }

    /***
     *
     * Best Time to Buy and Sell Stock III
     *
     */
    public int BuyAndSellStocksIII(int[] prices)
    {
        // TODO
        return 0;
    }

    /***
     *
     * Best Time to Buy and Sell Stock IV
     *
     */
    public int BuyAndSellStocksIV(int[] prices, int k )
    {
        // TODO
        return 0;
    }
}


public class Buy_and_sell_stocks {
    public static void main(String[] args) {
        int[] prices = new int[] {};
        int fee = 0;
        int k = 0;
        BuyAndSellStockUsingRecursion rec_obj = new BuyAndSellStockUsingRecursion( prices, fee, k );
        BuyAndSellStockUsingTabulation tab_obj = new BuyAndSellStockUsingTabulation( prices, fee, k );
    }
}
