package coupang;

/**
 * Created by zz on 2022/4/7.
 */
public interface Cache<K, V> {

    V get(K key);

    void put(K key, V value);

}
