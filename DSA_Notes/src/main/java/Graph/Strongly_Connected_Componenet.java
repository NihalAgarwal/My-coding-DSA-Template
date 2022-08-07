package Graph;

import java.util.*;

/***
 *                    ╻━━━━━━━━━➜0━━━━━━━━━╻
 *                    ┃       🡷    🡶       ┃
 *                    ┃      1      2      ┃
 *                    ┃     🡷 🡶   🡷 🡶     ┃
 *                    ╹━━━ 3   4  5   6<━━━╹
 *                             ╹-╹
 *      Tree Edge : (u,v) -> Parent-child relationship. (0,1), (0,2), (1,3), (2,5) are examples of tree edge.
 *      Forward edge : (u,v) -> ancestor-descendant relationship. (0,6) is a forward edge.
 *      Backward edge : (u,v) -> descendant-ancestor relationship. (3,0) is a backward edge.
 *      Cross edge : (u,v) -> No ancestor-descendant relationship. (4,5) is a cross edge.
 *
 *      Stack is required to keep track of nodes present in SCC. Traverse the graph and for every newly discovered node
 *      put it in a stack, and pop it when that vertex is totally traversed.
 *      * If edges is pointing to a visited node.
 *          1) if it is already in stack then it's a back edge,
 *          2) else cross edge.
 *      * Cross edge is the one which connects two SCC (Strongly Connected Component.)
 *      * In a graph, if we consider every SCC as a node, then the formed graph will be DAG, where every edge in a
 *        graph will be cross edge.
 *
 *      Tarjan's Algo:
 *          1) Low and disc are used to identify back edges.
 *          2) If 'u' is the current vertex and 'v' is the neighbour vertex.
 *             For back edge,                      low[u] = min( low[u], disc[v] )
 *             For other edges except cross-edge : low[u] = min( low[u], low[v] ).
 *             For cross edge, nothing to do, as cross-edge is the one which connects two SCC.
 *
 *          3) Maintain a "stack" to push the elements who are currently in hold on ongoing recursion call or dfs call.
 *          4) Maintain a "Present_in_stack" array, which helps to know which element is present in a stack.
 *          5) After completely traverse a node, check:
 *              a) if disc[u] == low[u], then this is the head of one SSC, so start popping out elements from stack
 *                 and also mark it as false in "Present_in_stack" array, unless you found out 'u' in stack.
 *              * The elements or vertices which was popped out form the stack, was a part of an SCC whose head is 'u'.
 *
 *      Kosaraju's Algo:
 *
 *
 *
 */

public class Strongly_Connected_Componenet {
    int time;
    public void FindSSCTarjans( int v, ArrayList<ArrayList<Integer>> adj, int[] disc, int[] low, boolean[] withStack, Stack<Integer> st, ArrayList<ArrayList<Integer>> res )
    {
        disc[v] = low[v] = time++;
        st.push(v);
        withStack[v] = true;

        for( int nb : adj.get(v) )
        {
            if( disc[nb] == -1 ) // any edge, except cross and back edge
            {
                FindSSCTarjans( nb, adj, disc, low, withStack, st, res );
                low[v] = Math.min( low[v], low[nb] );
            }
            else if ( withStack[nb] ) // back edge, 'nb' is within stack
            {
                low[v] = Math.min( low[v], disc[nb] );
            }
        }

        if( disc[v] == low[v] )
        {
            ArrayList<Integer> temp = new ArrayList<>();
            while( !st.isEmpty() && st.peek() != v )
            {
                int val = st.pop();
                withStack[val] = false;
                temp.add( val );
            }
            int val = st.pop();
            withStack[val] = false;
            temp.add( val );

            // Not required until you want vertices in SCC in ascending order
            Collections.sort( temp );

            res.add( temp );
        }
    }

    public ArrayList<ArrayList<Integer>> tarjans(int V, ArrayList<ArrayList<Integer>> adj)
    {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        time = 0;

        int[] disc = new int[V];
        int[] low = new int[V];
        boolean[] withinStack = new boolean[V];
        Stack<Integer> st = new Stack<>();

        Arrays.fill( disc, -1 );
        Arrays.fill( low, -1 );

        for( int v = 0; v < V; v++ )
        {
            if( disc[v] == -1 )
                FindSSCTarjans( v, adj, disc, low, withinStack, st, res );
        }

        // Not required until you want all SCC in lexicographically order
        res.sort(new Comparator<ArrayList<Integer>>() {
            @Override
            public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
                return o1.get(0).compareTo(o2.get(0));
            }
        });

        return res;
    }

    public int kosaraju(int V, ArrayList<ArrayList<Integer>> adj)
    {
        // TODO
        return 0;
    }

    public static void main(String[] args) {
        Strongly_Connected_Componenet obj = new Strongly_Connected_Componenet();

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        int number_of_vertices = 8;

        for( int i = 0; i < number_of_vertices; i++ )
            graph.add( new ArrayList<>() );

        graph.get(4).add(4);
        graph.get(3).add(1);
        graph.get(0).add(2);
        graph.get(6).add(3);
        graph.get(6).add(5);
        graph.get(1).add(4);
        graph.get(1).add(7);
        graph.get(3).add(7);
        graph.get(1).add(0);
        graph.get(3).add(3);
        graph.get(4).add(3);
        graph.get(1).add(4);
        graph.get(7).add(6);

        /*
            Tarjan's Algorithm
            https://practice.geeksforgeeks.org/problems/strongly-connected-component-tarjanss-algo-1587115621/1

            Time complexity : O ( V + E )
            Space Complexity : O ( V )
        */

        System.out.println(obj.tarjans(number_of_vertices, graph));

        /*
            Kosarajus's Algorithm
            https://practice.geeksforgeeks.org/problems/strongly-connected-components-kosarajus-algo/1

            Time complexity : O ( )
            Space Complexity : O (  )
        */

        System.out.println(obj.kosaraju(number_of_vertices, graph));
    }
}
