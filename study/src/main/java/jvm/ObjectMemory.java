package jvm;

import org.openjdk.jol.info.ClassLayout;

/**
 * Created by zz on 2022/4/18.
 */
public class ObjectMemory {

    public static void main(String[] args) {
        A a = new A();
        System.out.println("------After Initialization------\n" + ClassLayout.parseInstance(a).toPrintable());
    }

    static class A {
    }
}
