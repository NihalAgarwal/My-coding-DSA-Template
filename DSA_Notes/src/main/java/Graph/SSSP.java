package Graph;
import java.util.*;

/**
 *  SSSP -> Single Source Shortest Path
 *
 *  The minimum distance from given source vertex to destination vertex or all other vertices.
 *
 *  Algorithm:
 *      1) BFS/DFS: In a graph, all edges are having equal or unit weight.
 *      2) 0/1 BFS : In a graph, if all edges having either 'a' or 'b' weight.
 *      3) Shortest Path in DAG : Using Topological Sort using DFS + edge relaxation.
 *      3) Dijkstra's algorithm : If all edge weights are >= 0. Without worsening the runtime complexity, this algorithm
 *                                can in fact compute the shortest paths from a given start point s to all other nodes.
 *      4) Bellman-Ford algorithm : In contrast to Dijkstra's algorithm, edge weights may be negative.
 *
 *      DAG - SHORTEST - PATHS (G, w, s)
 *          1. Topologically sort the vertices of G.
 *          2. INITIALIZE - SINGLE- SOURCE (G, s)
 *          3. for each vertex u taken in topologically sorted order
 *             4. do for each vertex v ∈ Adj [u]
 *          5. do RELAX (u, v, w)
 *
 *          Time Complexity : O ( V + E )
 *
 *      Dijkstra's :
 *          Time Complexity : O ( (V+E) log(V) )
 *          Space Complexity : O ( V )
 *
 *      Bellman ford :
 *          1. Work for directed graph, for undirected graph, you had to convert it into directed graph.
 *          2. Detects negative cycle in a graph.
 *          3.
 */

public class SSSP {
    public static final int[] neighbour_row = {-1, 0, 1, 0, -1, 1, 1, -1};
    public static final int[] neighbour_col = {0, 1, 0, -1, 1, 1, -1, -1};

    /***
     *
     * Dijkstra's Algorithm
     */
    public static int networkDelayTime(int[][] times, int n, int k) {
        ArrayList<int[]>[] graph = new ArrayList[n];

        for( int i = 0; i < n; i++ )
        {
            graph[i] = new ArrayList<>();
        }

        for( int[] time : times )
        {
            graph[ time[0]-1 ].add( new int[] { time[1]-1, time[2] } );
        }

        // { node, distance }
        PriorityQueue<int[]> q = new PriorityQueue<>( (a, b) -> a[1] - b[1] );
        int[] dist = new int[n];
        boolean[] vis = new boolean[n];

        Arrays.fill( dist, Integer.MAX_VALUE );

        dist[k-1] = 0; // source
        q.add( new int[] { k-1, 0} );

        int count = 0;

        while( !q.isEmpty() )
        {
            int cur = q.peek()[0];

            q.poll();

            if( vis[cur] )
                continue;

            vis[cur] = true;
            count++;

            for( int[] nb : graph[cur] )
            {
                int to = nb[0];
                int edge_w = nb[1];

                if( dist[cur] + edge_w < dist[to] )
                {
                    dist[to] = dist[cur] + edge_w;
                    q.add( new int[] { to, dist[cur] + edge_w} );
                }
            }
        }

        if( count < n )
            return -1;

        int max = 0;

        for( int d : dist )
        {
            max = Math.max( max, d);
        }

        return max;
    }

    public static int minimumEffortPath(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;

        int[][] dist = new int[m][n];

        for( int i = 0; i < m; i++ )
        {
            Arrays.fill( dist[i], Integer.MAX_VALUE );
        }

        boolean[][] vis = new boolean[m][n];

        PriorityQueue< int[] > q = new PriorityQueue<>( (a,b) -> a[2] - b[2] );
        q.add( new int[] { 0, 0, 0 } );
        dist[0][0] = 0;

        while( !q.isEmpty() )
        {
            int r = q.peek()[0];
            int c = q.peek()[1];
            q.poll();

            if( vis[r][c] )
                continue;

            vis[r][c] = true;

            for( int i = 0; i < 4; i++ )
            {
                int nr = r + neighbour_row[i];
                int nc = c + neighbour_col[i];

                if( nr >= 0 && nr < m && nc >= 0 && nc < n && !vis[nr][nc] )
                {
                    int next_dis = Math.max( dist[r][c], Math.abs( heights[nr][nc] - heights[r][c] ) );

                    if( next_dis < dist[nr][nc] )
                    {
                        dist[nr][nc] = next_dis;
                        q.add( new int[] { nr, nc, next_dis } );
                    }
                }
            }
        }

        return dist[m-1][n-1];
    }

    public static void main(String[] args) {
        /*
            Dijkstra's
            https://leetcode.com/problems/network-delay-time/
            https://leetcode.com/problems/path-with-minimum-effort/
        */
        System.out.println(networkDelayTime(new int[][]{{2, 1, 1}, {2, 3, 1}, {3, 4, 1}}, 4, 2)); // 2
        System.out.println( minimumEffortPath(new int[][]{{1, 2, 1, 1, 1}, {1, 2, 1, 2, 1}, {1, 2, 1, 2, 1}, {1, 2, 1, 2, 1}, {1, 1, 1, 2, 1}}) ); // 0
        System.out.println( minimumEffortPath(new int[][]{{1, 2, 2}, {3, 8, 2}, {5, 3, 5}}) ); // 2



    }
}
