package Stack_Queue_Hash_Heap;
import java.util.*;
/***
 *  LeetCode : https://leetcode.com/problems/longest-valid-parentheses/solution/
 *
 */

public class Longest_Valid_Parentheses {

    public static int method1 ( String s )
    {
        int maxans = 0;
        Stack<Integer> stack = new Stack<>();

        stack.push(-1);

        for (int i = 0; i < s.length(); i++)
        {
            if (s.charAt(i) == '(')
            {
                stack.push(i);
            }
            else
            {
                stack.pop();
                if (stack.empty())
                {
                    stack.push(i);
                }
                else
                {
                    maxans = Math.max(maxans, i - stack.peek());
                }
            }
        }
        return maxans;
    }

    public static int method2 ( String s )
    {
        int left = 0, right = 0, maxlength = 0;

        for (int i = 0; i < s.length(); i++)
        {
            if (s.charAt(i) == '(')
            {
                left++;
            }
            else
            {
                right++;
            }
            if (left == right)
            {
                maxlength = Math.max(maxlength, 2 * right);
            }
            else if (right >= left)
            {
                left = right = 0;
            }
        }

        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--)
        {
            if (s.charAt(i) == '(')
            {
                left++;
            }
            else
            {
                right++;
            }
            if (left == right)
            {
                maxlength = Math.max(maxlength, 2 * right);
            }
            else if (left >= right)
            {
                left = right = 0;
            }
        }

        return maxlength;
    }

    public static void main(String[] args) {
        String s = ")()())";

        /*
            Time Complexity : O ( N ), N is length of string.
            Space Complexity : O ( N )
        */
        System.out.println( method1(s) );

        /*
            Time Complexity : O ( N ), N is length of string.
            Space Complexity : O ( 1 )
        */
        System.out.println( method2(s) );

    }
}
