package lc;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by zz on 2021/11/8.
 */
public class LFUCache {

    int minfreq, capacity;
    Map<Integer, Node> key_table;
    Map<Integer, LinkedList<Node>> freq_table;

    public LFUCache(int capacity) {
        this.minfreq = 0;
        this.capacity = capacity;
        key_table = new HashMap<>();
        freq_table = new HashMap<>();
    }

    public int get(int key) {
        if (capacity == 0) {
            return -1;
        }

        if (!key_table.containsKey(key)) {
            return -1;
        }

        Node node = key_table.get(key);
        int val = node.val, freq = node.freq;
        freq_table.get(freq).remove(node);
        // 如果当前链表为空，我们需要在哈希表中删除，且更新minFreq
        if (freq_table.get(freq).size() == 0) {
            freq_table.remove(freq);
            if (minfreq == freq) {
                minfreq += 1;
            }
        }

        // 插入到 freq + 1 中
        LinkedList<Node> list = freq_table.getOrDefault(freq + 1, new LinkedList<>());
        list.offerFirst(new Node(key, val, freq + 1));
        freq_table.put(freq + 1, list);
        key_table.put(key, freq_table.get(freq + 1).peekFirst());
        return val;
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }

        if (!key_table.containsKey(key)) {
            // 缓存已满，需要进行删除操作
            if (key_table.size() == capacity) {
                // 通过 minFreq 拿到 freq_table[minFreq] 链表的末尾节点
                Node node = freq_table.get(minfreq).peekLast();
                key_table.remove(node.key);
                freq_table.get(minfreq).pollLast();
                if (freq_table.get(minfreq).size() == 0) {
                    freq_table.remove(minfreq);
                }
            }
            LinkedList<Node> list = freq_table.getOrDefault(1, new LinkedList<Node>());
            list.offerFirst(new Node(key, value, 1));
            freq_table.put(1, list);
            key_table.put(key, freq_table.get(1).peekFirst());
            minfreq = 1;
        } else {
            // 与 get 操作基本一致，除了需要更新缓存的值
            Node node = key_table.get(key);
            int freq = node.freq;
            freq_table.get(freq).remove(node);
            if (freq_table.get(freq).size() == 0) {
                freq_table.remove(freq);
                if (minfreq == freq) {
                    minfreq += 1;
                }
            }
            LinkedList<Node> list = freq_table.getOrDefault(freq + 1, new LinkedList<Node>());
            list.offerFirst(new Node(key, value, freq + 1));
            freq_table.put(freq + 1, list);
            key_table.put(key, freq_table.get(freq + 1).peekFirst());
        }
    }

    class Node {
        int key, val, freq;

        Node(int key, int val, int freq) {
            this.key = key;
            this.val = val;
            this.freq = freq;
        }
    }

}
