package InterviewPrep.Module_3;
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

    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        PriorityQueue<Integer> q = new PriorityQueue<>( (a,b) -> b - a );

        int i = 0;
        List<Integer> res = new ArrayList<>();

        for( int j = 0; j < n; j++ )
        {
            q.add( nums[j] );

            if( j >= k -1 )
            {
                res.add( q.peek() );
                if( nums[i++] == q.peek() )
                    q.poll();
            }
        }

        return res.stream().mapToInt( j -> j ).toArray();
    }
}

public class Java_3 {
    public static void main(String[] args) {
        Solution obj = new Solution();
        System.out.println(Arrays.toString(obj.maxSlidingWindow(new int[]{9, 10, 9, -7, -4, -8, 2, -6}, 5)));
    }
}