package DSA_Notes;

/***
 *
 * <a href="https://leetcode.com/problems/count-of-smaller-numbers-after-self/">...</a>
 *
 * You are given an integer array nums and you have to return a new counts array.
 * The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].
 *
 * Note : The below solution helps us in that type of problems where, for current element we had to fetch the details
 * of all elements either on left part or either the right part of the array.
 */

import java.util.*;
import java.util.stream.Collectors;

public class Count_of_smaller_elements_to_right_in_array {
    int[] countAr;

    static class Pair
    {
        int val;
        int index;

        Pair ( int val, int index )
        {
            this.val = val;
            this.index = index;
        }
    }

    public Pair[] solve( int left, int right, Pair[] ar )
    {
        if( left == right )
        {
            return new Pair[] { ar[left] };
        }

        Pair[] res = new Pair[right-left+1];

        int mid = (left + right)/2;

        Pair[] leftAr = solve( left, mid, ar );
        Pair[] rightAr = solve( mid + 1, right, ar );

        int l = 0;
        int r = 0;
        int k = 0;

        while( l < leftAr.length && r < rightAr.length )
        {
            if( leftAr[l].val <= rightAr[r].val )
            {
                res[k++] = leftAr[l];
                countAr[ leftAr[l++].index ] += r;
            }
            else
            {
                res[k++] = rightAr[r++];
            }
        }

        while( l < leftAr.length )
        {
            res[k++] = leftAr[l];
            countAr[ leftAr[l++].index ] += r;
        }

        while( r < rightAr.length )
        {
            res[k++] = rightAr[r++];
        }

        return res;
    }

    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        Pair[] ar = new Pair[n];
        countAr = new int[n];

        for( int i = 0; i < n; i++ )
        {
            ar[i] = new Pair( nums[i], i );
        }

        solve( 0, n-1, ar );

        return Arrays.stream( countAr )
                .boxed()
                .collect(Collectors.toList());
    }
}
