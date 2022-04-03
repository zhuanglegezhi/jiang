package bridge_method;

import java.lang.reflect.Method;

/**
 * Created by zz on 2022/3/20.
 */
public class BridgeTest {
    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> clazz = Class.forName("bridge_method.TestOperator");
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method method : declaredMethods) {
            System.out.println(method.getName() + ", isBridge : " + method.isBridge());
        }
    }
}
