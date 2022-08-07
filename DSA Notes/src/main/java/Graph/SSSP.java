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
 */

public class SSSP {

    public static void main(String[] args) {

    }
}
