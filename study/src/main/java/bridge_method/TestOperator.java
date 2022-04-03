package bridge_method;

/**
 * Created by zz on 2022/3/20.
 */
public class TestOperator implements Operator<String> {
    @Override
    public void process(String s) {
        System.out.println(s);
    }
}
