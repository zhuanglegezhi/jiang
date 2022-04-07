package coupang;

import java.util.HashMap;
import java.util.Map;

/**
 * Least Recently Used，即最近最少使用
 *
 * @param <K>
 * @param <V>
 */
public class LRUCache<K, V> implements Cache<K, V> {

    private final Map<K, Node<K, V>> map;
    private final int capacity;
    // 链表
    private final Node<K, V> head;
    private final Node<K, V> tail;


    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>(capacity);
        head = new Node<>();
        tail = new Node<>();
        head.next = tail;
        tail.pre = head;
    }

    @Override
    public V get(K key) {
        Node<K, V> node = map.get(key);
        if (node != null) {
            moveNodeToHead(node);
            return node.value;
        }
        return null;
    }

    private void moveNodeToHead(Node<K, V> node) {
        removeNode(node);
        addHead(node);
    }

    @Override
    public void put(K key, V value) {
        Node<K, V> node = new Node<>();
        node.key = key;
        node.value = value;

        if (map.containsKey(key)) {
            removeNode(map.get(key));
        }
        addHead(node);
        map.put(key, node);

        if (map.size() > capacity) {
            Node<K, V> moved = removeTail();
            this.map.remove(moved.key);

            moved.pre = null;
            moved.next = null;
        }
    }

    private void addHead(Node<K, V> node) {
        Node<K, V> headAfter = head.next;
        head.next = node;
        node.pre = head;
        node.next = headAfter;
        headAfter.pre = node;
    }

    private void removeNode(Node<K, V> node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    private Node<K, V> removeTail() {
        Node<K, V> moved = tail.pre;
        moved.pre.next = tail;
        tail.pre = moved.pre;
        return moved;
    }

    private static class Node<K, V> {

        K key;
        V value;
        Node<K, V> pre;
        Node<K, V> next;

        public Node() {
        }

    }

    public static void main(String[] args) {
        LRUCache<Integer, Integer> lruCache = new LRUCache<>(3);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        lruCache.put(3, 3);
        Integer cache_2 = lruCache.get(2);

        lruCache.put(4, 4);
        lruCache.put(5, 5);
    }


}
