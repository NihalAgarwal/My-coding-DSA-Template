package DSA_Notes.Graph;

import java.util.*;

/***
 *
 */

public class BridgesAndArticulationPoint {

    int timer; // time when discover each vertex

    public void findBridge( int v, int parent, boolean[] vis, int[] start, int[] low, ArrayList<Integer>[] graph, List<List<Integer>> bridges )
    {
        vis[v] = true;
        start[v] = low[v] = timer++;  // discover u

        for( int nb : graph[v] )
        {
            if( nb == parent )
                continue;

            if( !vis[nb] )
            {
                findBridge( nb, v, vis, start, low, graph, bridges );

                if( start[v] < low[nb] ) // check for a bridge
                {
                    bridges.add( Arrays.asList( v, nb ) );
                }
            }

            low[v] = Math.min( low[v], low[nb] );
        }
    }

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        timer = 1;
        List<List<Integer>> bridges = new ArrayList<>();

        ArrayList<Integer>[] graph = new ArrayList[n];

        for( int i = 0;i < n; i++ )
            graph[i] = new ArrayList<>();

        for( List<Integer> edge : connections )
        {
            graph[ edge.get(0) ].add( edge.get(1) );
            graph[ edge.get(1) ].add( edge.get(0) );
        }

        boolean[] vis = new boolean[n];
        int[] start = new int[n];
        int[] low = new int[n];

        for( int v = 0; v < n; v++ )
        {
            if( !vis[v] )
            {
                findBridge( v, -1, vis, start, low, graph, bridges );
            }
        }

        return bridges;
    }

    public static void main(String[] args) {
        BridgesAndArticulationPoint obj = new BridgesAndArticulationPoint();

        /*
           Find Bridges in a graph.
           Time Complexity : O ( V + E )
           Space Complexity : O ( V )

           https://leetcode.com/problems/critical-connections-in-a-network/
        */
        List<List<Integer>> edges = new ArrayList<>();
        edges.add( Arrays.asList( 0, 1 ) );
        edges.add( Arrays.asList( 1, 2 ) );
        edges.add( Arrays.asList( 2, 0 ) );
        edges.add( Arrays.asList( 1, 3 ) );

        System.out.println(obj.criticalConnections(4, edges));

        /*
           Find Articulation point in a graph.
           Time Complexity : O ( V + E )
           Space Complexity : O ( V )

           https://leetcode.com/problems/critical-connections-in-a-network/
        */

    }
}
