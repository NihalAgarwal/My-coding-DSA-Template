package InterviewPrep.Module_4;

class container {
    public static final int[] neighbour_row = {-1, 0, 1, 0, -1, 1, 1, -1};
    public static final int[] neighbour_col = {0, 1, 0, -1, 1, 1, -1, -1};
    public static final int mod = 1000000007;

    public boolean check(int r, int c, int m, int n) {
        return r >= 0 && c >= 0 && r < m && c < n;
    }
}

class Solution extends container {

}

/*
//............................. ~DRIVER CLASS~ ...............................
 */

public class Java_4 {
    public static void main(String[] args) {
        Solution obj = new Solution();
    }
}