package Graph;
import java.util.*;

/***
 *  MST = Minimum Spanning Tree
 *
 *  Algorithms :
 *      1) Prim's :
 *          Time complexity : O ( V logE + E )
 *          Space Complexity : O ( E )
 *      2) Kruskal
 */

public class MinimumSpanningTree {

    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        ArrayList<Integer>[] graph = new ArrayList[n];

        for( int i = 0; i < n; i++ )
        {
            graph[i] = new ArrayList<>();
        }

        for( int[] edge : points )
        {
            graph[ edge[0] ].add( edge[1] );
            graph[ edge[1] ].add( edge[0] );
        }

        // TODO
        return 0;

    }

    public static void main(String[] args) {
        /*
            Prim's Algorithm
            https://leetcode.com/problems/min-cost-to-connect-all-points/
        */

    }
}
