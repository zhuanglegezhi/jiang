package lc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by zz on 2021/11/11.
 */
public class RandomizedSet {

    private final List<Integer> list = new ArrayList<>();
    /**
     * val -> idx
     */
    private final Map<Integer, Integer> map = new HashMap<>();

    public RandomizedSet() {

    }

    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        }
        list.add(val);
        map.put(val, list.size() - 1);
        return true;
    }

    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }
        Integer idx = map.get(val);
        Integer last = list.get(list.size() - 1);
        map.put(last, idx);
        list.set(idx, last);

        list.set(list.size() - 1, val);

        list.remove(list.size() - 1);
        map.remove(val);
        return true;
    }

    public int getRandom() {
        return list.get(new Random().nextInt(list.size()));
    }
}
