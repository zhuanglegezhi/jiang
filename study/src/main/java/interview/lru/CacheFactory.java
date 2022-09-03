package interview.lru;

/**
 * Created by zz on 2022/4/7.
 */
public class CacheFactory<K, V> implements Cache<K, V> {

    private final Cache<K, V> cache;

    public CacheFactory(Strategy strategy, int capacity) {
        this.cache = strategy == Strategy.LRU ? new LRUCache<>(capacity) : new LFUCache<>(capacity);
    }

    @Override
    public V get(K key) {
        return this.cache.get(key);
    }

    @Override
    public void put(K key, V value) {
        this.cache.put(key, value);
    }


    public enum Strategy {
        LRU, LFU
    }

}
