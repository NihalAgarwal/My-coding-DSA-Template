package Helper;
import java.util.*;

public class Tree {
    public static final int[] neighbour_row = {-1, 0, 1, 0, -1, 1, 1, -1};
    public static final int[] neighbour_col = {0, 1, 0, -1, 1, 1, -1, -1};

    // used by treeTraversal()
    static class pair{
        TreeNode node;
        int x;
        pair(TreeNode node, int x) {
            this.node = node;
            this.x = x;
        }
    }

    //Build Tree from the array and return the root
    public TreeNode buildTree(String string){
        if (string.equals("{}") )   return null;

        ArrayList<TreeNode> nodes = new ArrayList<>();
        Deque<TreeNode> kids = new LinkedList<>();

        for(String val : string.replaceAll("[\\[{\\]}]", "").split(",")){
            if( val.equals("null") )
                nodes.add(null);
            else
                nodes.add(new TreeNode(Integer.parseInt(val)));
            kids.addFirst(nodes.get(nodes.size()-1));
        }
        TreeNode root = kids.pollLast();

        for( TreeNode node : nodes ){
            if( node != null){
                if( !kids.isEmpty()) node.left = kids.pollLast();
                if( !kids.isEmpty()) node.right = kids.pollLast();
            }
        }
        return root;
    }

    // Take root node as input, and return the array which was given as input to "buildTree()"
    public static void inorder(TreeNode root){
        if( root == null){
            System.out.print("null" + " ");
            return;
        }
        inorder(root.left);
        System.out.print(root.val + " ");
        inorder(root.right);
    }

    public static void preorder(TreeNode root){
        if( root == null){
            return;
        }
        System.out.print(root.val + " ");
        preorder(root.left);
        preorder(root.right);
    }

    // Print the preorder, inorder, postorder of the tree.
    public static void treeTraversal(TreeNode A){
        if( A == null) return;
        StringBuilder pre = new StringBuilder("The preorder of a tree is  : ");
        StringBuilder in = new StringBuilder("The inorder of a tree is   : ");
        StringBuilder pos = new StringBuilder("The postorder of a tree is : ");

        Stack<pair> st = new Stack<>();
        st.push(new pair(A, 0));
        pair cur = null;
        while ( !st.isEmpty() || cur != null){
            cur = st.peek();
            if ( cur.x == 0){
                pre.append(cur.node.val).append(" ");
                if ( cur.node.left != null ) st.push(new pair(cur.node.left, 0));
                cur.x++;
            }
            else if ( cur.x == 1){
                in.append(cur.node.val).append(" ");
                if ( cur.node.right != null ) st.push(new pair(cur.node.right, 0));
                cur.x++;
            }
            else{
                pos.append(cur.node.val).append(" ");
                st.pop();
                cur = null;
            }
        }
        System.out.println(pre + "\n" + in + "\n" + pos + "\n");
    }

    // for demo purpose
    public static void main(String[] args) {
//        TreeNode root = new Tree().buildTree("{5,2,4,null,1}");
//        treeTraversal(root);
//        inorder(root);
    }

}  