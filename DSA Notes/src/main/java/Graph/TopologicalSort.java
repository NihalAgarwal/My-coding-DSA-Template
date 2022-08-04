package Graph;

import java.util.*;

/***
 *  Always work for DAG (Directed Acyclic Graph),
 */


public class TopologicalSort {

    /***
     *
     * Check if graph is DAG or not
     */
    public boolean isDAG( int v, ArrayList<Integer>[] graph, boolean[] vis, HashSet<Integer> withinRecursion )
    {
        vis[v] = true;
        withinRecursion.add( v );

        for( int nb : graph[v] )
        {
            if( withinRecursion.contains( nb ) )
                return false;

            if( !vis[nb] )
            {
                if( !isDAG( nb, graph, vis, withinRecursion ) )
                    return false;
            }

        }
        withinRecursion.remove( v );
        return true;
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        ArrayList<Integer>[] graph = new ArrayList[numCourses];

        for( int i = 0; i < numCourses; i++ )
            graph[i] = new ArrayList<>();

        for( int[] edge : prerequisites )
        {
            graph[ edge[1] ].add( edge[0] );
        }

        boolean[] vis = new boolean[numCourses];

        for( int i = 0; i < numCourses; i++ )
        {
            if( !vis[i] )
            {
                HashSet<Integer> set = new HashSet<>();
                if( !isDAG( i, graph, vis, set ) )
                    return false;

            }
        }
        return true;
    }

    /***
     *
     * Print the ordering of elements according to Topological sort, using DFS
     */
    public boolean topologicalSort( int v, ArrayList<Integer>[] graph, boolean[] vis, HashSet<Integer> inhold, Stack<Integer> st )
    {
        vis[v] = true;
        inhold.add( v );

        for( int nb : graph[v] )
        {
            if( inhold.contains( nb ) )
                return false;

            if( !vis[nb] )
            {
                if( !topologicalSort( nb, graph, vis, inhold, st ) )
                    return false;
            }

        }
        inhold.remove( v );
        st.push(v);

        return true;
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        ArrayList<Integer>[] graph = new ArrayList[numCourses];

        for( int i = 0; i < numCourses; i++ )
            graph[i] = new ArrayList<>();

        for( int[] edge : prerequisites )
        {
            graph[ edge[1] ].add( edge[0] );
        }

        boolean[] vis = new boolean[numCourses];
        Stack<Integer> st = new Stack<>();

        // Check if graph contains cycle or not and also find the ordering of elements according to Topological sort.
        // Here stack will store the vertices acc. to topological sort.
        // HashSet<> is used to detect the cycle in a Directed Graph
        for( int i = 0; i < numCourses; i++ )
        {
            if( !vis[i] )
                if( !topologicalSort( i, graph, vis, new HashSet<>(), st ) )
                    return new int[0];
        }

        int[] ans = new int[numCourses];
        int ind = 0;

        while( !st.isEmpty() )
        {
            ans[ind++] = st.pop();
        }

        return ans;
    }

    public int[] findOrderUsingKahn(int numCourses, int[][] prerequisites) {
        ArrayList<Integer>[] graph = new ArrayList[numCourses];
        int[] indegree = new int[numCourses];

        for( int i = 0; i < numCourses; i++ )
            graph[i] = new ArrayList<>();

        for( int[] edge : prerequisites )
        {
            graph[ edge[1] ].add( edge[0] );
            indegree[ edge[0] ]++;
        }

        Deque<Integer> q = new LinkedList<>();

        for( int i = 0; i < numCourses; i++ )
        {
            if( indegree[i] == 0 )
                q.addLast( i );
        }

        int[] ans = new int[numCourses];
        int ind = 0;

        while( !q.isEmpty() )
        {
            int v = q.pollFirst();
            ans[ind++] = v;

            for( int nb : graph[v] )
            {
                indegree[nb]--;
                if( indegree[nb] == 0 )
                    q.addLast( nb );
            }
        }

        return ind == numCourses ? ans : new int[0];
    }

    public static void main(String[] args) {
        TopologicalSort obj = new TopologicalSort();
        /*

         To Check if graph is DAG or not -> DFS or Kahn's Algorithm (BFS)
         https://leetcode.com/problems/course-schedule/

         Time Complexity : O ( V + E )
         Space Complexity : O( V )
         */
        System.out.println( obj.canFinish(5, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}}));

        /*

         Print topological sort using DFS.
         https://leetcode.com/problems/course-schedule-ii/submissions/

         Time Complexity : O ( V + E )
         Space Complexity : O( V )
         */
        int[] ans = obj.findOrder(5, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}} );
        System.out.println(Arrays.toString(ans));

        /*

         Print topological sort using BFS (Khan's Algorithm).
         With the help of Khan's algorithm we can check whether graph is DAG or not and print vertices acc. to Topological sort.
         https://leetcode.com/problems/course-schedule-ii/submissions/

         Time Complexity : O ( V + E )
         Space Complexity : O ( V )

         */
        ans = obj.findOrderUsingKahn(5, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}} );
        System.out.println(Arrays.toString(ans));
    }
}
