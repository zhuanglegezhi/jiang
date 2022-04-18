package jvm;

import java.util.UUID;

/**
 * Created by zz on 2022/4/11.
 */
public class GCLog {

    //-XX:+UseG1GC -Xmx15m  -Xms15m -XX:+PrintGCDetails -XX:+UnlockExperimentalVMOptions -XX:G1LogLevel=finest
    public static void main(String[] args) {
        while (true){
            UUID.randomUUID().toString().intern();
        }
    }
}
