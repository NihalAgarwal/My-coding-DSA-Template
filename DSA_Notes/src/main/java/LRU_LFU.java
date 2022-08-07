import java.util.*;

/***
 * LRU : complement implementation of Doubly-Linked List
 */
class LRUCache {
    static class Node
    {
        int key;
        int val;
        Node prev;
        Node next;

        Node( int key, int val )
        {
            this.key = key;
            this.val = val;
        }
    }

    Node head;
    Node tail;
    HashMap<Integer, Node> map;
    int cap;

    public LRUCache(int capacity) {
        map = new HashMap<>();
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        cap = capacity;
        head.next = tail;
        tail.prev = head;
    }

    public void removeFront()
    {
        Node temp = head.next;
        temp.prev = null;
        head.next = temp.next;
        temp.next = null;
        head.next.prev = head;

        map.remove(temp.key);
    }

    public void remove( Node node )
    {
        Node prev = node.prev;
        Node next = node.next;

        prev.next = next;
        next.prev = prev;

        node.next = node.prev = null;
    }

    public void addLast( Node node )
    {
        Node prev = tail.prev;
        prev.next = node;
        node.prev = prev;
        node.next = tail;
        tail.prev = node;
    }

    public int get(int key) {
        if( map.containsKey(key) )
        {
            Node temp = map.get(key);
            remove( temp );
            addLast( temp );
            return temp.val;
        }
        return -1;
    }

    public void put(int key, int value) {
        if( map.containsKey(key) )
        {
            Node temp = map.get(key);
            remove( temp );
            addLast( temp );
            temp.val = value;
        }
        else
        {
            if( map.size() == cap )
            {
                removeFront();
            }
            Node temp = new Node(key, value);
            addLast( temp );
            map.put( key, temp );
        }
    }
}

class LFUCache {

    public LFUCache(int capacity) {

    }

    public int get(int key) {
        return 0;
    }

    public void put(int key, int value) {

    }
}

public class LRU_LFU {
    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1);                     // cache is {1=1}
        lRUCache.put(2, 2);                     // cache is {1=1, 2=2}
        System.out.println(lRUCache.get(1));    // return 1
        lRUCache.put(3, 3);                     // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
        System.out.println(lRUCache.get(2));    // returns -1 (not found)
        lRUCache.put(4, 4);                     // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
        System.out.println(lRUCache.get(1));    // return -1 (not found)
        System.out.println(lRUCache.get(3));    // return 3
        System.out.println(lRUCache.get(4));    // return 4
    }
}
