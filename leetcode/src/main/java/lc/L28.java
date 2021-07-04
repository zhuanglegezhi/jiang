package lc;

/**
 * Created by zz on 2021/7/4.
 */
public class L28 {

    public int strStr(String haystack, String needle) {
        if (haystack == null || needle == null || haystack.length() < needle.length()) return -1;
        if (needle.length() == 0) return 0;
        for (int start = 0; start + needle.length() <= haystack.length(); start++) {
            int i = start;
            int j = 0;
            while (i < haystack.length() &&
                    j < needle.length() &&
                    haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;

                if (j == needle.length()) {
                    return start;           // 终止条件
                }
            }

        }
        return -1;
    }
}
