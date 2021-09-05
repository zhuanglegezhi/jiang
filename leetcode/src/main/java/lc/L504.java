package lc;

/**
 * Created by zz on 2021/9/5.
 */
public class L504 {

    public String convertToBase7(int num) {
        if (num < 0) return "-" + convertToBase7(-1 * num);
        if (num < 7) return Integer.toString(num);
        return convertToBase7(num / 7) + Integer.toString(num % 7);
    }

}
