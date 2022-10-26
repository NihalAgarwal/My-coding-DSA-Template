package DSA_Notes.String_Matching;

/***
 * KMP - Knuth Morris Pattern Match Algorithm
 * Time Complexity : O( N + M ), where N is length of text and M is length of pattern.
 * Space Complexity : O( M )
 *
 * To Learn : https://www.youtube.com/watch?v=4jY57Ehc14Y
 *
 * To solve more problems : <a href="https://leetcode.com/list/55qfmewg/">...</a>
 *
 * LPS (Longest Prefix Suffix) : the longest prefix which is equals to suffix of a string.
 * Ex: "abcdefabc" -> LPS : "abc", because "abc..def..abc"
 * Ex: "aaabcaaa" -> LPS : "aaa", not "aa" or "a"
 *
 * Step :
 *      a) Calculate the LPS array for "pattern" string for every substring[0..i] ( i -> 1 to n-1 ) and store it at dp[i].
 *      b) Using two pointer ( one at "text" string and other pointer at "pattern" ), with the help of LPS array (dp).
 */

public class KMP {

    public int[] LPS( String s )
    {
        int n = s.length();

        int[] LongestPrefixSuffix = new int[n];

        int i = 0;
        for( int j = 1; j < n; j++ )
        {
            while( i > 0 && s.charAt(j) != s.charAt(i) )
            {
                i = LongestPrefixSuffix[i-1];
            }

            if( s.charAt(i) == s.charAt(j) )
            {
                LongestPrefixSuffix[j] = ++i;
            }
        }

        return LongestPrefixSuffix;
    }

    public void KMP( String s )
    {
        int[] LongestprefixSuffix = LPS( s );

    }
}
