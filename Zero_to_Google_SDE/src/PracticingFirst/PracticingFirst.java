package PracticingFirst;

<<<<<<< HEAD
=======
import java.util.Deque;
import java.util.LinkedList;
>>>>>>> parent of 2a1b08e (new changes)

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
    public int minimumJumps(int[] forbidden, int a, int b, int x) {
        if( x == 0 ) return 0;
        // 1 forward
        // 0 backward
        int size = x+a+b+1;

        for(int i : forbidden)
            size = Math.max(size, i+a+b+1);

        int[][] dp = new int[size][2];

        for(int[] i : dp)
            i[0] = i[1] = INF;

        for(int i : forbidden )
            dp[i] = null;

        dp[0][0] = 0;
        dp[0][1] = 0;
        Deque<int[]> q = new LinkedList<>();
        q.add(new int[] {0, 0});

        while( !q.isEmpty()){
            int s = q.size();

            while (s-- > 0 ){
                int y = q.peekFirst()[0];
                int dir = q.peekFirst()[1];
                q.pollFirst();

                if( y == x) return dp[y][dir];

                helper(y, a, 1, dir, q, dp);
                if( dir == 0 || y-b < 0) continue;
                helper(y, -b, 0, dir, q, dp);
            }
        }
        return -1;
    }
    public void helper(int y, int v, int move, int dir, Deque<int[]> q, int[][] dp){
        if( y+v < dp.length && dp[y+v] != null && dp[y+v][move] > dp[y][dir]+1 ){
            q.addLast(new int[]{y+v, move});
            dp[y+v][move] = dp[y][dir]+1;
        }
    }
}

/*
//............................. ~DRIVER CLASS~ ...............................
 */
public class PracticingFirst {

    public static void main(String[] args) {
        Solution obj = new Solution();
        int[] ar = {162,118,178,152,167,100,40,74,199,186,26,73,200,127,30,124,193,84,184,36,103,149,153,9,54,154,133,95,45,198,79,157,64,122,59,71,48,177,82,35,14,176,16,108,111,6,168,31,134,164,136,72,98};
        System.out.println(obj.minimumJumps(ar, 29,98,80));
        ar = new int[] {1911, 1670};
        System.out.println(obj.minimumJumps(ar, 925,1464,173));
    }
}