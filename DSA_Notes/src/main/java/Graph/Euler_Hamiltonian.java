package Graph;

/***
 *  Walk : Any random traversal in graph. ( Vertices and edges can be repeated )
 *  Trail : A walk in which no edge is repeated ( Vertices can be repeated )
 *
 *  Euler Circuit : A trail which start and ends at same vertex.
 *
 *  Euler Graph : A graph which contains Euler Circuit.
 *      Condition :
 *          1. Every edge must be visited exactly once.
 *          2. Start == end
 *          3. Graph should not have multiple components, or if it had then only one component should have edges,
 *             rest other components vertices should have 0 degree.
 *          4. Adding Condition 1 & 2 : All node must have an even degree.
 *
 *   Euler Path : Every edge visit exactly once.
 *             ==> Euler circuit is euler path that starts and end at same vertex.
 *
 *   Semi - Eulerian Graph : A Eulerian Graph which is not Eulerian Circuit.
 *      Condition:
 *          1. Exactly two nodes should have odd degree.
 *
 *   ALGORITHM : To detect graph is Eulerian, Semi-Eulerian, Non-Eulerian
 *      1. Check there should be a single component in graph that should only have edges.
 *      2. Calculate the count of vertices that are having odd degree.
 *          a) count == 0 : Eulerian Graph
 *          b) count == 2 : Semi-eulerian
 *          c) count > 2 || count == 1 : Non eulerian
 *
 *      Time Complexity : O( V + E )
 */
public class Euler_Hamiltonian {
}
