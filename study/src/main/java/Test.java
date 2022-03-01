import java.util.Arrays;
import java.util.List;

/**
 * Created by zz on 2022/3/1.
 */
public class Test {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("s1", "s2", "s3", "s4");
        list.stream().map(s -> s + "_sub").forEach(System.out::println);
    }
}
