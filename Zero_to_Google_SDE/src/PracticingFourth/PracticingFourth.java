package PracticingFourth;

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

class Solution extends container {

}
public class PracticingFourth {

    public static void main(String[] args) {
<<<<<<< HEAD
        for(int i = 0; i < 100; i++){
            int l = i;
            int r = i+1;
            int mid = l + (r-l)/2;
            System.out.println(l + " + " + r + " :" + mid);
        }
        PracticingFourth obj = new PracticingFourth();
=======
        Solution obj = new Solution();
>>>>>>> parent of 2a1b08e (new changes)
    }
}
