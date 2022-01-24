package Helper;

public class ListNode {
    public int val;
    public ListNode next;
    public ListNode() {}
    public ListNode(int val) { this.val = val; }
    public ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    public static void print(ListNode head){
        StringBuilder str = new StringBuilder();
        while(head != null) {
            str.append(head.val).append(" -> ");
            head = head.next;
        }
        str.setLength(str.length()-4);
        System.out.println(str.toString());
    }
}