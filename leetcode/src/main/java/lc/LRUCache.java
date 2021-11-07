package lc;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zz on 2021/11/7.
 */
public class LRUCache {

    private final Map<Integer, Node> map;
    private final int capacity;
    // 链表
    private final Node head;
    private final Node tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>(capacity);
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.pre = head;
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node != null) {
            moveNodeToHead(node);
            return node.value;
        }
        return -1;
    }

    private void moveNodeToHead(Node node) {
        removeNode(node);
        addHead(node);
    }

    public void put(int key, int value) {
        Node node = new Node();
        node.key = key;
        node.value = value;

        if (map.containsKey(key)) {
            removeNode(map.get(key));
        }
        addHead(node);
        map.put(key, node);

        if (map.size() > capacity) {
            Node moved = removeTail();
            this.map.remove(moved.key);

            moved.pre = null;
            moved.next = null;
        }
    }

    private void addHead(Node node) {
        Node headAfter = head.next;
        head.next = node;
        node.pre = head;
        node.next = headAfter;
        headAfter.pre = node;
    }

    private void removeNode(Node node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    private Node removeTail() {
        Node moved = tail.pre;
        moved.pre.next = tail;
        tail.pre = moved.pre;
        return moved;
    }

    private static class Node {
        Integer key;
        Integer value;
        Node pre;
        Node next;

        public Node() {
        }
    }

}
