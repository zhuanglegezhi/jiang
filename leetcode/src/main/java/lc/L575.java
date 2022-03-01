package lc;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by zz on 2021/9/5.
 */
public class L575 {

    public int distributeCandies(int[] candyType) {
        Set<Integer> set = new HashSet<>();
        int count = 0;
        for (int i = 0; i < candyType.length && count != candyType.length/2; i++) {
            if (!set.contains(candyType[i])) {
                count++;
                set.add(candyType[i]);
            }
        }
        return set.size();
    }

}
