package Helper_Classes;

public class MyLinkedList {
    private ListNode head;

    public ListNode getHead() {
        return head;
    }

    public void setHead(ListNode head) {
        this.head = head;
    }

    public MyLinkedList(String string) {
        ListNode temp = new ListNode(0);
        this.head = temp;
        for (String val : string.replaceAll("[\\[{\\]}]", "").split(",")) {
            temp.next = new ListNode(Integer.parseInt(val));
            temp = temp.next;
        }
        this.head = head.next;
    }
}

