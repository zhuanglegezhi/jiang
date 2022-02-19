package 剑指;

/**
 * Created by zz on 2021/12/18.
 */
public class O18 {

    public boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left <= right) {
            if (!Character.isLetterOrDigit(s.charAt(left))) {
                left += 1;
            } else if (!Character.isLetterOrDigit(s.charAt(right))) {
                right -= 1;
            } else {
                char char1 = Character.toLowerCase(s.charAt(left++));
                char char2 = Character.toLowerCase(s.charAt(right--));
                if (char1 != char2) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        boolean result = new O18().isPalindrome("0P");
        System.out.println(result);
    }
}
