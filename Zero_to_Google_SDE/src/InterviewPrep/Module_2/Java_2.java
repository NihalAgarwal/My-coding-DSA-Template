package Module_2;

import Helper_Classes.Print2dArray;

import java.util.*;

class container {
    public static final int[] neighbour_row = {-1, 0, 1, 0, -1, 1, 1, -1};
    public static final int[] neighbour_col = {0, 1, 0, -1, 1, 1, -1, -1};
    public static final int mod = 1000000007;
    public static final int INF = Integer.MAX_VALUE;
    public static final int N_INF = Integer.MIN_VALUE;

    public boolean check(int r, int c, int m, int n) {
        return r >= 0 && c >= 0 && r < m && c < n;
    }
}

class Solution extends container {
    static class Node
    {
        Node[] child = new Node[26];
        int end = 0;
    }

    Node root;

    public void addToTrie( String word )
    {
        Node temp = root;
        for( char c : word.toCharArray() )
        {
            if( temp.child[c-'a'] == null )
            {
                temp.child[c-'a'] = new Node();
            }
            temp = temp.child[c-'a'];
        }
        temp.end++;
    }

    public int find( Node root, int i, HashMap<Character, Deque<Integer>> map )
    {

        int ans = root.end;

        for( char c = 'a'; c <= 'z'; c++ )
        {
            if( root.child[c-'a'] == null )
                continue;
            Deque<Integer> q = map.getOrDefault( c, new LinkedList<Integer>() );
            if( q.size() != 0 && q.peekFirst() >= i )
            {
                int val = q.pollFirst();
                ans += find( root.child[c-'a'], val + 1, map );
                q.addFirst( val );
            }
        }

        return ans;
    }

    public int numMatchingSubseq(String s, String[] words) {
        int sn = s.length();
        root = new Node();
        HashMap<Character, Deque<Integer>> map = new HashMap<>();


        for( int i = 0; i < sn; i++ )
        {
            map.computeIfAbsent( s.charAt(i), v -> new LinkedList<>() ).addLast( i );
        }

        for( String word : words )
        {
            addToTrie( word );
        }

        return find( root, 0, map );

    }
}

public class Java_2 {
    public static void main(String[] args) {
        Solution obj = new Solution();
        System.out.println(obj.numMatchingSubseq("dsahjpjauf", new String[]{"ahjpjau", "ja", "ahbwzgqnuk", "tnmlanowax"}));
    }
}


/*

3  4, 1 1
 3 2 = 9 + 4 = 13

2 3 = 4 + 9 = 13

num1/den1 != num2/den2

num1
 */