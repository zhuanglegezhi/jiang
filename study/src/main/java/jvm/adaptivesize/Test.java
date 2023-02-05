package jvm.adaptivesize;

import java.util.UUID;

/**
 * Created by zz on 2022/12/4.
 */
public class Test {

    /***
     *
     * -XX:+UseG1GC
     * -Xms4G
     * -Xmx4G
     * -XX:+PrintGCDetails
     * -XX:+PrintAdaptiveSizePolicy
     * -XX:+UnlockExperimentalVMOptions
     * -XX:G1LogLevel=finest
     *
     * @param args
     */
    public static void main(String[] args) {
        while (true) {
            String s = UUID.randomUUID().toString().intern();
        }
    }
}
