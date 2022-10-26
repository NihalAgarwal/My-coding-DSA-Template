package DSA_Notes;

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

    static class Node
    {
        int key;
        int freq;
        int val;
        Node prev;
        Node next;

        Node( int key, int val )
        {
            this.key = key;
            this.val = val;
            freq = 1;
        }
    }

    static class DoublyLinkedList
    {
        Node head;
        Node tail;

        DoublyLinkedList()
        {
            head = new Node( -1, -1 );
            tail = new Node( -1, -1 );
            head.next = tail;
            tail.prev = head;
        }

        public void addLast( Node node )
        {
            Node prev = tail.prev;
            prev.next = node;
            node.prev = prev;
            tail.prev = node;
            node.next = tail;
        }

        public int removeFirst()
        {
            int val = head.next.key;
            Node next = head.next.next;
            head.next.prev = head.next.next = null;
            next.prev = head;
            head.next = next;

            return val;
        }

        public void removeInBetween( Node node )
        {
            Node prev = node.prev;
            Node next = node.next;
            prev.next = next;
            next.prev = prev;
            node.next = node.prev = null;
        }

        public boolean isEmpty()
        {
            return head.next.next == null;
        }
    }

    HashMap<Integer, DoublyLinkedList> listOfList;
    HashMap<Integer, Node> map;
    int min;
    int capacity;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        listOfList = new HashMap<>();
        map = new HashMap<>();
        min = 0;
    }

    public void changeFrequencey( Node node )
    {
        listOfList.get( node.freq++ ).removeInBetween( node );
        listOfList.putIfAbsent( node.freq, new DoublyLinkedList() );
        listOfList.get( node.freq ).addLast( node );

        if( min == node.freq - 1 && listOfList.get( node.freq - 1 ).isEmpty() )
            min++;
    }

    public int get(int key) {
        if( !map.containsKey( key ) )
            return -1;

        Node node = map.get( key );
        changeFrequencey( node );
        return node.val;
    }

    public void put(int key, int value) {

        if( capacity == 0 )
            return;

        if( map.containsKey( key ) )
        {
            Node node = map.get( key );
            node.val = value;
            changeFrequencey( node );
        }
        else {
            if (map.size() == capacity) {
                int k = listOfList.get(min).removeFirst();
                map.remove(k);
            }
            Node node = new Node(key, value);
            map.put(key, node);
            listOfList.putIfAbsent(1, new DoublyLinkedList());
            listOfList.get(1).addLast(node);
            min = 1;
        }
    }
}

public class LRU_LFU {
    public static void main(String[] args) {
        System.out.println( "LRU:" );
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

        System.out.println( '\n' + "LFU:" );
        LFUCache lfu = new LFUCache(2);
        lfu.put(1, 1);                      // cache=[1,_], cnt(1)=1
        lfu.put(2, 2);                      // cache=[2,1], cnt(2)=1, cnt(1)=1
        System.out.println(lfu.get(1));     // return 1  cache=[1,2], cnt(2)=1, cnt(1)=2
        lfu.put(3, 3);                      // 2 is the LFU key because cnt(2)=1 is the smallest, invalidate 2. cache=[3,1], cnt(3)=1, cnt(1)=2
        System.out.println(lfu.get(2));     // return -1 (not found)
        System.out.println(lfu.get(3));     // return 3 cache=[3,1], cnt(3)=2, cnt(1)=2
        lfu.put(4, 4);                      // Both 1 and 3 have the same cnt, but 1 is LRU, invalidate 1. cache=[4,3], cnt(4)=1, cnt(3)=2
        System.out.println(lfu.get(1));     // return -1 (not found)
        System.out.println(lfu.get(3));     // return 3 cache=[3,4], cnt(4)=1, cnt(3)=3
        System.out.println(lfu.get(4));     // return 4 cache=[4,3], cnt(4)=2, cnt(3)=3
    }
}
