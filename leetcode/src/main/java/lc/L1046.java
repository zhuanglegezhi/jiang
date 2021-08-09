package lc;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by zz on 2021/8/9.
 */
public class L1046 {

    public int lastStoneWeight(int[] stones) {
        Queue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
        for (int stone : stones) {
            queue.offer(stone);
        }

        while (queue.size() > 1) {
            int y = queue.poll();
            int x = queue.poll();
            if (x != y) {
                queue.offer(y - x);
            }
        }
        if (queue.isEmpty()) {
            return 0;
        } else {
            return queue.peek();
        }
    }
}
