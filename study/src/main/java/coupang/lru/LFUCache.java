package coupang.lru;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by zz on 2022/4/7.
 * <p>
 * Least Frequently Used
 */
public class LFUCache<K, V> implements Cache<K, V> {

    private final int capacity;
    /**
     * cacheMap，存储数据
     */
    private final Map<K, Node<K, V>> map;

    /**
     * frequent -> Nodes
     */
    private final Map<Integer, LinkedList<Node<K, V>>> freqMap;
    private int minFreq;

    public LFUCache(int capacity) {
        this.minFreq = 0;
        this.capacity = capacity;
        map = new HashMap<>(capacity);
        freqMap = new HashMap<>();
    }

    @Override
    public V get(K key) {
        Node<K, V> node = map.get(key);
        if (node == null) {
            return null;
        }

        V value = node.value;
        int freq = node.freq;

        freqMap.get(freq).remove(node);
        // 如果当前链表为空，我们需要在哈希表中删除，且更新minFreq
        if (freqMap.get(freq).size() == 0) {
            freqMap.remove(freq);
            if (minFreq == freq) {
                minFreq += 1;
            }
        }

        // 插入到 freq + 1 中
        LinkedList<Node<K, V>> list = freqMap.getOrDefault(freq + 1, new LinkedList<>());
        list.offerFirst(new Node<K, V>(key, value, freq + 1));
        freqMap.put(freq + 1, list);
        map.put(key, freqMap.get(freq + 1).peekFirst());
        return value;
    }

    @Override
    public void put(K key, V value) {
        if (capacity == 0) {
            return;
        }

        if (!map.containsKey(key)) {
            // 缓存已满，需要进行删除操作
            if (map.size() == capacity) {
                // 通过 minFreq 拿到 freq_table[minFreq] 链表的末尾节点
                Node<K, V> node = freqMap.get(minFreq).peekLast();
                map.remove(node.key);
                freqMap.get(minFreq).pollLast();
                if (freqMap.get(minFreq).size() == 0) {
                    freqMap.remove(minFreq);
                }
            }
            LinkedList<Node<K, V>> list = freqMap.getOrDefault(1, new LinkedList<>());
            list.offerFirst(new Node(key, value, 1));
            freqMap.put(1, list);
            map.put(key, freqMap.get(1).peekFirst());
            minFreq = 1;
        } else {
            // 与 get 操作基本一致，除了需要更新缓存的值
            Node<K, V> node = map.get(key);
            int freq = node.freq;
            freqMap.get(freq).remove(node);
            if (freqMap.get(freq).size() == 0) {
                freqMap.remove(freq);
                if (minFreq == freq) {
                    minFreq += 1;
                }
            }
            LinkedList<Node<K, V>> list = freqMap.getOrDefault(freq + 1, new LinkedList<>());
            list.offerFirst(new Node<K, V>(key, value, freq + 1));
            freqMap.put(freq + 1, list);
            map.put(key, freqMap.get(freq + 1).peekFirst());
        }
    }

    static class Node<K, V> {

        private final K key;
        private final V value;
        private final int freq;

        Node(K key, V value, int freq) {
            this.key = key;
            this.value = value;
            this.freq = freq;
        }
    }

}
