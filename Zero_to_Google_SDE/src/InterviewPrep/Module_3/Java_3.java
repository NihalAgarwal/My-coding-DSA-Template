package Module_3;
import java.util.*;

class container {
    public static final int[] neighbour_row = {-1, 0, 1, 0, -1, 1, 1, -1};
    public static final int[] neighbour_col = {0, 1, 0, -1, 1, 1, -1, -1};
    public static final int mod = 1000000009;
    public static final int INF = Integer.MAX_VALUE;
    public static final int N_INF = Integer.MIN_VALUE;

    public boolean check(int r, int c, int m, int n) {
        return r >= 0 && c >= 0 && r < m && c < n;
    }
}

public class Java_3 {
    public static final int[] neighbour_row = {-1, 0, 1, 0, -1, 1, 1, -1};
    public static final int[] neighbour_col = {0, 1, 0, -1, 1, 1, -1, -1};

    public boolean check(int r, int c, int m, int n) {
        return r >= 0 && c >= 0 && r < m && c < n;
    }

    public int helper( int r, int c, int m, int n, int[][] grid, Integer[][] memo, boolean[][] vis )
    {
        if( r == m-1 && c == n-1 )
            return memo[r][c] = 0;
        vis[r][c] = true;

        int min = 1000000;

//        if( memo[r][c] != null )
//            return memo[r][c];

        for( int i = 0; i < 4; i++ )
        {
            int nr = r + neighbour_row[i];
            int nc = c + neighbour_col[i];

            if( check( nr, nc, m, n) && !vis[nr][nc] )
            {
                min = Math.min( min, grid[r][c] + helper( nr, nc, m, n, grid, memo, vis ) );
            }
        }

        vis[r][c] = false;
        return memo[r][c] = min;
    }

    public int minimumObstacles(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        Integer[][] memo = new Integer[m][n];
        boolean[][] vis = new boolean[m][n];

        int temp = helper( 0, 0, m, n, grid, memo, vis );
        return temp;
    }

    public static void main(String[] args) {
        Java_3 obj = new Java_3();
        System.out.println(obj.minimumObstacles(new int[][]{{0,1,0,0,0},{0,1,0,1,0},{0,0,0,1,0}}));
        System.out.println(obj.minimumObstacles(new int[][]{{0, 1, 1}, {1, 1, 0}, {1, 1, 0}}));
    }
}

/*
[0,0,0,1]
[[1,5],[4,1],[1,3],[4,4]]
4
2
4
 */
