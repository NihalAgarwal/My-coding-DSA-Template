package Helper;
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) {
        val = x;
        left=null;
        right=null;
    }
    public String toString(){
        return String.valueOf(val);
    }
}

