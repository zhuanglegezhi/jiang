package spi.java;

import java.util.ServiceLoader;

/**
 * Created by zz on 2022/5/16.
 */
public class Test {
    public static void main(String[] args) {
        ServiceLoader<Superman> serviceLoader = ServiceLoader.load(Superman.class);
        System.out.println("Java SPI:");
        serviceLoader.forEach(Superman::introduce);
    }
}
