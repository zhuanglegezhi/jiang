package lc;

/**
 * Created by zz on 2021/12/4.
 */
public class L698 {


    public boolean canPartitionKSubsets(int[] nums, int k) {
        int total = 0;
        for (int n : nums) {
            total += n;
        }
        if (total % k != 0) {
            return false;
        }
        int target = total / k;
        return backtrack(nums, 0, k, 0, new boolean[nums.length], target);
    }

    private boolean backtrack(int[] nums, int bucket, int k, int start, boolean[] visited, int target) {
        if (k == 0) return true;
        if (bucket == target) {
            return backtrack(nums, 0, k - 1, 0, visited, target);
        }

        // 从 start 开始向后探查有效的 nums[i] 装入当前桶
        for (int i = start; i < nums.length; i++) {
            // 剪枝
            if (visited[i]) {
                // nums[i] 已经被装入别的桶中
                continue;
            }
            if (nums[i] + bucket > target) {
                // 当前桶装不下 nums[i]
                continue;
            }
            // 做选择，将 nums[i] 装入当前桶中
            visited[i] = true;
            bucket += nums[i];
            // 递归穷举下一个数字是否装入当前桶
            if (backtrack(nums, bucket, k, i + 1, visited, target)) {
                return true;
            }
            // 撤销选择
            visited[i] = false;
            bucket -= nums[i];
        }
        // 穷举了所有数字，都无法装满当前桶
        return false;
    }

}
