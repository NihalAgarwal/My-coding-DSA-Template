package PracticingFirst;


class container{
    public boolean check(int r, int c, int m, int n){
        return r >= 0 && c >= 0 && r < m && c < n;
    }
    public static final int[] neighbour_row = {-1, 0, 1, 0, -1, 1, 1, -1};
    public static final int[] neighbour_col = {0, 1, 0, -1, 1, 1, -1, -1};
    public static final int mod = 1000000009;
    public static final int INF = Integer.MAX_VALUE;
    public static final int N_INF = Integer.MIN_VALUE;
}

class Solution extends container{

}

/*
//............................. ~DRIVER CLASS~ ...............................
 */


public class PracticingFirst {

    public static void main(String[] args) {
        Solution obj = new Solution();
    }
}