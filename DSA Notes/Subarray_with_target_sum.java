/***
 * <a href="https://leetcode.com/problems/number-of-submatrices-that-sum-to-target/">...</a>
 *
 * Problem Statement :
 *  Given a matrix and a target, return the number of non-empty matrices that sum to target.
 *  A sub-matrix x1, y1, x2, y2 is the set of all cells' matrix[x][y] with x1 <= x <= x2 and y1 <= y <= y2.
 *  Two sub-matrices (x1, y1, x2, y2) and (x1', y1', x2', y2') are different if they have some coordinate
 *  that is different: for example, if x1 != x1'.
 *
 */

import java.util.*;

class Scratch {

    public static void main(String[] args) {

    }

    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;

        int[][] prefixSum = new int[m][n];
        //  [1, 2, 3]       [1, 2, 3 ]
        //  [2, 3, 4]  ==>  [3, 5, 7 ]
        //  [1, 4, 6]       [4, 9, 13]
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                prefixSum[r][c] = matrix[r][c] + (r > 0 ? prefixSum[r - 1][c] : 0);
            }
        }

        int ans = 0;

        for (int r1 = 0; r1 < m; r1++) {
            for (int r2 = r1; r2 < m; r2++) {
                HashMap<Integer, Integer> map = new HashMap<>();
                map.put(0, 1);
                int sum = 0;

                for (int c = 0; c < n; c++) {
                    sum += prefixSum[r2][c] - (r1 > 0 ? prefixSum[r1 - 1][c] : 0);
                    ans += map.getOrDefault(sum - target, 0);
                    map.compute(sum, (k, v) -> v == null ? 1 : v + 1);
                }
            }
        }

        return ans;

        // Time : O(N^3)
        // Space : O(N*N)
    }
}