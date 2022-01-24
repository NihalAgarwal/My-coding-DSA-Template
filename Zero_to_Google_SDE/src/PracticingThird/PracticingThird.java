package PracticingThird;

<<<<<<< HEAD
import java.util.*;

=======
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

<<<<<<< HEAD
public class PracticingThird {
    public static final int[] neighbour_row = {-1, 0, 1, 0, -1, 1, 1, -1};
    public static final int[] neighbour_col = {0, 1, 0, -1, 1, 1, -1, -1};

    public boolean check(int[][] grid, int r, int c, int m, int n, int[][] dist){
        if( r < 0 || r >= m || c < 0 || c >= n)
            return false;
        return grid[r][c] != 0 && dist[r][c] == Integer.MAX_VALUE;
    }

    public List<List<Integer>> highestRankedKItems(int[][] grid, int[] pricing, int[] start, int k) {
        int m = grid.length;
        int n = grid[0].length;
        List<List<Integer>> res = new ArrayList<>();

        if( grid[start[0]][start[1]] == 0)
            return res;

        int[][] dist = new int[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++)
                dist[i][j] = Integer.MAX_VALUE;
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if( dist[o1[0]][o1[1]] != dist[o2[0]][o2[1]]){
                    return dist[o2[0]][o2[1]] - dist[o1[0]][o1[1]];
                }
                else if( grid[o1[0]][o1[1]] != grid[o2[0]][o2[1]]){
                    return grid[o2[0]][o2[1]] - grid[o1[0]][o1[1]];
                }
                else if( o1[0] != o2[0])
                    return o2[0] - o1[0];
                return o2[1] - o1[1];
            }
        });

        Deque<int[]> q = new LinkedList<>();

        q.add(start);
        dist[start[0]][start[1]] = 0;


        if(grid[start[0]][start[1]] >= pricing[0] && grid[start[0]][start[1]] <= pricing[1]){
            pq.add(start);
        }

        while(!q.isEmpty()){
            int r = q.peek()[0];
            int c = q.peek()[1];
            q.poll();

            for(int i = 0; i < 4; i++){
                int nr = r + neighbour_row[i];
                int nc = c + neighbour_col[i];

                if( !check(grid, nr, nc, m, n, dist))
                    continue;

                q.add(new int[]{nr, nc});
                dist[nr][nc] = dist[r][c] + 1;

                if(grid[nr][nc] != 1 && grid[nr][nc] >= pricing[0] && grid[nr][nc] <= pricing[1] ){
                    pq.add(new int[] {nr, nc});
                    if( pq.size() > k)
                        pq.poll();
                }
            }
        }

        while(!pq.isEmpty()){
            int[] temp = pq.poll();
            res.add(Arrays.asList(temp[0], temp[1]));
        }
        Collections.reverse(res);

        return res;
    }

    public static void main(String[] args) {
        PracticingThird obj = new PracticingThird();
        System.out.println(obj.highestRankedKItems(new int[][]{{1, 2, 0, 1}, {1, 3, 3, 1}, {0, 2, 5, 1}}, new int[]{2, 5}, new int[]{0, 0}, 3));
=======
class Solution extends container {

}
public class PracticingThird {

    public static void main(String[] args) {
        Solution obj = new Solution();
>>>>>>> parent of 2a1b08e (new changes)
    }
}
