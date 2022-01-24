package PracticingSecond;

<<<<<<< HEAD
import Helper.ListNode;
import Helper.MyLinkedList;

import java.util.Arrays;

class container{
    public boolean check(int r, int c, int m, int n){
        return r >= 0 && c >= 0 && r < m && c < n;
    }
    public static final int[] neighbour_row = {-1, 0, 1, 0, -1, 1, 1, -1};
    public static final int[] neighbour_col = {0, 1, 0, -1, 1, 1, -1, -1};
    public static final int mod = 1000000007;

=======
class container{
    public static final int[] neighbour_row = {-1, 0, 1, 0, -1, 1, 1, -1};
    public static final int[] neighbour_col = {0, 1, 0, -1, 1, 1, -1, -1};
    public static final int mod = 1000000009;
>>>>>>> parent of 2a1b08e (new changes)
    public static final int INF = Integer.MAX_VALUE;
    public static final int N_INF = Integer.MIN_VALUE;
}

<<<<<<< HEAD
public class PracticingSecond {

    public int fact (int val){
        int res = 0;

        while(val > 0){
            res += val--;
        }

        return res;
    }

    public int helper(int[] ar1, int[] ar2){
        int res = 0;
        int n = ar2.length;

        for(int i : ar1){
            long val = (long) i * i;
            int l = 0;
            int r = n-1;
            while(l < r){
                long temp = (long) ar2[l] * ar2[r];
                if(temp == val){
                    if( ar2[l] == ar2[r]){
                        res += fact(r-l);
                        break;
                    }
                    int count1 = 0;
                    int count2 = 0;
                    int tt = ar2[l];
                    while( l < r && ar2[l] == tt){
                        count1++;
                        l++;
                    }
                    tt = ar2[r];
                    while( l <= r && ar2[r] == tt){
                        count2++;
                        r--;
                    }
                    res += (count1*count2);
                }
                else if( temp > val){
                    int tt = ar2[r];
                    while( l < r && ar2[r] == tt)
                        r--;
                }
                else{
                    int tt = ar2[l];
                    while( l < r && ar2[l] == tt)
                        l++;
                }
            }
        }
        return res;
    }
=======
class Solution extends container {

}

/*
//............................. ~DRIVER CLASS~ ...............................
 */
public class PracticingSecond {
>>>>>>> parent of 2a1b08e (new changes)

    public int numTriplets(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        return helper(nums1, nums2) + helper(nums2, nums1);
    }

    public static void main(String[] args) {
<<<<<<< HEAD
        PracticingSecond obj = new PracticingSecond();
        System.out.println(obj.numTriplets(new int[]{1, 1}, new int[]{1, 1, 1}));
        System.out.println(obj.numTriplets(new int[]{4,1,4,1,12}, new int[]{3,2,5,4}));
=======
        Solution obj = new Solution();
>>>>>>> parent of 2a1b08e (new changes)
    }
}